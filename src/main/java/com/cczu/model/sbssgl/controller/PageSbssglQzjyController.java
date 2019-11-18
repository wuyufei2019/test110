package com.cczu.model.sbssgl.controller;

import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_QZJYEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBGLEntity;
import com.cczu.model.sbssgl.service.SBSSGLQzjyService;
import com.cczu.model.sbssgl.service.SBSSGLSbglService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 设备设施管理-备品备件controller
 */
@Controller
@RequestMapping("sbssgl/qzjy")
public class PageSbssglQzjyController extends BaseController {

	@Autowired
	private SBSSGLQzjyService sBSSGLQzjyService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private SBSSGLSbglService sBSSGLSbglService;
	
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
		return "sbssgl/qzjy/index";
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		map.put("m2", request.getParameter("m2"));
		map.put("m28", request.getParameter("m28"));
		map.put("time1", request.getParameter("time1"));
		map.put("m32", request.getParameter("m32"));
		map.put("status", request.getParameter("status"));
		map.put("sbtype", "1");  // 关联特种设备
		map.putAll(getAuthorityMap());
		return sBSSGLSbglService.dataGrid2(map);
	}
	
	/**
	 * 更新检测日期
	 * 
	 * @param id,model
	 */
	@RequestMapping(value = "update2/{sbid}", method = RequestMethod.GET)
	public String update2(@PathVariable("sbid") Long sbid, Model model) {
		// 查询选择的特种设备信息
		SBSSGL_SBGLEntity tzsb = sBSSGLSbglService.find(sbid);
		model.addAttribute("tzsb", tzsb);
		model.addAttribute("action", "update2");
		return "sbssgl/qzjy/form2";
	}
	
	/**
	 * 更新特种设备信息检测日期
	 * 
	 * @param request,model
	 */
	@RequestMapping(value = "update2")
	@ResponseBody
	public String update2(SBSSGL_SBGLEntity entity, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		SBSSGL_SBGLEntity tzsb = sBSSGLSbglService.find(entity.getID());

		SBSSGL_QZJYEntity qzjy = new SBSSGL_QZJYEntity();
		qzjy.setSbid(tzsb.getID());//设备id
		qzjy.setQyid(tzsb.getQyid());//企业id
		qzjy.setM1(tzsb.getM31());
		qzjy.setM2(tzsb.getM32());
		qzjy.setS1(t);
		qzjy.setS2(t);
		qzjy.setS3(0);
		//添加强制检验信息
		sBSSGLQzjyService.addInfo(qzjy);
		
		tzsb.setM31(entity.getM31());
		tzsb.setM32(entity.getM32());
		tzsb.setS2(t);
		//更新日期
		sBSSGLSbglService.updateInfo(tzsb);

		// 返回结果
		return datasuccess;
	}
	
	/**
	 * 更新历史页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "hisindex/{sbid}", method = RequestMethod.GET)
	public String showhistory(@PathVariable("sbid") Long sbid, Model model) {
		model.addAttribute("sbid",sbid);
		return "sbssgl/qzjy/hisindex";
	}
	
	/**
	 * 更新历史list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "hislist")
	@ResponseBody
	public Map<String, Object> getData2(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("sbid", request.getParameter("sbid").toString());
		return sBSSGLQzjyService.dataGrid(map);
	}

	
	
	/**
	 * 导出备品备件清单word
	 */
	/*@RequestMapping(value = "export")
	@ResponseBody
	public String getWord(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sbtype", request.getParameter("sbtype"));
		map.put("qyname", request.getParameter("qyname"));
		map.put("m2", request.getParameter("m2"));
		map.put("m3", request.getParameter("m3"));
		map.put("m5", request.getParameter("m5"));
		map.put("m6", request.getParameter("m6"));
		map.put("m7", request.getParameter("m7"));
		map.putAll(getAuthorityMap());
		Map<String, Object> dataMap = sBSSGLQzjyService.getWord(map);
		String ftlName = dataMap.get("ftlname").toString();
		//设置导出的文件名
		String filename = "备品备件清单_" + DateUtils.getDateRandom() + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(dataMap, ftlName, filePath, filename, request);
		return "/download/" + filename;
	}*/
}
