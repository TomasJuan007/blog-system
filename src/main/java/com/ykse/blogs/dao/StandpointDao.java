package com.ykse.blogs.dao;

import com.ykse.blogs.bean.Standpoint;

public interface StandpointDao {
	boolean saveStandpoint(Standpoint standpoint);
	boolean deleteStandpoint(Standpoint standpoint);
}
