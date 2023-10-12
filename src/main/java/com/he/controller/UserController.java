package com.he.controller;

import com.he.pojo.User;
import com.he.service.UserService.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author WJY
 * @ClassName UserController
 * @Description TODO
 * @date 2022/2/28-16:30
 */

@Controller
public class UserController {

    /*@Autowired
    private LoginService loginService;*/

    @RequestMapping("/api/login")
    @ResponseBody
    public String login(User user){
        if(!user.username.equals("Admin"))
        {
            return "UnknownAccount";
        }else if(!user.password.equals("Bupt1234")){
            return "IncorrectCredential";
        }else{
            return "true";
        }
    }
}
