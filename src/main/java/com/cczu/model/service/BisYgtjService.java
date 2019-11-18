package com.cczu.model.service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.BisYgtjxxDao;
import com.cczu.model.dao.ITZybysDao;
import com.cczu.model.entity.BIS_EmployeeTestEntity;
import com.cczu.model.entity.Tdic_BIS_OccupharmExamEntity;
import com.cczu.model.entity.dto.Tdic_NoteTreeDto;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.TreeUtils;


/**
 *  员工体检Service
 *
 */
@Transactional(readOnly=true)
@Service("BisYgtjService")
public class BisYgtjService {

	@Resource
	private BisYgtjxxDao bisYgtjxxDao;
	@Resource
	private ITZybysDao iTZybysDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=bisYgtjxxDao.dataGrid(mapData);
		int getTotalCount=bisYgtjxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 根据员工姓名查询记录数量
	 * @param mapData
	 * @return
	 */
	public int getCountByName(Map<String, Object> mapData) {
		
		return bisYgtjxxDao.getTotalCount(mapData);
	}
	
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		List<Map<String, Object>> list=bisYgtjxxDao.dataGrid2(mapData);
		int getTotalCount=bisYgtjxxDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//添加信息
	public void addInfo(BIS_EmployeeTestEntity ygtj) {
		Timestamp t=DateUtils.getSysTimestamp();
		ygtj.setS1(t);
		ygtj.setS2(t);
		ygtj.setS3(0);
		bisYgtjxxDao.save(ygtj);
	}
	
	//添加信息并返回id
	public long addInfoReturnID(BIS_EmployeeTestEntity ygtj) {
		Timestamp t=DateUtils.getSysTimestamp();
		ygtj.setS1(t);
		ygtj.setS2(t);
		ygtj.setS3(0);
		bisYgtjxxDao.save(ygtj);
		return ygtj.getID();
	}
	
	//更新信息
	public void updateInfo(BIS_EmployeeTestEntity ygtj) {
		Timestamp t=DateUtils.getSysTimestamp();
		ygtj.setS2(t);
		ygtj.setS3(0);
		bisYgtjxxDao.save(ygtj);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		bisYgtjxxDao.deleteInfo(id);
	}

	//根据id查找详细信息
	public BIS_EmployeeTestEntity findById(Long id) {
		return bisYgtjxxDao.find(id);
	}
	
	//获取企业list
	public List<Map<String, Object>> findQynmList(String xzqy,String type) {
		List<Map<String, Object>> qynmList = bisYgtjxxDao.findQynmList(xzqy,type);
		return qynmList;
	}
	
	//获取危害因素json数据
	public String gbtjson(){
		List<Tdic_BIS_OccupharmExamEntity> list = iTZybysDao.getAll();//获取全部数据
		int max = 0;
		for(int i = 0; i < list.size(); i++){
			Tdic_BIS_OccupharmExamEntity bis = list.get(i);
			if (max < bis.getID()) {
				max = Integer.parseInt(bis.getID()+"");
			} 
		}
		max += 1;
		List<Tdic_NoteTreeDto> list2=new ArrayList<Tdic_NoteTreeDto>();//子数据
		List<Tdic_NoteTreeDto> resultlist=new ArrayList<Tdic_NoteTreeDto>();//结果集
		Tdic_NoteTreeDto temp=new Tdic_NoteTreeDto();
		temp.setId("0");
		temp.setText(list.get(0).getM1());
		for (Tdic_BIS_OccupharmExamEntity infor : list) {
			Tdic_NoteTreeDto temp2=new Tdic_NoteTreeDto();
			if(infor.getM1().equals(temp.getText())){
				temp2.setId(infor.getID()+"");
				temp2.setText(infor.getM2());
				list2.add(temp2);
			}else{
				temp.setChildren(list2);
				resultlist.add(temp);
				temp=new Tdic_NoteTreeDto();
				temp.setId(max+"");
				temp.setText(infor.getM1());
				temp2=new Tdic_NoteTreeDto();
				list2=new  ArrayList<Tdic_NoteTreeDto>();
				temp2.setId(infor.getID()+"");
				temp2.setText(infor.getM2());
				list2.add(temp2);
				max++;	
			}
		}
		temp.setChildren(list2);
		resultlist.add(temp);
		String sssString=TreeUtils.commJsonTree(resultlist);
		return sssString;
	}
	
	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="员工体检信息表.xls";
		List<Map<String, Object>> list=bisYgtjxxDao.getExport(mapData);
		//格式化时间
		for (Map<String, Object> map : list) {
			Timestamp m3 = (Timestamp)map.get("m3");
			if (m3 != null) {
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				map.put("m3", format.format(m3));
			}
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
					BIS_EmployeeTestEntity ygtj = new BIS_EmployeeTestEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					ygtj.setS1(t);
					ygtj.setS2(t);
					ygtj.setS3(0);
					ygtj.setID1(Long.valueOf(map.get("qyid").toString()));
					ygtj.setM7(bis.get(0).toString());
					ygtj.setM1(bis.get(1).toString());
					ygtj.setM2(bis.get(2).toString());
					ygtj.setM3(DateUtils.getTimestampFromStr(bis.get(3).toString()));
					ygtj.setM4(bis.get(4).toString());
					ygtj.setM5(bis.get(5).toString());
					ygtj.setM6(bis.get(6).toString());
					bisYgtjxxDao.save(ygtj);
					result++;
				}catch(Exception e){
					error++;
				}
			}
			resultmap.put("success",result);
			resultmap.put("error", error);
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
}
