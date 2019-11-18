package com.cczu.model.service;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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

import com.cczu.model.dao.BisYgtjxxDao;
import com.cczu.model.dao.ErmYljlDao;
import com.cczu.model.dao.FxgkFxpgMajorRiskDao;
import com.cczu.model.dao.FxgkFxxxDao;
import com.cczu.model.dao.IBisAqpxxxDao;
import com.cczu.model.dao.YhpcJcjlDao;
import com.cczu.model.dao.YhpcXjjlDao;
import com.cczu.model.dao.YhpcYhpcjlDao;
import com.cczu.model.lbyp.dao.LbypFfbzDao;
import com.cczu.model.mbgl.dao.AqscFysyDao;
import com.cczu.model.mbgl.dao.TargetMbzpDao;
import com.cczu.model.mbgl.dao.TargetMeetingDao;
import com.cczu.model.mbgl.dao.TargetZbfpDao;
import com.cczu.model.mbgl.dao.TargetZbkhDao;

/**
 *  安全台账导出Service
 */
@Service("BisAqtzdcService")
public class BisAqtzdcService {
	
	@Resource
	private TargetZbfpDao targetMbfpDao;
	@Resource
	private TargetMbzpDao targetMbzpDao;
	@Resource
	private TargetZbkhDao targetMbkhDao;
	@Resource
	private AqscFysyDao aqscFysyDao;
	@Resource
	private LbypFfbzDao lbypFfbzDao;
	@Resource
	private BisYgtjxxDao bisYgtjxxDao;
	@Resource
	private TargetMeetingDao targetMeetingDao;
	@Resource
	private FxgkFxxxDao fxgkFxxxDao;
	@Resource
	private FxgkFxpgMajorRiskDao fxgkFxpgMajorRiskDao;
	@Resource
	private YhpcJcjlDao yhpcJcjlDao;
	@Resource
	private YhpcXjjlDao yhpcXjjlDao;
	@Resource
	private YhpcYhpcjlDao yhpcYhpcjlDao;
	@Resource
	private ErmYljlDao ermYljlDao;
	@Resource
	private IBisAqpxxxDao bisAqpxxxDao;
	
	/**
	 * 导出
	 * @param response
	 * @param map
	 */
	public void exportExcel(HttpServletResponse response,Map<String, Object> mapData,String checkval) {
		String fileName="安全台账导出.xls";
		try { 
			OutputStream os = response.getOutputStream();// 取得输出流        
		    response.reset();// 清空输出流        
		    response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("UTF-8"),"ISO8859-1"));  // 设定输出文件头        
		    response.setContentType("application/msexcel");// 定义输出类型     
		    
		    /*创建工作簿*/  
			WritableWorkbook workbook = Workbook.createWorkbook(os);
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
		    
		    /****************************** 处理sheet ************************************/
		    String[] sheetid = checkval.split(",");
			for(int i=0;i<sheetid.length;i++){
				if(sheetid[i].equals("1")){
					//指标分配
					List<Map<String, Object>> list=targetMbfpDao.getExport(mapData);
					String[] title={"年份","级别","责任部门","指标名称","指标值","预算（万元）","责任人","预计完成时间","制定人","审核人","批准人","批准日期"};  
					String[] keys={"m1","m3","deptname","targetname","targetval","m11","m12","m13","m7","m8","m9","m5"};
					createsheet(i,"指标分配",workbook,title,keys,list,wcf_center);
				}else if(sheetid[i].equals("2")){
					//目标自评
					List<Map<String, Object>> list=targetMbzpDao.getExport(mapData);
					String[] title={"年度","责任部门","季度","指标值","指标名称","达标情况","评定人","评定日期","备注"};  
					String[] keys={"year","department","jd","targetval","targetname","dbqk","m1","pdrq","m3"};
					createsheet(i,"目标自评",workbook,title,keys,list,wcf_center);
				}else if(sheetid[i].equals("3")){
					//指标考核
					List<Map<String, Object>> list=targetMbkhDao.getExport(mapData);
					String[] title={"年度","考核部门","目标指标数","自评达标数","考核达标数","考核结论","考核情况说明","考核人","考核时间"};  
					String[] keys={"year","dpname","tnum","zpnum","khnum","khjl","m5","m2","khsj"};
					createsheet(i,"指标考核",workbook,title,keys,list,wcf_center);
				}else if(sheetid[i].equals("4")){
					//安全费用使用
					List<Map<String, Object>> list=aqscFysyDao.getExport(mapData);
					String[] title={"日期","使用部门","支出项目类别","具体用途","金额（万元）","经办人","审核人","批准人"};  
					String[] keys={"rq","depart","lx","m3","m4","m5","m6","m7"};
					createsheet(i,"安全费用使用",workbook,title,keys,list,wcf_center);
				}else if(sheetid[i].equals("5")){
					//劳保用品发放
					List<Map<String, Object>> list=lbypFfbzDao.getExport(mapData);
					String[] title={"岗位/工种名称","员工姓名","用品名称","发放数量","最近领取日期"};  
					String[] keys={"jobtype","ename","goodsname","amount","lasttime"};
					createsheet(i,"劳保用品发放",workbook,title,keys,list,wcf_center);
				}else if(sheetid[i].equals("6")){
					//员工体检
					List<Map<String, Object>> list=bisYgtjxxDao.getExport(mapData);
					String[] title={"员工姓名","身份证号","体检类型","体检日期","接触危害因素","体检医院","体检结论","备注"};  
					String[] keys={"m7","m1","m2","tjrq","m9","m4","m5","m6"};
					createsheet(i,"员工体检",workbook,title,keys,list,wcf_center);
				}else if(sheetid[i].equals("7")){
					//安全会议
					List<Map<String, Object>> list=targetMeetingDao.getExport(mapData);
					String[] title={"会议主题","会议时间","地点","会议类型","状态"};  
					String[] keys={"theme","hysj","address","hylx","zt"};
					createsheet(i,"安全会议",workbook,title,keys,list,wcf_center);
				}else if(sheetid[i].equals("8")){
					//风险点辨识
					List<Map<String, Object>> list=fxgkFxxxDao.getExport(mapData);
					String[] title={"风险点名称","所在位置","主要危险因素","可能导致事故类型","风险等级","辨识时间","主要方法措施","主要依据","责任部门","责任人","责任人电话","可能性(L)","暴露频率(E)","严重性(C)","风险值"};  
					String[] keys={"m1","areaname","m7","m8","fxfj","rq","m10","m12","depart","m13","m14","aprobability","exposefrequency","aseverity","fxz"};
					createsheet(i,"风险点辨识",workbook,title,keys,list,wcf_center);
				}else if(sheetid[i].equals("9")){
					//重大风险清单
					List<Map<String, Object>> list=fxgkFxpgMajorRiskDao.getExport(mapData);
					String[] title={"名称","部门名称","风险值","风险等级","改进措施","分析人","分析时间","审核人","审定人"};  
					String[] keys={"name","deptname","riskvalue","risklevel","improvemeasure","analysisper","fxsj","reviewer","auditor"};
					createsheet(i,"重大风险清单",workbook,title,keys,list,wcf_center);
				}else if(sheetid[i].equals("10")){
					//安全检查
					List<Map<String, Object>> list=yhpcJcjlDao.getExport(mapData);
					String[] title={"任务名称","检查人","检查时间","检查结果","问题备注"};  
					String[] keys={"taskname","jcr","jcsj","jcjg","m5"};
					createsheet(i,"安全检查",workbook,title,keys,list,wcf_center);
				}else if(sheetid[i].equals("11")){
					//安全巡查
					List<Map<String, Object>> list=yhpcXjjlDao.getExport(mapData);
					String[] title={"检查点","所属班次","检查时间","检查人","检查结果","问题备注"};  
					String[] keys={"jcdname","name","createtime","uname","lx","note"};
					createsheet(i,"安全巡查",workbook,title,keys,list,wcf_center);
				}else if(sheetid[i].equals("12")){
					//隐患排查
					List<Map<String, Object>> list=yhpcYhpcjlDao.getExport(mapData);
					String[] title={"巡检点名称","隐患内容","隐患级别","隐患发现人","发现时间","整改人","整改时间","隐患状态"};  
					String[] keys={"xjdname","jcnr","yhjb","yhfxr","fxsj","zgrname","zgsj","yhzt"};
					createsheet(i,"隐患排查",workbook,title,keys,list,wcf_center);
				}else if(sheetid[i].equals("13")){
					//应急演练
					List<Map<String, Object>> list=ermYljlDao.getExcel(mapData);
					String[] title={"演练时间","演练地点","总指挥","参演部门","演练名称","效果评价","评审人员"};  
					String[] keys={"ylsj","m2","m3","m4","m5","m7","m8"};
					createsheet(i,"应急演练",workbook,title,keys,list,wcf_center);
				}else if(sheetid[i].equals("14")){
					//安全培训
					List<Map<String, Object>> list=bisAqpxxxDao.getExport(mapData);
					String[] title={"姓名","安全人员类别","职务","最近培训时间","下次培训时间","发证机关","备注"};  
					String[] keys={"m1","m2","m3","m4","m5","m6","m7"};
					createsheet(i,"安全培训",workbook,title,keys,list,wcf_center);
				}
			}
		    
		    workbook.write();  
			workbook.close();
		}catch (Exception e) {  
			System.out.println("系统提示：Excel文件导出失败，原因："+ e.toString());   
			e.printStackTrace();  
		}  
	}
	
	public void createsheet(int num,String sheetname,WritableWorkbook workbook,String[] title,String[] keys,List<Map<String, Object>> list,WritableCellFormat wcf_center){
		try { 
			//创建工作表
		    WritableSheet sheet = workbook.createSheet(sheetname, num); 
		    //设置纵横打印（默认为纵打）、打印纸  
		    jxl.SheetSettings sheetset = sheet.getSettings();  
		    sheetset.setProtected(false);  
	    
		    /** ***************以下是EXCEL第一行列标题********************* */  
		    for (int i = 0; i < title.length; i++) {  
		    	sheet.addCell(new Label(i, 0,title[i],wcf_center));  
		    }     
			/** ***************以下是EXCEL正文数据********************* */  
			//判断表中是否有数据  
	        if (list != null && list.size() > 0) { 
	  		    int i=0; 
	            //循环写入表中数据  
	            for (Map<String, Object> mapexc : list) {  
	                //循环输出map中的子集：既列值  
	                int j=0;  
	                for(String strkey : keys){  
	               	  	if(mapexc.get(strkey)!=null){
	               	  		sheet.addCell(new Label(j,i+1,String.valueOf(mapexc.get(strkey))));  
	               	  	}
	                    j++;  
	                } 
	                i++;
	            }  
	        }
		}catch (Exception e) {  
			System.out.println("系统提示：Excel文件导出失败，原因："+ e.toString());   
			e.printStackTrace();  
		}  
	}
}
