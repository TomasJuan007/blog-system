package com.ykse.blogs.dao;

import com.ykse.blogs.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
	User getUserById(Integer userId);
	User getUserByAccount(String userAccount);
	int saveUser(User user);
	int updateUser(User user);
	int changePsw(User user);
}
