package com.ykse.blogs.daoTest;


import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ykse.blogs.baseTest.SpringTestCase;
import com.ykse.blogs.bean.Blogs;
import com.ykse.blogs.bean.User;
import com.ykse.blogs.dao.BlogsDao;

import junit.framework.Assert;

public class BlogsDaoTest extends SpringTestCase {
	
	public static Blogs blogs = new Blogs();
	
	@BeforeClass
	public static void init() {
		User user = new User();
		user.setUserId(7);
		blogs.setUser(user);
	}
	
	@Autowired
	BlogsDao blogsDao;
	
	//@Test
	public void getBlogsByParamTest() {
		List<Blogs> list = blogsDao.getBlogsByParam(blogs, 0, 10);
		Assert.assertNotNull(list);
	}
	
	//@Test
	public void getBlogsCountByParamTest() {
		int i = blogsDao.getBlogsCountByParam(blogs);
		System.out.println(i);
	}
	
	//@Test
	public void updateSupportCountByIdTest() {
		boolean sucess = blogsDao.updateSupportCountById(6);
		Assert.assertTrue(sucess);
	}
	
	@Test
	public void updateSupportRateByIdTest() {
		boolean sucess = blogsDao.updateSupportRateById(6,98);
		Assert.assertTrue(sucess);
	}
}
