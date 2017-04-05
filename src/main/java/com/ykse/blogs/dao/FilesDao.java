package com.ykse.blogs.dao;

import com.ykse.blogs.bean.Files;

/**
 * Created by Administrator on 2017/4/5.
 */
public interface FilesDao {

    /**
     * 保存文件
     *
     * @param files
     * @return
     */
    public boolean saveBlob(Files files);
}
