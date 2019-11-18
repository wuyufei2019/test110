package com.cczu.model.sbssgl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.sbssgl.dao.SBSSGLGnysDao;
import com.cczu.model.sbssgl.dao.SBSSGLShjlDao;
import com.cczu.model.sbssgl.dao.SBSSGLSbysDao;
import com.cczu.model.sbssgl.entity.SBSSGL_GNYSEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBYSEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.util.common.StringUtils;

/**
 * 设备设施管理-设备验收Service
 *
 */
@Service("SBSSGLSbysService")
public class SBSSGLSbysService {

	@Resource
	private SBSSGLSbysDao sBSSGLSbysDao;
	@Resource
	private SBSSGLGnysDao sBSSGLGnysDao;
	@Resource
	private SBSSGLShjlDao sBSSGLShjlDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=sBSSGLSbysDao.dataGrid(mapData);
		int getTotalCount=sBSSGLSbysDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 删除设备验收信息
	 * @param id
	 */
	public void deleteSbsqById(long id) {
		sBSSGLSbysDao.deleteInfo(id);
	}
	
	/**
	 * 根据id查找设备验收详细信息
	 * @param id
	 * @return
	 */
	public SBSSGL_SBYSEntity find(Long id) {
		return sBSSGLSbysDao.find(id);
	}
	
	/**
	 * 添加设备验收信息
	 * @param entity
	 */
	public void addInfo(SBSSGL_SBYSEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		sBSSGLSbysDao.save(entity);
	}

	/**
	 * 更新设备验收信息
	 * @param entity
	 */
	public void updateInfo(SBSSGL_SBYSEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);
		sBSSGLSbysDao.save(entity);
	}
	
	/**
     * 根据设备验收id查找功能验收信息
     * @param sbysid
     * @return
     */
    public List<SBSSGL_GNYSEntity> findInfoBySbysid(Long sbysid) {
		return sBSSGLGnysDao.findInfoBySbysid(sbysid);
	}
	
	/**
	 * 删除功能验收信息
	 * @param sbysid
	 */
	public void deleteGnysBySbysid(long sbysid) {
		sBSSGLGnysDao.deleteBySbysid(sbysid);
	}
	
	/**
	 * 添加功能验收信息
	 * @param entity
	 */
	public void addGnys(SBSSGL_GNYSEntity entity) {
		sBSSGLGnysDao.save(entity);
	}
	
	/**
	 * 根据id获得设备验收单word表数据
	 * @param id
	 * @return
	 */
	public Map<String, Object> getWord(long id){
		Map<String, Object> map=new HashMap<String, Object>();
		//设备验收信息
		SBSSGL_SBYSEntity sbys = sBSSGLSbysDao.find(id);
		
		map.put("m2", sbys.getM2()==null?"":sbys.getM2());
		map.put("m3", sbys.getM3()==null?"":DateUtils.formatDate(sbys.getM3(),"yyyy年MM月dd日"));
		map.put("m4", sbys.getM4()==null?"":sbys.getM4());
		map.put("m5", sbys.getM5()==null?"":sbys.getM5());
		map.put("m6", sbys.getM6()==null?"":sbys.getM6());
		map.put("m7", sbys.getM7()==null?"":sbys.getM7());
		map.put("m8", sbys.getM8()==null?"":DateUtils.formatDate(sbys.getM8(),"yyyy年MM月dd日"));
		map.put("m9", sbys.getM9()==null?"":sbys.getM9());
		map.put("m10", sbys.getM10()==null?"":sbys.getM10());
		map.put("m11", sbys.getM11()==null?"":sbys.getM11());
		map.put("m12", sbys.getM12()==null?"":sbys.getM12());
		map.put("m13", sbys.getM13()==null?"":sbys.getM13());
		map.put("m14", sbys.getM14()==null?"":sbys.getM14());
		map.put("m15", sbys.getM15()==null?"":sbys.getM15());
		map.put("m16", sbys.getM16()==null?"":sbys.getM16());
		map.put("m17", sbys.getM17()==null?"":sbys.getM17());
		map.put("m18", sbys.getM18()==null?"":sbys.getM18());
		map.put("m19", sbys.getM19()==null?"":sbys.getM19());
		map.put("m20", sbys.getM20()==null?"":sbys.getM20());
		map.put("m21", sbys.getM21()==null?"":sbys.getM21());
		map.put("m22", sbys.getM22()==null?"":sbys.getM22());
		map.put("m23", sbys.getM23()==null?"":sbys.getM23());
		map.put("m24", sbys.getM24()==null?"":sbys.getM24());
		map.put("m25", sbys.getM25()==null?"":sbys.getM25());
		map.put("m26", sbys.getM26()==null?"   年     月     日":DateUtils.formatDate(sbys.getM26(),"yyyy年MM月dd日"));
		map.put("m27", sbys.getM27()==null?"   年     月     日":DateUtils.formatDate(sbys.getM27(),"yyyy年MM月dd日"));
		map.put("m28", sbys.getM28()==null?"":sbys.getM28().replaceAll("\\s*", ""));
		map.put("m29", sbys.getM29()==null?"":sbys.getM29().replaceAll("\\s*", ""));
		map.put("m33", sbys.getM33()==null?"":sbys.getM33().replaceAll("\\s*", ""));
		
		
		//勾选项
		map.put("z1", "00A3");
		map.put("z2", "00A3");
		map.put("z3", "00A3");
		map.put("z4", "00A3");
		map.put("z5", "00A3");
		map.put("z6", "00A3");
		
		//设备类型勾选
		if(!StringUtils.isEmpty(sbys.getM1())){
			if(sbys.getM1().equals("生产设备/厂务设施")){
				map.put("z1", "0052");
			}else if(sbys.getM1().equals("产品量测/检验仪器")){
				map.put("z2", "0052");
			}else if(sbys.getM1().equals("其它")){
				map.put("z3", "0052");
			}
		}
		
		//检验条件勾选
		if(!StringUtils.isEmpty(sbys.getM30())){
			if(sbys.getM30().equals("1")){
				map.put("z4", "0052");
			}else if(sbys.getM30().equals("2")){
				map.put("z5", "0052");
			}
		}
		
		//新设备购买勾选
		if(!StringUtils.isEmpty(sbys.getM31())){
			if(sbys.getM31().equals("2")){
				map.put("z6", "0052");
			}
		}
		
		//功能验收list
		List<SBSSGL_GNYSEntity> gnysList = sBSSGLGnysDao.findInfoBySbysid(id);
		map.put("GNYSLIST", gnysList);
		return map;
	}
	
}
