package com.he;

import com.he.utils.RsaUtils;
import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.Map;

public class RsaUtilsTest {
    @Test
    public void encryptAndDecrypt() throws Exception {
        /**
         * RSA数据加密和解密
         *
         * @throws Exception
         */
        Map<String, String> keyMap = RsaUtils.generateKey(2048);
        String publicKeyStr = keyMap.get("publicKeyStr");
        String privateKeyStr = keyMap.get("privateKeyStr");
        System.out.println("-----------------生成的公钥和私钥------------------------------");
        System.out.println("获取到的公钥：" + publicKeyStr);
        System.out.println("获取到的私钥：" + privateKeyStr);
        // 待加密数据
        String data = "tranSeq=1920542585&amount=100&payType=wechat";
        // 公钥加密
        System.out.println("-----------------加密和解密------------------------------");
        System.out.println("待加密的数据：" + data);
        String encrypt = RsaUtils.encryptByPublicKey(data, publicKeyStr);
        System.out.println("加密后数据：" + encrypt);
        // 私钥解密
        String decrypt = RsaUtils.decryptByPrivateKey(encrypt, privateKeyStr);
        System.out.println("解密后数据：" + decrypt);
    }

    /**
     * RSA数据签名和验签
     *
     * @throws Exception
     */
    @Test
    public void signAndVerify() throws Exception {
        Map<String, String> keyMap = RsaUtils.generateKey(2048);
        String publicKeyStr = keyMap.get("publicKeyStr");
        String privateKeyStr = keyMap.get("privateKeyStr");
        System.out.println("-----------------生成的公钥和私钥------------------------------");
        System.out.println("获取到的公钥：" + publicKeyStr);
        System.out.println("获取到的私钥：" + privateKeyStr);
        // 数字签名
        String data = "tranSeq=1920542585&amount=100&payType=wechat";
        System.out.println("待签名的数据：" + data);
        String sign = RsaUtils.sign(data.getBytes(), Base64.getDecoder().decode(privateKeyStr), "RSA");
        System.out.println("数字签名结果：" + sign);
        boolean verify = RsaUtils.verify(data.getBytes(), Base64.getDecoder().decode(sign), Base64.getDecoder().decode(publicKeyStr), "RSA");
        System.out.println("数字签名验证结果：" + verify);
    }

}
