package com.ykse.blogs.controller;

import com.ykse.blogs.bean.User;
import com.ykse.blogs.exception.BusinessException;
import com.ykse.blogs.exception.ParameterException;
import com.ykse.blogs.service.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class VoteController {

    @Autowired
    private BlogsService blogsService;

    @ResponseBody
    @RequestMapping(value="/vote", method= RequestMethod.GET)
    public void vote(String blogsId, String type, HttpSession session) {
        //用户标识userId
        if (session.getAttribute("User") == null) {
            throw new BusinessException("会话过期,请重新登陆！");
        }
        User user = (User)session.getAttribute("User");
        Integer userId = user.getUserId();
        //博客标识blogsId
        int bid = (blogsId == null || "".equals(blogsId)) ? 0 : Integer.parseInt(blogsId);
        //顶踩标识type
        if ("".equals(type)||type==null) {
            throw new ParameterException("请求参数异常！", null);
        }
        int typeInt = Integer.parseInt(type);
        //投票
        blogsService.vote(bid, userId, typeInt);
    }

    @ResponseBody
    @RequestMapping(value="/cancelVote", method=RequestMethod.GET)
    public void cancelVote(String blogsId, String type, HttpSession session) {
        //用户标识userId
        if (session.getAttribute("User") == null) {
            throw new BusinessException("会话过期,请重新登陆！");
        }
        User user = (User)session.getAttribute("User");
        Integer userId = user.getUserId();
        //博客标识blogsId
        int bid = (blogsId == null || "".equals(blogsId)) ? 0 : Integer.parseInt(blogsId);
        //顶踩标识type
        if ("".equals(type)||type==null) {
            throw new ParameterException("请求参数异常！", null);
        }
        int typeInt = Integer.parseInt(type);
        //取消投票
        blogsService.cancelVote(bid, userId, typeInt);
    }
}
