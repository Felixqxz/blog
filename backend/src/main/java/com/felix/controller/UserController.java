package com.felix.controller;


import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.felix.common.lang.Result;
import com.felix.entity.User;
import com.felix.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author felix
 * @since 2021-07-20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequiresAuthentication
    @GetMapping("/index")
    public Result index(){
        User user = userService.getById(1L);
        return Result.success(user);
    }

    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user){

        User existUser = userService.getOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        Assert.isNull(existUser, "User already exists");

        User temp = new User();
        temp.setStatus(0);
        temp.setCreated(LocalDateTime.now());
        temp.setUsername(user.getUsername());
        temp.setPassword(user.getPassword());
        temp.setEmail(user.getEmail());

        userService.save(temp);
        return Result.success(null);
    }
}
