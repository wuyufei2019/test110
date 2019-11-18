package com.cczu.model.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.TS_Video;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.TsVideoService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 视频监控信息维护controller
 * @author jason
 * @date 2017年9月6日
 */
@Controller
@RequestMapping("bis/spjk")
public class PageBisVideoController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private TsVideoService tsVideoService;

	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "zxjkyj/sjwh/sp/index";
	}

	/**
	 * list页面
	 * @param request
	 */
	@RequiresPermissions("bis:spjk:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = getPageMap(request);
		 
		//用户类型(1企业，0安监局，9系统管理员)
		if(sessionuser.getUsertype().equals("0")){
			map.put("xzqy",sessionuser.getXzqy());
		}else if(sessionuser.getUsertype().equals("1")){
			map.put("qyid", sessionuser.getQyid());
		}

		return tsVideoService.dataGrid(map);
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("bis:spjk:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		TS_Video video=new TS_Video();
		video.setUsername("admin");
		video.setPassword("xq123456");
		video.setIp("");
		video.setPort("554");
		model.addAttribute("usertype", sessionuser.getUsertype());
		if(sessionuser.getUsertype().equals("1")){
			video.setID1(sessionuser.getQyid());
		}
		model.addAttribute("video", video);
		model.addAttribute("action", "create");
		return "zxjkyj/sjwh/sp/form";
	}
	
	/**
	 * 添加视频监控信息
	 * @param request,model
	 */
	@RequiresPermissions("bis:spjk:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(TS_Video video, Model model) {
		String datasuccess="success";
		
		if(!tsVideoService.isexist(video.getName(), 0))
			tsVideoService.addInfo(video);
		else
			datasuccess="error";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--视频信息  【增加操作】");

		return datasuccess;
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("bis:spjk:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("usertype", sessionuser.getUsertype());
		
		TS_Video video=tsVideoService.findById(id);
		model.addAttribute("video", video);
		//返回页面
		model.addAttribute("action", "update");
		return "zxjkyj/sjwh/sp/form";
	}
	
	/**
	 * 修改视频监控信息
	 * @param request,model
	 */
	@RequiresPermissions("bis:spjk:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(TS_Video video, Model model){
		String datasuccess="success";		

		if(!tsVideoService.isexist(video.getName(), video.getID()))
			tsVideoService.updateInfo(video);
		else
			datasuccess="error";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--视频信息  【修改操作】");

		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除视频监控信息
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("bis:spjk:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			tsVideoService.deleteInfo(Long.parseLong(arrids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--视频信息  【删除操作】");

		return datasuccess;
	}
	
	 
	/**
	 * 生产视频流
	 * @param id,model
	 */
	@RequiresPermissions("bis:spjk:reset")
	@RequestMapping(value = "reset", method = RequestMethod.GET)
	@ResponseBody
	public String reset(Model model) {
		tsVideoService.reset();
		return "操作完成！";
	}
}
