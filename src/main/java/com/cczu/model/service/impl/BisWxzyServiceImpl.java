package com.cczu.model.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IBisWxzyDao;
import com.cczu.model.entity.BIS_DangerOperationEntity;
import com.cczu.model.service.IBisWxzyService;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;

@Transactional(readOnly=true)
@Service("BisWxzyService")
public class BisWxzyServiceImpl implements IBisWxzyService {
	@Resource
	private IBisWxzyDao bisWxzyDao;

	@Override
	public Page<BIS_DangerOperationEntity> search(
			Page<BIS_DangerOperationEntity> page, List<PropertyFilter> filters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BIS_DangerOperationEntity findById(Long id) {
		// TODO Auto-generated method stub
		return bisWxzyDao.findById(id);
	}

	@Override
	public BIS_DangerOperationEntity findAll(long qyid) {
		// TODO Auto-generated method stub
		return bisWxzyDao.findAll(qyid);
	}

	@Override
	public void addInfo(BIS_DangerOperationEntity bis) {
		// TODO Auto-generated method stub
		bisWxzyDao.addInfo(bis);
	}

	@Override
	public void updateInfo(BIS_DangerOperationEntity bis) {
		// TODO Auto-generated method stub
		bisWxzyDao.updateInfo(bis);
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		bisWxzyDao.deleteInfo(id);
	}

	@Override
	public String content(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return bisWxzyDao.content(mapData);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<BIS_DangerOperationEntity> list = bisWxzyDao.dataGrid(mapData);
		int getTotalCount = bisWxzyDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		// TODO Auto-generated method stub
			String[] Title={"id","作业名称","时间起","时间止","作业频次","备注"};  
			
			List<BIS_DangerOperationEntity> list=bisWxzyDao.getExport(mapData);
			List<Map<String, Object>> listContent=new ArrayList<>();
			String Ms4 ="";
			for(BIS_DangerOperationEntity entity:list){
				if(entity.getM4().equals("1")){
					Ms4 ="偶尔";
				}
				if(entity.getM4().equals("2")){
					Ms4 ="经常";
				}
				if(entity.getM4().equals("3")){
					Ms4 ="强化";
				}
				Map<String, Object> map =new HashMap<String, Object>();
				map.put("id", entity.getID());
				map.put("m1", entity.getM1());
				map.put("m2", entity.getM2());
				map.put("m3", entity.getM3());
				map.put("m4", Ms4);
				map.put("m5", entity.getM5());
				listContent.add(map);
			}
			
			export("信息表.xls",Title, listContent, response);
		}
		
		/**
		 * 导出Excel
		 * @param fileName
		 * @param Title
		 * @param listContent
		 * @param response
		 */
		private void export(String fileName,String[] Title, List<Map<String, Object>> listContent, HttpServletResponse response) {  
			  try {    
				  //定义输出流，以便打开保存对话框
				   OutputStream os = response.getOutputStream();// 取得输出流        
				   response.reset();// 清空输出流        
				   response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("UTF-8"),"ISO8859-1"));  
				   // 设定输出文件头        
				   response.setContentType("application/msexcel");// 定义输出类型      
				  
				   //创建工作簿
				   WritableWorkbook workbook = Workbook.createWorkbook(os);  
				  
				   //创建工作表/
				   WritableSheet sheet = workbook.createSheet("Sheet1", 0);  
				  
				   //设置纵横打印（默认为纵打）、打印纸
				   jxl.SheetSettings sheetset = sheet.getSettings();  
				   sheetset.setProtected(false);  
				  
				   //以下是EXCEL第一行列标题
				   for (int i = 0; i < Title.length; i++) {  
				    sheet.addCell(new Label(i, 0,Title[i]));  
				   }     
				   //*以下是EXCEL正文数据
				   int i=1;  
				   for(Map<String, Object> obj:listContent){ 
					   sheet.addCell(new Label(0,i,obj.get("id").toString()));
					   sheet.addCell(new Label(1,i,obj.get("m1").toString()));
					   sheet.addCell(new Label(2,i,obj.get("m2").toString()));
					   sheet.addCell(new Label(3,i,obj.get("m3").toString()));
					   sheet.addCell(new Label(4,i,obj.get("m4").toString()));
					   sheet.addCell(new Label(5,i,obj.get("m5").toString()));
					   
				       i++;  
				   }  
				   //将以上缓存中的内容写到EXCEL文件中
				   workbook.write();  
				  
				   //关闭文件
				   workbook.close();     
				   os.close();
				  
				  } catch (Exception e) {  
				   e.printStackTrace();  
				  }  
			} 

}
