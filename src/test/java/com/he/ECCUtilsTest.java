package com.he;

import com.he.utils.ECCUtils;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class ECCUtilsTest {
    //@Test
    /*public void testencrypt() {
        // 测试文本
        byte[] plain = "123".getBytes();

        // 生成密钥对
        KeyPair keyPair = ECCUtils.generateECCKeyPair(256);
        PublicKey publicKey = keyPair.getPublic();
        System.out.println("publicKey:"+publicKey);
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("privateKey:"+privateKey);

        // 加解密
        byte[] encrypt = ECCUtils.eccEncrypt(publicKey, plain);
        System.out.println("加密结果:"+encrypt);
        byte[] decrypt = ECCUtils.eccDecrypt(privateKey, encrypt);
        System.out.println("解密结果:"+new String(decrypt));
        System.err.println(new String(decrypt).equals(new String(plain)));
    }*/
    //@Test
    /*public void signAndVerify(){
        byte[] plain = "123".getBytes();

        // 生成密钥对
        KeyPair keyPair = ECCUtils.generateECCKeyPair(256);
        PublicKey publicKey = keyPair.getPublic();
        System.out.println("publicKey:"+publicKey);
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("privateKey:"+privateKey);

        // 签名验签
        byte[] sign = ECCUtils.eccSign(privateKey, plain);
        System.out.println("签名结果："+sign);
        boolean verify = ECCUtils.eccVerify(publicKey, plain, sign);
        System.err.println(verify);
    }*/
}
