package com.cczu.model.service.impl;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.service.AAAExcel;
@Transactional(readOnly=true)
@Service("Aexcel")
public class AAAExcelImpl implements AAAExcel {

	@Override
	public void export(int num, String fileName, String[] Title,
			List<Map<String, Object>> listContent, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
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
				   for(int a=0;a<num;a++){
					   
				   }
				   
				   sheet.addCell(new Label(1,i,obj.get("m1").toString()));
				   sheet.addCell(new Label(2,i,obj.get("m2").toString()));
				   sheet.addCell(new Label(3,i,obj.get("m3").toString()));
				   sheet.addCell(new Label(4,i,obj.get("m4").toString()));
				   sheet.addCell(new Label(5,i,obj.get("m5").toString()));
				   sheet.addCell(new Label(6,i,obj.get("m6").toString()));
				   sheet.addCell(new Label(7,i,obj.get("m7").toString()));
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
