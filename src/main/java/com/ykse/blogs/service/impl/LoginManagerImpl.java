package com.ykse.blogs.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ykse.blogs.bean.User;
import com.ykse.blogs.dao.UserDao;
import com.ykse.blogs.service.LoginManager;
import com.ykse.blogs.util.MD5Util;

/**
 * 登录验证接口实现
 * 
 * @author huangtao
 * @version $Id: LoginManagerImpl.java, v 0.1 2016年11月15日 下午4:20:31 huangtao Exp $
 */
@Service
public class LoginManagerImpl implements LoginManager{
    
    @Autowired
    private UserDao userDao;
    
    /**
     * @see com.ykse.blogs.service.LoginManager#login(java.lang.String, java.lang.String)
     */
    public Map<String,Object> login(String account, String psw) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userDao.getUserByAccount(account);
        if(user == null){
            map.put("msg", "帐号不存在!");
        }
        else if((psw == "" && user.getUserPassword() == null) || MD5Util.validPassword(psw, user.getUserPassword())) {
            map.put("User", user);
        }
        else{
            map.put("msg", "密码错误!");
        }
        return map;
    }
}
