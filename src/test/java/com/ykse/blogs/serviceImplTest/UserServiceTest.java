package com.ykse.blogs.serviceImplTest;

import org.junit.Test;  
import org.springframework.beans.factory.annotation.Autowired;

import com.ykse.blogs.baseTest.SpringTestCase;
import com.ykse.blogs.bean.User;
import com.ykse.blogs.service.UserService;

/**
 * 用户业务逻辑处理实现类单元测试
 * 
 * @author huangtao
 * @version $Id: UserServiceTest.java, v 0.1 2016年11月14日 下午4:45:46 huangtao Exp $
 */
public class UserServiceTest extends SpringTestCase {
	
	@Autowired  
    private UserService userService; 
	
	@Test
    public void getUserByIdTest(){  
        User user = userService.getUserById(1);  
        System.out.println(user.getUserName() + ":" + user.getUserPassword());
    }
	
	@Test
    public void getUserByAccountTest(){  
        User user = userService.getUserByAccount("2");
        System.out.println(user.getUserName() + ":" + user.getUserPassword());
    }
	
	@Test
    public void saveUserTest(){  
	    User user = new User();
	    user.setUserAccount("8463@qq.com");
	    user.setUserPassword("8888");
        user.setUserName("test");
        user.setUserSex("F");
        user.setUserPhone("188");
        System.out.println("插入用户结果：" + userService.saveUser(user));
    }
	
	@Test
    public void updateUserTest(){  
	    User user = new User();
        user.setUserId(1);
        user.setUserName("test");
        user.setUserPhone("188");
        System.out.println("更新用户结果：" + userService.updateUser(user));
    }
	
	@Test
    public void changePswTest(){
	    User user = new User();
	    user.setUserId(1);
	    user.setUserPassword("123456");
        System.out.println("修改密码结果：" + userService.changePsw(user));
    }

}
