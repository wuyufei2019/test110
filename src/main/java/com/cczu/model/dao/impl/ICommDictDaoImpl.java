package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.ICommDictDao;
import com.cczu.sys.system.entity.Dict;
import com.cczu.util.dao.BaseDao;

@Repository("ICommDictDao")
public class ICommDictDaoImpl extends BaseDao<Dict,Long> implements ICommDictDao {

	/**
     * 查询企业数据表格
     * @return
     */
	@Override
	public List<Dict> dataList(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" select * from t_dict where 1=1 "+ content ;
		List<Dict> list=findBySql(sql, null,Dict.class);
		return list;
	}
	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("type")!=null&&mapData.get("type")!=""){
			content = content + "and TYPE ='"+mapData.get("type")+"'"; 
		}
		if(mapData.get("value")!=null&&mapData.get("value")!=""){
			content = content + "and value ='"+mapData.get("value")+"'"; 
		}
		
		return content;
	}

	
}
