package com.ykse.blogs.baseTest;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.test.context.ContextConfiguration; 
import org.springframework.test.context.web.WebAppConfiguration;  
import org.springframework.web.context.WebApplicationContext;  


/**
 * SpringMVC测试基础类，用于加载Spring相关配置信息
 * 
 * @author huangtao
 * @version $Id: SpringMVCTestCase.java, v 0.1 2016年11月16日 上午11:01:28 huangtao Exp $
 */
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:springmvc/spring-mvc.xml" })  
public class SpringMVCTestCase {  
  
    @Autowired  
    protected WebApplicationContext wac;  
  
}  