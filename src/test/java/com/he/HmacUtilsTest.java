package com.he;

import com.he.utils.HmacUtils;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HmacUtilsTest {
    private void test(HmacUtils.Algorithm algorithm) throws InvalidKeyException, NoSuchAlgorithmException {
        String key = "key";
        HmacUtils hmacUtils = new HmacUtils(key, algorithm);
        String original = "ABCDEFG";
        byte[] content = original.getBytes(StandardCharsets.UTF_8);
        byte[] signResult = hmacUtils.sign(content);
        System.out.println(bytesToHex(signResult));
        System.out.println(hmacUtils.verify(signResult, content));
    }

   /* @Test
    public void testHmacMD5() throws InvalidKeyException, NoSuchAlgorithmException {
        test(HmacUtils.Algorithm.HMAC_MD5);
    }*/

    @Test
    public void testHmacSHA1() throws InvalidKeyException, NoSuchAlgorithmException {
        test(HmacUtils.Algorithm.HMAC_SHA1);
    }

    @Test
    public void testHmacSHA256() throws InvalidKeyException, NoSuchAlgorithmException {
        test(HmacUtils.Algorithm.HMAC_SHA256);
    }

    @Test
    public void testHmacSHA384() throws InvalidKeyException, NoSuchAlgorithmException {
        test(HmacUtils.Algorithm.HMAC_SHA384);
    }

    @Test
    public void testHmacSHA512() throws InvalidKeyException, NoSuchAlgorithmException {
        test(HmacUtils.Algorithm.HMAC_SHA512);
    }

    /**
     * 自定义字节到十六进制转换器来获取十六进制的哈希值
     */
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
