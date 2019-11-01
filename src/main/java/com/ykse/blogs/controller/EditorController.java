package com.ykse.blogs.controller;

import com.ykse.blogs.bean.Blogs;
import com.ykse.blogs.bean.User;
import com.ykse.blogs.service.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/center/editor")
public class EditorController {

    @Autowired
    private BlogsService blogsService;

    @ResponseBody
    @RequestMapping(value = "/submitBlogs", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ModelAndView saveBlogForCenter(String blogTitle, String blogContent, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("redirect:/index#listOwnBlogs");

        Blogs blogs = new Blogs();
        blogs.setBlogTitle(blogTitle);
        blogs.setBlogContent(blogContent);
        User user = (User)httpSession.getAttribute("User");
        blogs.setUser(user);
        blogsService.saveBlogs(blogs);

        return modelAndView;
    }

    @RequestMapping(value="/editBlogs", method=RequestMethod.GET)
    public ModelAndView getEditBlogModal() {
        return new ModelAndView("blogs/editor");
    }
}
