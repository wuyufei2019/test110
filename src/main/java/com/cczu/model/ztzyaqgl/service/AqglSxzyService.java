package com.cczu.model.ztzyaqgl.service;

import com.cczu.model.ztzyaqgl.dao.AqglAqcsDao;
import com.cczu.model.ztzyaqgl.dao.AqglAqzyAqcsDao;
import com.cczu.model.ztzyaqgl.dao.AqglSxzyDao;
import com.cczu.model.ztzyaqgl.dao.AqglSxzyFxDao;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_SxkjzyEntity;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_Zyaq_Aqcs;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.util.common.WordUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  安全管理-受限空间作业Service
 * @author zpc
 *
 */
@Service("ztAqglSxzyService")
public class AqglSxzyService {

	@Resource
	private AqglSxzyDao aqglSxzyDao;
	@Resource
	private AqglAqcsDao aqcsDao;
	@Resource
	private AqglSxzyFxDao aqglSxzyFxDao;
	@Resource
	private AqglAqzyAqcsDao aqglAqzyAqcsDao;
	
	//添加受限空间作业
	public void addInfo(ZTAQGL_SxkjzyEntity entity) {
		aqglSxzyDao.save(entity);
	}

	//特殊情况list
	public String tsqklist() {
		
		List<Map<String, Object>> list=aqglSxzyDao.tsqkList();
		return JsonMapper.getInstance().toJson(list);
	}
	
	//修改受限空间作业
	public void updateInfo(ZTAQGL_SxkjzyEntity entity) {
		Timestamp t= DateUtils.getSysTimestamp();
		entity.setS2(t);
		aqglSxzyDao.save(entity);
	}
	
	//查找受限空间作业
	public ZTAQGL_SxkjzyEntity find(long id) {
		return aqglSxzyDao.find(id);
	}

	//查找延期的受限作业数量
	public int finddelaycount(Map<String, Object> mapData) {
		return aqglSxzyDao.getTotalCount(mapData);
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {

		List<Map<String, Object>> list = aqglSxzyDao.dataGrid(mapData);
		int getTotalCount = aqglSxzyDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 根据id查找受限空间作业信息
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> findInforById(long id) {
		
		Map<String, Object> map=aqglSxzyDao.findInforById(id);
		return map;
	}
	
	/**
	 * 根据id查找受限空间作业信息2
	 * @param mapData
	 * @return
	 */
	public ZTAQGL_SxkjzyEntity findById(long id) {
		
		ZTAQGL_SxkjzyEntity sxzy=aqglSxzyDao.find(id);
		return sxzy;
	}

	//根据高处id删除
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		aqglSxzyDao.deleteInfo(id);
	}
	
	//安全措施datagrid
	public Map<String, Object> aqscdataGrid(Map<String, Object> mapData) {

		List<Map<String, Object>> list = aqglSxzyDao.aqcsList(mapData);
		int getTotalCount = aqglSxzyDao.aqcsCount();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//编制安全措施
	public void addAqcs(ZTAQGL_Zyaq_Aqcs entity) {
		aqglAqzyAqcsDao.save(entity);
	}
	
	//确认安全措施
	public void updateAqcs(ZTAQGL_Zyaq_Aqcs entity) {
		aqglAqzyAqcsDao.save(entity);
	}

	//查找安全措施
	public ZTAQGL_Zyaq_Aqcs findAqcs(Long id) {
		return aqglAqzyAqcsDao.find(id);
	}
	
	//根据id查找全部详情
	public Map<String,Object> findallById(long id) {
		return aqglSxzyDao.findallById(id);
	}

	//根据id查找全部详情
	public List<Map<String,Object>> findallWlfIds(String ids) {
		return aqglSxzyDao.findallWlfIds(ids);
	}
	
	//导出excel
	public void exportExcel(HttpServletResponse response,Map<String, Object> mapData) {
		String fileName="受限空间作业安全作业证表.xls";
		List<Map<String, Object>> list=aqglSxzyDao.getExport(mapData);
		String[] title={"企业名称","作业证编号","申请人","申请时间","申请单位","受限空间作业级别","受限空间方式","受限空间地点","受限空间时间起","受限空间时间止",
				"受限空间作业负责人","受限空间人","涉及的其他特殊作业","危害辨识","分析人","安全措施编制人","生产单位负责人","监火人","受限空间初审人","实施安全教育人","申请单位意见","申请单位负责人","确认时间",
				"安全管理部门意见","安全管理部门负责人","确认时间","受限空间审批人意见","受限空间审批人","审批时间","验收人","验收时间"};  
		String[] keys={"qyname","m1","sqr","s1","m2","m3","m4","m5","m6","m7","m8","m9","m10","m11","fxr","bzcsr","sqdwfzr","jhr","dhcsr","aqjyr","m22_1","sqdwfzr","m22_2",
				"m23_1","aqglr","m23_2","m24_1","dhspr","m24_2","ysr","m25_1"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		new ExportExcel(fileName, title, keys, list, response,true);
	}
	
	//根据id获得受限空间作业word表数据
	public Map<String, Object> getExportWord(long id,String webAddress){
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> mapret = aqglSxzyDao.findallById(id);
		
		map.put("m7", mapret.get("m7")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(mapret.get("m7").toString()), "yyyy年MM月dd日HH时mm分"));
		map.put("m8", mapret.get("m8")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(mapret.get("m8").toString()), "yyyy年MM月dd日HH时mm分"));
		map.put("m21_7", mapret.get("m21_7")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(mapret.get("m21_7").toString()), "yyyy年MM月dd日HH时mm分"));
		map.put("m16_2", mapret.get("m16_2")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(mapret.get("m16_2").toString()), "yyyy年MM月dd日HH时mm分"));
		map.put("m17_2", mapret.get("m17_2")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(mapret.get("m17_2").toString()), "yyyy年MM月dd日HH时mm分"));
		map.put("m18_2", mapret.get("m18_2")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(mapret.get("m18_2").toString()), "yyyy年MM月dd日HH时mm分"));
		map.put("m19_2", mapret.get("m19_2")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(mapret.get("m19_2").toString()), "yyyy年MM月dd日HH时mm分"));
		map.put("m20_2", mapret.get("m20_2")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(mapret.get("m20_2").toString()), "yyyy年MM月dd日HH时mm分"));
		map.put("m22", mapret.get("m22")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(mapret.get("m22").toString()), "yyyy年MM月dd日HH时mm分"));
		map.put("m23", mapret.get("m23")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(mapret.get("m23").toString()), "yyyy年MM月dd日HH时mm分"));

		String wlfname="";
		int num=1;
		List<Map<String, Object>> wlflist=aqglSxzyDao.findallWlfIds(mapret.get("m21_6").toString()+"0");
		for(Map<String, Object> wlf:wlflist){
			if(num!=wlflist.size())
				wlfname+=wlf.get("wlfname").toString()+",";
			else
				wlfname+=wlf.get("wlfname").toString();
			num++;
		}
		
		map.put("wlf", wlfname);
		map.put("bzr", mapret.get("bzr"));
		map.put("depname", mapret.get("depname"));
		map.put("m1", mapret.get("m1"));
		map.put("m2", mapret.get("m2"));
		map.put("m3", mapret.get("m3"));
		map.put("m4", mapret.get("m4"));
		map.put("m5", mapret.get("m5"));
		map.put("m6", mapret.get("m6"));
		map.put("m9", mapret.get("m9"));
		map.put("m10", mapret.get("m10"));
		map.put("m11", mapret.get("m11"));
		map.put("m12", mapret.get("m12"));
		map.put("m13", mapret.get("m13"));
		map.put("m14", mapret.get("m14"));
		map.put("m15", mapret.get("m15"));
		map.put("m16", mapret.get("m16"));
		map.put("m17", mapret.get("m17"));
		map.put("m18", mapret.get("m18"));
		map.put("m19", mapret.get("m19"));
		map.put("m20", mapret.get("m20"));
		
		Map<String,Object>  mapz= new HashMap<>();
		mapz.put("id1", id);
		mapz.put("m2", 2);
		List<Map<String, Object>> list = aqcsDao.bzaqcsAllList(mapz);
		for(int i = 0;i<list.size();i++){
			Map<String, Object> aqcs=list.get(i);
			//修改图片地址
			if(aqcs.get("m4")!=null&&!aqcs.get("m4").toString().equals("")){
				String[] url = aqcs.get("m4").toString().split("[||]");
				aqcs.put("m4", WordUtil.getImageStr(webAddress + url[0]));
			}else{
				aqcs.put("m4", null);
			}	
			list.set(i, aqcs);//替换对象
		}
		map.put("aqcslist", list);
		
		List<Map<String, Object>> list2 = aqglSxzyFxDao.findAllByid1(id);
		for(int i = 0;i<list2.size();i++){
			Map<String, Object> fx=list2.get(i);
			//修改图片地址
			if(fx.get("m8")!=null&&!fx.get("m8").toString().equals("")){
				String[] url = fx.get("m8").toString().split("[||]");
				fx.put("m8", WordUtil.getImageStr(webAddress + url[0]));
			}else{
				fx.put("m8", null);
			}	
			fx.put("m5", fx.get("m5")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(fx.get("m5").toString()), "yyyy年MM月dd日HH时mm分"));
			list2.set(i, fx);//替换对象
		}
		map.put("fxlist", list2);

		
		if(mapret.get("m21_2")!=null&&!mapret.get("m21_2").toString().equals("")){
			String[] url = mapret.get("m21_2").toString().split("[||]");
			map.put("img1", WordUtil.getImageStr(webAddress + url[0]));
		}else{
			map.put("img1", null);
		}

		if(mapret.get("m21_3")!=null&&!mapret.get("m21_3").toString().equals("")){
			String[] url = mapret.get("m21_3").toString().split("[||]");
			map.put("img2", WordUtil.getImageStr(webAddress + url[0]));
		}else{
			map.put("img2", null);
		}
		
		if(mapret.get("m16_1")!=null&&!mapret.get("m16_1").toString().equals("")){
			String[] url = mapret.get("m16_1").toString().split("[||]");
			map.put("img3", WordUtil.getImageStr(webAddress + url[0]));
		}else{
			map.put("img3", null);
		}
		
		if(mapret.get("m17_1")!=null&&!mapret.get("m17_1").toString().equals("")){
			String[] url = mapret.get("m17_1").toString().split("[||]");
			map.put("img4", WordUtil.getImageStr(webAddress + url[0]));
		}else{
			map.put("img4", null);
		}
		
		if(mapret.get("m19_1")!=null&&!mapret.get("m19_1").toString().equals("")){
			String[] url = mapret.get("m19_1").toString().split("[||]");
			map.put("img5", WordUtil.getImageStr(webAddress + url[0]));
		}else{
			map.put("img5", null);
		}
		
		if(mapret.get("m18_1")!=null&&!mapret.get("m18_1").toString().equals("")){
			String[] url = mapret.get("m18_1").toString().split("[||]");
			map.put("img6", WordUtil.getImageStr(webAddress + url[0]));
		}else{
			map.put("img6", null);
		}
		
		if(mapret.get("m20_1")!=null&&!mapret.get("m20_1").toString().equals("")){
			String[] url = mapret.get("m20_1").toString().split("[||]");
			map.put("img7", WordUtil.getImageStr(webAddress + url[0]));
		}else{
			map.put("img7", null);
		}
		
		if(mapret.get("m22_1")!=null&&!mapret.get("m22_1").toString().equals("")){
			String[] url = mapret.get("m22_1").toString().split("[||]");
			map.put("img8", WordUtil.getImageStr(webAddress + url[0]));
		}else{
			map.put("img8", null);
		}

		if(mapret.get("m23_1")!=null&&!mapret.get("m23_1").toString().equals("")){
			String[] url = mapret.get("m23_1").toString().split("[||]");
			map.put("img9", WordUtil.getImageStr(webAddress + url[0]));
		}else{
			map.put("img9", null);
		}
		return map;
	}

	/**
	 * 首页待审批受限空间作业数量统计
	 * @param mapData
	 * @return
	 */
	public int maincount(Map<String, Object> mapData) {
		int getTotalCount = aqglSxzyDao.getTotalCount(mapData);
		return getTotalCount;
	}
}
