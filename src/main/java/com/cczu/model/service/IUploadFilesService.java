package com.cczu.model.service;

import java.util.Map;

import com.cczu.model.entity.Upload_FilesEntity;
/**
 * 
 * @ClassName: IaqjdQyjbxxService
 * @Description: 安全监督管理_企业行政备案接口
 * @author jason
 * @date 2017年6月8日
 *
 */
public interface IUploadFilesService {
	/**
     * 查询企业行政备案信息数据表格
     * @return
     */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	/**
     * 根据id查找企业行政备案信息
     * @return
     */
	public Upload_FilesEntity findInfoById(long id);
	/**
     * 添加企业行政备案信息
     * @return
     */
	public void addInfo(Upload_FilesEntity file);
	/**
     * 删除企业行政备案信息
     * @return
     */
	public void deleteInfo(long id);
}
