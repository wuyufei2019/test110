package com.cczu.model.sbssgl.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_TSQDEntity;
import com.cczu.model.sbssgl.service.SBSSGLTsqdService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 设备设施管理-台时确定controller
 */
@Controller
@RequestMapping("sbssgl/tsqd")
public class PageSbssglTsqdController extends BaseController {

	@Autowired
	private SBSSGLTsqdService sBSSGLTsqdService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model, HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else{
			return "../error/403";
		}
		return "sbssgl/tsqd/index";
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		map.put("deptname", request.getParameter("deptname"));
		map.put("m1", request.getParameter("m1"));
		map.put("m2", request.getParameter("m2"));
		map.put("m3", request.getParameter("m3"));
		map.putAll(getAuthorityMap());
		return sBSSGLTsqdService.dataGrid(map);
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			//删除台时确定信息
			sBSSGLTsqdService.deleteInfoById(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//获取台时确定信息
		Map<String,Object> tsqd = sBSSGLTsqdService.findById(id);	
		model.addAttribute("tsqd", tsqd);
		return "sbssgl/tsqd/view";
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		model.addAttribute("action", "create");
		return "sbssgl/tsqd/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(SBSSGL_TSQDEntity entity, Model model,HttpServletRequest request) {
		String datasuccess="success";
		if (entity.getQyid() == null) {// 如果页面中需要使用qyid字端，删掉此句即可
			entity.setQyid(UserUtil.getCurrentShiroUser().getQyid());
		}
		sBSSGLTsqdService.addInfo(entity);
		//返回结果
		return datasuccess;
	}
	

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		SBSSGL_TSQDEntity tsqd = sBSSGLTsqdService.find(id);	
		model.addAttribute("tsqd", tsqd);
		//返回页面
		model.addAttribute("action", "update");
		return "sbssgl/tsqd/form";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(SBSSGL_TSQDEntity entity, Model model,HttpServletRequest request){
		String datasuccess="success";
		if (entity.getQyid() == null) {// 如果页面中需要使用qyid字端，删掉此句即可
			entity.setQyid(UserUtil.getCurrentShiroUser().getQyid());
		}
		sBSSGLTsqdService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
}
