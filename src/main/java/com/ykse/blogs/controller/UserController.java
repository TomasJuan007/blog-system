package com.ykse.blogs.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ykse.blogs.bean.User;
import com.ykse.blogs.exception.BusinessException;
import com.ykse.blogs.service.UserService;
import com.ykse.blogs.util.MD5Util;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/")
public class UserController {  
	
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView getRegistePage() {
        return new ModelAndView("../../register");
    }

    @RequestMapping(value = "/checkUser", method = RequestMethod.GET)
    @ResponseBody
    public String checkUser(String userAccount) {
        if (userService.getUserByAccount(userAccount) != null) {
            return "exist";
        } else {
            return "false";
        }
    }

    @RequestMapping(value="/index", method=RequestMethod.GET)    
    public ModelAndView getIndexs() {
        return new ModelAndView("index");
    }

    @RequestMapping(value="/chpwd", method=RequestMethod.GET)    
    public ModelAndView getChpwdPage(Model model) {
        return new ModelAndView("chpwd");
    }

    @RequestMapping(value="/savePsw", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String savePsw(String oldPwd, String newPwd, String rnewPassword, HttpSession session) {
        // 存放操作结果
        JSONObject result = new JSONObject();
        
        // 判断两次密码是否不一样
        if (!newPwd.equals(rnewPassword)) {
            result.put("message", "两次密码不一样，请重新输入！");
            result.put("statusCode", "300");
            result.put("dialog", "closeCurrent");
            return result.toString();
        } else if (session.getAttribute("User") != null) {
             // 判断原密码是否输入错误 
             User user = (User)session.getAttribute("User");
             try {
                if (!MD5Util.validPassword(oldPwd, user.getUserPassword())) {
                    result.put("message", "原密码错误，请重新输入！");
                    result.put("statusCode", "300");
                    result.put("dialog", "closeCurrent");
                    return result.toString();
                }
                user.setUserPassword(MD5Util.getEncryptedPwd(newPwd));
             } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                 LOGGER.error("", e);
                 throw new BusinessException(e.getMessage());
             }
            // 判断是否修改密码成功
             if (userService.changePsw(user)) {
                 // 更新session
                 if (session.getAttribute("User") != null) {
                     session.removeAttribute("User");
                 }
                 User users = userService.getUserById(user.getUserId());
                 session.setAttribute("User", users);
                 result.put("message", "修改密码成功！");
                 result.put("statusCode", "200");
                 result.put("dialog", "true");
                 return result.toString();
             }
        }
        result.put("message", "修改密码失败！");
        result.put("statusCode", "300");
        result.put("dialog", "closeCurrent");
        return result.toString();
    }

    @RequestMapping(value="/userInfo", method=RequestMethod.GET)
    public ModelAndView getUserInfo(Model model, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("updateInfo");
        if (session.getAttribute("User") != null) {
            User user = (User)session.getAttribute("User");
            model.addAttribute("user", user);
        }
        return modelAndView;
    }

    @RequestMapping(value="/saveUser", method=RequestMethod.POST)
    public String saveUser(User user, HttpSession session) {
        try {
            user.setUserPassword(MD5Util.getEncryptedPwd(user.getUserPassword()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
             LOGGER.error("", e);
             throw new BusinessException(e.getMessage());
        }
        if (userService.saveUser(user)) {
            // 更新session
            if (session.getAttribute("User") != null) {
                session.removeAttribute("User");
            }
            User users = userService.getUserByAccount(user.getUserAccount());
            session.setAttribute("User", users);
            // 跳转到主页
            return "index";
        }
        return "../../login";
    }

    @RequestMapping(value="/updateUser", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String updateUser(User user, Model model, HttpSession session) {
        // 存放操作结果
        JSONObject result = new JSONObject();
        if (userService.updateUser(user)) {
            // 更新session
            if (session.getAttribute("User") != null) {
                session.removeAttribute("User");
            }
            User users = userService.getUserById(user.getUserId());
            session.setAttribute("User", users);
            result.put("message", "修改信息成功！");
            result.put("statusCode", "200");
            result.put("dialog", "true");
            return result.toString();
        }
        result.put("message", "修改信息失败！");
        result.put("statusCode", "300");
        result.put("dialog", "closeCurrent");
        return result.toString();
    }

    @RequestMapping(value="/help", method=RequestMethod.GET)    
    public ModelAndView getHelp() {
        return new ModelAndView("help");
    }

    @RequestMapping(value="/contact", method=RequestMethod.GET)
    public ModelAndView getContact() {
        return new ModelAndView("contact");
    }

    @RequestMapping(value="/center", method=RequestMethod.GET)
    public ModelAndView getCenter() {
        return new ModelAndView("center");
    }
    
}
