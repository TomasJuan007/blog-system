package com.ykse.blogs.utilTest;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import com.ykse.blogs.util.MD5Util;

public class MD5UtilTest {
    
    
    @Test
    public void validPasswordTest() {
        Boolean psw = null;
        try {
         psw = MD5Util.validPassword("123456", "0F461F0F935C6052D1F1E6CE1EC07EFC31A5517BF738D1FD788D4946");
        } catch (NoSuchAlgorithmException e) {
            
        } catch (UnsupportedEncodingException e) {
            
        }
        System.out.println(psw);
    }
    
    @Test
    public void getEncryptedPwdTest() {
       String psw = null;
       try {
        psw = MD5Util.getEncryptedPwd("123456");
       } catch (NoSuchAlgorithmException e) {
           
       } catch (UnsupportedEncodingException e) {
           
       }
       System.out.println(psw);
    }
    
}
