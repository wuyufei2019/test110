package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.Upload_FilesEntity;

public interface IUploadFilesDao {

	/**
     * 查询文件集合
     * @return
     */
	public List<Upload_FilesEntity> dataGrid(Map<String, Object> mapData);
	/**
     * 查询文件_总记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	/**
     * 保存文件
     * @return
     */
	public void saveInfo(Upload_FilesEntity file);
	/**
     * 删除文件
     * @return
     */
	public void deleteInfo(Upload_FilesEntity file);
	/**
     * 根据id查询文件
     * @return
     */
	public Upload_FilesEntity findInfoById(long id);
}
