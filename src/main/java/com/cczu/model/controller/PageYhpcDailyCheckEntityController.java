package com.cczu.model.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.YHPC_DailyHiddenCheckEntity;
import com.cczu.model.entity.YHPC_RcjcLxglEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.YhpcDailyCheckEntityService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ServletUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 隐患排查---日常检查信息controller
 * @author 
 * @date 2018年06月25日
 */
@Controller
@RequestMapping("yhpc/rcjc")
public class PageYhpcDailyCheckEntityController extends BaseController {

	@Autowired
	private YhpcDailyCheckEntityService yhpcDailyCheckEntityService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private UserService userService;

	/**
     *页面跳转 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type", 2);
				else
					model.addAttribute("type", 1);
			}
		}else {//非企业用户页面
			model.addAttribute("type", 2);
		}
		model.addAttribute("userid", sessionuser.getId());
        return "yhpc/rcjc/index";
	}

	/**
	 * list界面
	 * @param request
	 */
	@RequiresPermissions("yhpc:rcjc:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> dataMap = getPageMap(request);
		dataMap.putAll(ServletUtils.getParametersStartingWith(request,  "view_"));
		dataMap.put("qyname", request.getParameter("view_qyname"));
		dataMap.put("starttime", request.getParameter("yhpc_jcyh_starttime"));
		dataMap.put("endtime",request.getParameter("yhpc_jcyh_finishtime"));
		dataMap.put("M6",request.getParameter("yhpc_jcyh_yhlb"));
		dataMap.put("M16",request.getParameter("yhpc_jcyh_yhdj"));
		dataMap.put("shstate",request.getParameter("yhpc_jcyh_shstate"));
		dataMap.put("M13",request.getParameter("yhpc_jcyh_zgstate"));
		dataMap.putAll(getAuthorityMap());
		return yhpcDailyCheckEntityService.dataGrid(dataMap);
	}


	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("yhpc:rcjc:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "yhpc/rcjc/form";
	}

	/**
	 * 添加信息
	 * @param entity
	 */
	@RequiresPermissions("yhpc:rcjc:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(YHPC_DailyHiddenCheckEntity entity) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setQyid(UserUtil.getCurrentShiroUser().getQyid());
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setShstate("0");//默认待审核
		entity.setM13("1");//默认待审核
		entity.setM19("YHZG"+DateUtils.getDateRandom());
		yhpcDailyCheckEntityService.addInfo(entity);
		return datasuccess;
	}

	/**
	 * 整改页面跳转
	 * @param id,model
	 */
   @RequiresPermissions("yhpc:rcjc:update")
	@RequestMapping(value = "reform/{id}", method = RequestMethod.GET)
	public String reform(@PathVariable("id") long id, Model model) {
		YHPC_DailyHiddenCheckEntity entity = yhpcDailyCheckEntityService.findById(id);
	    User usertemp = userService.getUserById(StringUtils.toInteger(entity.getM8()));
	    if(usertemp!=null)
			model.addAttribute("m8", usertemp.getName());
	    usertemp = userService.getUserById(StringUtils.toInteger(entity.getM9()));
	    if(usertemp!=null)
		    model.addAttribute("m9", usertemp.getName());
	    model.addAttribute("entity", entity);
		model.addAttribute("action", "reform");
		if(entity.getM13().equals("3"))//如果是整改审核未通过   则为修改操作
			model.addAttribute("update", "update");
		return "yhpc/rcjc/zgform";
	}

	/**
	 * 添加整改信息
	 * @param entity
	 */
	@RequestMapping(value = "reform")
	@ResponseBody
	public String reform(YHPC_DailyHiddenCheckEntity entity) {
		String datasuccess = "success";
		try {
			YHPC_DailyHiddenCheckEntity jcjl=yhpcDailyCheckEntityService.findById(entity.getID());
			jcjl.setM11(entity.getM11());//实际完成时间
			jcjl.setM15(entity.getM15());//整改费用
			jcjl.setM7(entity.getM7());//解决措施
			jcjl.setM12(entity.getM12());//整改后照片
			jcjl.setM13("2");//整改状态改成  整改待审核
			Timestamp t=DateUtils.getSysTimestamp();
			entity.setS2(t);
			yhpcDailyCheckEntityService.addInfo(jcjl);
		}catch (Exception e){
			e.printStackTrace();
			datasuccess = "error";
		}
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
   @RequiresPermissions("yhpc:rcjc:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") long id, Model model) {
		YHPC_DailyHiddenCheckEntity entity = yhpcDailyCheckEntityService.findById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("action", "update");
		return "yhpc/rcjc/form";
	}

	/**
	 * 修改信息
	 * 
	 * @param request,model
	 */
   @RequiresPermissions("yhpc:rcjc:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(YHPC_DailyHiddenCheckEntity entity, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		YHPC_DailyHiddenCheckEntity xgEntity = yhpcDailyCheckEntityService.findById(entity.getID());
		
		xgEntity.setM1(entity.getM1());
		xgEntity.setM16(entity.getM16());
		xgEntity.setM2(entity.getM2());
		xgEntity.setM3(entity.getM3());
		xgEntity.setM6_1(entity.getM6_1());
		xgEntity.setM6_2(entity.getM6_2());
		xgEntity.setM8(entity.getM8());
		xgEntity.setM10(entity.getM1());
		xgEntity.setM18(entity.getM18());
		xgEntity.setM4(entity.getM4());
		xgEntity.setM5(entity.getM5());
		
		xgEntity.setS2(t);
		xgEntity.setShstate("0");
		yhpcDailyCheckEntityService.updateInfo(xgEntity);
		return datasuccess;
	}

	/**
	 * 整改审核页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "zhgshenhe/{id}", method = RequestMethod.GET)
	public String zhGShenHe(@PathVariable("id") long id, Model model) {
		YHPC_DailyHiddenCheckEntity entity = yhpcDailyCheckEntityService.findById(id);
		User usertemp = userService.getUserById(StringUtils.toInteger(entity.getM8()));
		if(usertemp!=null)
			model.addAttribute("m8", usertemp.getName());
		usertemp = userService.getUserById(StringUtils.toInteger(entity.getM9()));
		if(usertemp!=null)
			model.addAttribute("m9", usertemp.getName());
		model.addAttribute("entity", entity);
		model.addAttribute("action", "zhgshenhetj");
		return "yhpc/rcjc/zgshform";
	}

	/**
	 * 整改审核提交
	 * @param entity,model
	 */
	@RequestMapping(value = "zhgshenhetj")
	@ResponseBody
	public String zhGShenHeTj(YHPC_DailyHiddenCheckEntity entity, Model model) {
		String datasuccess = "success";
		try {
			YHPC_DailyHiddenCheckEntity jcjl = yhpcDailyCheckEntityService.findById(entity.getID());
			jcjl.setM13(entity.getM13());//状态确认
			jcjl.setM14(entity.getM14());//稽核人
			jcjl.setM17(entity.getM17());//审核反馈
			Timestamp t = DateUtils.getSysTimestamp();
			entity.setS2(t);
			if(entity.getM13().equals("4"))//如果整改审核状态为已完成，则设置结案日期
				jcjl.setM20(DateUtils.getDate("yyyy-MM-dd"));
			yhpcDailyCheckEntityService.updateInfo(jcjl);
		}catch (Exception e){
			e.printStackTrace();
			datasuccess = "error";
		}
		return datasuccess;
	}
  
	/**
	 * 删除信息
     * @param ids
	 */
   @RequiresPermissions("yhpc:rcjc:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "success";
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			yhpcDailyCheckEntityService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id
	 */
   @RequiresPermissions("yhpc:rcjc:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String sview(@PathVariable("id") long id ,Model model) {
	    YHPC_DailyHiddenCheckEntity entity = yhpcDailyCheckEntityService.findById(id);
		User usertemp = userService.getUserById(StringUtils.toInteger(entity.getM8()));
		if(usertemp!=null)
			model.addAttribute("m8", usertemp.getName());
		usertemp = userService.getUserById(StringUtils.toInteger(entity.getM9()));
		if(usertemp!=null)
			model.addAttribute("m9", usertemp.getName());
		model.addAttribute("entity", entity);
		return "yhpc/rcjc/view";
	}

   /**
    * 审核页面跳转
    * @param model
    * @return
    */
	@RequestMapping(value = "shform/{id}", method = RequestMethod.GET)
	public String sh(@PathVariable("id") long id ,Model model) {
	   YHPC_DailyHiddenCheckEntity entity = yhpcDailyCheckEntityService.findById(id);
		model.addAttribute("entity", entity);
		User usertemp = userService.getUserById(StringUtils.toInteger(entity.getM8()));
		if(usertemp!=null) {
			model.addAttribute("m8", usertemp.getName());
			model.addAttribute("bmid", usertemp.getDepartmen());
		}
		model.addAttribute("action", "shform");
		return "yhpc/rcjc/shform";
	}
	
	/**
	 * 审核保存
	 * 
	 * @param request,model
	 */
	@RequestMapping(value = "shform")
	@ResponseBody
	public String sh(YHPC_DailyHiddenCheckEntity entity, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		YHPC_DailyHiddenCheckEntity shEntity = yhpcDailyCheckEntityService.findById(entity.getID());
		shEntity.setS2(t);
		
		shEntity.setM9(entity.getM9());
		shEntity.setM17(entity.getM17());
		shEntity.setShstate(entity.getShstate());
		
		yhpcDailyCheckEntityService.updateInfo(shEntity);
		return datasuccess;
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
	@RequiresPermissions("yhpc:rcjc:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = yhpcDailyCheckEntityService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}

	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("yhpc:rcjc:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","yhpc/rcjc/export");
		return "common/formexcel";
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("yhpc:rcjc:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("view_qyname"));
		if ("1".equals(UserUtil.getCurrentShiroUser().getUsertype())) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			map.put("starttime", request.getParameter("yhpc_jcyh_starttime"));
			map.put("endtime",request.getParameter("yhpc_jcyh_finishtime"));
			map.put("M6",request.getParameter("yhpc_jcyh_yhlb"));
			map.put("M16",request.getParameter("yhpc_jcyh_yhdj"));
		} 
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		yhpcDailyCheckEntityService.exportExcel(response, map);

	}
	
	/**
	 * 类型管理
	 */
	@RequestMapping(value="lxgl")
	public String lxgl() {
		return "yhpc/rcjc/lxgl";
	}
	
	/**
	 * 跳转检查类型页面
	 */
	@RequestMapping(value="jclx")
	public String jclx() {
		return "yhpc/rcjc/jclxindex";
	}
	
	/**
	 * 跳转缺失类型页面
	 */
	@RequestMapping(value="qslx")
	public String qslx() {
		return "yhpc/rcjc/qslxindex";
	}
	
	/**
	 * 检查类型List
	 */
	@RequestMapping(value = "lxlist")
	@ResponseBody
	public Map<String, Object> getLxglData(HttpServletRequest request) {
		String type = request.getParameter("type");
		return yhpcDailyCheckEntityService.lxglList(type);
	}
	
	/**
	 * 跳转类型添加页面
	 */
	@RequestMapping(value = "lxcreate/{type}", method = RequestMethod.GET)
	public String lxcreate(@PathVariable("type") String type,Model model) {
		model.addAttribute("action", "lxcreate");
		model.addAttribute("type", type);
		return "yhpc/rcjc/lxform";
	}
	
	/**
	 * 类型添加
	 */
	@RequestMapping(value = "lxcreate")
	@ResponseBody
	public String lxcreate(YHPC_RcjcLxglEntity entity, HttpServletRequest request) {
		String datasuccess = "success";
		entity.setType(request.getParameter("typelx"));
		yhpcDailyCheckEntityService.lxglAdd(entity);
		return datasuccess;
	}
	
	/**
	 * 跳转类型修改页面
	 */
	@RequestMapping(value = "lxupdate/{id}", method = RequestMethod.GET)
	public String lxupdate(@PathVariable("id") long id, Model model) {
		YHPC_RcjcLxglEntity entity = yhpcDailyCheckEntityService.lxglFind(id);
		model.addAttribute("entity", entity);
		model.addAttribute("action", "lxupdate");
		return "yhpc/rcjc/lxform";
	}
	
	/**
	 * 类型修改
	 */
	@RequestMapping(value = "lxupdate")
	@ResponseBody
	public String lxupdate(YHPC_RcjcLxglEntity entity, Model model) {
		String datasuccess = "success";
		yhpcDailyCheckEntityService.lxglUpdate(entity);
		return datasuccess;
	}
	
	/**
	 * 检查类型删除
	 */
	@RequestMapping(value = "lxdelete/{ids}")
	@ResponseBody
	public String lxdelete(@PathVariable("ids") String ids) {
		String datasuccess = "success";
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			yhpcDailyCheckEntityService.lxglDel(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 类型集合（json）
	 */
	@RequestMapping(value="lxjson/{type}")
	@ResponseBody
	public String lxlist(@PathVariable("type") String type){
		return yhpcDailyCheckEntityService.getAlllist(type);
	}
}
