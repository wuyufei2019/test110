package com.cczu.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.FxgkFxzpzDao;
import com.cczu.model.dao.FxgkTjfxDao;
import com.cczu.model.entity.FXGK_RiskPerEntity;

@Service("FxgkFxytService")
public class FxgkFxytService {

	@Resource
	private FxgkTjfxDao fxgkTjfxDao;
	@Resource
	private FxgkFxzpzDao fxgkFxzpzDao;
	
	public List<Map<String, Object>> findMapList(Map<String, Object> mapdata) {
		FXGK_RiskPerEntity riskp=fxgkFxzpzDao.findInfor();
		List<Map<String, Object>> relist= new ArrayList<Map<String, Object>>();//返回值
		List<Map<String, Object>> listm=fxgkTjfxDao.findMapList(mapdata);
		for (Map<String, Object> map : listm) {
			if(map.get("m1")!=null && !map.get("m1").toString().equals("")){
				int Rhong=(int)map.get("hong");
				int Rcheng=(int)map.get("cheng");
				int Rhuang=(int)map.get("huang");
				int Rlan=(int)map.get("lan");
				int num=Rhong+Rcheng+Rhuang+Rlan;
				float level=(float)(Math.round((riskp.getM1()*Rhong+riskp.getM2()*Rcheng+riskp.getM3()*Rhuang+riskp.getM4()*Rlan)*10))/10;
				String yanse="";
				map.put("id", map.get("id").toString());
				map.put("title", map.get("m1").toString());
				map.put("address", map.get("m33").toString());
				map.put("pointx", map.get("m16").toString());
				map.put("pointy", map.get("m17").toString());
				map.put("isOpen", 0);
				map.put("icon", "/static/model/images/map/bdmap_icon_l01.png");
				map.put("level", level);
				map.put("Rhong", Rhong);
				map.put("Rcheng", Rcheng);
				map.put("Rhuang", Rhuang);
				map.put("Rlan", Rlan);
				map.put("num", num);
				if(level>=riskp.getM5()){
					map.put("icon", "/static/model/images/map/bdmap_icon_h01.png");
					yanse="红色";
				}else if(level>=riskp.getM6()){
					map.put("icon", "/static/model/images/map/bdmap_icon_c01.png");
					yanse="橙色";
				}else if(level>=riskp.getM7()){
					map.put("icon", "/static/model/images/map/bdmap_icon_y01.png");
					yanse="黄色";
				}else if(level<riskp.getM7()){
					map.put("icon", "/static/model/images/map/bdmap_icon_l01.png");
					yanse="蓝色";
				}
				map.put("yanse", yanse);
			}
			relist.add(map);
		}
		return relist;
	}
}
