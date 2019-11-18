package com.cczu.model.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
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

import com.cczu.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.model.entity.AQZF_WfxwEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.XZCF_DczjEntity;
import com.cczu.model.entity.XZCF_YbcfDcbgEntity;
import com.cczu.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.model.service.AqzfSetBasicInfoService;
import com.cczu.model.service.AqzfSetNumberService;
import com.cczu.model.service.AqzfWfxwService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IXzcfCommonLaspService;
import com.cczu.model.service.XzcfCommonDcService;
import com.cczu.model.service.XzcfDCzjService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 行政处罚--调查报告controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("xzcf/ybcf/dcbg")
public class PageXzcfCommonDcController extends BaseController {

	@Autowired
	private XzcfCommonDcService xzcfcommondcservice;
	@Autowired
	private IXzcfCommonLaspService xzcfcommonlaspservice;
	@Autowired
	private AqzfSetNumberService setNumberService;
	@Autowired
	private IBisQyjbxxService  bisqyjbxxservice;
	@Autowired
	private AqzfWfxwService aqzfWfxwService;
	@Autowired
	private AqzfSetBasicInfoService setbasicservice;
	@Autowired
	private XzcfDCzjService xzcfDCzjService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/dcbg/index";
	}

	/**
	 * list页面
	 */
	@RequiresPermissions("xzcf:dcbg:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("name", request.getParameter("ybcf_dcbg_name"));
		map.put("qyname", request.getParameter("ybcf_dcbg_qyname"));
		map.put("startdate", request.getParameter("ybcf_dcbg_startdate"));
		map.put("enddate", request.getParameter("ybcf_dcbg_enddate"));
		map.put("xzqy",UserUtil.getCurrentShiroUser().getXzqy());
		return xzcfcommondcservice.dataGrid(map);
	}

	/**
	 * 添加调查信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:dcbg:add")
	@RequestMapping(value = "create/{id}")
	public String create(@PathVariable("id") long id,Model model) {
		XZCF_YbcfDcbgEntity ybe= new XZCF_YbcfDcbgEntity();
		XZCF_YbcfLaspEntity yle=xzcfcommonlaspservice.findInfoById(id);
		BIS_EnterpriseEntity bis= bisqyjbxxservice.findInfoById(yle.getId1());
		ybe.setId1(id);
		ybe.setCasename(yle.getCasename());
		ybe.setId2(yle.getId1());
		ybe.setQyname(bis.getM1());
		ybe.setQyaddress(bis.getM8());
		ybe.setEconomytype(bis.getM10());
		ybe.setLegalperson(bis.getM19());
		ybe.setQyfounddate(bis.getM4());
		ybe.setBusinessscope(bis.getM14());
		
		String researchresult = "";//检查时发现问题
		String unlaw = "";//违反条约
		String illactevidence = "";//违法事实
		String opinion = "";//承办人意见
		String enlaw = "";//处罚依据
		String punishresult = "";//处罚结果

		if(!StringUtils.isEmpty(yle.getCasecondition())){
			String[] wfxwids = yle.getCasecondition().split(",");
			for(int i=0;i<wfxwids.length;i++){
				try {
					AQZF_WfxwEntity wfxw = aqzfWfxwService.findById(Long.parseLong(wfxwids[i]));
					researchresult += (i+1)+"."+wfxw.getM1();
					unlaw += wfxw.getM2()+",";
					enlaw += wfxw.getM4()+",";
					punishresult += (i+1)+"."+wfxw.getM5();
				} catch (Exception e) {
					researchresult += (i+1)+"."+wfxwids[i];
				}
			}
			enlaw = enlaw.length()>0?enlaw.substring(0, enlaw.length()-1):"";
			opinion = "依据"+enlaw+"，建议进行如下处罚。";
			unlaw = unlaw.length()>0?unlaw.substring(0, unlaw.length()-1):"";
			illactevidence = "经调查认定,"+bis.getM1()+"存在如下违法事实:"+yle.getCasename()+"，该行为违反了"+unlaw+"。";
		}
		ybe.setResearchresult(researchresult);
		ybe.setUnlaw(unlaw);
		ybe.setIllactevidence(illactevidence);
		ybe.setOpinion(opinion);
		ybe.setEnlaw(enlaw);
		ybe.setPunishresult(punishresult);
		
		model.addAttribute("ybe", ybe);
		model.addAttribute("action", "createSub");
		return "aqzf/xzcf/ybcf/dcbg/form";
	}

	/**
	 * 添加调查信息
	 * @param request
	 */
	@RequiresPermissions("xzcf:dcbg:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(XZCF_YbcfDcbgEntity ybe,String czwt,String czwturl,HttpServletRequest request, Model model) throws IOException {
		String datasuccess = "failed";
		Timestamp t = DateUtils.getSysTimestamp();
		ybe.setS1(t);
		ybe.setS2(t);
		ybe.setS3(0);
		long id=xzcfcommondcservice.addInfoReturnID(ybe);
		
		if(id>0){
			//获取证据
			if(!StringUtils.isEmpty(czwt)){
				String[] czwt2=czwt.split(",");//证据
				String[] czwturl2=czwturl.split(",");//照片
				for(int i=0;i<czwt2.length;i++){
					XZCF_DczjEntity zj=new XZCF_DczjEntity();
					zj.setID1(id);//检查记录id
					zj.setM1(czwt2[i]);//证据
					zj.setM2(czwturl2[i]);//照片
					xzcfDCzjService.addInfo(zj);//添加证据
				}
			}
			
			XZCF_YbcfLaspEntity yle= xzcfcommonlaspservice.findInfoById(ybe.getId1());
			yle.setDcflag("1");
			xzcfcommonlaspservice.updateInfo(yle);
			datasuccess = "success";
		}
		return datasuccess;
	}

	/**
	 * 修改调查报告信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:dcbg:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {

		XZCF_YbcfDcbgEntity ybe = xzcfcommondcservice.findInfoById(id);
		model.addAttribute("ybe", ybe);
		//查询证据
		List<Map<String, Object>> czwt= xzcfDCzjService.dataGridCzwt(id);
		model.addAttribute("czwt", JsonMapper.getInstance().toJson(czwt));
		
		model.addAttribute("action", "updateSub");
		return "aqzf/xzcf/ybcf/dcbg/form";
	}

	/**
	 * 修改调查报告信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:dcbg:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(XZCF_YbcfDcbgEntity ybe,String czwt,String czwturl,HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		ybe.setS2(t);
		try {
			xzcfcommondcservice.updateInfo(ybe);
			
			xzcfDCzjService.deletebyId1(ybe.getID());
			//获取证据
			if(!StringUtils.isEmpty(czwt)){
				String[] czwt2=czwt.split(",");//证据
				String[] czwturl2=czwturl.split(",");//照片
				for(int i=0;i<czwt2.length;i++){
					XZCF_DczjEntity zj=new XZCF_DczjEntity();
					zj.setID1(ybe.getID());//检查记录id
					zj.setM1(czwt2[i]);//证据
					zj.setM2(czwturl2[i]);//照片
					xzcfDCzjService.addInfo(zj);//添加证据
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}

	/**
	 * 查看信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:dcbg:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		XZCF_YbcfDcbgEntity ybe = xzcfcommondcservice.findInfoById(id);
		model.addAttribute("ybe", ybe);
		//查询证据
		List<Map<String, Object>> czwt= xzcfDCzjService.dataGridCzwt(id);
		model.addAttribute("czwt", JsonMapper.getInstance().toJson(czwt));
		return "aqzf/xzcf/ybcf/dcbg/view";
	}

	/**
	 * 删除信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("xzcf:dcbg:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		try {
			for (int i = 0; i < arrids.length; i++) {
				xzcfcommondcservice.deleteInfo(Long.parseLong(arrids[i]));
				xzcfcommondcservice.updateLaspInfo(Long.parseLong(arrids[i]));
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
//		List<Map<String,Object>> list=xzcfcommondcservice.findGzCaseNameList("");
//		
//		return JsonMapper.getInstance().toJson(list);
//	}
	
	/**
	 * 导出调查报告word
	 * 
	 */
	@RequiresPermissions("xzcf:dcbg:export")
	@RequestMapping(value = "export/{flag}/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("flag") String  flag,@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		XZCF_YbcfDcbgEntity ybe;
		XZCF_YbcfLaspEntity yle;
		if("la".equals(flag)){
			yle= xzcfcommonlaspservice.findInfoById(id);
			ybe=xzcfcommondcservice.findInfoByLaId(id);
		}
		else{
			ybe= xzcfcommondcservice.findInfoById(id);
			yle=xzcfcommonlaspservice.findInfoById(ybe.getId1());
		}
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("researchresult", ybe.getResearchresult());
		map.put("researchdate", DateUtils.formatDate(ybe.getResearchdate(), "yyyy年MM月dd日"));
		map.put("qyname", ybe.getQyname());
		map.put("casename", ybe.getCasename());
		map.put("qyaddress", ybe.getQyaddress());
		map.put("economytype", ybe.getEconomytype());
		map.put("legalperson",ybe.getLegalperson());
		map.put("qyfounddate",DateUtils.formatDate(ybe.getQyfounddate(), "yyyy年MM月dd日"));
		map.put("s1",DateUtils.formatDate(ybe.getS1(), "yyyy年MM月dd日"));
		map.put("businessscope", ybe.getBusinessscope());
		map.put("xwresearch", ybe.getXwresearch());
		map.put("illactevidence", ybe.getIllactevidence());
		//map.put("opinion", ybe.getOpinion());
		map.put("unlaw", ybe.getUnlaw());
		map.put("enlaw",ybe.getEnlaw());
		map.put("punishresult", ybe.getPunishresult());
	    map.put("number", yle.getNumber());
	    
	    //证据
	    List<Map<String, Object>> czwt= xzcfDCzjService.dataGridCzwt(ybe.getID());
	    String evidence = "";
	    if(czwt.size()>0){
	    	int i = 1;
	    	for (Map<String, Object> map2 : czwt) {
	    		evidence += i+"."+map2.get("m1").toString()+" ";
	    		i++;
			}
	    }
	    map.put("getevidence", evidence);
	    
	    AQZF_SetBasicInfoEntity bh =setbasicservice.findInfor();
	    map.put("ssqmc", bh.getSsqmc());
	    
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "xzcfdcbg.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	/**
	 * 证据添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "choosenr")
	public String choosepic(Model model) {
		return "aqzf/xzcf/ybcf/dcbg/nrForm";
	}
}
