package com.ykse.blogs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.security.SecureRandom;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final SecureRandom secureRandom = new SecureRandom();
    /**
     * 随机生成的字符串集合
     */
    private final String randomStrings = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    /**
     * 图片宽
     */
    private final int width = 80;
    /**
     * 图片高
     */
    private final int height = 26;
    /**
     * 干扰线数量
     */
    private static final int LINE_SIZE = 40;
    /**
     * 随机生成的字符串数量
     */
    private static final int STRING_NUM = 4;

    @RequestMapping(value="/code", method= RequestMethod.GET)
    public String getSystemManageLoginCode(HttpServletResponse response) {
        String random = null;
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Set-Cookie", "name=value; HttpOnly");
        response.setDateHeader("Expire", 0);
        try {
            random = getRandomImageCode(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return random;
    }

    /**
     * 生成随机图片
     */
    private String getRandomImageCode(HttpServletResponse response) {
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.fillRect(0,0,width,height);
        g.setFont(new Font("Times New Roman",Font.PLAIN,18));
        g.setColor(getRandomColor());
        for (int i=0; i<LINE_SIZE; i++) {
            drawLine(g);
        }
        String randomString = "";
        for (int i=0; i<STRING_NUM; i++) {
            randomString = drawString(g,randomString,i);
        }
        g.dispose();

        try {
            ByteArrayOutputStream tmp = new ByteArrayOutputStream();
            ImageIO.write(image,"png",tmp);
            tmp.close();
            int contentLength = tmp.size();
            response.setHeader("content-length", String.valueOf(contentLength));
            response.getOutputStream().write(tmp.toByteArray());
        } catch (Exception e) {
            logger.error("Error when generate image login code, exception: ",e);
        } finally {
            try {
                response.getOutputStream().flush();
                response.getOutputStream().close();
            } catch (IOException e) {
                logger.error("Error when close output while generating image login code, exception: ",e);
            }
        }
        return randomString;
    }

    private Color getRandomColor() {
        int fc = 110;
        int bc = 133;
        int r = fc + secureRandom.nextInt(bc-fc-16);
        int g = fc + secureRandom.nextInt(bc-fc-14);
        int b = fc + secureRandom.nextInt(bc-fc-18);
        return new Color(r,g,b);
    }

    private void drawLine(Graphics g) {
        int x = secureRandom.nextInt(width);
        int y = secureRandom.nextInt(height);
        int xl = secureRandom.nextInt(13);
        int yl = secureRandom.nextInt(15);
        g.drawLine(x,y,xl,yl);
    }

    private String drawString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(secureRandom.nextInt(101),
                secureRandom.nextInt(111),
                secureRandom.nextInt(121)));
        String rand = getRandomString(secureRandom.nextInt(randomStrings.length()));
        randomString += rand;
        g.translate(secureRandom.nextInt(3),secureRandom.nextInt(3));
        g.drawString(rand, 13*i, 16);
        return randomString;
    }

    private Font getFont() {
        return new Font("Fixedsys", Font.BOLD, 18);
    }

    private String getRandomString(int num) {
        return String.valueOf(randomStrings.charAt(num));
    }

}
