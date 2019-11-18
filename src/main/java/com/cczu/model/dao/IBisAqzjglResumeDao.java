package com.cczu.model.dao;

import java.util.List;

import com.cczu.model.entity.BIS_DirectorResumeEntity;

public interface IBisAqzjglResumeDao {
	/**
     * 查询所有
     * @return
     */
	public List<BIS_DirectorResumeEntity> findAlllist();
	/**
     * 根据安监局ID查询所
     * @return
     */
	public List<BIS_DirectorResumeEntity> findAllById1(long id1);
	/**
     * 根据id查找安全总监
     * @return
     */
	public BIS_DirectorResumeEntity findInfoById(long id);
	/**
     * 添加安全总监返回ID
     * @return
     */
	public Long addInfore(BIS_DirectorResumeEntity bis);
	/**
     * 修改安全总监
     * @return
     */
	public void updateInfo(BIS_DirectorResumeEntity bis);
	/**
     * 删除安全总监
     * @return
     */
	public void deleteInfo(long id);
}
