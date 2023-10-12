package com.he.pojo;

/**
 * @author: huangyibo
 * @Date: 2022/4/29 18:47
 * @Description: 非对称加密 密钥对对象
 */

public class RsaKeyPair {

    private String publicKey;

    private String privateKey;

    public RsaKeyPair(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }
}