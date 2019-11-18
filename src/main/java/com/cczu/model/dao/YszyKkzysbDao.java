package com.cczu.model.dao;

import com.cczu.model.entity.YSZY_KkTaskDeclarationEntity;
import com.cczu.model.entity.YSZY_TransportationGoods;
import com.cczu.sys.system.entity.Role;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 卡口作业申报dao层
 *
 */
@Repository("YszyKkzysbDao")
public class YszyKkzysbDao extends BaseDao<YSZY_KkTaskDeclarationEntity, Long> {

	/**
	 * 卡口作业申报list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = " SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.* FROM yszy_kktaskdeclaration a"
				   + "  where a.s3 = 0 " + content + " ) "
				   + " as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	/**
	 * 卡口作业申报list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM yszy_kktaskdeclaration a  WHERE a.s3=0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("entrust_company")!=null&&mapData.get("entrust_company")!=""){
			content = content + " AND a.entrust_company like '%"+mapData.get("entrust_company")+"%' "; 
		}
		if(mapData.get("consignee_company")!=null&&mapData.get("consignee_company")!=""){
			content = content + " AND a.consignee_company like '%"+mapData.get("consignee_company")+"%' "; 
		}
		if(mapData.get("accept_company")!=null&&mapData.get("accept_company")!=""){
			content = content + " AND a.accept_company like '%"+mapData.get("accept_company")+"%' "; 
		}
		if(mapData.get("plate_num")!=null&&mapData.get("plate_num")!=""){
			content = content + " AND a.plate_num like '%"+mapData.get("plate_num")+"%' "; 
		}
		if(mapData.get("driver_name")!=null&&mapData.get("driver_name")!=""){
			content = content + " AND a.driver_name like '%"+mapData.get("driver_name")+"%' "; 
		}
		if(mapData.get("type")!=null&&mapData.get("type")!=""){
			content = content + " AND a.type = '"+mapData.get("type")+"' "; 
		}
		
		if(mapData.get("userid")!=null&&mapData.get("userid")!=""){
			content = content + " AND a.userid = "+mapData.get("userid");
		}
		
		if(mapData.get("cphm")!=null&&mapData.get("cphm")!=""){
			content = content + " AND a.plate_num = '"+mapData.get("cphm")+"' "; 
		}
		return content;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" update yszy_kktaskdeclaration set s3=1 WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 添加卡口作业申报
	 * @param clcs
	 */
	public void addInfo(YSZY_KkTaskDeclarationEntity entity) {
		save(entity);
	}
	
	/**
	 * 根据id查找卡口作业申报
	 * @param id
	 * @return
	 */
	public YSZY_KkTaskDeclarationEntity findInfoById(long id) {
		YSZY_KkTaskDeclarationEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 修改卡口作业申报
	 * @param entity
	 */
	public void updateInfo(YSZY_KkTaskDeclarationEntity entity) {
		save(entity);
	}

	/**
	 * 根据id查询卡口作业申报
	 * @return
	 */
	public YSZY_KkTaskDeclarationEntity findInforById(Long id) {
		return findInfoById(id);
	}


	/**
	 * 根据企业id查询所有卡口作业申报信息
	 * @return
	 */
	public List<YSZY_KkTaskDeclarationEntity> findAllInfo() {
		String sql ="SELECT a.* FROM yszy_kktaskdeclaration a WHERE a.s3=0 ";
		List<YSZY_KkTaskDeclarationEntity> list=findBySql(sql, null, YSZY_KkTaskDeclarationEntity.class);
		return list;
	}
	
	/**
	 * 导出Excel
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExportInfo(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql=" select a.* from yszy_kktaskdeclaration a where a.s3=0 " + content + " ORDER BY a.id DESC";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}


	public void updateOrderState(String number, int state) {
		String updateSql = " update yszy_kktaskdeclaration set state=:p1 where number =:p2";
		updateBySql(updateSql,new Parameter(state,number));
	}

	public List<YSZY_TransportationGoods> listByOrderNum(String orderNum){
		String sql = "select b.* from yszy_kktaskdeclaration a inner join yszy_transoods b on b.transid=a.id where " +
				" a.s3 =0 and a.number= :p1";
		List<YSZY_TransportationGoods> list = findBySql(sql,new Parameter(orderNum), YSZY_TransportationGoods.class);
		return list;
	}
	/*
     根据登陆者id得到登陆者的角色编码
      */
	public List<Role> getUserRoleCode(int id){
		String sql = "SELECT a.* FROM t_role as a LEFT JOIN t_user_role as b ON a.id = b.ROLE_ID LEFT JOIN t_user as c ON b.USER_ID = c.ID WHERE c.ID ="+id;
		List<Role> userList = findBySql(sql,null, Role.class);
		return userList;
	}

	/*
	得到已经超过预计到达时间两天的运单id集合
	 */
	public List<Long> getYdId(){
		String sql = "SELECT a.id FROM yszy_kktaskdeclaration AS a WHERE a.s3 = 0 AND ((SELECT DATEDIFF(day,(SELECT CONVERT(VARCHAR(100),GETDATE(),121)),a.predict_arrive_time))<=-3)";
		List<Long> list = findBySql(sql,null,null);
		return list;
	}
}
