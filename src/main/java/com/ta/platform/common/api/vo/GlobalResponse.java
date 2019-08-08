package com.ta.platform.common.api.vo;

import com.ta.platform.common.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Creator: zhuji
 * Date: 8/7/2019
 * Time: 11:34 AM
 * Description: 全局返回对象
 */
@Data
@ApiModel(value="接口返回对象", description="接口返回对象")
public class GlobalResponse<T> implements Serializable {

    private static final long serialVersionUID = -9045332763706343551L;

    /**
     * 成功标志
     */
    @ApiModelProperty(value = "成功标志")
    private boolean success = true;

    private Integer code = 0;

    /**
     * 返回处理消息
     */
    @ApiModelProperty(value = "返回处理消息")
    private String msg = "操作成功！";

    private T data;

    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private long timestamp = System.currentTimeMillis();

    private GlobalResponse(){}

    public static GlobalResponse success(){
        GlobalResponse response = new GlobalResponse();
        response.setSuccess(true);
        response.setCode(CommonConstant.SC_OK_200);
        response.setMsg("操作成功");
        return response;
    }

    public static GlobalResponse success(String message){
        GlobalResponse response = new GlobalResponse();
        response.setSuccess(true);
        response.setCode(CommonConstant.SC_OK_200);
        response.setMsg(message);
        return response;
    }

    public static <T> GlobalResponse<T> success(T data){
        GlobalResponse response = new GlobalResponse();
        response.setSuccess(true);
        response.setCode(CommonConstant.SC_OK_200);
        response.setMsg("操作成功");
        response.setData(data);
        return response;
    }

    public static <T> GlobalResponse<T> success(String message, T data){
        GlobalResponse response = new GlobalResponse();
        response.setSuccess(true);
        response.setCode(CommonConstant.SC_OK_200);
        response.setMsg(message);
        response.setData(data);
        return response;
    }

    public static <T> GlobalResponse<T> error(String message){
        GlobalResponse response = new GlobalResponse();
        response.setSuccess(false);
        response.setCode(CommonConstant.SC_INTERNAL_SERVER_ERROR_500);
        response.setMsg(message);
        return response;
    }

    public static <T> GlobalResponse<T> error(int code, String message){
        GlobalResponse response = new GlobalResponse();
        response.setSuccess(false);
        response.setCode(code);
        response.setMsg(message);
        return response;
    }
}
