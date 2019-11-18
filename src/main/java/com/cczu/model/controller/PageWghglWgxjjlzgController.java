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

import com.cczu.model.service.WghglWgyxjjlzgService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 网格员巡检记录整改
 * @author zpc
 * @date 2017/10/14
 */
@Controller
@RequestMapping("wghgl/xjjlzg")
public class PageWghglWgxjjlzgController extends BaseController{

	@Autowired
	private WghglWgyxjjlzgService wghglWgyxjjlzgService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser user = UserUtil.getCurrentShiroUser();
		if("1".equals(user.getUsertype())){
			return "wghgl/xjjlzg/qyindex";
		}else{
			return "wghgl/xjjlzg/index";
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
		map.put("qyname",request.getParameter("wghgl_xjjlzg_qyname"));//企业名称关键字
		map.put("starttime", request.getParameter("wghgl_xjjlzg_starttime"));//开始时间
		map.put("finishtime", request.getParameter("wghgl_xjjlzg_finishtime"));//结束时间
		map.put("dangerstatus", request.getParameter("wghgl_xjjlzg_dangerstatus"));//整改状态
		map.put("xjjlzgxzqy", request.getParameter("wghgl_xjjlzg_xzqy"));//搜索框行政区域
		//map.put("dangerorigin", request.getParameter("wghgl_xjjlzg_dangerorigin"));//隐患来源
		if("1".equals(user.getUsertype())){//企业
			map.put("qyid",(user.getQyid()));
		}
		if("0".equals(user.getUsertype())){//安监
			map.put("xzqy",user.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0)
				map.put("jglx",user.getUserroleflg());
		}
		return wghglWgyxjjlzgService.dataGrid(map);
	}
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="syindex")
	public String syindex(Model model) {
		return "wghgl/xjjlzg/syindex";
	}
	
	/**
	 * 首页list页面
	 */
	@RequestMapping(value="sylist")
	@ResponseBody
	public Map<String, Object> getsylistData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		ShiroUser user = UserUtil.getCurrentShiroUser();
		map.put("qyname",request.getParameter("wghgl_xjjlzg_qyname"));//企业名称关键字
		map.put("starttime", request.getParameter("wghgl_xjjlzg_starttime"));//开始时间
		map.put("finishtime", request.getParameter("wghgl_xjjlzg_finishtime"));//结束时间
		map.put("dangerstatus", "0");//整改状态
		map.put("xjjlzgxzqy", request.getParameter("wghgl_xjjlzg_xzqy"));//搜索框行政区域
		//map.put("dangerorigin", request.getParameter("wghgl_xjjlzg_dangerorigin"));//隐患来源
		if("0".equals(user.getUsertype())){//安监
			map.put("xzqy",user.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0)
				map.put("jglx",user.getUserroleflg());
		}
		return wghglWgyxjjlzgService.dataGrid(map);
	}
	
	/**
	 * 查看页面跳转
	 * 隐患机记录id
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("yhjlid",id);
		return "wghgl/xjjlzg/view";
	}
	
	/**
	 * 整改list页面
	 */
	@RequestMapping(value="zglist")
	@ResponseBody
	public Map<String, Object> getzglistData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("yhjlid",request.getParameter("yhjlid"));
		return wghglWgyxjjlzgService.zglistdataGrid(map);
	}
	
	/**
	 * 查看隐患记录详情页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "viewxq/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long yhjlid, Model model) {
		Map<String,Object> yhpcjl = wghglWgyxjjlzgService.findInforById(yhjlid);
		
		model.addAttribute("yhpcjl", yhpcjl);
		//返回页面
		model.addAttribute("action", "view");
		return "wghgl/xjjlzg/viewxq";
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
			wghglWgyxjjlzgService.deleteYhrByYchid(Long.parseLong(arrids[i]));
			wghglWgyxjjlzgService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
}
