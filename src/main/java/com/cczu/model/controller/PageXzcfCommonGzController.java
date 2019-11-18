package com.cczu.model.controller;

import java.io.IOException;
import java.sql.Timestamp;
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

import com.cczu.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.model.entity.XZCF_GzsInfoEntity;
import com.cczu.model.entity.XZCF_YbcfDcbgEntity;
import com.cczu.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.model.service.AqzfSetBasicInfoService;
import com.cczu.model.service.AqzfSetNumberService;
import com.cczu.model.service.AqzfWfxwService;
import com.cczu.model.service.IMsgService;
import com.cczu.model.service.IPunishSimpleGzService;
import com.cczu.model.service.IXzcfCommonLaspService;
import com.cczu.model.service.XzcfCommonDcService;
import com.cczu.model.service.XzcfDCzjService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 行政处罚--告知controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("xzcf/ybcf/cfgz")
public class PageXzcfCommonGzController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private IMsgService msgService;
	@Autowired
	private IPunishSimpleGzService punishsimplegzservice;
	@Autowired
	private IXzcfCommonLaspService xzcfcommonlaspservice;
	@Autowired
	private AqzfSetNumberService setNumberService;
	@Autowired
	private AqzfSetBasicInfoService aqzfsetbasicinfoservice;
	@Autowired
	private AqzfWfxwService aqzfWfxwService;
	@Autowired
	private XzcfCommonDcService xzcfcommondcservice;
	@Autowired
	private XzcfDCzjService xzcfDCzjService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/cfgz/index";
	}

	/**
	 * list页面
	 */
	@RequiresPermissions("ybcf:cfgz:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("number", request.getParameter("ybcf_cfgz_number"));
		map.put("name", request.getParameter("ybcf_cfgz_name"));
		map.put("startdate", request.getParameter("ybcf_cfgz_startdate"));
		map.put("enddate", request.getParameter("ybcf_cfgz_enddate"));
		map.put("cftype", "1");
		map.put("xzqy",UserUtil.getCurrentShiroUser().getXzqy());
		return punishsimplegzservice.dataGrid(map);
	}
	
	/**
	 * 首页提醒
	 * 
	 * @param request
	 */
	//@RequestMapping(value = "html")
	//@ResponseBody
//	public Map<String, Object>  getHtml(HttpServletRequest request, Model model) {
//		Map<String, Object> map = getPageMap(request);
//		Map<String, Object> remap = new HashMap<>();//返回值
//		ShiroUser user= UserUtil.getCurrentShiroUser();
//		map.put("year", DateUtils.getYear());
//		map.put("month", Integer.parseInt(DateUtils.getMonth()));
//		if(user.getUsertype().equals("1")){//0是企业，1为安监局
//		map.put("id", user.getQyid());
//		}
//		remap.put("rows",aqjgcheckplanservice.dataGrid(map).get("rows") );
//		remap.put("usertype",UserUtil.getCurrentShiroUser().getUsertype());
//		return remap;
//	}
	

	/**
	 * 添加告知信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:cfgz:add")
	@RequestMapping(value = "create/{id}")
	public String create(@PathVariable("id") long id,Model model) {
		XZCF_GzsInfoEntity jie = new XZCF_GzsInfoEntity();
		XZCF_YbcfDcbgEntity dcbg = xzcfcommondcservice.findInfoByLaId(id);
		jie.setId1(id);
		jie.setName(dcbg.getQyname());
		jie.setUnlaw(dcbg.getUnlaw());
		jie.setIllegalact(dcbg.getResearchresult());
		jie.setEnlaw(dcbg.getEnlaw());
		jie.setPunishresult(dcbg.getPunishresult());
		//添加证据
		List<Map<String, Object>> czwt= xzcfDCzjService.dataGridCzwt(dcbg.getID());
	    String evidence = "";
	    if(czwt.size()>0){
			int i = 1;
			for (Map<String, Object> map2 : czwt) {
				evidence += i+"."+map2.get("m1").toString()+" ";
				i++;
			}
		}
		jie.setEvidence(evidence);//证据
		model.addAttribute("jie", jie);
		
		model.addAttribute("action", "createSub");
		return "aqzf/xzcf/ybcf/cfgz/form";
	}

	/**
	 * 添加计划
	 * @param request
	 */
	@RequiresPermissions("ybcf:cfgz:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(XZCF_GzsInfoEntity jie,
			HttpServletRequest request, Model model) throws IOException {
		String datasuccess = "failed";
		Timestamp t = DateUtils.getSysTimestamp();
		jie.setS1(t);
		jie.setS2(t);
		jie.setS3(0);
		jie.setCftype("1");
		
		//设置编号
		AQZF_SetBasicInfoEntity bh =aqzfsetbasicinfoservice.findInfor();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(jie.getId1());
		String m0 = ybcf.getNumber();
		jie.setNumber("（"+bh.getSsqjc()+"）安监罚告〔"+DateUtils.getYear()+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		
		if (punishsimplegzservice.addInfoReturnID(jie) > 0) {
			XZCF_YbcfLaspEntity yle= xzcfcommonlaspservice.findInfoById(jie.getId1());
			yle.setGzflag("1");
			xzcfcommonlaspservice.updateInfo(yle);
			datasuccess = "success";
		}
		return datasuccess;
	}

	/**
	 * 修改计划信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:cfgz:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {

		XZCF_GzsInfoEntity jie = punishsimplegzservice.findInfoById(id);
		model.addAttribute("jie", jie);
		//model.addAttribute("jid", id);
		model.addAttribute("action", "updateSub");
		return "aqzf/xzcf/ybcf/cfgz/form";
	}

	/**
	 * 修改告知信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:cfgz:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(XZCF_GzsInfoEntity jie,
			HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		jie.setS2(t);
		try {
			punishsimplegzservice.updateInfo(jie);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}

	/**
	 * 查看告知信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("ybcf:cfgz:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		XZCF_GzsInfoEntity jie = punishsimplegzservice.findInfoById(id);
		model.addAttribute("jie", jie);
		return "aqzf/xzcf/ybcf/cfgz/view";
	}

	/**
	 * 删除告知信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("ybcf:cfgz:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		try {
			for (int i = 0; i < arrids.length; i++) {
				punishsimplegzservice.deleteInfo(Long.parseLong(arrids[i]));
				punishsimplegzservice.updateLaspInfo(Long.parseLong(arrids[i]));
			}
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="删除失败";
		}
		
		return datasuccess;
	}
	
	/**
	 * 获取案件下拉菜单的内容
	 * @param id,model
	 */
//	@RequestMapping(value = "casenamelist")
//	@ResponseBody
//	public String getGzCaseNameList() {
//		List<Map<String,Object>> list=punishsimplegzservice.findGzCaseNameList("");
//		
//		return JsonMapper.getInstance().toJson(list);
//	}
	
	/**
	 * 导出告知书记录word
	 * 
	 */
	@RequiresPermissions("ybcf:cfgz:export")
	@RequestMapping(value = "exportgzs/{flag}/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("flag") String  flag,@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		XZCF_GzsInfoEntity jie;
		AQZF_SetBasicInfoEntity sbe=aqzfsetbasicinfoservice.findInfor();
		if("gz".equals(flag))
		 jie= punishsimplegzservice.findInfoById(id);
		else
		jie=punishsimplegzservice.findInfoByLaId(id);
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("address", sbe.getAddress()==null?"":sbe.getAddress());
		map.put("ybcode", sbe.getYbcode()==null?"":sbe.getYbcode());
		map.put("contactname", sbe.getContact()==null?"":sbe.getContact());
		map.put("phone", sbe.getPhone()==null?"":sbe.getPhone());
		map.put("name", jie.getName());
		map.put("comorper",(jie.getName().length()>=4?"你单位":"你"));
		map.put("punishdate",DateUtils.formatDate(jie.getPunishdate(), "yyyy年MM月dd日"));
		map.put("illegalact", jie.getIllegalact()==null?"":jie.getIllegalact());
		map.put("evidence", jie.getEvidence()==null?"":jie.getEvidence());
		map.put("unlaw", jie.getUnlaw()==null?"":jie.getUnlaw());
		map.put("enlaw",jie.getEnlaw()==null?"":jie.getEnlaw());
		map.put("punishresult", jie.getPunishresult()==null?"":jie.getPunishresult());
	    map.put("number", jie.getNumber());
	  
	    map.put("ssqmc", sbe.getSsqmc());

		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "gzsrecord.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
}
