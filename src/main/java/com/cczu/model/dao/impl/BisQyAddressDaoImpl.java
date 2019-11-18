package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.BIS_EnterpriseFactoryEntity;
import com.cczu.model.dao.IBisQyAddressDao;
import com.cczu.util.dao.BaseDao;

@Repository("BisQyAddressDao")
public class BisQyAddressDaoImpl extends BaseDao<BIS_EnterpriseFactoryEntity, Long> implements IBisQyAddressDao {

	@Override
	public List<BIS_EnterpriseFactoryEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY ID) AS RowNumber,* FROM bis_enterprisefactory WHERE S3=0 "+content+" ) "
				+ "AS a WHERE S3=0 and RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) "+ content ;
		
		List<BIS_EnterpriseFactoryEntity> list=findBySql(sql, null,BIS_EnterpriseFactoryEntity.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) SUM  FROM bis_enterprisefactory WHERE S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public List<BIS_EnterpriseFactoryEntity> findInfoByQyId(long qyid) {
		String sql="SELECT * FROM bis_enterprisefactory WHERE S3=0 AND ID1="+qyid;
		List<BIS_EnterpriseFactoryEntity> list=findBySql(sql, null,BIS_EnterpriseFactoryEntity.class);
		return list;
	}

	@Override
	public void addInfo(BIS_EnterpriseFactoryEntity bis) {
		String sql="INSERT INTO bis_enterprisefactory (S1,S2,S3,M1,M2,M3,ID1) "
				+ " VALUES (getdate() ,getdate(),0,'"+bis.getM1()+"','"+bis.getM2()+"','"+bis.getM3()+"','"+bis.getID1()+"'  )";
		updateBySql(sql);
		
	}

	@Override
	public Long returnBySqlID(BIS_EnterpriseFactoryEntity bis) {
		save(bis);
		return bis.getID();
	}
	
	@Override
	public void updateInfo(BIS_EnterpriseFactoryEntity bis) {
		String sql=" UPDATE bis_enterprisefactory SET "
				+ " S2 = '"+bis.getS2()+"',M1='"+bis.getM1()+"',M2 ='"+bis.getM2()+"',M3 ='"+bis.getM3()+"' "
				+ " WHERE ID="+bis.getID()+" ";
		updateBySql(sql);
	}

	@Override
	public void deleteInfo(long id) {
		String sql=" UPDATE bis_enterprisefactory SET S3=1 WHERE ID="+id+" ";
		updateBySql(sql);
	}

	@Override
	public List<BIS_EnterpriseFactoryEntity> findAlllist() {
		String sql=" SELECT  * FROM  bis_enterprisefactory WHERE S3=0";
		List<BIS_EnterpriseFactoryEntity> list=findBySql(sql, null,BIS_EnterpriseFactoryEntity.class);
		return list;
	}

	@Override
	public BIS_EnterpriseFactoryEntity findInfoById(long id) {
		String sql="SELECT * FROM bis_enterprisefactory WHERE S3=0 AND ID="+id;
		List<BIS_EnterpriseFactoryEntity> list=findBySql(sql, null,BIS_EnterpriseFactoryEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND ID1="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND M1 like'%"+mapData.get("qyname")+"%'"; 
		}
		if(mapData.get("datestart")!=null&&mapData.get("datestart")!=""){
			content = content + "AND M6 >='"+mapData.get("datestart")+" 00:00:00"+"' "; 
		}
		if(mapData.get("dateend")!=null&&mapData.get("dateend")!=""){
			content = content + "AND M6 <='"+mapData.get("dateend")+" 23:59:59"+"' "; 
		}
		
		return content;
	}

	
}
