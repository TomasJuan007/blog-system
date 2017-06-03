package com.ykse.blogs.dao;

import com.ykse.blogs.bean.User;

/**
 * User表数据库操作接口
 * 
 * <pre>主要含：获取用户，更新用户，插入用户，更改用户密码</pre>
 * 
 * @author huangtao
 * @version $Id: UserDao.java, v 0.1 2016年11月14日 下午5:24:10 huangtao Exp $
 */
public interface UserDao {
	
    /**
     * 根据用户ID获取用户
     * 
     * @param userId
     * @return User
     */
	User getUserById(Integer userId);
	
	/**
     * 根据用户账号获取用户
     * 
     * @param userAccount
     * @return User
     */
	User getUserByAccount(String userAccount);
	
	/**
	 * 插入用户
	 * 
	 * @param user
	 * @return 插入的数据条数
	 */
	int saveUser(User user);
	
	/**
	 * 更新用户
	 * 
	 * @param user
	 * @return 更新的数据条数
	 */
	int updateUser(User user);
	
	/**
	 * 修改用户密码
	 * 
	 * @param user
	 * @return 更新的数据条数
	 */
	int changePsw(User user);
	
}
