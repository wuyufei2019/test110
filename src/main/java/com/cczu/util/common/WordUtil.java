package com.cczu.util.common;

import freemarker.template.Configuration;
import freemarker.template.Template;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Locale;
import java.util.Map;

@SuppressWarnings("restriction")
public class WordUtil {
	
	@SuppressWarnings("deprecation")
	public static void createWord(Map<String,Object> dataMap,String templateName,String filePath,String fileName){
        try {
        //创建配置实例 
        Configuration configuration = new Configuration();
        
        //设置编码
            configuration.setDefaultEncoding("UTF-8");
            
            //ftl模板文件统一放至 com.lun.template 包下面
            configuration.setClassForTemplateLoading(WordUtil.class,"/com/cczu/util/common/");
            
            //获取模板 
            Template template = configuration.getTemplate(templateName);
            
            //输出文件
            File outFile = new File(filePath+File.separator+fileName);
            
            //如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()){
                outFile.getParentFile().mkdirs();
            }
            
            //将模板和数据模型合并生成文件 
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));


            //生成文件
            template.process(dataMap, out);
            
            //关闭流
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	/*** 
     * @param dataMap 数据集 
     * @param templateName 模板名称 
     * @param filePath 文件路径 
     * @param fileName 文件名称 
     * @param HttpServletRequest request
     */ 
	@SuppressWarnings("deprecation")
	public static void ireportWord(Map<String,Object> dataMap,String templateName,String filePath,String fileName,HttpServletRequest request) {
		 try {
			 	Configuration freemarkerCfg = new Configuration();
			 	freemarkerCfg.setServletContextForTemplateLoading(request.getSession().getServletContext(), "/WEB-INF/templates");  
			 	freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
				//获取模板 
	            Template template = freemarkerCfg.getTemplate(templateName);
	            //输出文件
	            File outFile = new File(filePath+File.separator+fileName);
	            //如果输出目标文件夹不存在，则创建
	            if (!outFile.getParentFile().exists()){
	                outFile.getParentFile().mkdirs();
	            }
	            //将模板和数据模型合并生成文件 
	            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
	            //生成文件
	            template.process(dataMap, out);
	            
	            //关闭流
	            out.flush();
	            out.close();
		 	} catch (Exception e) {
	            e.printStackTrace();
	        }
    }
	 public static String getImageStr(String imagePath) {  
		 String str="";
         InputStream in = null;  
         byte[] data = null;  
         try {  
             in = new FileInputStream(imagePath);  
             data = new byte[in.available()];  
             in.read(data);  
             in.close();  
             BASE64Encoder encoder = new BASE64Encoder();  
             str=encoder.encode(data);  
         } catch (Exception e) {  
             //e.printStackTrace();  
         }  
         
         return str;
     } 
}
