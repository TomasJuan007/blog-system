package com.ykse.blogs.dao;

import com.ykse.blogs.bean.WechatFavoriteInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WechatFavoriteDao {
    List<WechatFavoriteInfo> getAll();
}
