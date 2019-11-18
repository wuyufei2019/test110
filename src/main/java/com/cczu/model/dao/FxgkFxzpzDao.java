package com.cczu.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.FXGK_RiskPerEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 风险管控_风险值配置DAO
 * @author zpc
 * @date 2017年8月10日
 */
@Repository("FxgkFxzpzDao")
public class FxgkFxzpzDao extends BaseDao<FXGK_RiskPerEntity, Long> {

	
	/**
	 * 查询数据库中保存的记录 （在数据库中只有一条）
	 * @return
	 */
	public FXGK_RiskPerEntity findInfor(){
		List<FXGK_RiskPerEntity> list=findAll();
		if(list.size()>0)
			return list.get(0);
		else
			return new FXGK_RiskPerEntity();
	}
	
	/**
	 * 修改企业风险等级
	 * @param id
	 */
	public void updateQyFxdj(FXGK_RiskPerEntity entity) {
		String sql="update bis_enterprise set m44=fxdj.dj from "
				+ "(SELECT id,(CASE WHEN(LEVEL>="+entity.getM5()+") THEN 1 WHEN(LEVEL>="+entity.getM6()+" AND LEVEL<"+entity.getM5()+") THEN 2 WHEN(LEVEL>="+entity.getM7()+" AND LEVEL<"+entity.getM6()+") THEN 3 ELSE 4 END) dj "
				+ "FROM (SELECT ((SELECT count(1) from fxgk_accidentrisk a where a.id1=b.id and a.m9=4 and s3=0)*"+entity.getM4()+"+(SELECT count(1) from fxgk_accidentrisk a where a.id1=b.id and a.s3=0 and a.m9=3)*"+entity.getM3()+"+(SELECT count(1) from fxgk_accidentrisk a where a.id1=b.id and a.m9=2 and a.s3=0)*"+entity.getM2()+"+(SELECT count(1) from fxgk_accidentrisk a where a.id1=b.id and a.m9=1 and a.s3=0)*"+entity.getM1()+") level,b.id "
				+ "From  bis_enterprise b LEFT JOIN (SELECT DISTINCT id1 FROM fxgk_accidentrisk WHERE s3 = 0)a ON a.id1 = b.id WHERE b.s3 = 0)a )as fxdj  where bis_enterprise.id=fxdj.id";
		updateBySql(sql);
	}
	
}
