package com.he.service.UserService.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.he.mapper.UserMapper;
import com.he.pojo.User;
import com.he.service.UserService.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WJY
 * @ClassName LoginServiceImpl
 * @Description TODO
 * @date 2022/2/28-16:34
 */

/*@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;

   public int UserExist(String username, String password) {

       //登录验证
       QueryWrapper<User> queryWrapper = new QueryWrapper<>();
       queryWrapper.eq("username",username);
       User user = userMapper.selectOne(queryWrapper);
       if(user == null)//用户名不存在
           return 1;
       else if(!user.password.equals(password)){//密码错误
           return 2;
       }else{//正确
           return 3;
       }
   }
}*/
