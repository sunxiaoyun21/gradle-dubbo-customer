package com.example.controller;

import com.example.UserService;

import com.example.core.others.Ajax;
import com.example.model.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 孙小云
 * @version 1.0
 * @date 2019/6/12 15:15
 **/

@RestController
@RequestMapping("/userInfo/")
@Api(value = "/userInfo/", description = "用户模块")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("保存用户信息")
    @PostMapping("saveUser")
    public Ajax saveUser(UserModel userModel) {
        return Ajax.ok(userService.saveUser(userModel));
    }


}
