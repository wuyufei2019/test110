package com.cczu.model.zdgl.service;

import com.cczu.model.entity.dto.Tdic_NoteTreeDto;
import com.cczu.model.zdgl.dao.ZdglFlfgDao;
import com.cczu.model.zdgl.entity.ZDGL_FLFGEntity;
import com.cczu.model.zdgl.entity.ZDGL_LbflEntity;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  制度管理-法律法规库Service
 *
 */
@Service("ZdglFlfgService")
public class ZdglFlfgService {

	@Resource
	private ZdglFlfgDao zdglFlfgDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=zdglFlfgDao.dataGrid(mapData);
		int getTotalCount=zdglFlfgDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//删除信息
	public void deleteInfo(long id) {
		zdglFlfgDao.deleteInfo(id);
	}
	
	//根据id查找详细信息
	public Map<String,Object> findById(Long id) {
		return zdglFlfgDao.findById(id);
	}
	
	//添加信息
	public void addInfo(ZDGL_FLFGEntity flfg) {
		Timestamp t=DateUtils.getSysTimestamp();
		flfg.setS1(t);
		flfg.setS2(t);
		flfg.setS3(0);
		flfg.setM10(UserUtil.getCurrentUser().getId().toString());
		zdglFlfgDao.save(flfg);
	}

	public ZDGL_FLFGEntity find(Long id) {
		return zdglFlfgDao.find(id);
	}
	
	//更新信息
	public void updateInfo(ZDGL_FLFGEntity flfg) {
		Timestamp t=DateUtils.getSysTimestamp();
		flfg.setS2(t);
		zdglFlfgDao.save(flfg);
	}
	
	//导出
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="法律法规库表.xls";
		List<Map<String, Object>> list=zdglFlfgDao.getExport(mapData);
		/*for (Map<String, Object> map : list) {
			String m8 = map.get("m8").toString();
			m8 = (request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+m8);
			map.put("m2", m8);
		}*/
		String[] title={"大类别","小类别","法律法规名称","颁布单位","文件编号","发布日期","实施日期","摘要","录入人员","录入时间"};
		String[] keys={"m1_1","lb","m2","m3","m4","m5","m6","m7","lrname","s1"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		new ExportExcel(fileName, title, keys, list, response,true, "m2", "m8", request);
	}

	//导出
	public void exportExcel2(HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="法律法规库表.xls";
		List<Map<String, Object>> list=zdglFlfgDao.getExport(mapData);
		/*for (Map<String, Object> map : list) {
			String m8 = map.get("m8").toString();
			m8 = (request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+m8);
			map.put("m2", m8);
		}*/
		String[] title={"大类别","法律法规名称","颁布单位","文件编号","发布日期","实施日期","摘要","录入人员","录入时间"};
		String[] keys={"m1_1","m2","m3","m4","m5","m6","m7","lrname","s1"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		new ExportExcel(fileName, title, keys, list, response,true, "m2", "m8", request);
	}

	/**
	 * 根据类别获取法律法规名称
	 * @param mapData
	 * @return
	 */
	public String findbym1(Map<String, Object> mapData){
		List<Map<String, Object>> list= zdglFlfgDao.findbym1(mapData);
		return JsonMapper.getInstance().toJson(list);
	}
	
	/**
	 * 根据id获取法律法规名称
	 * @param id
	 * @return
	 */
	public String findbyidjson(String id){
		return JsonMapper.getInstance().toJson(zdglFlfgDao.findById(Long.parseLong(id)));
	}
	
	/**
	 * 获取所有菜单
	 * @return 菜单集合
	 */
	public List<Tdic_NoteTreeDto> getLbfl(Long qyid) {
		List<ZDGL_LbflEntity> list=zdglFlfgDao.findLbfl(qyid);
		
		List<Tdic_NoteTreeDto> list2=new ArrayList<Tdic_NoteTreeDto>();
		if(list.size()>0){
			for(ZDGL_LbflEntity lbfl : list){
				Tdic_NoteTreeDto dto=new Tdic_NoteTreeDto();
				dto.setId(lbfl.getID().toString());
				dto.setPid(lbfl.getPid().toString());
				dto.setText(lbfl.getM1());
				list2.add(dto);
			}
		}
		
		List<Tdic_NoteTreeDto> nodeList = new ArrayList<Tdic_NoteTreeDto>();  
		for(Tdic_NoteTreeDto dto1 : list2){  
		    boolean mark = false;
		    for(Tdic_NoteTreeDto dto2 : list2){  
		        if(dto1.getPid().equals(dto2.getId())){  
		            mark = true;  
		            if(dto2.getChildren() == null)  
		            	dto2.setChildren(new ArrayList<Tdic_NoteTreeDto>());  
		            dto2.getChildren().add(dto1);   
		            break;  
		        }  
		    }  
		    if(!mark){  
		        nodeList.add(dto1);   
		    }  
		}  
		return nodeList;
	}
	
	//添加类别信息
	public String addLbflInfo(ZDGL_LbflEntity lbfl) {
		String datasuccess = "success";
		lbfl.setID1(UserUtil.getCurrentUser().getId2());
		zdglFlfgDao.saveLbfl(lbfl);
		return datasuccess;
	}

	//添加类别信息
	public Long addLbflInfo2(ZDGL_LbflEntity lbfl) {
		String datasuccess = "success";
		lbfl.setID1(UserUtil.getCurrentUser().getId2());
		zdglFlfgDao.saveLbfl(lbfl);
		return lbfl.getID();
	}
	
	//根据id查询详细信息
	public Map<String, Object> findInforById(Long id) {
		return zdglFlfgDao.findInforById(id);
	}
	
	//更新信息
	public String updateInfo(ZDGL_LbflEntity entity) {
		String datasuccess = "success";
		zdglFlfgDao.saveLbfl(entity);
		return datasuccess;
	}
	
	//删除信息
	public void deleteLbInfo(long id) {
		zdglFlfgDao.deleteLbInfo(id);
	}
	
	//导入
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
			resultmap.put("success",result);
			for (List<Object> bis : list) {
				if(i<=2){ //跳过前三行
					i++;
					continue;
				}
				if(StringUtils.isBlank(bis.get(0).toString()))//判断是否为空的一行
					continue;
				try{
					ZDGL_FLFGEntity entity = new ZDGL_FLFGEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					entity.setS1(t);
					entity.setS2(t);
					entity.setS3(0);
					if(map.get("type").toString().equals("1"))
						entity.setID1(Long.valueOf(map.get("qyid").toString()));
					else
						entity.setID1(null);
					entity.setM10(UserUtil.getCurrentUser().getId().toString());
					entity.setM2(bis.get(0).toString());//法律法规名称
					//大类别
					String type=bis.get(1).toString();
					if(type.equals("法律")){
						entity.setM1_1("1");
					}else if(type.equals("法规")){
						entity.setM1_1("2");
					}else if(type.equals("规章")){
						entity.setM1_1("3");
					}else if(type.equals("地方性法规")){
						entity.setM1_1("4");
					}else if(type.equals("政府文件")){
						entity.setM1_1("5");
					}else if(type.equals("标准规范")){
						entity.setM1_1("6");
					}else if(type.equals("其他")){
						entity.setM1_1("7");
					}else if(type.equals("国际公约")){
						entity.setM1_1("8");
					}
					
					//判断小类别是否存在
					List<ZDGL_LbflEntity> lblist=zdglFlfgDao.findByName(bis.get(2).toString(),Long.valueOf(map.get("qyid").toString()));
					if(lblist.size()==0){
						error++;
						continue;
					}else{
						ZDGL_LbflEntity lb = lblist.get(0);
						entity.setM1(String.valueOf(lb.getID()));
					}
					
					entity.setM3(bis.get(3).toString());//颁布单位
					entity.setM4(bis.get(4).toString());//编号
					
					if(!bis.get(5).toString().equals("") && bis.get(5).toString() != null) {
						entity.setM5(DateUtils.getTimestampFromStr(bis.get(5).toString()));//发布日期
					}
					if(!bis.get(6).toString().equals("") && bis.get(6).toString() != null) {
						entity.setM6(DateUtils.getTimestampFromStr(bis.get(6).toString()));//实施日期
					}
					entity.setM7(bis.get(7).toString());//实施日期
					
					zdglFlfgDao.save(entity);
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

	//导入
	public Map<String,Object> exinExcel2(Map<String, Object> map) {
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
			resultmap.put("success",result);
			for (List<Object> bis : list) {
				if(i<=2){ //跳过前三行
					i++;
					continue;
				}
				if(StringUtils.isBlank(bis.get(0).toString()))//判断是否为空的一行
					continue;
				try{
					ZDGL_FLFGEntity entity = new ZDGL_FLFGEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					entity.setS1(t);
					entity.setS2(t);
					entity.setS3(0);
					if(map.get("type").toString().equals("1"))
						entity.setID1(Long.valueOf(map.get("qyid").toString()));
					else
						entity.setID1(null);
					entity.setM10(UserUtil.getCurrentUser().getId().toString());
					entity.setM2(bis.get(0).toString());//法律法规名称
					//大类别
					String type=bis.get(1).toString();
					if(type.equals("法律")){
						entity.setM1_1("1");
					}else if(type.equals("法规")){
						entity.setM1_1("2");
					}else if(type.equals("规章")){
						entity.setM1_1("3");
					}else if(type.equals("地方性法规")){
						entity.setM1_1("4");
					}else if(type.equals("政府文件")){
						entity.setM1_1("5");
					}else if(type.equals("标准规范")){
						entity.setM1_1("6");
					}else if(type.equals("其他")){
						entity.setM1_1("7");
					}else if(type.equals("国际公约")){
						entity.setM1_1("8");
					}

					entity.setM3(bis.get(2).toString());//颁布单位
					entity.setM4(bis.get(3).toString());//编号

					if(!bis.get(4).toString().equals("") && bis.get(4).toString() != null) {
						entity.setM5(DateUtils.getTimestampFromStr(bis.get(4).toString()));//发布日期
					}
					if(!bis.get(5).toString().equals("") && bis.get(5).toString() != null) {
						entity.setM6(DateUtils.getTimestampFromStr(bis.get(5).toString()));//实施日期
					}
					entity.setM7(bis.get(6).toString());//实施日期

					zdglFlfgDao.save(entity);
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
	 * 根据企业id和类别名称查询结果集
	 * @param m1
	 * @param qyid
	 * @return
	 */
	public List<ZDGL_LbflEntity> getLblistByName(String m1, Long qyid){
		return zdglFlfgDao.findByName(m1, qyid);
	}
}
