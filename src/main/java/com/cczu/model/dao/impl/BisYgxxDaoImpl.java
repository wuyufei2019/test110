package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import com.cczu.sys.system.entity.User;
import com.cczu.util.common.Parameter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IBisYgxxDao;
import com.cczu.model.entity.BIS_EmployeeEntity;
import com.cczu.util.dao.BaseDao;

@Repository("BisYgxxDao")
public class BisYgxxDaoImpl extends BaseDao<BIS_EmployeeEntity, Long> implements IBisYgxxDao {

	@Override
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
		if(mapData.get("sort")!=null&&mapData.get("sort")!=""&&mapData.get("order")!=null&&mapData.get("order")!="")
			content = content + "order by "+mapData.get("sort")+" "+mapData.get("order"); 
		
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,a.m4 gw,c.m1 bm,u.id uid,u.LOGIN_NAME"
				+" FROM bis_employee a LEFT JOIN t_department c ON a.ID4 = c.ID left join t_user u on u.id=a.id1 "
				+ " where a.S3=0"+content+
				 ")AS a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> swrylist(Map<String, Object> mapData) {
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY b.id) AS RowNumber,b.*,a.m1 shcd,b.m4 gw FROM sggl_casualty a LEFT JOIN bis_employee b"
				+ " ON a.id2 = b.id where a.id1 ="+ mapData.get("id1")+") "
				+ " as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public List<BIS_EmployeeEntity> swrylist2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.*,a.m4 gw,'' shcd FROM bis_employee a"
				+ " where a.s3=0 and a.id3="+ mapData.get("qyid")+content
						+ " and a.id not in(select id2 from sggl_casualty where id1 ="+ mapData.get("id1")+"))"
				+ " as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<BIS_EmployeeEntity> list=findBySql(sql,null,BIS_EmployeeEntity.class);
		return list;
	}
	
	@Override
	public int getSwryTotalCount(Map<String, Object> mapData) {
		String sql="SELECT COUNT(*) FROM sggl_casualty a LEFT JOIN bis_employee b ON a.id2 = b.id where a.id1 ="+ mapData.get("id1");
	    List<Object>list = findBySql(sql);
		return (int) list.get(0);
	}
	
	@Override
	public int getSwryTotalCount2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM bis_employee a where a.S3=0 "+content
				+ " and a.id not in(select id2 from sggl_casualty where id1 ="+ mapData.get("id1")+")";
	    List<Object>list = findBySql(sql);
		return (int) list.get(0);
	}
	
	@Override
	public BIS_EmployeeEntity findInfoByUserID(Long userid) {
		String sql=" SELECT  * FROM  bis_employee WHERE S3=0 AND ID1="+userid;
		List<BIS_EmployeeEntity> list=findBySql(sql, null,BIS_EmployeeEntity.class);
		if(list.size()>0)
			return list.get(0);
		else
			return null;
		
	}
	//查询员工和企业信息
	@Override
	public Map<String,Object> findQyYgInfoByID(Long id) {
		String sql=" SELECT  a.id ,a.m3 ,a.m4,a.m7,a.m8,a.m9,a.m10, b.m1,b.m33 FROM  bis_employee a ,bis_enterprise b  WHERE a.id3=b.id and  a.ID="+id;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
			return  list.get(0);
	}
	
	
	@Override
	public void deleteInfo(Long id) {
		String sql=" UPDATE bis_employee SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
	
	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) SUM   FROM bis_employee a where a.S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("jobnum")!=null&&mapData.get("jobnum")!=""){
			content = content +" AND a.m2 like '%"+mapData.get("jobnum")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.ID3="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("ygname")!=null&&mapData.get("ygname")!=""){
			content = content +" AND a.M1 like '%"+mapData.get("ygname")+"%' "; 
		}
		if(mapData.get("ygname2")!=null&&mapData.get("ygname2")!=""){
			content = content +" AND a.M1 = '"+mapData.get("ygname2")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.M1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND b.ID2 like '"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("ygids")!=null&&mapData.get("ygids")!=""){
			content = content +" AND a.ID IN ("+mapData.get("ygids")+") "; 
		}
		if(mapData.get("gw")!=null&&mapData.get("gw")!=""){
			content = content +" AND a.M4 like '%"+mapData.get("gw")+"%' "; 
		}
		if(mapData.get("ewm")!=null&&mapData.get("ewm")!=""){
			content = content +" AND a.M17 like '%"+mapData.get("ewm")+"%' "; 
		}
		if(mapData.get("cjz")!=null&&mapData.get("cjz")!=""){
			content = content +" AND b.cjz = '"+mapData.get("cjz")+"' "; 
		}
		if(mapData.get("status")!=null&&mapData.get("status")!=""){
			content = content +" AND a.status = "+mapData.get("status")+" "; 
		}
		if (!"".equals(mapData.get("qyid")) && null != (mapData.get("qyid"))) {
			content += " and a.id3 = " + mapData.get("qyid");
		}
		if (!"".equals(mapData.get("bmid")) && null != (mapData.get("bmid"))) {
			content += " and a.id4 = " + mapData.get("bmid");
		}
		if (!"".equals(mapData.get("gwname")) && null != (mapData.get("gwname"))) {
			content += " and a.m4 = '" + mapData.get("gwname").toString()+"' ";
		}
		if (!"".equals(mapData.get("keyword")) && null != (mapData.get("keyword"))) {
			content += " and a.m1 like '%" + mapData.get("keyword").toString()+"%' ";
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}

	@Override
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT b.m1 qyname,a.*,c.M1 bm FROM bis_employee a "
				+ " LEFT JOIN t_department c ON a.ID4 = c.ID  "
				+ " LEFT JOIN bis_enterprise b on b.id=a.id3 "
				+ " WHERE a.S3=0 and b.S3=0 "+ content+" order by b.id ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}


	@Override
	public List<Map<String, Object>> dataGridAJ(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY b.m1,a.ID desc ");
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber, a.*,b.m1 qyname,c.M1 bm FROM bis_employee a left join bis_enterprise b on a.id3=b.id LEFT JOIN t_department c ON a.ID4 = c.ID where a.S3=0 and b.S3=0 "+content+") "
				+ "AS a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public int getTotalCountAJ(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) SUM FROM (SELECT a.*,b.m1 qyname FROM bis_employee a left join bis_enterprise b on a.id3=b.id  where a.S3=0 and b.S3=0 "+content+ ") s";
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

    //查询设备名称list
	public List<Map<String, Object>> findYgxxList(String idcard,String qyid) {
		String content="";
		if(StringUtils.isNotBlank(idcard)){
		    content=" and m8 ='"+idcard+"'";
		}
		if(!qyid.equals("0")){
			content =content + " and ID3 ='"+qyid+"'";
		}
		String sql="SELECT id as id,m1 as text,m3 sex,m8 idcard,m4 gw FROM bis_employee WHERE S3=0"+ content; 
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	public List<Map<String, Object>> findByQyID(Long qyid) {
		String sql=" SELECT a.M1 m1,a.M2 m2,a.M3 m3,a.M4 m4,a.M5 m5,a.M6 m6,a.M7 m7,a.M8 m8,a.M9 m9,a.M10 m10,c.M1 bm FROM bis_employee a "
				+ " LEFT JOIN t_department c ON a.ID4 = c.ID "
				+ " WHERE a.S3=0 and a.id3="+qyid;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public Map<String,Object> findAllByID(Long id) {
		String sql=" SELECT a.*,b.m1 bmmc,a.m4 gw FROM bis_employee a LEFT JOIN t_department b ON a.ID4 = b.ID where a.s3 = 0 and a.id ="+id;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public BIS_EmployeeEntity findByID(Long id) {
		String sql=" SELECT * from bis_employee where id="+id;
		List<BIS_EmployeeEntity> list=findBySql(sql, null,BIS_EmployeeEntity.class);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public void deleteInfoByM2(String m2) {
		String sql=" UPDATE bis_employee SET S3=1 WHERE m2="+m2;
		updateBySql(sql);
	}

	@Override
	public void connectUserAccount(long id, long userid) {
		String sql=" UPDATE bis_employee SET ID1=:p1 WHERE id=:p2";
		updateBySql(sql,new Parameter(userid,id));
	}

	@Override
	public void removeBind(long id) {
		String sql=" UPDATE bis_employee SET ID1=null WHERE id=:p1";
		updateBySql(sql,new Parameter(id));
	}

    @Override
    public int getBindCount(long id) {
        String sql="SELECT COUNT(*)  FROM bis_employee where s3=0 and ID1=:p1";
        List<Object> list=findBySql(sql,new Parameter(id));
        return (int) list.get(0);
    }

	@Override
	public void deleteDimission(Long qyid) {
		// TODO Auto-generated method stub
		String sql=" update bis_employee set status=2 where id not in ( select b.id from bis_employee_second a LEFT JOIN bis_employee b on a.m2=b.m2 where b.id is not null) and id3="+qyid;
		updateBySql(sql);
	}

	@Override
	public void updateInfor(Long qyid) {
		// TODO Auto-generated method stub
		String sql=" update bis_employee set bis_employee.id4=b.id4,bis_employee.m4=b.m4,bis_employee.m7=b.m7,bis_employee.m9=b.m9 from bis_employee_second b where bis_employee.m2=b.m2 and bis_employee.s3=0 and bis_employee.status!=2 and bis_employee.id3="+qyid;
		updateBySql(sql);
	}

	@Override
	public void addNewEmployee() {
		// TODO Auto-generated method stub
		String sql="  insert into bis_employee (s1, s2, s3, id1, id2, id3, id4, m1, m10, m11, m12, m13, m14, m15, m16, a.m17, m2, m3, m4, m5, m6, m7, m8, m9, status) "
				+ " select a.s1, a.s2, a.s3, a.id1, a.id2, a.id3, a.id4, a.m1, a.m10, a.m11, a.m12, a.m13, a.m14, a.m15, a.m16, a.m17, a.m2, a.m3, a.m4, a.m5, a.m6, a.m7, a.m8, a.m9, a.status from bis_employee_second a LEFT JOIN bis_employee b on a.m2=b.m2 where b.id is null ";
		updateBySql(sql);
	}
	
	@Override
	public void updID1() {
		// TODO Auto-generated method stub
		String sql=" update bis_employee_second set bis_employee_second.id1=a.id from t_user a where bis_employee_second.s3=0 and a.LOGIN_NAME = bis_employee_second.m2 ";
		updateBySql(sql);
	}

	@Override
	public void deleteDimissionUser(Long qyid) {
		// TODO Auto-generated method stub
		String sql=" update t_user set DEL_FLAG=1 where id in( select b.id from bis_employee a LEFT JOIN t_user b on a.m2=b.login_name where a.s3=1 and b.id is not null and (b.userroleflg is null or b.userroleflg!='1'))  and id2="+qyid;
		updateBySql(sql);		
	}

	@Override
	public void addNewUser() {
		// TODO Auto-generated method stub
		String sql=" INSERT INTO t_user (LOGIN_NAME,name,password,salt,CREATE_DATE,usertype,id2,gender) "
				+ " SELECT m2,m1,'70278ed5fe584dddc703deca5afa7b1a0fc0b56a','29d081fb97f8bf66',GETDATE() as rq,'1',id3,(case m3 when '男' then '1' else '0' end) from (select a.* from bis_employee_second a LEFT JOIN bis_employee b on a.m2=b.m2 where b.id is null ) s ";
		updateBySql(sql);		
	}
	
	@Override
	public List<User> findNoRoleUser(Long qyid) {
		// TODO Auto-generated method stub
		String sql=" select a.* from t_user a LEFT JOIN t_user_role b on a.ID=b.USER_ID where b.ROLE_ID is NULL and a.id2="+qyid;
		List<User> list=findBySql(sql, null,User.class);
		return list;
	}

	@Override
	/**
	 * 根据工号删除离职人员
	 */
	public void deleteUserByM2(String loginname) {
		// TODO Auto-generated method stub
		String sql="update t_user set DEL_FLAG=1 where login_Name='"+loginname+"'";
		updateBySql(sql);				
	}
	
	/*public List<Map<String, Object>> findInfoByQyID(Map<String, Object> map) {
		String content = "";
		if (!"".equals(map.get("qyid")) && null != (map.get("qyid"))) {
			content += " and a.id3 = " + map.get("qyid");
		}
		if (!"".equals(map.get("bmid")) && null != (map.get("bmid"))) {
			content += " and a.id4 = " + map.get("bmid");
		}
		if (!"".equals(map.get("gwname")) && null != (map.get("gwname"))) {
			content += " and a.m4 = '" + map.get("gwname").toString()+"' ";
		}
		String sql=" SELECT a.id,a.M1 text FROM bis_employee a "
				+ " LEFT JOIN t_department c ON a.ID4 = c.ID "
				+ " WHERE a.S3=0 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}*/
	
	public List<Map<String, Object>> findInfoByQyID(Map<String, Object> map) {
		String content = content(map);
		String sql=" SELECT TOP "+map.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.ID desc ) AS RowNumber, a.id, a.m1 name, a.m3 sex,c.m1 deptname FROM bis_employee a "
				+ " LEFT JOIN t_department c ON a.ID4 = c.ID "
				+ " WHERE a.S3=0 "+content+")"
				+ " AS a WHERE  RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	public int getTotalCountByQyid(Map<String, Object> map) {
		String content = content(map);
		String sql="SELECT COUNT(*) SUM FROM bis_employee a LEFT JOIN t_department c ON a.ID4 = c.ID  left join bis_jobpostentity d on d.m1=a.m4 where a.S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public Map<String, Object> findBaseInfor(Long id) {
		// TODO Auto-generated method stub
		String sql=" select a.m1 ygname, a.m3 sex, a.m9 tel, a.m13 mz, a.m10 birth, a.m6 zy, a.m5 xl, a.m7 zc, b.m1 depname, (b.m1+'-'+a.m4) zycs "
				+ " from bis_employee a LEFT JOIN t_department b on a.id4=b.id "
				+ " where a.id="+id;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return  list.get(0);
	}

	/**
	 * 同一公司下验证是否有相同二维码
	 * @param id
	 * @param ewm
	 * @return
	 */
	public boolean checkSameEwm(long id, String ewm,long qyid) {
		String sql="SELECT * FROM bis_employee WHERE S3=0 AND id != "+id+" and m17 = '"+ewm+"' and id3 = "+qyid;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		if(list.size()>0){
			return true;
		}
		return false;
	}
}
