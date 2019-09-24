package com.ykse.blogs.config;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import org.springframework.web.servlet.ModelAndView;  
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ykse.blogs.bean.User;

public class CommonInterceptor extends HandlerInterceptorAdapter {
    
    public static final String LAST_PAGE = "com.alibaba.lastPage";

    @Override
	public boolean preHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler) throws Exception {    
        User user =  (User)request.getSession().getAttribute("User");
        if (user == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);  
            return false;
        }  
        return true;
    }

    @Override    
    public void postHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler,    
            ModelAndView modelAndView) throws Exception {     
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request,    
            HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
    
}
