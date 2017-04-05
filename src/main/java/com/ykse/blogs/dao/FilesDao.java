package com.ykse.blogs.dao;

import com.ykse.blogs.bean.Files;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    boolean saveBlob(Files files);

    /**
     * 获取文件列表
     *
     * @return
     */
    List<Files> getAllFiles(@Param("startRow") int startRow, @Param("endRow") int endRow);

    /**
     * 获取文件数
     *
     * @return
     */
    int getFilesCount();
}
