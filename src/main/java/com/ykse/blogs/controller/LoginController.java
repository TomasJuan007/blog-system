package com.ykse.blogs.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ykse.blogs.service.LoginManager;

/**
 * 登录拦截器
 * 
 * <li>主要含：用户登录、注销拦截</li>
 * 
 * @author dianyu.fang
 * @version $Id: LoginController.java, v 0.1 2016年11月15日 下午4:23:34 dainyu.fang Exp $
 */
@Controller
public class LoginController {
    
    @Resource
    private LoginManager loginManager;
    
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 获取登录页面
     * 
     * @return
     */
    @RequestMapping("/")
    public ModelAndView getLogin(){      
        ModelAndView modelAndView = new ModelAndView("../../login");
        return modelAndView;    
    }
    
    /**
     * 获取登录页面
     * 
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView getLoginPage(){      
        ModelAndView modelAndView = new ModelAndView("../../login");
        return modelAndView;
    }
    
    /**
     * 用户登录拦截，若成功登录则将用户信息保存到session里，若登录失败则返回失败信息
     * 
     * @param model
     * @param account
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/getIndex", method=RequestMethod.POST)
    public String login(Model model, String account, String password, HttpSession session) {
        // 判断账号或密码是否没填写
        if(account == "" || password == "")
            return "../../login";
        
        // 判断登录是否成功
        Map<String, Object> map = null;
        try {
            map = loginManager.login(account, password);
        } catch (Exception e) {
            LOG.error("", e);
        }
        // 登录失败，弹框提醒
        if(map.get("User") == null && map.get("msg") != null){
            model.addAttribute("msg", map.get("msg"));
            return "../../login";
        }
        else{
            // 登录成功，设置session信息
            session.setAttribute("User", map.get("User"));
        }   
        // 跳转到主页
        return "index";
    }

    /**
     * 注销账号，清空session
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        if(session.getAttribute("User") != null){
            session.removeAttribute("User");
        }
        if(session.getAttribute("msg") != null){
            session.removeAttribute("msg");
        }
        return "../../login";
    }
 
}
