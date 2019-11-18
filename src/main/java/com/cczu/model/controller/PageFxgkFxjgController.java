package com.cczu.model.controller;

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

import com.cczu.model.service.FxgkFxjgService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 风险管控_风险监管Controller
 * @author zpc
 * @date 2017年8月10日
 */
@Controller
@RequestMapping("fxgk/fxjg")
public class PageFxgkFxjgController extends BaseController{

	@Autowired
	private FxgkFxjgService fxgkfxjgservice;
	/**
	 * 查询企业红橙黄蓝数量 list
	 * 
	 */
	@RequestMapping(value = "index")
	public String risklist(Model model,HttpServletRequest request) {
		if(request.getParameter("sys")!=null &&"1".equals(request.getParameter("sys"))){
			model.addAttribute("sys", "sys");
			if(request.getParameter("mflag")!=null &&(request.getParameter("mflag"))!="")
				model.addAttribute("fxdj", request.getParameter("mflag"));
		}
		return "fxgk/fxdjg/index";
	}
	
	/**
	 * 查看平面图
	 */
	@RequestMapping(value="pmt/{qyid}")
	public String pmt(@PathVariable("qyid") Long qyid, Model model) {
		List<Map<String, Object>> list=fxgkfxjgservice.findPmtByQyid(qyid);
		Map<String, Object> map=list.get(0);
		if(map.get("pmt")!=null){
			String pmturl[]=map.get("pmt").toString().split("\\|");
			model.addAttribute("pmturl", pmturl[0]);
		}else{
			model.addAttribute("pmturl", "");
		}
		model.addAttribute("pmt", JsonMapper.getInstance().toJson(list));
		return "fxgk/fxdjg/pmtview";
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
			fxgkfxjgservice.xzPmtByQyid(qyid,filePath,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 平面图json
	 */
	@RequestMapping(value="pmtjson")
	@ResponseBody
	public String pmt(Model model,HttpServletRequest request) {
		long qyid = Long.parseLong(request.getParameter("qyid"));
		List<Map<String, Object>> list=fxgkfxjgservice.findPmtByQyid(qyid);
		return JsonMapper.getInstance().toJson(list);
	}
	
	@RequestMapping(value = "riskPageList")
	@ResponseBody
	public Map<String, Object> riskPageList(HttpServletRequest request, HttpServletResponse response) {
	
		Map<String, Object> map = getPageMap(request);
		//TODO --分页查询企业list，列中要包含各企业红橙黄蓝风险点的数量
		map.put("qyname", request.getParameter("fxgk_fxjg_qyname"));
		map.put("xiangzhen", request.getParameter("xiangzhen"));//乡镇
		map.put("hasfx",request.getParameter("fxgk_fxjg_hasfx"));//有无风险点
		map.put("fxdj",request.getParameter("fxgk_fxjg_fxdj"));//风险等级
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0){
			map.put("jglx",sessionuser.getUserroleflg());
		}
		return fxgkfxjgservice.dateGrid(map);
	}
	@RequestMapping(value = "gkdc/{id}")
	public String gkdcIndex(@PathVariable long id,Model model) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		//TODO --分页查询企业list，列中要包含各企业红橙黄蓝风险点的数量
		map.put("qyid", id);
		model.addAttribute("sglx",fxgkfxjgservice.getSgtypeByQyid(map));
		map.put("pageSize", 1);
		map.put("pageNo", 1);
		model.addAttribute("row", fxgkfxjgservice.getQyData(map));
		return "fxgk/fxdjg/view";
	}
	

}
