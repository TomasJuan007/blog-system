package com.ykse.blogs.daoTest;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ykse.blogs.baseTest.SpringTestCase;
import com.ykse.blogs.bean.Blogs;
import com.ykse.blogs.bean.Standpoint;
import com.ykse.blogs.bean.User;
import com.ykse.blogs.dao.StandpointDao;

public class StandpointDaoTest extends SpringTestCase {

	public static Blogs blogs = new Blogs();
	public static User user = new User();
	
	@BeforeClass
	public static void init() {
		blogs.setBlogsId(3);
		user.setUserId(6);
	}
	
	@Autowired
	StandpointDao standpointDao;
	
	@Test
	public void saveStandpoint() {
		Standpoint standpoint = new Standpoint();
		standpoint.setBlogs(blogs);
		standpoint.setUser(user);
		standpoint.setType(2);
		boolean sucess = standpointDao.saveStandpoint(standpoint);
		Assert.assertTrue(sucess);
	}
}
