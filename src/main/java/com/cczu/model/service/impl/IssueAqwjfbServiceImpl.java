package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.IIssueAqwjfbDao;
import com.cczu.model.entity.ISSUE_SecurityFileReleaseEntity;
import com.cczu.model.service.IIssueAqwjfbService;
import com.cczu.sys.comm.utils.wordToPDF;
/**
 * 安全文件发布service接口实现类
 * @author jason
 *
 */

@Service("IssueAqwjfbService")
public class IssueAqwjfbServiceImpl implements IIssueAqwjfbService {
	@Resource
	private IIssueAqwjfbDao aqwjfbDao;


	@Override
	public int addInfor(ISSUE_SecurityFileReleaseEntity sfr) {	
		return aqwjfbDao.addInfor(sfr);
		
	}

	@Override
	public List<ISSUE_SecurityFileReleaseEntity> findAlllist() {
		// TODO Auto-generated method stub
		return aqwjfbDao.findAlllist();
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=aqwjfbDao.dataGrid(mapData);
		int getTotalCount=aqwjfbDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public int deleteInfo(long id) {
		return aqwjfbDao.deleteInfo(id);
	}

	@Override
	public ISSUE_SecurityFileReleaseEntity findInfoById(long id) {
		return aqwjfbDao.findInfoById(id);
	}

	@Override
	public int updateInfoByID(long id, ISSUE_SecurityFileReleaseEntity aqwj, String filePath) {
		// TODO Auto-generated method stu
		if(aqwj.getM3()!=null&&aqwj.getM3()!=""){
			String[] fileurl = aqwj.getM3().split(",");
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
			aqwj.setM6(purl);
			aqwj.setM7(surl);
		}
		return aqwjfbDao.updateInfoByID(id, aqwj);
	}

//	@Override
//	public Long addInforReturnID(ISSUE_SecurityFileReleaseEntity sfr) {
//		// TODO Auto-generated method stub
//		return aqwjfbDao.addInforReturnID(sfr);
//	}

	@Override
	public Map<String, Object> dataGrid2(Map<String, Object> mapData ) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list=aqwjfbDao.dataGrid2(mapData );
		int getTotalCount=aqwjfbDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public Long addInforReturnID(ISSUE_SecurityFileReleaseEntity aqwj,String filePath) {
	if(aqwj.getM3()!=null&&aqwj.getM3()!=""){
		String[] fileurl = aqwj.getM3().split(",");
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
		aqwj.setM6(purl);
		aqwj.setM7(surl);
	}
		return aqwjfbDao.addInforReturnID(aqwj);
	}
	
	
	/**
	 * 获取qyids字段
	 * @param id
	 */
	@Override
	public String getqyids(long id){
		return aqwjfbDao.getqyids(id);
	}
}
