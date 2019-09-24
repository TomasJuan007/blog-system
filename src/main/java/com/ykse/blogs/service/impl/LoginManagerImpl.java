package com.ykse.blogs.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ykse.blogs.bean.User;
import com.ykse.blogs.dao.UserDao;
import com.ykse.blogs.service.LoginManager;
import com.ykse.blogs.util.MD5Util;

@Service
public class LoginManagerImpl implements LoginManager{
    
    @Autowired
    private UserDao userDao;

    public Map<String,Object> login(String account, String psw) throws Exception{
        Map<String, Object> map = new HashMap<>();
        User user = userDao.getUserByAccount(account);
        if(user == null) {
            map.put("msg", "帐号不存在!");
        } else if(("".equals(psw) && user.getUserPassword() == null)
                || MD5Util.validPassword(psw, user.getUserPassword())) {
            map.put("User", user);
        } else{
            map.put("msg", "密码错误!");
        }
        return map;
    }
}
