package com.ykse.blogs.controller;

import com.ykse.blogs.bean.WechatFavoriteInfo;
import com.ykse.blogs.service.WechatFavoriteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/wechat")
public class WechatController {
    @Autowired
    private WechatFavoriteInfoService wechatFavoriteInfoService;

    @RequestMapping("/list")
    @ResponseBody
    public List<WechatFavoriteInfo> list() {
        return wechatFavoriteInfoService.getAllInfo();
    }
}
