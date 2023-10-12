package com.he.utils;


import com.he.pojo.RsaKeyPair;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class ECCUtils {
    private static final String EC_ALGORITHM = "EC";

    private static final String EC_PROVIDER = "BC";

    private static final String ECIES_ALGORITHM = "ECIES";

    private static final String SIGNATURE = "SHA256withECDSA";

    static{
        try{
            Security.addProvider(new BouncyCastleProvider());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 生成密钥对
     *
     * @param keySize   密钥长度
     * @return          密钥对象
     */
    public static RsaKeyPair generateEccKeyPair(int keySize) {
        try {
            // 获取指定算法的密钥对生成器
            KeyPairGenerator generator = KeyPairGenerator.getInstance(EC_ALGORITHM, EC_PROVIDER);
            // 初始化密钥对生成器（指定密钥长度, 使用默认的安全随机数源）
            generator.initialize(keySize);
            // 随机生成一对密钥（包含公钥和私钥）
            KeyPair keyPair = generator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            String publicKeyString = Base64.encodeBase64String(publicKey.getEncoded());
            String privateKeyString = Base64.encodeBase64String(privateKey.getEncoded());
            return new RsaKeyPair(publicKeyString, privateKeyString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ECC 加密
     *
     * @param publicKeyText 公钥
     * @param data      原文
     * @return　密文
     */
    public static String eccEncrypt(String publicKeyText, String data) {
        try {
            X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyText));
            KeyFactory keyFactory = KeyFactory.getInstance(EC_ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec2);
            Cipher cipher = Cipher.getInstance(ECIES_ALGORITHM, EC_PROVIDER);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] result = cipher.doFinal(data.getBytes());
            return Base64.encodeBase64String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ECC 解密
     *
     * @param privateKeyText 私钥
     * @param data  　       密文
     * @return　原文
     */
    public static String eccDecrypt(String privateKeyText, String data) {
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec5 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyText));
            KeyFactory keyFactory = KeyFactory.getInstance(EC_ALGORITHM);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec5);
            Cipher cipher = Cipher.getInstance(ECIES_ALGORITHM, EC_PROVIDER);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(Base64.decodeBase64(data));
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 私钥签名
     *
     * @param privateKey 私钥
     * @param data       原文
     * @return 签名
     */
    public static String eccSign(String privateKey, String data) {
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec5 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
            KeyFactory keyFactory = KeyFactory.getInstance(EC_ALGORITHM);
            PrivateKey key = keyFactory.generatePrivate(pkcs8EncodedKeySpec5);
            Signature signature = Signature.getInstance(SIGNATURE);
            signature.initSign(key);
            signature.update(data.getBytes());
            return new String(Base64.encodeBase64(signature.sign()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 公钥验签
     *
     * @param publicKey 公钥
     * @param srcData   原文
     * @param sign      签名
     * @return
     */
    public static boolean eccVerify(String publicKey, String srcData, String sign) {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
            KeyFactory keyFactory = KeyFactory.getInstance(EC_ALGORITHM);
            PublicKey key = keyFactory.generatePublic(keySpec);
            Signature signature = Signature.getInstance(SIGNATURE);
            signature.initVerify(key);
            signature.update(srcData.getBytes());
            return signature.verify(Base64.decodeBase64(sign.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        // 测试文本
        String plain = "12345678";

        // 生成密钥对
        RsaKeyPair rsaKeyPair = generateEccKeyPair(256);
        System.out.println("ECC公钥: "+rsaKeyPair.getPublicKey());
        System.out.println("ECC私钥: "+rsaKeyPair.getPrivateKey());

        // 加解密
        String encrypt = eccEncrypt(rsaKeyPair.getPublicKey(), plain);
        System.out.println("encrypt"+encrypt);
        String decrypt = eccDecrypt(rsaKeyPair.getPrivateKey(), encrypt);
        System.out.println("decrypt"+decrypt);
        System.err.println(plain.equals(decrypt));


        // 签名验签
        String sign = eccSign(rsaKeyPair.getPrivateKey(), plain);
        System.out.println("sign"+sign);
        boolean verify = eccVerify(rsaKeyPair.getPublicKey(), plain, sign);
        System.err.println(verify);
    }

}
