package com.cczu.model.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cczu.model.entity.AQPX_CourseEntity;
import com.cczu.model.entity.AQPX_OutSourceEducationHistoryEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.AqpxWlfpxHistoryService;
import com.cczu.model.service.IAqpxKCxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.impl.BisYgxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.Global;
import com.cczu.sys.comm.utils.QRCode;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.DepartmentService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;


/**
 * @description 安全培训管理——外来方培训Controller
 * @author jason
 * @date 2018年1月25日
 */
@Controller
@RequestMapping("aqpx/wlfpx")
public class PageAqpxWlfpxZxxxController extends BaseController {
	
	 
	@Autowired
	private IAqpxKCxxService aqpxKCxxService;
	@Autowired
	private AqpxWlfpxHistoryService aqpxWlfpxHistoryService;
	@Autowired
	private BisYgxxServiceImpl ygxxService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	
	/**
	 * 首页显示外来方培训学习课程
	 * @param model	
	 */
	@RequestMapping(value="zxxx")
	public String ZXXX(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(StringUtils.isBlank((String)session.getAttribute("wlfname"))){
			model.addAttribute("flag", 0);
		}else{
			model.addAttribute("flag", 1);
		}
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyid", sessionuser.getQyid());
		map.put("type", 3);//外来方培训课程
		List<AQPX_CourseEntity> list=aqpxKCxxService.getList(map);
		model.addAttribute("kclist", list);
		return "aqpx/wlfpx/zxxx/index";
	}
	
	/**
	 * 外来方培训考试记录页面
	 * @param model	
	 */
	@RequestMapping(value="ksjlindex")
	public String ksjlindex(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "aqpx/wlfpx/ksjl/ajindex";
				else
					return "aqpx/wlfpx/ksjl/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "aqpx/wlfpx/ksjl/ajindex";
		}
	}
	
	/**
	 * 首页显示外来方培训学习课程
	 * @param model	
	 */
	@RequestMapping(value="zxks")
	public String ZXKS(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(StringUtils.isBlank((String)session.getAttribute("wlfname"))){
			model.addAttribute("flag", 0);
		}else{
			model.addAttribute("flag", 1);
		}
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyid", sessionuser.getQyid());
		map.put("type", 3);//外来方培训课程
		List<AQPX_CourseEntity> list=aqpxKCxxService.getList(map);
		model.addAttribute("kclist", list);
		return "aqpx/wlfpx/zxks/index";
	}
	
	
	
	/**
	 * 培训记录页面
	 * @param model
	 */
	@RequestMapping(value="pxjl/index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "aqpx/wlfpx/pxjl/ajindex";
				else
					return "aqpx/wlfpx/pxjl/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "aqpx/wlfpx/pxjl/ajindex";
		}
	}
	

	/**
	 * 培训记录list页面
	 * @param request
	 */
	@RequestMapping(value="pxjl/list")
	@ResponseBody
	public Map<String, Object> getData(Model model, HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("aqpx_wlpxjl_qyname"));
		map.put("name", request.getParameter("aqpx_wlpxjl_name"));
		map.putAll(getAuthorityMap());
		return aqpxWlfpxHistoryService.dataGrid(map);	
	}
	
	/**
	 * 培训记录添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("wlfpx:pxjl:add")
	@RequestMapping(value = "pxjl/create" , method = RequestMethod.GET)
	public String create(Model model) {
		AQPX_OutSourceEducationHistoryEntity pxjl=new AQPX_OutSourceEducationHistoryEntity();
		pxjl.setID1(UserUtil.getCurrentShiroUser().getQyid());
		pxjl.setM2(DateUtils.getSystemTime());
		model.addAttribute("action", "create");
		model.addAttribute("pxjl",pxjl);
		return "aqpx/wlfpx/pxjl/form";
		
	}
	
	/**
	 * 培训记录添加
	 * @param model
	 */
	@RequiresPermissions("wlfpx:pxjl:add")
	@RequestMapping(value = "pxjl/create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(AQPX_OutSourceEducationHistoryEntity jl,Model model) {
		String result="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		jl.setID1(sessionuser.getQyid());
		jl.setState("2");
		aqpxWlfpxHistoryService.addInfo(jl);
		return result;
	}

	/**
	 * 外来方人口基本信息添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "regist" , method = RequestMethod.GET)
	public String regist(Model model) {
		return "aqpx/wlfpx/form";
		
	}
	
	/**
	 * 外来方人口填写基本信息
	 * @param model
	 */
	@RequestMapping(value = "regist" , method = RequestMethod.POST)
	@ResponseBody
	public String regist(AQPX_OutSourceEducationHistoryEntity jl,Model model,HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.setAttribute("wlfname",jl.getM1_1() );
		session.setAttribute("idcard", jl.getM1_2());
		session.setAttribute("unit",jl.getM1() );
		session.setAttribute("post",jl.getM1_3() );
		session.setAttribute("kind",jl.getM1_4() );
		model.addAttribute("flag", 1);
		return "success";
	}
	
	/**
	 * 培训记录修改页面跳转
	 * @param model
	 */
	@RequiresPermissions("wlfpx:pxjl:update")
	@RequestMapping(value = "pxjl/update/{id}" , method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id,Model model) {
		AQPX_OutSourceEducationHistoryEntity pxjl = aqpxWlfpxHistoryService.findById(id);
		model.addAttribute("action", "update");
		model.addAttribute("pxjl",pxjl);
		return "aqpx/wlfpx/pxjl/form";
	}
	
	/**
	 * 培训记录修改
	 * @param model
	 */
	@RequiresPermissions("wlfpx:pxjl:update")
	@RequestMapping(value = "pxjl/update" , method = RequestMethod.POST)
	@ResponseBody
	public String update(AQPX_OutSourceEducationHistoryEntity jl,Model model) {
		aqpxWlfpxHistoryService.updateInfo(jl);
		return "success";
	}
	
	
	/**
	 * 培训记录查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("wlfpx:pxjl:view")
	@RequestMapping(value = "pxjl/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		AQPX_OutSourceEducationHistoryEntity pxjl = aqpxWlfpxHistoryService.findById(id);
		model.addAttribute("pxjl",pxjl);
		return "aqpx/wlfpx/pxjl/view";
	}
	
	/**
	 * 删除培训记录
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("wlfpx:pxjl:delete")
	@RequestMapping(value = "pxjl/delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqpxWlfpxHistoryService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 显示所有列
	 * @param id,model 
	 */
	@RequiresPermissions("wlfpx:pxjl:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","aqpx/wlfpx/pxjl/export");
		return "common/formexcel";
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("wlfpx:pxjl:export")
	@RequestMapping(value = "pxjl/export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String param=request.getParameter("param");
		Map<String,Object> map2 = JSON.parseObject(param);
		map.put("qyname", map2.get("aqpx_wlpxjl_qyname"));
		map.put("name", map2.get("aqpx_wlpxjl_name"));
		map.put("idcard", request.getParameter("idcard"));
		map.putAll(getAuthorityMap());
		aqpxWlfpxHistoryService.exportExcel(response, map);
	}
	
	/**
	 * 导出excel(集团)
	 * 
	 */
	@RequiresPermissions("wlfpx:pxjl:export")
	@RequestMapping(value = "pxjl/export2")
	@ResponseBody
	public void export2(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String param=request.getParameter("param");
		Map<String,Object> map2 = JSON.parseObject(param);
		map.put("qyname", map2.get("aqpx_wlpxjl_qyname"));
		map.put("name", map2.get("aqpx_wlpxjl_name"));
		map.put("idcard", request.getParameter("idcard"));
		map.putAll(getAuthorityMap());
		aqpxWlfpxHistoryService.exportExcel2(response, map);
	}

	/**
	 * 生成二维码图片
	 */
	@RequestMapping(value="erm")
	@ResponseBody
	public String erweima(HttpServletResponse response,HttpServletRequest request) {
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		String text=" ";
		Long qyid=UserUtil.getCurrentShiroUser().getQyid();
//		text+=basePath+"/ENT_H5/wlfpx/index.do?qyid="+qyid;
		text+=Global.getH5url()+"/wlfpx/index.do?qyid="+qyid;
		// 取得输出流        
		String dowmloadPath = request.getSession().getServletContext().getRealPath("/")+"/download";
		String url="/download/";
		try {
			url =url+ QRCode.encode(text, null, dowmloadPath, true,"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}

	//导入页面跳转
	@RequestMapping(value = "exinjump", method = RequestMethod.GET)
	public String exin(Model model) {
		model.addAttribute("action", "exin");
		return "common/importForm";
	}
	
	/**
	 * 导入
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequiresPermissions("wlfpx:pxjl:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = aqpxWlfpxHistoryService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}
}
