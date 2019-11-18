package com.cczu.model.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.AQZF_SafetyCheckContentEntity;
import com.cczu.model.entity.AQZF_SafetyCheckRecordEntity;
import com.cczu.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.model.entity.AQZF_ReformEntity;
import com.cczu.model.entity.AQZF_WfxwEntity;
import com.cczu.model.service.AqzfJcfaService;
import com.cczu.model.service.AqzfJcjlService;
import com.cczu.model.service.AqzfJcnrService;
import com.cczu.model.service.AqzfSetBasicInfoService;
import com.cczu.model.service.AqzfWfxwService;
import com.cczu.model.service.AqzfZlzgService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;


/**
 * 责令整改Controller
 * @author YZH
 */
@Controller
@RequestMapping("aqzf/zlzg")
public class PageAqzfZlzgController extends BaseController {

	@Autowired
	private AqzfJcjlService aqzfJcjlService;
	@Autowired
	private AqzfJcfaService aqzfJcfaService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private AqzfZlzgService aqzfZlzgService;
	@Autowired
	private AqzfSetBasicInfoService aqzfsetbasicinfoservice;
	@Autowired
	private IBisQyjbxxService bisqyjbxxservice;
	@Autowired
	private AqzfJcnrService aqzfJcnrService;
	@Autowired
	private AqzfWfxwService aqzfWfxwService;
	 
	
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/zlzg/index";
	}
	
	/**
	 * list页面 
	 * @param request
	 */
	@RequiresPermissions("aqzf:zlzg:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("0")){//安监局
			map.put("xzqy",sessionuser.getXzqy());
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}
		map.put("qyname", request.getParameter("aqzf_zlzg_qyname"));
		map.put("M3", request.getParameter("aqzf_zlzg_M3"));
		return aqzfZlzgService.dataGrid(map);	
		
	}

	
	/**
	 * 添加页面跳转
	 * @param model
	 * id 记录id
	 */
	@RequestMapping(value = "create/{id}" , method = RequestMethod.GET)
	public String create(@PathVariable("id") Long id, Model model) {
		AQZF_ReformEntity zlzg=new AQZF_ReformEntity();
		//根据检查记录id查找检查记录
		AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(id);
		
		
		//设置相关信息政府法院
		AQZF_SetBasicInfoEntity sbe=aqzfsetbasicinfoservice.findInfor();
		zlzg.setM4(sbe.getGov()==null?"":sbe.getGov());
		zlzg.setM5(sbe.getCourt()==null?"":sbe.getCourt());

		zlzg.setM8(jcjl.getM2());//负责人
		zlzg.setID2(jcjl.getID2());//企业id
		zlzg.setID1(jcjl.getID());//检查记录id
		zlzg.setM1(jcjl.getM6());//检查日期
		
		List<AQZF_WfxwEntity> wfxwlist = new ArrayList<>();
		//存在问题
		List<AQZF_SafetyCheckContentEntity> list = aqzfJcnrService.findByJlid(id);
		for (AQZF_SafetyCheckContentEntity scc : list) {
			//根据违法id查找违法行为
			AQZF_WfxwEntity wfxw = new AQZF_WfxwEntity();
		    try {
		    	wfxw = aqzfWfxwService.findById(Long.parseLong(scc.getM2()));
			} catch (Exception e) {
				wfxw.setM1(scc.getM2());
			}
			wfxwlist.add(wfxw);
		}
		model.addAttribute("wfxwlist", JsonMapper.getInstance().toJson(wfxwlist));
		
		model.addAttribute("action", "create");
		model.addAttribute("zlzg",zlzg);
		return "aqzf/zlzg/form";
	}
	
	/**
	 * 添加责令整改信息
	 * @param request,model
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQZF_ReformEntity entity, Model model) {
		String datasuccess="success";
		//添加责令整改编号
		AQZF_SetBasicInfoEntity bh =aqzfsetbasicinfoservice.findInfor();
		AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(entity.getID1());
		Calendar a=Calendar.getInstance();
		String year=a.get(Calendar.YEAR)+"";
		String m0 = jcjl.getM0();
		entity.setM0("（"+bh.getSsqjc()+"）安监责改〔"+year+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		aqzfZlzgService.addInfo(entity);
		return datasuccess;
		
	}	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqzf:zlzg:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的责令整改信息
		AQZF_ReformEntity zlzg = aqzfZlzgService.findById(id);
		model.addAttribute("zlzg", zlzg);
		//存在问题
		List<AQZF_WfxwEntity> wfxwlist = new ArrayList<>();
		List<AQZF_SafetyCheckContentEntity> list = aqzfJcnrService.findByJlid(zlzg.getID1());
		for (AQZF_SafetyCheckContentEntity scc : list) {
			//根据违法id查找违法行为
			AQZF_WfxwEntity wfxw = new AQZF_WfxwEntity();
		    try {
		    	wfxw = aqzfWfxwService.findById(Long.parseLong(scc.getM2()));
			} catch (Exception e) {
				wfxw.setM1(scc.getM2());
			}
			wfxwlist.add(wfxw);
		}
		model.addAttribute("wfxwlist", JsonMapper.getInstance().toJson(wfxwlist));
		//返回页面
		model.addAttribute("action", "update");
		
		return "aqzf/zlzg/form";
	}
	
	/**
	 * 修改责令整改信息
	 * @param request,model
	 */
	@RequiresPermissions("aqzf:zlzg:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQZF_ReformEntity entity,  Model model){
		String datasuccess="success";	
		aqzfZlzgService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除责令整改信息
	 */
	@RequiresPermissions("aqzf:zlzg:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqzfZlzgService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqzf:zlzg:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String, Object> zlzg = aqzfZlzgService.findInforById(id);
		//存在问题
		String sgyh = "";
		if(zlzg.get("m2")!=null&&!StringUtils.isEmpty(zlzg.get("m2").toString())){
			String[] wfxwids = zlzg.get("m2").toString().split(",");
			for(int i=0;i<wfxwids.length;i++){
				AQZF_WfxwEntity wfxw = new AQZF_WfxwEntity();
			    try {
			    	wfxw = aqzfWfxwService.findById(Long.parseLong(wfxwids[i]));
			    	sgyh += (i+1)+". "+wfxw.getM1()+" ";
				} catch (Exception e) {
					sgyh += (i+1)+". "+wfxwids[i]+" ";
				}
			}
		}
		model.addAttribute("czwt", sgyh);
		model.addAttribute("zlzg", zlzg);

		return "aqzf/zlzg/view";
	}

	/**
	 * 导出责令限期整改指令书word
	 * 
	 */
	@RequiresPermissions("aqzf:zlzg:exportword")
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = aqzfZlzgService.getAjWord(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "zlzgzls.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
}
