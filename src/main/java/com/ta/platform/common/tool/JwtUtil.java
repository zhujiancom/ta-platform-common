package com.ta.platform.common.tool;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ey.tax.toolset.core.PropertyUtil;
import com.ey.tax.toolset.core.RandomUtil;
import com.ey.tax.toolset.core.StrUtil;
import com.fasterxml.uuid.impl.UUIDUtil;
import com.google.common.base.Joiner;
import com.ta.platform.common.constant.DataBaseConstant;
import com.ta.platform.common.constant.RequestConstant;
import com.ta.platform.common.exception.PlatformException;
import com.ta.platform.common.system.model.SysUserCacheInfo;
import com.ta.platform.common.web.WebContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.util.Date;

/**
 * JWT 工具类
 */
@Slf4j
public class JwtUtil {

    // 过期时间30分钟
    public static final long EXPIRE_TIME = 30 * 60 * 1000;

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            // 根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            // 效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            if (jwt != null) {
                return true;
            }
        } catch (Exception exception) {
            log.error("Verify Token Exception", exception);
        }
        return false;
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        if (StrUtil.isBlank(token)) {
            return null;
        }
        DecodedJWT decodedJWT = getJwtInfo(token);
        if (decodedJWT == null) {
            return null;
        }
        return decodedJWT.getClaim("username").asString();
    }

    /**
     * 生成签名,5min后过期
     *
     * @param username 用户名
     * @param secret   用户的密码
     * @return 加密的token
     */
    public static String sign(String username, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create().withClaim("username", username).withExpiresAt(date).sign(algorithm);
    }

    /**
     * @param username       用户名
     * @param secret         用户密码
     * @param expireDuration 过期时间
     * @return
     */
    public static String sign(String username, String secret, Duration expireDuration) {
        try {
            if (StrUtil.isBlank(username)) {
                log.error("username不能为空");
                return null;
            }
            log.debug("username:{}", username);

            if (StrUtil.isBlank(secret)) {
                secret = PropertyUtil.getString("platform.jwt.secret");
            }

            log.debug("secret:{}", secret);

            // 过期时间，单位：秒
            Long expireSecond;
            // 默认过期时间为1小时
            if (expireDuration == null) {
                expireSecond = PropertyUtil.getLong("platform.jwt.expiresecond");
            } else {
                expireSecond = expireDuration.getSeconds();
            }
            log.debug("expireSecond:{}", expireSecond);
            Date expireDate = DateUtils.addSeconds(new Date(), expireSecond.intValue());
            log.debug("expireDate:{}", expireDate);

            // 生成token
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withClaim("username", username)
                    .withJWTId(RandomUtil.getUUIDBaseOnV2())
                    .withIssuedAt(new Date())       // 签名时间
                    .withExpiresAt(expireDate)      // 过期时间
                    .sign(algorithm);
            return token;
        } catch (Exception e) {
            log.error("generate token occurs error.", e);
        }
        return null;
    }

    /**
     * 根据request中的token获取用户账号
     *
     * @param request
     * @return
     * @throws PlatformException
     */
    public static String getUserNameByRequest(HttpServletRequest request) throws PlatformException {
        String accessToken = request.getHeader(RequestConstant.X_ACCESS_TOKEN);
        String username = getUsername(accessToken);
        if (StrUtil.isEmpty(username)) {
            throw new PlatformException("未获取到用户");
        }
        return username;
    }

    /**
     * 从session中获取变量
     *
     * @param key
     * @return
     */
    public static String getSessionData(String key) {
        //${myVar}%
        //得到${} 后面的值
        String moshi = "";
        if (key.indexOf("}") != -1) {
            moshi = key.substring(key.indexOf("}") + 1);
        }
        String returnValue = null;
        if (key.contains("#{")) {
            key = key.substring(2, key.indexOf("}"));
        }
        if (StrUtil.isNotEmpty(key)) {
            HttpSession session = ApplicationContextProvider.getHttpServletRequest().getSession();
            returnValue = (String) session.getAttribute(key);
        }
        //结果加上${} 后面的值
        if (returnValue != null) {
            returnValue = returnValue + moshi;
        }
        return returnValue;
    }

    /**
     * 从当前用户中获取变量
     *
     * @param key
     * @param user
     * @return
     */
    public static String getUserSystemData(String key, SysUserCacheInfo user) {
        if (user == null) {
            user = WebContext.loadLoginUserInfo();
        }
        //#{sys_user_code}%
        String moshi = "";
        if (key.indexOf("}") != -1) {
            moshi = key.substring(key.indexOf("}") + 1);
        }
        String returnValue = null;
        //针对特殊标示处理#{sysOrgCode}，判断替换
        if (key.contains("#{")) {
            key = key.substring(2, key.indexOf("}"));
        } else {
            key = key;
        }

        //替换为系统登录用户ID
        if (key.equals(DataBaseConstant.SYS_USER_ID) || key.equals(DataBaseConstant.SYS_USER_ID_TABLE)) {
            returnValue = user.getSysUserId();
        }

        //替换为系统登录用户帐号
        if (key.equals(DataBaseConstant.SYS_USER_CODE) || key.equals(DataBaseConstant.SYS_USER_CODE_TABLE)) {
            returnValue = user.getSysUserCode();
        }
        //替换为系统登录用户真实名字
        if (key.equals(DataBaseConstant.SYS_USER_NAME) || key.equals(DataBaseConstant.SYS_USER_NAME_TABLE)) {
            returnValue = user.getSysUserName();
        }


        //替换为系统用户登录所使用的机构编码
        if (key.equals(DataBaseConstant.SYS_ORG_CODE) || key.equals(DataBaseConstant.SYS_ORG_CODE_TABLE)) {
            returnValue = user.getSysOrgCode();
        }

        //替换为系统用户所拥有的所有机构编码
        if (key.equals(DataBaseConstant.SYS_MULTI_ORG_CODE) || key.equals(DataBaseConstant.SYS_MULTI_ORG_CODE_TABLE)) {
            if (user.isOneDepart()) {
                returnValue = user.getSysMultiOrgCode().get(0);
            }
            returnValue = Joiner.on(",").join(user.getSysMultiOrgCode());
        }

        if (key.equals(DataBaseConstant.SYS_DEPART_CODE) || key.equals(DataBaseConstant.SYS_DEPART_CODE_TABLE)) {
            returnValue = user.getSysDepartCode();
        }

        //替换为当前系统时间(年月日)
        if (key.equals(DataBaseConstant.SYS_DATE) || key.equals(DataBaseConstant.SYS_DATE_TABLE)) {
            returnValue = user.getSysDate();
        }
        //替换为当前系统时间（年月日时分秒）
        if (key.equals(DataBaseConstant.SYS_TIME) || key.equals(DataBaseConstant.SYS_TIME_TABLE)) {
            returnValue = user.getSysTime();
        }

        //流程状态默认值（默认未发起）
        else if (key.equals(DataBaseConstant.BPM_STATUS) || key.equals(DataBaseConstant.BPM_STATUS_TABLE)) {
            returnValue = "1";
        }

        if (returnValue != null) {
            returnValue = returnValue + moshi;
        }
        return returnValue;
    }

    /**
     * 获取创建时间
     *
     * @param token
     * @return
     */
    public static Date getIssuedAt(String token) {
        DecodedJWT decodedJwt = getJwtInfo(token);
        if (decodedJwt == null) {
            return null;
        }
        return decodedJwt.getIssuedAt();
    }

    /**
     * 获取过期时间
     *
     * @param token
     * @return
     */
    public static Date getExpireDate(String token) {
        DecodedJWT decodedJWT = getJwtInfo(token);
        if (decodedJWT == null) {
            return null;
        }
        return decodedJWT.getExpiresAt();
    }

    /**
     * 解析token，获取token数据
     *
     * @param token
     * @return
     */
    public static DecodedJWT getJwtInfo(String token) {
        return JWT.decode(token);
    }

    public static boolean isExpired(String token) {
        Date expireDate = getExpireDate(token);
        if (expireDate == null) {
            return true;
        }
        return expireDate.before(new Date());
    }

    /**
     * 从请求头或者请求参数中
     *
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("request不能为空");
        }
        // 从请求头中获取token
        String token = request.getHeader(RequestConstant.X_ACCESS_TOKEN);
        if (StrUtil.isBlank(token)) {
            // 从请求参数中获取token
            token = request.getParameter(RequestConstant.X_ACCESS_TOKEN);
        }
        return token;
    }
}
