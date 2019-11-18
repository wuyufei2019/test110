package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQPX_ExamresultsEntity;

public interface IAqpxKscjDao {
	
	/**
	 * 添加考试成绩记录
	 * @param ae
	 */
	public long addInfo(AQPX_ExamresultsEntity ae);
	
	/**
	 * 修改成绩
	 * @param ae
	 */
	public void updateInfo(AQPX_ExamresultsEntity ae);
	
	/**
	 * 
	 * 查看员工成绩
	 * @param ygid
	 * @param kcid
	 * @return
	 */
	public List<AQPX_ExamresultsEntity> getlist(Long ygid,Long kcid);
	
	/**
	 * 
	 * @param ygid
	 * @param M2
	 * @return
	 */
	public List<AQPX_ExamresultsEntity> findid(Long ygid, String H);
	
	/**
	 * 查询员工所有的成绩
	 * @param ygid
	 * @return
	 */
	public List<AQPX_ExamresultsEntity> listall(Long ygid);
	
	
	/**
	 * 查询员工每门考试最高的成绩
	 * @param ygid
	 * @return
	 */
	public List<Map<String, Object>> listmax(Long ygid);

	/**
	 * 考试记录展示(企业普通员工查看)
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData);
	
	
	/**
	 * 考试记录展示(企业管理员查看)
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridCompanyAdmin(Map<String, Object> mapData);

	/**
	 * 外来方考试记录展示(企业管理员查看)
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridCompanyAdmin2(Map<String, Object> mapData);
	
	/**
	 * 企业普通员工查看数据统计
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData);
	
	/**
	 * 企业管理员查看数据统计
	 * @param mapData
	 * @return
	 */
	public int getTotalCount2(Map<String, Object> mapData);
	
	/**
	 * 统计外来方培训记录数量（企业管理员）
	 * @param mapData
	 * @return
	 */
	public int getTotalCount3(Map<String, Object> mapData);
	
	/**
	 * excel导出
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData);
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteInfo(Long id);
}
