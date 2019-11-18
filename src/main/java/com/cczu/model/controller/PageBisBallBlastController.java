package com.cczu.model.controller;

import com.cczu.model.entity.BIS_BallBlastEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.impl.BisBallBlastServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
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
import java.util.Map;

/**
 * 抛丸信息controller
 * 
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("bis/ballblast")
public class PageBisBallBlastController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private BisBallBlastServiceImpl bisBallBlastService;

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
					return "qyxx/ballblast/ajindex";
				else
					return "qyxx/ballblast/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "qyxx/ballblast/ajindex";
		}	
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("bis:ballblast:view")
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

		map.put("m1", request.getParameter("M1"));
		map.put("m5", request.getParameter("M5"));
		map.put("m6", request.getParameter("M6"));
		map.put("m7", request.getParameter("M7"));
		map.put("m8", request.getParameter("M8"));
		return bisBallBlastService.dataGrid(map);
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
		return bisBallBlastService.dataGrid(map);
	}

	/**
	 * list页面(安监局查看所有企业抛丸信息)
	 * 
	 * @param request
	 */
	@RequiresPermissions("bis:ballblast:view")
	@RequestMapping(value = "ajlist")
	@ResponseBody
	public Map<String, Object> getAllData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("bis_ballblast_cx_qyname"));
		map.put("m1", request.getParameter("M1"));
		map.put("m5", request.getParameter("M5"));
		map.put("m6", request.getParameter("M6"));
		map.put("m7", request.getParameter("M7"));
		map.put("m8", request.getParameter("M8"));
		map.putAll(getAuthorityMap());
		return bisBallBlastService.dataGridAJ(map);
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("bis:ballblast:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "qyxx/ballblast/form";
	}

	/**
	 * 添加抛丸信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("bis:ballblast:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(BIS_BallBlastEntity bs, Model model, HttpServletRequest request) {
		String datasuccess = "success";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			long ID = be.getID();//获取到企业id
			bs.setId1(ID);
		}
		Timestamp t=DateUtils.getSysTimestamp();
		bs.setS1(t);
		bs.setS2(t);
		bs.setS3(0);
		bisBallBlastService.addInfo(bs);
		log.info(sessionuser.getLoginName()+":  一企一档--抛丸信息  【添加操作】");
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:ballblast:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		// 查询选择的抛丸信息
		BIS_BallBlastEntity bs = bisBallBlastService.findById(id);

		model.addAttribute("entity", bs);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		// 返回页面
		model.addAttribute("action", "update");
		return "qyxx/ballblast/form";
	}

	/**
	 * 修改抛丸信息
	 * 
	 * @param bs,model
	 */
	@RequiresPermissions("bis:ballblast:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_BallBlastEntity bs, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		bs.setS2(t);
		bisBallBlastService.updateInfo(bs);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--抛丸信息  【修改操作】");
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除抛丸信息
	 * 
	 * @param ids
	 * @throws ParseException
	 */
	@RequiresPermissions("bis:ballblast:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--抛丸信息  【删除操作】");
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			bisBallBlastService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {

		BIS_BallBlastEntity bs = bisBallBlastService.findById(id);
		model.addAttribute("entity", bs);
		// 返回页面
		return "qyxx/ballblast/view";
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:ballblast:view")
	@RequestMapping(value = "sview/{id}", method = RequestMethod.GET)
	public String sview(@PathVariable("id") Integer id, Model model) {
		// 查询选择的产品信息
		long id1 = id;
		BIS_BallBlastEntity bs = bisBallBlastService.findById(id1);

		model.addAttribute("entity", bs);
		// 返回页面
		model.addAttribute("action", "view");
		return "qyxx/ballblast/view";
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
	@RequiresPermissions("bis:ballblast:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			resultmap = bisBallBlastService.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("bis:ballblast:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("bis_ballblast_cx_qyname"));
		map.put("m1", request.getParameter("M1"));
		map.put("m5", request.getParameter("M5"));
		map.put("m6", request.getParameter("M6"));
		map.put("m7", request.getParameter("M7"));
		map.put("m8", request.getParameter("M8"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		bisBallBlastService.exportExcel(response, map);

	}

	/**
	 * 显示所有列
	 * 
	 * @param model
	 */
	@RequiresPermissions("bis:ballblast:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","bis/ballblast/export");
		return "/common/formexcel";
	}
	
}
