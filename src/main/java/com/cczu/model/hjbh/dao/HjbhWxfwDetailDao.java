package com.cczu.model.hjbh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.hjbh.entity.HJBH_DangerTrashRecordDetail;
import com.cczu.util.dao.BaseDao;

@Repository("HjbhWxfwDetailDao")
public class HjbhWxfwDetailDao extends BaseDao<HJBH_DangerTrashRecordDetail, Long> {

	public void deleteInfoById1(long id1) {
		String hql = " DELETE  from HJBH_DangerTrashRecordDetail WHERE recordid=?0";
		batchExecute(hql, id1);
	}
	
	public List<Map<String,Object>> dataGrid(long id){
		String sql="SELECT a.*,b.name , b.kind from HJBH_DangerTrashRecordDetail a left join hjbh_wxgl b on b.id =a.trashid where b.s3=0 and a.recordid="+id;
		List<Map<String,Object>> list=findBySql(sql, null, Map.class);
		return list;
	}
	
}
