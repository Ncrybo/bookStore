package com.bobo.service;

import com.bobo.dao.UserMapper;
import com.bobo.entity.Result;
import com.bobo.entity.User;
import com.bobo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LoginService {
    @Autowired
    UserMapper userMapper;

    public User getById(String name) {
        return userMapper.getByName(name);
    }

    public Result login(User user) {
        Result result = new Result();
        User myuser = userMapper.getByName(user.getUsername());
        if(myuser.getPassword().equals(user.getPassword())) {
            String token = JwtUtils.generateToken(myuser.getUsername());
            return Result.succeed(token,myuser);
        }
        else {
            return Result.fail("账号或密码错误");
        }
    }

}
