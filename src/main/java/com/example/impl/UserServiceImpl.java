package com.example.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.UserService;
import com.example.api.UserApi;
import com.example.model.UserModel;
import org.springframework.stereotype.Service;

/**
 * @author 孙小云
 * @version 1.0
 * @date 2019/6/12 15:16
 **/
@Service
public class UserServiceImpl implements UserService {

    @Reference
    UserApi userApi;



    @Override
    public boolean saveUser(UserModel userModel) {
        return userApi.saveUser(userModel);
    }
}
