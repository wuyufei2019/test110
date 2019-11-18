package com.cczu.model.ztzyaqgl.service;

import com.cczu.model.ztzyaqgl.dao.AqglAqcsDao;
import com.cczu.model.ztzyaqgl.dao.AqglAqzyAqcsDao;
import com.cczu.model.ztzyaqgl.dao.AqglMbcdzyDao;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_Mbcdzy;
import com.cczu.model.ztzyaqgl.entity.ZTAQGL_Zyaq_Aqcs;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.util.common.WordUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  安全管理-盲板抽堵作业Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("ztAqglMbcdService")
public class AqglMbcdService {
	
	@Resource
	private AqglMbcdzyDao mbcdzyDao;
	@Resource
	private AqglAqzyAqcsDao aqglAqzyAqcsDao;
	@Resource
	private AqglAqcsDao aqcsDao;
	
	//添加盲板抽堵作业
	public void addInfo(ZTAQGL_Mbcdzy entity) {
		mbcdzyDao.save(entity);
	}

	//修改盲板抽堵作业
	public void updateInfo(ZTAQGL_Mbcdzy entity) {
		Timestamp t= DateUtils.getSysTimestamp();
		entity.setS2(t);
		mbcdzyDao.save(entity);
	}
	
	//查找盲板抽堵作业
	public ZTAQGL_Mbcdzy find(long id) {
		return mbcdzyDao.find(id);
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {

		List<Map<String, Object>> list = mbcdzyDao.dataGrid(mapData);
		int getTotalCount = mbcdzyDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 根据id查找盲板抽堵作业信息
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> findInforById(long id) {
		
		Map<String, Object> map=mbcdzyDao.findInforById(id);
		return map;
	}
	
	/**
	 * 根据id查找盲板抽堵作业信息2
	 * @param mapData
	 * @return
	 */
	public ZTAQGL_Mbcdzy findById(long id) {
		
		ZTAQGL_Mbcdzy gczy=mbcdzyDao.find(id);
		return gczy;
	}

	//根据盲板抽堵id删除
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		mbcdzyDao.deleteInfo(id);
	}
	
	//安全措施datagrid
	public Map<String, Object> aqscdataGrid(Map<String, Object> mapData) {

		List<Map<String, Object>> list = mbcdzyDao.aqcsList(mapData);
		int getTotalCount = mbcdzyDao.aqcsCount();
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
		return mbcdzyDao.findallById(id);
	}

	//根据id查找全部详情
	public List<Map<String,Object>> findallWlfIds(String ids) {
		return mbcdzyDao.findallWlfIds(ids);
	}
	
	//导出excel
	public void exportExcel(HttpServletResponse response,Map<String, Object> mapData) {
		String fileName="盲板抽堵作业安全作业证表.xls";
		List<Map<String, Object>> list=mbcdzyDao.getExport(mapData);
		String[] title={"企业名称","作业证编号","申请人","申请时间","申请单位","盲板抽堵作业级别","盲板抽堵方式","盲板抽堵地点","盲板抽堵时间起","盲板抽堵时间止",
				"盲板抽堵作业负责人","盲板抽堵人","涉及的其他特殊作业","危害辨识","分析人","安全措施编制人","生产单位负责人","监火人","盲板抽堵初审人","实施安全教育人","申请单位意见","申请单位负责人","确认时间",
				"安全管理部门意见","安全管理部门负责人","确认时间","盲板抽堵审批人意见","盲板抽堵审批人","审批时间","验收人","验收时间"};  
		String[] keys={"qyname","m1","sqr","s1","m2","m3","m4","m5","m6","m7","m8","m9","m10","m11","fxr","bzcsr","sqdwfzr","jhr","dhcsr","aqjyr","m22_1","sqdwfzr","m22_2",
				"m23_1","aqglr","m23_2","m24_1","dhspr","m24_2","ysr","m25_1"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		new ExportExcel(fileName, title, keys, list, response,true);
	}
	
	//根据id获得盲板抽堵作业word表数据
	public Map<String, Object> getExportWord(long id,String webAddress){
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> mapret = mbcdzyDao.findallById(id);
		
		map.put("m11", mapret.get("m11")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(mapret.get("m11").toString()), "yyyy年MM月dd日HH时mm分"));
		map.put("m12", mapret.get("m12")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(mapret.get("m12").toString()), "yyyy年MM月dd日HH时mm分"));
		map.put("m29_7", mapret.get("m29_7")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(mapret.get("m29_7").toString()), "yyyy年MM月dd日HH时mm分"));
		map.put("m23_2", mapret.get("m23_2")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(mapret.get("m23_2").toString()), "yyyy年MM月dd日HH时mm分"));
		map.put("m24_2", mapret.get("m24_2")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(mapret.get("m24_2").toString()), "yyyy年MM月dd日HH时mm分"));
		map.put("m25_2", mapret.get("m25_2")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(mapret.get("m25_2").toString()), "yyyy年MM月dd日HH时mm分"));
		map.put("m26_2", mapret.get("m26_2")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(mapret.get("m26_2").toString()), "yyyy年MM月dd日HH时mm分"));
		map.put("m27_2", mapret.get("m27_2")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(mapret.get("m27_2").toString()), "yyyy年MM月dd日HH时mm分"));
		map.put("m28_2", mapret.get("m28_2")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(mapret.get("m28_2").toString()), "yyyy年MM月dd日HH时mm分"));
		map.put("m30", mapret.get("m30")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(mapret.get("m30").toString()), "yyyy年MM月dd日HH时mm分"));
		map.put("m31", mapret.get("m31")==null?"年  月  日  时  分": DateUtils.formatDate(Timestamp.valueOf(mapret.get("m31").toString()), "yyyy年MM月dd日HH时mm分"));

		String wlfname="";
		int num=1;
		List<Map<String, Object>> wlflist=mbcdzyDao.findallWlfIds(mapret.get("m29_6").toString()+"0");
		for(Map<String, Object> wlf:wlflist){
			if(num!=wlflist.size())
				wlfname+=wlf.get("wlfname").toString()+",";
			else
				wlfname+=wlf.get("wlfname").toString();
			num++;
		}		
		
		map.put("wlf", wlfname);
		map.put("depname", mapret.get("depname"));
		map.put("bzr", mapret.get("bzr"));
		map.put("m1", mapret.get("m1"));
		map.put("m2", mapret.get("m2"));
		map.put("m3", mapret.get("m3"));
		map.put("m4", mapret.get("m4"));
		map.put("m5", mapret.get("m5"));
		map.put("m6", mapret.get("m6"));
		map.put("m7", mapret.get("m7"));
		map.put("m8", mapret.get("m8"));
		map.put("m9", mapret.get("m9"));
		map.put("m10", mapret.get("m10"));
		map.put("m13", mapret.get("m13"));
		map.put("m14", mapret.get("m14"));
		map.put("m15", mapret.get("m15"));
		map.put("m16", mapret.get("m16"));
		map.put("m17", mapret.get("m17"));
		map.put("m18", mapret.get("m18"));
		map.put("m19", mapret.get("m19"));
		map.put("m20", mapret.get("m20"));
		map.put("m21", mapret.get("m21"));
		map.put("m21_1", mapret.get("m21_1"));
		map.put("m23", mapret.get("m23"));
		map.put("m24", mapret.get("m24"));
		map.put("m25", mapret.get("m25"));
		map.put("m26", mapret.get("m26"));
		map.put("m27", mapret.get("m27"));
		map.put("m28", mapret.get("m28"));
		
		Map<String,Object>  mapz= new HashMap<>();
		mapz.put("id1", id);
		mapz.put("m2", 6);
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

		if(mapret.get("m22")!=null&&!mapret.get("m22").toString().equals("")){
			String[] url = mapret.get("m22").toString().split("[||]");
			map.put("img1", WordUtil.getImageStr(webAddress + url[0]));
			
			File picture = new File(webAddress + url[0]);
            BufferedImage sourceImg;
			try {
				sourceImg = ImageIO.read(new FileInputStream(picture));
	            float width = sourceImg.getWidth()*2/3; //源图宽度
	            float height = sourceImg.getHeight()*2/3; // 源图高度
	            if(width>426){
	            	height = height/(width/426);
	            	width = 426;
	            }
	            map.put("width",width);
	            map.put("height",height);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}else{
			map.put("img1", null);
		}
		
		
		if(mapret.get("m29_2")!=null&&!mapret.get("m29_2").toString().equals("")){
			String[] url = mapret.get("m29_2").toString().split("[||]");
			map.put("img2", WordUtil.getImageStr(webAddress + url[0]));
		}else{
			map.put("img2", null);
		}
		
		if(mapret.get("m29_3")!=null&&!mapret.get("m29_3").toString().equals("")){
			String[] url = mapret.get("m29_3").toString().split("[||]");
			map.put("img3", WordUtil.getImageStr(webAddress + url[0]));
		}else{
			map.put("img3", null);
		}
		
		if(mapret.get("m23_1")!=null&&!mapret.get("m23_1").toString().equals("")){
			String[] url = mapret.get("m23_1").toString().split("[||]");
			map.put("img4", WordUtil.getImageStr(webAddress + url[0]));
		}else{
			map.put("img4", null);
		}

		if(mapret.get("m24_1")!=null&&!mapret.get("m24_1").toString().equals("")){
			String[] url = mapret.get("m24_1").toString().split("[||]");
			map.put("img5", WordUtil.getImageStr(webAddress + url[0]));
		}else{
			map.put("img5", null);
		}
		
		if(mapret.get("m25_1")!=null&&!mapret.get("m25_1").toString().equals("")){
			String[] url = mapret.get("m25_1").toString().split("[||]");
			map.put("img6", WordUtil.getImageStr(webAddress + url[0]));
		}else{
			map.put("img6", null);
		}
		
		if(mapret.get("m26_1")!=null&&!mapret.get("m26_1").toString().equals("")){
			String[] url = mapret.get("m26_1").toString().split("[||]");
			map.put("img7", WordUtil.getImageStr(webAddress + url[0]));
		}else{
			map.put("img7", null);
		}

		if(mapret.get("m27_1")!=null&&!mapret.get("m27_1").toString().equals("")){
			String[] url = mapret.get("m27_1").toString().split("[||]");
			map.put("img8", WordUtil.getImageStr(webAddress + url[0]));
		}else{
			map.put("img8", null);
		}

		if(mapret.get("m28_1")!=null&&!mapret.get("m28_1").toString().equals("")){
			String[] url = mapret.get("m28_1").toString().split("[||]");
			map.put("img9", WordUtil.getImageStr(webAddress + url[0]));
		}else{
			map.put("img9", null);
		}
		
		if(mapret.get("m30_1")!=null&&!mapret.get("m30_1").toString().equals("")){
			String[] url = mapret.get("m30_1").toString().split("[||]");
			map.put("img10", WordUtil.getImageStr(webAddress + url[0]));
		}else{
			map.put("img10", null);
		}
		
		if(mapret.get("m31_1")!=null&&!mapret.get("m31_1").toString().equals("")){
			String[] url = mapret.get("m31_1").toString().split("[||]");
			map.put("img11", WordUtil.getImageStr(webAddress + url[0]));
		}else{
			map.put("img11", null);
		}
		return map;
	}

	/**
	 * 首页待审批盲板抽堵作业数量统计
	 * @param mapData
	 * @return
	 */
	public int maincount(Map<String, Object> mapData) {
		int getTotalCount = mbcdzyDao.getTotalCount(mapData);
		return getTotalCount;
	}
}
