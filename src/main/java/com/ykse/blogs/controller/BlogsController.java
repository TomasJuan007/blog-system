package com.ykse.blogs.controller;

import com.ykse.blogs.bean.Blogs;
import com.ykse.blogs.bean.Pagination;
import com.ykse.blogs.bean.User;
import com.ykse.blogs.exception.BusinessException;
import com.ykse.blogs.service.BlogsService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class BlogsController {

    @Autowired
    private BlogsService blogsService;

    @ResponseBody
    @RequestMapping(value = "/submitBlogs", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ModelAndView submitBlogs(String blogTitle, String blogContent, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("/index");

        Blogs blogs = new Blogs();
        blogs.setBlogTitle(blogTitle);
        blogs.setBlogContent(blogContent);
        User user = (User)httpSession.getAttribute("User");
        blogs.setUser(user);
        blogsService.saveBlogs(blogs);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/saveBlogs", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String addBlogs(String blogTitle, String blogContent, HttpSession httpSession) {
        Blogs blogs = new Blogs();
        blogs.setBlogTitle(blogTitle);
        blogs.setBlogContent(blogContent);
        User user = (User)httpSession.getAttribute("User");
        blogs.setUser(user);

        JSONObject result = new JSONObject();
        if (blogsService.saveBlogs(blogs)) {
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

    @RequestMapping(value="/listBlogs")
    public ModelAndView getBlogs(HttpServletRequest request, String type) {
        ModelAndView modelAndView = new ModelAndView("/blogs/listBlogs");

        Pagination<Blogs> page = new Pagination<>();
        String pageNumStr = request.getParameter("pageNum");
        String numPerPageStr = request.getParameter("numPerPage");
        Integer pageNum = (pageNumStr == null || "".equals(pageNumStr))
                ? 1 : Integer.parseInt(pageNumStr);
        Integer numPerPage = (numPerPageStr == null || "".equals(numPerPageStr))
                ? 10 : Integer.parseInt(numPerPageStr);
        page.setCurrentPage(pageNum);
        page.setNumPerPage(numPerPage);
        page.setTotalCount(blogsService.getBlogsCount());
        page.calculatePage();

        int startRow = (page.getCurrentPage() - 1) * page.getNumPerPage();
        int endRow = page.getNumPerPage();
        List<Blogs> blogs = blogsService.getBlogsAll(type, startRow, endRow);
        page.setContent(blogs);
        request.setAttribute("page", page);

        return modelAndView;
    }

    @RequestMapping(value="/getBlogsInfo", method=RequestMethod.GET)
    public ModelAndView getInfo(String blogsId, HttpServletRequest request) {
        Blogs blogs =  blogsService.getBlogsById(Integer.parseInt(blogsId));
        request.setAttribute("blogsId", blogs.getBlogsId());
        request.setAttribute("blogTitle", blogs.getBlogTitle());
        request.setAttribute("blogContent", blogs.getBlogContent());
        return new ModelAndView("/blogs/updateBlogs");
    }

    @RequestMapping(value="/listOwnBlogs")
    public ModelAndView getOwnBlogs(HttpServletRequest request, HttpSession session, String type) {
        if (session.getAttribute("User") == null) {
            throw new BusinessException("会话过期,请重新登陆！");
        }
        User user = (User)session.getAttribute("User");
        Integer userId = user.getUserId();

        ModelAndView modelAndView = new ModelAndView("/blogs/listOwnBlogs");

        Pagination<Blogs> page = new Pagination<>();
        String pageNumStr = request.getParameter("pageNum");
        String numPerPageStr = request.getParameter("numPerPage");
        Integer pageNum = (pageNumStr == null || "".equals(pageNumStr))
                ? 1 : Integer.parseInt(pageNumStr);
        Integer numPerPage = (numPerPageStr == null || "".equals(numPerPageStr))
                ? 10 : Integer.parseInt(numPerPageStr);
        page.setCurrentPage(pageNum);
        page.setNumPerPage(numPerPage);
        page.setTotalCount(blogsService.getBlogsCountByParam(userId));
        page.calculatePage();

        int startRow = (page.getCurrentPage() - 1) * page.getNumPerPage();
        int endRow = page.getNumPerPage();
        List<Blogs> blogs = blogsService.getBlogsByParam(userId, type, startRow, endRow);
        page.setContent(blogs);
        request.setAttribute("page", page);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/updateBlogs",method = RequestMethod.POST)
    public String updateBlog(String blogTitle,String blogContent,int blogsId) {
        Blogs blogs = new Blogs();
        blogs.setBlogsId(blogsId);
        blogs.setBlogTitle(blogTitle);
        blogs.setBlogContent(blogContent);

        JSONObject result = new JSONObject();
        if (blogsService.updateBlogs(blogs)) {
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
    @RequestMapping(value="/deleteBlog", method=RequestMethod.GET)
    public void deleteBlog(String blogsId) {
        if (blogsId != null && !"".equals(blogsId)) {
            Integer bid = Integer.parseInt(blogsId);
            blogsService.deleteBlog(bid);
        }
    }

    @RequestMapping(value="/searchBlogs")
    public ModelAndView searchBlogs(HttpServletRequest request, String type, String keyword) {
        ModelAndView modelAndView = new ModelAndView("/blogs/searchBlogs");

        Pagination<Blogs> page = new Pagination<>();
        String pageNumStr = request.getParameter("pageNum");
        String numPerPageStr = request.getParameter("numPerPage");
        Integer pageNum = (pageNumStr == null || "".equals(pageNumStr))
                ? 1 : Integer.parseInt(pageNumStr);
        Integer numPerPage = (numPerPageStr == null || "".equals(numPerPageStr))
                ? 10 : Integer.parseInt(numPerPageStr);
        page.setCurrentPage(pageNum);
        page.setNumPerPage(numPerPage);

        int startRow = (page.getCurrentPage() - 1) * page.getNumPerPage();
        int endRow = page.getNumPerPage();

        Blogs blogs = new Blogs();
        if ("0".equals(type)) {
            blogs.setBlogTitle(keyword);
        } else {
            blogs.setBlogContent(keyword);
        }
        List<Blogs> list = blogsService.getByParam(blogs, startRow, endRow);

        page.setTotalCount(list.size());
        page.calculatePage();

        page.setContent(list);
        request.setAttribute("page", page);

        return modelAndView;
    }

    @RequestMapping(value="/addBlogs", method=RequestMethod.GET)
    public ModelAndView getAddBlogModal(Model model) {
        return new ModelAndView("blogs/addBlogs");
    }

    @RequestMapping(value="/editBlogs", method=RequestMethod.GET)
    public ModelAndView getEditBlogModal(Model model) {
        return new ModelAndView("blogs/editor");
    }
}
