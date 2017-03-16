package com.ykse.blogs.dao;

import com.ykse.blogs.bean.Standpoint;

/**
 * Standpoint表数据库操作接口
 * 
 * @author tao.huang
 * @version $Id: StandpointDao.java, v 0.1 2017年3月16日 下午1:20:06 tao.huang Exp$
 */
public interface StandpointDao {
	
	/**
	 * 保存立场
	 * 
	 * @param standpoint
	 * @return
	 */
	public boolean saveStandpoint(Standpoint standpoint);
	
	/**
	 * 取消立场
	 * 
	 * @param standpoint
	 * @return
	 */
	public boolean deleteStandpoint(Standpoint standpoint);
}
