package com.ykse.blogs.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ykse.blogs.bean.Blogs;
import com.ykse.blogs.bean.Pagination;
import com.ykse.blogs.bean.User;
import com.ykse.blogs.service.BlogsService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/listBlogs")
public class BlogsController {

    @Autowired
    private BlogsService      blogsService;

    @RequestMapping(value="")
    public ModelAndView getBlogs(HttpServletRequest request ) {
        ModelAndView modelAndView = new ModelAndView("/blogs/listBlogs");
        int startRow,endRow;
        Pagination<Blogs> page = new Pagination<Blogs>();
        String pageNum = (String)request.getParameter("pageNum");
        String numPerPage = (String)request.getParameter("numPerPage");
        String totalCount = (String)request.getParameter("totalCount");
        Integer pagenum = (pageNum == null || pageNum == "")? 1 : Integer.parseInt(pageNum); 
        Integer numperpage = (numPerPage == null || numPerPage == "")? 10 : Integer.parseInt(numPerPage); 
//        Integer totalcount = (totalCount == null || totalCount == "")? blogsService.getBlogsCount() : Integer.parseInt(totalCount);        
        page.setCurrentPage(pagenum);
        page.setTotalCount(blogsService.getBlogsCount());
        page.setNumPerPage(numperpage);
        if (page.getCurrentPage() == null) {
            page.setCurrentPage(1);
        }
        if (page.getNumPerPage() == null) {
            page.setNumPerPage(10);
        } 
        startRow = (page.getCurrentPage() - 1) * page.getNumPerPage();
        endRow = page.getNumPerPage();
        List<Blogs> blogs = blogsService.getBlogsAll(startRow, endRow);
        page.setContent(blogs);                    
        page.calcutePage();
        request.setAttribute("page", page);
        return modelAndView;
    }
    
    /**
     * 获取发帖子页面
     * 
     * @return
     */
    @RequestMapping(value="/addBlogs", method=RequestMethod.GET)    
    public ModelAndView getUserInfo(Model model){      
        ModelAndView modelAndView = new ModelAndView("blogs/addBlogs");
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping(value = "/saveBlogs",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    public String addBlogs(String blogs_title,String blogs_concent,HttpSession httpSession) {
        
        Blogs blogs = new Blogs();
        blogs.setBlogsContent(blogs_concent);
        blogs.setBlogsTitle(blogs_title);
//        System.out.println(blogs_concent);
        blogs.setCommentCount(0);
        blogs.setCreateTime(new Timestamp(System.currentTimeMillis()));
        User user = (User)httpSession.getAttribute("User");
        blogs.setUser(user);
        
        JSONObject result = new JSONObject();
        if(blogsService.saveBlogs(blogs)){
            result.put("message", "保存成功！");
            result.put("statusCode", "200");
            result.put("dialog", "true");
            return result.toString();
        }
        result.put("message", "保存失败！");
        result.put("statusCode", "300");
        result.put("dialog", "closeCurrent");
        return result.toString();
    }
    
    
    @RequestMapping(value="/updateBlogs", method=RequestMethod.GET)    
    public ModelAndView updateInfo(String blogsId, HttpServletRequest request){  
        int blogsid = Integer.parseInt(blogsId);
        request.setAttribute("BlogsId", blogsid);
        Blogs blogs =  blogsService.getBlogsById(blogsid);
        String blogsTitle = blogs.getBlogsTitle();
        String blogsContent = blogs.getBlogsContent();
        request.setAttribute("BlogsTitle", blogsTitle);
        request.setAttribute("BlogsContent", blogsContent);
        ModelAndView modelAndView = new ModelAndView("/blogs/updateBlogs");      
        return modelAndView;
    }
    
    @ResponseBody
    @RequestMapping(value = "/updateBlogs",method = RequestMethod.POST)
    public String updateUser(String blogs_title,String blogs_concent,int blogs_id) {
        Blogs blogs = new Blogs();
        blogs.setBlogsContent(blogs_concent);
        blogs.setBlogsTitle(blogs_title);
        blogs.setBlogsId(blogs_id);
        JSONObject result = new JSONObject();
        if(blogsService.updateBlogs(blogs)){
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
