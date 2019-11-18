package com.cczu.model.sbssgl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.sbssgl.dao.SBSSGLQgsbDao;
import com.cczu.model.sbssgl.dao.SBSSGLSbsqDao;
import com.cczu.model.sbssgl.dao.SBSSGLShjlDao;
import com.cczu.model.sbssgl.entity.SBSSGL_QGSBEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBSQEntity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 设备设施管理-设备申请Service
 *
 */
@Service("SBSSGLSbsqService")
public class SBSSGLSbsqService {

	@Resource
	private SBSSGLSbsqDao sBSSGLSbsqDao;
	@Resource
	private SBSSGLQgsbDao sBSSGLQgsbDao;
	@Resource
	private SBSSGLShjlDao sBSSGLShjlDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=sBSSGLSbsqDao.dataGrid(mapData);
		int getTotalCount=sBSSGLSbsqDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 删除设备申请信息
	 * @param id
	 */
	public void deleteSbsqById(long id) {
		sBSSGLSbsqDao.deleteInfo(id);
	}
	
	/**
	 * 根据id查找设备申请详细信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findById(Long id) {
		return sBSSGLSbsqDao.findById(id);
	}
	
	public SBSSGL_SBSQEntity find(Long id) {
		return sBSSGLSbsqDao.find(id);
	}
	
	/**
	 * 添加设备申请信息
	 * @param entity
	 */
	public void addInfo(SBSSGL_SBSQEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		sBSSGLSbsqDao.save(entity);
	}

	/**
	 * 更新设备申请信息
	 * @param entity
	 */
	public void updateInfo(SBSSGL_SBSQEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);
		sBSSGLSbsqDao.save(entity);
	}
	
	/**
     * 根据设备申请id查找
     * @param sbsqid
     * @return
     */
    public List<SBSSGL_QGSBEntity> findInfoBySbsqid(Long sbsqid) {
		return sBSSGLQgsbDao.findInfoBySbsqid(sbsqid);
	}
    
    /**
     * 根据设备申请id查找请购设备信息和对应的验收状态
     * @param sbsqid
     * @return
     */
    public Map<String, Object> qgzcDataGrid(Map<String, Object> mapData) {
    	List<Map<String,Object>> list=sBSSGLQgsbDao.dataGrid(mapData);
		int getTotalCount=sBSSGLQgsbDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 删除请购设备信息
	 * @param sbsqid
	 */
	public void deleteQgsbBySbsqid(long sbsqid) {
		sBSSGLQgsbDao.deleteBySbsqid(sbsqid);
	}
	
	/**
	 * 修改设备申请状态为全部验收
	 * @param sbsqid
	 */
	public void updYsStatus(long sbsqid) {
		sBSSGLSbsqDao.updYsStatus(sbsqid);
	}
	
	
	/**
	 * 添加请购设备信息
	 * @param entity
	 */
	public void addQgsb(SBSSGL_QGSBEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		sBSSGLQgsbDao.save(entity);
	}
	
	/**
	 * 根据id获得固定资产请购单word表数据
	 * @param id
	 * @return
	 */
	public Map<String, Object> getWord(long id){
		Map<String, Object> map=new HashMap<String, Object>();
		//设备申请信息
		Map<String,Object> sbsq = sBSSGLSbsqDao.findById(id);
		map.put("sqbmname", sbsq.get("sqbmname")==null?"":sbsq.get("sqbmname").toString());
		map.put("sqrname", sbsq.get("sqrname")==null?"":sbsq.get("sqrname").toString());
		map.put("qgrq", sbsq.get("m3")==null?"":DateUtils.formatDate(Timestamp.valueOf(sbsq.get("m3").toString()), "yyyy年MM月dd日"));
		map.put("m4", sbsq.get("m4")==null?"":sbsq.get("m4").toString().replaceAll("\\s*", ""));
		map.put("m5", sbsq.get("m5")==null?"":sbsq.get("m5").toString());
		map.put("m6", sbsq.get("m6")==null?"":sbsq.get("m6").toString());
		map.put("m8", sbsq.get("m8")==null?"":sbsq.get("m8").toString());
		map.put("m9", sbsq.get("m9")==null?"":sbsq.get("m9").toString().replaceAll("\\s*", ""));
		
		
		
		//勾选项
		map.put("z1", "00A3");
		map.put("z2", "00A3");
		map.put("z3", "00A3");
		map.put("z4", "00A3");
		if(sbsq.get("m7")!=null){
			String m7 = sbsq.get("m7").toString();
			if(m7.equals("1")){//预算内
				map.put("z1", "0052");
			}else if(m7.equals("2")){//预算外
				map.put("z2", "0052");
			}
			
			if(sbsq.get("m5")!=null&&!sbsq.get("m5").toString().equals("")){
				int m5 = Integer.valueOf(sbsq.get("m5").toString());
				if(m7.equals("1")){//预算内
					if(m5>1500000){
						map.put("z3", "0052");
					}
				}else if(m7.equals("2")){//预算外
					if(m5>67000&&m5<=300000){
						map.put("z3", "0052");
					}else if(m5>300000){
						map.put("z4", "0052");
					}
				}
			}
		}
		
		//请购设备list
		List<SBSSGL_QGSBEntity> qgsbList = sBSSGLQgsbDao.findInfoBySbsqid(id);
		map.put("QGSBLIST", qgsbList);
		return map;
	}
	
	/**
     * 根据请购设备id查找请购设备信息
     * @param qgsbid
     * @return
     */
    public SBSSGL_QGSBEntity findQgsbInfoByQgsbid(Long qgsbid) {
		return sBSSGLQgsbDao.findInfoById(qgsbid);
	}
    
    /**
     * 根据请购设备id修改该设备的验收状态
     * @param qgsbid
     * @return
     */
    public void updQgsbInfoById(Long qgsbid) {
		sBSSGLQgsbDao.updateById(qgsbid);
	}
}
