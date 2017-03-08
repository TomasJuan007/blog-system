package com.ykse.blogs.serviceImplTest;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ykse.blogs.baseTest.SpringTestCase;
import com.ykse.blogs.bean.Blogs;
import com.ykse.blogs.service.BlogsService;

/**
 * @author huangtao
 * @version $Id: UserServiceTest.java, v 0.1 2016年11月11日 下午3:08:34 huangtao Exp $
 */
public class BLogsServiceTest extends SpringTestCase {

    @Autowired
    private BlogsService blogsService;

    @Test
    public void getUserByIdTest() {
        List<Blogs> blogsList = blogsService.getBlogsAll(0, 1);
        Iterator it = blogsList.iterator();
        while (it.hasNext()) {
            Blogs blogs = (Blogs) it.next();
            System.out.println(blogs.getBlogsId() + ":" + blogs.getBlogsTitle());
        }
    }

    @Test
    public void addCountByIdTest() {
        blogsService.addCountById(1);
    }
    
    @Test
    public void subtractCountByIdTest() {
        blogsService.subtractCountById(1);
    }
    
}
