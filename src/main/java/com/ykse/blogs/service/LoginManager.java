package com.ykse.blogs.service;

import java.util.Map;

public interface LoginManager {
    Map<String,Object> login(String account, String psw) throws Exception;
    
}
