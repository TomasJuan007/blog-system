package com.ykse.blogs.controller;

import com.ykse.blogs.bean.Files;
import com.ykse.blogs.bean.Pagination;
import com.ykse.blogs.bean.User;
import com.ykse.blogs.dao.FilesDao;
import com.ykse.blogs.exception.BusinessException;
import com.ykse.blogs.util.FileEncryptUtil;
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
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class FileController {

    @Autowired
    FilesDao filesDao;

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
        String pageNum = request.getParameter("pageNum");
        String numPerPage = request.getParameter("numPerPage");
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

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadImageCtlr(ModelMap model,
                                  HttpServletRequest request,
                                  @RequestParam MultipartFile file,
                                  HttpSession session) {

        //检查当前用户
        if(session.getAttribute("User") == null){
            throw new BusinessException("会话过期,请重新登陆！");
        }
        User user = (User)session.getAttribute("User");
        Integer userId = user.getUserId();

        //创建目录和文件
        String rootPath = request.getSession().getServletContext().getRealPath("/resources/");
        File dir = new File(rootPath + File.separator + "img");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
        String fileName = file.getOriginalFilename();

        try {
            //将上传文件写到磁盘
            InputStream is = null;
            is = file.getInputStream();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            int i;
            while ((i = is.read()) != -1) {
                stream.write(i);
            }
            stream.flush();
            is.close();
            stream.close();

            FileEncryptUtil fe = new FileEncryptUtil();
            String pass = userId.toString();
            fe.encrypt(serverFile, fileName, fe.md5s(pass)+fe.md5s(pass)+fe.md5s(pass));


//            //持久化
//            Files files = new Files();
//            files.setBlobContent(data);
//            files.setUser(user);
//            files.setFileName(latestUploadPhoto);
//            filesDao.saveBlob(files);

        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("photo", fileName);

        return "file";
    }

    @RequestMapping("/viewFile")
    public ModelAndView viewFile(ModelMap model,
                                 HttpServletRequest request,
                                 String filesId,
                                 HttpSession session) {

        ModelAndView modelAndView = new ModelAndView("/listFiles");

        //检查当前用户
        if(session.getAttribute("User") == null){
            throw new BusinessException("会话过期,请重新登陆！");
        }
        User user = (User)session.getAttribute("User");
        Integer userId = user.getUserId();

        try {

            //创建目录和文件
            String rootPath = request.getSession().getServletContext().getRealPath("/resources/");
            File dir = new File(rootPath + File.separator + "encrypt");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File serverFile = new File(dir.getAbsolutePath() + File.separator + "MVC.jpg");
//            if (serverFile.exists())
//            {
//                serverFile.delete();
//            }
            FileEncryptUtil fe = new FileEncryptUtil();
            String pass = userId.toString();
            fe.decrypt(serverFile,fe.md5s(pass)+fe.md5s(pass)+fe.md5s(pass));

        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("photo", "不知道");

        return modelAndView;
    }
}
