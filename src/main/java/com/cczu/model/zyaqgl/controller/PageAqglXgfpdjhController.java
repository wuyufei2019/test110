package com.cczu.model.zyaqgl.controller;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.zyaqgl.entity.AQGL_RelevantEvaluation;
import com.cczu.model.zyaqgl.entity.AQGL_RelevantEvaluation_content;
import com.cczu.model.zyaqgl.service.AqglXgfpdjhService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全管理-相关方评定计划Controller
 * @author YZH
 */
@Controller
@RequestMapping("zyaqgl/xgfpdjh")
public class PageAqglXgfpdjhController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private AqglXgfpdjhService aqglxgfpdjhService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		model.addAttribute("qyid", request.getParameter("qyid"));
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		return "zyaqgl/xggl/xgfpdjh/index";
	}
	
	/**
	 * 获取相关方单位下拉框list
	 * xgfdwjson  {"id":11,"text":"相关方单位名称"}
	 * return String
	 */
	@RequestMapping(value="xgfdwjson")
	@ResponseBody
	public String codeIdjson() {
		Map<String, Object> map = new HashMap<String, Object>();
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}
		return aqglxgfpdjhService.getXgfdwjson(map);
	}
	
	/**
	 * 数据list 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xgfname", request.getParameter("zyaqgl_xgfpdjh_xgfname"));//相关方名称
		map.put("m5", request.getParameter("zyaqgl_xgfpdjh_m5"));//评定等级
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			map.put("qyid",(UserUtil.getCurrentShiroUser().getQyid()));
		}

		//安监条件
		String qyid = request.getParameter("qyid");
		if(StringUtils.isNotEmpty(qyid))
			map.put("qyid", qyid);
		return aqglxgfpdjhService.dataGrid(map);	
		
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		model.addAttribute("action", "create");
		model.addAttribute("dwid", request.getParameter("dwid"));
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "zyaqgl/xggl/xgfpdjh/form";
	}
	
	/**
	 * 添加相关方评定信息
	 * @param request,model
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQGL_RelevantEvaluation entity, Model model, HttpServletRequest request) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		//企业id
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			entity.setID1(UserUtil.getCurrentShiroUser().getQyid());
		}
		//根据评定人员id获得要评定的次数，以此来判断评定状态
		if(!StringUtils.isEmpty(request.getParameter("pdryids"))){
			String[] aids=request.getParameter("pdryids").split(",");
			entity.setM12(aids.length);
		}
		//添加相关方评定计划
		aqglxgfpdjhService.addInfo(entity);
		
		//初始化评定内容
		if(!StringUtils.isEmpty(request.getParameter("pdryids"))){
			String[] aids = request.getParameter("pdryids").split(",");
			for(int i=0;i<aids.length;i++){
				AQGL_RelevantEvaluation_content y2 = new AQGL_RelevantEvaluation_content();
				y2.setS1(t);
				y2.setS2(t);
				y2.setS3(0);
				y2.setID1(entity.getID());
				y2.setID2(Long.parseLong(aids[i]));
				aqglxgfpdjhService.addpdnrInfo(y2);
			}
		}
		// 返回结果
		return datasuccess;
	}
	
	/**
	 * 评定人员选择页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "pdrychoose")
	public String pdrychoosechoose(Model model) {
		model.addAttribute("action", "pdrychoose");
		return "zyaqgl/xggl/xgfpdjh/index_pdrychoose";
	}
	
	/**
	 * 评定记录页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "pdnr")
	public String pdjl(Model model,HttpServletRequest request) {
		String usertype = UserUtil.getCurrentShiroUser().getUsertype();
		model.addAttribute("usertype", usertype);
		model.addAttribute("id1", request.getParameter("id1"));
		return "zyaqgl/xggl/xgfpdjh/pdindex";
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{type}/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("type") String type,@PathVariable("id") Long id, Model model) {
		//查询选择的相关方评定信息
		Map<String, Object> xgfpdjh = aqglxgfpdjhService.findInforById(id);
		model.addAttribute("xgfpdjh", xgfpdjh);
		//评定人员传值
		String idname = "";
		List<Map<String,Object>> list1 = aqglxgfpdjhService.getidname(Long.parseLong(xgfpdjh.get("id").toString()));
		for (Map<String, Object> map1 : list1) {
			idname = idname + map1.get("ID") + "||" + map1.get("NAME") + "||" + map1.get("GENDER") + "||" + map1.get("PHONE") + ",";
		}
		model.addAttribute("idname", idname);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("type", type);
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "zyaqgl/xggl/xgfpdjh/form";
	}
	
	/**
	 * 修改相关方评定信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQGL_RelevantEvaluation entity,  Model model, HttpServletRequest request){
		String datasuccess="success";	
		entity.setS1(new java.sql.Timestamp(new java.util.Date().getTime()));
		entity.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		entity.setS3(0);
		//添加或删除评定内容
		if(!StringUtils.isEmpty(request.getParameter("pdryids"))){
			String pdryids=request.getParameter("pdryids");
			String pdryids2=request.getParameter("pdryids2");
			//如果评定人员变动，重新计算平均分和等级
			if(!pdryids.equals(pdryids2)){
			String[] aids = request.getParameter("pdryids").split(",");//重选后的评定人员
			String[] aids2 = request.getParameter("pdryids2").split(",");//原先评定人员
			
			//添加新增id（不在原先评定人员中的id）
			for(int i=0;i<aids.length;i++){
				List<String> list=Arrays.asList(aids2);
				if(!list.contains(aids[i])){//如果不包含则为新增id
					AQGL_RelevantEvaluation_content y2 = new AQGL_RelevantEvaluation_content();
					Timestamp t=DateUtils.getSysTimestamp();
					y2.setS1(t);
					y2.setS2(t);
					y2.setS3(0);
					y2.setID1(entity.getID());
					y2.setID2(Long.parseLong(aids[i]));
					aqglxgfpdjhService.addpdnrInfo(y2);
				}
			}
			//删除不存在id（原先ID不在重选后评定人员中的id）
			for(int i=0;i<aids2.length;i++){
				List<String> list=Arrays.asList(aids);
				if(!list.contains(aids2[i])){//如果不包含则为要删除内容
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("ID1", entity.getID());
					map.put("ID2", Long.parseLong(aids2[i]));
					aqglxgfpdjhService.deletepdnrInfo(map);//根据id1,ID2删除评定内容表
				}
			}
			//修改评定计划平均分,评定等级
			Map<String, Object> map=aqglxgfpdjhService.findcount(entity.getID());
			if(map.get("cj")!=null){
				Integer count=Integer.parseInt(map.get("cj").toString());
				String level="";
				if(count>=80){
					level="优秀";
				}else if(count>=70&&count<80){
					level="良好";
				}else if(count>=60&&count<70){
					level="合格";
				}else{
					level="不合格";
				}
				entity.setM4(Integer.parseInt(map.get("cj").toString()));
				entity.setM5(level);
			  }else{
					entity.setM4(null);
					entity.setM5(null);
			  }
			}
		}
		String role=request.getParameter("role");
		if(!StringUtils.isEmpty(role)){
			if(role.equals("2")){
				//审核
				entity.setM7(UserUtil.getCurrentUser().getName());//审核人
				Timestamp t=DateUtils.getSysTimestamp();
				entity.setM8(t);//审核日期
			}else if(role.equals("3")){
				//批准
				entity.setM10(UserUtil.getCurrentUser().getName());//批准人
				Timestamp t=DateUtils.getSysTimestamp();
				entity.setM11(t);//批准日期
			}
		}
		aqglxgfpdjhService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除相关方评定信息
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqglxgfpdjhService.deleteInfo(Long.parseLong(arrids[i]));
			aqglxgfpdjhService.deletepdnrInfo(Long.parseLong(arrids[i]));//根据计划id删除评定内容表
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String, Object> xgfpdjh = aqglxgfpdjhService.findInforById(id);
		model.addAttribute("xgfpdjh", xgfpdjh);
		//评定人员传值
		String idname = "";
		List<Map<String,Object>> list1 = aqglxgfpdjhService.getidname(Long.parseLong(xgfpdjh.get("id").toString()));
		for (Map<String, Object> map1 : list1) {
			idname = idname + map1.get("ID") + "||" + map1.get("NAME") + "||" + map1.get("GENDER") + "||" + map1.get("PHONE") + ",";
		}
		model.addAttribute("idname", idname);
		//返回页面
		model.addAttribute("action", "view");
		return "zyaqgl/xggl/xgfpdjh/view";
	}
	
}
