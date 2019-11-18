package com.cczu.model.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cczu.sys.comm.mapper.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.YhpcXjdztService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 巡检点状态
 * @author zpc
 * @date 2017/08/24
 */
@Controller
@RequestMapping("yhpc/xjdzt")
public class PageYhpcXjdztController extends BaseController{

	@Autowired
	private YhpcXjdztService yhpcXjdztService;
	@Autowired
	private BisQyjbxxServiceImpl bisQyjbxxService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		if("1".equals(sessionuser.getUsertype())){//企业
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "yhpc/tjfx/index";
			} 
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("qyid", sessionuser.getQyid());
			List<Map<String,Object>> list = yhpcXjdztService.dataGrid(map);
			model=returnModel(list,model);
			return "yhpc/xjdzt/qyindex";
		}else{
			return "../error/403";
		}	
	}
	
	public Model returnModel(List<Map<String,Object>> list,Model model){
		//避免jstl表达式多次循环判断风险点等级
		List<Map<String,Object>> listr=new ArrayList<>();//红
		List<Map<String,Object>> listo=new ArrayList<>();//橙	
		List<Map<String,Object>> listy=new ArrayList<>();//黄
		List<Map<String,Object>> listb=new ArrayList<>();//蓝
		List<Map<String,Object>> listd=new ArrayList<>();//自定义
		//{红/橙/黄/蓝/自定义{正常，异常，未巡检},所有检查点{正常，异常，未巡检}}
		int tj[][]= {{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0,list.size()}};
		for(Map<String,Object> m:list){
			if("1".equals(m.get("yanse")+"")){
				listr.add(returnmap(m));
				if("0".equals(m.get("checkresult"))){
					tj[0][0]++;
					tj[5][0]++;
				}
				else if("1".equals(m.get("checkresult"))){
					tj[0][1]++;
					tj[5][1]++;
				}
				else{
					tj[0][2]++;
					tj[5][2]++;
				}
			}else if("2".equals(m.get("yanse")+"")){
				listo.add(returnmap(m));
				if("0".equals(m.get("checkresult"))){
					tj[1][0]++;
					tj[5][0]++;					
				}
				else if("1".equals(m.get("checkresult"))){
					tj[1][1]++;
					tj[5][1]++;					
				}
				else {
					tj[1][2]++;
					tj[5][2]++;					
				}
			}else if("3".equals(m.get("yanse")+"")){
				listy.add(returnmap(m));
				if("0".equals(m.get("checkresult"))){
					tj[2][0]++;
					tj[5][0]++;					
				}
				else if("1".equals(m.get("checkresult"))){
					tj[2][1]++;
					tj[5][1]++;					
				}
				else {
					tj[2][2]++;
					tj[5][2]++;					
				}
			}else if("4".equals(m.get("yanse")+"")){
				listb.add(returnmap(m));
				if("0".equals(m.get("checkresult"))){
					tj[3][0]++;
					tj[5][0]++;					
				}
				else if("1".equals(m.get("checkresult"))){
					tj[3][1]++;
					tj[5][1]++;					
				}
				else {
					tj[3][2]++;
					tj[5][2]++;					
				}
			}else if("5".equals(m.get("yanse")+"")){
				listd.add(returnmap(m));
				if("0".equals(m.get("checkresult"))){
					tj[4][0]++;
					tj[5][0]++;					
				}
				else if("1".equals(m.get("checkresult"))){
					tj[4][1]++;
					tj[5][1]++;					
				}
				else {
					tj[4][2]++;
					tj[5][2]++;					
				}
			}
		}
		model.addAttribute("listr",listr);
		model.addAttribute("listo",listo);
		model.addAttribute("listy",listy);
		model.addAttribute("listb",listb);
		model.addAttribute("listd",listd);
		model.addAttribute("tj",tj);
		return model;
		
	}
	
	public Map<String,Object> returnmap(Map<String,Object> m){
		Map<String,Object> map = new HashMap<>();
		map.put("qyid", m.get("qyid").toString());
		map.put("checkpointtype", m.get("jcdtype").toString());
		map.put("checkpointid", m.get("jcdid").toString());
		List<Map<String,Object>> list = yhpcXjdztService.dataXjGrid(map);
		m.put("bclist", list);
		return m;
	}
	
	/**
	 * 统计页面跳转
	 */
	@RequestMapping(value="index2")
	public String index2(Model model,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("qyid",request.getParameter("qyid"));
//		map.put("starttime",request.getParameter("starttime"));
//		map.put("finishtime",request.getParameter("finishtime"));
		List<Map<String,Object>> list = yhpcXjdztService.dataGrid(map);
		model=returnModel(list, model);
		model.addAttribute("qyid",request.getParameter("qyid"));
		return "yhpc/xjdzt/ajindex";
	}
	
	@RequestMapping(value="list2")
	public String getlist2(Model model,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("jcdname",request.getParameter("yhpc_xjdzt_jcdname"));
		map.put("checkresult",request.getParameter("yhpc_xjdzt_checkresult"));
		map.put("qyid",request.getParameter("qyid"));
		List<Map<String,Object>> list = yhpcXjdztService.dataGrid(map);
		model.addAttribute("xjdztlist",list);
		model.addAttribute("qyid",request.getParameter("qyid"));
		return "yhpc/xjdzt/ajindex";
	}
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="xjjl")
	public String xjjl(Model model,HttpServletRequest request) {
		model.addAttribute("jcdid",request.getParameter("jcdid"));
		model.addAttribute("jcdtype",request.getParameter("jcdtype"));
		return "yhpc/xjdzt/xjindex";
	}

	/**
	 * 根据风险点ID查询信息
	 */
	@RequestMapping(value="getInfo/{fxdid}")
	@ResponseBody
	public String getInfoByFxdid(@PathVariable("fxdid") Long fxdid, HttpServletRequest request) {
		Map<String, Object> map = getAuthorityMap();
		map.put("jcdid", fxdid);
		return JsonMapper.toJsonString(yhpcXjdztService.dataGrid(map));
	}
}
