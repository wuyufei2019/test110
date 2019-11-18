package com.cczu.model.sbssgl.controller;

import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBJFEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBYSEntity;
import com.cczu.model.sbssgl.service.SBSSGLSbjfService;
import com.cczu.model.sbssgl.service.SBSSGLSbysService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 设备设施管理-设备交付controller
 */
@Controller
@RequestMapping("sbssgl/sbjf")
public class PageSbssglSbjfController extends BaseController {

	@Autowired
	private SBSSGLSbjfService sBSSGLSbjfService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private SBSSGLSbysService sBSSGLSbysService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
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
		return "sbssgl/sbjf/index";
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
		map.put("m4", request.getParameter("m4"));
		map.put("m5", request.getParameter("m5"));
		map.put("m15", request.getParameter("m15"));
		map.put("m20", request.getParameter("m20"));
		map.putAll(getAuthorityMap());
		return sBSSGLSbjfService.dataGrid(map);
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			//删除设备启用信息
			sBSSGLSbjfService.deleteSbjfById(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		SBSSGL_SBJFEntity sbjf = sBSSGLSbjfService.find(id);	
		model.addAttribute("sbjf", sbjf);
		return "sbssgl/sbjf/view";
	}
	
	/**
	 * 添加页面跳转
	 * @param sbysid 设备验收id
	 * @param model
	 */
	@RequestMapping(value = "create/{sbysid}" , method = RequestMethod.GET)
	public String create(@PathVariable("sbysid") Long sbysid,Model model) {
		model.addAttribute("action", "create");
		SBSSGL_SBYSEntity sbys = sBSSGLSbysService.find(sbysid);	
		SBSSGL_SBJFEntity sbjf = new SBSSGL_SBJFEntity();
		sbjf.setSbysid(sbysid);
		sbjf.setM4(sbys.getM2());
		sbjf.setM5(sbys.getM4());
		sbjf.setM6(sbys.getM6());
		sbjf.setM15(DateUtils.getSysTimestamp());
		sbjf.setQyid(sbys.getQyid());
		model.addAttribute("sbjf", sbjf);
		return "sbssgl/sbjf/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(SBSSGL_SBJFEntity entity, Model model,HttpServletRequest request) {
		String datasuccess="success";
		/*entity.setQyid(UserUtil.getCurrentShiroUser().getQyid());*/
		entity.setM20("0");
		sBSSGLSbjfService.addInfo(entity);
		//返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		SBSSGL_SBJFEntity sbjf = sBSSGLSbjfService.find(id);	
		model.addAttribute("sbjf", sbjf);
		//返回页面
		model.addAttribute("action", "update");
		return "sbssgl/sbjf/form";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(SBSSGL_SBJFEntity entity, Model model,HttpServletRequest request){
		String datasuccess="success";
		sBSSGLSbjfService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 导出设备交付单word
	 */
	@RequestMapping(value = "export/{id}")
	@ResponseBody
	public String getWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = sBSSGLSbjfService.getWord(id);
		//设置导出的文件名
		String filename = "设备启动单_" + DateUtils.getDateRandom() + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "sbjfd.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	/**
	 * 上传附件页面跳转
	 */
	@RequestMapping(value = "uploadindex/{id}")
	public String uploadindex(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		return "sbssgl/sbjf/upload";
	}
	
	/**
	 * 上传附件
	 */
	@RequestMapping(value = "uploadfj")
	@ResponseBody
	public String uploadfj(long id,String m19) {
		String datasuccess="success";
		SBSSGL_SBJFEntity sbjf = sBSSGLSbjfService.find(id);	
		sbjf.setM19(m19);
		sbjf.setM20("1");
		sBSSGLSbjfService.updateInfo(sbjf);
		return datasuccess;
	}
}
