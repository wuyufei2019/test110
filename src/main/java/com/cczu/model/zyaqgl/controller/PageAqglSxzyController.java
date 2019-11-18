package com.cczu.model.zyaqgl.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.model.zyaqgl.entity.AQGL_SxkjzyEntity;
import com.cczu.model.zyaqgl.entity.AQGL_SxkjzyFxEntity;
import com.cczu.model.zyaqgl.entity.AQGL_Zyaq_Aqcs;
import com.cczu.model.zyaqgl.service.AqglAqcsService;
import com.cczu.model.zyaqgl.service.AqglDhzyService;
import com.cczu.model.zyaqgl.service.AqglSxzyFxService;
import com.cczu.model.zyaqgl.service.AqglSxzyService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.MessageUtil;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 安全生产-受限空间作业Controller
 * @author zpc
 */
@Controller
@RequestMapping("zyaqgl/sxzy")
public class PageAqglSxzyController extends BaseController {

	@Autowired
	private AqglSxzyService aqglSxzyService;
	@Autowired
	private AqglSxzyFxService aqglSxzyFxService;
	@Autowired
	private AqglAqcsService aqglaqcsService;
	@Autowired
	private AqglDhzyService aqgldhzyService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	 
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		model.addAttribute("userid", UserUtil.getCurrentUser().getId());
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("qyid", request.getParameter("qyid"));
		//model.addAttribute("businesstype", WorkflowUtils.AQGLSXKJZY);
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type", "2");
				else
					model.addAttribute("type", "1");
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			model.addAttribute("type", "2");
		}	
		return "zyaqgl/zyaq/sxzy/index";
	}
	
	/**
	 * 数据list 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m0", request.getParameter("zyaq_sxzy_m0"));
		map.put("m3", request.getParameter("zyaq_sxzy_m3"));
		map.put("zt", request.getParameter("zyaq_sxzy_zt"));
		map.put("qyname", request.getParameter("zyaq_sxzy_qyname"));//企业名
		map.putAll(getAuthorityMap());

		//安监端条件
		String qyid = request.getParameter("qyid");
		if(StringUtils.isNotEmpty(qyid))
			map.put("qyid", qyid);
		return aqglSxzyService.dataGrid(map);	
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqglSxzyService.deleteInfo(Long.parseLong(arrids[i]));
			//删除相应分析数据
			aqglSxzyFxService.deleteInfoByid1(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		String code="SXZY-"+DateUtils.getDateRandom();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("m0", code);
		map.put("sqr", UserUtil.getCurrentUser().getName());//申请人
		model.addAttribute("sxzy", map);
		model.addAttribute("action", "create");
		model.addAttribute("sqr", UserUtil.getCurrentUser().getName());//申请人
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "zyaqgl/zyaq/sxzy/form";
	}
	
	/**
	 * 添加
	 * @param request,model
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(AQGL_SxkjzyEntity sxzy, Model model) {
		String datasuccess = "success";
		Timestamp t=DateUtils.getSysTimestamp();
		sxzy.setID1(UserUtil.getCurrentUser().getId2());
		sxzy.setID2((long)UserUtil.getCurrentUser().getId());
		sxzy.setS1(t);
		sxzy.setS2(t);
		sxzy.setS3(0);
		sxzy.setZt("0");
		sxzy.setStatus("0");
		aqglSxzyService.addInfo(sxzy);
		// 返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		AQGL_SxkjzyEntity sxzy = aqglSxzyService.findById(id);
		model.addAttribute("sxzy", sxzy);
		//返回页面
		model.addAttribute("action", "update");
		return "zyaqgl/zyaq/sxzy/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(AQGL_SxkjzyEntity sxzy,  Model model){
		String datasuccess="success";	
		AQGL_SxkjzyEntity sxzy2 = aqglSxzyService.findById(sxzy.getID());
		sxzy2.setM0(sxzy.getM0());
		sxzy2.setM1(sxzy.getM1());
		sxzy2.setM2(sxzy.getM2());
		sxzy2.setM3(sxzy.getM3());
		sxzy2.setM4(sxzy.getM4());
		sxzy2.setM5(sxzy.getM5());
		sxzy2.setM6(sxzy.getM6());
		sxzy2.setM7(sxzy.getM7());
		sxzy2.setM8(sxzy.getM8());
		sxzy2.setM9(sxzy.getM9());
		sxzy2.setM9(sxzy.getM10());
		sxzy2.setM9(sxzy.getM11());
		aqglSxzyService.updateInfo(sxzy2);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 分配任务页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "fprw/{id}", method = RequestMethod.GET)
	public String fprw(@PathVariable("id") Long id, Model model) {
		AQGL_SxkjzyEntity sxzy = aqglSxzyService.findById(id);
		model.addAttribute("sxzy", sxzy);
		//返回页面
		model.addAttribute("action", "fprw");
		return "zyaqgl/zyaq/sxzy/fprwform";
	}
	
	/**
	 * 分配任务
	 * @param request,model
	 */
	@RequestMapping(value = "fprw")
	@ResponseBody
	public String fprw(AQGL_SxkjzyEntity sxzy,  Model model){
		String datasuccess="success";	
		sxzy.setZt("1");
		sxzy.setM27(UserUtil.getCurrentUser().getId()+"");
		sxzy.setM27_1(DateUtils.getSysTimestamp());
		aqglSxzyService.updateInfo(sxzy);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 分析页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "fx/{id}", method = RequestMethod.GET)
	public String fx(@PathVariable("id") Long id, Model model) {
		model.addAttribute("sxzyid", id);
		//返回页面
		model.addAttribute("action", "fx");
		return "zyaqgl/zyaq/sxzy/fxform";
	}
	
	/**
	 * 分析
	 * @param request,model
	 */
	@RequestMapping(value = "fx")
	@ResponseBody
	public String fx(Long id,String M29, String M30, String M31, Model model, HttpServletRequest request){
		String datasuccess="success";	
		String wz[] = request.getParameterValues("wz");
		String krq[] = request.getParameterValues("krq");
		String yhl[] = request.getParameterValues("yhl");
		String fxsj[] = request.getParameterValues("fxsj");
		String bw[] = request.getParameterValues("bw");
		String czr[] = request.getParameterValues("czr");
		//循环插入分析表
		if(wz != null){
			for(int i=0;i<wz.length;i++){
				AQGL_SxkjzyFxEntity sxfx = new AQGL_SxkjzyFxEntity();
				sxfx.setID1(id);
				sxfx.setM1(wz[i]);
				sxfx.setM2(krq[i]);
				sxfx.setM3(yhl[i]);
				sxfx.setM4(StringUtils.isBlank(fxsj[i])?null:Timestamp.valueOf(fxsj[i]));
				sxfx.setM5(bw[i]);
				sxfx.setM6(czr[i]);
				aqglSxzyFxService.addInfo(sxfx);
			}
		}
		
		//修改作业证状态
		AQGL_SxkjzyEntity sxzy = aqglSxzyService.findById(id);
		sxzy.setM29(M29);
		sxzy.setM30(M30);
		sxzy.setM31(M31);
		sxzy.setZt("2");//已分析
		aqglSxzyService.updateInfo(sxzy);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 动火作业安全措施列表显示页面
	 * @param model
	 */
	@RequestMapping(value="aqcsindex")
	public String aqcsindex(Model model,HttpServletRequest request) {
		model.addAttribute("id1", request.getParameter("id1"));
		model.addAttribute("type", request.getParameter("type"));
		return "zyaqgl/zyaq/sxzy/aqcsindex";
	}
	
	/**
	 * 受限空间作业安全措施list 
	 * @param request
	 */
	@RequestMapping(value="aqcslist")
	@ResponseBody
	public Map<String, Object> aqcsData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		String type=request.getParameter("type");
		if(type!=null){
			map.put("id1", request.getParameter("id1"));
			map.put("type", type);
			return aqglaqcsService.bzaqscdataGrid2(map);	
		}else{
			return aqglaqcsService.aqscdataGrid2(map);
		}
	}
	
	/**
	 * 编制安全措施信息
	 * @param request,model
	 */
	@RequestMapping(value = "createAqcs/{id1}", method = RequestMethod.GET)
	@ResponseBody
	public String createAqcs(@PathVariable("id1") Long id1,HttpServletRequest request) {
		String datasuccess="编制成功";
		//批量关联安全措施
        String listjson=request.getParameter("list");
        List<AQGL_Zyaq_Aqcs> list =JSON.parseArray(listjson, AQGL_Zyaq_Aqcs.class);
		for(int i=0;i<list.size();i++){
            AQGL_Zyaq_Aqcs aqcs = list.get(i);
			aqcs.setID1(id1);
			aqcs.setM2("2");
			aqgldhzyService.addAqcs(aqcs);
		}
		//修改状态
		AQGL_SxkjzyEntity sxzy = aqglSxzyService.findById(id1);
		sxzy.setM15_1(DateUtils.getSysTimestamp());
		sxzy.setZt("3");
		aqglSxzyService.updateInfo(sxzy);
		return datasuccess;
	}
	
	/**
	 * 确认安全措施信息
	 * @param request,model
	 */
	@RequestMapping(value = "confirmAqcs")
	@ResponseBody
	public String confirmAqcs(HttpServletRequest request) {
		String datasuccess="已确认安全措施";
		String listjson=request.getParameter("list");
		long id=0l;
        List<AQGL_Zyaq_Aqcs> list =JSON.parseArray(listjson, AQGL_Zyaq_Aqcs.class);
        for(int i=0;i<list.size();i++){
	        try {
	        	AQGL_Zyaq_Aqcs aqsc=list.get(i);
	        	id=aqsc.getID1();
	        	aqgldhzyService.updateAqcs(aqsc);
			} catch (Exception e) {
				datasuccess="failed";
			}
        }
      //修改状态
		AQGL_SxkjzyEntity sxzy = aqglSxzyService.findById(id);
		sxzy.setM16_1(DateUtils.getSysTimestamp());
		sxzy.setZt("4");
		aqglSxzyService.updateInfo(sxzy);
		return datasuccess;
	}
	
	/**
	 * 实施安全教育
	 */
	@RequestMapping(value = "ssjy/{id}")
	@ResponseBody
	public String ssjy(@PathVariable("id") long id) {
		String datasuccess="实施成功";
		AQGL_SxkjzyEntity sxzy = aqglSxzyService.findById(id);
		sxzy.setM13_1(DateUtils.getSysTimestamp());
		sxzy.setZt("5");
		aqglSxzyService.updateInfo(sxzy);
		return datasuccess;
	}
	
	/**
	 * 申请单位确认页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "dwqr/{id}", method = RequestMethod.GET)
	public String dwqr(@PathVariable("id") Long id, Model model) {
		model.addAttribute("sxzyid", id);
		//返回页面
		model.addAttribute("action", "dwqr");
		return "zyaqgl/zyaq/sxzy/dwqrform";
	}
	
	/**
	 * 申请单位确认
	 * @param request,model
	 */
	@RequestMapping(value = "dwqr")
	@ResponseBody
	public String dwqr(long id, String M21, Model model){
		String datasuccess="success";	
		AQGL_SxkjzyEntity sxzy = aqglSxzyService.findById(id);
		sxzy.setM21(M21);
		sxzy.setM22(DateUtils.getSysTimestamp());
		sxzy.setZt("6");
		aqglSxzyService.updateInfo(sxzy);
		
		Map<String,Object>  msgmap = new HashMap<String,Object>();
		msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zyaqgl/dhzy/sxkj.jsp");
		msgmap.put(Message.MSGTARGET_PC,"zyaqgl/sxzy/index");
		MessageUtil.sendMsg(sxzy.getM18(), UserUtil.getCurrentShiroUser().getId()+"", "受限空间作业审批", Message.MessageType.DSP.getMsgType(),JSON.toJSONString(msgmap), "受限空间作业审批");
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 审批页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "sp/{id}", method = RequestMethod.GET)
	public String sp(@PathVariable("id") Long id, Model model) {
		model.addAttribute("sxzyid", id);
		//返回页面
		model.addAttribute("action", "sp");
		return "zyaqgl/zyaq/sxzy/spform";
	}
	
	/**
	 * 审批确认
	 * @param request,model
	 */
	@RequestMapping(value = "sp")
	@ResponseBody
	public String sp(long id, String M23, Model model){
		String datasuccess="success";	
		AQGL_SxkjzyEntity sxzy = aqglSxzyService.findById(id);
		sxzy.setM23(M23);
		sxzy.setM24(DateUtils.getSysTimestamp());
		sxzy.setZt("7");
		aqglSxzyService.updateInfo(sxzy);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 验收
	 */
	@RequestMapping(value = "ys/{id}")
	@ResponseBody
	public String ys(@PathVariable("id") long id) {
		String datasuccess="验收成功";
		AQGL_SxkjzyEntity sxzy = aqglSxzyService.findById(id);
		sxzy.setM25(DateUtils.getSysTimestamp());
		sxzy.setZt("8");
		aqglSxzyService.updateInfo(sxzy);
		return datasuccess;
	}
	
	/**
	 * 关闭
	 */
	@RequestMapping(value = "gb/{id}")
	@ResponseBody
	public String gb(@PathVariable("id") long id) {
		String datasuccess="关闭成功";
		AQGL_SxkjzyEntity sxzy = aqglSxzyService.findById(id);
		sxzy.setM28(UserUtil.getCurrentUser().getId()+"");
		sxzy.setM28_1(DateUtils.getSysTimestamp());
		sxzy.setZt("9");
		aqglSxzyService.updateInfo(sxzy);
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> sxzy = aqglSxzyService.findallById(id);
		model.addAttribute("sxzy", sxzy);
		return "zyaqgl/zyaq/sxzy/view";
	}
	
	/**
	 * 分析数据list 
	 * @param request
	 */
	@RequestMapping(value="sxzyfxlist")
	@ResponseBody
	public Map<String, Object> getFxsjData(HttpServletRequest request) {
		String sxzyid = request.getParameter("sxzyid");
		return aqglSxzyFxService.dataGrid(sxzyid);	
	}
	
	/**
	 * 查看状态页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "viewzt/{id}", method = RequestMethod.GET)
	public String viewzt(@PathVariable("id") Long id, Model model) {
		Map<String,Object> sxzy = aqglSxzyService.findallById(id);
		model.addAttribute("sxzy", sxzy);
		//根据作业证id查找分析数据详情
		List<Map<String,Object>> sxfxs = aqglSxzyFxService.findAllByid1(id);
		model.addAttribute("sxfxs", sxfxs);
		return "zyaqgl/zyaq/sxzy/viewzt";
	}
	
	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","zyaqgl/sxzy/export");
		return "/common/formexcel";
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m0", request.getParameter("zyaq_sxzy_m0"));
		map.put("m3", request.getParameter("zyaq_sxzy_m3"));
		map.put("zt", request.getParameter("zyaq_sxzy_zt"));
		map.put("qyname", request.getParameter("zyaq_sxzy_qyname"));//企业名
		map.putAll(getAuthorityMap());
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		aqglSxzyService.exportExcel(response, map);
	}
	
	/**
	 * 导出受限空间作业word
	 */
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String getexportword(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = aqglSxzyService.getExportWord(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "sxkjzy.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
