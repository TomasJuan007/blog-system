package com.ykse.blogs.service.impl;

import com.ykse.blogs.bean.WechatFavoriteInfo;
import com.ykse.blogs.dao.WechatFavoriteDao;
import com.ykse.blogs.service.WechatFavoriteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WechatFavoriteInfoServiceImpl implements WechatFavoriteInfoService {
    @Autowired
    private WechatFavoriteDao wechatFavoriteDao;

    @Override
    public List<WechatFavoriteInfo> getAllInfo() {
        List<WechatFavoriteInfo> wechatFavoriteInfos = wechatFavoriteDao.getAll();
        wechatFavoriteInfos.forEach(e -> e.setHash("https://mp.weixin.qq.com/s/"+e.getHash()));
        return wechatFavoriteInfos;
    }
}
