package com.cczu.model.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQZF_SetNumberEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.dao.BaseDao;

/**
 * 安全执法_设置文书编号DAO
 * @author jason
 * @date 2017年8月3日
 */
@Repository("AqzfSetNumberDao")
public class AqzfSetNumberDao extends BaseDao<AQZF_SetNumberEntity, Long> {

	
	/**
	 * 查询数据库中保存的记录 
	 * @return
	 */
	public AQZF_SetNumberEntity findInfor(){
		String sql="select a.* from aqzf_setnumber a left join t_user u on a.id1=u.id where u.xzqy = '"+UserUtil.getCurrentUser().getXzqy()+"' ";
		List<AQZF_SetNumberEntity> list2=findBySql(sql, null, AQZF_SetNumberEntity.class);
		if(list2.size()>0){
			return list2.get(0);
		}
		else{
			AQZF_SetNumberEntity asn = new AQZF_SetNumberEntity();
			Timestamp t=DateUtils.getSysTimestamp();
			asn.setID1(UserUtil.getCurrentUser().getId());
			asn.setS1(t);
			asn.setS2(t);
			asn.setS3(0);
			asn.setM1(1);
			asn.setM6(1);
			save(asn);
			return asn;
		}
	}
}
