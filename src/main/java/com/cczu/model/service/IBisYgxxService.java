package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.BIS_EmployeeEntity;

/**
 * @author jason
 * 员工信息Service
 */
public interface IBisYgxxService {
	
	/**
	 * 添加
	 * @param ac
	 */
	public void addInfo(BIS_EmployeeEntity em);
	
	/**
	 * 已存在账号,添加账号的员工信息
	 * @param ac
	 */
	public void addInfo2(BIS_EmployeeEntity em);
	
	/**
	 * 添加员工信息返回员工id
	 * @param ac
	 */
	public long addInforReturnID(BIS_EmployeeEntity ac);
	
	/**
	 * 修改员工信息
	 * @param ac
	 */
	public void updateInfo(BIS_EmployeeEntity em);
	
	/**
	 * 删除员工信息
	 * @param id
	 */
	public void deleteInfo(Long id);

	/**
	 * 绑定用户账户
	 * @param id
	 */
	void connectUserAccount(long id,long userid);
	/**
	 * 解除绑定用户账户
	 * @param id
	 */
	void removeBind(long id);
	/**
	 * 查看是够已经绑定用户
	 * @param userid 用户id
	 */
	int getBindCount(long userid);

	/**
	 * list数据
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData);	
	
	/**
	 * 伤亡人员list数据
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> swrylist(Map<String, Object> mapData);	
	
	/**
	 * 选择伤亡人员list数据
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> swrylist2(Map<String, Object> mapData);	
	
	/**
	 * 根据员工信息id查询员工信息
	 * @param qyid
	 * @return
	 */
	public BIS_EmployeeEntity findInfoByID(Long id);
	
	/**
	 * 根据员工信息id查询员工详情
	 * @return
	 */
	public Map<String, Object> findAllByID(Long id);
	
	/**
	 * 导出excel
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);
	
	/**
	 * 根据cardNo 返回工卡id
	 * @param cardNo
	 * @return
	 */
	public long CardIDBycardNo(String cardNo);
	
	/**
	 * list数据 安监和管理员查看
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridAJ(Map<String, Object> mapData);	
	
	/**
	 * 根据身份证获取员工信息
	 * @param mapData
	 * @return
	 */
	public String findYgxxList(String idcard ,String qyid);	
	
	/**
	 * 根据身份证获取员工信息
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> findYgnmList(String idcard ,String qyid);	
	
	/**
	 * 导入
	 */
	public Map<String,Object> exinExcel(Map<String, Object> map);
	
	/**@param 员工id
	 * 返回员工和企业的某些信息
	 */
	public Map<String,Object> findQyYgInfoByID(Long id);
	
	
	/**
	 * 根据用户id查询员工信息
	 * @param userid 用户id
 	 * @return
	 */
	public BIS_EmployeeEntity findInfoByUserID(Long userid);
	
	/**
	 * 导出word
	 * @param id
	 * @return
	 */
	Map<String, Object> getExportWord(long id);
	
	/**
	 * 导出职业健康监护档案表word
	 * @param id
	 * @return
	 */
	Map<String, Object> getExportWord2(long id);

	/**
	 * 保存
	 * @param yg
	 */
	public void save(BIS_EmployeeEntity yg);

	/**
	 * 同一公司下验证是否有相同二维码
	 * @param id
	 * @param ewm
	 * @param qyid
	 * @return
	 */
	public boolean checkSameEwm(long id, String ewm,long qyid);
}
