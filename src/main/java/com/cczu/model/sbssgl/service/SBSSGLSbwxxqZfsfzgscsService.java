package com.cczu.model.sbssgl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.IBisQyjbxxDao;
import com.cczu.model.sbssgl.dao.SBSSGLSbwxxqZfsfzgscsDao;
import com.cczu.model.sbssgl.entity.SBSSGL_SBWXXQ_ZFSFZGSCSEntity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 设备设施管理-再发生防止改善措施Service
 *
 */
@Service("SBSSGLSbwxxqZfsfzgscsService")
public class SBSSGLSbwxxqZfsfzgscsService {

	@Resource
	private SBSSGLSbwxxqZfsfzgscsDao sBSSGLSbwxxqZfsfzgscsDao;
	@Resource
	private IBisQyjbxxDao bisQyjbxxDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=sBSSGLSbwxxqZfsfzgscsDao.dataGrid(mapData);
		int getTotalCount=sBSSGLSbwxxqZfsfzgscsDao.getTotalCount(mapData);
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
		sBSSGLSbwxxqZfsfzgscsDao.deleteInfo(id);
	}
	
	/**
	 * 根据id查找备品备件信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findById(Long id) {
		return sBSSGLSbwxxqZfsfzgscsDao.findById(id);
	}
	
	public SBSSGL_SBWXXQ_ZFSFZGSCSEntity find(Long id) {
		return sBSSGLSbwxxqZfsfzgscsDao.find(id);
	}
	
	/**
	 * 根据id查找备品备件信息
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> findByWxid(Long id) {
		return sBSSGLSbwxxqZfsfzgscsDao.findByWxid(id);
	}
	
	/**
	 * 添加备品备件信息
	 * @param entity
	 */
	public void addInfo(SBSSGL_SBWXXQ_ZFSFZGSCSEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		sBSSGLSbwxxqZfsfzgscsDao.save(entity);
	}

	/**
	 * 更新备品备件信息
	 * @param entity
	 */
	public void updateInfo(SBSSGL_SBWXXQ_ZFSFZGSCSEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);
		sBSSGLSbwxxqZfsfzgscsDao.save(entity);
	}
	
	/**
	 * 根据Map获取备品备件word表数据
	 * @param map
	 * @return
	 */
	/*public Map<String, Object> getWord(Map<String, Object> dataMap){
		List<Map<String, Object>> wordList = new ArrayList<>();
		Map<String, Object> wordMap = new HashMap<>();
		
		if (dataMap.get("fid") != null) {//集团公司 导出全部 备品备件数据
			
			if (StringUtils.isEmpty(dataMap.get("qyname").toString())) {//导出全部备品备件数据
				List<Map<String, Object>> bpbjList = null;
				Map<String, Object> bpbjMap = null;
				
				//集团公司数据
				dataMap.put("qyid", dataMap.get("fid"));
				bpbjList = sBSSGLSbwxjlDao.findListByMap(dataMap);
				bpbjMap = new HashMap<>();
				bpbjMap.put("qyname", bpbjList.get(0).get("qyname"));
				bpbjMap.put("bpbjlist", bpbjList);
				wordList.add(bpbjMap);
				
				//子公司数据
				List<Map<String, Object>> qyidList = bisQyjbxxDao.findIdsByFid(Long.parseLong(dataMap.get("fid").toString()));
				for (Map<String, Object> idMap : qyidList) {
					bpbjMap = new HashMap<>();
					bpbjList = sBSSGLSbwxjlDao.findListByMap(idMap);
					bpbjMap.put("qyname", bpbjList.get(0).get("qyname"));
					bpbjMap.put("bpbjlist", bpbjList);
					wordList.add(bpbjMap);
				}
				
				wordMap.put("qyname", bpbjList.get(0).get("qyname"));
				wordMap.put("bpbjlist", wordList);
				wordMap.put("ftlname", "bpbj2.ftl");
			} else { //导出 子公司 或者 集团公司 的备品备件数据
				dataMap.remove(dataMap.get("fid"));
				//备品备件信息
				List<Map<String, Object>> bpbjList = sBSSGLSbwxjlDao.findListByMap(dataMap);
				wordMap.put("qyname", bpbjList.get(0).get("qyname"));
				wordMap.put("ftlname", "bpbj.ftl");
				wordMap.put("bpbjlist", bpbjList);
			}
		} else if (dataMap.get("qyid") != null ) {//子公司 导出自己公司的备品备件清单
			//备品备件信息
			List<Map<String, Object>> bpbjList = sBSSGLSbwxjlDao.findListByMap(dataMap);
			wordMap.put("qyname", bpbjList.get(0).get("qyname"));
			wordMap.put("ftlname", "bpbj.ftl");
			wordMap.put("bpbjlist", bpbjList);
		}
		
		return wordMap;
	}*/
}
