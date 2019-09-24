package com.ykse.blogs.service;

import com.ykse.blogs.bean.User;

public interface UserService {
    User getUserById(Integer userId);
    User getUserByAccount(String userAccount);
    boolean saveUser(User user);
    boolean updateUser(User user);
    boolean changePsw(User user);
    
}
