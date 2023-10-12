package com.he.service.HeService.Impl;
import cn.hutool.crypto.SmUtil;
import com.he.pojo.RsaKeyPair;
import com.he.service.HeService.EncryptionService;
import com.he.utils.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

public class EncryptionServiceImpl implements EncryptionService {


    /**
     * @author djy
     * @date 2023/10/12
     * HMAC
     * HmacSHA1、HmacSHA256、HmacSHA512算法
     */
    /*public byte[] hmacSha256(String algorithm,String message, String key) {
        Mac mac = null;
        try {
            mac = Mac.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        try {
            mac.init(secretKeySpec);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        byte[] bytes = mac.doFinal(message.getBytes());
        System.out.println("Signature: " + bytesToHex(bytes));
        return bytes;
    }*/
    public String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    /**
     * @author djy
     * @date 2023/10/12
     * HMAC
     * HmacSHA1、HmacSHA256、Hmac384、HmacSHA512算法
     */
    @Override
    public String hmac(HmacUtils.Algorithm algorithm, String message, String key) throws InvalidKeyException, NoSuchAlgorithmException{
        HmacUtils hmacUtils = new HmacUtils(key, algorithm);
        byte[] content = message.getBytes(StandardCharsets.UTF_8);
        byte[] signResult = hmacUtils.sign(content);
        return(bytesToHex(signResult));
    }
    @Override
    public boolean hmacVerify(HmacUtils.Algorithm algorithm, byte[] signResult, String key,String message) throws InvalidKeyException, NoSuchAlgorithmException{
        HmacUtils hmacUtils = new HmacUtils(key, algorithm);
        byte[] content = message.getBytes(StandardCharsets.UTF_8);
        return(hmacUtils.verify(signResult, content));
    }
    /**
     * @author djy
     * @date 2023/10/12 21:14
     * SM3 AES SM4
     */

    /**
     * sm3算法秘钥加密
     *
     * @param paramStr 待加密字符串
     * @param key 加密秘钥
     * @return 返回加密后，固定长度=32的16进制字符串
     * @explain
     */
    @Override
    public String encryptSm3(String paramStr,String key) {
        if(key==null||key.equals(""))
        {
            return Sm3Utils.SM3(paramStr);
        }
        else{
            return Sm3Utils.SM3ByKey(paramStr,key);
        }
    }
    /**
     * sm3算法秘钥加密
     *
     * @param paramStr 待加密字符串
     * @param key 加密秘钥
     * @return 返回加密后，固定长度=32的16进制字符串
     * @explain
     */
    /*  String encryptSm3ByKey(String paramStr,String key) {
        return Sm3Utils.SM3ByKey(paramStr,key);
    }*/
    /**
     * 判断源数据与加密数据是否一致
     *
     * @param srcStr       原字符串
     * @param sm3HexString 16进制字符串
     * @return 校验结果
     */
    /*public  boolean verifySm3(String srcStr, String sm3HexString) {
        return Sm3Utils.verify(srcStr,sm3HexString);
    }*/
    /**
     * 判断源数据与加密数据是否一致
     *
     * @param srcStr       原字符串
     * @param key 加密秘钥
     * @param sm3HexString 16进制字符串
     * @return 校验结果
     */
    @Override
    public  boolean verifySm3(String srcStr, String sm3HexString,String key) {
        if(key==null||key.equals(""))
        {
            return Sm3Utils.verify(srcStr,sm3HexString);
        }else{
            return Sm3Utils.verifyByKey(srcStr,sm3HexString,key);
        }
    }


    //SM4
    /**
     * SM4算法加密
     */
    public  String encryptEcb(String password, String message) throws NoSuchAlgorithmException, NoSuchProviderException {
        String key = Sm4Utils.bytesToHexString(Sm4Utils.generateKey(password));
        String encMsg = Sm4Utils.encryptEcb(key,message);
        return encMsg;
    }

    /**
     * SM4算法解密
     */
    public  String decryptEcb(String password, String encMsg) throws NoSuchAlgorithmException, NoSuchProviderException {
        String key = Sm4Utils.bytesToHexString(Sm4Utils.generateKey(password));
        String decMsg = Sm4Utils.encryptEcb(key,encMsg);
        return decMsg;
    }

}
