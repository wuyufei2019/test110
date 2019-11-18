package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cczu.sys.system.service.ShiroRealm;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.dao.IBisGwgyDao;
import com.cczu.model.entity.BIS_DangerProcessEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.Tdic_BIS_DangerProcess;
import com.cczu.model.service.IBisGwgyService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 高危工艺信息controller
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("bis/gwgy")
public class PageBisGwgyController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IBisGwgyService bisgwgyService;
	@Resource
	private IBisGwgyDao bisGwgyDao;
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "qyxx/wxgy/adminindex";
				else
					return "qyxx/wxgy/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "qyxx/wxgy/adminindex";
		}
	}

	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="ajlist")
	@ResponseBody
	public Map<String, Object> ajgetData(HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("bis_gwgy_qy_name"));
		map.put("m1", request.getParameter("bis_gwgy_cx_m1"));
		map.putAll(getAuthorityMap());
		return bisgwgyService.ajdataGrid(map);
	}


	/**
	 * list页面
	 * @param request
	 */
	@RequiresPermissions("bis:gwgy:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		
		//获取企业id
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		
		Map<String, Object> map = getPageMap(request);
		if(be!=null){
			map.put("qyid", be.getID());
		}
		map.put("processcode", request.getParameter("bis_gwgy_cx_bm"));
		map.putAll(getAuthorityMap());
		return bisgwgyService.gwgy(map);
	}

	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="tablist/{qyid}")
	@ResponseBody
	public Map<String, Object> getData(@PathVariable("qyid") Integer qyid, HttpServletRequest request) {
		
		int pageNo=1;	//当前页码
		int pageSize=20;	//每页行数
		if(StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo=Integer.valueOf(request.getParameter("page"));
		if(StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize=Integer.valueOf(request.getParameter("rows"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		map.put("qyid", qyid);
		return bisgwgyService.gwgy(map);
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("bis:gwgy:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "qyxx/wxgy/form";
	}
	
	//BIS_DangerProcessEntity
	/**
	 * 添加高危工艺信息
	 * @param request,model
	 */
	@RequiresPermissions("bis:gwgy:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(BIS_DangerProcessEntity bd, Model model) {
		String datasuccess="success";

		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			long ID = be.getID();//获取到企业id
			bd.setID1(ID);
			bd.setCompanycode(be.getCompanycode());
			bd.setDistrictcode(be.getID2());//所属行政区划
			bd.setParkid(be.getParkid());//园区标识
		}

		Timestamp t=DateUtils.getSysTimestamp();
		bd.setS1(t);
		bd.setS2(t);
		bd.setS3(0);
		bd.setUuid(UUID.randomUUID().toString());
		bisgwgyService.addInfo(bd);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--高危工艺信息  【添加操作】");

		return datasuccess;
		
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:gwgy:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		//查询选择的高危工艺信息
		long id1 = id;
		Map<String,Object> map=bisgwgyService.findById(id1);
		BIS_DangerProcessEntity bd = (BIS_DangerProcessEntity)map.get("danger");
		model.addAttribute("qylist", bd);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		//返回页面
		model.addAttribute("action", "update");
		return "qyxx/wxgy/form";
	}
	
	/**
	 * 修改高危工艺信息
	 * @param request,model
	 */
	@RequiresPermissions("bis:gwgy:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_DangerProcessEntity bd, Model model){
		String datasuccess="success";
		
		Timestamp t=DateUtils.getSysTimestamp();
		bd.setS2(t);
		bisgwgyService.updateInfo(bd);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--高危工艺信息  【修改操作】");

		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除高危工艺信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequiresPermissions("bis:gwgy:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			bisgwgyService.deleteInfo(Long.parseLong(aids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--高危工艺信息  【删除操作】");

		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> map=bisgwgyService.findById(id);
		BIS_DangerProcessEntity bd = (BIS_DangerProcessEntity)map.get("danger");
		model.addAttribute("qylist", bd);//高位工艺信息
		//返回页面
		model.addAttribute("action", "view");
		return "qyxx/wxgy/view";
	}
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:gwgy:view")
	@RequestMapping(value = "sview/{id}", method = RequestMethod.GET)
	public String sview(@PathVariable("id") Long id, Model model) {
		Map<String,Object> map=bisgwgyService.findById(id);
		BIS_DangerProcessEntity bd = (BIS_DangerProcessEntity)map.get("danger");
		Tdic_BIS_DangerProcess td=bisgwgyService.findByM0(bd.getM1());
		model.addAttribute("qylist", bd);//高位工艺信息
		model.addAttribute("info", td);//具体高位工艺数据信息
		model.addAttribute("label",((Map<String,Object>)map.get("label")).get("label"));
		//返回页面
		model.addAttribute("action", "view");
		return "qyxx/wxgy/view";
	}
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("bis:gwgy:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m1", request.getParameter("bis_gwgy_cx_m1"));
		map.put("qyname", request.getParameter("bis_gwgy_qy_name"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		bisgwgyService.exportExcel(response, map);
		
	}
	
	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("bis:gwgy:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url", "bis/gwgy/export");
		return "common/formexcel";
	}
	
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
	@RequiresPermissions("bis:gwgy:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		map.put("companycode",be.getCompanycode());
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = bisgwgyService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		String ss=JsonMapper.toJsonString(resultmap);
		return ss;
	}
	
	
	/**
	 * 根据高危工艺类别查询高危工艺的字典信息
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "gwgyjson")
	@ResponseBody
	public String gwgyjson(String name) {
		Tdic_BIS_DangerProcess td=bisgwgyService.findByM0(name );
		return JsonMapper.toJsonString(td);
	}

	/**
	 * 根据高危工艺名称查询高危工艺的字典信息
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "gwgyjson1")
	@ResponseBody
	public String gwgyjson1(String name) {
		Tdic_BIS_DangerProcess td=bisgwgyService.findByGwgyName(name);
		return JsonMapper.toJsonString(td);
	}

	/**
	 * 统计页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "statistics", method = RequestMethod.GET)
	public String statistics(Model model) {
		List<Map<String, Object>>maplist = bisgwgyService.statistics(getAuthorityMap());
		model.addAttribute("data", JsonMapper.getInstance().toJson(maplist));
		return "qyxx/wxgy/statistics";
	}

    /**
     * 获取高危工艺下拉框内容
     * @param model
     */
    @RequestMapping(value = "getjson", method = RequestMethod.POST)
    @ResponseBody
    public String getJson(Model model) {
        Map<String, Object> mapdata = getAuthorityMap();
        return bisgwgyService.getGwgyJson(mapdata);
    }

    /**
     * 获取高危工艺下拉框内容
     * @param model
     */
    @RequestMapping(value = "getjson/{qyid}", method = RequestMethod.POST)
    @ResponseBody
    public String getJson(@PathVariable("qyid") Long qyid, Model model) {
        Map<String, Object> mapdata = new HashMap<>();
        mapdata.put("qyid", qyid);
        return bisgwgyService.getGwgyJson(mapdata);
    }

}
