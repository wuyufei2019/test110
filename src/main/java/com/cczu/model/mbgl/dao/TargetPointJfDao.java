package com.cczu.model.mbgl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.mbgl.entity.Target_Points_jf;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.dao.BaseDao;

/**
 * 积分设置DAO
 */
@Repository("TargetPointJfDao")
public class TargetPointJfDao extends BaseDao<Target_Points_jf, Long> {
	
	/**
	 * 查询数据库中保存的记录 
	 * @return
	 */
	public Target_Points_jf findInfor(){
		String sql="select * from target_points_jf where id2 = "+UserUtil.getCurrentUser().getId2();
		List<Target_Points_jf> list2=findBySql(sql, null, Target_Points_jf.class);
		if(list2.size()>0){
			return list2.get(0);
		}
		else{
			Target_Points_jf asn = new Target_Points_jf();
			asn.setID1(UserUtil.getCurrentUser().getId());
			asn.setID2(UserUtil.getCurrentUser().getId2());
			asn.setM1(0);
			asn.setM2(0);
			asn.setM3(0);
			asn.setM4(0);
			asn.setM5(0);
			asn.setM1_1(100);
			asn.setM2_1(100);
			asn.setM3_1(100);
			asn.setM4_1(100);
			asn.setM5_1(100);
			save(asn);
			return asn;
		}
	}
}
