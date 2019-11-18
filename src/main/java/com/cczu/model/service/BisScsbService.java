package com.cczu.model.service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.BisScsbDao;
import com.cczu.model.entity.BIS_ProductionFacility;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;

/**
 *  公用工程Service
 *
 */
@Transactional(readOnly=true)
@Service("BisScsbService")
public class BisScsbService {

	@Resource
	private BisScsbDao bisScsbDao;

	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {

		List<Map<String,Object>> list=bisScsbDao.dataGrid(mapData);
		int getTotalCount=bisScsbDao.getTotalCount(mapData);
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

		List<Map<String,Object>> list=bisScsbDao.dataGrid2(mapData);
		int getTotalCount=bisScsbDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	//添加信息
	public void addInfo(BIS_ProductionFacility scsb) {
		Timestamp t=DateUtils.getSysTimestamp();
		scsb.setS1(t);
		scsb.setS2(t);
		scsb.setS3(0);
		bisScsbDao.save(scsb);
	}

	public long addInfoReturnID(BIS_ProductionFacility scsb) {
		Timestamp t=DateUtils.getSysTimestamp();
		scsb.setS1(t);
		scsb.setS2(t);
		scsb.setS3(0);
		bisScsbDao.save(scsb);
		return scsb.getID();
	}

	//更新信息
	public void updateInfo(BIS_ProductionFacility scsb) {
		Timestamp t=DateUtils.getSysTimestamp();
		scsb.setS2(t);
		scsb.setS3(0);
		bisScsbDao.save(scsb);
	}

	//删除信息
	public void deleteInfo(long id) {
		bisScsbDao.deleteInfo(id);
	}

	//根据id查找详细信息
	public BIS_ProductionFacility findById(Long id) {
		return bisScsbDao.find(id);
	}

	//获取设备类别list
	public List<Map<String, Object>> findSblbList() {
		List<Map<String, Object>> sblbList = bisScsbDao.findSblbList();
		return sblbList;
	}

	//获取设备名称list
	public List<Map<String, Object>> findSbmcList(String sblb) {
		List<Map<String, Object>> sbmcList = bisScsbDao.findSbmcList(sblb);
		return sbmcList;
	}

	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="生产设备信息表.xls";
		List<Map<String, Object>> list=bisScsbDao.getExport(mapData);
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

	//导入
	public Map<String,Object> exinExcel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String,Object> resultmap = new HashMap<String, Object>();
		int result = 0;
		ExinExcel exin = new ExinExcel();
		List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
		int i = 0, error = 0;
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
					BIS_ProductionFacility scsb = new BIS_ProductionFacility();
					Timestamp t = DateUtils.getSysTimestamp();
					scsb.setS1(t);
					scsb.setS2(t);
					scsb.setS3(0);
					scsb.setID1(Long.valueOf(map.get("qyid").toString()));
					scsb.setM1(bis.get(0).toString());//设备位号
					scsb.setM2(bis.get(1).toString());//设备类别
					scsb.setM3(bis.get(2).toString());//设备名称
					scsb.setM4(bis.get(3).toString());//规格尺寸
					scsb.setM5(bis.get(4).toString());//型号
					scsb.setM14(bis.get(5).toString());//功率
					if(bis.get(6).toString()!=null&&!bis.get(6).toString().equals(""))
						scsb.setM15(DateUtils.getTimestampFromStr(bis.get(6).toString()));//出厂日期
					scsb.setM16(bis.get(7).toString());//设备状况
					List<Map<String, Object>> list2 = bisScsbDao.findByDepartment(bis.get(8).toString(), map.get("qyid").toString());
					if(list2.size()>0){
						Long departId = Long.valueOf(String.valueOf(list2.get(0).get("id")));
						scsb.setM17(departId);//使用单位
					}
					scsb.setM18(bis.get(9).toString());//主要技术参数
					scsb.setM6(bis.get(10).toString());//数量
					scsb.setM9(bis.get(13).toString());//介质
					if(bis.get(12).toString()!=null&&!bis.get(12).toString().equals(""))
						scsb.setM8(DateUtils.getTimestampFromStr(bis.get(12).toString()));//投用日期
					if(bis.get(14).toString()!=null&&!bis.get(14).toString().equals(""))
						scsb.setM10(DateUtils.getTimestampFromStr(bis.get(14).toString()));//下次检验日期
					scsb.setM7(bis.get(11).toString());//制造单位
					scsb.setM11(bis.get(15).toString());//责任人
					scsb.setM12(bis.get(16).toString());//联系电话
					scsb.setM13(bis.get(17).toString());//主要危险性

					bisScsbDao.save(scsb);
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
	 * @param xzqy
	 * @return
	 */
	public Map<String, Object> statistics(Map<String,Object> map){
	    List<Integer> list = new ArrayList<>();
	    Map<String,Object> remap=new HashMap<String,Object>();
	    remap.put("sblb", bisScsbDao.statistics(map));//设备类型数据
	    //获取投用日期数据
		for(int i=1;i<5;i++){
			map.put("m8", i+"");
			list.add(bisScsbDao.getTotalCount2(map));
			map.remove("m8");
		}
		remap.put("tyrq", list);
		return remap;

	}
	
}
