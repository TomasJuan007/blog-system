package com.ykse.blogs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ykse.blogs.bean.Blogs;
import com.ykse.blogs.bean.Comment;
import com.ykse.blogs.bean.Pagination;
import com.ykse.blogs.bean.User;
import com.ykse.blogs.service.BlogsService;
import com.ykse.blogs.service.CommentService;
import com.ykse.blogs.service.UserService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/")
public class CommentController {  
	
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogsService blogsService;
    @Autowired
    private UserService userService;

    @RequestMapping(value="/listComment")
    public ModelAndView getComments(String blogsId, HttpServletRequest request) {
        int bid = (blogsId == null || "".equals(blogsId)) ? 1 : Integer.parseInt(blogsId);
        Blogs blogs = blogsService.getBlogsById(bid);
        blogs.setViewCount(blogs.getViewCount()+1);
		blogsService.updateViewCount(blogs);
        
        ModelAndView modelAndView = new ModelAndView("blogs/listComment");
        Pagination<Comment> page = new Pagination<>();
        
        String pageNumStr = request.getParameter("pageNum");
        String numPerPageStr = request.getParameter("numPerPage");
        Integer pageNum = (pageNumStr == null || "".equals(pageNumStr))? 1 : Integer.parseInt(pageNumStr);
        Integer numPerPage = (numPerPageStr == null || "".equals(numPerPageStr))? 10 : Integer.parseInt(numPerPageStr);
        page.setCurrentPage(pageNum);
        page.setNumPerPage(numPerPage);
        page.setTotalCount(blogs.getCommentCount());
       
        int startRow = (page.getCurrentPage() - 1) * page.getNumPerPage();
        int endRow = page.getNumPerPage();
       
        List<Comment> comments = commentService.getCommentsByBlogsId(bid, startRow, endRow);
        page.setContent(comments);
        
        page.setTotalCount(blogs.getCommentCount());             
        page.calculatePage();
        request.setAttribute("page", page);
        request.setAttribute("blogs", blogs);
        return modelAndView;
    }

    @RequestMapping(value="/listOwnComment")
    public ModelAndView getOwnComments(String blogsId, HttpServletRequest request) {
        int bid = (blogsId == null || "".equals(blogsId)) ? 1 : Integer.parseInt(blogsId);
        Blogs blogs = blogsService.getBlogsById(bid);
        
        ModelAndView modelAndView = new ModelAndView("blogs/listOwnComment");
        Pagination<Comment> page = new Pagination<>();
        
        String pageNumStr = request.getParameter("pageNum");
        String numPerPageStr = request.getParameter("numPerPage");
        Integer pageNum = (pageNumStr == null || "".equals(pageNumStr))? 1 : Integer.parseInt(pageNumStr);
        Integer numPerPage = (numPerPageStr == null || "".equals(numPerPageStr))? 10 : Integer.parseInt(numPerPageStr);
        page.setCurrentPage(pageNum);
        page.setNumPerPage(numPerPage);
        page.setTotalCount(blogs.getCommentCount());
       
        int startRow, endRow;
        startRow = (page.getCurrentPage() - 1) * page.getNumPerPage();
        endRow = page.getNumPerPage();
       
        List<Comment> comments = commentService.getCommentsByBlogsId(bid, startRow, endRow);
        page.setContent(comments);
        
        page.setTotalCount(blogs.getCommentCount());             
        page.calculatePage();
        request.setAttribute("page", page);
        request.setAttribute("blogs", blogs);
        return modelAndView;
    }

    @RequestMapping(value="/addComment", method=RequestMethod.GET)    
    public ModelAndView addComment(Model model, String userId, String blogsId){
        model.addAttribute("userId", userId);
        model.addAttribute("blogsId", blogsId);
        return new ModelAndView("/blogs/addComment");
    }

    @ResponseBody
    @RequestMapping(value="/saveComment", method=RequestMethod.POST,produces = "application/json; charset=utf-8")    
    public String setComment(String blogsId, String userId, String commentContent, HttpSession seesion){
        int bid = (blogsId == null || "".equals(blogsId)) ? 1 : Integer.parseInt(blogsId);
        Integer uid = ((User)seesion.getAttribute("User")).getUserId();
        
        Comment comment = new Comment();
        Blogs blogs = blogsService.getBlogsById(bid);
        comment.setBlogs(blogs);
        User user = userService.getUserById(uid);
        comment.setUser(user);
        comment.setCommentContent(commentContent);
        JSONObject result = new JSONObject();
        if(commentService.saveComment(comment)){
            result.put("message", "更改成功！");
            result.put("statusCode", "200");
            result.put("dialog", "true");
            return result.toString();
        }
        result.put("message", "更改失败！");
        result.put("statusCode", "300");
        result.put("dialog", "closeCurrent");
        return result.toString();
    }

    @ResponseBody
    @RequestMapping(value="/deleteComment",produces = "application/json; charset=utf-8")    
    public String deleteComment(String commentId){
        Integer cid = (commentId == null || "".equals(commentId)) ? 0 : Integer.parseInt(commentId);
        JSONObject result = new JSONObject();
        if(commentService.deleteComment(cid)){
            result.put("message", "更改成功！");
            result.put("statusCode", "200");
            result.put("dialog", "true");
            return result.toString();
        }
        result.put("message", "更改失败！");
        result.put("statusCode", "300");
        result.put("dialog", "closeCurrent");
        return result.toString();
    }
    
}  
