package com.cczu.model.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.service.WghglWgyxjjlService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 网格员巡检记录
 * @author zpc
 * @date 2017/09/27
 */
@Controller
@RequestMapping("wghgl/xjjl")
public class PageWghglWgxjjlController extends BaseController{

	@Autowired
	private WghglWgyxjjlService wghglWgyxjjlService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "wghgl/wgxjjl/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		ShiroUser user = UserUtil.getCurrentShiroUser();
		map.put("qyname",request.getParameter("wghgl_xjjl_qyname"));//企业名称关键字
		map.put("starttime", request.getParameter("wghgl_xjjl_starttime"));//巡检开始时间
		map.put("finishtime", request.getParameter("wghgl_xjjl_finishtime"));//巡检结束时间
		map.put("checkresult", request.getParameter("wghgl_xjjl_checkresult"));//检查结果
		map.put("xjjlxzqy", request.getParameter("wghgl_xjjl_xzqy"));//搜索框行政区域
		map.put("qyid", request.getParameter("qyid"));//搜索框行政区域
		if("0".equals(user.getUsertype())){//安监
			map.put("xzqy",user.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0)
				map.put("jglx",user.getUserroleflg());
		}
		return wghglWgyxjjlService.dataGrid(map);
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="tablist/{qyid}")
	@ResponseBody
	public Map<String, Object> getData2(@PathVariable("qyid") Integer qyid,HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyid", qyid);
		return wghglWgyxjjlService.dataGrid(map);
	}

	/**
	 * 查看页面跳转
	 * 
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		// 查询选择的产品信息
		long id1 = id;
		Map<String,Object> xj = wghglWgyxjjlService.findInforById(id1);
		model.addAttribute("xjlist", xj);
		return "wghgl/wgxjjl/view";
	}

	/**
	 * 巡检内容list页面
	 * @param request
	 */
	@RequestMapping(value = "xjnrlist/{id1}")
	@ResponseBody
	public Map<String, Object> xjnrlist(@PathVariable("id1") Long id1, HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("id1", id1);
		return wghglWgyxjjlService.xjnrdataGrid(map);
	}
	
	/**
	 * 删除网格巡检记录信息
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			wghglWgyxjjlService.deleteYchByYcrid(Long.parseLong(arrids[i]));
			wghglWgyxjjlService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
}
