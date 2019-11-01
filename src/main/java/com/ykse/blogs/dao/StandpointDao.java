package com.ykse.blogs.dao;

import com.ykse.blogs.bean.Standpoint;
import org.springframework.stereotype.Repository;

@Repository
public interface StandpointDao {
	boolean saveStandpoint(Standpoint standpoint);
	boolean deleteStandpoint(Standpoint standpoint);
}
