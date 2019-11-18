package com.cczu.model.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.impl.AqpxKcxxDaoImpl;
import com.cczu.model.entity.AQPX_CourseEntity;
import com.cczu.model.service.IAqpxKCxxService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.wordToPDF;

@Transactional(readOnly=true)
@Service("AqpxKcxxService")
public class AqpxKcxxServiceImpl implements IAqpxKCxxService {

	@Resource
	private AqpxKcxxDaoImpl aqpxkcxxDao;
	
	@Override
	public List<AQPX_CourseEntity> findByQyID(Long qyid) {
		// TODO Auto-generated method stub
		return aqpxkcxxDao.findAllInfoByQyID(qyid);
	}

	@Override
	public void addInfo(AQPX_CourseEntity ac) {
		// TODO Auto-generated method stub
		ac.setS1(DateUtils.getSystemTime());
		ac.setS2(DateUtils.getSystemTime());
		ac.setS3(0);
		aqpxkcxxDao.save(ac);
	}

	@Override
	/**
	 * 对上传的word，ppt文件转换并返回地址
	 * @param as
	 */
	public String uploadReturnName(String url,String filePath) {
		// TODO Auto-generated method stub
		String s[]=wordToPDF.getUN(url);//获取存放word地址和文件名的数组
		String x=wordToPDF.getWurl(url);
		String wurl=filePath+x;//获取word存放路径
//		String purl=s[0]+"/ps/"+s[1]+".pdf";//获得pdf数据库里存放的内容
		String surl=s[0]+"/"+s[1]+".swf";//获得swg数据库里存放的内容
		String purl2=filePath+s[0]+"/"+s[1]+".pdf";//设置pdf的存放地址
		String surl2=filePath+s[0]+"/"+s[1]+".swf";//设置swg的存放地址
		//将word转pdf
		wordToPDF.WordToPDF(wurl, purl2);
		wordToPDF.ConvertPdfToSwf(purl2,surl2);
		return surl;
	}
	
	@Override
	public void updateInfo(AQPX_CourseEntity ac) {
		// TODO Auto-generated method stub
		ac.setS2(DateUtils.getSystemTime());
		aqpxkcxxDao.saveUp(ac);
	}

	@Override
	public void deleteInfo(Long id) {
		// TODO Auto-generated method stub
		aqpxkcxxDao.deleteInfo(id);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<AQPX_CourseEntity> list = aqpxkcxxDao.dataGrid(mapData);
		int getTotalCount = aqpxkcxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public String content(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return aqpxkcxxDao.content(mapData);
	}

	@Override
	public List<AQPX_CourseEntity> getList(Map<String, Object> mapData){
		return aqpxkcxxDao.findByContent(mapData);
	}


	@Override
	public List<AQPX_CourseEntity> getkcid(Long kcid) {
		// TODO Auto-generated method stub
		return aqpxkcxxDao.getkcid(kcid);
	}

	@Override
	public AQPX_CourseEntity findbyid(Long id) {
		// TODO Auto-generated method stub
		return aqpxkcxxDao.findbyid(id);
	}

	@Override
	public AQPX_CourseEntity findygidandkcid( Long kcid) {
		// TODO Auto-generated method stub
		return aqpxkcxxDao.findygidandkcid( kcid);
	}

	@Override
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		// TODO Auto-generated method stub

		String[] title={"课程名称","课程学时","学分"};  
		String fileName="课程信息表.xls";
		List<Object> list=aqpxkcxxDao.getExcel(mapData);
		new ExportExcel(fileName, title,list, response);
		
	}

	@Override
	public AQPX_CourseEntity findid(Long id1, String m1) {
		// TODO Auto-generated method stub
		return aqpxkcxxDao.findid(id1, m1);
	}

	@Override
	public List<AQPX_CourseEntity> getlistByKcids(String kcids) {
		// TODO Auto-generated method stub
		return aqpxkcxxDao.getlistByKcids(kcids);
	}

	@Override
	public List<Map<String, Object>> getlistByKcids2(String kcids,String planname,Long planid) {
		// TODO Auto-generated method stub
		return aqpxkcxxDao.getlistByKcids2(kcids,planname,planid);
	}
	
	@Override
	public long addInforReturnID(AQPX_CourseEntity ac) {
		// TODO Auto-generated method stub
		 ac.setS1(DateUtils.getSystemTime());
		 ac.setS2(DateUtils.getSystemTime());
		 ac.setS3(0);
		 aqpxkcxxDao.save(ac);
		 return ac.getID();
	}

	@Override
	public List<Map<String, Object>> getKclist(Long qyid, Long bmid) {
		// TODO Auto-generated method stub
		return aqpxkcxxDao.getKclist(qyid, bmid);
	}

	@Override
	public String findCKByContent(Map<String, Object> mapData){
		// TODO Auto-generated method stub
		List<AQPX_CourseEntity> list= aqpxkcxxDao.findByContent(mapData);
		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
		for(AQPX_CourseEntity ck:list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", ck.getID());
			map.put("text", ck.getM1());
			arrylist.add(map);
		}
		return JsonMapper.getInstance().toJson(arrylist);
	}

}
