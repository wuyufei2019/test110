package com.cczu.model.mbgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.mbgl.dao.TargetAqdtDao;
import com.cczu.model.mbgl.entity.Target_SecurityFileRelease;
import com.cczu.sys.comm.utils.wordToPDF;
/**
 * 目标管理-安全文化-安全动态
 * @author jason
 *
 */

@Service("TargetAqdtService")
public class TargetAqdtService {
	@Resource
	private TargetAqdtDao Dao;

	public int addInfor(Target_SecurityFileRelease entity) {	
		return Dao.addInfor(entity);
		
	}

	public List<Target_SecurityFileRelease> findAlllist() {
		// TODO Auto-generated method stub
		return Dao.findAlllist();
	}
	public List<Target_SecurityFileRelease> findTop4(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return Dao.findTop4(map);
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=Dao.dataGrid(mapData);
		int getTotalCount=Dao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	public int deleteInfo(long id) {
		return Dao.deleteInfo(id);
	}

	public Target_SecurityFileRelease findInfoById(long id) {
		return Dao.findInfoById(id);
	}

	public int updateInfoByID(long id, Target_SecurityFileRelease entity, String filePath) {
		// TODO Auto-generated method stu
		convertPdfSwg(entity,filePath);
		return Dao.updateInfoByID(id, entity);
	}
	
	public void convertPdfSwg(Target_SecurityFileRelease entity, String filePath){
		if(entity.getM3()!=null&&entity.getM3()!=""){
			String[] fileurl = entity.getM3().split(",");
			String purl="";
			String surl="";
			int filesize=fileurl.length;
			for(int i=0;i<filesize;i++){
				String url=fileurl[i];
				String s[]=wordToPDF.getUN(url);//获取存放word地址和文件名的数组
				String x=wordToPDF.getWurl(url);
				String wurl=filePath+x;//获取word存放路径
				if(i==0||i==(filesize-1)){
					purl+=s[0]+"/"+s[1]+".pdf";//获得pdf数据库里存放的内容
					surl+=s[0]+"/"+s[1]+".swf";//获得swg数据库里存放的内容
					if(i<filesize-1){
						purl=purl+",";
						surl=surl+",";
					}
				}else{
					purl+=s[0]+"/"+s[1]+".pdf,";//获得pdf数据库里存放的内容
					surl+=s[0]+"/"+s[1]+".swf,";//获得swg数据库里存放的内容
				}
				String purl2=filePath+s[0]+"/"+s[1]+".pdf";//设置pdf的存放地址
				String surl2=filePath+s[0]+"/"+s[1]+".swf";//设置swg的存放地址
				//将word转pdf
				wordToPDF.WordToPDF(wurl, purl2);
				wordToPDF.ConvertPdfToSwf(purl2,surl2);
			}
			entity.setM6(purl);
			entity.setM7(surl);
		}
	}

	public Map<String, Object> dataGrid2(Map<String, Object> mapData ) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list=Dao.dataGrid2(mapData );
		int getTotalCount=Dao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	 
	public Long addInforReturnID(Target_SecurityFileRelease entity,String filePath) {
		convertPdfSwg(entity,filePath);
		return Dao.addInforReturnID(entity);
	}
	
	public int updateViewCount(long id){
		//更新浏览次数
		 return Dao.updateViewCount(id);
	}
}
