package com.cczu.model.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cczu.sys.comm.mapper.JsonMapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.YhpcXjjlService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 巡检记录
 * @author zpc
 * @date 2017/08/24
 */
@Controller
@RequestMapping("yhpc/xjjl")
public class PageYhpcXjjlController extends BaseController{

	@Autowired
	private YhpcXjjlService yhpcXjjlService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		ShiroUser user = UserUtil.getCurrentShiroUser();
		model.addAttribute("iszdwxy", request.getParameter("iszdwxy"));
		if("1".equals(user.getUsertype())){
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(user.getQyid());
			if(be.getIsbloc()!=null&&be.getIsbloc()==1){
				return "yhpc/xjjl/index";
			}else{//判断是否为集团公司
				return "yhpc/xjjl/qyindex";
			}
		}else if("0".equals(user.getUsertype())||"9".equals(user.getUsertype())){
			return "yhpc/xjjl/index";
		}else{
			return "../error/403";
		}
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		ShiroUser user = UserUtil.getCurrentShiroUser();
		map.put("iszdwxy", request.getParameter("iszdwxy"));//是否重大危险源
		map.put("jcdid", request.getParameter("jcdid"));//检查点id
		map.put("jcdtype", request.getParameter("jcdtype"));//检查点类型
		map.put("jcdname", request.getParameter("yhpc_xjjl_jcdname"));//检查点名称
		map.put("starttime", request.getParameter("yhpc_xjjl_starttime"));//巡检开始时间
		map.put("finishtime", request.getParameter("yhpc_xjjl_finishtime"));//巡检结束时间
		map.put("checkresult", request.getParameter("yhpc_xjjl_checkresult"));//检查结果
		map.put("qyname",request.getParameter("yhpc_xjjl_qyname"));
		if("1".equals(user.getUsertype())&&request.getParameter("jcdid")==null){//企业
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(user.getQyid());
			if(be.getIsbloc()!=null&&be.getIsbloc()==1){
				map.put("fid", user.getQyid());
				return yhpcXjjlService.ajdataGrid(map);
			}//判断是否为集团公司
			else{
				map.put("checkplan", request.getParameter("yhpc_xjjl_checkplan"));//所属班次
				map.put("qyid",(user.getQyid()));
				return yhpcXjjlService.dataGrid(map);
			}
		}else if("0".equals(user.getUsertype())){//安监
			map.put("xzqy",user.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0)
				map.put("jglx",user.getUserroleflg());
		}
		
		return yhpcXjjlService.ajdataGrid(map);
	}

	/**
	 * 删除巡检记录信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("yhpc:xjjl:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			yhpcXjjlService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * 
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		// 查询选择的产品信息

		long id1 = id;
		Map<String,Object> xj = yhpcXjjlService.findInforById(id1);
		model.addAttribute("xjlist", xj);

		return "yhpc/xjjl/view2";
	}
	
	/**
	 * 图片跳转页面
	 */
	@RequestMapping(value="img")
	public String img(Model model,HttpServletRequest request) {
		model.addAttribute("imgurl",request.getParameter("imgurl"));
	    return "yhpc/xjjl/img";
	}
	
	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("yhpc:xjjl:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","yhpc/xjjl/export");
		return "common/formexcel";
	}
	
	/**
	 * 企业导出Excel
	 * 
	 * @param request
	 */
	@RequiresPermissions("yhpc:xjjl:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		ShiroUser user = UserUtil.getCurrentShiroUser();
		map.put("iszdwxy", request.getParameter("iszdwxy"));//是否重大危险源
		map.put("jcdname", request.getParameter("yhpc_xjjl_jcdname"));//检查点名称
		map.put("starttime", request.getParameter("yhpc_xjjl_starttime"));//巡检开始时间
		map.put("finishtime", request.getParameter("yhpc_xjjl_finishtime"));//巡检结束时间
		map.put("checkresult", request.getParameter("yhpc_xjjl_checkresult"));//检查结果
		map.put("qyid",(user.getQyid()));
		map.put("checkplan", request.getParameter("yhpc_xjjl_checkplan"));//所属班次
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		yhpcXjjlService.exportExcel(response, map);
	}
	
	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("yhpc:xjjl:export2")
	@RequestMapping(value = "colindex2", method = RequestMethod.GET)
	public String colindex2(Model model) {
		model.addAttribute("url","yhpc/xjjl/export2");
		return "common/formexcel";
	}
	
	/**
	 * 安监导出Excel
	 * 
	 * @param request
	 */
	@RequiresPermissions("yhpc:xjjl:export2")
	@RequestMapping(value = "export2")
	@ResponseBody
	public void getExcel2(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		ShiroUser user = UserUtil.getCurrentShiroUser();
		map.put("iszdwxy", request.getParameter("iszdwxy"));//是否重大危险源
		map.put("jcdname", request.getParameter("yhpc_xjjl_jcdname"));//检查点名称
		map.put("starttime", request.getParameter("yhpc_xjjl_starttime"));//巡检开始时间
		map.put("finishtime", request.getParameter("yhpc_xjjl_finishtime"));//巡检结束时间
		map.put("checkresult", request.getParameter("yhpc_xjjl_checkresult"));//检查结果
		map.put("xzqy",user.getXzqy());
		//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
		if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0)
			map.put("jglx",user.getUserroleflg());
		map.put("qyname",request.getParameter("yhpc_xjjl_qyname"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		yhpcXjjlService.exportExcel2(response, map);
	}

	/**
	 * 根据风险点颜色获取巡查信息
	 *
	 * @param fxdcolor
	 */
	@RequestMapping(value = "getxcinfo/{fxdcolor}")
	@ResponseBody
	public String getXcinfoByColor(@PathVariable("fxdcolor") String fxdcolor, HttpServletRequest request) {
		Map<String, Object> map = getAuthorityMap();
		map.put("fxdcolor", fxdcolor);
		map.put("checkpointid", request.getParameter("fxdid"));
		return JsonMapper.toJsonString(yhpcXjjlService.getXcinfoByFxdColor(map));
	}

}
