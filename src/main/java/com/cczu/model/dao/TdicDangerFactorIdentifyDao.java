package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.Tdic_DangerFactorIdentifyEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 字典---危险因素辨识DAO
 * @author jason
 * @date 2017年8月8日
 */
@Repository("TdicDangerFactorIdentifyDao")
public class TdicDangerFactorIdentifyDao extends BaseDao<Tdic_DangerFactorIdentifyEntity,Long> {
	
	/**
	 * 根据m1行业    获取m2行业类别信息
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getM2Data(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT distinct m2 text FROM tdic_dangerfactoridentify  WHERE m2 is not null "+content ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}
	
	/**
	 * 根据m1行业,m2行业类别   获取m3工段信息
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getM3Data(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT distinct m3 text FROM tdic_dangerfactoridentify  WHERE m3 is not null "+content ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}
	
	/**
	 * 根据m1行业,m2行业类别 ,m3工段信息    获取m4部位信息
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getM4Data(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT distinct m4 text FROM tdic_dangerfactoridentify  WHERE m4 is not null "+content ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}
	
	
	/**
	 * 根据m1行业    获取m6易发生的事故类型
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getM6Data(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT distinct m6 text FROM tdic_dangerfactoridentify  WHERE m6 is not null "+content ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}
	
	/**
	 * 根据m1行业、m2行业类别 、m3工段信息、m4部位、m6易发生的事故类型 获取对象
	 * @param mapData
	 * @return
	 */
	public Tdic_DangerFactorIdentifyEntity getEntityData(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT * FROM tdic_dangerfactoridentify  WHERE 0=0 "+content ;
		List<Tdic_DangerFactorIdentifyEntity> list=findBySql(sql, null,Tdic_DangerFactorIdentifyEntity.class);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND m1 like '"+mapData.get("m1")+"' "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND m2 like '"+mapData.get("m2")+"' "; 
		}
		if(mapData.get("m3")!=null&&mapData.get("m3")!=""){
			content = content +" AND m3 like '"+mapData.get("m3")+"' "; 
		}
		if(mapData.get("m4")!=null&&mapData.get("m4")!=""){
			content = content +" AND m4 like '"+mapData.get("m4")+"' "; 
		}
		if(mapData.get("m6")!=null&&mapData.get("m6")!=""){
			content = content +" AND m6 like '"+mapData.get("m6")+"' "; 
		}
		return content;	
	}
}
