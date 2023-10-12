package com.he.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author djy
 * @date 2023/10/10 19:00
 * 当前登录用户信息
 * 认证成功后返回的用户详细信息，也就是当前登录用户的信息：
 */
@Data
@AllArgsConstructor
public class UserDto {

    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;

}