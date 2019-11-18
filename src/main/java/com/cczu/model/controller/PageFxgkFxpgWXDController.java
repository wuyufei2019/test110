package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
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
import com.cczu.model.entity.FXGK_MajorRisk;
import com.cczu.model.entity.FXGK_WxdRiskAssessment;
import com.cczu.model.service.FxgkFxpgMajorRiskService;
import com.cczu.model.service.FxgkFxpgWxdService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 风险评估wxd信息controller
 * 
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("fxpg/wxd")
public class PageFxgkFxpgWXDController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private FxgkFxpgWxdService fxgkfxpgwxdservice;
	@Autowired
	private FxgkFxpgMajorRiskService fxgkfxpgmajorriskservice;
	
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
		return "fxgk/fxpg/wxd/index";
	}

	
	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("fxpg:wxd:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("unit", request.getParameter("view_unit"));
		map.put("material", request.getParameter("view_material"));
		map.put("qyname", request.getParameter("view_qyname"));
		map.putAll(getAuthorityMap());
		return fxgkfxpgwxdservice.dataGrid(map);
	}

	
	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("fxpg:wxd:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("username", UserUtil.getCurrentShiroUser().getName());
		return "fxgk/fxpg/wxd/form";
	}
	
	
	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("fxpg:wxd:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") long id, Model model) {
		// 查询选择的风险评估信息
		FXGK_WxdRiskAssessment entity = fxgkfxpgwxdservice.findById(id);
		model.addAttribute("entity", entity);
		// 返回页面
		model.addAttribute("action", "update");
		return "fxgk/fxpg/wxd/form";
	}
	
	/**
	 * 添加风险评估信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("fxpg:wxd:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(FXGK_WxdRiskAssessment e,HttpServletRequest request) {
		//不需要事务
		String datasuccess = "failed";
		Timestamp t= DateUtils.getSysTimestamp();
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		e.setS1(t);
		e.setS2(t);
		e.setS3(0);
		e.setQyid(sessionuser.getQyid());
		long id=fxgkfxpgwxdservice.addInfoReID(e);
		if(id>0&&e.getRiskvalue()>=16){
			addMajorRisk(e);	
		}
		datasuccess="success";
		return datasuccess;
	}
	
	
	/**
	 * 修改风险评估信息
	 * @param request,model
	 */
	@RequiresPermissions("fxpg:wxd:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(FXGK_WxdRiskAssessment entity, Model model) {
		String datasuccess = "success";
		FXGK_WxdRiskAssessment old=fxgkfxpgwxdservice.findById(entity.getID());
		if (entity.getRiskvalue()>=16 && old.getRiskvalue()<16) {
			// 增加重大风险
			addMajorRisk(entity);
		} else if (entity.getRiskvalue()<16 && old.getRiskvalue()>=16) {
			// 删除重大风险
			fxgkfxpgmajorriskservice.deletinfo(entity.getID());
		}
		Timestamp t = DateUtils.getSysTimestamp();
		entity.setS2(t);
		fxgkfxpgwxdservice.updateInfo(entity);
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
	@RequiresPermissions("fxpg:wxd:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			fxgkfxpgwxdservice.deleteInfo(Long.parseLong(arrids[i]));
			fxgkfxpgmajorriskservice.deletinfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("fxpg:wxd:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String sview(@PathVariable("id") long id, Model model) {
		FXGK_WxdRiskAssessment entity = fxgkfxpgwxdservice.findById(id);
		model.addAttribute("entity", entity);
		return "fxgk/fxpg/wxd/view";
	}
	
	
	public void addMajorRisk(FXGK_WxdRiskAssessment e){
		FXGK_MajorRisk risk=new FXGK_MajorRisk();
		risk.setId1(e.getID());
		risk.setDeptid(e.getDeptid());
		risk.setQyid(UserUtil.getCurrentShiroUser().getQyid());
		risk.setName(e.getUnit());
		risk.setAnalysistime(e.getAnalysistime());
		risk.setAnalysisper(e.getAnalysisper());
		risk.setAuditor(e.getAuditor());
		risk.setReviewer(e.getReviewer());
		risk.setRisklevel(e.getRisklevel());
		risk.setRiskvalue((float)e.getRiskvalue());
		risk.setImprovemeasure(e.getImprovemeasure());
		fxgkfxpgmajorriskservice.addInfo(risk);
	}

}
