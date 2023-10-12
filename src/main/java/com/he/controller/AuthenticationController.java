package com.he.controller;

import com.he.pojo.AuthenticationRequest;
import com.he.pojo.UserDto;
import com.he.service.HeService.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    /**
     * 用户认证
     * @param authenticationRequest 登录请求
     * @return
     */
    @RequestMapping("/api/aut")
    @ResponseBody
    public String login(AuthenticationRequest authenticationRequest){
        UserDto userDto = authenticationService.authentication(authenticationRequest);
        return userDto.getFullname() + " 认证成功";
    }

    @RequestMapping("/api/sign")
    @ResponseBody
    public byte[] hmacSha256(String message, String key){
        return authenticationService.hmacSha256(message,key);
    }
    @RequestMapping("/api/verify")
    @ResponseBody
    public boolean HAMC(String MAC,String message,String key){
        return authenticationService.HAMC(MAC,message,key);
    }
}