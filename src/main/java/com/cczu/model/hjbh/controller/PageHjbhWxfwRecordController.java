package com.cczu.model.hjbh.controller;

import java.sql.Timestamp;
import java.text.ParseException;
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
import com.cczu.model.hjbh.entity.HJBH_DangerTrashRecord;
import com.cczu.model.hjbh.entity.HJBH_DangerTrashRecordDetail;
import com.cczu.model.hjbh.service.HjbhWxfwDetailService;
import com.cczu.model.hjbh.service.HjbhWxfwRecordService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 风险评估信息controller
 * 
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("hjbh/wxfw")
public class PageHjbhWxfwRecordController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private HjbhWxfwDetailService hjbhWxfwDetailService;
	@Autowired
	private HjbhWxfwRecordService hjbhWxfwRecordService;
	
/**+++++++++++++++++++++++++++++++++++++危险废物记录++++++++++++++++++++++++++++++++++++++++++****/
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
					model.addAttribute("type", 2);
				else
					model.addAttribute("type", 1);
			}
		}else {//非企业用户页面
			model.addAttribute("type", 2);
		}
		return "hjbh/wxfw/index";
	}

	
	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("hjbh:wxfw:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("year", request.getParameter("view_year"));
		map.putAll(getAuthorityMap());
		return hjbhWxfwRecordService.dataGrid(map);
	}

	
	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("hjbh:wxfw:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		User user = UserUtil.getCurrentUser();
		model.addAttribute("username", user.getName());
		model.addAttribute("phone", user.getPhone());
		return "hjbh/wxfw/form";
	}
	
	
	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("hjbh:wxfw:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") long id, Model model) {
		// 查询选择的风险评估信息
		HJBH_DangerTrashRecord entity = hjbhWxfwRecordService.findById(id);
		model.addAttribute("entity", entity);
		// 返回页面
		model.addAttribute("action", "update");
		return "hjbh/wxfw/form";
	}
	
	/**
	 * 添加信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("hjbh:wxfw:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(HttpServletRequest request) {
		//不需要事务
		String datasuccess = "failed";
		Timestamp t= DateUtils.getSysTimestamp();
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		String entity=request.getParameter("entity");
		HJBH_DangerTrashRecord e= JsonMapper.getInstance().fromJson(entity,HJBH_DangerTrashRecord.class);
		e.setS1(t);
		e.setS2(t);
		e.setS3(0);
		e.setQyid(sessionuser.getQyid());
		long id=hjbhWxfwRecordService.addInfoReID(e);
		if(id>0){
			String list=request.getParameter("list");
			List<HJBH_DangerTrashRecordDetail> l= JSON.parseArray(list, HJBH_DangerTrashRecordDetail.class);
			for(HJBH_DangerTrashRecordDetail action: l){
				action.setRecordid(id);
				hjbhWxfwDetailService.addInfo(action);
			}
		}
		datasuccess="success";
		return datasuccess;
	}
	
	
	/**
	 * 修改信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("hjbh:wxfw:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(HJBH_DangerTrashRecord entity, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		entity.setS2(t);
		hjbhWxfwRecordService.updateInfo(entity);
		// 返回结果
		return datasuccess;
	}

	
	/**
	 * 删除风险评估信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("hjbh:wxfw:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			hjbhWxfwRecordService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("hjbh:wxfw:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String sview(@PathVariable("id") long id, Model model) {
		HJBH_DangerTrashRecord entity = hjbhWxfwRecordService.findById(id);
		model.addAttribute("entity", entity);
		return "hjbh/wxfw/view";
	}
	
	
	/**+++++++++++++++++++++++++++++++++++++危险废物信息++++++++++++++++++++++++++++++++++++++++++****/
	
	
	/**--------------------------------------危险废物附件详细信息---------------------------------------------***/
	
	/**
	 * 修改详细信息跳转(未插入数据库)
	 * 
	 * @param model
	 */
	@RequiresPermissions("hjbh:wxfw:add")
	@RequestMapping(value = "detailupdate", method = RequestMethod.GET)
	public String updateAction(Model model,HttpServletRequest request) {
		String data=request.getParameter("data");
		model.addAttribute("action", "update");
		model.addAttribute("time", request.getParameter("time"));
		HJBH_DangerTrashRecordDetail d=JsonMapper.getInstance().fromJson(data, HJBH_DangerTrashRecordDetail.class);
		model.addAttribute("entity", d);
		return "hjbh/wxfw/detailform";
	}
	
	/**
	 * 查看附件详细信息list
	 * 
	 * @param model
	 */
	@RequiresPermissions("hjbh:wxfw:add")
	@RequestMapping(value = "detaillist/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String viewAction(@PathVariable("id")long id) {
		Map<String,Object> list=hjbhWxfwDetailService.dataGridDetail(id);
		return JsonMapper.getInstance().toJson(list);
	}
	
	/**
	 * 添加附件详细信息跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("hjbh:wxfw:add")
	@RequestMapping(value = "detailcreate", method = RequestMethod.GET)
	public String createAction(Model model) {
		model.addAttribute("action", "createdetail");
		return "hjbh/wxfw/detailform";
	}
	
	/**
	 * 修改附件详细信息跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("hjbh:wxfw:add")
	@RequestMapping(value = "updatedetail/{id}", method = RequestMethod.GET)
	public String updateaction(@PathVariable("id")long id,Model model,HttpServletRequest request) {
		HJBH_DangerTrashRecordDetail entity= hjbhWxfwDetailService.findInfoById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("action", "updatedetail");
		return "hjbh/wxfw/detailform";
	}
	
	
	/**
	 * 增加附件详细信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("hjbh:wxfw:update")
	@RequestMapping(value = "createdetail")
	@ResponseBody
	public String createAct(HJBH_DangerTrashRecordDetail entity, Model model) {
		String datasuccess = "success";
		hjbhWxfwDetailService.addInfo(entity);
		// 返回结果
		return datasuccess;
	}
	/**
	 * 修改附件详细信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("hjbh:wxfw:update")
	@RequestMapping(value = "updatedetail")
	@ResponseBody
	public String updateAct(HJBH_DangerTrashRecordDetail entity, Model model) {
		String datasuccess = "success";
		hjbhWxfwDetailService.updateInfo(entity);
		// 返回结果
		return datasuccess;
	}
	
	
	/**
	 * 删除附件详细信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("hjbh:wxfw:delete")
	@RequestMapping(value = "deletedetail/{id}")
	@ResponseBody
	public String deleteAct(@PathVariable("id") long id) {
		String datasuccess = "删除成功";
		hjbhWxfwDetailService.deleteInfo(id);
		return datasuccess;
	}
	
	/**--------------------------------------风险活动---------------------------------------------***/

}
