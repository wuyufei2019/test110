package com.cczu.model.xfssgl.controller;

import java.util.HashMap;
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

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.model.xfssgl.service.XfssglXfssjcService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 消防设施检查Controller
 * @author wbth
 */
@Controller
@RequestMapping("xfssgl/xfssjc")
public class PageXfssjcController extends BaseController{
	
	@Autowired
	private XfssglXfssjcService xfssglXfssjcService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	
	/**
	 * 跳转到列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type", 2);
				else
					model.addAttribute("type", 1);
			}
		}else {//非企业用户页面
			model.addAttribute("type", 2);
		}
		return "xfssgl/xfssjc/index";
	}
	
	/**
	 * 消防设施检查list页面 
	 * @param request
	 */
	@RequiresPermissions("xfssgl:xfssjc:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("xfssgl_xfssjc_cx_qyname"));
		map.put("category", request.getParameter("xfssgl_xfssjc_category"));
		map.put("xfssname", request.getParameter("xfssgl_xfssjc_name"));
		map.putAll(getAuthorityMap());//企业用户登录会有qyid,集团用户登录会有fid,没有qyid
		return xfssglXfssjcService.dataGrid(map);	
		
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@SuppressWarnings("unchecked")
	@RequiresPermissions("xfssgl:xfssjc:view")
	@RequestMapping(value = "view/{id}/{employeeid}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, @PathVariable("employeeid") Long employeeid, Model model) {
		Map<String, Object> mapData = new HashMap<>();
		mapData.put("id", id);
		 mapData.put("employeeid", employeeid);
		ShiroUser user = UserUtil.getCurrentShiroUser();
		mapData.put("qyid",user.getQyid());
		Map<String, Object> entity = xfssglXfssjcService.findInfoByIds(mapData);
		model.addAttribute("entity", entity);

		return "xfssgl/xfssjc/view";
	}
	
	/**
	 * 删除消防设施检查信息
	 */
	@RequiresPermissions("xfssgl:xfssjc:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			xfssglXfssjcService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

}
