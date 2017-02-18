package com.ykse.blogs.serviceImplTest;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ykse.blogs.baseTest.SpringTestCase;
import com.ykse.blogs.bean.Blogs;
import com.ykse.blogs.bean.Comment;
import com.ykse.blogs.bean.User;
import com.ykse.blogs.service.BlogsService;
import com.ykse.blogs.service.CommentService;
import com.ykse.blogs.service.UserService;

/**
 * 评论业务逻辑处理实现类单元测试
 * 
 * @author tao.huang
 * @version $Id: CommentServiceTest.java, v 0.1 2016年11月17日 上午11:15:51 tao.huang Exp $
 */
public class CommentServiceTest extends SpringTestCase {

    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    BlogsService blogsService;
    
    @Test
    public void viewCommentsTest() {
        List<Comment> comments = commentService.getCommentsByBlogsId(8,0,10);
        System.out.println(comments.get(0).getCommentContent());
    }
    
    @Test
    public void deleteCommentTest() {
        boolean flag = commentService.deleteComment(12);
        System.out.println(flag);
    }
    
    @Test
    public void saveCommentTest() {
        Comment comment = new Comment();
        User user = userService.getUserById(3);
        Blogs blogs = blogsService.getBlogsById(1);
        
        comment.setUser(user);
        comment.setBlogs(blogs);
        comment.setCommentContent("2333333333333");
        boolean flag = commentService.saveComment(comment);
        System.out.println(flag);
    }
    

    @Test
    public void getCommentByIdTest() {

        Comment comment = commentService.getCommentById(1);
        System.out.println(comment.getUser().getUserName());
    }

    @Test
    public void updateCommentTest() {

        Comment comment = commentService.getCommentById(1);
        comment.setCommentContent("qweqweqwe");
        commentService.updateComment(comment);
        System.out.println(commentService.getCommentById(1).getCommentContent());
    }
}
