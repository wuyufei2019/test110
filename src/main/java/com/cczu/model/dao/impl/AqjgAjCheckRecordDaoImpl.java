package com.cczu.model.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IAqjgAjCheckRecordDao;
import com.cczu.model.entity.AQJD_AjCheckRecordEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqjgAjCheckRecordDao")
public class AqjgAjCheckRecordDaoImpl extends
		BaseDao<AQJD_AjCheckRecordEntity, Long> implements
		IAqjgAjCheckRecordDao {

	/**
	 * 添加安监初检信息
	 */
	@Override
	public void addAjCheckInfo(AQJD_AjCheckRecordEntity acre) {
		// TODO Auto-generated method stub
		save(acre);
	}

	@Override
	public AQJD_AjCheckRecordEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		String sql = "select * from aqjd_ajcheckrecord where s3=0 and id=" + id;
		List<AQJD_AjCheckRecordEntity> list = findBySql(sql, null,
				AQJD_AjCheckRecordEntity.class);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public int updateAjFjRecordInfo(AQJD_AjCheckRecordEntity acre) {
		// TODO Auto-generated method stub
		String sql =" update aqjd_ajcheckrecord set rename='"+acre.getRename()+"', reopinion='"+acre.getReopinion()+"', businessreperson='"+acre.getBusinessreperson()+"',  reurl='"+acre.getReurl()+"',recheckdate='"+acre.getRecheckdate()+"', s2='"+acre.getS2()+"',refjurl='"+acre.getRefjurl()+"' where id="+acre.getID();
		 return updateBySql(sql);
	}
	@Override
	public void updateAjRecordInfo(AQJD_AjCheckRecordEntity acre) {
		// TODO Auto-generated method stub
		/*String sql =" update aqjd_ajcheckrecord set checkname='"+ acre.getCheckname()+"', opinion='"+acre.getOpinion()+"',  url='"+acre.getUrl()+"',businessperson='"+acre.getBusinessperson()+"', checkdate='"+acre.getCheckdate()+"', s2='"+acre.getS2()+"' where id="+acre.getID();
		return updateBySql(sql);*/
		save(acre);
	}

	@Override
	public AQJD_AjCheckRecordEntity findInfoByQyId(long id1) {
		// TODO Auto-generated method stub
		String sql = "select * from aqjd_ajcheckrecord where s3=0 and id1="
				+ id1;
		List<AQJD_AjCheckRecordEntity> list = findBySql(sql, null,
				AQJD_AjCheckRecordEntity.class);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	

}
