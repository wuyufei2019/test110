package com.cczu.model.zdgl.controller;

import com.cczu.model.entity.dto.Tdic_NoteTreeDto;
import com.cczu.model.zdgl.entity.ZDGL_FLFGEntity;
import com.cczu.model.zdgl.entity.ZDGL_LbflEntity;
import com.cczu.model.zdgl.service.ZdglFlfgService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.wordToPDF;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 制度管理-法律法规库controller
 */
@Controller
@RequestMapping("zdgl/flfg")
public class PageZdglFlfgController extends BaseController {

	@Autowired
	private ZdglFlfgService zdglFlfgService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		model.addAttribute("type","1");
		model.addAttribute("qyid", request.getParameter("qyid"));
		return "zdgl/flfgsb/flfg/index";
	}

	/**
	 * 安全知识库 法律法规标准库
	 * @param model
	 */
	@RequestMapping(value="sekb/index")
	public String sekbindex(Model model,HttpServletRequest request) {
		model.addAttribute("type","2");
		model.addAttribute("qyid", request.getParameter("qyid"));
		return "zdgl/flfgsb/flfg/index2";
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("type", request.getParameter("type"));
		map.put("m1", request.getParameter("zdgl_flfg_m1"));
		map.put("m1_1", request.getParameter("zdgl_flfg_m1_1"));
		map.put("m2", request.getParameter("zdgl_flfg_m2"));
		map.put("m3", request.getParameter("zdgl_flfg_m3"));
		String qyid = request.getParameter("qyid");
		if(StringUtils.isEmpty(qyid)){
			map.put("qyid", UserUtil.getCurrentUser().getId2());
		}else{
			map.put("qyid", qyid);
		}
		return zdglFlfgService.dataGrid(map);
	}

	/**
	 * 删除法律法规
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			zdglFlfgService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> flfg = zdglFlfgService.findById(id);		
		model.addAttribute("flfg", flfg);
		return "zdgl/flfgsb/flfg/view";
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zdgl:flfg:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		model.addAttribute("action", "create");
		model.addAttribute("type",request.getParameter("type"));
		return "zdgl/flfgsb/flfg/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("zdgl:flfg:add")
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(ZDGL_FLFGEntity flfg, Model model,HttpServletRequest request) {
		String datasuccess="success";
		//设置pdf，swg
		String url=flfg.getM8();
		if(!StringUtils.isEmpty(url)){
			if (!url.startsWith("http")) {//用户添加的数据
				String filePath = request.getSession().getServletContext().getRealPath("/");
				String s[] = wordToPDF.getUN(url);//获取存放word地址和文件名的数组
				String x = wordToPDF.getWurl(url);
				String wurl = filePath + x;//获取word存放路径
				String purl = s[0] + "/" + s[1] + ".pdf";//获得pdf数据库里存放的内容
				String surl = s[0] + "/" + s[1] + ".swf";//获得swg数据库里存放的内容
				String purl2 = filePath + s[0] + "/" + s[1] + ".pdf";//设置pdf的存放地址
				String surl2 = filePath + s[0] + "/" + s[1] + ".swf";//设置swg的存放地址
				//将word转pdf
				wordToPDF.WordToPDF(wurl, purl2);
				wordToPDF.ConvertPdfToSwf(purl2, surl2);
				flfg.setM11(purl);
				flfg.setM12(surl);
			} else {//从资源库中导入的数据
				//根据导入数据中的小类别和企业id查询结果集
				List<ZDGL_LbflEntity> lblist=zdglFlfgService.getLblistByName(flfg.getM1(),UserUtil.getCurrentUser().getId2());
				if (lblist.size() == 0) {//如果没有此类别，则添加类别，将返回的id赋给flfg
					ZDGL_LbflEntity lbfl = new ZDGL_LbflEntity();
					lbfl.setM1(flfg.getM1());
					lbfl.setPid(0L);
					Timestamp t=DateUtils.getSysTimestamp();
					lbfl.setS1(t);
					lbfl.setS2(t);
					lbfl.setS3(0);
					ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
					if(sessionuser.getUsertype().equals("1")){
						lbfl.setID1(sessionuser.getQyid());
					}
					Long lbid = zdglFlfgService.addLbflInfo2(lbfl);//添加类别
					flfg.setM1(lbid+"");
				} else {//如果存在此类别，则取出id并赋给flfg
					ZDGL_LbflEntity lb = lblist.get(0);
					flfg.setM1(String.valueOf(lb.getID()));
				}
			}
		}
		String type = request.getParameter("type");
		if(type.equals("1"))
			flfg.setID1(UserUtil.getCurrentUser().getId2());
		else
			flfg.setID1(null);
		zdglFlfgService.addInfo(flfg);
		//返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		ZDGL_FLFGEntity flfg = zdglFlfgService.find(id);
		model.addAttribute("flfg", flfg);
		//返回页面
		model.addAttribute("action", "update");
		return "zdgl/flfgsb/flfg/form";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(ZDGL_FLFGEntity flfg, Model model,HttpServletRequest request){
		String datasuccess="success";
		//设置pdf，swg
		String url=flfg.getM8();
		if(!StringUtils.isEmpty(url)){
			if (!url.startsWith("http")) {//用户添加的数据
				String filePath = request.getSession().getServletContext().getRealPath("/");
				String s[] = wordToPDF.getUN(url);//获取存放word地址和文件名的数组
				String x = wordToPDF.getWurl(url);
				String wurl = filePath + x;//获取word存放路径
				String purl = s[0] + "/" + s[1] + ".pdf";//获得pdf数据库里存放的内容
				String surl = s[0] + "/" + s[1] + ".swf";//获得swg数据库里存放的内容
				String purl2 = filePath + s[0] + "/" + s[1] + ".pdf";//设置pdf的存放地址
				String surl2 = filePath + s[0] + "/" + s[1] + ".swf";//设置swg的存放地址
				//将word转pdf
				wordToPDF.WordToPDF(wurl, purl2);
				wordToPDF.ConvertPdfToSwf(purl2, surl2);
				flfg.setM11(purl);
				flfg.setM12(surl);
			}
		}else{
			flfg.setM11(null);
			flfg.setM12(null);
		}
		zdglFlfgService.updateInfo(flfg);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("zdgl:flfg:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m1_1", request.getParameter("zdgl_flfg_m1_1"));
		map.put("m1", request.getParameter("zdgl_flfg_m1"));
		map.put("m2", request.getParameter("zdgl_flfg_m2"));
		map.put("m3", request.getParameter("zdgl_flfg_m3"));
		map.put("qyid", UserUtil.getCurrentUser().getId2());
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		String type = request.getParameter("type");
		map.put("type",type);
		if(type.equals("1"))
			zdglFlfgService.exportExcel(request,response, map);
		else
			zdglFlfgService.exportExcel2(request,response, map);

	}

	/**
	 * 显示所有列
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("zdgl:flfg:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model, HttpServletRequest request) {
		String type = request.getParameter("type");
		model.addAttribute("url","zdgl/flfg/export?type="+type);
		return "/common/formexcel";
	}
	
	/**
	 * 根据类别获取法律法规名称
	 * @param lb
	 * @return
	 */
	@RequestMapping(value = "json1")
	@ResponseBody
	public String json1(String lb, String m1_1) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sbm1", lb);
		map.put("m1_1", m1_1);
		map.put("qyid", UserUtil.getCurrentUser().getId2());
		map.put("type", "3");
		return zdglFlfgService.findbym1(map);
	}
	
	/**
	 * 根据id获取法律法规名称
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "json2")
	@ResponseBody
	public String json2(String id) {
		return zdglFlfgService.findbyidjson(id);
	}
	
	/**
	 * 在线查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view2/{id}", method = RequestMethod.GET)
	public String view2(@PathVariable("id") Long id, Model model) {
		Map<String,Object> flfg = zdglFlfgService.findById(id);		
		model.addAttribute("flfg", flfg);
		return "zdgl/flfgsb/flfg/view2";
	}
	
	
	/**
	 * 跳转类别分类管理页面
	 * @param model
	 */
	@RequestMapping(value = "lbgl" , method = RequestMethod.GET)
	public String lbgl(Model model) {
		return "zdgl/flfgsb/flfg/lbgl";
	}

	/**
	 * 分类集合(JSON) Treegrid
	 */
	@RequestMapping(value="treelist")//,method = RequestMethod.GET)
	@ResponseBody
	public String lbgl(){
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		List<Tdic_NoteTreeDto> lbflList=zdglFlfgService.getLbfl(sessionuser.getQyid());
		return JsonMapper.getInstance().toJson(lbflList);
	}
	
	/**
	 * 添加类别分类页面跳转
	 * @param model
	 */
	@RequestMapping(value = "createLb" , method = RequestMethod.GET)
	public String createCategory(Model model) {
		model.addAttribute("bis", new ZDGL_FLFGEntity());
		model.addAttribute("action", "createLb");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "zdgl/flfgsb/flfg/form2";
	}
	
	/**
	 * 添加类别分类
	 * @param request,model
	 */
	@RequestMapping(value = "createLb")
	@ResponseBody
	public String createLbfl(ZDGL_LbflEntity entity,HttpServletRequest request) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){
			entity.setID1(sessionuser.getQyid());
		}
		return zdglFlfgService.addLbflInfo(entity);
		
	}

	/**
	 * 修改类别页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "updateLb/{id}", method = RequestMethod.GET)
	public String updateLb(@PathVariable("id") Long id, Model model) {
		//查询选择的隐患排查点信息
		Map<String, Object> entity = zdglFlfgService.findInforById(id);
		model.addAttribute("entity", entity);
		//返回页面
		model.addAttribute("action", "updateLb");
		return "zdgl/flfgsb/flfg/form2";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequestMapping(value = "updateLb")
	@ResponseBody
	public String updatelb(ZDGL_LbflEntity entity,HttpServletRequest request){
		String datasuccess = "failed";
		Timestamp t= DateUtils.getSysTimestamp();
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		//必须设置，否则会出错
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setID1(sessionuser.getQyid());
		zdglFlfgService.updateInfo(entity);
		datasuccess="success";
		return datasuccess;
	}
	
	/**
	 * 删除类别分类
	 */
	@RequestMapping(value = "deleteLb/{id}")
	@ResponseBody
	public String deleteLb(@PathVariable("id") String id) {
		String datasuccess="success";
		zdglFlfgService.deleteLbInfo(Long.parseLong(id));
		return datasuccess;
	}
	
	/**
	 * 导入页面跳转
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "exinjump", method = RequestMethod.GET)
	public String exin(Model model) {
		model.addAttribute("action", "exin");
		return "common/importForm";
	}
	
	/**
	 * 导入
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequiresPermissions("zdgl:flfg:exin")
	@RequestMapping(value = "exin")
	@ResponseBody
	public String exinExcel(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = uploadExcel(request, response);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		if (map.get("content") != null) {
			map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
			String type = request.getParameter("type");
			map.put("type", type);
			if(type.equals("1"))
				resultmap = zdglFlfgService.exinExcel(map);
			else
				resultmap = zdglFlfgService.exinExcel2(map);
		} else {
			resultmap.put("returncode", -2);
		}
		return JsonMapper.toJsonString(resultmap);
	}
}
