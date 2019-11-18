package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.BIS_EmployeeEntity;
import com.cczu.sys.system.entity.User;

/**
 * @author jason
 * 员工信息DAO
 */
public interface IBisYgxxDao {
	
	/**
	 * list数据
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData);
	
	/**
	 * 已绑定伤亡人员list数据
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> swrylist(Map<String, Object> mapData);
	
	/**
	 * 绑定伤亡人员list数据
	 * @param mapData
	 * @return
	 */
	public List<BIS_EmployeeEntity> swrylist2(Map<String, Object> mapData);

	/**
	 * 伤亡人员数量
	 * @param mapData
	 * @return
	 */
	public int getSwryTotalCount(Map<String, Object> mapData);
	
	/**
	 * 选择伤亡人员数量
	 * @param mapData
	 * @return
	 */
	public int getSwryTotalCount2(Map<String, Object> mapData);
	
	/**
	 * 根据用户id查询员工信息
	 * @param userid 用户id
 	 * @return
	 */
	public BIS_EmployeeEntity findInfoByUserID(Long userid);
	
	/**
	 * 删除员工信息
	 * @param id 员工信息id
	 * @return
	 */
	public void deleteInfo(Long id);
	
	
	/**
	 * 分页查询总记录数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData);

	/**
	 * 条件判断
	 * @param mapData
	 * @return
	 */
	public String content(Map<String, Object> mapData);
	
	/**
	 * 获得导出数据
	 * @return
	 */
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData);
	
	/**
	 * list数据  安监和管理员查看
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridAJ(Map<String, Object> mapData);
	
	/**
	 * 分页查询总记录数
	 * @param mapData
	 * @return
	 */
	public int getTotalCountAJ(Map<String, Object> mapData);
	
	/**
	 * 获得已有id
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> findYgxxList(String idcard ,String qyid) ;

	/**
	 * 根据工号绑定员工和用户
	 * @param mapData
	 * @return
	 */
	public void updID1();
	
	/**
	 * 获得已有id
	 * @param mapData
	 * @return
	 */
	public Map<String,Object> findQyYgInfoByID(Long id);
	
	/**
	 * 根据id获得详情
	 * @param mapData
	 * @return
	 */
	public Map<String,Object> findAllByID(Long id);
	
	/**
	 * 根据id获得详情
	 * @param id
	 * @return
	 */
	public BIS_EmployeeEntity findByID(Long id);
	
	/**
	 * 根据工号删除员工信息
	 * @param id 员工信息id
	 * @return
	 */
	public void deleteInfoByM2(String id);
	/**
	 * 关联账户信息
	 * @param id 员工信息id，userid 用户信息
	 * @return
	 */
	 void connectUserAccount(long id, long userid);
	/**
	 * 解除绑定用户账户
	 * @param id
	 */
	void removeBind(long id);
	/**
	 * 获取账号绑定员工的个数
	 * @param id
	 */
    int getBindCount(long id);
    
    /**
     * 两表比较删除离职员工信息
     */
	public void deleteDimission(Long qyid);
	
    /**
     * 两表比较更新已有员工信息
     */
	public void updateInfor(Long qyid);
	
	/**
	 * 两表比较插入新员工数据
	 * @param bis
	 */
	public void addNewEmployee();
	
	/**
	 * 两表比较删除离职用户
	 * @param bis
	 */
	public void deleteDimissionUser(Long qyid);
	
	/**
	 * 两表比较插入新用户数据
	 * @param bis
	 */
	public void addNewUser();

	/**
	 * 查找没有角色的用户
	 * @return
	 */
	public List<User> findNoRoleUser(Long qyid);
	
	/**
	 * 根据工号删除离职人员
	 */
	public void deleteUserByM2(String loginname);
	
	/**
	 * 获得部门和基础信息
	 * @param mapData
	 * @return
	 */
	public Map<String,Object> findBaseInfor(Long id);
}
