package com.ykse.blogs.baseTest;

import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

/**
 * Spring测试基础类，用于加载Spring相关配置信息
 * 
 * @author huangtao
 * @version $Id: SpringTestCase.java, v 0.1 2016年11月14日 下午5:40:56 huangtao Exp $
 */

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTestCase extends AbstractJUnit4SpringContextTests {

}
