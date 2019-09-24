package com.ykse.blogs.dao;

import com.ykse.blogs.bean.Files;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FilesDao {
    boolean saveBlob(Files files);
    List<Files> getAllFiles(@Param("startRow") int startRow, @Param("endRow") int endRow);
    int getFilesCount();
    Files getFilesById(int filesId);
}
