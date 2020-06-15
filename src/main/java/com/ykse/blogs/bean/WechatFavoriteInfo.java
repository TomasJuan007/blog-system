package com.ykse.blogs.bean;

import java.util.Date;
import java.util.List;

public class WechatFavoriteInfo {
    private Long idWechatFavoriteInfo;
    private String hash;
    private String title;
    private Date dateCreated;
    private Date dateUpdated;
    private List<WechatFavoriteTag> tags;

    public Long getIdWechatFavoriteInfo() {
        return idWechatFavoriteInfo;
    }

    public void setIdWechatFavoriteInfo(Long idWechatFavoriteInfo) {
        this.idWechatFavoriteInfo = idWechatFavoriteInfo;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public List<WechatFavoriteTag> getTags() {
        return tags;
    }

    public void setTags(List<WechatFavoriteTag> tags) {
        this.tags = tags;
    }
}
