package com.ykse.blogs.controller;

import com.ykse.blogs.util.DESUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.security.InvalidKeyException;

@Controller
@RequestMapping(value = "/")
public class PhotoController {

    @RequestMapping( method = RequestMethod.GET)
    public String get() {
        return "index";
    }


    @RequestMapping(value = "/uploadimgctlr", method = RequestMethod.POST)
    public String uploadImageCtlr(ModelMap model,
                                  HttpServletRequest request,
                                  @RequestParam MultipartFile file){

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

                File encryptFile = new File(dir.getAbsolutePath() + File.separator + "encrypt-" + file.getOriginalFilename());
                BufferedOutputStream s = new BufferedOutputStream(new FileOutputStream(encryptFile));
                DESUtil.encrypt(is,s);
                s.close();
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
}
