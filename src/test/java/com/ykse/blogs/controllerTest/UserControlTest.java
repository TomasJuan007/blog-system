package com.ykse.blogs.controllerTest;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ykse.blogs.baseTest.SpringMVCTestCase;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControlTest extends SpringMVCTestCase{
        
    private MockMvc mockMvc;  
    
    @Autowired
    protected WebApplicationContext wac;
  
    @Before  
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }  

}
