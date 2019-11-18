package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.ISekbAqscjsbzDao;
import com.cczu.model.entity.TMESK_TechnologystandardEntity;
import com.cczu.model.service.ISekbAqscjsbzService;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.wordToPDF;

@Transactional(readOnly=true)
@Service("SekbAqscjsbzService")
public class SekbAqscjsbzServiceImpl implements ISekbAqscjsbzService {
	
	@Resource
	private ISekbAqscjsbzDao sekbAqscjsbzDao;

	@Override
	public TMESK_TechnologystandardEntity findAll() {
		return sekbAqscjsbzDao.findAllInfo();
	}

	@Override
	public void addInfo(TMESK_TechnologystandardEntity sekb ,String filePath) {
		if(sekb.getM4()!=null&&sekb.getM4()!=""){
			String url=sekb.getM4();
			String s[]=wordToPDF.getUN(url);//获取存放word地址和文件名的数组
			String x=wordToPDF.getWurl(url);
			String wurl=filePath+x;//获取word存放路径
			String purl=s[0]+"/"+s[1]+".pdf";//获得pdf数据库里存放的内容
			String surl=s[0]+"/"+s[1]+".swf";//获得swg数据库里存放的内容
			String purl2=filePath+s[0]+"/"+s[1]+".pdf";//设置pdf的存放地址
			String surl2=filePath+s[0]+"/"+s[1]+".swf";//设置swg的存放地址
			//将word转pdf
			wordToPDF.WordToPDF(wurl, purl2);
			wordToPDF.ConvertPdfToSwf(purl2,surl2);
			sekb.setM5(purl);
			sekb.setM6(surl);
		}
		sekbAqscjsbzDao.addInfo(sekb);
		
	}

	@Override
	public void updateInfo(TMESK_TechnologystandardEntity sekb ,String filePath) {
		if(sekb.getM4()!=null&&sekb.getM4()!=""){
			String url=sekb.getM4();
			String s[]=wordToPDF.getUN(url);//获取存放word地址和文件名的数组
			String x=wordToPDF.getWurl(url);
			String wurl=filePath+x;//获取word存放路径
			String purl=s[0]+"/"+s[1]+".pdf";//获得pdf数据库里存放的内容
			String surl=s[0]+"/"+s[1]+".swf";//获得swg数据库里存放的内容
			String purl2=filePath+s[0]+"/"+s[1]+".pdf";//设置pdf的存放地址
			String surl2=filePath+s[0]+"/"+s[1]+".swf";//设置swg的存放地址
			//将word转pdf
			wordToPDF.WordToPDF(wurl, purl2);
			wordToPDF.ConvertPdfToSwf(purl2,surl2);
			sekb.setM5(purl);
			sekb.setM6(surl);
		}
		sekbAqscjsbzDao.updateInfo(sekb);
	}

	@Override
	public Page<TMESK_TechnologystandardEntity> search(Page<TMESK_TechnologystandardEntity> page,
			List<PropertyFilter> filters) {
		return null;
	}

	
	
	@Override
	public String content(Map<String, Object> mapData) {
		return sekbAqscjsbzDao.content(mapData);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<TMESK_TechnologystandardEntity> list=sekbAqscjsbzDao.dataGrid(mapData);
		int getTotalCount=sekbAqscjsbzDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		sekbAqscjsbzDao.deleteInfo(id);
	}

	@Override
	public TMESK_TechnologystandardEntity findById(Long id) {
		return sekbAqscjsbzDao.findById(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"标题","正文","备注"}; 
		String[] keys={"m1","m2","m3"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		String fileName="安全生产技术标准.xls";
		List<Map<String, Object>> list=sekbAqscjsbzDao.getExcel(mapData);
		new ExportExcel(fileName, title, keys, list, response);
	}
}
