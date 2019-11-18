package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.ISSUE_FileTransmissionReceivingEntity;

/**
 * 文件传递接收service接口
 * @author jason
 *
 */
public interface IIssueWjcdjsService {
	/**
	 * 添加文件传递接收记录
	 * @param spd
	 * @return int 影响条数
	 */
	public int addInfor(Map<String, Object> mapData);

	/**
	 * 分页查询文件传递接收记录
	 * @return 
	 */
	public  Map<String, Object> dataGrid(Map<String, Object> mapData) ;
	
	/**
     * 查询文件传递接收记录数据表格_总记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	
	/**
	 * 删除文件传递接收记录
	 * @param id
	 * @return int 影响条数
	 */
	public int deleteInfo(long id);
	/**
	 *添加回执信息
	 * @param id
	 */
	public String addHzInfor(ISSUE_FileTransmissionReceivingEntity e);
	
	/**
	 * 根据安全文件发布ID删除文件传递接收记录
	 * @param fid  安全文件发布ID
	 * @return int 影响条数
	 */
	public int deleteInfoByFid(long fid);
	
	/**
     * 根据id查找文件传递接收记录
     * @param id
     * @return ISSUE_SecurityFileReleaseEntity
     */
	public ISSUE_FileTransmissionReceivingEntity findInfoById(long id);
	/**
	 * 根据当前用户id，和文件id查找文件传递接收记录
	 * @param id
	 * @return ISSUE_SecurityFileReleaseEntity
	 */
	public ISSUE_FileTransmissionReceivingEntity findInfoByIds(long uid,long fid);
	
	
	/**
	 * 修改为已经查看
	 * @param uid 用户id
	 * @param fid 文件 id
	 * @return int 影响条数
	 */
	public int updateIsorNotLook(long uid,long fid);
	
	/**
	 * 修改为已经下载
	 * @param uid 用户id
	 * @param fid 文件 id
	 * @return int 影响条数
	 */
	public int updateIsorNotDownload(long uid,long fid);
	/**
	 * 根据用户ID查询未读信息记录
	 * @param uid 用户ID
	 * @return 
	 */
	public List<ISSUE_FileTransmissionReceivingEntity> findInfoByUId(long uid);
	
	/**
	 * 导出excel
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);
	
	public List<Map<String, Object>> findUserListByState(int wjid,int state,int type);
	
}
