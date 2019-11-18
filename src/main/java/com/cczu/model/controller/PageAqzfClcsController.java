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
import com.cczu.model.entity.AQZF_TreatmentEntity;
import com.cczu.model.entity.AQZF_WfxwEntity;
import com.cczu.model.service.AqzfClcsService;
import com.cczu.model.service.AqzfJcfaService;
import com.cczu.model.service.AqzfJcjlService;
import com.cczu.model.service.AqzfJcnrService;
import com.cczu.model.service.AqzfSetBasicInfoService;
import com.cczu.model.service.AqzfWfxwService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;


/**
 * 处理措施Controller
 * @author YZH
 */
@Controller
@RequestMapping("aqzf/clcs")
public class PageAqzfClcsController extends BaseController {

	@Autowired
	private AqzfJcfaService aqzfJcfaService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private AqzfClcsService aqzfClcsService;
	@Autowired
	private AqzfSetBasicInfoService aqzfsetbasicinfoservice;
	@Autowired
	private AqzfJcjlService aqzfJcjlService;
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
		return "aqzf/clcs/index";
	}
	
	/**
	 * list页面 
	 * @param request
	 */
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
		map.put("qyname", request.getParameter("aqzf_clcs_qyname"));
		map.put("M1", request.getParameter("aqzf_clcs_M1"));
		return aqzfClcsService.dataGrid(map);	
		
	}

//	/**
//	 * 添加页面跳转(直接添加暂时弃用)
//	 * @param model
//	 */
//	@RequestMapping(value = "create" , method = RequestMethod.GET)
//	public String create(Model model) {
//		AQZF_SetBasicInfoEntity sbe=aqzfsetbasicinfoservice.findInfor();
//		model.addAttribute("gov", sbe.getGov()==null?"":sbe.getGov());
//		model.addAttribute("court", sbe.getCourt()==null?"":sbe.getCourt());
//		model.addAttribute("action", "create");
//		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
//		return "aqzf/clcs/form";
//	}
	
	/**
	 * 检查记录页面添加现场处理页面跳转
	 * @param model
	 * id 检查记录id
	 */
	@RequestMapping(value = "create2/{id}" , method = RequestMethod.GET)
	public String create2(@PathVariable("id") Long id,Model model) {
		AQZF_TreatmentEntity clcs = new AQZF_TreatmentEntity();
		clcs.setID3(id);
		//设置政府法院
		AQZF_SetBasicInfoEntity sbe=aqzfsetbasicinfoservice.findInfor();
		clcs.setM5(sbe.getGov()==null?"":sbe.getGov());
		clcs.setM6(sbe.getCourt()==null?"":sbe.getCourt());
		//根据检查记录id查找检查记录
		AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(id);
		clcs.setID2(jcjl.getID2());//设置企业id
		clcs.setM9(jcjl.getM2());//负责人
		clcs.setM1(DateUtils.getSysTimestamp());
		
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
		
		
		model.addAttribute("clcs", clcs);
		model.addAttribute("action", "create");
		
		return "aqzf/clcs/form2";
	}
	
	/**
	 * 添加处理措施信息
	 * @param request,model
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQZF_TreatmentEntity entity, Model model) {
		String datasuccess="success";
		//获取文书编号
		AQZF_SetBasicInfoEntity bh =aqzfsetbasicinfoservice.findInfor();
		AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(entity.getID3());
		Calendar a=Calendar.getInstance();
		String year=a.get(Calendar.YEAR)+"";
		String m0 = jcjl.getM0();
		entity.setM0("（"+bh.getSsqjc()+"）安监现决〔"+year+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		
		Timestamp t=DateUtils.getSysTimestamp();
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setID1(Long.valueOf(sessionuser.getId()));
		aqzfClcsService.addInfo(entity);
		return datasuccess;
		
	}	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqzf:clcs:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的处理措施信息
		AQZF_TreatmentEntity clcs = aqzfClcsService.findById(id);
		model.addAttribute("clcs", clcs);
		//存在问题
		List<AQZF_WfxwEntity> wfxwlist = new ArrayList<>();
		List<AQZF_SafetyCheckContentEntity> list = aqzfJcnrService.findByJlid(clcs.getID3());
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
		return "aqzf/clcs/form2";
	}
	
	/**
	 * 修改处理措施信息
	 * @param request,model
	 */
	@RequiresPermissions("aqzf:clcs:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQZF_TreatmentEntity entity,  Model model){
		String datasuccess="success";	
		entity.setS1(new java.sql.Timestamp(new java.util.Date().getTime()));
		entity.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		entity.setS3(0);
		aqzfClcsService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除处理措施信息
	 */
	@RequiresPermissions("aqzf:clcs:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqzfClcsService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("aqzf:clcs:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String, Object> clcs = aqzfClcsService.findInforById(id);
		//事故
		String sgyh = "";
		if(clcs.get("m2")!=null&&!StringUtils.isEmpty(clcs.get("m2").toString())){
			String[] wfxwids = clcs.get("m2").toString().split(",");
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
		model.addAttribute("sgyh", sgyh);
		model.addAttribute("clcs", clcs);
		return "aqzf/clcs/view";
	}

	/**
	 * 导出处理措施word
	 * 
	 */
	@RequiresPermissions("aqzf:clcs:exportword")
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = aqzfClcsService.getAjWord(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "clcsjds.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
