package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.ISekbAqscfgDao;
import com.cczu.model.entity.TMESK_LawsEntity;
import com.cczu.model.service.ISekbAqscfgService;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.Global;
import com.cczu.sys.comm.utils.wordToPDF;


@Transactional(readOnly=true)
@Service("SekbAqscfgService")
public class SekbAqscfgServiceImpl implements ISekbAqscfgService {
	
	@Resource
	private ISekbAqscfgDao sekbAqscfgDao;

	@Override
	public TMESK_LawsEntity findAll() {
		return sekbAqscfgDao.findAllInfo();
	}

	@Override
	public void addInfo(TMESK_LawsEntity sekb,String filePath) {
	if(sekb.getM5()!=null&&sekb.getM5()!=""){
		String url=sekb.getM5();
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
		sekb.setM6(purl);
		sekb.setM7(surl);
	}
		sekbAqscfgDao.addInfo(sekb);
		
	}

	@Override
	public void updateInfo(TMESK_LawsEntity sekb,String filePath) {
	if(sekb.getM5()!=null&&sekb.getM5()!=""){
		String url=sekb.getM5();
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
		sekb.setM6(purl);
		sekb.setM7(surl);
	}
		sekbAqscfgDao.updateInfo(sekb);
	}
	
	@Override
	public void addQrcode(TMESK_LawsEntity sekb) {
		sekb.setQrcode(Global.getH5url()+"/know/detail.do?id="+sekb.getID()+"&type="+sekb.getM1()+"&ylxs=1");
		sekbAqscfgDao.addInfo(sekb);
	}

	@Override
	public Page<TMESK_LawsEntity> search(Page<TMESK_LawsEntity> page,
			List<PropertyFilter> filters) {
		return null;
	}

	
	
	@Override
	public String content(Map<String, Object> mapData) {
		return sekbAqscfgDao.content(mapData);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<TMESK_LawsEntity> list=sekbAqscfgDao.dataGrid(mapData);
		int getTotalCount=sekbAqscfgDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		sekbAqscfgDao.deleteInfo(id);
	}

	@Override
	public TMESK_LawsEntity findById(Long id) {
		return sekbAqscfgDao.findById(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"标题","正文","备注"};
		String[] keys={"m2","m3","m4"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		String fileName="安全生产法律法规.xls";
		List<Map<String, Object>> list=sekbAqscfgDao.getExcel(mapData);
		new ExportExcel(fileName, title, keys, list, response);
	}
}
