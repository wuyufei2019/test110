package com.cczu.model.jtjcsj.service;

import com.cczu.model.jtjcsj.dao.JtjcsjSbxxDao;
import com.cczu.model.jtjcsj.entity.Jtjcsj_SbxxEntity;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.*;

/**
 *  静态基础数据-设备信息Service
 *
 */
@Transactional(readOnly=true)
@Service("JtjcsjSbxxService")
public class JtjcsjSbxxService {

	@Resource
	private JtjcsjSbxxDao jtjcsjSbxxDao;
	
	/**
	 * tab页分页显示111
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=jtjcsjSbxxDao.dataGrid(mapData);
		int getTotalCount=jtjcsjSbxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=jtjcsjSbxxDao.dataGrid2(mapData);
		int getTotalCount=jtjcsjSbxxDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//添加信息
	public void addInfo(Jtjcsj_SbxxEntity scsb) {
		Timestamp t=DateUtils.getSysTimestamp();
		scsb.setS1(t);
		scsb.setS2(t);
		scsb.setS3(0);
		jtjcsjSbxxDao.save(scsb);
	}
	
	public long addInfoReturnID(Jtjcsj_SbxxEntity scsb) {
		Timestamp t=DateUtils.getSysTimestamp();
		scsb.setS1(t);
		scsb.setS2(t);
		scsb.setS3(0);
		jtjcsjSbxxDao.save(scsb);
		return scsb.getID();
	}
	
	//更新信息
	public void updateInfo(Jtjcsj_SbxxEntity scsb) {
		Timestamp t=DateUtils.getSysTimestamp();
		scsb.setS2(t);
		scsb.setS3(0);
		jtjcsjSbxxDao.save(scsb);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		jtjcsjSbxxDao.deleteInfo(id);
	}

	//根据id查找详细信息
	public Jtjcsj_SbxxEntity findById(Long id) {
		return jtjcsjSbxxDao.find(id);
	}
	
	//获取设备类别list
	public List<Map<String, Object>> findSblbList() {
		List<Map<String, Object>> sblbList = jtjcsjSbxxDao.findSblbList();
		return sblbList;
	}
	
	//获取设备名称list
	public List<Map<String, Object>> findSbmcList(String sblb) {
		List<Map<String, Object>> sbmcList = jtjcsjSbxxDao.findSbmcList(sblb);
		return sbmcList;
	}
	
	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="生产设备信息表.xls";
		List<Map<String, Object>> list=jtjcsjSbxxDao.getExport(mapData);
		//格式化时间，让其不显示微秒
		for (Map<String, Object> map : list) {
			Timestamp m8 = (Timestamp)map.get("m8");
			Timestamp m10 = (Timestamp)map.get("m10");
			Timestamp m15 = (Timestamp) map.get("m15");
			if (m8 != null && !"".equals(m8)) {
				String format_m8 = DateUtils.formatDateTime(new Date(m8.getTime()));
				map.put("m8", format_m8);
			}
			if (m10 != null && !"".equals(m10)) {
				String format_m10 = DateUtils.formatDateTime(new Date(m10.getTime()));
				map.put("m10", format_m10);
			}
			if (m15 != null && !"".equals(m15)) {
				String format_m15 = DateUtils.formatDateTime(new Date(m15.getTime()));
				map.put("m15", format_m15);
			}
			map.put("m17", map.get("department"));
		}
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response, true);
	}

	public Map<String,Object> exinExcel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String,Object> resultmap = new HashMap<String, Object>();
		int result = 0;
		ExinExcel exin = new ExinExcel();
		List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
		int i = 0,  error = 0;
		if (list.size() > 3) {
			result = 0;
			resultmap.put("total", list.size()-3);
			resultmap.put("returncode", "success");
			for (List<Object> bis : list) {
				if(i<=2){ //跳过前三行
					i++;
					continue;
				}

				try{
					Jtjcsj_SbxxEntity cg = new Jtjcsj_SbxxEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					cg.setS1(t);
					cg.setS2(t);
					cg.setS3(0);
					cg.setID1(Long.valueOf(map.get("qyid").toString()));
					cg.setEquipcode(bis.get(0).toString());
					cg.setM3(bis.get(1).toString());
					cg.setHazardcode(bis.get(2).toString());
					if(bis.get(3)!=null&&bis.get(3).toString()!=""){
						String m3=bis.get(3).toString();
						switch (m3) {
							case "罐":cg.setEquiptype("G0");break;
							case "气体检测仪":cg.setEquiptype("Q0");break;
							case "生产装置":cg.setEquiptype("S0");break;
							case "仓库":cg.setEquiptype("C0");break;
							default:
								break;
						}
					}
					cg.setEquipdescribe(bis.get(4).toString());
					cg.setM9(bis.get(5).toString());
					if(bis.get(6)!=null&&bis.get(6).toString()!=""){
						String m6=bis.get(6).toString();
						switch (m6) {
							case "停用":cg.setEquipstatus("0");break;
							case "在用":cg.setEquipstatus("1");break;
							default:
								break;
						}
					}
					cg.setInstallloc(bis.get(7).toString());
					cg.setLongitude(Float.parseFloat(bis.get(8).toString()));
					cg.setLatitude(Float.parseFloat(bis.get(9).toString()));
					jtjcsjSbxxDao.save(cg);
					result++;
				}catch(Exception e){
					error++;
				}
				resultmap.put("success",result);
				resultmap.put("error", error);
			}
		}else if(list.size()==3){
			resultmap.put("success",result);
			resultmap.put("error", error);
			resultmap.put("returncode", "warn");
		}else if(list.size()<3){
			resultmap.put("success",result);
			resultmap.put("error", error);
			resultmap.put("returncode", "ext");
		}
		if(Integer.valueOf(resultmap.get("success").toString())==0){
			resultmap.put("returncode", "warn");
		}
		return resultmap;

	}

	/**
	 * 统计分析
	 * @param map
	 * @return
	 */
	public Map<String, Object> statistics(Map<String,Object> map){
	    List<Integer> list = new ArrayList<>();
	    Map<String,Object> remap=new HashMap<String,Object>();
	    remap.put("sblb", jtjcsjSbxxDao.statistics(map));//设备类型数据
	    //获取投用日期数据
		for(int i=1;i<5;i++){
			map.put("m8", i+"");
			list.add(jtjcsjSbxxDao.getTotalCount2(map));
			map.remove("m8");
		}
		remap.put("tyrq", list);
		return remap;
		
	}
	
	/**
	 * 设备编码json
	 * @return
	 */
	public String findEquipCodeList() {
		Long qyid = UserUtil.getCurrentShiroUser().getQyid();
		List<Map<String, Object>> list =jtjcsjSbxxDao.findEquipCodeList(qyid);
		return JsonMapper.getInstance().toJson(list);
	}
	
	/**
	 * 查询最新的流水号
	 * @param qyid
	 * @return
	 */
	public String findWaterCode(Long qyid) {
		return jtjcsjSbxxDao.findWaterCode(qyid);
	}
	
	/**
	 * 根据id查询详细信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findInfoById(Long id) {
		return jtjcsjSbxxDao.findInfoById(id);
	}
	
}
