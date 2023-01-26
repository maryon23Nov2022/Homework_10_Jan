package com.maven_example.mapper;

import com.maven_example.pojo.User;

public interface UserMapper{
    User selectForLogin(User user);

    User selectByUsername(User user);

    void insert(User user);
}
