package com.ta.platform.common.tool;

import com.ey.tax.toolset.crypto.SecureUtil;
import com.ey.tax.toolset.crypto.symmetric.SymmetricAlgorithm;
import com.ey.tax.toolset.crypto.symmetric.SymmetricCrypto;

import javax.crypto.SecretKey;
import javax.crypto.spec.PBEParameterSpec;

/**
 * Creator: zhuji
 * Date: 3/20/2020
 * Time: 4:06 PM
 * Description:
 */
public final class EncryptUtil {
    /**
     * 定义迭代次数为1000次
     */
    private static final int ITERATIONCOUNT = 100;
    /**
     * 加密
     * @param plaintext
     * @param password
     * @param salt
     * @return
     */
    public static String encrypt(String plaintext, String password, String salt){
        SecretKey pbeKey = SecureUtil.generatePBEKey(SymmetricAlgorithm.PBEWithMD5AndDES.getValue(),password.toCharArray());
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt.getBytes(), ITERATIONCOUNT);
        SymmetricCrypto crypto = new SymmetricCrypto(SymmetricAlgorithm.PBEWithMD5AndDES.getValue(), pbeKey, parameterSpec);
        return crypto.encryptHex(plaintext);
    }

    /**
     * 解密
     * @param password
     * @param salt
     * @return
     */
    public static String decrypt(String password, String salt){
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt.getBytes(), ITERATIONCOUNT);
        SecretKey pbeKey = SecureUtil.generatePBEKey(SymmetricAlgorithm.PBEWithMD5AndDES.getValue(),password.toCharArray());
        SymmetricCrypto crypto = new SymmetricCrypto(SymmetricAlgorithm.PBEWithMD5AndDES.getValue(), pbeKey, parameterSpec);
        return crypto.decryptStr(password);
    }
}
