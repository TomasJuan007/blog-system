package com.ykse.blogs.service;

import com.ykse.blogs.bean.User;

/**
 * 用户业务逻辑处理接口
 * 
 * <li>主要含：用户注册、用户登录、修改个人信息业务</li>
 * 
 * @author huangtao
 * @version $Id: UserService.java, v 0.1 2016年11月14日 下午4:48:09 huangtao Exp $
 */
public interface UserService {
    
    /**
     * 根据用户ID获取用户
     * 
     * @param userId
     * @return User
     */
    public User getUserById(Integer userId);
    
    /**
     * 根据用户账号获取用户
     * 
     * @param userAccount
     * @return User
     */
    public User getUserByAccount(String userAccount);
    
    /**
     * 插入用户
     * 
     * @param user
     * @return 操作结果，true为成功，false为失败
     */
    public boolean saveUser(User user);
    
    /**
     * 更新用户
     * 
     * @param user 提供要修改的userId，新的姓名和手机号
     * @return 操作结果，true为成功，false为失败
     */
    public boolean updateUser(User user);
    
    /**
     * 修改用户密码
     * 
     * @param user 提供要修改的userId，新的密码
     * @return 操作结果，true为成功，false为失败
     */
    public boolean changePsw(User user);
    
}
