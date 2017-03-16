package com.ykse.blogs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ykse.blogs.bean.Blogs;
import com.ykse.blogs.bean.Standpoint;
import com.ykse.blogs.bean.User;
import com.ykse.blogs.dao.BlogsDao;
import com.ykse.blogs.dao.StandpointDao;
import com.ykse.blogs.service.BlogsService;

@Service
public class BlogsServiceImpl implements BlogsService {

    @Autowired
    private BlogsDao blogsDao;
    
    @Autowired
    private StandpointDao standpointDao;
    
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Blogs getBlogsById(int blogsId) {
    	return blogsDao.getBlogsById(blogsId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Blogs> getBlogsAll(int startRow, int endRow) {
        return blogsDao.getBlogsAll(startRow, endRow);
    }
    
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer getBlogsCount() {
    	return blogsDao.getBlogsCount();
    }
    
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Blogs> getBlogsByParam(Integer userId, int startRow, int endRow) {
    	User user = new User();
		user.setUserId(userId);
		Blogs blogs = new Blogs();
		blogs.setUser(user);
        return blogsDao.getBlogsByParam(blogs, startRow, endRow);
    }
    
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer getBlogsCountByParam(Integer userId) {
    	User user = new User();
		user.setUserId(userId);
		Blogs blogs = new Blogs();
		blogs.setUser(user);
    	return blogsDao.getBlogsCountByParam(blogs);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveBlogs(Blogs blogs) {
    	return blogsDao.saveBlogs(blogs);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updateBlogs(Blogs blogs) {
    	
    	return blogsDao.updateBlogs(blogs);
    }

    @Override
    public boolean deleteBlog(Integer blogsId) {
    	if(blogsDao.deleteBlogsById(blogsId) != 1)
    		return false;
    	return true;
    }
    
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean addCountById(int blogsId) {
        if(blogsDao.addCountById(blogsId) != true)
            return false;
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean subtractCountById(int blogsId) {
        if(blogsDao.subtractCountById(blogsId) != true)
                return false;
        return true;
    }

	@Override
	@Transactional
	public boolean vote(int blogsId, int userId, int type) {
		Standpoint standpoint = new Standpoint();
		Blogs blogs = new Blogs();
		blogs.setBlogsId(blogsId);
		User user = new User();
		user.setUserId(userId);
		standpoint.setBlogs(blogs);
		standpoint.setUser(user);
		standpoint.setType(type);
		standpointDao.saveStandpoint(standpoint);
		blogsDao.updateSupportCountById(blogsId);
		
		blogs = blogsDao.getBlogsById(blogsId);
		int support = blogs.getSupport();
		int nonsupport = blogs.getNonsupport();
		int rate = support-nonsupport;
		
		return blogsDao.updateSupportRateById(blogsId, rate);
	}
	
	@Override
	@Transactional
	public boolean cancelVote(int blogsId, int userId, int type) {
		Standpoint standpoint = new Standpoint();
		Blogs blogs = new Blogs();
		blogs.setBlogsId(blogsId);
		User user = new User();
		user.setUserId(userId);
		standpoint.setBlogs(blogs);
		standpoint.setUser(user);
		standpoint.setType(type);
		standpointDao.deleteStandpoint(standpoint);
		blogsDao.updateSupportCountById(blogsId);
		
		blogs = blogsDao.getBlogsById(blogsId);
		int support = blogs.getSupport();
		int nonsupport = blogs.getNonsupport();
		int rate = support-nonsupport;
		
		return blogsDao.updateSupportRateById(blogsId, rate);
	}
}
