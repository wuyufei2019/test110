package com.cczu.model.sggl.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IBisYgxxService;
import com.cczu.model.sggl.entity.SGGL_AccidentManageEntity;
import com.cczu.model.sggl.service.SgglSgxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 事故登记信息controller
 *
 * @author jason
 * @date 2017年7月1日
 */
@Controller
@RequestMapping("sggl/sgxx")
public class PageSgglSgxxController extends BaseController {

	@Autowired
	private SgglSgxxService sgglSgxxService;
	@Autowired
	private IBisYgxxService bisYgxxService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;

	/**
	 * 列表显示页面
	 *
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type", 2);
				else
					model.addAttribute("type", 1);
			}
		}
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		model.addAttribute("qyid",request.getParameter("id"));//企业id
		model.addAttribute("zt",request.getParameter("zt"));//企业id
		return "sggl/sgxx/index";
	}


	/**
	 * list页面
	 *
	 * @param request
	 */
	@RequiresPermissions("sggl:sgxx:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("qynm", request.getParameter("view_qyname"));
		map.put("m1", request.getParameter("sggl_sgxx_sgbh"));
		map.put("m2", request.getParameter("sggl_sgxx_cs_name"));
		map.put("sgtype",request.getParameter("sggl_sgxx_sglx"));//事故类型
		map.put("sglevel",request.getParameter("sggl_sgxx_sglevel"));//事故等级
		map.put("m5", request.getParameter("sggl_sgxx_m5"));
		map.put("qyid", request.getParameter("qyid"));
		map.put("zt", request.getParameter("zt"));
		return sgglSgxxService.dataGrid(map);

	}

	/**
	 * 事故统计list页面
	 *
	 * @param request
	 */
	@RequiresPermissions("sggl:sgxx:view")
	@RequestMapping(value = "tjlist")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		String year=request.getParameter("sggl_sgtj_year");
		map.put("year",year);//年份
		map.put("qynm", request.getParameter("sggl_sgxx_qy_name"));
		map.put("sgtype",request.getParameter("sggl_sgtj_sglx"));//事故类型
		map.put("sglevel",request.getParameter("sggl_sgtj_sglevel"));//事故等级
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}
		return sgglSgxxService.dataGrid(map);

	}

	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("sggl:sgxx:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			model.addAttribute("qyid", (UserUtil.getCurrentShiroUser().getQyid()));
		}
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "sggl/sgxx/form";
	}

	/**
	 * 添加事故信息
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("sggl:sgxx:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(SGGL_AccidentManageEntity ee, HttpServletRequest request) {
		Timestamp t=DateUtils.getSysTimestamp();
		ee.setS1(t);
		ee.setS2(t);
		ee.setS3(0);
		ee.setZt("0");
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			ee.setQyid(UserUtil.getCurrentShiroUser().getQyid());
		}
		//获取今年年份
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MMdd");
		Date date = new Date();
		String formatDate = sdf.format(date);
		String formatDate2 = sdf2.format(ee.getM5());
		//添加演练记录
		String swryids=request.getParameter("swryids");
		String shcdids=request.getParameter("shcdids");
		String datasuccess=sgglSgxxService.addInfo(ee,swryids,shcdids);
		ee.setM1(formatDate+"第"+(formatDate2)+"号");
		sgglSgxxService.updateInfo(ee);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 *
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("sggl:sgxx:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		// 查询选择的演练记录信息

		long id1 = id;
		SGGL_AccidentManageEntity ee = sgglSgxxService.findById(id1);
		model.addAttribute("res", ee);
		// 返回页面
		model.addAttribute("action", "update");
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			model.addAttribute("qyid", (UserUtil.getCurrentShiroUser().getQyid()));
		}
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "sggl/sgxx/form";
	}

	/**
	 * 修改事故信息
	 *
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("sggl:sgxx:update")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(SGGL_AccidentManageEntity ee, Model model, HttpServletRequest request) {
		Timestamp t=DateUtils.getSysTimestamp();
		ee.setS2(t);
		String swryids=request.getParameter("swryids");
		String shcdids=request.getParameter("shcdids");
		return sgglSgxxService.updateInfoById1(ee,swryids,shcdids);
	}

	/**
	 * 删除事故信息
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("sggl:sgxx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			sgglSgxxService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("sggl:sgxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息

		long id1 = id;
		SGGL_AccidentManageEntity ee = sgglSgxxService.findById(id1);
		model.addAttribute("res", ee);
		//返回页面
		model.addAttribute("action", "view");
		return "sggl/sgxx/view";
	}

	/**
	 * 伤亡人员list页面
	 * @param request
	 */
	@RequestMapping(value="swrylist")
	@ResponseBody
	public Map<String, Object> getDataswry(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		String id1=request.getParameter("sgid");
		if(!"add".equals(id1)){
			map.put("id1", request.getParameter("sgid"));
		}
		map.put("qyid", request.getParameter("qyid"));
		return bisYgxxService.swrylist(map);
	}

	/**
	 * 选择伤亡人员list页面
	 * @param request
	 */
	@RequestMapping(value="swrylist2")
	@ResponseBody
	public Map<String, Object> swrylist2(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		String id1=request.getParameter("id1");
		if(!"add".equals(id1)){
			map.put("id1", request.getParameter("id1"));
		}
		map.put("qyid", request.getParameter("qyid"));
		map.put("ygname", request.getParameter("bis_ygxx_cx_m1"));
		map.put("gw", request.getParameter("bis_ygxx_cx_m4"));
		return bisYgxxService.swrylist2(map);
	}

	//跳转伤亡人员list
	@RequestMapping(value = "swrycreate/{id1},{qyid}", method = RequestMethod.GET)
	public String swrycreate(@PathVariable("id1") Long id1,@PathVariable("qyid") Long qyid, Model model,HttpServletRequest request) {
		if(!(id1==0)){
			model.addAttribute("id1", id1);
		}
		model.addAttribute("qyid", qyid);
		return "sggl/sgxx/swryall";
	}

	/**
	 * 删除巡检内容信息
	 */
	@RequestMapping(value = "swrydelete/{ids}")
	@ResponseBody
	public String swrydelete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		System.out.println(ids);
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			sgglSgxxService.deleteSwry(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	//导入页面跳转
	@RequestMapping(value = "exinjump", method = RequestMethod.GET)
	public String exin(Model model) {
		model.addAttribute("action", "exin");
		return "common/importForm";
	}

	/**
	 * 列表显示页面
	 *
	 * @param model
	 */
	@RequestMapping(value = "tjindex")
	public String tjindex(Model model) {
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		return "sggl/sgtj/index";
	}

	/**
	 * 获取年份
	 * @param id,model
	 */
	@RequestMapping(value = "year")
	@ResponseBody
	public String getyear() {
		Object[] objs=sgglSgxxService.getMaxYearAndMinYear();
		List<Map<String, Object>> yearlist=new ArrayList<Map<String, Object>>();
		if(objs[0]!=null&&objs[1]!=null){
			int maxyear= Integer.parseInt(objs[0].toString());
			int minyear= Integer.parseInt(objs[1].toString());
			Map<String, Object> map;
			for(int i=maxyear;i>=minyear;i--){
				map=new HashMap<String,Object>();
				map.put("id", i);
				map.put("text", i);
				yearlist.add(map);
			}
		}
		return JsonMapper.getInstance().toJson(yearlist);
	}

	/**
	 * Echarts图返回数据(按月份)
	 */
	@RequestMapping(value="jsondate")
	@ResponseBody
	public Map<String,Object> getJsonDate(HttpServletRequest request){
		Map<String ,Object> querymap= new HashMap<String,Object>();
		String year=request.getParameter("year");
		year=(StringUtils.isBlank(year))?DateUtils.getYear():year;
		querymap.put("year",year);//年份
		querymap.put("sgtype",request.getParameter("sgtype"));//事故类型
		querymap.put("sglevel",request.getParameter("sglevel"));//事故等级
		querymap.put("qyid",UserUtil.getCurrentUser().getId2());
		return sgglSgxxService.getCountEveryMonth(querymap);
	}

	/**
	 * Echarts图返回数据(按类型)
	 */
	@RequestMapping(value="jsontype")
	@ResponseBody
	public Map<String,Object> getJsonType(HttpServletRequest request){
		Map<String ,Object> querymap= new HashMap<String,Object>();
		String year=request.getParameter("year");
		year=(StringUtils.isBlank(year))?DateUtils.getYear():year;
		querymap.put("year",year);//年份
		querymap.put("sgtype",request.getParameter("sgtype"));//事故类型
		querymap.put("sglevel",request.getParameter("sglevel"));//事故等级
		querymap.put("qyid",UserUtil.getCurrentUser().getId2());
		return sgglSgxxService.getCountEveryType(querymap);
	}

	/**
	 * Echarts图返回数据(按部门)
	 */
	@RequestMapping(value="jsonbm")
	@ResponseBody
	public Map<String,Object> getJsonBm(HttpServletRequest request){
		Map<String ,Object> querymap= new HashMap<String,Object>();
		String year=request.getParameter("year");
		year=(StringUtils.isBlank(year))?DateUtils.getYear():year;
		querymap.put("year",year);//年份
		querymap.put("sgtype",request.getParameter("sgtype"));//事故类型
		querymap.put("sglevel",request.getParameter("sglevel"));//事故等级
		querymap.put("qyid",UserUtil.getCurrentUser().getId2());
		return sgglSgxxService.getCountEveryBm(querymap);
	}

	/**
	 * 批量上报
	 *
	 * @param
	 * @param
	 * @throws ParseException
	 */
	@RequiresPermissions("sggl:sgxx:plsb")
	@RequestMapping(value = "plsb/{ids}")
	@ResponseBody
	public String plsb(@PathVariable("ids") String ids) {
		String datasuccess = "上报成功";
		// 可以批量上报
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			sgglSgxxService.plsb(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 批量取消上报
	 *
	 * @param
	 * @param
	 * @throws ParseException
	 */
	@RequiresPermissions("sggl:sgxx:plsb")
	@RequestMapping(value = "plqx/{ids}")
	@ResponseBody
	public String plqx(@PathVariable("ids") String ids) {
		String datasuccess = "取消成功";
		// 可以批量上报
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			sgglSgxxService.plqx(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
}
