package com.cczu.model.sbssgl.controller;

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
import com.cczu.model.sbssgl.entity.SBSSGL_GZZDEntity;
import com.cczu.model.sbssgl.service.SBSSGLGzzdService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.wordToPDF;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;

/**
 * 设备设施管理-规章制度controller
 */
@Controller
@RequestMapping("sbssgl/gzzd")
public class PageSbssglGzzdController extends BaseController {

	@Autowired
	private SBSSGLGzzdService sBSSGLGzzdService;
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
		
		return "sbssgl/gzzd/gzzd/index";
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("sbtype", request.getParameter("sbtype"));
		map.put("qyname", request.getParameter("qyname"));
		map.put("m1", request.getParameter("m1"));
		map.put("m2", request.getParameter("m2"));
		map.put("m3", request.getParameter("m3"));
		map.putAll(getAuthorityMap());
		return sBSSGLGzzdService.dataGrid(map);
	}

	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		model.addAttribute("action", "create");
		model.addAttribute("sbtype", request.getParameter("sbtype"));
		return "sbssgl/gzzd/gzzd/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(SBSSGL_GZZDEntity entity, Model model,HttpServletRequest request) {
		String datasuccess="success";
		ShiroUser user = UserUtil.getCurrentShiroUser();
		entity.setQyid(user.getQyid());
		entity.setM2(DateUtils.getSystemTime());
		entity.setScrid(Long.parseLong(user.getId()+""));
		//设置pdf，swg
		String url=entity.getM3();
		if(!StringUtils.isEmpty(url)){
			String filePath = request.getSession().getServletContext().getRealPath("/");
			String s[]=wordToPDF.getUN(url);//获取存放word地址和文件名的数组
			String x=wordToPDF.getWurl(url);
			String wurl=filePath+x;//获取word存放路径
			String purl=s[0]+"/"+s[1]+".pdf";//获得pdf数据库里存放的内容
			String surl=s[0]+"/"+s[1]+".swf";//获得swg数据库里存放的内容
			String purl2=filePath+s[0]+"/"+s[1]+".pdf";//设置pdf的存放地址
			String surl2=filePath+s[0]+"/"+s[1]+".swf";//设置swg的存放地址
			//将word转pdf
			wordToPDF.WordToPDF(wurl, purl2);
			wordToPDF.ConvertPdfToSwf(purl2,surl2);
			entity.setM9(purl);
			entity.setM10(surl);
		}
		sBSSGLGzzdService.addInfo(entity);
		//返回结果
		return datasuccess;
	}
	

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		SBSSGL_GZZDEntity gzzd = sBSSGLGzzdService.find(id);	
		model.addAttribute("gzzd", gzzd);
		//返回页面
		model.addAttribute("action", "update");
		return "sbssgl/gzzd/gzzd/form";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(SBSSGL_GZZDEntity entity, Model model, HttpServletRequest request){
		String datasuccess="success";
		//设置pdf，swg
		String url=entity.getM3();
		if(!StringUtils.isEmpty(url)){
			String filePath = request.getSession().getServletContext().getRealPath("/");
			String s[]=wordToPDF.getUN(url);//获取存放word地址和文件名的数组
			String x=wordToPDF.getWurl(url);
			String wurl=filePath+x;//获取word存放路径
			String purl=s[0]+"/"+s[1]+".pdf";//获得pdf数据库里存放的内容
			String surl=s[0]+"/"+s[1]+".swf";//获得swg数据库里存放的内容
			String purl2=filePath+s[0]+"/"+s[1]+".pdf";//设置pdf的存放地址
			String surl2=filePath+s[0]+"/"+s[1]+".swf";//设置swg的存放地址
			//将word转pdf
			wordToPDF.WordToPDF(wurl, purl2);
			wordToPDF.ConvertPdfToSwf(purl2,surl2);
			entity.setM9(purl);
			entity.setM10(surl);
		}
		sBSSGLGzzdService.updateInfo(entity);
		//返回结果
		return datasuccess;
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
			//删除点巡检信息
			sBSSGLGzzdService.deleteInfoById(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//获取点巡检信息
		Map<String,Object> gzzd = sBSSGLGzzdService.findById(id);	
		model.addAttribute("gzzd", gzzd);
		return "sbssgl/gzzd/gzzd/view";
	}
	
	/**
	 * 在线查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view2/{id}", method = RequestMethod.GET)
	public String view2(@PathVariable("id") Long id, Model model) {
		Map<String,Object> gzzd = sBSSGLGzzdService.findById(id);		
		model.addAttribute("gzzd", gzzd);
		return "sbssgl/gzzd/gzzd/view2";
	}
}
