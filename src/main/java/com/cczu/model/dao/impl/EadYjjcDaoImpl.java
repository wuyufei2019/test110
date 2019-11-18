package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.EAD_AccidentEntity;
import com.cczu.model.entity.ERM_EmergencyContactsEntity;
import com.cczu.model.entity.ERM_EmergencyDispTechnologyEntity;
import com.cczu.model.entity.ERM_EmergencyHospitalEntity;
import com.cczu.model.entity.ERM_EmergencyResTeamEntity;
import com.cczu.model.entity.ERM_EmergencyResInstrumentEntity;
import com.cczu.model.entity.ERM_EmergencyResPlaceEntity;
import com.cczu.model.entity.ERM_EmergencyMateEntity;
import com.cczu.model.entity.ERM_EmergencyResExpertEntity;
import com.cczu.model.entity.TMESK_MsdsEntity;
import com.cczu.model.dao.IEadYjjcDao;
import com.cczu.util.dao.BaseDao;

@Repository("IEadYjjcDao")
public class EadYjjcDaoImpl extends BaseDao<EAD_AccidentEntity, Long> implements IEadYjjcDao {

	@Override
	public long saveAccidentRid(EAD_AccidentEntity ead) {
		save(ead);
		return ead.getID();
	}

	@Override
	public EAD_AccidentEntity findById(Long id) {
		return find(id);
	}

	/*****************应急队伍***************************/
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String contentResTeam(Map<String, Object> map) {
		String content=" ";
		if(map.get("yjdwname")!=null&&map.get("yjdwname")!=""){
			content = content + "AND er.m1 like '%"+map.get("yjdwname")+"%' "; 
		}
		if(map.get("yjdwdistance")!=null&&map.get("yjdwdistance")!=""){
			if(map.get("yjdwdistance").equals("4")){//3-5KM
				content = content + "AND ee.distance between 3 and 5 "; 
			}else if(map.get("yjdwdistance").equals("6")){//5-10KM
				content = content + "AND ee.distance between 5 and 10 "; 
			}else{
				content = content + "AND ee.distance <= "+map.get("yjdwdistance")+" "; 
			}
		}
		if(map.get("consequenceid")!=null&&map.get("consequenceid")!=""){
			content = content + "AND ee.accident_id="+map.get("consequenceid")+" "; 
		}
		return content;
	}
	
	@Override
	public Map<String, Object> findMapResTeam(Map<String, Object> map) {
		String content=contentResTeam(map);
		String sql="SELECT  * FROM ead_accident_erm_resteam ee left join erm_emergencyresteam er on er.id=ee.resteam_id where 1=1 "+content;
		List<ERM_EmergencyResTeamEntity> list=findBySql(sql, null,ERM_EmergencyResTeamEntity.class);
		
		Map<String, Object> mapnew = new HashMap<String, Object>();
		mapnew.put("data", list);
		return mapnew;
	}
	
	@Override
	public Map<String, Object> dataGridResTeam(Map<String, Object> map) {
		String content=contentResTeam(map);
		String sql="SELECT TOP "+map.get("pageSize")+" * FROM ( select ROW_NUMBER() OVER ( ORDER BY ee.id) AS RowNumber,er.* "
				+ " from ead_accident_erm_resteam ee left join erm_emergencyresteam er on er.id=ee.resteam_id where 1=1 "+content+" ) "
				+ " AS a WHERE RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) ";
		List<ERM_EmergencyResTeamEntity> list=findBySql(sql, null,ERM_EmergencyResTeamEntity.class);
		
		Map<String, Object> mapnew = new HashMap<String, Object>();
		mapnew.put("list", list);
		return mapnew;
	}

	@Override
	public int getTotaldataGridResTeam(Map<String, Object> map) {
		String content=contentResTeam(map);
		String sql=" SELECT COUNT(*) SUM  FROM ead_accident_erm_resteam ee left join erm_emergencyresteam er on er.id=ee.resteam_id where 1=1 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}


	/*****************应急装备***************************/
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String contentResInstrument(Map<String, Object> map) {
		String content=" ";
		if(map.get("yjzbname")!=null&&map.get("yjzbname")!=""){
			content = content + "AND er.m2 like '%"+map.get("yjzbname")+"%' "; 
		}
		if(map.get("yjzbdistance")!=null&&map.get("yjzbdistance")!=""){
			if(map.get("yjzbdistance").equals("4")){//3-5KM
				content = content + "AND ee.distance between 3 and 5 "; 
			}else if(map.get("yjzbdistance").equals("6")){//5-10KM
				content = content + "AND ee.distance between 5 and 10 "; 
			}else{
				content = content + "AND ee.distance <= "+map.get("yjzbdistance")+" "; 
			}
		}
		if(map.get("consequenceid")!=null&&map.get("consequenceid")!=""){
			content = content + "AND ee.accident_id="+map.get("consequenceid")+" "; 
		}
		return content;
	}
	
	@Override
	public Map<String, Object> findMapResInstrument(Map<String, Object> map) {
		String content=contentResInstrument(map);
		String sql="SELECT  er.* ,bis.m1 qyname FROM ead_accident_erm_resinstrument ee left join erm_emergencyresInstrument er on er.id=ee.resInstrument_id  left join bis_enterprise bis on er.qyid=bis.id where 1=1 "+content;
		List<ERM_EmergencyResInstrumentEntity> list=findBySql(sql, null,ERM_EmergencyResInstrumentEntity.class);
		
		Map<String, Object> mapnew = new HashMap<String, Object>();
		mapnew.put("data", list);
		return mapnew;
	}
	
	@Override
	public Map<String, Object> dataGridResInstrument(Map<String, Object> map) {
		String content=contentResInstrument(map);
		String sql="SELECT TOP "+map.get("pageSize")+" * FROM ( select ROW_NUMBER() OVER ( ORDER BY er.qyid) AS RowNumber,er.* ,bis.m1 qyname "
				+ " from ead_accident_erm_resinstrument ee left join erm_emergencyresInstrument er on er.id=ee.resInstrument_id left join bis_enterprise bis on er.qyid=bis.id where 1=1 "+content+" ) "
				+ " AS a WHERE RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		Map<String, Object> mapnew = new HashMap<String, Object>();
		mapnew.put("list", list);
		return mapnew;
	}

	@Override
	public int getTotaldataGridResInstrument(Map<String, Object> map) {
		String content=contentResInstrument(map);
		String sql=" SELECT COUNT(*) SUM  FROM ead_accident_erm_resinstrument ee left join erm_emergencyresInstrument er on er.id=ee.resInstrument_id where 1=1 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}


	/*****************应急物资***************************/
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String contentMate(Map<String, Object> map) {
		String content=" ";
		if(map.get("yjwzname")!=null&&map.get("yjwzname")!=""){
			content = content + "AND er.m2 like '%"+map.get("yjwzname")+"%' "; 
		}
		if(map.get("yjwzdistance")!=null&&map.get("yjwzdistance")!=""){
			if(map.get("yjwzdistance").equals("4")){//3-5KM
				content = content + "AND ee.distance between 3 and 5 "; 
			}else if(map.get("yjwzdistance").equals("6")){//5-10KM
				content = content + "AND ee.distance between 5 and 10 "; 
			}else{
				content = content + "AND ee.distance <= "+map.get("yjwzdistance")+" "; 
			}
		}
		if(map.get("consequenceid")!=null&&map.get("consequenceid")!=""){
			content = content + "AND ee.accident_id="+map.get("consequenceid")+" "; 
		}
		return content;
	}
	
	@Override
	public Map<String, Object> findMapMate(Map<String, Object> map) {
		String content=contentMate(map);
		String sql="SELECT  * FROM ead_accident_erm_mate ee left join erm_emergencymate er on er.id=ee.mate_id where 1=1 "+content;
		List<ERM_EmergencyMateEntity> list=findBySql(sql, null,ERM_EmergencyMateEntity.class);
		
		Map<String, Object> mapnew = new HashMap<String, Object>();
		mapnew.put("data", list);
		return mapnew;
	}
	
	@Override
	public Map<String, Object> dataGridMate(Map<String, Object> map) {
		String content=contentMate(map);
		String sql="SELECT TOP "+map.get("pageSize")+" * FROM ( select ROW_NUMBER() OVER ( ORDER BY er.qyid) AS RowNumber,er.* ,bis.m1 qyname"
				+ " from ead_accident_erm_mate ee left join erm_emergencymate er on er.id=ee.mate_id left join bis_enterprise bis on er.qyid=bis.id where 1=1 "+content+" ) "
				+ " AS a WHERE RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		Map<String, Object> mapnew = new HashMap<String, Object>();
		mapnew.put("list", list);
		return mapnew;
	}

	@Override
	public int getTotaldataGridMate(Map<String, Object> map) {
		String content=contentMate(map);
		String sql=" SELECT COUNT(*) SUM  FROM ead_accident_erm_mate ee left join erm_emergencymate er on er.id=ee.mate_id where 1=1 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	
	/*****************避难场所***************************/
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String contentResPlace(Map<String, Object> map) {
		String content=" ";
		if(map.get("bncsname")!=null&&map.get("bncsname")!=""){
			content = content + "AND er.m1 like '%"+map.get("bncsname")+"%' "; 
		}
		if(map.get("bncsdistance")!=null&&map.get("bncsdistance")!=""){
			if(map.get("bncsdistance").equals("4")){//3-5KM
				content = content + "AND ee.distance between 3 and 5 "; 
			}else if(map.get("bncsdistance").equals("6")){//5-10KM
				content = content + "AND ee.distance between 5 and 10 "; 
			}else{
				content = content + "AND ee.distance <= "+map.get("bncsdistance")+" "; 
			}
		}
		if(map.get("consequenceid")!=null&&map.get("consequenceid")!=""){
			content = content + "AND ee.accident_id="+map.get("consequenceid")+" "; 
		}
		return content;
	}
	
	@Override
	public Map<String, Object> findMapResPlace(Map<String, Object> map) {
		String content=contentResPlace(map);
		String sql="SELECT  * FROM ead_accident_erm_resplace ee left join erm_emergencyresplace er on er.id=ee.resplace_id where 1=1 "+content;
		List<ERM_EmergencyResPlaceEntity> list=findBySql(sql, null,ERM_EmergencyResPlaceEntity.class);
		
		Map<String, Object> mapnew = new HashMap<String, Object>();
		mapnew.put("data", list);
		return mapnew;
	}
	
	@Override
	public Map<String, Object> dataGridResPlace(Map<String, Object> map) {
		String content=contentResPlace(map);
		String sql="SELECT TOP "+map.get("pageSize")+" * FROM ( select ROW_NUMBER() OVER ( ORDER BY ee.id) AS RowNumber,er.* "
				+ " from ead_accident_erm_resplace ee left join erm_emergencyresplace er on er.id=ee.resplace_id where 1=1 "+content+" ) "
				+ " AS a WHERE RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) ";
		List<ERM_EmergencyResPlaceEntity> list=findBySql(sql, null,ERM_EmergencyResPlaceEntity.class);
		
		Map<String, Object> mapnew = new HashMap<String, Object>();
		mapnew.put("list", list);
		return mapnew;
	}

	@Override
	public int getTotaldataGridResPlace(Map<String, Object> map) {
		String content=contentResPlace(map);
		String sql=" SELECT COUNT(*) SUM  FROM ead_accident_erm_resplace ee left join erm_emergencyresplace er on er.id=ee.resplace_id where 1=1 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	

	/*****************应急专家***************************/
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String contentResExpert(Map<String, Object> map) {
		String content=" ";
		if(map.get("yjzjname")!=null&&map.get("yjzjname")!=""){
			content = content + "AND er.m1 like '%"+map.get("yjzjname")+"%' "; 
		}
		if(map.get("consequenceid")!=null&&map.get("consequenceid")!=""){
			content = content + "AND ee.accident_id="+map.get("consequenceid")+" "; 
		}
		return content;
	}
	
	@Override
	public Map<String, Object> findMapResExpert(Map<String, Object> map) {
		String content=contentResExpert(map);
		String sql="SELECT  * FROM ead_accident_erm_resexpert ee left join erm_emergencyresexpert er on er.id=ee.resexpert_id where 1=1 "+content;
		List<ERM_EmergencyResExpertEntity> list=findBySql(sql, null,ERM_EmergencyResExpertEntity.class);
		
		Map<String, Object> mapnew = new HashMap<String, Object>();
		mapnew.put("data", list);
		return mapnew;
	}
	
	@Override
	public Map<String, Object> dataGridResExpert(Map<String, Object> map) {
		String content=contentResExpert(map);
		String sql="SELECT TOP "+map.get("pageSize")+" * FROM ( select ROW_NUMBER() OVER ( ORDER BY ee.id) AS RowNumber,er.* "
				+ " from ead_accident_erm_resexpert ee left join erm_emergencyresexpert er on er.id=ee.resexpert_id where 1=1 "+content+" ) "
				+ " AS a WHERE RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) ";
		List<ERM_EmergencyResExpertEntity> list=findBySql(sql, null,ERM_EmergencyResExpertEntity.class);
		
		Map<String, Object> mapnew = new HashMap<String, Object>();
		mapnew.put("list", list);
		return mapnew;
	}

	@Override
	public int getTotaldataGridResExpert(Map<String, Object> map) {
		String content=contentResExpert(map);
		String sql=" SELECT COUNT(*) SUM  FROM ead_accident_erm_resexpert ee left join erm_emergencyresexpert er on er.id=ee.resexpert_id where 1=1 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}


	/*****************应急医院***************************/
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String contentHospital(Map<String, Object> map) {
		String content=" ";
		if(map.get("yjyyname")!=null&&map.get("yjyyname")!=""){
			content = content + "AND er.m1 like '%"+map.get("yjyyname")+"%' "; 
		}
		if(map.get("yjyydistance")!=null&&map.get("yjyydistance")!=""){
			if(map.get("yjyydistance").equals("4")){//3-5KM
				content = content + "AND ee.distance between 3 and 5 "; 
			}else if(map.get("yjyydistance").equals("6")){//5-10KM
				content = content + "AND ee.distance between 5 and 10 "; 
			}else{
				content = content + "AND ee.distance <= "+map.get("yjyydistance")+" "; 
			}
		}
		if(map.get("consequenceid")!=null&&map.get("consequenceid")!=""){
			content = content + "AND ee.accident_id="+map.get("consequenceid")+" "; 
		}
		return content;
	}
	
	@Override
	public Map<String, Object> findMapHospital(Map<String, Object> map) {
		String content=contentHospital(map);
		String sql="SELECT  * FROM ead_accident_erm_hospital ee left join erm_emergencyhospital er on er.id=ee.hospital_id where 1=1 "+content;
		List<ERM_EmergencyHospitalEntity> list=findBySql(sql, null,ERM_EmergencyHospitalEntity.class);
		
		Map<String, Object> mapnew = new HashMap<String, Object>();
		mapnew.put("data", list);
		return mapnew;
	}
	
	@Override
	public Map<String, Object> dataGridHospital(Map<String, Object> map) {
		String content=contentHospital(map);
		String sql="SELECT TOP "+map.get("pageSize")+" * FROM ( select ROW_NUMBER() OVER ( ORDER BY ee.id) AS RowNumber,er.* "
				+ " from ead_accident_erm_hospital ee left join erm_emergencyhospital er on er.id=ee.hospital_id where 1=1 "+content+" ) "
				+ " AS a WHERE RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) ";
		List<ERM_EmergencyHospitalEntity> list=findBySql(sql, null,ERM_EmergencyHospitalEntity.class);
		
		Map<String, Object> mapnew = new HashMap<String, Object>();
		mapnew.put("list", list);
		return mapnew;
	}

	@Override
	public int getTotaldataGridHospital(Map<String, Object> map) {
		String content=contentHospital(map);
		String sql=" SELECT COUNT(*) SUM  FROM ead_accident_erm_hospital ee left join erm_emergencyhospital er on er.id=ee.hospital_id where 1=1 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	
	/*****************应急处置技术***************************/
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String contentDispTechnology(Map<String, Object> map) {
		String content=" ";
		if(map.get("yjczjsname")!=null&&map.get("yjczjsname")!=""){
			content = content + "AND er.m1 like '%"+map.get("yjczjsname")+"%' "; 
		}
		if(map.get("consequenceid")!=null&&map.get("consequenceid")!=""){
			content = content + "AND ee.accident_id="+map.get("consequenceid")+" "; 
		}
		return content;
	}
		
	@Override
	public Map<String, Object> dataGridDispTechnology(Map<String, Object> map) {
		String content=contentDispTechnology(map);
		String sql="SELECT TOP "+map.get("pageSize")+" * FROM ( select ROW_NUMBER() OVER ( ORDER BY ee.id) AS RowNumber,er.* "
				+ " from ead_accident_erm_disptechnology ee left join erm_emergencydisptechnology er on er.id=ee.disptechnology_id where 1=1 "+content+" ) "
				+ " AS a WHERE RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) ";
		List<ERM_EmergencyDispTechnologyEntity> list=findBySql(sql, null,ERM_EmergencyDispTechnologyEntity.class);
		
		Map<String, Object> mapnew = new HashMap<String, Object>();
		mapnew.put("list", list);
		return mapnew;
	}

	@Override
	public int getTotaldataGridDispTechnology(Map<String, Object> map) {
		String content=contentDispTechnology(map);
		String sql=" SELECT COUNT(*) SUM  FROM ead_accident_erm_disptechnology ee left join erm_emergencydisptechnology er on er.id=ee.disptechnology_id where 1=1 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	@Override
	public Map<String, Object> findAllDispTechnology(Map<String, Object> map) {
		String content=contentDispTechnology(map);
		String sql="SELECT  * FROM ead_accident_erm_disptechnology ee left join erm_emergencydisptechnology er on er.id=ee.disptechnology_id where 1=1 "+content;
		List<ERM_EmergencyDispTechnologyEntity> list=findBySql(sql, null,ERM_EmergencyDispTechnologyEntity.class);
		
		Map<String, Object> mapnew = new HashMap<String, Object>();
		mapnew.put("yjczjslist", list);
		return mapnew;
	}
	
	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String contentMsds(Map<String, Object> map) {
		String content=" ";
		if(map.get("yjczjsname")!=null&&map.get("yjczjsname")!=""){
			content = content + "AND tms.m1 like '%"+map.get("yjczjsname")+"%' "; 
		}
		if(map.get("consequenceid")!=null&&map.get("consequenceid")!=""){
			content = content + "AND ee.accident_id="+map.get("consequenceid")+" "; 
		}
		return content;
	}
	
	@Override
	public Map<String, Object> dataGridMsds(Map<String, Object> map) {
		String content=contentMsds(map);
		String sql="SELECT TOP "+map.get("pageSize")+" * FROM ( select ROW_NUMBER() OVER ( ORDER BY ee.id) AS RowNumber,tms.* "
				+ " from ead_accident_tmesk_msds ee left join tmesk_msds tms on tms.id=ee.msds_id where 1=1 "+content+" ) "
				+ " AS a WHERE RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) ";
		List<TMESK_MsdsEntity> list=findBySql(sql, null,TMESK_MsdsEntity.class);
		
		Map<String, Object> mapnew = new HashMap<String, Object>();
		mapnew.put("list", list);
		return mapnew;
	}

	@Override
	public int getTotaldataGridMsds(Map<String, Object> map) {
		String content=contentMsds(map);
		String sql=" SELECT COUNT(*) SUM  FROM ead_accident_tmesk_msds ee left join tmesk_msds tms on tms.id=ee.msds_id where 1=1 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public Map<String, Object> findAllMsds(Map<String, Object> map) {
//		String m2="";
		String content=contentMsds(map);
		String sql="SELECT  tms.* FROM ead_accident_tmesk_msds ee left join tmesk_msds tms on tms.id=ee.msds_id where 1=1 "+content;
//		List<Object> Objectlist=findBySql(sql);
//		List<Map<String, Object>> maplist = new ArrayList<Map<String,Object>>();
//		for(int i = 0; i < Objectlist.size(); i++) {
//			m2="";
//			Object[] obj = (Object[])Objectlist.get(i);
//			Map<String, Object> mapd = new HashMap<String, Object>();
//			mapd.put("id", obj[0].toString());
//			if(obj[1]!=null){
//				mapd.put("m1", obj[1].toString());
//			}else{
//				mapd.put("m1", "");
//			}
//			if(obj[2]!=null){
//				if(StringUtils.isNotEmpty(obj[2].toString())) m2=m2+"健康危害:"+obj[2].toString()+"&nbsp;&nbsp;&nbsp;&nbsp;";
//			}
//			if(obj[3]!=null){
//				if(StringUtils.isNotEmpty(obj[3].toString())) m2=m2+"环境危害:"+obj[3].toString()+"&nbsp;&nbsp;&nbsp;&nbsp;";
//			}
//			if(obj[4]!=null){
//				if(StringUtils.isNotEmpty(obj[4].toString())) m2=m2+"燃爆危害:"+obj[4].toString()+"&nbsp;&nbsp;&nbsp;&nbsp;";
//			}
//			
//			mapd.put("m2", m2);
//			
//			if(obj[1]!=null){
//				mapd.put("m3", obj[5].toString());
//			}else{
//				mapd.put("m3", "");
//			}
//			
//			maplist.add(mapd);
//		}
		List<TMESK_MsdsEntity> list=findBySql(sql, null,TMESK_MsdsEntity.class);
		
		Map<String, Object> mapnew = new HashMap<String, Object>();
		mapnew.put("data", list);
		return mapnew;
	}
	/*****************应急通讯录***************************/
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String contentContacts(Map<String, Object> map) {
		String content=" ";
		if(map.get("yjtxlname")!=null&&map.get("yjtxlname")!=""){
			content = content + "AND er.m1 like '%"+map.get("yjtxlname")+"%' "; 
		}
		if(map.get("consequenceid")!=null&&map.get("consequenceid")!=""){
			content = content + "AND ee.accident_id="+map.get("consequenceid")+" "; 
		}
		return content;
	}
	
	@Override
	public Map<String, Object> dataGridContacts(Map<String, Object> map) {
		String content=contentContacts(map);
		String sql="SELECT TOP "+map.get("pageSize")+" * FROM ( select ROW_NUMBER() OVER ( ORDER BY ee.id) AS RowNumber,er.* "
				+ " from ead_accident_erm_contacts ee left join erm_emergencycontact er on er.id=ee.contact_id where 1=1 "+content+" ) "
				+ " AS a WHERE RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) ";
		List<ERM_EmergencyContactsEntity> list=findBySql(sql, null,ERM_EmergencyContactsEntity.class);
		
		Map<String, Object> mapnew = new HashMap<String, Object>();
		mapnew.put("list", list);
		return mapnew;
	}

	@Override
	public int getTotaldataGridContacts(Map<String, Object> map) {
		String content=contentContacts(map);
		String sql=" SELECT COUNT(*) SUM  FROM ead_accident_erm_contacts ee left join erm_emergencycontact er on er.id=ee.contact_id where 1=1 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public Map<String, Object> findAllContacts(Map<String, Object> map) {
		String content=contentContacts(map);
		String sql="SELECT  * FROM ead_accident_erm_contacts ee left join erm_emergencycontact er on er.id=ee.contact_id where 1=1 "+content;
		List<ERM_EmergencyContactsEntity> list=findBySql(sql, null,ERM_EmergencyContactsEntity.class);
		
		Map<String, Object> mapnew = new HashMap<String, Object>();
		mapnew.put("yjtxllist", list);
		return mapnew;
	}
	
}
