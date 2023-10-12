package com.he.service.HeService.Impl;

import com.he.pojo.AuthenticationRequest;
import com.he.pojo.UserDto;
import com.he.service.HeService.AuthenticationService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    /**
     * @author djy
     * @date 2023/10/10 19:00
     * 用户认证验证
     * 认证成功后返回的用户详细信息，也就是当前登录用户的信息：
     */
    @Override
    public UserDto authentication(AuthenticationRequest authenticationRequest) {
        System.out.println(authenticationRequest);
        if(authenticationRequest == null
                || StringUtils.isEmpty(authenticationRequest.getUsername())
                || StringUtils.isEmpty(authenticationRequest.getPassword())){
            throw new RuntimeException("账号或密码为空");
        }
        UserDto userDto = getUserDto(authenticationRequest.getUsername());
        if(userDto == null){
            throw new RuntimeException("查询不到该用户");
        }
        if(!authenticationRequest.getPassword().equals(userDto.getPassword())){
            throw new RuntimeException("账号或密码错误");
        }
        return userDto;
    }
    //模拟用户查询
    public UserDto getUserDto(String username){
        return userMap.get(username);
    }
    //用户信息
    private Map<String,UserDto> userMap = new HashMap<>();
    {
        userMap.put("zhangsan",new UserDto("1010","zhangsan","zhangsan123","张三","133443"));
        userMap.put("lisi",new UserDto("1011","lisi","456","李四","144553"));
    }
    /**
     * @author djy
     * @date 2023/10/10 23:04
     * HMAC
     */
    public byte[] hmacSha256(String message, String key) {
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        try {
            mac.init(secretKeySpec);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        byte[] bytes = mac.doFinal(message.getBytes());
        System.out.println("Signature: " + bytesToHex(bytes));
        return bytes;
    }
    public String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    public boolean HAMC(String MAC,String message,String key) {
        boolean result = false;
        byte[] bytes = hmacSha256(message, key);
        System.out.println("MAC: " + MAC+"  bytes: "+bytesToHex(bytes));
        result= MAC.equals(bytesToHex(bytes));
        return result;
    }

}