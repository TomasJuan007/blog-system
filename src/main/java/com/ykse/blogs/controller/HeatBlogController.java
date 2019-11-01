package com.ykse.blogs.controller;

import com.ykse.blogs.bean.Blogs;
import com.ykse.blogs.bean.Pagination;
import com.ykse.blogs.service.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/center/heat")
public class HeatBlogController {

    @Autowired
    private BlogsService blogsService;

    @RequestMapping(value="/listHeatBlogs")
    public ModelAndView getBlogList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("blogs/listBlogs");

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
        List<Blogs> blogs = blogsService.getAllHeatBlogs(startRow, endRow);
        page.setContent(blogs);
        request.setAttribute("page", page);

        return modelAndView;
    }

    @RequestMapping(value="/searchBlogs")
    public ModelAndView searchBlogs(HttpServletRequest request, String type, String keyword) {
        ModelAndView modelAndView = new ModelAndView("blogs/listBlogs");

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
}
