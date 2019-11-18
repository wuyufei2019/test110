package com.cczu.model.sbssgl.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.sbssgl.entity.SBSSGL_SBBYTASKEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBBYTASKSECEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SHJLEntity;
import com.cczu.model.sbssgl.service.SBSSGLSbbyTaskSecService;
import com.cczu.model.sbssgl.service.SBSSGLSbbyTaskService;
import com.cczu.model.sbssgl.service.SBSSGLSbglService;
import com.cczu.model.sbssgl.service.SBSSGLShjlService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.sys.system.utils.ZipUtils;
import com.itextpdf.text.DocumentException;

/**
 * 设备设施管理-设备二级保养任务controller
 */
@Controller
@RequestMapping("sbssgl/sbbytasksec")
public class PageSbssglSbbyTaskSecController extends BaseController {

	@Autowired
	private SBSSGLSbbyTaskSecService sBSSGLSbbyTaskSecService;
	@Autowired
	private SBSSGLSbbyTaskService sBSSGLSbbyTaskService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private SBSSGLSbglService sBSSGLSbglService;
	@Autowired
	private SBSSGLShjlService sBSSGLShjlService;
	@Autowired
	private UserService userService;
	

	/**
	 * 添加信息
	 * @param request,model
	 * @throws ParseException 
	 */
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(HttpServletRequest request) throws ParseException {
		String datasuccess="success";
		String jau = ("," + request.getParameter("jau") + ",");
		String feb = ("," + request.getParameter("feb") + ",");
		String mar = ("," + request.getParameter("mar") + ",");
		String apr = ("," + request.getParameter("apr") + ",");
		String may = ("," + request.getParameter("may") + ",");
		String jun = ("," + request.getParameter("jun") + ",");
		String jul = ("," + request.getParameter("jul") + ",");
		String aug = ("," + request.getParameter("aug") + ",");
		String sep = ("," + request.getParameter("sep") + ",");
		String oct = ("," + request.getParameter("oct") + ",");
		String nov = ("," + request.getParameter("nov") + ",");
		String dec = ("," + request.getParameter("dec") + ",");
		Long qyid = Long.parseLong(request.getParameter("qyidsec"));
		Long deptid = Long.parseLong(request.getParameter("deptidsec"));
		String year = request.getParameter("yearsec");// 年度
		String bztime = request.getParameter("bztimesec");// 编制日期
		String sbtype = request.getParameter("sbtypesec");
		
		SBSSGL_SBBYTASKEntity task = new SBSSGL_SBBYTASKEntity();
		task.setQyid(qyid);
		task.setDeptid(deptid);
		task.setYear(year);
		task.setBztime(new Timestamp(DateUtils.parseDate(bztime, "yyyy-MM-dd").getTime()));
		task.setBzrid(Long.parseLong(UserUtil.getCurrentShiroUser().getId()+""));
		task.setType("1");
		task.setSbtype(sbtype);
	
		Long taskid = sBSSGLSbbyTaskService.addInfo(task);
		
		Map<String, Object> map = new HashMap<>();
		map.put("sbtype", request.getParameter("sbtype"));
		map.put("qyid", qyid);
		map.put("m23", deptid);
		List<Map<String, Object>> sbList = sBSSGLSbglService.findListByMap(map);
		if (sbList.size() > 0) {
			for (Map<String, Object> sbInfo : sbList) {
				SBSSGL_SBBYTASKSECEntity entity = new SBSSGL_SBBYTASKSECEntity();
				Long sbid = Long.parseLong(sbInfo.get("id").toString());
				if (jau.contains("," + sbid + ",")) {entity.setM1("1");} else {entity.setM1("0");}
				if (feb.contains("," + sbid + ",")) {entity.setM2("1");} else {entity.setM2("0");}
				if (mar.contains("," + sbid + ",")) {entity.setM3("1");} else {entity.setM3("0");}
				if (apr.contains("," + sbid + ",")) {entity.setM4("1");} else {entity.setM4("0");}
				if (may.contains("," + sbid + ",")) {entity.setM5("1");} else {entity.setM5("0");}
				if (jun.contains("," + sbid + ",")) {entity.setM6("1");} else {entity.setM6("0");}
				if (jul.contains("," + sbid + ",")) {entity.setM7("1");} else {entity.setM7("0");}
				if (aug.contains("," + sbid + ",")) {entity.setM8("1");} else {entity.setM8("0");}
				if (sep.contains("," + sbid + ",")) {entity.setM9("1");} else {entity.setM9("0");}
				if (oct.contains("," + sbid + ",")) {entity.setM10("1");} else {entity.setM10("0");}
				if (nov.contains("," + sbid + ",")) {entity.setM11("1");} else {entity.setM11("0");}
				if (dec.contains("," + sbid + ",")) {entity.setM12("1");} else {entity.setM12("0");}
				entity.setSbid(sbid);
				entity.setTaskid(taskid);
				entity.setM13("0");// 此时把状态设置为待上传附件
				sBSSGLSbbyTaskSecService.addInfo(entity);
			}
		}
		//返回结果
		return datasuccess;
	}
	

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{taskid}", method = RequestMethod.GET)
	public String update(@PathVariable("taskid") Long taskid, HttpServletRequest request, Model model) {
		Map<String, Object> entity = sBSSGLSbbyTaskService.findById(taskid);
		model.addAttribute("entity", entity);// 年度、企业名称、部门名称、编制日期的信息
		model.addAttribute("sbtype", request.getParameter("sbtype"));
		model.addAttribute("action", "update");
		//返回页面
		return "sbssgl/sbbyjl/sec/form";
	}
	
	/**
	 * 分页list页面
	 * @param request
	 */
	@RequestMapping(value="pagelist/{taskid}")
	@ResponseBody
	public Map<String, Object> getPageListMap(@PathVariable("taskid") Long taskid, HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("taskid", taskid);
		map.putAll(getAuthorityMap());
		return sBSSGLSbbyTaskSecService.findPageMapByMap(map);	
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 * @throws ParseException 
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String updateinfo(HttpServletRequest request) throws ParseException{
		String datasuccess="success";
		String jau = ("," + request.getParameter("jau") + ",");
		String feb = ("," + request.getParameter("feb") + ",");
		String mar = ("," + request.getParameter("mar") + ",");
		String apr = ("," + request.getParameter("apr") + ",");
		String may = ("," + request.getParameter("may") + ",");
		String jun = ("," + request.getParameter("jun") + ",");
		String jul = ("," + request.getParameter("jul") + ",");
		String aug = ("," + request.getParameter("aug") + ",");
		String sep = ("," + request.getParameter("sep") + ",");
		String oct = ("," + request.getParameter("oct") + ",");
		String nov = ("," + request.getParameter("nov") + ",");
		String dec = ("," + request.getParameter("dec") + ",");
		String year = request.getParameter("year");// 年度
		String bztime = request.getParameter("bztime");// 编制日期
		Long taskid = Long.parseLong(request.getParameter("ID"));
		
		SBSSGL_SBBYTASKEntity task = sBSSGLSbbyTaskService.find(taskid);
		task.setYear(year);
		task.setBztime(new Timestamp(DateUtils.parseDate(bztime, "yyyy-MM-dd").getTime()));
		sBSSGLSbbyTaskService.updateInfo(task);
		
		Map<String, Object> map = new HashMap<>();
		map.put("taskid", taskid);
		List<Map<String, Object>> list = sBSSGLSbbyTaskSecService.findListByMap(map);// 根据taskid查找关联的二级保养记录
		if (list.size() > 0) {
			for (Map<String, Object> info : list) {
				SBSSGL_SBBYTASKSECEntity entity = new SBSSGL_SBBYTASKSECEntity();
				Long id = Long.parseLong(info.get("id").toString());
				if (jau.contains("," + id + ",")) {entity.setM1("1");} else {entity.setM1("0");}
				if (feb.contains("," + id + ",")) {entity.setM2("1");} else {entity.setM2("0");}
				if (mar.contains("," + id + ",")) {entity.setM3("1");} else {entity.setM3("0");}
				if (apr.contains("," + id + ",")) {entity.setM4("1");} else {entity.setM4("0");}
				if (may.contains("," + id + ",")) {entity.setM5("1");} else {entity.setM5("0");}
				if (jun.contains("," + id + ",")) {entity.setM6("1");} else {entity.setM6("0");}
				if (jul.contains("," + id + ",")) {entity.setM7("1");} else {entity.setM7("0");}
				if (aug.contains("," + id + ",")) {entity.setM8("1");} else {entity.setM8("0");}
				if (sep.contains("," + id + ",")) {entity.setM9("1");} else {entity.setM9("0");}
				if (oct.contains("," + id + ",")) {entity.setM10("1");} else {entity.setM10("0");}
				if (nov.contains("," + id + ",")) {entity.setM11("1");} else {entity.setM11("0");}
				if (dec.contains("," + id + ",")) {entity.setM12("1");} else {entity.setM12("0");}
				entity.setID(id);
				entity.setSbid(Long.parseLong(info.get("sbid").toString()));
				entity.setTaskid(taskid);
				/*entity.setM13("0");*/
				entity.setM13(info.get("m13").toString());// 状态不修改 
				sBSSGLSbbyTaskSecService.addInfo(entity);
			}
		}
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 上传附件页面跳转
	 */
	@RequestMapping(value = "uploadindex/{id}")
	public String uploadindex(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		return "sbssgl/sbbyjl/sec/upload";
	}
	
	/**
	 * 上传附件
	 */
	@RequestMapping(value = "uploadfj")
	@ResponseBody
	public String uploadfj(long id,String fj) {
		String datasuccess="success";
		SBSSGL_SBBYTASKSECEntity sbbyjl = sBSSGLSbbyTaskSecService.find(id);
		sbbyjl.setM13("1");
		sbbyjl.setM14(fj);
		sBSSGLSbbyTaskSecService.updateInfo(sbbyjl);
		return datasuccess;
	}
	
	/**
	 * 审核结果页面跳转
	 */
	@RequestMapping(value = "shjg/{id}")
	public String shjg(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		return "sbssgl/sbbyjl/sec/shjg";
	}
	
	/**
	 * 保存审核结果
	 * @throws DocumentException 
	 * @throws IOException 
	 */
	@RequestMapping(value = "shjg")
	@ResponseBody
	public String bhzt(HttpServletRequest request) throws IOException, DocumentException {
		String datasuccess="success";
		Long id = Long.parseLong(request.getParameter("id")); 
		String shjg = request.getParameter("shjg");
		String btgyy = request.getParameter("btgyy");
		SBSSGL_SBBYTASKSECEntity sbbyjl = sBSSGLSbbyTaskSecService.find(id);
		if ("0".equals(shjg)) {// 未完成
			sbbyjl.setM13("3");  // 将状态改为"未完成"
		} else if ("1".equals(shjg)) {// 完成
			sbbyjl.setM13("2");  // 将状态改为"完成"
			//加签电子签章
			String m14 = sbbyjl.getM14();
			String[] oldPdfFile = m14.split("\\|\\|");
			//设置模板路径
			String filePath = request.getSession().getServletContext().getRealPath("/");
			//根据附件路径截取附件所在的目录
			String directory = oldPdfFile[0].substring(0, oldPdfFile[0].lastIndexOf("/"));
			//添加电子签章时所生成的pdf文件名
			String pdfFileName = directory + "/" + DateUtils.getDateRandom() + new Random().nextInt(100) + ".pdf";
			
			/*String img = userService.get(UserUtil.getCurrentShiroUser().getId()).getElecsignature();
			String[] elecSignature = img.split("\\|\\|");
			//在附件上添加电子签章
			PageSbssglSbysController.pdfAddElecSignature(filePath + oldPdfFile[0], filePath + pdfFileName, filePath + elecSignature[0]);*/
			//将实体中的附件设置为带有电子签章的pdf文件
			sbbyjl.setM14(pdfFileName + "||" + oldPdfFile[1]);
		}
		sBSSGLSbbyTaskSecService.updateInfo(sbbyjl);// 修改设备二级保养计划的状态
		
		SBSSGL_SHJLEntity shjl = new SBSSGL_SHJLEntity();
		Timestamp t = DateUtils.getSystemTime();
		shjl.setS1(t);
		shjl.setS2(t);
		shjl.setS3(0);
		shjl.setM1(shjg);// 审核结果
		shjl.setShrid(Long.parseLong(UserUtil.getCurrentShiroUser().getId()+""));//审核人id
		shjl.setM2(btgyy);// 不通过原因
		shjl.setM3(sbbyjl.getM14());//附件
		shjl.setId2(id);// 设备二级保养计划id
		shjl.setM4("4");// 类别，4代表设备二级保养计划的审核记录
		sBSSGLShjlService.addShJlInfo(shjl);//保存审核记录信息
		
		return datasuccess;
	}
	
	/**
	 * 查看审核记录页面跳转
	 */
	@RequestMapping(value = "viewshjl/{id}")
	public String viewshjl(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		return "sbssgl/sbbyjl/sec/viewshjl";
	}
	
	/**
	 * 审核记录list
	 * @param request
	 * @return
	 */
	@RequestMapping(value="shjllist/{id}")
	@ResponseBody
	public Map<String, Object> shjlList(@PathVariable("id") Long id, HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("sbbytasksecid", id);
		return sBSSGLShjlService.shjlDataGrid(map);
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{taskid}", method = RequestMethod.GET)
	public String view(@PathVariable("taskid") Long taskid, Model model, HttpServletRequest request) {
		Map<String, Object> entity = sBSSGLSbbyTaskService.findById(taskid);
		model.addAttribute("entity", entity);// 年度、企业名称、部门名称、编制日期的信息
		model.addAttribute("flag", request.getParameter("flag"));// 页面上的查看和完成情况功能的页面时同一个，只不过查看时不显示状态和操作栏，所以需要通过flag值来判断，null代表查看， 1代表完成情况
		return "sbssgl/sbbyjl/sec/view";
	}
	
	/**
	 * 导出设备二级保养计划
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sbtype", request.getParameter("sbtypesec"));
		map.put("qyname", request.getParameter("qynamesec"));
		map.put("year", request.getParameter("yearsec"));
		map.put("deptid", request.getParameter("deptidsec"));
		map.put("bztime", request.getParameter("bztimesec"));
		map.put("bzrname", request.getParameter("bzrnamesec"));
		map.putAll(getAuthorityMap());
		sBSSGLSbbyTaskSecService.exportExcel(response, map, request);
	}
	
	/**
     * 打包压缩下载保养记录文件
     */
    @RequestMapping(value = "exportjl")
    public void downLoadZipFile(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	String zipName = "aa.zip";
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("sbtype", request.getParameter("sbtypefir"));
		map.put("qyname", request.getParameter("qynamefir"));
		map.put("year", request.getParameter("yearfir"));
		map.put("month", request.getParameter("month"));
		map.put("deptid", request.getParameter("deptidfir"));
		map.put("bztime", request.getParameter("bztimefir"));
		map.put("bzrname", request.getParameter("bzrnamefir"));
		map.putAll(getAuthorityMap());
        
        List<Map<String, Object>> fileList = sBSSGLSbbyTaskSecService.getFileList(map);//查询数据库中记录
        response.setContentType("APPLICATION/OCTET-STREAM");  
        response.setHeader("Content-Disposition","attachment; filename="+zipName);
        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
        try {
            for(Map<String, Object> file: fileList){
            	if (file.get("fj") != null) {
            		String[] fileUrl = file.get("fj").toString().split("\\|\\|");
            		ZipUtils.doCompress(request.getSession().getServletContext().getRealPath("/")+fileUrl[0], out);
                    response.flushBuffer();
				}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            out.close();
        }
    }

}
