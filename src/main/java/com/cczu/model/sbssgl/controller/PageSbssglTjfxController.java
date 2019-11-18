package com.cczu.model.sbssgl.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.sbssgl.service.SBSSGLTjfxService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 设备设施管理-统计分析controller
 */
@Controller
@RequestMapping("sbssgl/tjfx")
public class PageSbssglTjfxController extends BaseController {

	@Autowired
	private SBSSGLTjfxService sBSSGLTjfxService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model, HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else{
			return "../error/403";
		}
		
		return "sbssgl/tjfx/index";
	}
	
	/**
	 * 设备完好状况统计
	 * @throws ParseException 
	 */
	@RequestMapping(value="sbwhtj")
	@ResponseBody
	public String aqjctj3(HttpServletRequest request) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyid", request.getParameter("qyid"));
		map.put("deptid", request.getParameter("deptid"));
		map.put("starttime", request.getParameter("starttime"));
		return JsonMapper.getInstance().toJson(sBSSGLTjfxService.getSbwhCount(map));
	}
	
	/**
	 * 设备保养状况统计
	 * @throws ParseException 
	 */
	@RequestMapping(value="sbbytj")
	@ResponseBody
	public String aqjctj4(HttpServletRequest request) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyid", request.getParameter("qyid"));
		map.put("deptid", request.getParameter("deptid"));
		map.put("starttime", request.getParameter("starttime"));
		return JsonMapper.getInstance().toJson(sBSSGLTjfxService.getSbbyCount(map));
	}
	
	/**
	 * 设备其他状况统计
	 * @throws ParseException 
	 */
	@RequestMapping(value="sbqttj")
	@ResponseBody
	public String aqjctj5(HttpServletRequest request) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyid", request.getParameter("qyid"));
		map.put("deptid", request.getParameter("deptid"));
		map.put("starttime", request.getParameter("starttime"));
		return JsonMapper.getInstance().toJson(sBSSGLTjfxService.getSbqtCount(map));
	}
	
	/**
	 * 导出月度报表word
	 * @throws ParseException 
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public String getWord(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		Map<String, Object> mapData = new HashMap<>();
		mapData.put("qyid", request.getParameter("qyid"));
		mapData.put("deptid", request.getParameter("deptid"));
		mapData.put("starttime", request.getParameter("starttime"));
		Map<String, Object> map = sBSSGLTjfxService.getWord(mapData);
		//设置导出的文件名
		String filename = "设备管理月报表_" + DateUtils.getDateRandom() + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "ydbb.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	/**
	 * 安全隐患类别占比统计
	 * @throws ParseException 
	 */
	/*@RequestMapping(value="aqjctj5")
	@ResponseBody
	public String aqjctj5(HttpServletRequest request) throws ParseException {
		Map<String, Object> map = getAuthorityMap();
		map = getIdMap(map, request);
		map.put("m2", request.getParameter("fxlb_name"));
		return JsonMapper.getInstance().toJson(yhpcTjfxService.getFxlbCount(map));
	}*/
	
}
