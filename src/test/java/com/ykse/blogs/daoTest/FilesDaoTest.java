package com.ykse.blogs.daoTest;

import com.ykse.blogs.baseTest.SpringTestCase;
import com.ykse.blogs.bean.Files;
import com.ykse.blogs.bean.User;
import com.ykse.blogs.dao.FilesDao;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.List;

/**
 * Created by Administrator on 2017/4/5.
 */
public class FilesDaoTest extends SpringTestCase {

    public static Files files = new Files();
    public static User user = new User();

    @BeforeClass
    public static void init() {
        user.setUserId(3);
        files.setUser(user);
        files.setFileName("test");
    }

    @Autowired
    FilesDao filesDao;

    @Test
    public void saveBlobTest() {
        try {
            //读入文件
            File file = new File("C:\\Users\\Administrator\\Desktop\\草稿\\001.gif");
            //序列化
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(arrayOutputStream);
            objectOutputStream.writeObject(file);
            objectOutputStream.flush();
            byte[] data=arrayOutputStream.toByteArray();
            //保存到数据库
            files.setFileName("001.gif");
            files.setUser(user);
            files.setBlobContent(data);
            Assert.assertTrue(filesDao.saveBlob(files));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllFilesTest() {
        List<Files> files = filesDao.getAllFiles(0,10);
        Assert.assertNotNull(files);
        System.out.println(files.get(0).getFileName());
    }

    @Test
    public void getFilesByIdTest() throws IOException, ClassNotFoundException {
        //读数据库
        Files files = filesDao.getFilesById(Integer.valueOf("23"));
        byte[] data = files.getBlobContent();
        //反序列化
        InputStream is = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(is);
        File file = (File) ois.readObject();
        //取出文件
        FileInputStream fis = new FileInputStream(file);
        File file1 = new File("target\\file.gif");
        try {
            OutputStream os = new FileOutputStream(file1);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
