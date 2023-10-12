package com.he.utils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class HmacUtils {
    private Mac mac;

    public HmacUtils(String key, Algorithm algorithm) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm.getValue());
        mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
    }

    public byte[] sign(byte[] content) {
        return mac.doFinal(content);
    }

    public boolean verify(byte[] signature, byte[] content) {
        byte[] result = mac.doFinal(content);
        return Arrays.equals(signature, result);
    }

    public static enum Algorithm {

        //HMAC_MD5("HmacMD5"),
        HMAC_SHA1("HmacSHA1"),
        HMAC_SHA256("HmacSHA256"),
        HMAC_SHA384("HmacSHA384"),
        HMAC_SHA512("HmacSHA512");

        private String value;

        Algorithm(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
