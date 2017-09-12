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
import com.ykse.blogs.bean.Pagination;
import com.ykse.blogs.bean.User;
import com.ykse.blogs.exception.BusinessException;
import com.ykse.blogs.exception.ParameterException;
import com.ykse.blogs.service.BlogsService;

import net.sf.json.JSONObject;

/**
 *  博客控制器
 * 
 * <li>主要含：展示博客、展示个人博客、添加博客、保存博客、获取修改的博客信息、修改博客、删除博客</li>
 * 
 * @author tao.huang
 *@version $Id: BlogsController.java, v 0.1 2016年11月14日 下午5:32:34 tao.huang Exp $
 */
@Controller
@RequestMapping("/")
public class BlogsController {

    @Autowired
    private BlogsService blogsService;

    /**
     * 展示博客
     * 
     * @param request
     * @return
     */
    @RequestMapping(value="/listBlogs")
    public ModelAndView getBlogs(HttpServletRequest request, String type) {
    	ModelAndView modelAndView = new ModelAndView("/blogs/listBlogs");
		
		Pagination<Blogs> page = new Pagination<Blogs>();
		String pageNum = (String) request.getParameter("pageNum");
		String numPerPage = (String) request.getParameter("numPerPage");
		Integer pagenum = (pageNum == null || pageNum == "")
				? 1 : Integer.parseInt(pageNum);
		Integer numperpage = (numPerPage == null || numPerPage == "")
				? 10 : Integer.parseInt(numPerPage);
		page.setCurrentPage(pagenum);
		page.setNumPerPage(numperpage);
		page.setTotalCount(blogsService.getBlogsCount());
		page.calcutePage();
		
		int startRow = (page.getCurrentPage() - 1) * page.getNumPerPage();
		int endRow = page.getNumPerPage();
		List<Blogs> blogs = blogsService.getBlogsAll(type, startRow, endRow);
		page.setContent(blogs);
		request.setAttribute("page", page);
		
		return modelAndView;
    }
    
    /**
     * 展示个人博客
     * 
     * @param request
     * @return
     */
    @RequestMapping(value="/listOwnBlogs")
    public ModelAndView getOwnBlogs(HttpServletRequest request, HttpSession session, String type) {
    	if(session.getAttribute("User") == null){
            throw new BusinessException("会话过期,请重新登陆！");
        }
        User user = (User)session.getAttribute("User");
        Integer userId = user.getUserId();
    	
    	ModelAndView modelAndView = new ModelAndView("/blogs/listOwnBlogs");
		
		Pagination<Blogs> page = new Pagination<Blogs>();
		String pageNum = (String) request.getParameter("pageNum");
		String numPerPage = (String) request.getParameter("numPerPage");
		Integer pagenum = (pageNum == null || pageNum == "")
				? 1 : Integer.parseInt(pageNum);
		Integer numperpage = (numPerPage == null || numPerPage == "")
				? 10 : Integer.parseInt(numPerPage);
		page.setCurrentPage(pagenum);
		page.setNumPerPage(numperpage);
		page.setTotalCount(blogsService.getBlogsCountByParam(userId));
		page.calcutePage();
		
		int startRow = (page.getCurrentPage() - 1) * page.getNumPerPage();
		int endRow = page.getNumPerPage();
		List<Blogs> blogs = blogsService.getBlogsByParam(userId, type, startRow, endRow);
		page.setContent(blogs);
		request.setAttribute("page", page);
		
		return modelAndView;
    }
    
    /**
     * 添加博客
     * 
     * @return
     */
    @RequestMapping(value="/addBlogs", method=RequestMethod.GET)
    public ModelAndView getModal(Model model){      
        ModelAndView modelAndView = new ModelAndView("blogs/addBlogs");
        return modelAndView;
    }
    
    /**
     * 添加博客（个人中心-编辑器）
     * 
     * @return
     */
    @RequestMapping(value="/editBlogs", method=RequestMethod.GET)
    public ModelAndView getPage(Model model){      
        ModelAndView modelAndView = new ModelAndView("blogs/editor");
        return modelAndView;
    }
    
    /**
     * 保存博客
     * 
     * @param blogsTitle
     * @param blogsContent
     * @param httpSession
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveBlogs",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    public String addBlogs(String blogsTitle,String blogsContent,HttpSession httpSession) {
        Blogs blogs = new Blogs();
        blogs.setBlogsTitle(blogsTitle);
        blogs.setBlogsContent(blogsContent);
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
    
    /**
     * 保存博客（个人中心-编辑器）
     * 
     * @param blogsTitle
     * @param blogsContent
     * @param httpSession
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/submitBlogs",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    public ModelAndView submitBlogs(String blogsTitle,String blogsContent,HttpSession httpSession) {
    	
    	ModelAndView modelAndView = new ModelAndView("/index");
    	
        Blogs blogs = new Blogs();
        blogs.setBlogsTitle(blogsTitle);
        blogs.setBlogsContent(blogsContent);
        User user = (User)httpSession.getAttribute("User");
        blogs.setUser(user);
        blogsService.saveBlogs(blogs);
        
        return modelAndView;
    }
    
    /**
     * 获取修改的博客信息
     * 
     * @param blogsId
     * @param request
     * @return
     */
    @RequestMapping(value="/getBlogsInfo", method=RequestMethod.GET)
    public ModelAndView updateInfo(String blogsId, HttpServletRequest request){  
        Blogs blogs =  blogsService.getBlogsById(Integer.parseInt(blogsId));
        request.setAttribute("blogsId", blogs.getBlogsId());
        request.setAttribute("blogsTitle", blogs.getBlogsTitle());
        request.setAttribute("blogsContent", blogs.getBlogsContent());
        ModelAndView modelAndView = new ModelAndView("/blogs/updateBlogs");      
        return modelAndView;
    }
    
    /**
     * 修改博客
     * 
     * @param blogsTitle
     * @param blogsContent
     * @param blogsId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateBlogs",method = RequestMethod.POST)
    public String updateBlog(String blogsTitle,String blogsContent,int blogsId) {
        Blogs blogs = new Blogs();
        blogs.setBlogsId(blogsId);
        blogs.setBlogsTitle(blogsTitle);
        blogs.setBlogsContent(blogsContent);
        
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
    
    /**
     * 删除博客
     *
     * @param blogsId
     */
    @ResponseBody
    @RequestMapping(value="/deleteBlog", method=RequestMethod.GET)
	public void deleteBlog(String blogsId) {
		Integer bid = (blogsId == null || blogsId == "") ? 0 : Integer.parseInt(blogsId);
		blogsService.deleteBlog(bid);
	}
    
    /**
     * 投票
     * 
     * @param blogsId
     * @param type
     * @param session
     */
    @ResponseBody
    @RequestMapping(value="/vote", method=RequestMethod.GET)
	public void vote(String blogsId, String type, HttpSession session) {
    	//用户标识userId
    	if(session.getAttribute("User") == null){
            throw new BusinessException("会话过期,请重新登陆！");
        }
        User user = (User)session.getAttribute("User");
        Integer userId = user.getUserId();
        //博客标识blogsId
		Integer bid = (blogsId == null || blogsId == "") ? 0 : Integer.parseInt(blogsId);
		//顶踩标识type
		if("".equals(type)||type==null){
			throw new ParameterException("请求参数异常！", null);
		}
		Integer typeInt = Integer.parseInt(type);
        //投票
		blogsService.vote(bid, userId, typeInt);
	}
    
    /**
     * 取消投票
     * 
     * @param blogsId
     * @param type
     * @param session
     */
    @ResponseBody
    @RequestMapping(value="/cancelVote", method=RequestMethod.GET)
	public void cancelVote(String blogsId, String type, HttpSession session) {
    	//用户标识userId
    	if(session.getAttribute("User") == null){
            throw new BusinessException("会话过期,请重新登陆！");
        }
        User user = (User)session.getAttribute("User");
        Integer userId = user.getUserId();
        //博客标识blogsId
		Integer bid = (blogsId == null || blogsId == "") ? 0 : Integer.parseInt(blogsId);
		//顶踩标识type
		if("".equals(type)||type==null){
			throw new ParameterException("请求参数异常！", null);
		}
		Integer typeInt = Integer.parseInt(type);
		//取消投票
        blogsService.cancelVote(bid, userId, typeInt);
	}

    /**
     * 跳转上传文件页面（个人中心-上传文件）
     *
     * @return
     */
    @RequestMapping(value="/uploadFile", method=RequestMethod.GET)
    public ModelAndView uploadFile(Model model){
        ModelAndView modelAndView = new ModelAndView("file");
        return modelAndView;
    }

    /**
     * 搜索博客
     * type=0 按标题
     * type=1 按内容
     *
     * @param request
     * @param type
     * @param keyword
     * @return
     */
    @RequestMapping(value="/searchBlogs")
    public ModelAndView searchBlogs(HttpServletRequest request, String type, String keyword) {
        ModelAndView modelAndView = new ModelAndView("/blogs/searchBlogs");

        Pagination<Blogs> page = new Pagination<Blogs>();
        String pageNum = (String) request.getParameter("pageNum");
        String numPerPage = (String) request.getParameter("numPerPage");
        Integer pagenum = (pageNum == null || pageNum == "")
                ? 1 : Integer.parseInt(pageNum);
        Integer numperpage = (numPerPage == null || numPerPage == "")
                ? 10 : Integer.parseInt(numPerPage);
        page.setCurrentPage(pagenum);
        page.setNumPerPage(numperpage);

        int startRow = (page.getCurrentPage() - 1) * page.getNumPerPage();
        int endRow = page.getNumPerPage();

        Blogs blogs = new Blogs();
        if ("0".equals(type)) {
            blogs.setBlogsTitle(keyword);
        }else{
            blogs.setBlogsContent(keyword);
        }
        List<Blogs> list = blogsService.getByParam(blogs, startRow, endRow);

        page.setTotalCount(list.size());
        page.calcutePage();

        page.setContent(list);
        request.setAttribute("page", page);

        return modelAndView;
    }
}
