package com.cczu.model.sbssgl.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_GNYSEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_QGSBEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBSQEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBYSEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SHJLEntity;
import com.cczu.model.sbssgl.service.SBSSGLSbsqService;
import com.cczu.model.sbssgl.service.SBSSGLSbysService;
import com.cczu.model.sbssgl.service.SBSSGLShjlService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.PdfAddImg;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;
import com.cczu.util.common.WordUtil;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/**
 * 设备设施管理-设备验收controller
 */
@Controller
@RequestMapping("sbssgl/sbys")
public class PageSbssglSbysController extends BaseController {

	@Autowired
	private SBSSGLSbysService sBSSGLSbysService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private SBSSGLSbsqService sBSSGLSbsqService;
	@Autowired
	private SBSSGLShjlService sBSSGLShjlService;
	@Autowired
	private UserService userService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else{
			return "../error/403";
		}
		return "sbssgl/sbys/index";
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		map.put("m1", request.getParameter("m1"));
		map.put("m2", request.getParameter("m2"));
		map.put("m3", request.getParameter("m3"));
		map.put("m4", request.getParameter("m4"));
		map.put("m34", request.getParameter("m34"));
		map.putAll(getAuthorityMap());
		return sBSSGLSbysService.dataGrid(map);
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			//删除设备申请信息
			sBSSGLSbysService.deleteSbsqById(Long.parseLong(aids[i]));
			//删除关联的功能验收信息
			sBSSGLSbysService.deleteGnysBySbysid(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		SBSSGL_SBYSEntity sbys = sBSSGLSbysService.find(id);	
		List<SBSSGL_GNYSEntity> gnyslist = sBSSGLSbysService.findInfoBySbysid(id);
		model.addAttribute("sbys", sbys);
		model.addAttribute("gnyslist", gnyslist);
		return "sbssgl/sbys/view";
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "yscreate" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		String qgsbid = request.getParameter("qgsbid");//请购设备id
		SBSSGL_QGSBEntity qgsb = sBSSGLSbsqService.findQgsbInfoByQgsbid(Long.parseLong(qgsbid));
		model.addAttribute("action", "create");
		Map<String,Object> sbys = new HashMap<String, Object>();
		sbys.put("m3", DateUtils.getDate());
		model.addAttribute("sbys", sbys);
		model.addAttribute("qgsb", qgsb);
		model.addAttribute("qgsbid", qgsbid);
		return "sbssgl/sbys/ysform";
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		Map<String,Object> sbys = new HashMap<String, Object>();
		sbys.put("m3", DateUtils.getDate());
		model.addAttribute("sbys", sbys);
		return "sbssgl/sbys/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(SBSSGL_SBYSEntity entity, Model model,HttpServletRequest request) {
		String datasuccess="success";
		if(!StringUtils.isEmpty(entity.getM30())){
			if(entity.getM30().equals("1")){//不需校验
				entity.setM31("1");
				entity.setM32("");
			}else{//需校验
				if(!StringUtils.isEmpty(entity.getM31())){
					if(entity.getM31().equals("1")){//不选择新购买设备
						entity.setM32("");
					}
				}
			}
		}
		entity.setQyid(UserUtil.getCurrentShiroUser().getQyid());
		entity.setM34("0");
		sBSSGLSbysService.addInfo(entity);
			
		//添加功能验收
		addGnys(entity,request);
		
		if (entity.getQgsbid() != null) {
			Long qgsbid = entity.getQgsbid();
			sBSSGLSbsqService.updQgsbInfoById(qgsbid);//将该请购设备的状态改为已验收
			
			SBSSGL_QGSBEntity qgsb = sBSSGLSbsqService.findQgsbInfoByQgsbid(qgsbid);//根据请购设备id查找到该请购设备实体
			Long sbsqid = qgsb.getSbsqid();//获得该请购设备的设备申请id
			List<SBSSGL_QGSBEntity> qgsbList = sBSSGLSbsqService.findInfoBySbsqid(sbsqid);//根据设备申请id查找到所有关联的请购设备集合
			boolean flag = true;
			for (SBSSGL_QGSBEntity qgsbEntity : qgsbList) {//循环遍历集合
				if ("0".equals(qgsbEntity.getM7())) {//如果该请购设备的状态为0（未验收），那么就退出整个循环
					flag = false;
					break;
				}
			}
			if (flag) {//如果集合中所有请购设备的状态不为0，也就是为1时，修改对应申请记录中的验收状态为全部验收
				SBSSGL_SBSQEntity sbsqEntity = sBSSGLSbsqService.find(sbsqid);//根据设备申请id查找到该设备申请记录实体
				sbsqEntity.setM12("1");//修改状态为全部验收
				sBSSGLSbsqService.updYsStatus(sbsqid);
			}
		}
		
		//返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		SBSSGL_SBYSEntity sbys = sBSSGLSbysService.find(id);	
		List<SBSSGL_GNYSEntity> gnyslist = sBSSGLSbysService.findInfoBySbysid(id);
		model.addAttribute("sbys", sbys);
		model.addAttribute("gnyslist", JsonMapper.getInstance().toJson(gnyslist));
		//返回页面
		model.addAttribute("action", "update");
		return "sbssgl/sbys/form";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(SBSSGL_SBYSEntity entity, Model model,HttpServletRequest request){
		String datasuccess="success";
		if(!StringUtils.isEmpty(entity.getM30())){
			if(entity.getM30().equals("1")){//不需校验
				entity.setM31("1");
				entity.setM32("");
			}else{//需校验
				if(!StringUtils.isEmpty(entity.getM31())){
					if(entity.getM31().equals("1")){//不选择新购买设备
						entity.setM32("");
					}
				}
			}
		}
		sBSSGLSbysService.updateInfo(entity);
		
		//删除关联的功能验收信息
		sBSSGLSbysService.deleteGnysBySbysid(entity.getID());
		
		//添加功能验收
		addGnys(entity,request);
		
		//返回结果
		return datasuccess;
	}
	
	public void addGnys(SBSSGL_SBYSEntity entity, HttpServletRequest request){
		String csxm[] = request.getParameterValues("csxm");//测试项目
		String csjg[] = request.getParameterValues("csjg");//测试结果
		
		//循环插入功能验收表
		if(csxm != null){
			for(int i=0;i<csxm.length;i++){
				SBSSGL_GNYSEntity gnys = new SBSSGL_GNYSEntity();
				gnys.setSbysid(entity.getID());
				gnys.setM1(csxm[i]);
				gnys.setM2(csjg[i]);
				sBSSGLSbysService.addGnys(gnys);
			}
		}
	}
	
	/**
	 * 导出设备验收单word
	 */
	@RequestMapping(value = "export/{id}")
	@ResponseBody
	public String getWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = sBSSGLSbysService.getWord(id);
		//设置导出的文件名
		String filename = "设备验收单_" + DateUtils.getDateRandom() + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "sbys.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	/**
	 * 导出设备验收单pdf
	 */
	/*@RequestMapping(value = "export/{id}")
	@ResponseBody
	public String getWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = sBSSGLSbysService.getWord(id);
		//设置导出的文件名
		String fileName = "设备验收单_" + DateUtils.getDateRandom() + new Random().nextInt(100) + ".doc";
		//设置另存为文件的文件名
		String saveAsFileName = "设备验收单_" + DateUtils.getDateRandom() + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "sbysd.ftl", filePath, filename, request);
		WordUtil.ireportWord(map, "sbys.ftl", filePath, fileName, request);
		try {
			 //将系统生成的word另存为同目录下的另一个文件
			 wordToPDF.jacobchange(filePath, fileName, saveAsFileName);
			 
			 //将另存为的文件转换为pdf文件
			 wordToPDF.wordTopdf(filePath+saveAsFileName, filePath+saveAsFileName.replace("doc", "pdf"));
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		//打开pdf文件
		return "/download/" + saveAsFileName.replace("doc", "pdf");
	}*/
	
	/**
	 * 上传附件页面跳转
	 */
	@RequestMapping(value = "uploadindex/{id}")
	public String uploadindex(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		return "sbssgl/sbys/upload";
	}
	
	/**
	 * 上传附件
	 */
	@RequestMapping(value = "uploadfj")
	@ResponseBody
	public String uploadfj(long id,String m35) {
		String datasuccess="success";
		SBSSGL_SBYSEntity sbys = sBSSGLSbysService.find(id);
		sbys.setM34("1");
		sbys.setM35(m35);
		sBSSGLSbysService.updateInfo(sbys);
		return datasuccess;
	}
	
	/**
	 * 改变状态
	 */
	@RequestMapping(value = "changezt/{id}/{type}")
	@ResponseBody
	public String changezt(@PathVariable("id") Long id,@PathVariable("type") String type) {
		String datasuccess="操作成功";
		SBSSGL_SBYSEntity sbys = sBSSGLSbysService.find(id);
		sbys.setM34(type);
		sBSSGLSbysService.updateInfo(sbys);
		return datasuccess;
	}
	
	/**
	 * 审核结果页面跳转
	 */
	@RequestMapping(value = "shjg/{id}")
	public String shjg(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		return "sbssgl/sbys/shjg";
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
		SBSSGL_SBYSEntity sbys = sBSSGLSbysService.find(id);
		if ("0".equals(shjg)) {//不通过
			sbys.setM34("3");  //将状态改为"不通过"
		} else if ("1".equals(shjg)) {//通过
			sbys.setM34("2");  //将状态改为"通过"
			String m35 = sbys.getM35();
			String[] oldPdfFile = m35.split("\\|\\|");
			 //如果该条记录的审核结果是"通过"
			 if (StringUtils.isNotEmpty(sbys.getM34()) && "2".equals(sbys.getM34())) {
				//设置模板路径
				String filePath = request.getSession().getServletContext().getRealPath("/");
				//根据附件路径截取附件所在的目录
				String directory = oldPdfFile[0].substring(0, oldPdfFile[0].lastIndexOf("/"));
				//添加电子签章时所生成的pdf文件名
				String pdfFileName = directory + "/" + DateUtils.getDateRandom() + new Random().nextInt(100) + ".pdf";
				
				/*String img = userService.get(UserUtil.getCurrentShiroUser().getId()).getElecsignature();
				String[] elecSignature = img.split("\\|\\|");
				//在附件上添加电子签章
				pdfAddElecSignature(filePath + oldPdfFile[0], filePath + pdfFileName, filePath + elecSignature[0]);*/
				//将实体中的附件设置为带有电子签章的pdf文件
				sbys.setM35(pdfFileName + "||" + oldPdfFile[1]);
			 }

		}
		sBSSGLSbysService.updateInfo(sbys);//修改设备申请表的状态
		
		SBSSGL_SHJLEntity shjl = new SBSSGL_SHJLEntity();
		Timestamp t = DateUtils.getSystemTime();
		shjl.setS1(t);
		shjl.setS2(t);
		shjl.setS3(0);
		shjl.setM1(shjg);//审核结果
		shjl.setShrid(Long.parseLong(UserUtil.getCurrentShiroUser().getId()+""));//审核人id
		shjl.setM2(btgyy);//不通过原因
		shjl.setM3(sbys.getM35());//附件
		shjl.setId2(id);//设备验收id
		shjl.setM4("1");//类别，1代表设备验收的审核记录
		sBSSGLShjlService.addShJlInfo(shjl);//保存审核记录信息
		
		return datasuccess;
	}
	
	/**
	 * 查看审核记录页面跳转
	 */
	@RequestMapping(value = "viewshjl/{sbysid}")
	public String viewshjl(@PathVariable("sbysid") Long sbysid, Model model) {
		model.addAttribute("sbysid", sbysid);
		return "sbssgl/sbys/viewshjl";
	}
	
	/**
	 * 审核记录list
	 * @param request
	 * @return
	 */
	@RequestMapping(value="shjllist/{sbysid}")
	@ResponseBody
	public Map<String, Object> shjlList(@PathVariable("sbysid") Long sbysid, HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("sbysid",sbysid);
		return sBSSGLShjlService.shjlDataGrid(map);
	}
	
	/**
	 * pdf上添加电子签章
	 * @param filePath oldPdfFile路径 和 newPdfFile路径
	 * @param oldPdfFileName 模板文件的文件名
	 * @param newPdfFileName 生成的文件文件名
	 * @param imgFile        电子签章路径加文件名
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void pdfAddElecSignature(String oldPdfFile, String newPdfFile, String imgFile) throws IOException, DocumentException {
		File pdfFile = new File(oldPdfFile);
		byte[] pdfData = new byte[(int) pdfFile.length()];
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(pdfFile);
			inputStream.read(pdfData);
		} catch (IOException e) {
			throw e;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
		}

		String keyword = "设备保障部";

		List<float[]> positions = PdfAddImg.findKeywordPostions(pdfData, keyword);

		System.out.println("total:" + positions.size());
		
		
		// 模板文件路径
	    String templatePath = oldPdfFile;
	    // 生成的文件路径
	    String targetPath = newPdfFile;
	    // 图片路径
	    String imagePath = imgFile;
	    
	    // 读取模板文件
	    InputStream input = new FileInputStream(new File(templatePath));
	    PdfReader reader = new PdfReader(input);
	    PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(targetPath));
	    // 提取pdf中的表单
	    AcroFields form = stamper.getAcroFields();
	    form.addSubstitutionFont(BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));
	 
	    // 读图片
	    Image image = Image.getInstance(imagePath);
		
		if (positions != null && positions.size() > 0) {
			for (float[] position : positions) {
				System.out.print("pageNum: " + (int) position[0]);
				System.out.print("\tx: " + position[1]);
				System.out.println("\ty: " + position[2]);
				
				//获取添加图片页的长宽
				com.itextpdf.text.Rectangle pageSize = reader.getPageSize((int) position[0]);
				float width = pageSize.getWidth();
	            float height = pageSize.getHeight();
	            System.out.println(width);
	            System.out.println(height);
				
				
			    // 获取操作的页面
			    PdfContentByte under = stamper.getOverContent((int) position[0]);
			    // 根据域的大小缩放图片(图片大小)
			    image.scaleToFit(50, 30);
			    // 添加图片(左距，下距)
			    image.setAbsolutePosition(width*(float)(position[1]+0.15), height*(float)(1-position[2]-0.015));
			    under.addImage(image);
			 
			}
		}
		
		stamper.close();
	    reader.close();
	}
}
