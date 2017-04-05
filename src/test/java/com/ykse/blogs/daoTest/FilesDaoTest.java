package com.ykse.blogs.daoTest;

import com.ykse.blogs.baseTest.SpringTestCase;
import com.ykse.blogs.bean.Files;
import com.ykse.blogs.bean.User;
import com.ykse.blogs.dao.FilesDao;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;

/**
 * Created by Administrator on 2017/4/5.
 */
public class FilesDaoTest extends SpringTestCase {

    public static Files files = new Files();

    @BeforeClass
    public static void init() {
        User user = new User();
        user.setUserId(3);
        files.setUser(user);
        files.setFileName("test");
    }

    @Autowired
    FilesDao filesDao;

    @Test
    public void saveBlobTest() {
        try {
        File file = new File("C:\\Users\\Administrator\\Desktop\\草稿\\001.gif");
        FileInputStream fis = new FileInputStream(file);
        byte[] buf = new byte[1024];
        StringBuffer sb = new StringBuffer();
        while ((fis.read(buf)) != -1) {
            sb.append(new String(buf));
            buf = new byte[1024];
        }
        Blob blob = new SerialBlob(sb.toString().getBytes());
        files.setBlobContent(blob);
        boolean re = filesDao.saveBlob(files);
        if (re == true) {
            System.out.println("success");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
