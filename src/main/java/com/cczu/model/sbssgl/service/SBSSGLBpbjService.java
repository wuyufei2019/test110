package com.cczu.model.sbssgl.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.cczu.model.sbssgl.dao.SBSSGLBpbjDao;
import com.cczu.model.sbssgl.entity.SBSSGL_BPBJEntity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 设备设施管理-备品备件Service
 *
 */
@Service("SBSSGLBpbjService")
public class SBSSGLBpbjService {

	@Resource
	private SBSSGLBpbjDao sBSSGLBpbjDao;
	
	/**
	 * 添加备品备件信息
	 * @param entity
	 */
	public void addInfo(HttpServletRequest request) {
		String[] m1 = request.getParameterValues("m1");
		String[] m2 = request.getParameterValues("m2");
		String[] m3 = request.getParameterValues("m3");
		String[] m4 = request.getParameterValues("m4");
		String[] m6 = request.getParameterValues("m6");
		String[] m7 = request.getParameterValues("m7");
		String sbidStr = request.getParameter("sbid");
		
		Long sbid = Long.parseLong(sbidStr);
		sBSSGLBpbjDao.deleteInfoBySbid(sbid); //删除数据库中与该sbid关联的记录
		
		for (int i = 0; i < m1.length; i++) {
			SBSSGL_BPBJEntity entity = new SBSSGL_BPBJEntity();
			Timestamp t = DateUtils.getSystemTime();
			entity.setS1(t);
			entity.setS2(t);
			entity.setS3(0);
			entity.setSbid(sbid); //关联的设备id
			entity.setM1(m1[i]);
			entity.setM2(m2[i]);
			entity.setM3(m3[i]);
			entity.setM4(m4[i]);
			entity.setM6(m6[i]);
			entity.setM7(m7[i]);
			
			if ("0".equals(request.getParameter("sbtype"))) { //如果是普通设备
				entity.setM9("0");
			} else if ("1".equals(request.getParameter("sbtype"))) { //如果是特种设备
				entity.setM9("1");
			}
			sBSSGLBpbjDao.save(entity); //保存备品备件信息
		}
		
	}
	
	/**
	 * 根据设备id查找关联的备品备件
	 * @param sbid 设备id
	 * @return
	 */
	public List<Map<String, Object>> findListBySbId(Long sbid) {
		return sBSSGLBpbjDao.findListBySbId(sbid);
	}

	
	/**
	 * 根据Map获取备品备件word表数据
	 * @param map
	 * @return
	 */
	/*public Map<String, Object> getWord(Map<String, Object> dataMap){
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		List<Map<String, Object>> wordList = new ArrayList<>();
		Map<String, Object> wordMap = new HashMap<>();
		
		if (dataMap.get("fid") != null) {//集团公司
			
			if (StringUtils.isEmpty(dataMap.get("qyname").toString())) {//导出全部的备品备件数据
				List<Map<String, Object>> bpbjList = null;
				Map<String, Object> bpbjMap = null;
				
				//集团公司数据
				dataMap.put("qyid", dataMap.get("fid"));
				bpbjList = sBSSGLBpbjDao.findListByMap(dataMap);
				bpbjMap = new HashMap<>();
				bpbjMap.put("qyname", bisQyjbxxDao.findInfoById(sessionuser.getQyid()).getM1());
				bpbjMap.put("bpbjlist", bpbjList);
				wordList.add(bpbjMap);
				
				//子公司数据
				List<Map<String, Object>> qyidList = bisQyjbxxDao.findIdsByFid(Long.parseLong(dataMap.get("fid").toString()));
				for (Map<String, Object> idMap : qyidList) {
					bpbjMap = new HashMap<>();
					idMap.put("sbtype", dataMap.get("sbtype"));
					bpbjList = sBSSGLBpbjDao.findListByMap(idMap);
					BIS_EnterpriseEntity bis = bisQyjbxxDao.findInfoById(Long.parseLong(idMap.get("qyid").toString()));
					if (bis != null) {
						bpbjMap.put("qyname", bis.getM1());
					}
					bpbjMap.put("bpbjlist", bpbjList);
					wordList.add(bpbjMap);
				}
				
				BIS_EnterpriseEntity bis2 = bisQyjbxxDao.findInfoById(sessionuser.getQyid());
				if (bis2 != null) {
					wordMap.put("qyname", bis2.getM1());
				}
				wordMap.put("bpbjlist", wordList);
				wordMap.put("ftlname", "bpbj2.ftl");//bpbj2.ftl 为集团用户导出子公司及集团公司全部数据时所使用的模板
			} else if (!StringUtils.isEmpty(dataMap.get("qyname").toString())){ //导出 子公司 或者 集团公司 的备品备件数据
				dataMap.remove(dataMap.get("fid"));
				//备品备件信息
				List<Map<String, Object>> bpbjList = sBSSGLBpbjDao.findListByMap(dataMap);
				Map<String, Object> map = new HashMap<>();
				map.put("qyname", dataMap.get("qyname"));
				List<Map<String, Object>> list = bisQyjbxxDao.findQyIdTextList(map);
				if (list.size() > 0) {
					wordMap.put("qyname", list.get(0).get("text"));
				}
				wordMap.put("ftlname", "bpbj.ftl");//bpbj.ftl 为集团用户导出子公司数据时所使用的模板
				wordMap.put("bpbjlist", bpbjList);
			}
		} else if (dataMap.get("qyid") != null ) {//子公司 导出自己公司的备品备件清单
			//备品备件信息
			List<Map<String, Object>> bpbjList = sBSSGLBpbjDao.findListByMap(dataMap);
			wordMap.put("qyname", bisQyjbxxDao.findInfoById(sessionuser.getQyid()).getM1());
			wordMap.put("ftlname", "bpbj.ftl");//bpbj.ftl 为子公司导出数据时所使用的模板
			wordMap.put("bpbjlist", bpbjList);
		}
		
		return wordMap;
	}*/
}
