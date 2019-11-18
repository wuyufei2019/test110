package com.cczu.model.hjbh.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.hjbh.entity.HJBH_OtherDw;
import com.cczu.model.hjbh.entity.HJBH_Wxgl;
import com.cczu.model.hjbh.service.HjbhOtherDwService;
import com.cczu.model.hjbh.service.HjbhWxglService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 危险废物特性管理
 * @author wbth
 * @date 2018/04/11
 */
@Controller
@RequestMapping("hjbh/wxfwgl")
public class PageHjbhWxglController extends BaseController{
	
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private HjbhWxglService hjbhWxglService;
	@Autowired
	private HjbhOtherDwService hjbhOtherDwService;
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type", 2);
				else
					model.addAttribute("type", 1);
			}
		}else {//非企业用户页面
			model.addAttribute("type", 2);
		}
		return "hjbh/wxgl/index";
	}
	
	/**
	 * 将获取的废物id 和 废物名称 转换为 json 格式
	 * @return
	 */
	@RequestMapping(value="wxfwjson") 
	@ResponseBody
	public List<Map<String, Object>> getWxfwjson(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		List<Map<String, Object>> list=hjbhWxglService.getWxfwIdNamejson(map);
		return list;
	}
		
	/**
	 * 获取危险废物特性列表
	 * @param request
	 * @return
	 */
	@RequiresPermissions("hjbh:wxgl:view")
	@RequestMapping(value="list") 
	@ResponseBody
	public Map<String, Object> getWxglList(HttpServletRequest request){
		Map<String, Object> map = getPageMap(request);
		map.put("name", request.getParameter("name"));
		map.put("kind", request.getParameter("kind"));
		map.put("qyname", request.getParameter("view_qyname"));
		map.putAll(getAuthorityMap());
 		Map<String, Object> map2 = hjbhWxglService.dataGrid(map);
		return map2;
	}

	/**
	 * 跳转到添加危险特性页面
	 * @param model
	 */
	@RequiresPermissions("hjbh:wxgl:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "wxglcreate");
		return "hjbh/wxgl/form";
	}
	
	/**
	 * 保存添加的危险特性信息和外单位信息list 
	 * @param model
	 */
	@RequiresPermissions("hjbh:wxgl:add")
	@RequestMapping(value = "wxglcreate" , method = RequestMethod.POST)
	@ResponseBody
	public String wxglcreate(HttpServletRequest request) {
		String datasuccess = "failed";
		Timestamp t= DateUtils.getSysTimestamp();
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		String entity=request.getParameter("entity");
		HJBH_Wxgl e= JsonMapper.getInstance().fromJson(entity,HJBH_Wxgl.class);
		e.setS1(t);
		e.setS2(t);
		e.setS3(0);
		e.setQyid(sessionuser.getQyid());
		long id=hjbhWxglService.addInfoReID(e);
		if(id>0){
			String list=request.getParameter("list");
			List<HJBH_OtherDw> l= JSON.parseArray(list, HJBH_OtherDw.class);
			for(HJBH_OtherDw odw: l){
				odw.setID1(id);
				odw.setS1(t);
				odw.setS2(t);
				odw.setS3(0);
				hjbhOtherDwService.addInfo(odw);
				
			}
		}
		datasuccess="success";
		return datasuccess;
	}
	
	/**
	 * 保存修改的危险特性信息
	 * @param model
	 */
	@RequiresPermissions("hjbh:wxgl:add")
	@RequestMapping(value = "wxglupdate" , method = RequestMethod.POST)
	@ResponseBody
	public String wxglupdate(HJBH_Wxgl entity) {
		String datasuccess = "failed";
		Timestamp t= DateUtils.getSysTimestamp();
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setQyid(sessionuser.getQyid());
		hjbhWxglService.updateInfo(entity);
		datasuccess="success";
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("hjbh:wxgl:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String sview(@PathVariable("id") long id, Model model) {
		HJBH_Wxgl entity = hjbhWxglService.findById(id);
		model.addAttribute("entity", entity);
		return "hjbh/wxgl/view";
	}
	
	/**
	 * 修改危险废物特性页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("hjbh:wxgl:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String toupdatewxfw(@PathVariable("id") long id, Model model) {
		HJBH_Wxgl entity = hjbhWxglService.findById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("action", "wxglupdate");
		return "hjbh/wxgl/form";
	}
	
	/**
	 * 删除危险废物特性信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("hjbh:wxgl:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			hjbhWxglService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
}
