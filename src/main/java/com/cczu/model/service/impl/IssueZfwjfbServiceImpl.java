package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.IIssueZfwjfbDao;
import com.cczu.model.entity.ISSUE_GovermentFileReleaseEntity;
import com.cczu.model.service.IIssueZfwjfbService;
import com.cczu.sys.comm.utils.wordToPDF;
import com.cczu.sys.system.entity.User;
/**
 * 政府文件发布service接口实现类
 * @author jason
 *
 */

@Service("IssueZfwjfbService")
public class IssueZfwjfbServiceImpl implements IIssueZfwjfbService {
	@Resource
	private IIssueZfwjfbDao zfwjfbDao;


	@Override
	public int addInfor(ISSUE_GovermentFileReleaseEntity sfr) {	
		return zfwjfbDao.addInfor(sfr);
		
	}

	@Override
	public List<ISSUE_GovermentFileReleaseEntity> findAlllist() {
		// TODO Auto-generated method stub
		return zfwjfbDao.findAlllist();
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=zfwjfbDao.dataGrid(mapData);
		int getTotalCount=zfwjfbDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public Map<String, Object> findAllZfList(Map<String, Object> mapData ) {
		List<Map<String, Object>> list = zfwjfbDao.findZflist(mapData);
		Map<String, Object> mapr = new HashMap<String, Object>();
		mapr.put("rows", list);
		return mapr;
	}
	
	@Override
	public int deleteInfo(long id) {
		return zfwjfbDao.deleteInfo(id);
	}

	@Override
	public ISSUE_GovermentFileReleaseEntity findInfoById(long id) {
		return zfwjfbDao.findInfoById(id);
	}

	@Override
	public int updateInfoByID(long id, ISSUE_GovermentFileReleaseEntity aqwj, String filePath) {
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
		return zfwjfbDao.updateInfoByID(id, aqwj);
	}

//	@Override
//	public Long addInforReturnID(ISSUE_GovermentFileReleaseEntity sfr,String filePath) {
//		// TODO Auto-generated method stub
//		return zfwjfbDao.addInforReturnID(sfr,filePath);
//	}
	
	@Override
	public Long addInforReturnID(ISSUE_GovermentFileReleaseEntity zfwj,String filePath) {
	if(zfwj.getM3()!=null&&zfwj.getM3()!=""){
		String[] fileurl = zfwj.getM3().split(",");
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
		zfwj.setM6(purl);
		zfwj.setM7(surl);
	}
		return zfwjfbDao.addInforReturnID(zfwj);
	}

	@Override
	public Map<String, Object> dataGrid2(Map<String, Object> mapData ) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list=zfwjfbDao.dataGrid2(mapData );
		int getTotalCount=zfwjfbDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public List<User> findAllUser(String ids) {
		// TODO Auto-generated method stub
		List<User> list= zfwjfbDao.findAllUser(ids);
		return list;
	}
	
	@Override
	public List<User> dataListE(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return zfwjfbDao.dataListE(map);
	}
}
