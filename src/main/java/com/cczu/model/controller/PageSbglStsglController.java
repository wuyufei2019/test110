package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.Sbgl_StsglEntity;
import com.cczu.model.entity.Sbgl_StsglUrlEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.SbglStsglService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 设备管理-三同时管理controller
 * 
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("sbgl/stsgl")
public class PageSbglStsglController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private SbglStsglService sbglStsglService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		if(request.getParameter("sys")!=null &&"1".equals(request.getParameter("sys")))
			model.addAttribute("sys", "sys");
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "sbgl/stsgl/index";
				else
					return "sbgl/stsgl/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "sbgl/stsgl/index";
		}	
	}


	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("sbgl:stsgl:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		map.put("projectname", request.getParameter("view_projectname"));
		return sbglStsglService.dataGrid(map);
	}


	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("sbgl:stsgl:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "sbgl/stsgl/form";
	}

	/**
	 * 添加信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("sbgl:stsgl:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(@RequestParam("list")String list,@RequestParam("entity")String entity) {
		String datasuccess = "success";
		Sbgl_StsglEntity e= JsonMapper.getInstance().fromJson(entity,Sbgl_StsglEntity.class);
		List<Sbgl_StsglUrlEntity> l= JSON.parseArray(list, Sbgl_StsglUrlEntity.class);
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		if (sessionuser.getUsertype().equals("1")) {
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			e.setID1(be.getID());
		}
		try {
			sbglStsglService.addInfo(e,l);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			datasuccess="error";
		}
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("sbgl:stsgl:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		Sbgl_StsglEntity bs = sbglStsglService.findById(id);
		model.addAttribute("entity", bs);
		// 返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "sbgl/stsgl/form";
	}

	/**
	 * 修改信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("sbgl:stsgl:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Sbgl_StsglEntity bs, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		bs.setS2(t);
		sbglStsglService.updateInfo(bs);

		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("sbgl:stsgl:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			sbglStsglService.deleteInfo(Long.parseLong(arrids[i]));
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
	public String view(@PathVariable("id") Integer id, Model model) {
		// 查询选择的产品信息
		long id1 = id;
		Sbgl_StsglEntity bs = sbglStsglService.findById(id1);
		model.addAttribute("entity", bs);
		// 返回页面
		return "sbgl/stsgl/view";
	}
	
	
	//------------------file附件操作----------------------------
	/**
	 * list页面
	 * @param request
	 */
	@RequiresPermissions("sbgl:stsgl:view")
	@RequestMapping(value = "filelist/{id}")
	@ResponseBody
	public Map<String, Object> getFileData(@PathVariable("id")long id) {
		List<Sbgl_StsglUrlEntity> list=sbglStsglService.findFileById1(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", list.size());
		return map;
	}
	
	/**
	 * 附件添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("sbgl:stsgl:add")
	@RequestMapping(value = "addfile", method = RequestMethod.GET)
	public String addFileLink(Model model) {
		model.addAttribute("action", "addfile");
		return "sbgl/stsgl/fileform";
	}
	
	/**
	 * 附件添加
	 * @param model
	 */
	@RequiresPermissions("sbgl:stsgl:add")
	@RequestMapping(value = "addfile", method = RequestMethod.POST)
	@ResponseBody
	public String addFile(Sbgl_StsglUrlEntity entity) {
		String datasuccess = "success";
		try {
			sbglStsglService.addFile(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			datasuccess="error";
		}
		return datasuccess;
	}
	
	/**
	 * 附件删除
	 * 
	 * @param model
	 */
	@RequiresPermissions("sbgl:stsgl:delete")
	@RequestMapping(value = "deletefile/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String deletefile(@PathVariable("id")long id,Model model) {
		String datasuccess = "删除成功";
		try {
			sbglStsglService.deleteFile(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			datasuccess="error";
		}
		return datasuccess;
	}


}
