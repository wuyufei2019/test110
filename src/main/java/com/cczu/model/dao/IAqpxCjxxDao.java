package com.cczu.model.dao;

import java.sql.Date;
import java.util.List;

import com.cczu.model.entity.AQPX_ExamresultsEntity;

public interface IAqpxCjxxDao {
	
	/**
	 * 添加考试成绩
	 * @param ae
	 */
	public void addInfo(AQPX_ExamresultsEntity ae);
	
	/**
	 * 修改成绩
	 * @param ae
	 */
	public void updateInfo(AQPX_ExamresultsEntity ae);
	
	/**
	 * 获取员工每一科考试的所有成绩
	 * @param ygid
	 * @param kcid
	 * @return
	 */
	public List<AQPX_ExamresultsEntity> getlist(Long ygid,Long kcid,Date kssj);
	
	

}
