package com.he.service.HeService;

import com.he.utils.HmacUtils;
import com.he.utils.Sm3Utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public interface EncryptionService {
    String hmac(HmacUtils.Algorithm algorithm, String message, String key)throws InvalidKeyException, NoSuchAlgorithmException;
    boolean hmacVerify(HmacUtils.Algorithm algorithm, byte[] signResult, String key,String message) throws InvalidKeyException, NoSuchAlgorithmException;
    String encryptSm3(String paramStr,String key);
    boolean verifySm3(String srcStr, String sm3HexString,String key);
    String encryptEcb(String password, String message) throws NoSuchAlgorithmException, NoSuchProviderException;
    public  String decryptEcb(String password, String encMsg) throws NoSuchAlgorithmException, NoSuchProviderException;


    }
