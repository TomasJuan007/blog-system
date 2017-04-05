package com.ykse.blogs.controller;

import com.ykse.blogs.bean.Files;
import com.ykse.blogs.bean.Pagination;
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
import org.springframework.web.servlet.ModelAndView;

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
import java.util.List;

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

    /**
     * 展示文件
     *
     * @param request
     * @return
     */
    @RequestMapping(value="/listFiles")
    public ModelAndView getFiles(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/listFiles");

        Pagination<Files> page = new Pagination<>();
        String pageNum = (String) request.getParameter("pageNum");
        String numPerPage = (String) request.getParameter("numPerPage");
        Integer pagenum = (pageNum == null || pageNum == "")
                ? 1 : Integer.parseInt(pageNum);
        Integer numperpage = (numPerPage == null || numPerPage == "")
                ? 10 : Integer.parseInt(numPerPage);
        page.setCurrentPage(pagenum);
        page.setNumPerPage(numperpage);
        page.setTotalCount(filesDao.getFilesCount());
        page.calcutePage();

        int startRow = (page.getCurrentPage() - 1) * page.getNumPerPage();
        int endRow = page.getNumPerPage();
        List<Files> files = filesDao.getAllFiles(startRow, endRow);
        page.setContent(files);
        request.setAttribute("page", page);

        return modelAndView;
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

    @RequestMapping("/viewFile")
    public ModelAndView viewFile(String filesId, String userId) {
        ModelAndView modelAndView = new ModelAndView("/listFiles");
        getKey(userId);
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        //TODO: 由filesId得到解密后的file

        return modelAndView;
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
