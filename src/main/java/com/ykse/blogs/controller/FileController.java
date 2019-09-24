package com.ykse.blogs.controller;

import com.ykse.blogs.bean.Files;
import com.ykse.blogs.bean.Pagination;
import com.ykse.blogs.bean.User;
import com.ykse.blogs.dao.FilesDao;
import com.ykse.blogs.exception.BusinessException;
import com.ykse.blogs.service.FileEncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class FileController {

    @Autowired
    private FilesDao filesDao;

    @Autowired
    private FileEncryptionService fileEncryptionService;

    @RequestMapping(value="/listFiles")
    public ModelAndView getFiles(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/listFiles");

        Pagination<Files> page = new Pagination<>();
        String pageNumStr = request.getParameter("pageNum");
        String numPerPageStr = request.getParameter("numPerPage");
        Integer pageNum = (pageNumStr == null || "".equals(pageNumStr))
                ? 1 : Integer.parseInt(pageNumStr);
        Integer numPerPage = (numPerPageStr == null || "".equals(numPerPageStr))
                ? 10 : Integer.parseInt(numPerPageStr);
        page.setCurrentPage(pageNum);
        page.setNumPerPage(numPerPage);
        page.setTotalCount(filesDao.getFilesCount());
        page.calculatePage();

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
        if(session.getAttribute("User") == null) {
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
            InputStream is;
            is = file.getInputStream();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            int i;
            while ((i = is.read()) != -1) {
                stream.write(i);
            }
            stream.flush();
            is.close();
            stream.close();

            String pass = userId.toString();
            fileEncryptionService.encrypt(serverFile, fileName,
                    fileEncryptionService.md5s(pass)+fileEncryptionService.md5s(pass)
                            +fileEncryptionService.md5s(pass));

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
        if(session.getAttribute("User") == null) {
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
            String pass = userId.toString();
            fileEncryptionService.decrypt(serverFile,
                    fileEncryptionService.md5s(pass)+fileEncryptionService.md5s(pass)
                            +fileEncryptionService.md5s(pass));
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("photo", "不知道");

        return modelAndView;
    }

    @RequestMapping(value="/uploadFile", method=RequestMethod.GET)
    public ModelAndView uploadFile(Model model) {
        return new ModelAndView("file");
    }
}
