package com.cczu.model.sbssgl.service;

import java.io.File;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Service;

import com.cczu.model.sbssgl.dao.SBSSGLSbbyTaskFirDao;
import com.cczu.model.sbssgl.entity.SBSSGL_SBBYTASKFIREntity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 设备设施管理-设备一级保养任务Service
 *
 */
@Service("SBSSGLSbbyTaskFirService")
public class SBSSGLSbbyTaskFirService {

	@Resource
	private SBSSGLSbbyTaskFirDao sBSSGLSbbyTaskFirDao;
	
	/**
	 * 根据Map分页查找设备二级保养计划
	 * @param id
	 * @return
	 */
	public Map<String,Object> findPageMapByMap(Map<String, Object> mapData) {
		List<Map<String,Object>> list=sBSSGLSbbyTaskFirDao.findPageListByMap(mapData);
		int getTotalCount=sBSSGLSbbyTaskFirDao.getPageTotalCountByMap(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 添加设备一级保养任务信息
	 * @param entity
	 */
	public void addInfo(SBSSGL_SBBYTASKFIREntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		sBSSGLSbbyTaskFirDao.save(entity);
	}

	/**
	 * 更新设备一级保养任务信息
	 * @param entity
	 */
	public void updateInfo(SBSSGL_SBBYTASKFIREntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);
		sBSSGLSbbyTaskFirDao.save(entity);
	}
	
	/**
	 * 删除设备一级保养任务信息
	 * @param id
	 */
	public void deleteInfoById(long id) {
		sBSSGLSbbyTaskFirDao.deleteInfo(id);
	}
	
	/**
	 * 删除和taskid关联的数据
	 * @param taskid
	 */
	public void deleteInfoByTaskid(Long taskid) {
		sBSSGLSbbyTaskFirDao.deleteInfoByTaskid(taskid);
	}
	
	public SBSSGL_SBBYTASKFIREntity find(Long id) {
		return sBSSGLSbbyTaskFirDao.find(id);
	}
	
	/**
	 * 根据Map查找设备一级保养任务
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> findListByMap(Map<String, Object> map) {
		return sBSSGLSbbyTaskFirDao.findListByMap(map);
	}
	
	/**
	 * 导出设备一级保养计划excel
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData, HttpServletRequest request) {
		String year = mapData.get("year").toString();
		String month = mapData.get("month").toString();
		String fileName = year + "年" + month + "月设备一级保养计划单.xls";
		String title = year + "年" + month + "月设备一级保养计划表";
		Map<String, Object> dataMap = new HashMap<>();
		List<Map<String, Object>> list = sBSSGLSbbyTaskFirDao.getExport(mapData);
		dataMap.put("list", list);
		dataMap.put("title", title);
		String tempPath = request.getSession().getServletContext().getRealPath("/")+"/WEB-INF/templates/一级保养计划模板.xls";
		this.exportSbJfExcelMap(fileName, dataMap, tempPath, response);
	}
	
	/**根据模板导出Excel
	  * @param fileName excel文件名称 
	  * @param datamap excel文件正文数据集合
	  * @param tempPath 模板路径
	  * @return 
	  */  
	@SuppressWarnings("unchecked")
	public void exportSbJfExcelMap(String fileName,Map<String, Object> datamap,String tempPath,HttpServletResponse response) {     
	  // 以下开始输出到EXCEL  
	  try {      
		   OutputStream os = response.getOutputStream();// 取得输出流        
		   response.reset();// 清空输出流        
		   response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("UTF-8"),"ISO8859-1"));  // 设定输出文件头        
		   response.setContentType("application/msexcel");// 定义输出类型      
		   //模板
		   File sourceFile = new File(tempPath);
	       // 源文件读入
	       Workbook template = Workbook.getWorkbook(sourceFile);
       
		   /*创建工作簿*/  
		   WritableWorkbook workbook = Workbook.createWorkbook(os,template);
		   /*创建工作表*/  
		   WritableSheet sheet = workbook.getSheet(0);

		   /*设置单元格字体*/  
		   WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 9);  
		   WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 24,WritableFont.BOLD);  
		   // 设置标题字体
			WritableCellFormat wcf_title = new WritableCellFormat(BoldFont);
			wcf_title.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_title.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_title.setWrap(false); // 文字是否换行
		   // 用于正文居左  
		   WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);  
		   wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条  
		   wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
		   wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐  
		   wcf_left.setWrap(false); // 文字是否换行     
		   
		   sheet.addCell(new Label(0, 0, datamap.get("title").toString(),wcf_title));
		   
		   /** ***************以下是EXCEL正文数据********************* */  
		   List<Map<String, String>> list = (List<Map<String, String>>) datamap.get("list");
		   //判断表中是否有数据  
       if (list != null && list.size() > 0) { 
		   int i=3; 
           //循环写入表中数据  
           for (Map<String, String> map : list) {  
          	  sheet.insertRow(i);
               //循环输出map中的子集：既列值  
        		   sheet.addCell(new Label(0,i,i-2+"",wcf_left));
        		   sheet.addCell(new Label(1,i,String.valueOf(map.get("sbbh")),wcf_left));
            	   sheet.addCell(new Label(2,i,String.valueOf(map.get("sbname")),wcf_left));
            	   sheet.addCell(new Label(3,i,String.valueOf(map.get("sbxh") == null?" ":map.get("sbxh")  + " " + map.get("sbgg") == null?" ":map.get("sbgg")),wcf_left));
            	   sheet.addCell(new Label(4,i,String.valueOf(map.get("m1")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(5,i,String.valueOf(map.get("m2")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(6,i,String.valueOf(map.get("m3")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(7,i,String.valueOf(map.get("m4")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(8,i,String.valueOf(map.get("m5")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(9,i,String.valueOf(map.get("m6")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(10,i,String.valueOf(map.get("m7")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(11,i,String.valueOf(map.get("m8")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(12,i,String.valueOf(map.get("m9")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(13,i,String.valueOf(map.get("m10")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(14,i,String.valueOf(map.get("m11")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(15,i,String.valueOf(map.get("m12")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(16,i,String.valueOf(map.get("m13")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(17,i,String.valueOf(map.get("m14")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(18,i,String.valueOf(map.get("m15")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(19,i,String.valueOf(map.get("m16")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(20,i,String.valueOf(map.get("m17")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(21,i,String.valueOf(map.get("m18")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(22,i,String.valueOf(map.get("m19")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(23,i,String.valueOf(map.get("m20")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(24,i,String.valueOf(map.get("m21")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(25,i,String.valueOf(map.get("m22")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(26,i,String.valueOf(map.get("m23")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(27,i,String.valueOf(map.get("m24")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(28,i,String.valueOf(map.get("m25")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(29,i,String.valueOf(map.get("m26")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(30,i,String.valueOf(map.get("m27")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(31,i,String.valueOf(map.get("m28")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(32,i,String.valueOf(map.get("m29")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(33,i,String.valueOf(map.get("m30")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(34,i,String.valueOf(map.get("m31")).equals("1")?"√":"",wcf_left));
            	   sheet.addCell(new Label(35,i,String.valueOf(map.get("deptname")),wcf_left));
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
	
	// 导出保养记录附件
	public List<Map<String, Object>> getFileList(Map<String, Object> mapData) {
		 return sBSSGLSbbyTaskFirDao.getFileList(mapData);
	}
}
