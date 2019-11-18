package com.cczu.model.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.FXGK_AccidentRisk;
import com.cczu.model.service.FxgkFxxxService;
import com.cczu.model.service.TdicDangerFactorIdentifyService;
import com.cczu.model.service.YhpcStoppointService;
import com.cczu.model.service.YhpcYhpcdService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.QRCode;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.comm.utils.wordToPDF;
import com.cczu.sys.system.entity.Department;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

/**
 * 风险管控---风险点辨识
 * @author jason
 * @date 2017年8月8日
 */
@Controller
@RequestMapping("fxgk/fxxx")
public class PageFxgkfxxxController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private FxgkFxxxService sxgkfxxxService;
	@Autowired
	private TdicDangerFactorIdentifyService identifyService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private YhpcYhpcdService yhpcYhpcdService;
	@Autowired
	private BisQyjbxxServiceImpl bisQyjbxxServiceImpl;
	@Autowired
	private YhpcStoppointService yhpcStoppointService;
	private static final int FTL_A3_HEIGHT = 8600;//模板导出高度
	private static final int FTL_A2_HEIGHT = 10900;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		if(request.getParameter("sys")!=null &&"1".equals(request.getParameter("sys"))){
			model.addAttribute("sys", "sys");
			if(request.getParameter("mflag")!=null &&(request.getParameter("mflag"))!="")
				model.addAttribute("fxdj", request.getParameter("mflag"));
		}
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("usertype", sessionuser.usertype);
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "fxgk/fxdxx/index";
				else
					return "fxgk/fxdxx/qyindex";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "fxgk/fxdxx/index";
		}	
	}

	/**
	 * tab页列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index/{id}")
	public String tabindex(@PathVariable Long id, Model model) {
		model.addAttribute("qyid", id);
		return "fxgk/fxdxx/index";

	}

	/**
	 * 风险点分析图跳转
	 * @param model
	 */
	@RequestMapping(value = "index2")
	public String fxtindex(HttpServletRequest request, Model model) {
		model.addAttribute("qyname", request.getParameter("qyname"));
		return "fxgk/fxdxx/index";

	}
	
	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "ajlist")
	@ResponseBody
	public Map<String, Object> ajgetData(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		//删除过期停产数据
		yhpcStoppointService.deleteStaleData();
		
		Map<String, Object> map = getPageMap(request);	
		//map.put("province", request.getParameter("province"));
		//map.put("city", request.getParameter("city"));
		//map.put("country", request.getParameter("country"));
		map.put("name", request.getParameter("fxgk_fxxx_name"));
		map.put("fxlb", request.getParameter("fxgk_fxxx_fxlb"));
		map.put("fxfj", request.getParameter("fxgk_fxxx_fxfj"));
		map.put("sglx", request.getParameter("fxgk_fxxx_sglx"));
		map.put("ewm", request.getParameter("fxgk_fxxx_ewm"));
		map.put("xiangzhen", request.getParameter("fxgk_fxxx_xz"));
		map.put("wgxzqy", request.getParameter("fxgk_fxxx_xzqy"));
		map.put("qyname", request.getParameter("fxgk_fxxx_qyname"));

		//判断用户部门权限
		Department dep=sessionuser.getDep();
		if(dep!=null){
			String qxbs=dep.getM4();
			if(qxbs==null){ }
			else if(qxbs.equals("1"))//本级
				map.put("depcode1", dep.getCode());
			else if(qxbs.equals("2"))//下级
				map.put("depcode2", dep.getCode());
		}
		
		map.putAll(getAuthorityMap());
		return sxgkfxxxService.dataGrid(map);

	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "ajlist/{qyname}")
	@ResponseBody
	public Map<String, Object> fbtajgetData(HttpServletRequest request,@PathVariable String qyname) {
		//删除过期停产数据
		yhpcStoppointService.deleteStaleData();
		
		Map<String, Object> map = getPageMap(request);
		if(request.getParameter("panduan")==null){
			map.put("qyname", qyname);
		}else{
			map.put("qyname", request.getParameter("fxgk_fxxx_qyname"));
		}
		map.put("name", request.getParameter("fxgk_fxxx_name"));
		map.put("fxlb", request.getParameter("fxgk_fxxx_fxlb"));
		map.put("fxfj", request.getParameter("fxgk_fxxx_fxfj"));
		map.put("sglx", request.getParameter("fxgk_fxxx_sglx"));
		map.put("ewm", request.getParameter("fxgk_fxxx_ewm"));
		map.put("xiangzhen", request.getParameter("fxgk_fxxx_xz"));
		map.put("wgxzqy", request.getParameter("fxgk_fxxx_xzqy"));
		map.putAll(getAuthorityMap());
		return sxgkfxxxService.dataGrid(map);

	}
	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "tablist/{qyid}")
	@ResponseBody
	public Map<String, Object> getDataTab(@PathVariable("qyid") Integer qyid, HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("qyid", qyid);
		return sxgkfxxxService.dataGrid(map);
	}
	
	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("fxgk:fxxx:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
	//	String qyid = request.getParameter("qyid") == null ? "" : request.getParameter("qyid");
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String,Object> sgfx = new HashMap<>();
		//如果是企业，获取企业平面图
		if(sessionuser.getUsertype().equals("1")){
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			sgfx.put("pmt", StringUtils.defaultString(be.getM33_3()));
			model.addAttribute("qyid", sessionuser.getQyid());
			
			sgfx.put("m13", be.getM23());
			sgfx.put("m14", be.getM25());
			
			model.addAttribute("lng", be.getM16());
			model.addAttribute("lat", be.getM17());
		}
		//生成二维码编码
		sgfx.put("bindcontent",UUID.randomUUID().toString().replaceAll("-", ""));
		model.addAttribute("sgfx",sgfx);
		model.addAttribute("usertype",sessionuser.getUsertype());
		model.addAttribute("action", "createSub");
		return "fxgk/fxdxx/form";
	}

	
	/**
	 * 添加信息
	 * 
	 * @param request
	 * model
	 */
	@RequiresPermissions("fxgk:fxxx:add")
	@RequestMapping(value = "createSub")
	@ResponseBody
	public String create(FXGK_AccidentRisk entity,HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		String bdnrids=request.getParameter("bdnrids");
		if(sessionuser.getDep()!=null)
			entity.setDepid(sessionuser.getDep().getId());
		if (request.getParameter("aprobability2")!=null&&request.getParameter("aprobability2")!=""){
			entity.setAprobability(request.getParameter("aprobability2"));
			entity.setAseverity(request.getParameter("aseverity2"));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  风险管控--风险点信息  【增加操作】");

		return sxgkfxxxService.addInfo(entity,bdnrids);
	}

	/**
	 * 复制页面跳转
	 * @param id
	 *  model
	 */
	@RequiresPermissions("fxgk:fxxx:addfz")
	@RequestMapping(value = "addfz/{id}", method = RequestMethod.GET)
	public String addfz(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		Map<String, Object> bis = sxgkfxxxService.findInforById(id);
		//获取企业平面图
		String url=yhpcYhpcdService.findpmtByqyid(Long.parseLong(bis.get("id1").toString()));
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		bis.put("pmt", url);

		//重新分配二维码
		bis.put("bindcontent",UUID.randomUUID().toString().replaceAll("-", ""));

		if("1".equals(sessionuser.getUsertype()))
			model.addAttribute("qyid", sessionuser.getQyid());
		model.addAttribute("sgfx", bis);
		model.addAttribute("usertype",sessionuser.getUsertype());
		// 返回页面
		model.addAttribute("id1", id);
		model.addAttribute("action", "addfz");
		return "fxgk/fxdxx/form";
	}

	/**
	 * 复制信息
	 *
	 * @param request
	 * model
	 */
	@RequiresPermissions("fxgk:fxxx:addfz")
	@RequestMapping(value = "addfz")
	@ResponseBody
	public String addfz(FXGK_AccidentRisk entity,HttpServletRequest request) {
		String bdnrids=request.getParameter("bdnrids");
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  风险管控--风险点信息  【复制操作】");

		return sxgkfxxxService.addInfo(entity,bdnrids);
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 *  model
	 */
	@RequiresPermissions("fxgk:fxxx:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model,
			HttpServletRequest request) {
		Map<String, Object> bis = sxgkfxxxService.findInforById(id);
		
		//根据企业id获取企业对象
		BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(StringUtils.toLong(bis.get("id1")));
		//获取企业平面图
		bis.put("pmt", StringUtils.defaultString(be.getM33_3()));
		//如果无风险点坐标获取企业坐标
		if(bis.get("lng")==null||bis.get("lng").equals("")){
			model.addAttribute("lng", be.getM16());
			model.addAttribute("lat", be.getM17());
		}
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		
		
		if(bis.get("bindcontent")==null||bis.get("bindcontent").equals(""))
			bis.put("bindcontent",UUID.randomUUID().toString().replaceAll("-", ""));
		if("1".equals(sessionuser.getUsertype()))
			model.addAttribute("qyid", sessionuser.getQyid());
		model.addAttribute("sgfx", bis);
		model.addAttribute("usertype",sessionuser.getUsertype());
		model.addAttribute("radio1",bis.get("radio1"));
		
		
		// 返回页面
		model.addAttribute("id1", id);
		model.addAttribute("action", "updateSub");
		return "fxgk/fxdxx/form";
	}

	/**
	 * 修改信息
	 * 
	 * @param request
	 *  model
	 */
	@RequiresPermissions("fxgk:fxxx:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String update(FXGK_AccidentRisk entity,HttpServletRequest request) {
		String bdnrids=request.getParameter("bdnrids");
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  风险管控--风险点信息  【修改操作】");

		return sxgkfxxxService.updateInfo(entity,bdnrids);
	}

	/**
	 * 删除信息
	 */
	@RequiresPermissions("fxgk:fxxx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		System.out.println(ids);
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			long qyid = sxgkfxxxService.findById(Long.parseLong(arrids[i])).getID1(); 
			yhpcYhpcdService.deletexjnrbyid1(Long.parseLong(arrids[i]));
			sxgkfxxxService.deleteInfo(Long.parseLong(arrids[i]));
			sxgkfxxxService.updateQyfxdj(qyid);
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  风险管控--风险点信息  【删除操作】");

		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id
	 *  ,model
	 */
	@RequiresPermissions("fxgk:fxxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {

		Map<String, Object> sgfx = sxgkfxxxService.findInforById(id);
		BIS_EnterpriseEntity entity = qyjbxxServiceImpl.findInfoById(Long.parseLong(sgfx.get("id1").toString()));
		sgfx.put("pmt", entity.getM33_3());
		
		model.addAttribute("sgfx", sgfx);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("qyname", entity.getM1());
		// 返回页面
		return "fxgk/fxdxx/view";
	}

  
	/**
	 * 根据行业获取行业类别
	 * @param hy
	 * @return
	 */
	@RequestMapping(value = "json1")
	@ResponseBody
	public String json1(String hy) {
		Map<String, Object> map = new HashMap<>();
		map.put("m1", hy);

		return identifyService.getM2Data(map);
	}
	/**
	 * 根据危险类型
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "accident")
	@ResponseBody
	public String accident() {

		return sxgkfxxxService.getAllType();
	}

	/**
	 * 根据行业,行业类别获取工段
	 * 
	 * @param hy
	 * @return
	 */
	@RequestMapping(value = "json2")
	@ResponseBody
	public String json2(String hy, String hylb) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m1", hy);
		map.put("m2", hylb);
		return identifyService.getM3Data(map);
	}

	/**
	 * 根据行业,行业类别,工段获取部位 
	 * 
	 * @param hy
	 * @return
	 */
	@RequestMapping(value = "json3")
	@ResponseBody
	public String json3(String hy, String hylb, String gd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m1", hy);
		map.put("m2", hylb);
		map.put("m3", gd);
		return identifyService.getM4Data(map);
	}

	/**
	 * 根据行业,行业类别,工段,部位获取该条记录信息
	 * 
	 * @param hy
	 * @return
	 */
	@RequestMapping(value = "json4")
	@ResponseBody
	public String json4(String hy, String hylb, String gd, String bw) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m1", hy);
		map.put("m2", hylb);
		map.put("m3", gd);
		map.put("m4", bw);
		return identifyService.getEntityData(map);
	}

	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindexdao(Model model) {
		model.addAttribute("url","fxgk/fxxx/export");
		return "common/formexcel";
	}
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("fxgk:fxxx:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colval", request.getParameter("colval"));

		map.put("coltext", request.getParameter("coltext"));
		map.put("name", request.getParameter("fxgk_fxxx_name"));
		map.put("fxlb", request.getParameter("fxgk_fxxx_fxlb"));
		map.put("fxfj", request.getParameter("fxgk_fxxx_fxfj"));
		map.put("sglx", request.getParameter("fxgk_fxxx_sglx"));
		map.put("ewm", request.getParameter("fxgk_fxxx_ewm"));
		map.put("xiangzhen", request.getParameter("fxgk_fxxx_xz"));
		map.put("wgxzqy", request.getParameter("fxgk_fxxx_xzqy"));
		map.put("qyname", request.getParameter("fxgk_fxxx_qyname"));
		map.putAll(getAuthorityMap());
		sxgkfxxxService.exportExcel(response, map);
	}
	
	
	/**
	 * 导出word
	 * 
	 */
	@RequiresPermissions("fxgk:fxxx:export")
	@RequestMapping(value = "exportbs/{id}")
	@ResponseBody
	public String export3(@PathVariable("id") Long id,
			HttpServletRequest request, HttpServletResponse response) {
		FXGK_AccidentRisk sgfx = sxgkfxxxService.findById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", sgfx.getM1());
		map.put("buwei", sgfx.getM6());
		map.put("guankong", sgfx.getM10());
		map.put("yingji", sgfx.getM11());
		map.put("leixing", sgfx.getM8());
		map.put("bianhao", sgfx.getID());
		// 获取图片地址
		String  image = "";
		String  image2 = "";
		List<String> list  = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		if (sgfx.getM16() != null) {
			String[] url = sgfx.getM16().split(",");
			for (String u : url) {
				String[] url2 = u.split("[||]");
				image = WordUtil.getImageStr(request.getSession().getServletContext().getRealPath("/")  + url2[0]);
				list.add(image);
			}
		}

		if (sgfx.getM17() != null) {
			String[] url = sgfx.getM17().split(",");
			for (String u : url) {
				String[] url2 = u.split("[||]");
				image2 = WordUtil.getImageStr(request.getSession().getServletContext().getRealPath("/") + url2[0]);
				list2.add(image2);
			}
		}

		map.put("image", list);
		map.put("image2", list2);

		// 判断颜色
		//String color = "";
		switch (sgfx.getM9()) {
		case "1":
			//color = "FF0000";
			map.put("dengji", "红");
			break;
		case "2":
			//color = "FF8C00";
			map.put("dengji", "橙");
			break;
		case "3":
		   //color = "FFFF00";
			map.put("dengji", "黄");
			break;
		case "4":
			//color = "1E90FF";
			map.put("dengji", "蓝");
			break;
		}
		map.put("bianhao", sgfx.getM18());
		//map.put("color", color);
		
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		
		WordUtil.ireportWord(map, "jdwxysmb.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	/**
	 * 导出彩色风险告知卡
	 * 
	 * @param id
	 *            ,model
	 * @throws IOException
	 */
	@RequiresPermissions("fxgk:fxxx:export") 
	@RequestMapping(value = "exportfxgz/{id}")
	@ResponseBody
	public String viewka(@PathVariable("id") Long id, Model model,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		//不带项目名的服务器物理路径
        String webAddress= request.getSession().getServletContext().getRealPath("/").replace(request.getContextPath().replace("/","\\"),"");
		
		String type = request.getParameter("type");
		Map<String,Object> map = new HashMap<>();
		FXGK_AccidentRisk sgfx = sxgkfxxxService.find_sgfx_ById(id);
		//反转html字符并替换<>&字符串
		com.cczu.util.common.StringUtils.parseBeanForWord(sgfx);
		BIS_EnterpriseEntity bis=bisQyjbxxServiceImpl.findInfoById(sgfx.getID1());
		
		//匹配（d）的字符在"（"加上freemarker的换行符
		sgfx.setM10(sgfx.getM10().replaceAll("(\\(|（|\\b)(\\d{1,2}\\s*[\\)）]\\b)", "<w:br/>$1$2").replaceFirst("<w:br/>", ""));
		sgfx.setM11(sgfx.getM11().replaceAll("(\\(|（|\\b)(\\d{1,2}\\s*[\\)）]\\b)", "<w:br/>$1$2"));
		map.put("sgfx", sgfx);
		map.put("qyname", bis.getM1());

		// 获取图片地址
		String image = "", image2 = "";
		List<String> list = new ArrayList<String>();
		List <String>list2 = new ArrayList<String>();
		if (sgfx.getM16() != null && !sgfx.getM16().equals("")) {
			String[] url = sgfx.getM16().split(",");
			for (String u : url) {
				String[] url2 = u.split("[||]");
				//服务器没有项目名，本地需要把 str的项目名去掉
				
				image = WordUtil.getImageStr(webAddress + url2[0]);
				list.add(image);
			}
		}else{//未上传现场照片的用 默认图片
				image = WordUtil.getImageStr(webAddress+"/static/model/images/fxbs_nopic.jpg");
				list.add(image);
		}

		if (!StringUtils.isEmpty(sgfx.getM17())) {
			String[] url = sgfx.getM17().split(",");
			int j=("A2".equals(type))?3:4;
			int i=0;
			for (String u : url) {
				if(i<j){
					String[] url2 = u.split("[||]");
					image2 = WordUtil.getImageStr(webAddress + url2[0]);
					list2.add(image2);
					i++;
				}
			}
		}

		try {
			map.put("image", list.size()==0?"":list.get(0));
			map.put("image2", list2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 生成二维码
		String text = sgfx.getBindcontent();
		if (StringUtils.isEmpty(text)) {
			text = " ";
		}
		// 生成二维码
		String dowmloadPath = request.getSession().getServletContext().getRealPath("/")+"/download";
		map.put("orimg", WordUtil.getImageStr(dowmloadPath+"/"+QRCode.encode(text, dowmloadPath)));

		String color="";
		String colorcode="";
		String fillcolor="000000";
		switch (sgfx.getM9()) {
		case "1":
			color="红";
			colorcode="C00000";
			fillcolor="FFFFFF";
			break;
		case "2":
			color="橙";
			colorcode="FFA042";
			break;
		case "3":
			color="黄";
			colorcode="FFD700";
			break;
		case "4":
			color="蓝";
			colorcode="2894FF";
		default :
			color="蓝";
			colorcode="2894FF";
			break;
		}
		map.put("color", color);
		map.put("colorcode", colorcode);
		map.put("fillcolor", fillcolor);
		
		String M1=sgfx.getM1();
		if(M1.indexOf("/") != -1){
			M1=M1.replace("/", "");
		}
		//设置导出的文件名
		String filename =  bis.getM1()+ "_" + M1 + ".doc";
		
		int h1=sgfx.getM10().length();
		int h2=sgfx.getM11().length();
		if("A2".equals(type)){
			if(StringUtils.isEmpty(sgfx.getM10())||StringUtils.isEmpty(sgfx.getM11())){
				map.put("h1", FTL_A2_HEIGHT>>1);
				map.put("h2", FTL_A2_HEIGHT>>1);
			}else{
				int WEIGHT = 0;//优化高度权值
				map.put("h1", (int)(FTL_A2_HEIGHT*(((float)h1/(h1+h2)))+WEIGHT));
				map.put("h2", (int)(FTL_A2_HEIGHT*(((float)h2/(h1+h2)))-WEIGHT));
			}
			WordUtil.ireportWord(map, "sgfxA2.ftl", dowmloadPath, filename, request);
		}else if("A3".equals(type)){
			if(StringUtils.isEmpty(sgfx.getM10())||StringUtils.isEmpty(sgfx.getM11())){
				map.put("h1", FTL_A3_HEIGHT>>1);
				map.put("h2", FTL_A3_HEIGHT>>1);
			}else{
				int WEIGHT = 0;//优化高度权值
				map.put("h1", (int)(FTL_A3_HEIGHT*(((float)h1/(h1+h2)))+WEIGHT));
				map.put("h2", (int)(FTL_A3_HEIGHT*(((float)h2/(h1+h2)))-WEIGHT));
			}
			WordUtil.ireportWord(map, "sgfxA3.ftl", dowmloadPath, filename, request);
		}
		return filename;
	}
	/**
	 * 导出彩色风险告知卡
	 * 
	 * @param id
	 *            ,model
	 * @throws 
	 * @throws IOException
	 */
	@RequiresPermissions("fxgk:fxxx:export") 
	@RequestMapping(value = "exportpdf/{id}")
	@ResponseBody
	public String exportPdf(@PathVariable("id") Long id, Model model,HttpServletResponse response, HttpServletRequest request) throws Exception{
		String filepath = request.getSession().getServletContext().getRealPath("/");
		String wordpath=viewka(id,model,response,request);
		String pdfpath=wordpath.replace(".doc", ".pdf");
		//将word转pdf
		wordToPDF.WordToPDF(filepath+wordpath, filepath+pdfpath);				
		return pdfpath ;
		
	}
	
	/**
	 * 导入页面跳转
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "exinjump", method = RequestMethod.GET)
	public String exin(Model model) {
		model.addAttribute("action", "exin");
		return "common/importForm";
	}
	
	/**
	 * 导入
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = sxgkfxxxService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}
	
	//跳转已绑定巡检内容list
	@RequestMapping(value = "bdxjnr/{id}", method = RequestMethod.GET)
	public String bdxjnr(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		String qyid = request.getParameter("qyid");
		model.addAttribute("id1", id);
		model.addAttribute("qyid", qyid);
		return "fxgk/fxdxx/xjnr";
	}
	
	/**
	 * 绑定巡检内容list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "xjnrlist/{id1}")
	@ResponseBody
	public Map<String, Object> xjnrlist(@PathVariable("id1") String id1, HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		if(!"add".equals(id1))
		map.put("id1", id1);
		return sxgkfxxxService.xjnrdataGrid(map);

	}
	
	/**
	 * 绑定巡检内容list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "xjnrlist2/{id1}")
	@ResponseBody
	public Map<String, Object> xjnrlist2(@PathVariable("id1") Long id1, HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("id1", id1);
		return sxgkfxxxService.xjnrdataGrid2(map);

	}
	
	/**
	 * 绑定巡检内容list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "xjnrlist3")
	@ResponseBody
	public Map<String, Object> xjnrlist3(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("jcdid", request.getParameter("jcdid"));
		map.put("xjjlid", request.getParameter("xjjlid"));
		map.put("type", request.getParameter("type"));
		return sxgkfxxxService.xjnrdataGrid3(map);

	}
	
	/**
	 * 删除巡检内容信息
	 */
	@RequestMapping(value = "xjnrdelete/{ids}")
	@ResponseBody
	public String xjnrdelete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		System.out.println(ids);
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			sxgkfxxxService.deleteXjnr(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	//跳转巡检内容list
	@RequestMapping(value = "xjnrcreate/{id1},{qyid}", method = RequestMethod.GET)
	public String xjnrcreate(@PathVariable("id1") Long id1,@PathVariable("qyid") Long qyid, Model model,HttpServletRequest request) {
		model.addAttribute("id1", id1);
		model.addAttribute("qyid", qyid);
		return "fxgk/fxdxx/xjnrall";
	}
	
	/**
	 * 巡检内容list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "xjnralllist")
	@ResponseBody
	public Map<String, Object> xjnralllist(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("id1", request.getParameter("id1"));//风险点id
		map.put("qyid", request.getParameter("qyid"));
		map.put("yhjb", request.getParameter("yhjb"));
		map.put("checktitle", request.getParameter("checktitle"));
		return sxgkfxxxService.xjnralldataGrid(map);

	}
	
	/**
	 * 绑定巡检内容
	 * 
	 * @param request,model
	 */
	@RequestMapping(value = "buildCheckContent/{ids}")
	@ResponseBody
	public String buildCheckContent(@PathVariable("ids") String ids,HttpServletRequest request) {
		String id1= request.getParameter("id1");//风险点
		String[] arrids = ids.split(",");
		try{
			for (int i = 0; i < arrids.length; i++) {
				sxgkfxxxService.bulidCheckContent(Long.parseLong(id1),Long.parseLong(arrids[i]));
			}
		}catch(Exception e){
			return "绑定失败!";
		}
		// 返回结果
		return "绑定成功!";

	}
	
	/**
	 * 风险点巡检记录跳转
	 * @param fxdid
	 */
	@RequestMapping(value = "viewXjjl/{fxdid}")
	public String getfxdxjjl(@PathVariable("fxdid") long fxdid, Model model) {
		model.addAttribute("jcdid",fxdid);
		model.addAttribute("jcdtype","1");
		return "yhpc/xjdzt/xjindex";
	}

	/**
	 * 根据风险等级获取风险点信息
	 * @param fxfj
	 */
	@RequestMapping(value = "getFxdInfo/{fxfj}")
	@ResponseBody
	public String getFxdInfo(@PathVariable("fxfj") String fxfj, Model model) {
		Map<String, Object> map = new HashMap<>();
		map.put("fxfj", fxfj);
		map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		return sxgkfxxxService.getJsonByMap(map);
	}

	/**
	 * 根据qyid获取该企业中的各个风险等级数量
	 *
	 */
	@RequestMapping(value = "getFxdCount")
	@ResponseBody
	public Map<String, Object> getFxdInfo() {
		return sxgkfxxxService.getFxdCount(UserUtil.getCurrentShiroUser().getQyid());
	}

	/**
	 * 根据qyid获取该企业的风险等级值
	 *
	 */
	@RequestMapping(value = "getFxdjz")
	@ResponseBody
	public Map<String, Object> getFxdjz() {
		return sxgkfxxxService.getQyFxdjzByQyid(UserUtil.getCurrentShiroUser().getQyid());
	}

	/**
	 * 根据风险点id查询信息
	 *
	 */
	@RequestMapping(value = "getFxdInfo/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getFxdInfoById(@PathVariable("id") Long id) {
		return sxgkfxxxService.findInforById(id);
	}
	
}
