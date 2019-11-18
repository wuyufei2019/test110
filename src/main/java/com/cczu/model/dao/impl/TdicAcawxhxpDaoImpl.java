package com.cczu.model.dao.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.ITdicAcawxhxpDao;
import com.cczu.model.entity.Tdic_AcaWxhxpmonitoringEntity;
import com.cczu.util.dao.BaseDao;

@Repository("ITdicAcawxhxpDao")
public class TdicAcawxhxpDaoImpl extends BaseDao<Tdic_AcaWxhxpmonitoringEntity,Long> implements ITdicAcawxhxpDao {

	@Override
	public List<Tdic_AcaWxhxpmonitoringEntity> getAllList() {
		return findAll();
	}

	@Override
	public Tdic_AcaWxhxpmonitoringEntity findById(Long tid) {
		String sql="select * from tdic_acawxhxpmonitoring where id="+tid;
		List<Tdic_AcaWxhxpmonitoringEntity> list=findBySql(sql, null,Tdic_AcaWxhxpmonitoringEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Tdic_AcaWxhxpmonitoringEntity find(Long id) {
		return find(id);
	}

	@Override
	public List<Map<String,Object>> getAllxx(String sum) {
		String sql="select id,m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13,m14 from tdic_acawxhxpmonitoring";
		List<Object> list=findBySql(sql);
		List<Map<String,Object>> newlist=new ArrayList<Map<String,Object>>();
		
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[])list.get(i);
			Map<String,Object> map=new HashMap<String,Object>();
			if(Integer.parseInt(sum)==1){//池火灾
				map.put("id", obj[0]);
				map.put("Mingchen", obj[1]);
				map.put("BiReRong", obj[4]);
				map.put("FeiDian", obj[3]);
				map.put("MiDu", obj[10]);
				map.put("RanShaoRei", obj[13]);
				map.put("ZhenFaRei", obj[14]);
			}else if(Integer.parseInt(sum)==2){//喷射火
				map.put("id", obj[0]);
				map.put("Mingchen", obj[1]);
				map.put("ReRongBi", obj[6]);
				map.put("FenZiLiang", obj[2]);
				map.put("RanShaoRei", obj[13]);
			}else if(Integer.parseInt(sum)==3){//火球
				map.put("id", obj[0]);
				map.put("Mingchen", obj[1]);
				map.put("RanShaoRei", obj[13]);
			}else if(Integer.parseInt(sum)==5){//化学爆炸
				map.put("id", obj[0]);
				map.put("Mingchen", obj[1]);
				map.put("RanShaoRei", obj[13]);
			}else if(Integer.parseInt(sum)==6){//压缩气体物理爆炸
				map.put("id", obj[0]);
				map.put("Mingchen", obj[1]);
				map.put("ReRongBi", obj[6]);
			}else if(Integer.parseInt(sum)==7){//持续泄露
				map.put("id", obj[0]);
				map.put("Mingchen", obj[1]);
				
				map.put("Bianjie1", obj[7]);
				map.put("Bianjie2", obj[8]);
				map.put("Bianjie3", obj[9]);
				map.put("FenZiLiang", obj[2]);
				map.put("ReRongBi", obj[6]);
				
			}else if(Integer.parseInt(sum)==8){//瞬时泄露
				map.put("id", obj[0]);
				map.put("Mingchen", obj[1]);
				map.put("Bianjie1", obj[7]);
			}else{
				
			}
			
			newlist.add(map);
		}
		return newlist;
	}

	@Override
	public List<Tdic_AcaWxhxpmonitoringEntity> findByName(String name) {
		// TODO Auto-generated method stub
		String sql="select * from tdic_acawxhxpmonitoring where m1 like '%"+name+"%'";
		List<Tdic_AcaWxhxpmonitoringEntity> list=findBySql(sql, null,Tdic_AcaWxhxpmonitoringEntity.class);
		return list;
	}
	
}
