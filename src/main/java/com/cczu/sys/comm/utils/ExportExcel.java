package com.cczu.sys.comm.utils;
import java.awt.Color;
import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;  

import jxl.Workbook;  
import jxl.format.Alignment;  
import jxl.format.Border;  
import jxl.format.BorderLineStyle;  
import jxl.format.Colour;
import jxl.format.VerticalAlignment;  
import jxl.write.Label;  
import jxl.write.WritableCellFormat;  
import jxl.write.WritableFont;  
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;  
import jxl.write.WritableWorkbook; 

public class ExportExcel {
	
	public ExportExcel(String fileName,String[] title, List<Object> list, HttpServletResponse response){
		excelObj(fileName, title, list,response);
	}
	
	public ExportExcel(String fileName,String[] title, List<Map<String, Object>> list, HttpServletResponse response,String parment){
		excelMap(fileName, title, list,response);
	}
	
	public ExportExcel(String fileName,String[]
			title, String[] keys, List<Map<String, Object>> list, HttpServletResponse response){
		excelMap(fileName, title, keys, list,response);
	}
	
	public ExportExcel(String fileName,String[] title, String[] keys, List<Map<String, Object>> list, HttpServletResponse response, boolean merged){
		excelMapMerged(fileName, title, keys, list,response);
	}
	
	public ExportExcel(String fileName,String[] title, String[] keys, List<Map<String, Object>> list, HttpServletResponse response, boolean merged, String targetName, String linkName,HttpServletRequest request){
		excelMapMergedLink(fileName, title, keys, list,response,targetName, linkName,request);
	}
	
	public ExportExcel(String fileName,String[] title, String[] keys, List<Map<String, Object>> list, HttpServletResponse response, boolean merged, String[] targetNames, HttpServletRequest request){
		excelMapMergedLink(fileName, title, keys, list,response,targetNames,request);
	}
	
	
	/**
	  * @param fileName excel文件名称 
	  * @param listTitle excel文件第一行列标题集合 
	  * @param list excel文件正文数据集合 
	  * @return 
	  */  
	public void excelObj(String fileName,String[] title, List<Object> list, HttpServletResponse response) {     
	  // 以下开始输出到EXCEL  
	  try {      
		   OutputStream os = response.getOutputStream();// 取得输出流        
		   response.reset();// 清空输出流        
		   response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("UTF-8"),"ISO8859-1"));  // 设定输出文件头        
		   response.setContentType("application/x-msdownload");//("application/msexcel");// 定义输出类型      
		  
		   /*创建工作簿*/  
		   WritableWorkbook workbook = Workbook.createWorkbook(os);
		   /*创建工作表*/  
		   WritableSheet sheet = workbook.createSheet("Sheet1", 0); 
		   /*设置纵横打印（默认为纵打）、打印纸*/  
		   jxl.SheetSettings sheetset = sheet.getSettings();  
		   sheetset.setProtected(false);  
		   /*设置单元格字体*/  
		   WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);  
		   WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);  
		  
		   /** ***************以下设置三种单元格样式，灵活备用********************* */  
		   // 用于标题居中  
		   WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);  
		   wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条  
		   wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
		   wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐  
		   wcf_center.setWrap(false); // 文字是否换行  
		     
		   // 用于正文居左  
		   WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);  
		   wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条  
		   wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
		   wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐  
		   wcf_left.setWrap(false); // 文字是否换行     
		   
		  
		   /** ***************以下是EXCEL第一行列标题********************* */  
		   for (int i = 0; i < title.length; i++) {  
		    sheet.addCell(new Label(i, 0,title[i],wcf_center));  
		   }     
		   /** ***************以下是EXCEL正文数据********************* */  
		   for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[])list.get(i);
				int j=0;
				for(Object ob2:obj){
					if(ob2==null){
						ob2="";
					}
					sheet.addCell(new Label(j,i+1,ob2.toString()));
					j++;
				}
		   }
		   
		   workbook.write();  
		   workbook.close();     
		  
		} catch (Exception e) {  
			System.out.println("系统提示：Excel文件导出失败，原因："+ e.toString());   
			e.printStackTrace();  
		} 
	}  
	 
	
	/**
	  * @param fileName excel文件名称 
	  * @param listTitle excel文件第一行列标题集合 
	  * @param list excel文件正文数据集合 
	  * @return 
	  */  
	public void excelMap(String fileName,String[] title, List<Map<String, Object>> list, HttpServletResponse response) {     
	  // 以下开始输出到EXCEL  
	  try {      
		   OutputStream os = response.getOutputStream();// 取得输出流        
		   response.reset();// 清空输出流        
		   response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("UTF-8"),"ISO8859-1"));  // 设定输出文件头        
		   response.setContentType("application/msexcel");// 定义输出类型      
		  
		   /*创建工作簿*/  
		   WritableWorkbook workbook = Workbook.createWorkbook(os);
		   /*创建工作表*/  
		   WritableSheet sheet = workbook.createSheet("Sheet1", 0); 
		   /*设置纵横打印（默认为纵打）、打印纸*/  
		   jxl.SheetSettings sheetset = sheet.getSettings();  
		   sheetset.setProtected(false);  
		   /*设置单元格字体*/  
		   WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);  
		   WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);  
		  
		   /** ***************以下设置三种单元格样式，灵活备用********************* */  
		   // 用于标题居中  
		   WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);  
		   wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条  
		   wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
		   wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐  
		   wcf_center.setWrap(false); // 文字是否换行  
		   wcf_center.setBackground(Colour.GRAY_50);  
		   // 用于正文居左  
		   WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);  
		   wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条  
		   wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
		   wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐  
		   wcf_left.setWrap(false); // 文字是否换行     
		   
		  
		   /** ***************以下是EXCEL第一行列标题********************* */  
		   for (int i = 0; i < title.length; i++) {  
		    sheet.addCell(new Label(i, 0,title[i],wcf_center));  
		   }     
		   /** ***************以下是EXCEL正文数据********************* */  
		   //判断表中是否有数据  
           if (list != null && list.size() > 0) { 
    		   int i=1; 
               //循环写入表中数据  
               for (Map<String, Object> map : list) {  
                   //循环输出map中的子集：既列值  
                   int j=0;  
                   for(Object o:map.keySet()){  
                       //ps：因为要“”通用”“导出功能，所以这里循环的时候不是get("Name"),而是通过map.get(o)  
                	   sheet.addCell(new Label(j,i+1,String.valueOf(map.get(o))));  
                       j++;  
                   } 
                   i++;
               }  
           }
		   
		   workbook.write();  
		   workbook.close();     
		  
		} catch (Exception e) {  
			System.out.println("系统提示：Excel文件导出失败，原因："+ e.toString());   
			e.printStackTrace();  
		}  
	}
	
	/**
	  * @param fileName excel文件名称 
	  * @param listTitle excel文件第一行列标题集合 
	  * @param list excel文件正文数据集合 
	  * @return 
	  */  
	public void excelMap(String fileName,String[] title,String[] keys, List<Map<String, Object>> list, HttpServletResponse response) {     
	  // 以下开始输出到EXCEL  
	  try {      
		   OutputStream os = response.getOutputStream();// 取得输出流        
		   response.reset();// 清空输出流        
		   response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("UTF-8"),"ISO8859-1"));  // 设定输出文件头        
		   response.setContentType("application/msexcel");// 定义输出类型      
		  
		   /*创建工作簿*/  
		   WritableWorkbook workbook = Workbook.createWorkbook(os);
		   /*创建工作表*/  
		   WritableSheet sheet = workbook.createSheet("Sheet1", 0); 
		   /*设置纵横打印（默认为纵打）、打印纸*/  
		   jxl.SheetSettings sheetset = sheet.getSettings();  
		   sheetset.setProtected(false);  
		   /*设置单元格字体*/  
		   WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);  
		   WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);  
		  
		   /** ***************以下设置三种单元格样式，灵活备用********************* */  
		   // 用于标题居中  
		   WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);  
		   wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条  
		   wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
		   wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐  
		   wcf_center.setWrap(false); // 文字是否换行  
		     
		   // 用于正文居左  
		   WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);  
		   wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条  
		   wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
		   wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐  
		   wcf_left.setWrap(false); // 文字是否换行     
		   
		  
		   /** ***************以下是EXCEL第一行列标题********************* */  
		   for (int i = 0; i < title.length; i++) {  
		    sheet.addCell(new Label(i, 0,title[i],wcf_center));  
		   }     
		   /** ***************以下是EXCEL正文数据********************* */  
		   //判断表中是否有数据  
          if (list != null && list.size() > 0) { 
   		   int i=0; 
              //循环写入表中数据  
              for (Map<String, Object> map : list) {  
                  //循环输出map中的子集：既列值  
                  int j=0;  
                  for(String strkey : keys){  
                      //ps：因为要“”通用”“导出功能，所以这里循环的时候不是get("Name"),而是通过map.get(o)  
                	  if(map.get(strkey)!=null){
                		  sheet.addCell(new Label(j,i+1,String.valueOf(map.get(strkey))));  
                	  }
                      j++;  
                  } 
                  i++;
              }  
          }
		   
		   workbook.write();  
		   workbook.close();     
		  
		} catch (Exception e) {  
			System.out.println("系统提示：Excel文件导出失败，原因："+ e.toString());   
			e.printStackTrace();  
		}  
	}
	/**
	 * 合并首列相同值
	 * @param fileName excel文件名称 
	 * @param listTitle excel文件第一行列标题集合 
	 * @param list excel文件正文数据集合 
	 * @return 
	 */  
	@SuppressWarnings("deprecation")
	public void excelMapMerged(String fileName,String[] title,String[] keys, List<Map<String, Object>> list, HttpServletResponse response) {     
		// 以下开始输出到EXCEL  
		try {      
			OutputStream os = response.getOutputStream();// 取得输出流        
			response.reset();// 清空输出流        
			response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("UTF-8"),"ISO8859-1"));  // 设定输出文件头        
			response.setContentType("application/msexcel");// 定义输出类型      
			
			/*创建工作簿*/  
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			/*创建工作表*/  
			WritableSheet sheet = workbook.createSheet("Sheet1", 0); 
			/*设置纵横打印（默认为纵打）、打印纸*/  
			jxl.SheetSettings sheetset = sheet.getSettings();  
			sheetset.setProtected(false);  
			sheetset.setDefaultColumnWidth(30);
			/*设置单元格字体*/  
			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);  
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);  
			
			/** ***************以下设置三种单元格样式，灵活备用********************* */  
			// 用于标题居中  
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);  
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条  
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐  
			wcf_center.setWrap(false); // 文字是否换行  
			
			// 用于正文居左  
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);  
			wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条  
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
			wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐  
			wcf_left.setWrap(false); // 文字是否换行     
			
			
			/** ***************以下是EXCEL第一行列标题********************* */  
			for (int i = 0; i < title.length; i++) {  
				sheet.addCell(new Label(i, 0,title[i],wcf_center));  
			}     
			/** ***************以下是EXCEL正文数据********************* */  
			//判断表中是否有数据  
			if (list != null && list.size() > 0) { 
				int i=0; 
				//循环写入表中数据  
				int preindex=0;
				for (Map<String, Object> map : list) {  
					//循环输出map中的子集：既列值  
					int j=0;  
					
					for(String strkey : keys){  
						//ps：因为要“”通用”“导出功能，所以这里循环的时候不是get("Name"),而是通过map.get(o)  
						if(map.get(strkey)!=null){
							sheet.addCell(new Label(j,i+1,String.valueOf(map.get(strkey))));  
							//sheet.setColumnView(0, 35, wcf_left);
							//sheet.setColumnView(j, (map.get(strkey).toString().length()<<1)+5);
						}
						if(j==0 && i>0){
							String pretext = sheet.getCell(0, i).getContents();
							if(pretext.equals(String.valueOf(map.get(strkey)))){
								if(preindex == 0){
									preindex = i;//有重复数据
								}
								if(i+1==list.size()){//如果是最后一行，合并当前的数据
									if(preindex != 0){
										sheet.mergeCells(0, preindex, 0, i + 1);
										preindex = 0;
									}
								}
							}else{//如果和上一行不一样，合并之前的数据
								if(preindex != 0){
									sheet.mergeCells(0, preindex, 0, i);
									preindex = 0;
								}
							}
						}
						j++;  
					} 
					i++;
				}  
				
				
			}
			
			workbook.write();  
			workbook.close();     
			
		} catch (Exception e) {  
			System.out.println("系统提示：Excel文件导出失败，原因："+ e.toString());   
			e.printStackTrace();  
		}  
	}
	
	/**
	 * 合并首列相同值,为某一列添加超链接
	 * @param fileName
	 * @param title
	 * @param keys
	 * @param list
	 * @param response
	 * @param targetName 目标列的名称(要添加超链接的列名)，如m1
	 * @param linkName   链接地址列的名称(是上传文件后生成的地址(/upload/file/20180730/20180730095817_582.doxc||安全法)所在列的列名)，如m2
	 */
	@SuppressWarnings("deprecation")
	public void excelMapMergedLink(String fileName,String[] title,String[] keys, List<Map<String, Object>> list, HttpServletResponse response, String targetName, String linkName,HttpServletRequest request) {     
		// 以下开始输出到EXCEL  
		try {      
			OutputStream os = response.getOutputStream();// 取得输出流        
			response.reset();// 清空输出流        
			response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("UTF-8"),"ISO8859-1"));  // 设定输出文件头        
			response.setContentType("application/msexcel");// 定义输出类型      
			
			/*创建工作簿*/  
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			/*创建工作表*/  
			WritableSheet sheet = workbook.createSheet("Sheet1", 0); 
			/*设置纵横打印（默认为纵打）、打印纸*/  
			jxl.SheetSettings sheetset = sheet.getSettings();  
			sheetset.setProtected(false);  
			sheetset.setDefaultColumnWidth(30);
			/*设置单元格字体*/  
			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);  
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);  
			
			/** ***************以下设置三种单元格样式，灵活备用********************* */  
			// 用于标题居中  
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);  
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条  
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐  
			wcf_center.setWrap(false); // 文字是否换行  
			
			// 用于正文居左  
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);  
			wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条  
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
			wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐  
			wcf_left.setWrap(false); // 文字是否换行     
			
			
			/** ***************以下是EXCEL第一行列标题********************* */  
			for (int i = 0; i < title.length; i++) {  
				sheet.addCell(new Label(i, 0,title[i],wcf_center));  
			}     
			/** ***************以下是EXCEL正文数据********************* */  
			//判断表中是否有数据  
			if (list != null && list.size() > 0) { 
				int i=0; 
				//循环写入表中数据  
				int preindex=0;
				for (Map<String, Object> map : list) {  
					//循环输出map中的子集：既列值  
					int j=0; 
					String targetName_value = "";//目标列对应的值
					String targetName_new_value = "";//拼接后目标列对应的链接地址值
					if (StringUtils.isNotBlank(targetName) && StringUtils.isNotBlank(linkName)) {
						//目标列对应的值
						targetName_value = map.get(targetName).toString();
						//链接地址列的值
						String linkName_value = map.get(linkName).toString();
						//找到项目地址进行拼接
						targetName_new_value = (request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+linkName_value);
						//将拼接后的地址赋给目标列
						map.put(targetName, targetName_new_value);
					}
					
					
					for(String strkey : keys){  
						//ps：因为要“”通用”“导出功能，所以这里循环的时候不是get("Name"),而是通过map.get(o)  
						if(map.get(strkey)!=null){
	                		  String target = map.get(strkey).toString();
	                		  if(target.startsWith("http")){
	                			  String[] urls = StringUtils.split(target, Global.FILE_SPLIT_CHAR);
	                			  if(j==keys.length-1){//最后一列列出所有附件
	                				  for(int k=0;k<urls.length;k++){
	                					  sheet.addHyperlink(new WritableHyperlink(j+k,i+1,new File(urls[k]),targetName_value+(k+1)));
	                					 /* sheet.addHyperlink(new WritableHyperlink(j+k,i+1,new File(urls[k]),"附件"+(k+1)));*/
	                				  }
	                			  }else{
	                				  sheet.addHyperlink(new WritableHyperlink(j,i+1,new File(urls[0]),targetName_value));
	                				  /* sheet.addHyperlink(new WritableHyperlink(j+k,i+1,new File(urls[0]),"附件"+(k+1)));*/
	                			  }
	                		  }
	                		  else
	                			  sheet.addCell(new Label(j,i+1,target));  
	                	  }
						if(j==0 && i>0){
							String pretext = sheet.getCell(0, i).getContents();
							if(pretext.equals(String.valueOf(map.get(strkey)))){
								if(preindex == 0){
									preindex = i;//有重复数据
								}
								if(i+1==list.size()){//如果是最后一行，合并当前的数据
									if(preindex != 0){
										sheet.mergeCells(0, preindex, 0, i + 1);
										preindex = 0;
									}
								}
							}else{//如果和上一行不一样，合并之前的数据
								if(preindex != 0){
									sheet.mergeCells(0, preindex, 0, i);
									preindex = 0;
								}
							}
						}
						j++;  
					} 
					i++;
				}  
				
				
			}
			
			workbook.write();  
			workbook.close();     
			
		} catch (Exception e) {  
			System.out.println("系统提示：Excel文件导出失败，原因："+ e.toString());   
			e.printStackTrace();  
		}  
	}
	
	/**
	 * 合并首列相同值,为某一列添加超链接
	 * @param fileName
	 * @param title
	 * @param keys
	 * @param list
	 * @param response
	 * @param targetNames 带附件地址的列名
	 *   
	 */
	public void excelMapMergedLink(String fileName,String[] title,String[] keys, List<Map<String, Object>> list,HttpServletResponse response, String[] targetNames, HttpServletRequest request) {     
		// 以下开始输出到EXCEL  
		try {      
			OutputStream os = response.getOutputStream();// 取得输出流        
			response.reset();// 清空输出流        
			response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("UTF-8"),"ISO8859-1"));  // 设定输出文件头        
			response.setContentType("application/msexcel");// 定义输出类型      
			
			/*创建工作簿*/  
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			/*创建工作表*/  
			WritableSheet sheet = workbook.createSheet("Sheet1", 0); 
			/*设置纵横打印（默认为纵打）、打印纸*/  
			jxl.SheetSettings sheetset = sheet.getSettings();  
			sheetset.setProtected(false);  
			sheetset.setDefaultColumnWidth(30);
			/*设置单元格字体*/  
			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);  
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);  
			
			/** ***************以下设置三种单元格样式，灵活备用********************* */  
			// 用于标题居中  
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);  
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条  
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐  
			wcf_center.setWrap(false); // 文字是否换行  
			
			// 用于正文居左  
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);  
			wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条  
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
			wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐  
			wcf_left.setWrap(false); // 文字是否换行     
			
			
			/** ***************以下是EXCEL第一行列标题********************* */  
			for (int i = 0; i < title.length; i++) {  
				sheet.addCell(new Label(i, 0,title[i],wcf_center));  
			}     
			/** ***************以下是EXCEL正文数据********************* */  
			//判断表中是否有数据  
			if (list != null && list.size() > 0) { 
				int i=0; 
				//循环写入表中数据  
				int preindex=0;
				for (Map<String, Object> map : list) {  
					//循环输出map中的子集：既列值  
					int j=0; 
					String targetName_value = "";//目标列对应的值
					String targetName_new_value = "";//拼接后目标列对应的链接地址值
					if (targetNames.length > 0) {
						for (String targetName : targetNames) {
							//链接地址列的值
							if (map.get(targetName) != null) {
								targetName_value = map.get(targetName).toString();
								//找到项目地址进行拼接
								targetName_new_value = (request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+targetName_value);
								//将拼接后的地址赋给目标列
								map.put(targetName, targetName_new_value);
							}
						}
					}
					
					
					for(String strkey : keys){  
						//ps：因为要“”通用”“导出功能，所以这里循环的时候不是get("Name"),而是通过map.get(o)  
						if(map.get(strkey)!=null){
	                		  String target = map.get(strkey).toString();
	                		  if(target.startsWith("http")){
	                			  String[] urls = StringUtils.split(target, Global.FILE_SPLIT_CHAR);
                				   sheet.addHyperlink(new WritableHyperlink(j,i+1,new File(urls[0]),"附件"));
	                		  }
	                		  else
	                			  sheet.addCell(new Label(j,i+1,target));  
	                	  }
						if(j==0 && i>0){
							String pretext = sheet.getCell(0, i).getContents();
							if(pretext.equals(String.valueOf(map.get(strkey)))){
								if(preindex == 0){
									preindex = i;//有重复数据
								}
								if(i+1==list.size()){//如果是最后一行，合并当前的数据
									if(preindex != 0){
										sheet.mergeCells(0, preindex, 0, i + 1);
										preindex = 0;
									}
								}
							}else{//如果和上一行不一样，合并之前的数据
								if(preindex != 0){
									sheet.mergeCells(0, preindex, 0, i);
									preindex = 0;
								}
							}
						}
						j++;  
					} 
					i++;
				}  
				
				
			}
			
			workbook.write();  
			workbook.close();     
			
		} catch (Exception e) {  
			System.out.println("系统提示：Excel文件导出失败，原因："+ e.toString());   
			e.printStackTrace();  
		}  
	}

}
