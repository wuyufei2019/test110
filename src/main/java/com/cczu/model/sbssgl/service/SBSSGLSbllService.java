package com.cczu.model.sbssgl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.IBisQyjbxxDao;
import com.cczu.model.sbssgl.dao.SBSSGLGnysDao;
import com.cczu.model.sbssgl.dao.SBSSGLQgsbDao;
import com.cczu.model.sbssgl.dao.SBSSGLSbbfDao;
import com.cczu.model.sbssgl.dao.SBSSGLSbbyTaskFirDao;
import com.cczu.model.sbssgl.dao.SBSSGLSbbyTaskSecDao;
import com.cczu.model.sbssgl.dao.SBSSGLSbdxDao;
import com.cczu.model.sbssgl.dao.SBSSGLSbglDao;
import com.cczu.model.sbssgl.dao.SBSSGLSbgzDao;
import com.cczu.model.sbssgl.dao.SBSSGLSbjfDao;
import com.cczu.model.sbssgl.dao.SBSSGLSbsqDao;
import com.cczu.model.sbssgl.dao.SBSSGLSbwxxqDao;
import com.cczu.model.sbssgl.dao.SBSSGLSbysDao;
import com.cczu.model.sbssgl.entity.SBSSGL_QGSBEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBGLEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBJFEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBSQEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBYSEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.util.common.StringUtils;

/**
 * 设备设施管理-设备履历Service
 *
 */
@Service("SBSSGLSbllService")
public class SBSSGLSbllService {

	@Resource
	private SBSSGLSbglDao sBSSGLSbglDao;
	@Resource
	private IBisQyjbxxDao bisQyjbxxDao;
	@Resource
	private SBSSGLSbwxxqDao sBSSGLSbwxxqDao;
	@Resource
	private SBSSGLSbsqDao sBSSGLSbsqDao;
	@Resource
	private SBSSGLQgsbDao sBSSGLQgsbDao;
	@Resource
	private SBSSGLSbysDao sBSSGLSbysDao;
	@Resource
	private SBSSGLGnysDao sBSSGLGnysDao;
	@Resource
	private SBSSGLSbjfDao sBSSGLSbjfDao;
	@Resource
	private SBSSGLSbgzDao sBSSGLSbgzDao;
	@Resource
	private SBSSGLSbbyTaskFirDao sBSSGLSbbyTaskFirDao;
	@Resource
	private SBSSGLSbbyTaskSecDao sBSSGLSbbyTaskSecDao;
	@Resource
	private SBSSGLSbbfDao sBSSGLSbbfDao;
	@Resource
	private SBSSGLSbdxDao sBSSGLSbdxDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=sBSSGLSbglDao.dataGrid(mapData);
		int getTotalCount=sBSSGLSbglDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 删除备品备件信息
	 * @param id
	 */
	public void deleteInfoById(long id) {
		sBSSGLSbglDao.deleteInfo(id);
	}
	
	/**
	 * 根据id查找备品备件信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findById(Long id) {
		return sBSSGLSbglDao.findById(id);
	}
	
	public SBSSGL_SBGLEntity find(Long id) {
		return sBSSGLSbglDao.find(id);
	}
	
	/**
	 * 添加备品备件信息
	 * @param entity
	 */
	public void addInfo(SBSSGL_SBGLEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		sBSSGLSbglDao.save(entity);
	}

	/**
	 * 更新备品备件信息
	 * @param entity
	 */
	public void updateInfo(SBSSGL_SBGLEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);
		sBSSGLSbglDao.save(entity);
	}
	
	/**
	 * 根据id获得设备维修word表数据
	 * @param id
	 * @return
	 */
	public Map<String, Object> getWord(long id){
		Map<String, Object> map = new HashMap<>();
		//设备信息
		Map<String,Object> sbinfo = sBSSGLSbglDao.findById(id);
		map.put("m1", sbinfo.get("m1")==null?"":sbinfo.get("m1").toString());
		map.put("m2", sbinfo.get("m2")==null?"":sbinfo.get("m2").toString());
		map.put("m3", sbinfo.get("m3")==null?"":sbinfo.get("m3").toString());
		map.put("m4", sbinfo.get("m4")==null?"":sbinfo.get("m4").toString());
		map.put("m5", sbinfo.get("m5")==null?"":sbinfo.get("m5").toString());
		map.put("m6", sbinfo.get("m6")==null?"":DateUtils.formatDate(Timestamp.valueOf(sbinfo.get("m6").toString()), "yyyy年MM月dd日"));
		map.put("m7", sbinfo.get("m7")==null?"":sbinfo.get("m7").toString());
		map.put("m8", sbinfo.get("m8")==null?"":sbinfo.get("m8").toString());
		map.put("bgrname", sbinfo.get("bgrname")==null?"":sbinfo.get("bgrname").toString());
		if (sbinfo.get("m19") != null) {
			String m19 = sbinfo.get("m19").toString();
			if ("0".equals(m19)) {
				map.put("m19", "启用");
			} else if ("1".equals(m19)) {
				map.put("m19", "停用");
			} else if ("2".equals(m19)) {
				map.put("m19", "报废");
			} 
		}
		
		//设备维修记录list
		List<Map<String, Object>> wxlist = sBSSGLSbwxxqDao.findBySbid(id);
		for (Map<String, Object> wx : wxlist) {
			wx.put("m1", wx.get("m25")==null?"":DateUtils.formatDate(Timestamp.valueOf(wx.get("m25").toString()), "yyyy年MM月dd日"));
			wx.put("m2", wx.get("m26")==null?"":wx.get("m26").toString());
			wx.put("m3", wx.get("m27")==null?"":wx.get("m27").toString());
		}
		map.put("wxlist", wxlist);
		
		return map;
		
	}
	
	// 导出保养记录附件
	public Map<String, Object> getFileList(Long sbid) {
		Map<String, Object> map = new HashMap<>();
		SBSSGL_SBGLEntity sbgl = sBSSGLSbglDao.find(sbid);
		Long sbjfid = sbgl.getSbjfid();
		if (sbjfid != null && sbjfid != 0) {
			// 设备交付
			SBSSGL_SBJFEntity sbjf = sBSSGLSbjfDao.find(sbjfid);
			if (sbjf != null && StringUtils.isNotBlank(sbjf.getM19())) {
				map.put("sbjffj", sbjf.getM19().split("\\|\\|")[0]);// 设备交付附件
			}
			
			// 设备验收
			SBSSGL_SBYSEntity sbys = sBSSGLSbysDao.find(sbjf.getSbysid());
			if (sbys != null && StringUtils.isNotBlank(sbys.getM35())) {
				map.put("sbysfj", sbys.getM35().split("\\|\\|")[0]);// 设备验收附件
			}
			
			// 请购设备
			SBSSGL_QGSBEntity qgsb = sBSSGLQgsbDao.find(sbys.getQgsbid());
			// 设备申请
			SBSSGL_SBSQEntity sbsq = sBSSGLSbsqDao.find(qgsb.getSbsqid());
			if (sbsq != null && StringUtils.isNotBlank(sbsq.getM11())) {
				map.put("sbsqfj", sbsq.getM11().split("\\|\\|")[0]);// 设备申请附件
			}
		}
		// 设备故障
		Map<String, Object> sbgz = sBSSGLSbgzDao.findBySbid(sbid);
		if (sbgz != null) {
			if (sbgz.get("m8") != null) {
				map.put("sbgzwxfj", sbgz.get("m8").toString().split("\\|\\|")[0]);// 设备维修报告附件
			}
			if (sbgz.get("m14") != null) {
				map.put("sbgzxqfj", sbgz.get("m14").toString().split("\\|\\|")[0]);// 设备维修需求单附件
			}
		}
		
		// 设备大修
		String sbbh = sbgl.getM1();// 设备编号
		Map<String, Object> sbdx = sBSSGLSbdxDao.findBySbbh(sbbh);
		if (sbdx != null) {
			if (sbdx.get("m14") != null) {
				map.put("sbdxysfj", sbdx.get("m14").toString().split("\\|\\|")[0]);// 设备大修验收报告附件
			}
			if (sbdx.get("m22") != null) {
				map.put("sbdxwxfj", sbdx.get("m22").toString().split("\\|\\|")[0]);// 设备大修维修合同附件
			}
		} 
		
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("sbid", sbid);
		// 设备一级保养计划附件集合
		List<Map<String, Object>> firfjList = sBSSGLSbbyTaskFirDao.getFileList(dataMap);
		if (firfjList != null && firfjList.size() > 0) {
			for (Map<String, Object> firfj : firfjList) {
				if (firfj.get("fj") != null) {
					firfj.put("fj", firfj.get("fj").toString().split("\\|\\|")[0]);
				}
			}
			map.put("firfjlist", firfjList);
		}
		
		// 设备二级保养计划附件集合
		List<Map<String, Object>> secfjList = sBSSGLSbbyTaskSecDao.getFileList(dataMap);
		if (secfjList != null && secfjList.size() > 0) {
			for (Map<String, Object> secfj : secfjList) {
				if (secfj.get("fj") != null) {
					secfj.put("fj", secfj.get("fj").toString().split("\\|\\|")[0]);
				}
			}
			map.put("secfjlist", secfjList);
		}
		
		// 设备报废
		Map<String, Object> sbbf = sBSSGLSbbfDao.findBySbid(sbid);
		if (sbbf != null && sbbf.get("fj") != null) {
			map.put("sbbffj", sbbf.get("fj").toString().split("\\|\\|")[0]);// 设备报废附件
		}
		return map;
	}
}
