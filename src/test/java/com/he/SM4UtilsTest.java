package com.he;

import cn.hutool.core.codec.Base64;
import com.he.utils.HmacUtils;
import com.he.utils.Sm4Utils;
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class SM4UtilsTest {
    @Test
    public void testSm4encrypt() throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String key1 = "1234567890123456";

        String message = "2";
        String encode1 = Base64.encode(message);
        //System.out.println(Sm4Utils.generateKey());

    }
}
