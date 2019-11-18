package com.cczu.model.xfssgl.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.model.xfssgl.service.XfssglSbfbService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.BarrioService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 消防设施管理-设备分布controller
 * @author wbth
 * @date 2018年4月23日
 */
@Controller
@RequestMapping("xfssgl/sbfb")
public class PageSbfbController extends BaseController{
	@Autowired
	private BarrioService barrioservice;
	@Autowired
	private XfssglSbfbService xfssglSbfbService;
	@Autowired
	private BisQyjbxxServiceImpl bisQyjbxxService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> mapData=new HashMap<String, Object>();
		//根据企业名称查询
		String qyname=request.getParameter("qyname");
		if(qyname!=null){
			mapData.put("qyname", qyname);
		}			
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1){//判断是否为集团公司
					mapData.put("fid", sessionuser.getQyid());
					//获取企业名称list
					List<Map<String, Object>> list=xfssglSbfbService.findQyList(mapData);
					model.addAttribute("qylist", list);
					model.addAttribute("qyname", qyname);
					return "xfssgl/sbfb/index";
				}
				else{
					long qyid = UserUtil.getCurrentShiroUser().getQyid();
					//企业平面图
					BIS_EnterpriseEntity bis = bisQyjbxxService.findInfoById(qyid);
					model.addAttribute("bis", bis);
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("qyid", qyid);
					//获取该企业相关信息
					List<Map<String,Object>> list = xfssglSbfbService.getAllByQyid(map);
					model.addAttribute("xfsslist", JsonMapper.getInstance().toJson(list));
					model.addAttribute("qyid", qyid);
				    return "xfssgl/sbfb/mapindex";
			    }
			}
		}	
		return "xfssgl/sbfb/mapindex";
	}
	
	/**
	 * 查看企业消防设施分布页面跳转
	 * @param qyid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="showfb/{qyid}")
	public String showfb(@PathVariable Long qyid, Model model) {
		//企业平面图
		BIS_EnterpriseEntity bis = bisQyjbxxService.findInfoById(qyid);
		model.addAttribute("bis", bis);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("qyid", qyid);
		//获取该企业相关信息
		List<Map<String,Object>> list = xfssglSbfbService.getAllByQyid(map);
		model.addAttribute("xfsslist", JsonMapper.getInstance().toJson(list));
		model.addAttribute("qyid", qyid);
		return "xfssgl/sbfb/mapindex";
	}
	
	/**
	 * 按设施名称查询返回结果
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="xfsslist/{qyid}")
	@ResponseBody
	public Map<String, Object> getFxdslist(@PathVariable Long qyid,HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String,Object> mapdate = new HashMap<>();
		Map<String,Object> map = new HashMap<>();
		//设施名称
		String xfssname = request.getParameter("keyword");
		map.put("xfssname", xfssname);
		map.put("qyid", qyid);
		List<Map<String,Object>> list = xfssglSbfbService.getAllByQyid(map);
		mapdate.put("xfsslist", JsonMapper.getInstance().toJson(list));
//		//企业平面图
//		BIS_EnterpriseEntity bis = bisQyjbxxService.findInfoById(qyid);
//		mapdate.put("pmt", bis.getM33_3());
		return mapdate;
	}
	
	/**
	 * 下载平面图
	 */
	@RequestMapping(value="xzpmt/{qyid}")
	@ResponseBody
	public void xzpmt(@PathVariable("qyid") Long qyid, HttpServletRequest request, HttpServletResponse response) {
		//获取项目部署的物理路径
		String filePath = request.getSession().getServletContext().getRealPath("/");
		try {
			xfssglSbfbService.xzPmtByQyid(qyid,filePath,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 企业list页面
	 * @param request
	 */
	@RequestMapping(value="qylist")
	@ResponseBody
	public  List<Map<String,Object>> getQyList(HttpServletRequest request) {
		return xfssglSbfbService.findQyList(getAuthorityMap());
	}

}
