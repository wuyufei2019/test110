package com.cczu.model.service;

import com.cczu.model.dao.*;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * @Description: 隐患大数据Service
 * @author: LL
 * @date: 2019年7月5日
 */
@Transactional(readOnly=true)
@Service("YhpcDsjService")
public class YhpcDsjService {

	@Autowired
	private IBisQyjbxxDao bisQyjbxx;
	@Resource
	private FxgkFxxxDao fxgkFxxxDao;
	@Resource
	private YhpcYhpcjlDao yhpcYhpcjlDao;
	@Resource
	private IBisTzsbxxDao bisTzsbxxDao;
    @Resource
    private IBisYgxxDao ygxxDao;
	@Resource
	private IMonitorTankDataDao monitorTankDataDao;
	@Resource
	private IMonitorGasDataDao monitorGasDataDao;
	@Resource
	private IMonitorGwgyDataDao gwgyDataDao;
	@Autowired
	private IMonitorEdmDataDao monitorEdmDataDao;
	@Resource
	private IMonitorHydropowerDataDao monitorHydropowerDataDao;
	/**
	 * 获取信息总览
	 * @param id
	 * @return
	 */
	public String getxxzl(Long qyid) {
		ShiroRealm.ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Map<String, Object> mapData=new HashMap<>();
		mapData.put("qyid",qyid);
		BIS_EnterpriseEntity qyinfor = bisQyjbxx.findInfoById(sessionuser.getQyid());
        int ygCount=Integer.parseInt(qyinfor.getM30());//员工人数
		int fxCount=fxgkFxxxDao.getTotalCount(mapData);//风险点数
		int tzsbCount=bisTzsbxxDao.getTotalCount(mapData);//特种设备
		int yhCount=yhpcYhpcjlDao.getTotalCount(mapData);//当前隐患
		int cgCount=monitorTankDataDao.getTotalCount(mapData);//储罐数量
		int qtCount=monitorGasDataDao.getTotalCount(mapData);//气体浓度
		int gwgyCount=gwgyDataDao.getTotalCount(mapData);//高危工艺
		int edmCount=monitorEdmDataDao.getTotalCount(mapData);//二道门
		int sdqCount=monitorHydropowerDataDao.getTotalCount(mapData);//水电气

		Map<String, Object> mapData2=new HashMap<>();
        mapData2.put("ygCount",ygCount);
		mapData2.put("fxCount",fxCount);
		mapData2.put("tzsbCount",tzsbCount);
		mapData2.put("yhCount",yhCount);
		mapData2.put("cgCount",cgCount);
		mapData2.put("qtCount",qtCount);
		mapData2.put("gwgyCount",gwgyCount);
		mapData2.put("edmCount",edmCount);
		mapData2.put("sdqCount",sdqCount);
		return JsonMapper.getInstance().toJson(mapData2);
	}

    /**
     * 风险点统计
     * @param id
     * @return
     */
    public String fxdtj(Long qyid) {
        Map<String, Object> mapData=new HashMap<>();
        mapData.put("qyid",qyid);
        return JsonMapper.getInstance().toJson(fxgkFxxxDao.getFxdCount(qyid));
    }

	/**
	 * 隐患整改情况
	 * @param qyid
	 * @return
	 */
	public String yhzgqk(Long qyid) {
        Map<String, Object> mapData=new HashMap<>();
        mapData.put("qyid",qyid);
        return JsonMapper.getInstance().toJson(yhpcYhpcjlDao.yhzgqk(qyid));
    }

    /**
     * 实时隐患排查（风险点+自查点）
     * @param mapData
     * @return
     */
    public String yhpcs(Long qyid){
        Map<String, Object> map=new HashMap<>();
        return JsonMapper.getInstance().toJson(yhpcYhpcjlDao.weekyhzg(qyid).get(0));
    }

	/**
	 * 今日隐患
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> jryh(Long qyid){
		return yhpcYhpcjlDao.jryh(qyid);
	}
}
