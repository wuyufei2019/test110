package com.cczu.model.hjbh.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.hjbh.entity.HJBH_OtherDw;
import com.cczu.model.hjbh.service.HjbhOtherDwService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 外单位信息
 * @author wbth
 * @date 2018/04/12
 */
@Controller
@RequestMapping("hjbh/otherDw")
public class PageHjbhOtherDwController extends BaseController{
	
	@Autowired
	private HjbhOtherDwService hjbhOtherDwService;
	
	
	@RequestMapping(value="wxfwjson") 
	@ResponseBody
	public List<Object> getWxfwjson(){
		return null;
	}
	
	/**
	 * 跳转到添加外单位信息页面
	 * 
	 * @param model
	 */
	@RequiresPermissions("hjbh:wxgl:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "odwcreate");
		return "hjbh/wxgl/otherdwform";
	}
	
	/**
	 * 添加外单位信息
	 * @param request,model
	 */
	@RequiresPermissions("hjbh:wxgl:add")
	@RequestMapping(value = "odwcreate" )
	@ResponseBody
	public String odwcreate(HJBH_OtherDw entity) {
		String datasuccess="success";
		Timestamp t= DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		hjbhOtherDwService.addInfo(entity);
		return datasuccess;
	}
	
	
	
	/**
	 * 获取查看页面中的外单位页面list
	 * 
	 * @param model
	 */
	@RequiresPermissions("hjbh:wxgl:add")
	@RequestMapping(value = "list/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String list(@PathVariable("id")long id) {
		List<HJBH_OtherDw> list=hjbhOtherDwService.findAllById1(id);
		return JsonMapper.getInstance().toJson(list);
	}
	
	/**
	 * 修改外单位信息页面跳转(未插入数据库)
	 * 
	 * @param model
	 */
	@RequiresPermissions("hjbh:wxgl:add")
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String update(Model model,HttpServletRequest request) {
		String data=request.getParameter("data");
		model.addAttribute("time", request.getParameter("time"));
		model.addAttribute("action", "odwupdate");
		model.addAttribute("entity", JsonMapper.getInstance().fromJson(data, HJBH_OtherDw.class));
		return "hjbh/wxgl/otherdwform";
	}
	
	/**
	 * 修改外单位信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("hjbh:wxgl:add")
	@RequestMapping(value = "odwupdate/{id}", method = RequestMethod.GET)
	public String odwupdate(@PathVariable("id")long id,Model model,HttpServletRequest request) {
		HJBH_OtherDw entity = hjbhOtherDwService.findById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("action", "odwcreate");
		return "hjbh/wxgl/otherdwform";
	}
	
	
	/**
	 * 删除外单位信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("hjbh:wxgl:delete")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") long id) {
		String datasuccess = "删除成功";
		hjbhOtherDwService.deleteInfo(id);
		return datasuccess;
	}
	
	
}
