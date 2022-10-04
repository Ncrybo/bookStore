package com.bobo.controller;

import com.bobo.entity.Result;
import com.bobo.entity.User;
import com.bobo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        System.out.println(user);
        return loginService.login(user);
    }
    @PostMapping("/test")
    public Result test() {
        return Result.succeed();
    }


}
