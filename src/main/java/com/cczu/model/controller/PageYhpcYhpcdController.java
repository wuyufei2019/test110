package com.cczu.model.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.YHPC_CheckPointEntity;
import com.cczu.model.service.YhpcStoppointService;
import com.cczu.model.service.YhpcYhpcdService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.QRCode;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;


/**
 * 隐患排查点Controller
 * @author YZH
 */
@Controller
@RequestMapping("yhpc/yhpcd")
public class PageYhpcYhpcdController extends BaseController {

	@Autowired
	private YhpcYhpcdService yhpcYhpcdService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private YhpcStoppointService yhpcStoppointService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "yhpc/yhpcd/index";
	}
	
	/**
	 * 隐患排查点list页面 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request, Model model) {
		//删除过期停产数据
		yhpcStoppointService.deleteStaleData();
		
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("yhpc_yhpcd_cx_qyname"));
		map.put("yhdname", request.getParameter("yhpc_yhpcd_yhdname"));
		map.put("yt", request.getParameter("yt"));
		ShiroUser user = UserUtil.getCurrentShiroUser();
		if("0".equals(user.getUsertype())){//安监
			map.put("xzqy",user.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0)
				map.put("jglx",user.getUserroleflg());
		}else if("1".equals(user.getUsertype())){//企业
			map.put("qyid",user.getQyid());
		}
		map.putAll(getAuthorityMap());
		return yhpcYhpcdService.dataGrid1(map);	
		
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		Map<String,Object> yhpcd = new HashMap<>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		//如果是企业，获取企业平面图
		if(sessionuser.getUsertype().equals("1")){
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			yhpcd.put("pmt", StringUtils.defaultString(be.getM33_3()));
			yhpcd.put("id1", sessionuser.getQyid());
			//获取企业地图坐标
			model.addAttribute("lng", be.getM16());
			model.addAttribute("lat", be.getM17());
		}
		//生成二维码编码
		yhpcd.put("bindcontent",UUID.randomUUID().toString().replaceAll("-", ""));
		model.addAttribute("yhpcd", yhpcd);
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "yhpc/yhpcd/form";
	}
	
	/**
	 * 添加隐患排查点信息
	 * @param request,model
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(YHPC_CheckPointEntity entity,HttpServletRequest request) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setCreatetime(t);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){
			entity.setID1(sessionuser.getQyid());;
		}
		String bdnrids=request.getParameter("bdnrids");
		return yhpcYhpcdService.addInfo(entity,bdnrids);
		
	}	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的隐患排查点信息
		Map<String, Object> yhpcd = yhpcYhpcdService.findInforById(id);
		model.addAttribute("yhpcd", yhpcd);
		
		//根据企业id获取企业对象
		BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(StringUtils.toLong(yhpcd.get("id1")));
		//获取企业平面图
		yhpcd.put("pmt", StringUtils.defaultString(be.getM33_3()));
		//如果无隐患点坐标获取企业坐标
		if(yhpcd.get("lng")==null||yhpcd.get("lng").equals("")){
			model.addAttribute("lng", be.getM16());
			model.addAttribute("lat", be.getM17());
		}
		 
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "yhpc/yhpcd/form";
	}
	
	/**
	 * 修改隐患排查点信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(YHPC_CheckPointEntity entity,HttpServletRequest request){
		String bdnrids=request.getParameter("bdnrids");
		return yhpcYhpcdService.updateInfo(entity,bdnrids);
	}
	
	/**
	 * 删除隐患排查点信息
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			yhpcYhpcdService.deletexjnrbyid1(Long.parseLong(arrids[i]));
			yhpcYhpcdService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String, Object> yhpcd = yhpcYhpcdService.findInforById(id);
		
		model.addAttribute("yhpcd", yhpcd);

		return "yhpc/yhpcd/view";
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("yhpc:yhpcd:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.put("qyname", request.getParameter("yhpc_yhpcd_cx_qyname"));
		map.put("yhdname", request.getParameter("yhpc_yhpcd_yhdname"));
//		map.put("starttime", request.getParameter("yhpc_yhpcd_starttime"));
//		map.put("endtime", request.getParameter("yhpc_yhpcd_finishtime"));
		map.put("yt", request.getParameter("yt"));
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		ShiroUser user = UserUtil.getCurrentShiroUser();
		if("0".equals(user.getUsertype())){//安监
			map.put("xzqy",user.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0)
				map.put("jglx",user.getUserroleflg());
		}else if("1".equals(user.getUsertype())){//企业
			map.put("qyid",user.getQyid());
		}
		yhpcYhpcdService.exportExcel(response, map);
	}
	
	/**
	 * 显示所有列
	 */
	@RequiresPermissions("yhpc:yhpcd:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","yhpc/yhpcd/export");
		return "/common/formexcel";
	}
	
	//导入页面跳转
	@RequestMapping(value = "exinjump", method = RequestMethod.GET)
	public String exin(Model model) {
		model.addAttribute("action", "exin");
		return "common/importForm";
	}	

	/**
	 * 绑定巡检内容list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "xjnrlist/{id1}")
	@ResponseBody
	public Map<String, Object> xjnrlist(@PathVariable("id1") String id1, HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		if(!"add".equals(id1)){
			map.put("id1", id1);
		}
		return yhpcYhpcdService.xjnrdataGrid(map);

	}
	
	//跳转已绑定巡检内容list
	@RequestMapping(value = "bdxjnr/{id}", method = RequestMethod.GET)
	public String bdxjnr(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		String qyid = request.getParameter("qyid");
		model.addAttribute("id1", id);
		model.addAttribute("qyid", qyid);
		return "yhpc/yhpcd/xjnr";
	}

	//跳转巡检内容list
	@RequestMapping(value = "xjnrcreate/{id1},{qyid}", method = RequestMethod.GET)
	public String xjnrcreate(@PathVariable("id1") Long id,@PathVariable("qyid") Long qyid, Model model,HttpServletRequest request) {
		model.addAttribute("id1", id);
		model.addAttribute("qyid", qyid);
		return "yhpc/yhpcd/xjnrall";
	}

	/**
	 * 绑定巡检内容
	 * 
	 * @param request,model
	 */
	@RequestMapping(value = "buildCheckContent/{ids}")
	@ResponseBody
	public String buildCheckContent(@PathVariable("ids") String ids,HttpServletRequest request) {
		String id1= request.getParameter("id1");//风险点
		String[] arrids = ids.split(",");
		try{
			for (int i = 0; i < arrids.length; i++) {
				yhpcYhpcdService.bulidCheckContent(Long.parseLong(id1),Long.parseLong(arrids[i]));
			}
		}catch(Exception e){
			return "绑定失败!";
		}
		// 返回结果
		return "绑定成功!";

	}
	
	/**
	 * 删除巡检内容信息
	 */
	@RequestMapping(value = "xjnrdelete/{ids}")
	@ResponseBody
	public String xjnrdelete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			yhpcYhpcdService.deleteXjnr(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 根据企业id获取企业平面图显示
	 */
	@RequestMapping(value="qypmt")
	@ResponseBody
	public String findpmt(Long qyid) {
		String url="";
		try {
			url = yhpcYhpcdService.findpmtByqyid(qyid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	
	/**
	 * 巡检内容list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "xjnralllist")
	@ResponseBody
	public Map<String, Object> xjnralllist(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("id1", request.getParameter("id1"));//隐患点id
		map.put("qyid", request.getParameter("qyid"));
		map.put("yhjb", request.getParameter("yhjb"));
		map.put("checktitle", request.getParameter("checktitle"));
		return yhpcYhpcdService.xjnralldataGrid(map);
	}
	
	/**
	 * 生成二维码图片
	 */
	@RequestMapping(value="erm")
	@ResponseBody
	public String erweima(Long id,HttpServletResponse response,HttpServletRequest request) {
		Map<String, Object> yhpcd = yhpcYhpcdService.findInforById(id);
		String text=" ";
		if(yhpcd.get("bindcontent")!=null)
			text=yhpcd.get("bindcontent").toString();
		// 取得输出流        
		String dowmloadPath = request.getSession().getServletContext().getRealPath("/")+"/download";
		String url="/download/";
		try {
			url =url+ QRCode.encode(text, null, dowmloadPath, true,"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
			
}
