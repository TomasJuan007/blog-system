package com.ykse.blogs.dao;

import com.ykse.blogs.bean.Files;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilesDao {
    boolean saveBlob(Files files);
    List<Files> getAllFiles(@Param("startRow") int startRow, @Param("endRow") int endRow);
    int getFilesCount();
    Files getFilesById(int filesId);
}
