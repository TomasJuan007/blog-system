package com.ykse.blogs.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 错误信息统一处理
 * 
 * @author dianyu.fang
 * @version $Id: GlobalExceptionResolver.java, v 0.1 2016年11月17日 下午5:20:50 dainyu.fang Exp $
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@ResponseBody
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		
	    LOG.error("访问" + request.getRequestURI() + " 发生错误, 错误信息:" + ex.getMessage());	
		Map<String, Object> model = new HashMap<String, Object>();  
        model.put("exMsg", ex.getMessage());  
        
        // 根据不同错误跳转到定制化的错误页面  
        if(ex instanceof BusinessException) {  
            return new ModelAndView("error-business", model);  
        }else if(ex instanceof ParameterException) {  
            return new ModelAndView("error-parameter", model);  
        } else {
            return new ModelAndView("error", model);  
        }
		
	}

}
