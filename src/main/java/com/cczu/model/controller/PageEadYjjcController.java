package com.cczu.model.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.ACA_FireballEntity;
import com.cczu.model.entity.ACA_GasphysicalEntity;
import com.cczu.model.entity.ACA_InstantleakageEntity;
import com.cczu.model.entity.ACA_JetFireEntity;
import com.cczu.model.entity.ACA_LeakageEntity;
import com.cczu.model.entity.ACA_PhysicalEntity;
import com.cczu.model.entity.ACA_PoolFireEntity;
import com.cczu.model.entity.ACA_VceEntity;
import com.cczu.model.service.IAcaFireballService;
import com.cczu.model.service.IAcaGasphysicalService;
import com.cczu.model.service.IAcaInstantleakageService;
import com.cczu.model.service.IAcaJetFireService;
import com.cczu.model.service.IAcaLeakageService;
import com.cczu.model.service.IAcaPhysicalService;
import com.cczu.model.service.IAcaPoolFireService;
import com.cczu.model.service.IAcaVceService;
import com.cczu.model.service.IEadYjjcService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.StringUtils;

/**
 * 事故应急决策controller
 * @author jason
 */
@Controller
@RequestMapping("ead/yjjc")
public class PageEadYjjcController extends BaseController {

	@Autowired
	private IEadYjjcService eadYjjcService;
	@Autowired
	private IAcaFireballService acaFireballService;
	@Autowired
	private IAcaGasphysicalService acaGasphysicalService;
	@Autowired
	private IAcaInstantleakageService acaInstantleakageService;
	@Autowired
	private IAcaJetFireService acaJetFireService;
	@Autowired
	private IAcaLeakageService acaLeakageService;
	@Autowired
	private IAcaPhysicalService acaPhysicalService;
	@Autowired
	private IAcaPoolFireService acaPoolFireService;
	@Autowired
	private IAcaVceService acaVceService;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "ead/yjjc/index";
	}
	
	/**
	 * 计算页面
	 */
	@RequestMapping(value="form", method = RequestMethod.GET)
	public String formpoolfire(Model model) {
		return "ead/yjjc/form";
	}
	
	/**
	 * 计算结束_事故后果页面
	 */
	@RequestMapping(value="consequence/{id}", method = RequestMethod.GET)
	public String consequence(@PathVariable("id") Long id,Model model) {
		eadYjjcService.saveDistance(id);
		model.addAttribute("consequenceid", id);
		return "ead/yjjc/consequence";
	}
	
	/**
	 * 计算结束_事故后果页面_救援路线
	 */
	@RequestMapping(value="consequencepage/{routeindex}/{id}", method = RequestMethod.GET)
	public String consequenceRouteIndex(@PathVariable("routeindex") String routeindex, @PathVariable("id") Long id, Model model) {
		model.addAttribute("consequenceid", id);
		return "ead/yjjc/"+routeindex;
	}
	
//	/**
//	 * 计算结束_事故后果页面_避难场所
//	 */
//	@RequestMapping(value="conseque/resplaceindex/{id}", method = RequestMethod.GET)
//	@ResponseBody
//	public String consequenceResPlaceIndex(@PathVariable("id") Long id,Model model) {
//		model.addAttribute("consequenceid", id);
//		return "ead/yjjc/resplaceindex";
//	}
//	
	/**
	 * 计算结束_事故后果页面_救援路线
	 */
	@RequestMapping(value="conseque/route")
	@ResponseBody
	public String consequenceRoute(HttpServletRequest request, Model model) {
		long id=Long.parseLong(request.getParameter("consequenceid"));
		Map<String,Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(request.getParameter("consequenceid")))
			map.put("id", id);
		try {
			eadYjjcService.saveDistance(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eadYjjcService.findconsequenceRoute(map);
	}
	
	/**
	 * 计算结束_事故后果页面_避难场所  地图显示
	 */
	@RequestMapping(value="conseque/resplacemap")
	@ResponseBody
	public String consequenceResPlace(HttpServletRequest request, Model model) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("bncsname", request.getParameter("yjjc_consequence_bncs_cs_name"));
		map.put("bncsdistance", request.getParameter("yjjc_consequence_bncs_cs_distance"));
		if(StringUtils.isNotEmpty(request.getParameter("consequenceid")))
			map.put("consequenceid", request.getParameter("consequenceid"));
		return eadYjjcService.findconsequenceResPlaceMap(map);
	}
	
	/**
	 * 计算结束_事故后果页面_避难场所  列表显示
	 */
	@RequestMapping(value="conseque/resplace", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> consequenceResPlaceList(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		map.put("bncsname", request.getParameter("yjjc_consequence_bncs_cs_name"));
		map.put("bncsdistance", request.getParameter("yjjc_consequence_bncs_cs_distance"));
		if(StringUtils.isNotEmpty(request.getParameter("consequenceid")))
			map.put("consequenceid", request.getParameter("consequenceid"));
		
		return eadYjjcService.findconsequenceResPlaceDataGrid(map);
	}
	
	/**
	 * 计算结束_事故后果页面_应急队伍	地图显示
	 */
	@RequestMapping(value="conseque/resteammap")
	@ResponseBody
	public String consequenceResTeam(HttpServletRequest request, Model model) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("yjdwname", request.getParameter("yjjc_consequence_yjdw_dw_name"));
		map.put("yjdwdistance", request.getParameter("yjjc_consequence_yjdw_dw_distance"));
		map.put("consequenceid", request.getParameter("consequenceid"));
		return eadYjjcService.findconsequenceResTeamMap(map);
	}
	
	/**
	 * 计算结束_事故后果页面_应急队伍  列表显示
	 */
	@RequestMapping(value="conseque/resteam", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> consequenceResTeamList(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		map.put("yjdwname", request.getParameter("yjjc_consequence_yjdw_dw_name"));
		map.put("yjdwdistance", request.getParameter("yjjc_consequence_yjdw_dw_distance"));
		map.put("consequenceid", request.getParameter("consequenceid"));
		return eadYjjcService.findconsequenceResTeamDataGrid(map);
	}
	
	/**
	 * 计算结束_事故后果页面_应急装备	地图显示
	 */
	@RequestMapping(value="conseque/resinstrumentmap")
	@ResponseBody
	public String consequenceResInstrument(HttpServletRequest request, Model model) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("yjzbname", request.getParameter("yjjc_consequence_yjzb_zb_name"));
		map.put("yjzbdistance", request.getParameter("yjjc_consequence_yjzb_zb_distance"));
		map.put("consequenceid", request.getParameter("consequenceid"));
		return eadYjjcService.findconsequenceResInstrumentMap(map);
	}
	/**
	 * 计算结束_事故后果页面_应急装备   列表显示
	 */
	@RequestMapping(value="conseque/resinstrument", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> consequenceResInstrumentList(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		map.put("yjzbname", request.getParameter("yjjc_consequence_yjzb_zb_name"));
		map.put("yjzbdistance", request.getParameter("yjjc_consequence_yjzb_zb_distance"));
		map.put("consequenceid", request.getParameter("consequenceid"));
		return eadYjjcService.findconsequenceResInstrumentDataGrid(map);
	}
	
	/**
	 * 计算结束_事故后果页面_应急物资	地图显示
	 */
	@RequestMapping(value="conseque/matemap")
	@ResponseBody
	public String consequenceMate(HttpServletRequest request, Model model) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("yjwzname", request.getParameter("yjjc_consequence_yjwz_wz_name"));
		map.put("yjwzdistance", request.getParameter("yjjc_consequence_yjwz_wz_distance"));
		map.put("consequenceid", request.getParameter("consequenceid"));
		return eadYjjcService.findconsequenceMateMap(map);
	}
	/**
	 * 计算结束_事故后果页面_应急物资   列表显示
	 */
	@RequestMapping(value="conseque/mate", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> consequenceMateList(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		map.put("yjwzname", request.getParameter("yjjc_consequence_yjwz_wz_name"));
		map.put("yjwzdistance", request.getParameter("yjjc_consequence_yjwz_wz_distance"));
		map.put("consequenceid", request.getParameter("consequenceid"));
		return eadYjjcService.findconsequenceMateDataGrid(map);
	}

	/**
	 * 计算结束_事故后果页面_应急专家	地图显示
	 */
	/*@RequestMapping(value="conseque/resexpertmap")
	@ResponseBody
	public String consequenceResExpert(HttpServletRequest request, Model model) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("yjzjname", request.getParameter("yjjc_consequence_yjzj_zj_name"));
		map.put("consequenceid", request.getParameter("consequenceid"));
		return eadYjjcService.findconsequenceResExpertMap(map);
	}*/
	/**
	 * 计算结束_事故后果页面_应急专家
	 */
	@RequestMapping(value="conseque/resexpert", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> consequenceResExpertList(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		map.put("yjzjname", request.getParameter("yjjc_consequence_yjzj_zj_name"));
		map.put("consequenceid", request.getParameter("consequenceid"));
		return eadYjjcService.findconsequenceResExpertDataGrid(map);
	}	
	
	/**
	 * 计算结束_事故后果页面_应急医院	地图显示
	 */
	@RequestMapping(value="conseque/hospitalmap")
	@ResponseBody
	public String consequenceHospital(HttpServletRequest request, Model model) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("yjyyname", request.getParameter("yjjc_consequence_yjyy_yy_name"));
		map.put("yjyydistance", request.getParameter("yjjc_consequence_yjyy_yy_distance"));
		map.put("consequenceid", request.getParameter("consequenceid"));
		return eadYjjcService.findconsequenceHospitalMap(map);
	}
	/**
	 * 计算结束_事故后果页面_应急医院
	 */
	@RequestMapping(value="conseque/hospital", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> consequenceHospitalList(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		map.put("yjyyname", request.getParameter("yjjc_consequence_yjyy_yy_name"));
		map.put("yjyydistance", request.getParameter("yjjc_consequence_yjyy_yy_distance"));
		map.put("consequenceid", request.getParameter("consequenceid"));
		return eadYjjcService.findconsequenceHospitalDataGrid(map);
	}
	
	/**
	 * 计算结束_事故后果页面_应急处置技术
	 */
	@RequestMapping(value="conseque/disptechnology", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> consequenceDispTechnology(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		map.put("yjczjsname", request.getParameter("yjjc_consequence_yjczjs_hxp_name"));
		map.put("consequenceid", request.getParameter("consequenceid"));
		return eadYjjcService.findconsequenceMsdsDataGrid(map);
	}	
	
	/**
	 * 计算结束_事故后果页面_通讯录
	 */
	@RequestMapping(value="conseque/contacts", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> consequenceContactsList(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		map.put("yjtxlname", request.getParameter("yjjc_consequence_yjtxl_name"));
		map.put("consequenceid", request.getParameter("consequenceid"));
		return eadYjjcService.findconsequenceContactsDataGrid(map);
	}
	
	
	/**
	 * 生成应急辅助决策文书
	 * @param request,model
	 */
	@RequestMapping(value = "conseque/word")//, method = RequestMethod.GET)
	@ResponseBody
	public String exportWord(HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		if( StringUtils.isNotEmpty( request.getParameter("consequenceid") ) )
			map.put("consequenceid", request.getParameter("consequenceid"));
		return eadYjjcService.exportWord(request,map);
	}
	
	
	/**
	 * 火球
	 * @param aca
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createfireball")
	@ResponseBody
	public String createFireball( @Valid ACA_FireballEntity aca, HttpServletRequest request, Model model) throws Exception{
		return eadYjjcService.countSaveEadFireball(eadYjjcService.commonSetEad(request),aca);
	}
	
	/**
	 * 压缩气体物理爆炸
	 * @param aca
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "creategasphysical")
	@ResponseBody
	public String createGasphysical(@Valid ACA_GasphysicalEntity aca, HttpServletRequest request, Model model) throws Exception{
		return eadYjjcService.countSaveEadGasphysical(eadYjjcService.commonSetEad(request),aca);
	}
		
	/**
	 * 喷射火
	 * @param aca
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createjetfire")
	@ResponseBody
	public String createJetFire(@Valid ACA_JetFireEntity aca, HttpServletRequest request, Model model) throws Exception{
		return eadYjjcService.countSaveEadJetFire(eadYjjcService.commonSetEad(request),aca);
	}
	
	/**
	 * 持续泄漏
	 * @param aca
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createleakage")
	@ResponseBody
	public String createLeakage(@Valid ACA_LeakageEntity aca, HttpServletRequest request, Model model) throws Exception{
		
		return eadYjjcService.countSaveEadLeakage(eadYjjcService.commonSetEad(request),aca);
	}
	
	/**
	 * 物理爆炸（压力容器爆炸）
	 * @param aca
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createphysical")
	@ResponseBody
	public String createPhysical(@Valid ACA_PhysicalEntity aca, HttpServletRequest request, Model model) throws Exception{
		return eadYjjcService.countSaveEadPhysical(eadYjjcService.commonSetEad(request),aca);
	}
	
	/**
	 * 池火灾
	 * @param aca
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createpoolfire")
	@ResponseBody
	public String createPoolFire(@Valid ACA_PoolFireEntity aca, HttpServletRequest request, Model model) throws Exception{
		return eadYjjcService.countSaveEadPoolFire(eadYjjcService.commonSetEad(request),aca);
	}
	
	/**
	 * 化学爆炸（蒸气云爆炸）
	 * @param aca
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createvce")
	@ResponseBody
	public String createVce(@Valid ACA_VceEntity aca, HttpServletRequest request, Model model) throws Exception{
		return eadYjjcService.countSaveEadVce(eadYjjcService.commonSetEad(request),aca);
	}
	
	/**
	 * 瞬时泄漏
	 * @param aca
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createinstantleakage")
	@ResponseBody
	public Map<String, Object> createInstantleakage(@Valid ACA_InstantleakageEntity aca, HttpServletRequest request, Model model) throws Exception{
		return eadYjjcService.countSaveEadInstantleakage(eadYjjcService.commonSetEad(request),aca);
	}

	
}
