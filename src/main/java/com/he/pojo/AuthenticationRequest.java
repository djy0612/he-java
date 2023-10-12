package com.he.pojo;

import lombok.Data;
/**
 * @author djy
 * @date 2023/10/10 19:00
 * 用户请求认证信息
 * 认证成功后返回的用户详细信息，也就是当前登录用户的信息：
 */
@Data
public class AuthenticationRequest {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}