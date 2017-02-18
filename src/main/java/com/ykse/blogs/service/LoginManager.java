package com.ykse.blogs.service;

import java.util.Map;

/**
 * 登录验证接口
 * 
 * @author dianyu.fang
 * @version $Id: LoginManager.java, v 0.1 2016年11月15日 下午4:20:56 dainyu.fang Exp $
 */
public interface LoginManager {
    
    /**
     * 登录验证，判断账号是否存在以及密码是否正确
     * 
     * @param account
     * @param psw
     * @return
     * @throws Exception
     */
    public Map<String,Object> login(String account, String psw) throws Exception;
    
}
