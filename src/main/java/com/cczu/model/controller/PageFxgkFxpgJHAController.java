package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.cczu.model.entity.FXGK_MajorRisk;
import com.cczu.model.entity.FXGK_RiskAction;
import com.cczu.model.entity.FXGK_RiskAssessment;
import com.cczu.model.service.FxgkFxpgJhaActionService;
import com.cczu.model.service.FxgkFxpgMajorRiskService;
import com.cczu.model.service.FxgkFxpgjhaService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 风险评估信息controller
 * 
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("fxpg/jha")
public class PageFxgkFxpgJHAController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private FxgkFxpgjhaService fxgkfxpgservice;
	@Autowired
	private FxgkFxpgJhaActionService fxgkfxpgjhaactionservice;
	@Autowired
	private FxgkFxpgMajorRiskService fxgkfxpgmajorriskservice;
	
/**+++++++++++++++++++++++++++++++++++++风险评估++++++++++++++++++++++++++++++++++++++++++****/
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
		return "fxgk/fxpg/jha/index";
	}

	
	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("fxpg:jha:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.put("deptname", request.getParameter("view_deptname"));
		map.put("jobname", request.getParameter("view_jobname"));
		map.put("qyname", request.getParameter("view_qyname"));
		map.putAll(getAuthorityMap());
		return fxgkfxpgservice.dataGrid(map);
	}

	
	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("fxpg:jha:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("username", UserUtil.getCurrentShiroUser().getName());
		return "fxgk/fxpg/jha/form";
	}
	
	
	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("fxpg:jha:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") long id, Model model) {
		// 查询选择的风险评估信息
		FXGK_RiskAssessment entity = fxgkfxpgservice.findById(id);
		model.addAttribute("entity", entity);
		// 返回页面
		model.addAttribute("action", "update");
		return "fxgk/fxpg/jha/form";
	}
	
	/**
	 * 添加风险评估信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("fxpg:jha:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(HttpServletRequest request) {
		//不需要事务
		String datasuccess = "failed";
		Timestamp t= DateUtils.getSysTimestamp();
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		String entity=request.getParameter("entity");
		FXGK_RiskAssessment e= JsonMapper.getInstance().fromJson(entity,FXGK_RiskAssessment.class);
		e.setS1(t);
		e.setS2(t);
		e.setS3(0);
		e.setQyid(sessionuser.getQyid());
		long id=fxgkfxpgservice.addInfoReID(e);
		if(id>0){
			String list=request.getParameter("list");
			List<FXGK_RiskAction> l= JSON.parseArray(list, FXGK_RiskAction.class);
			for(FXGK_RiskAction action: l){
				action.setID1(id);
				fxgkfxpgjhaactionservice.addInfo(action);
				if("极度危险".equals(action.getRisklevel())||"高度危险".equals(action.getRisklevel())){
					addMajorRisk(action, e);
				}
			}
		}
		datasuccess="success";
		return datasuccess;
	}
	
	
	/**
	 * 修改风险评估信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("fxpg:jha:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(FXGK_RiskAssessment entity, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		entity.setS2(t);
		fxgkfxpgservice.updateInfo(entity);
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
	@RequiresPermissions("fxpg:jha:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			fxgkfxpgservice.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("fxpg:jha:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String sview(@PathVariable("id") long id, Model model) {
		FXGK_RiskAssessment entity = fxgkfxpgservice.findById(id);
		model.addAttribute("entity", entity);
		return "fxgk/fxpg/jha/view";
	}
	
	
	/**+++++++++++++++++++++++++++++++++++++风险评估++++++++++++++++++++++++++++++++++++++++++****/
	
	
	/**--------------------------------------风险活动---------------------------------------------***/
	
	/**
	 * 修改安全活动页面跳转(未插入数据库)
	 * 
	 * @param model
	 */
	@RequiresPermissions("fxpg:jha:add")
	@RequestMapping(value = "actionupdate", method = RequestMethod.GET)
	public String updateAction(Model model,HttpServletRequest request) {
		String data=request.getParameter("data");
		model.addAttribute("action", "update");
		model.addAttribute("time", request.getParameter("time"));
		model.addAttribute("entity", JsonMapper.getInstance().fromJson(data, FXGK_RiskAction.class));
		return "fxgk/fxpg/jha/actionform";
	}
	
	/**
	 * 查看风险活动页面list
	 * 
	 * @param model
	 */
	@RequiresPermissions("fxpg:jha:add")
	@RequestMapping(value = "actionlist/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String viewAction(@PathVariable("id")long id) {
		List<FXGK_RiskAction> list=fxgkfxpgjhaactionservice.findAllById1(id);
		return JsonMapper.getInstance().toJson(list);
	}
	
	/**
	 * 添加安全活动页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("fxpg:jha:add")
	@RequestMapping(value = "actioncreate", method = RequestMethod.GET)
	public String createAction(Model model) {
		model.addAttribute("action", "createact");
		return "fxgk/fxpg/jha/actionform";
	}
	
	/**
	 * 修改安全活动页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("fxpg:jha:add")
	@RequestMapping(value = "updateaction/{id}", method = RequestMethod.GET)
	public String updateaction(@PathVariable("id")long id,Model model,HttpServletRequest request) {
		FXGK_RiskAction entity= fxgkfxpgjhaactionservice.findById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("action", "updateact");
		return "fxgk/fxpg/jha/actionform";
	}
	
	
	/**
	 * 增加风险活动信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("fxpg:jha:update")
	@RequestMapping(value = "createact")
	@ResponseBody
	public String createAct(FXGK_RiskAction entity, Model model) {
		String datasuccess = "success";
		fxgkfxpgjhaactionservice.addInfo(entity);
		if("极度危险".equals(entity.getRisklevel())||"高度危险".equals(entity.getRisklevel())){
			FXGK_RiskAssessment assessment=fxgkfxpgservice.findById(entity.getID1());
			addMajorRisk(entity, assessment);
		}
		// 返回结果
		return datasuccess;
	}
	/**
	 * 修改风险活动信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("fxpg:jha:update")
	@RequestMapping(value = "updateact")
	@ResponseBody
	public String updateAct(FXGK_RiskAction entity, Model model) {
		String datasuccess = "success";
		FXGK_RiskAction old = fxgkfxpgjhaactionservice.findById(entity.getID());
		if (("极度危险".equals(entity.getRisklevel())&& !"极度危险".equals(old.getRisklevel()))||("高度危险".equals(entity.getRisklevel())&& !"高度危险".equals(old.getRisklevel()))) {
			// 增加重大风险
			FXGK_RiskAssessment assessment = fxgkfxpgservice.findById(entity.getID1());
			addMajorRisk(entity, assessment);
		} else if ((!"极度危险".equals(entity.getRisklevel())&& "极度危险".equals(old.getRisklevel()))||(!"高度危险".equals(entity.getRisklevel())&& "高度危险".equals(old.getRisklevel()))) {
			// 删除重大风险
			fxgkfxpgmajorriskservice.deletinfo(entity.getID());
		}
		fxgkfxpgjhaactionservice.updateInfo(entity);
		// 返回结果
		return datasuccess;
	}
	
	
	/**
	 * 删除风险活动信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("fxpg:jha:delete")
	@RequestMapping(value = "deleteact/{id}")
	@ResponseBody
	public String deleteAct(@PathVariable("id") long id) {
		String datasuccess = "删除成功";
		fxgkfxpgjhaactionservice.deleteInfo(id);
		fxgkfxpgmajorriskservice.deletinfo(id);
		return datasuccess;
	}
	
	
	
	
	/**--------------------------------------风险活动---------------------------------------------***/

	
	public void addMajorRisk(FXGK_RiskAction action,FXGK_RiskAssessment e){
		FXGK_MajorRisk risk=new FXGK_MajorRisk();
		risk.setId1(action.getID());
		risk.setDeptid(e.getDeptid());
		risk.setQyid(UserUtil.getCurrentShiroUser().getQyid());
		risk.setName(action.getWorkstep());
		risk.setAnalysistime(e.getAnalysistime());
		risk.setAnalysisper(e.getAnalysisper());
		risk.setAuditor(e.getAuditor());
		risk.setReviewer(e.getReviewer());
		risk.setRisklevel(action.getRisklevel());
		risk.setRiskvalue((float)action.getRiskvalue());
		risk.setImprovemeasure(action.getImprovemeasure());
		fxgkfxpgmajorriskservice.addInfo(risk);
	}
	
	/**
	 * 导出word
	 * 
	 */
	@RequiresPermissions("fxpg:jha:export")
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String exportWord(@PathVariable("id") String ids,HttpServletRequest request, HttpServletResponse response) {
		String[] arrids = ids.split(",");
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> remap = new HashMap<String, Object>();
		for (int i = 0; i < arrids.length; i++) {
			Map<String, Object> map = fxgkfxpgservice.getExportWord(Long.parseLong(arrids[i]));
			list.add(map);
		}
		remap.put("list", list);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(remap, "fxpg_jha.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	//导入页面跳转
	@RequestMapping(value = "exinjump", method = RequestMethod.GET)
	public String exin(Model model) {
		model.addAttribute("action", "exin");
		return "common/importForm";
	}
	
	
	/**
	 * 导入
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequiresPermissions("fxpg:jha:exin")
	@RequestMapping(value = "exin/{id}")
	@ResponseBody
	public String expiExcel(@PathVariable("id")long id,HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			map.put("id1", id);
			resultmap = fxgkfxpgservice.exinExcel(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}

}
