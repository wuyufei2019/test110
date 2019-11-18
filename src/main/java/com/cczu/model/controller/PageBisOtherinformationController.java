package com.cczu.model.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.BIS_OtherinformationEntity;
import com.cczu.model.service.IBisOtherinformationService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
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
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 其他信息controller
 * 
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("bis/otherinformation")
public class PageBisOtherinformationController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IBisOtherinformationService bisOtherinformationService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "qyxx/otherinformation/ajindex";
				else
					return "qyxx/otherinformation/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "qyxx/otherinformation/ajindex";
		}	
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("bis:otherinformation:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		// 获取企业id
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());

		Map<String, Object> map = getPageMap(request);
		if (be != null) {
			map.put("qyid", be.getID());
		}
		map.put("m11", request.getParameter("bis_otherinformation_m11"));
		return bisOtherinformationService.dataGrid(map);
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

		return bisOtherinformationService.dataGrid(map);
	}

	/**
	 * list页面(安监局查看所有企业其他信息)
	 * 
	 * @param request
	 */
	@RequiresPermissions("bis:otherinformation:view")
	@RequestMapping(value = "ajlist")
	@ResponseBody
	public Map<String, Object> getAllData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("bis_otherinformation_cx_qyname"));
		map.put("m11", request.getParameter("bis_otherinformation_m11"));
		map.putAll(getAuthorityMap());
		return bisOtherinformationService.dataGridAJ(map);
	}

	/**
	 * 添加页面跳转
	 *
	 * @param model
	 */
	@RequiresPermissions("bis:otherinformation:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "qyxx/otherinformation/form";

	}

	/**
	 * 添加其他信息
	 *
	 * @param request
	 *            ,model
	 */
	@RequiresPermissions("bis:otherinformation:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(BIS_OtherinformationEntity bt,HttpServletRequest request, Model model) {
		String datasuccess = "success";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--其他信息  【添加操作】");

		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){
			bt.setID1(sessionuser.getQyid());
		}
		Timestamp t=DateUtils.getSysTimestamp();
		bt.setS1(t);
		bt.setS2(t);
		bt.setS3(0);
		bisOtherinformationService.addInfo(bt);
		return datasuccess;

	}

	/**
	 * 修改页面跳转
	 *
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("bis:otherinformation:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		// 查询选择的其他信息
		long id1 = id;
		BIS_OtherinformationEntity bt = bisOtherinformationService.findById(id1);
		model.addAttribute("otherinformationlist", bt);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		// 返回页面
		model.addAttribute("action", "update");
		return "qyxx/otherinformation/form";
	}

	/**
	 * 修改其他信息
	 *
	 * @param request
	 *
	 */
	@RequiresPermissions("bis:otherinformation:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_OtherinformationEntity bt,HttpServletRequest request, Model model) {
		String datasuccess = "success";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--其他信息  【修改操作】");
		Timestamp t=DateUtils.getSysTimestamp();
		bt.setS2(t);
		BIS_OtherinformationEntity bis = bisOtherinformationService.findById(bt.getID());
		bisOtherinformationService.updateInfo(bt);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除其他信息
	 *
	 * @param
	 * @param
	 * @throws ParseException
	 */
	@RequiresPermissions("bis:otherinformation:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";

		// 可以批量删除
		String[] aids = ids.split(",");
		for (int i = 0; i < aids.length; i++) {
			bisOtherinformationService.deleteInfo(Long.parseLong(aids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--其他信息  【删除操作】");
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 *
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		// 查询选择的产品信息

		long id1 = id;
		BIS_OtherinformationEntity bs = bisOtherinformationService.findById2(id1);
		model.addAttribute("otherinformationlist", bs);

		// 返回页面
		model.addAttribute("action", "view");
		return "qyxx/otherinformation/view";
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
	@RequiresPermissions("bis:otherinformation:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = bisOtherinformationService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}


	/**
	 * 导出excel
	 *
	 */
	@RequiresPermissions("bis:otherinformation:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("bis_otherinformation_cx_qyname"));
		map.put("m11", request.getParameter("bis_otherinformation_m11"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		bisOtherinformationService.exportExcel(response, map);

	}

	/**
	 * 显示所有列
	 *
	 * @param
	 *            ,model
	 */
	@RequiresPermissions("bis:otherinformation:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","bis/otherinformation/export");
		return "common/formexcel";
	}

}
