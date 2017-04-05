package com.ykse.blogs.controller;

import com.ykse.blogs.bean.Files;
import com.ykse.blogs.bean.User;
import com.ykse.blogs.dao.FilesDao;
import com.ykse.blogs.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Blob;
import java.sql.SQLException;

@Controller
@RequestMapping(value = "/")
public class PhotoController {

    @Autowired
    FilesDao filesDao;

    private static Key key;

    @RequestMapping( method = RequestMethod.GET)
    public String get() {
        return "index";
    }


    @RequestMapping(value = "/uploadimgctlr", method = RequestMethod.POST)
    public String uploadImageCtlr(ModelMap model,
                                  HttpServletRequest request,
                                  @RequestParam MultipartFile file,
                                  HttpSession session){

        if(session.getAttribute("User") == null){
            throw new BusinessException("会话过期,请重新登陆！");
        }
        User user = (User)session.getAttribute("User");
        Integer userId = user.getUserId();

        String rootPath = request.getSession().getServletContext().getRealPath("/resources/");
        File dir = new File(rootPath + File.separator + "img");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
        String latestUploadPhoto = file.getOriginalFilename();

        //write uploaded image to disk
        try {
            try (InputStream is = file.getInputStream();
                 BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {

                int i;
                while ((i = is.read()) != -1) {
                    stream.write(i);
                }
                stream.flush();

                //保存加密二进制文件
                getKey(userId.toString());
                Files files = new Files();

                Cipher cipher = null;
                try {
                    cipher = Cipher.getInstance("DES");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                }
                cipher.init(Cipher.ENCRYPT_MODE, key);

                try {
                    CipherInputStream cis = new CipherInputStream(is, cipher);

                    byte[] buf = new byte[1024];
                    StringBuffer sb = new StringBuffer();
                    while ((cis.read(buf)) != -1) {
                        sb.append(new String(buf));
                        buf = new byte[1024];
                    }
                    Blob blob = new SerialBlob(sb.toString().getBytes());
                    files.setBlobContent(blob);
                    files.setUser(user);
                    files.setFileName(latestUploadPhoto);
                    filesDao.saveBlob(files);
                    cis.close();
                } catch (IOException e) {
                    System.out.println("error : " + e.getMessage());
                } catch (SerialException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                is.close();

            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("error : " + e.getMessage());
        }

        //send photo name to jsp
        model.addAttribute("photo", latestUploadPhoto);

        return "file";
    }

    /**
     * 根据参数生成KEY
     */
    private static void getKey(String strKey) {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance("DES");
            _generator.init(new SecureRandom(strKey.getBytes()));
            key = _generator.generateKey();
            _generator = null;
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
    }
}
