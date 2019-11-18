package com.cczu.model.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.dao.BaseDao;

/**
 * 安全执法_设置基本信息DAO
 * @author jason
 * @date 2017年8月3日
 */
@Repository("AqzfSetBasicInfoDao")
public class AqzfSetBasicInfoDao extends BaseDao<AQZF_SetBasicInfoEntity, Long> {

	
	/**
	 * 查询数据库中保存的记录 
	 * @return
	 */
	public AQZF_SetBasicInfoEntity findInfor(){
		String sql="select a.* from xzcf_basicinfo a left join t_user u on a.id1=u.id where u.xzqy = '"+UserUtil.getCurrentUser().getXzqy()+"' ";
		List<AQZF_SetBasicInfoEntity> list2 = findBySql(sql, null, AQZF_SetBasicInfoEntity.class);
		if(list2.size()>0){
			return list2.get(0);
		}
		else{
			AQZF_SetBasicInfoEntity asb = new AQZF_SetBasicInfoEntity();
			Timestamp t=DateUtils.getSysTimestamp();
			asb.setID1(UserUtil.getCurrentUser().getId());
			asb.setS1(t);
			asb.setS2(t);
			asb.setS3(0);
			asb.setAccount("");
			asb.setAddress("");
			asb.setBankname("");
			asb.setContact("");
			asb.setCourt("");
			asb.setGov("");
			asb.setHighgov("");
			asb.setPhone("");
			asb.setYbcode("");
			asb.setSsqmc("");
			asb.setSsqjc("");
			save(asb);
			return asb;
		}
	}
	
}
