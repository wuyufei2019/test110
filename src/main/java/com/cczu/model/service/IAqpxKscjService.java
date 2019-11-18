package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.AQPX_ExamresultsEntity;

public interface IAqpxKscjService {
	
	/**
	 * 添加考试成绩记录
	 * @param ae
	 */
	public void addInfo(AQPX_ExamresultsEntity ae);
	/**
	 * 添加考试成绩记录(返回实体id)
	 * @param ae
	 */
	public long addInfoReId(AQPX_ExamresultsEntity ae);
	
	/**
	 * 修改成绩
	 * @param ae
	 */
	public void updateInfo(AQPX_ExamresultsEntity ae);
	
	
	/**
	 * 查看员工成绩
	 * @param ygid
	 * @param kcid
	 * @return
	 */
	public List<AQPX_ExamresultsEntity> getlist(Long ygid,Long kcid);
	
	/**
	 * 通过员工id和时间来查询信息
	 * @param ygid
	 * @param M2
	 * @return
	 */
	public List<AQPX_ExamresultsEntity> findid(Long ygid, String H);
	
	/**
	 * 通过员工id查询成绩
	 * @param ygid
	 * @return
	 */
	public List<AQPX_ExamresultsEntity> listall(Long ygid);
	
	/**
	 * 通过员工id和课程id查询每科最高成绩
	 * @param ygid
	 * @return
	 */
	public List<Map<String, Object>> listmax(Long ygid );
	
	/**
	 * 考试记录数据(企业普通员工查看)
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String,Object> mapData);

	/**
	 * 考试记录数据(企业管理员查看)
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridCompanyAdmin(Map<String,Object> mapData);
	
	/**
	 * 外来方考试记录数据(企业管理员查看)
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridCompanyAdmin2(Map<String,Object> mapData);
	
	/**
	 * 考试记录数据导出
	 * @param mapData
	 * @return
	 */
	public void exportExcel(HttpServletResponse response,Map<String, Object> mapData);
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteInfo(long id);
	
	
	/**
	 * 
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getksjlByJH(Map<String,Object> mapData);
}
