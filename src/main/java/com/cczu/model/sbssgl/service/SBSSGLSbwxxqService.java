package com.cczu.model.sbssgl.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.IBisQyjbxxDao;
import com.cczu.model.sbssgl.dao.SBSSGLSbwxxqDao;
import com.cczu.model.sbssgl.dao.SBSSGLSbwxxqZfsfzgscsDao;
import com.cczu.model.sbssgl.entity.SBSSGL_SBWXXQEntity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 设备设施管理-设备维修记录Service
 *
 */
@Service("SBSSGLSbwxjlService")
public class SBSSGLSbwxxqService {

	@Resource
	private SBSSGLSbwxxqDao sBSSGLSbwxxqDao;
	@Resource
	private IBisQyjbxxDao bisQyjbxxDao;
	@Resource
	private SBSSGLSbwxxqZfsfzgscsDao sBSSGLSbwxxqZfsfzgscsDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=sBSSGLSbwxxqDao.dataGrid(mapData);
		int getTotalCount=sBSSGLSbwxxqDao.getTotalCount(mapData);
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
		sBSSGLSbwxxqDao.deleteInfo(id);
	}
	
	/**
	 * 根据id查找备品备件信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findById(Long id) {
		return sBSSGLSbwxxqDao.findById(id);
	}
	
	public SBSSGL_SBWXXQEntity find(Long id) {
		return sBSSGLSbwxxqDao.find(id);
	}
	
	/**
	 * 添加备品备件信息
	 * @param entity
	 */
	public void addInfo(SBSSGL_SBWXXQEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		sBSSGLSbwxxqDao.save(entity);
	}

	/**
	 * 更新备品备件信息
	 * @param entity
	 */
	public void updateInfo(SBSSGL_SBWXXQEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);
		sBSSGLSbwxxqDao.save(entity);
	}
	
	/**
	 * 根据id获得设备维修word表数据
	 * @param id
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, Object> getWord(long id) throws ParseException{
		//设备维修信息
		Map<String,Object> sbwx = sBSSGLSbwxxqDao.findById(id);
		
		sbwx.put("m1", sbwx.get("m1")==null?"":DateUtils.formatDate(Timestamp.valueOf(sbwx.get("m1").toString()), "yyyy年MM月dd日"));
		sbwx.put("m3", sbwx.get("m3")==null?"":DateUtils.formatDate(Timestamp.valueOf(sbwx.get("m3").toString()), "HH时mm分"));
		if (sbwx.get("m10") != null && sbwx.get("m11") != null) {
			String m10Str = sbwx.get("m10").toString();
			String m11Str = sbwx.get("m11").toString();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			Date m10Date = format.parse(m10Str);//维修开始时间
			Date m11Date = format.parse(m11Str);//维修结束时间
			sbwx.put("m12", getDatePoor(m11Date, m10Date));
		}
		
		sbwx.put("m10", sbwx.get("m10")==null?"":DateUtils.formatDate(Timestamp.valueOf(sbwx.get("m10").toString()), "HH时mm分"));
		sbwx.put("m11", sbwx.get("m11")==null?"":DateUtils.formatDate(Timestamp.valueOf(sbwx.get("m11").toString()), "HH时mm分"));
		
		//勾选项
		sbwx.put("z1", "00A3"); sbwx.put("z2", "00A3"); sbwx.put("z3", "00A3");
		sbwx.put("z4", "00A3"); sbwx.put("z5", "00A3"); sbwx.put("z6", "00A3");
		sbwx.put("z7", "00A3"); sbwx.put("z8", "00A3"); sbwx.put("z9", "00A3");
		sbwx.put("z10", "00A3"); sbwx.put("z11", "00A3"); sbwx.put("z12", "00A3");
		sbwx.put("z13", "00A3"); sbwx.put("z14", "00A3"); sbwx.put("z15", "00A3");
		sbwx.put("z16", "00A3"); sbwx.put("z17", "00A3"); sbwx.put("z18", "00A3");
		sbwx.put("z19", "00A3"); sbwx.put("z20", "00A3"); sbwx.put("z21", "00A3");
		sbwx.put("z22", "00A3"); sbwx.put("z23", "00A3"); sbwx.put("z24", "00A3");
		
		if(sbwx.get("m4")!=null){
			String m4 = sbwx.get("m4").toString();
			if(m4.equals("0")){
				sbwx.put("z1", "0052");
			}else if(m4.equals("1")){
				sbwx.put("z2", "0052");
			}else if(m4.equals("2")){
				sbwx.put("z3", "0052");
			}else if(m4.equals("3")){
				sbwx.put("z4", "0052");
			}
		}
		
		if(sbwx.get("m6")!=null){
			String m6 = sbwx.get("m6").toString();
			if(m6.equals("0")){
				sbwx.put("z5", "0052");
			}else if(m6.equals("1")){
				sbwx.put("z6", "0052");
			}else if(m6.equals("2")){
				sbwx.put("z7", "0052");
			}else if(m6.equals("3")){
				sbwx.put("z8", "0052");
			}else if(m6.equals("4")){
				sbwx.put("z9", "0052");
			}else if(m6.equals("5")){
				sbwx.put("z10", "0052");
			}else if(m6.equals("6")){
				sbwx.put("z11", "0052");
			}else if(m6.equals("7")){
				sbwx.put("z12", "0052");
			}else if(m6.equals("8")){
				sbwx.put("z13", "0052");
			}else if(m6.equals("9")){
				sbwx.put("z14", "0052");
			}
			
			if(sbwx.get("m13")!=null){
				String m13 = sbwx.get("m13").toString();
				if(m13.equals("0")){
					sbwx.put("z15", "0052");
				}else if(m13.equals("1")){
					sbwx.put("z16", "0052");
				}
			}
			
			if(sbwx.get("m14")!=null){
				String m14 = sbwx.get("m14").toString();
				if(m14.equals("0")){
					sbwx.put("z17", "0052");
				}else if(m14.equals("1")){
					sbwx.put("z18", "0052");
				}
			}
			
			if(sbwx.get("m15")!=null){
				String m15 = sbwx.get("m15").toString();
				if(m15.equals("0")){
					sbwx.put("z19", "0052");
				}else if(m15.equals("1")){
					sbwx.put("z20", "0052");
				}
			}
			
			if(sbwx.get("m16")!=null){
				String m16 = sbwx.get("m16").toString();
				if(m16.equals("0")){
					sbwx.put("z21", "0052");
				}else if(m16.equals("1")){
					sbwx.put("z22", "0052");
				}
			}
			
			if(sbwx.get("m17")!=null){
				String m17 = sbwx.get("m17").toString();
				if(m17.equals("0")){
					sbwx.put("z23", "0052");
				}else if(m17.equals("1")){
					sbwx.put("z24", "0052");
				}
			}
		}
		
		//再发生防止改善措施list
		List<Map<String, Object>> cslist = sBSSGLSbwxxqZfsfzgscsDao.findByWxid(id);
		for (Map<String, Object> cs : cslist) {
			cs.put("m2", cs.get("m2")==null?"":DateUtils.formatDate(Timestamp.valueOf(cs.get("m2").toString()), "MM月dd日"));
			cs.put("m3", cs.get("m3")==null?"":DateUtils.formatDate(Timestamp.valueOf(cs.get("m3").toString()), "MM月dd日"));
		}
		sbwx.put("cslist", cslist);
		return sbwx;
	}
	
	public  String getDatePoor(Date endDate, Date nowDate) {
		 
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    // long ns = 1000;
	    // 获得两个时间的毫秒时间差异
	    long diff = endDate.getTime() - nowDate.getTime();
	    // 计算差多少天
	   /* long day = diff / nd;*/
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    // long sec = diff % nd % nh % nm / ns;
	    
	    return hour + "小时" + min + "分钟";
	}
}
