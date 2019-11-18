package com.cczu.model.dao;

import java.util.List;
import java.util.Map;





import com.cczu.model.entity.ISSUE_SecurityFileReleaseEntity;

public interface IIssueAqwjfbDao {
	/**
	 * 添加安全文件发布信息
	 * @param sfr
	 * @return 返回影响记录条数
	 */
	public int addInfor(ISSUE_SecurityFileReleaseEntity sfr);
	/**
	 * 添加安全文件发布信息
	 * @param sfr
	 * @return 返回新记录id
	 */
	public Long addInforReturnID(ISSUE_SecurityFileReleaseEntity sfr);
	/**
	 * 查询所有安全文件发布信息
	 * @return
	 */
	public List<ISSUE_SecurityFileReleaseEntity> findAlllist();
	
	/**
	 * 分页查询安全文件发布信息
	 * @return 
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) ;
	
	/**
	 * 分页查询安全文件发布信息(企业用户查看)
	 * @return 
	 */
	public List<Map<String,Object>> dataGrid2(Map<String, Object> mapData ) ;
	
	/**
     * 查询安全文件数据表格_总记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	
	/**
     * 查询安全文件数据表格_总记录数(企业用户)
     * @return
     */
	public int getTotalCount2(Map<String, Object> mapData);
	
	/**
	 * 删除安全文件发布信息
	 * @param id
	 * @return int 影响条数
	 */
	public int deleteInfo(long id);
	/**
     * 根据id查找安全文件信息
     * @param id
     * @return ISSUE_SecurityFileReleaseEntity
     */
	public ISSUE_SecurityFileReleaseEntity findInfoById(long id);
	
	
	/**
	 * 修改安全文件发布信息
	 * @param id
	 * @return int 影响条数
	 */
	public int updateInfoByID(long id,ISSUE_SecurityFileReleaseEntity sfr);
	/**
	 * 获取qyids字段
	 * @param id
	 */
	public String getqyids(long id);
	
	}


