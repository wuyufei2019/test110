package com.cczu.model.service;

import java.util.List;

import com.cczu.model.entity.BIS_DirectorResumeEntity;
/**
 * 
 * @ClassName: IBisAqzjglService
 * @Description: 企业基本信息-安全总监管理接口
 * @author jason
 *
 */
public interface IBisAqzjglResumeService {
	/**
     * 查询所有
     * @return
     */
	public List<BIS_DirectorResumeEntity> findAll();
	
	/**
     * 根据安监总监查询相应的简历信息
     * @return
     */
	public List<BIS_DirectorResumeEntity> findAllById1(long id1);

	
	/**
     * 根据id查找
     * @return
     */
	public BIS_DirectorResumeEntity findInfoById(long id);
	
	/**
     * 添加
     * @return
     */
	public Long addInfo(BIS_DirectorResumeEntity bis);
	/**
     * 修改
     * @return
     */
	public void updateInfo(BIS_DirectorResumeEntity bis);
	/**
     * 删除
     * @return
     */
	public void deleteInfo(long id);

}
