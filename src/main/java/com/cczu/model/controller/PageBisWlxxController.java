package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.BIS_MatEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IBisWlxxService;
import com.cczu.model.service.ITdicDangerousChemicalsService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 物料信息controller
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("bis/wlxx")
public class PageBisWlxxController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IBisWlxxService bisWlxxService;
	@Autowired
	private ITdicDangerousChemicalsService dicdangerservice;

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
					return "qyxx/wlxx/ajindex";
				else
					return "qyxx/wlxx/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "qyxx/wlxx/ajindex";
		}	
	}
	
	/**
	 * 重点监管危化品列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index2")
	public String index2(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		if(be!=null&& be.getM1() != null){
			return "qyxx/zdjg/index";
		}else{
			// 获取用户角色
			List<Role> list = roleService.findRoleById(sessionuser.getId());
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					//判断用户角色，如果包含有企业用户角色，则转到企业用户显示的界面
					if (list.get(i).getRoleCode().equals("company")||list.get(i).getRoleCode().equals("companyadmin"))
						return "../error/001";
				}
			}
			return "qyxx/zdjg/ajindex";
		}
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequiresPermissions("bis:wlxx:view")
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
		map.put("m1", request.getParameter("bis_wlxx_cx_m1"));
		map.put("ccfs", request.getParameter("ccfs"));
		map.put("whplb", request.getParameter("whplb"));
		return bisWlxxService.dataGrid(map);

	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="tablist/{qyid}")
	@ResponseBody
	public Map<String, Object> getDataTab(@PathVariable("qyid") Integer qyid, HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyid", qyid);
		return bisWlxxService.dataGrid(map);
	}

	
	/**
	 * list页面(安监局查看所有企业物料信息)
	 * @param request
	 */
	@RequiresPermissions("bis:wlxx:view")
	@RequestMapping(value="ajlist")
	@ResponseBody
	public Map<String, Object> getAllData(HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("bis_cjxx_qy_name"));
		map.put("leibie", request.getParameter("bis_wlxx_cx_m2"));
		map.put("m1", request.getParameter("bis_wlxx_cx_m1"));
		map.put("ccfs", request.getParameter("ccfs"));
		map.put("whplb", request.getParameter("whplb"));
		map.put("zdjg", request.getParameter("bis_zdjg"));
		map.put("jd", request.getParameter("bis_jd"));
		map.put("yzd", request.getParameter("bis_yzd"));
		map.putAll(getAuthorityMap());
		return bisWlxxService.dataGridAJ(map);
	}
	
	/**
	 * 重点监管list页面
	 * @param request
	 */
	@RequestMapping(value="list2")
	@ResponseBody
	public Map<String, Object> getZdjg(HttpServletRequest request) {
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("bis_cjxx_qy_name"));
		map.put("leibie", request.getParameter("bis_wlxx_cx_m2"));
		map.put("m1", request.getParameter("bis_wlxx_cx_m1"));
		map.put("ccfs", request.getParameter("ccfs"));
		map.put("whplb", request.getParameter("whplb"));
		map.put("zdjg", request.getParameter("bis_zdjg"));
		map.put("jd", request.getParameter("bis_jd"));
		map.put("yzd", request.getParameter("bis_yzd"));
		map.put("qyid", request.getParameter("qyid"));
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("5")){//第三方
			map.put("cjz",sessionuser.getId());
		}else if(sessionuser.getUsertype().equals("1")){//企业
			map.put("qyid", sessionuser.getQyid());
		}else if(sessionuser.getUsertype().equals("0")){//安监局
			map.put("xzqy",sessionuser.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}
		return bisWlxxService.dataGrid2(map);
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("bis:wlxx:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
			return "qyxx/wlxx/form";
		
	}
	
	/**
	 * 添加物料信息
	 * @param request,model
	 */
	@RequiresPermissions("bis:wlxx:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(BIS_MatEntity bm, Model model) {
		String datasuccess="success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		if(sessionuser.getUsertype().equals("1")){
			bm.setID1(sessionuser.getQyid());
		} 
		bm.setUuid(UUID.randomUUID().toString());//uuid
		bm.setParkid(be.getParkid());//园区标识
		bm.setDistrictcode(be.getID2());//所属园区划
		bm.setCompanycode(be.getCompanycode());//企业编码
		
		Timestamp t=DateUtils.getSysTimestamp();
		bm.setS1(t);
		bm.setS2(t);
		bm.setS3(0);
		bisWlxxService.addInfo(bm);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--物料信息  【增加操作】");

		return datasuccess;
		
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:wlxx:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的物料信息
		BIS_MatEntity bm = bisWlxxService.findById(id);
		model.addAttribute("wllist", bm);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		//返回页面
		model.addAttribute("action", "update");
		return "qyxx/wlxx/form";
	}
	
	/**
	 * 修改物料信息
	 * @param request,model
	 */
	@RequiresPermissions("bis:wlxx:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_MatEntity bm, Model model){
		String datasuccess="success";
		//查询选择的产品信息
		Timestamp t=DateUtils.getSysTimestamp();
		bm.setS2(t);
		bisWlxxService.updateInfo(bm);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--物料信息  【修改操作】");

		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除物料信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequiresPermissions("bis:wlxx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			bisWlxxService.deleteInfo(Long.parseLong(aids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--物料信息  【删除操作】");

		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的产品信息
		Map<String, Object> bm = bisWlxxService.findById2(id);
		model.addAttribute("wllist", bm);
		//返回页面
		model.addAttribute("action", "view");
		return "qyxx/wlxx/view";
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
	@RequiresPermissions("bis:wlxx:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = bisWlxxService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}

	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("bis:wlxx:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ccfs", request.getParameter("ccfs"));
		map.put("whplb", request.getParameter("whplb"));
		map.put("m1", request.getParameter("bis_wlxx_cx_m1"));
		
		map.put("qyname", request.getParameter("bis_cjxx_qy_name"));
		map.put("leibie", request.getParameter("bis_wlxx_cx_m2"));
		map.put("zdjg", request.getParameter("bis_zdjg"));
		map.put("jd", request.getParameter("bis_jd"));
		map.put("yzd", request.getParameter("bis_yzd"));
		
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		bisWlxxService.exportExcel(response, map);
		
	}
	
	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("bis:wlxx:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","bis/wlxx/export");
		return "common/formexcel";
	}

	/**
	 * 导出重点监管危化品excel
	 * 
	 */
	@RequiresPermissions("bis:wlxx:export")
	@RequestMapping(value = "export2")
	@ResponseBody
	public void export2(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			map.put("m1", request.getParameter("bis_wlxx_cx_m1"));
			map.put("ccfs", request.getParameter("ccfs"));
			map.put("whplb", request.getParameter("whplb"));
		}else{
			map.put("qyname", request.getParameter("bis_cjxx_qy_name"));
			map.put("leibie", request.getParameter("bis_wlxx_cx_m2"));
			map.put("m1", request.getParameter("bis_wlxx_cx_m1"));
			map.put("ccfs", request.getParameter("ccfs"));
			map.put("whplb", request.getParameter("whplb"));
			map.put("jd", request.getParameter("bis_jd"));
			map.put("yzd", request.getParameter("bis_yzd"));
			if("0".equals(UserUtil.getCurrentShiroUser().getUsertype())){
				map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
				//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
				if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
					map.put("jglx",sessionuser.getUserroleflg());
			}else if("5".equals(UserUtil.getCurrentShiroUser().getUsertype())){
				map.put("cjz", UserUtil.getCurrentShiroUser().getId());
			}
		}
		map.put("zdjg", '1');
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		bisWlxxService.exportExcel(response, map);
		
	}
	
	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("bis:wlxx:export")
	@RequestMapping(value = "colindex2", method = RequestMethod.GET)
	public String colindex2(Model model) {
		model.addAttribute("url","bis/wlxx/export2");
		return "common/formexcel";
	}
	
	/**
	 * 物料名称字典显示
	 */
	@RequestMapping(value="/wldict")
	@ResponseBody
	public String wlname() {
		return bisWlxxService.findAllwlList();
	}
	
	/**
	 * 物料名称字典显示
	 */
	@RequestMapping(value="/wlforck")
	@ResponseBody
	public String wlforck(String qyid) {
		return bisWlxxService.findWlforck(qyid);
	}
	
	/**
	 * 安监端获取企业物料
	 * @param id,model
	 */
	@RequestMapping(value = "ajwllist")
	@ResponseBody
	public String findwlByqyid(Long qyid){
		List<Map<String, Object>> wllist=new ArrayList<Map<String, Object>>();
		if(qyid!=null&&qyid>0){
			wllist = bisWlxxService.findwlByqyid(qyid);
		}
		return JsonMapper.getInstance().toJson(wllist);
	}
	
//	/**
//	 * 字典显示
//	 * @param {id}
//	 */
//	@RequestMapping(value="/wldict2")
//	@ResponseBody
//	public String wltext(HttpServletRequest request) {
//		List<BIS_MatEntity> matlist = bisWlxxService.findByName(request.getParameter("text"));
//		if(matlist!=null&&matlist.size()>0){
//			return String.valueOf(matlist.get(0).getM4());
//		}else{
//			return "";
//		}
//	}
	
	/**
	 * 字典显示
	 * @param {json}
	 */
	@RequestMapping(value="/wlname2")
	@ResponseBody
	public String json(HttpServletRequest request) {
		String m2=request.getParameter("text");
		return dicdangerservice.dangerList(m2);
	}
	
	/**
	 * 字典显示
	 * @param {json}
	 */
	@RequestMapping(value="/wlname3")
	@ResponseBody
	public String json2(HttpServletRequest request) {
		String m2=request.getParameter("t2");
		return dicdangerservice.dangerList2(m2);
	}
	
	/**
	 * 字典显示
	 * @param {json}
	 */
	@RequestMapping(value="/wlname")
	@ResponseBody
	public String jsonlist(HttpServletRequest request) {
		//查询所有的name
		return dicdangerservice.findlist();
	}
	
	/**
	 * 字典显示
	 * @param 
	 */
	@RequestMapping(value="/wlnames")
	@ResponseBody
	public String jsons3(HttpServletRequest request) {
		String m1=request.getParameter("t2");
		if(m1==null){
			m1="";
		}
		return dicdangerservice.findByMs(m1);
	}
	
	/**
	 * 物料名称验证
	 * @param 
	 */
	@RequestMapping(value="/wlnmck")
	@ResponseBody
	public String wlnmck(HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		String wlnm=request.getParameter("M1");
		String ccfs=request.getParameter("M5");
		String id=request.getParameter("ID");
		if(id==null||id.equals("")){
			id="0";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyid", sessionuser.getQyid());
		map.put("wlnm", wlnm);
		map.put("ccfs", ccfs);
		map.put("id", id);
		return bisWlxxService.wlnmck(map);
	}
	
	
}
