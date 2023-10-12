package com.he.service.HeService;

import com.he.pojo.AuthenticationRequest;
import com.he.pojo.UserDto;

/**
 * 认证服务
 * （1）定义认证接口，此接口用于对传来的用户名、密码校验，若成功则返回该用户的详细信息，否则抛出错误异常：
 */
public interface AuthenticationService {
    /**
     * @author djy
     * @date 2023/10/10 19:00
     * 用户请求认证信息
     * 认证成功后返回的用户详细信息，也就是当前登录用户的信息：
     */
    UserDto authentication(AuthenticationRequest authenticationRequest);
    /**
     * @author djy
     * @date 2023/10/10 23:16
     * HMAC 签名与验签
     */
    byte[] hmacSha256(String message, String key);

    boolean HAMC(String MAC,String message,String key);

}
