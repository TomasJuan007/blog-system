package com.ykse.blogs.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ykse.blogs.bean.User;
import com.ykse.blogs.dao.UserDao;
import com.ykse.blogs.exception.BusinessException;
import com.ykse.blogs.exception.ParameterException;
import com.ykse.blogs.service.UserService;

@Service  
public class UserServiceImpl implements UserService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    
	@Autowired
    private UserDao userDao;

	@Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

	@Override
    public User getUserByAccount(String userAccount) {
        return userDao.getUserByAccount(userAccount);
    }

	@Override
    public boolean saveUser(User user) {
	    if (userDao.getUserByAccount(user.getUserAccount()) != null) {
	        LOG.info("插入用户失败，用户已存在。");
	        throw new ParameterException("插入用户失败，用户已存在。");
	    }
        if (userDao.saveUser(user) <= 0) {
            LOG.info("插入用户失败。");
            throw new BusinessException("插入用户失败。");
        }
        return true;
    }

	@Override
    public boolean updateUser(User user) {
        if (userDao.updateUser(user) <= 0) {
            LOG.info("更新用户信息失败。");
            throw new BusinessException("更新用户信息失败。");
        }
        return true;
    }

	@Override
    public boolean changePsw(User user) {
        if (userDao.changePsw(user) <= 0) {
            LOG.info("修改用户信息失败。");
            throw new BusinessException("修改用户信息失败。");
        }
        return true;
    }
}
