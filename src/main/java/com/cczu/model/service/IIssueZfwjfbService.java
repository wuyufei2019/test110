package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.ISSUE_GovermentFileReleaseEntity;
import com.cczu.sys.system.entity.User;
/**
 * 政府文件发布service接口
 * @author jason
 *
 */
public interface IIssueZfwjfbService {
	
	
	/**
	 * 添加安全文件发布信息
	 * @param sfr
	 * @return  返回影响条数
	 */
	public int addInfor(ISSUE_GovermentFileReleaseEntity sfr);
	/**
	 * 添加安全文件发布信息
	 * @param sfr
	 * @return 返回新记录id
	 */
	public Long addInforReturnID(ISSUE_GovermentFileReleaseEntity sfr,String filePath);
	
	/**
	 * 查询政府工作人员信息
	 * @return
	 */
	public Map<String, Object> findAllZfList(Map<String, Object> mapData);
	
	/**
	 * 查询所有安全文件发布信息
	 * @return
	 */
	public List<ISSUE_GovermentFileReleaseEntity> findAlllist();
	
	/**
	 * 分页查询安全文件发布信息
	 * @return 
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) ;
	
	/**
	 * 分页查询安全文件发布信息（企业用户查看）
	 * @return 
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) ;
	
	/**
	 * 查询所有政府人员信息
	 * @return
	 */
	public List<User> findAllUser(String ids);
	
	/**
	 * 删除安全文件发布信息
	 * @param id
	 * @return int 影响条数
	 */
	public int deleteInfo(long id);
	/**
     * 根据id查找安全文件信息
     * @param id
     * @return ISSUE_GovermentFileReleaseEntity
     */
	public ISSUE_GovermentFileReleaseEntity findInfoById(long id);
	/**
	 * 修改安全文件发布信息
	 * @param id
	 * @return int 影响条数
	 */
	public int updateInfoByID(long id, ISSUE_GovermentFileReleaseEntity sfr, String filePath);
	
	/**
     * 根据条件查询全部企业数据表格
     * @return List<User>
     */
	public List<User> dataListE(Map<String, Object> map);
}
