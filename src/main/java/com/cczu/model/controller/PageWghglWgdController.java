package com.cczu.model.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
import com.cczu.model.entity.YHPC_CheckPoint_Content;
import com.cczu.model.service.FxgkFxxxService;
import com.cczu.model.service.WghglWgdService;
import com.cczu.model.service.YhpcYhpcdService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.QRCode;
import com.cczu.sys.system.service.BarrioService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;
import com.cczu.util.common.WordUtil;


/**
 * 网格点Controller
 * @author YZH
 */
@Controller
@RequestMapping("wghgl/wgd")
public class PageWghglWgdController extends BaseController {

	@Autowired
	private WghglWgdService wghglWgdService;
	@Autowired
	private YhpcYhpcdService yhpcYhpcdService;
	@Autowired
	private BarrioService barrioService;
	@Autowired
	private FxgkFxxxService fxgkFxxxService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "wghgl/wgd/index";
	}
	
	/**
	 * 网格点list页面 
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("wghgl_wgd_cx_qyname"));
		map.put("starttime", request.getParameter("wghgl_wgd_starttime"));
		map.put("endtime", request.getParameter("wghgl_wgd_finishtime"));
		ShiroUser user = UserUtil.getCurrentShiroUser();
		if("0".equals(user.getUsertype())){//安监
			map.put("xzqy",user.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0)
				map.put("jglx",user.getUserroleflg());
		}
		map.put("wgxzqy", request.getParameter("wghgl_wgd_cx_wgxzqy"));
		return wghglWgdService.dataGrid1(map);	
		
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		Map<String,Object> wgd = new HashMap<>();
		//生成二维码编码
		wgd.put("bindcontent",UUID.randomUUID().toString().replaceAll("-", ""));
		model.addAttribute("wgd",wgd);
		return "wghgl/wgd/form";
	}
	
	/**
	 * 添加网格点信息
	 * @param request,model
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(YHPC_CheckPointEntity entity, Model model,HttpServletRequest request) {
		String datasuccess="success";
		String bindcontent = entity.getBindcontent();
		String rfid = entity.getRfid();
		//判断绑定二维码rfid是否重复
		if(wghglWgdService.checkSameBuildContent(0,bindcontent)&&(!com.cczu.util.common.StringUtils.isEmpty(bindcontent))){
			datasuccess = "ewmerror";
		}else if(wghglWgdService.checkSameRfid(0,rfid)&&(!com.cczu.util.common.StringUtils.isEmpty(rfid))){
			datasuccess = "rfiderror";
		}else{
			wghglWgdService.addInfo(entity);
			String xjnrid = request.getParameter("xjnrid");
			if(!StringUtils.isEmpty(xjnrid)){
				String[] arrids = xjnrid.split(",");
				for (int i = 0; i < arrids.length; i++) {
					wghglWgdService.bulidCheckContent(entity.getID(),Long.parseLong(arrids[i]));
				}
			}
		}
		return datasuccess;
		
	}	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的网格点信息
		Map<String, Object> wgd = wghglWgdService.findInforById(id);
		//根据网格点id查询已绑定内容
		List<YHPC_CheckPoint_Content> bdxjnrlist = wghglWgdService.findxjnrbyid1(id);
		String xjnrid="";
		if(bdxjnrlist!=null){
		   for (YHPC_CheckPoint_Content yy : bdxjnrlist) {
			   xjnrid = xjnrid + yy.getID2() + ",";
		   }	
		}
		model.addAttribute("xjnrid",xjnrid);
		model.addAttribute("wgd", wgd);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "wghgl/wgd/form";
	}
	
	/**
	 * 修改网格点信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(YHPC_CheckPointEntity entity,  Model model,HttpServletRequest request){
		String datasuccess="success";
		String bindcontent = entity.getBindcontent();
		String rfid = entity.getRfid();
		//判断绑定二维码rfid是否重复
		if(wghglWgdService.checkSameBuildContent(entity.getID(),bindcontent)&&(!com.cczu.util.common.StringUtils.isEmpty(bindcontent))){
			datasuccess = "ewmerror";
		}else if(wghglWgdService.checkSameRfid(entity.getID(),rfid)&&(!com.cczu.util.common.StringUtils.isEmpty(rfid))){
			datasuccess = "rfiderror";
		}else{
			yhpcYhpcdService.deletexjnrbyid1(entity.getID());
			wghglWgdService.updateInfo(entity);
			String xjnrid = request.getParameter("xjnrid");
			if(!StringUtils.isEmpty(xjnrid)){
				String[] arrids = xjnrid.split(",");
				for (int i = 0; i < arrids.length; i++) {
					wghglWgdService.bulidCheckContent(entity.getID(),Long.parseLong(arrids[i]));
				}
			}
		}
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 删除网格点信息
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			yhpcYhpcdService.deletexjnrbyid1(Long.parseLong(arrids[i]));
			wghglWgdService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}	
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String, Object> wgd = wghglWgdService.findInforById(id);
		
		model.addAttribute("wgd", wgd);

		return "wghgl/wgd/view";
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("wghgl:wgd:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.put("qyname", request.getParameter("wghgl_wgd_cx_qyname"));
		map.put("starttime", request.getParameter("wghgl_wgd_starttime"));
		map.put("endtime", request.getParameter("wghgl_wgd_finishtime"));
		map.put("yt", request.getParameter("yt"));
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		wghglWgdService.exportExcel(response, map);
	}
	
	/**
	 * 显示所有列
	 */
	@RequiresPermissions("wghgl:wgd:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","wghgl/wgd/export");
		return "/common/formexcel";
	}

	/**
	 * 批量生成
	 */
	@RequestMapping(value = "plsc")
	@ResponseBody
	public String plsc(Model model) {
		String datasuccess="生成成功";
		Map<String, Object> map = new HashMap<>();
		ShiroUser user = UserUtil.getCurrentShiroUser();
		if("0".equals(user.getUsertype())){//安监
			map.put("xzqy",user.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(user.getUserroleflg()!=null&&user.getUserroleflg()!=0){
				map.put("jglx",user.getUserroleflg());
			}
		}
		//查询行政区域内未生成的网格点
		List<BIS_EnterpriseEntity> wsclist = wghglWgdService.getwsclist(map);
		if(wsclist!=null){
			for (BIS_EnterpriseEntity bis : wsclist) {
				YHPC_CheckPointEntity cp = new YHPC_CheckPointEntity();
				cp.setID1(bis.getID());
				cp.setName(bis.getM1());
				cp.setUsetype("1");
				cp.setLng(bis.getM16());
				cp.setLat(bis.getM17());
				cp.setBindcontent(UUID.randomUUID().toString().replaceAll("-", ""));
				wghglWgdService.addInfo(cp);
				wghglWgdService.plbdxjnr(cp.getID());
			}
		}
		return datasuccess;
	}	
	
	/**
	 * 获取未生成企业的企业名称和id
	 * idjson  {"id":11,"text":"企业名称"}
	 * return String
	 */
	@RequestMapping(value="idjson")
	@ResponseBody
	public String codeIdjson() {
		Map<String, Object> map = new HashMap<String, Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("0")){//安监用户
			map.put("xzqy",sessionuser.getXzqy());
			//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
				map.put("jglx",sessionuser.getUserroleflg());
		}
		return wghglWgdService.getQyIdjson(map);
	}
	
	
	//导入页面跳转
	@RequestMapping(value = "exinjump", method = RequestMethod.GET)
	public String exin(Model model) {
		model.addAttribute("action", "exin");
		return "common/importForm";
	}	

	/**
	 * 网格巡检内容list页面 
	 * @param request
	 */
	@RequestMapping(value="wgxjnrlist")
	@ResponseBody
	public Map<String, Object> getDatawgxjnr(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		return wghglWgdService.dataGridwgxjnr(map);			
	}
	
	/**
	 * 绑定巡检内容list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "xjnrlist")
	@ResponseBody
	public Map<String, Object> xjnrlist(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("jcdid", request.getParameter("jcdid"));
		map.put("xjjlid", request.getParameter("xjjlid"));
		return wghglWgdService.xjnrdataGrid(map);

	}
	
	//跳转已绑定巡检内容list
	@RequestMapping(value = "bdxjnr/{id}", method = RequestMethod.GET)
	public String bdxjnr(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		String qyid = request.getParameter("qyid");
		model.addAttribute("id1", id);
		model.addAttribute("qyid", qyid);
		return "wghgl/wgd/xjnr";
	}

	//跳转巡检内容list
	@RequestMapping(value = "xjnrcreate/{id1},{qyid}", method = RequestMethod.GET)
	public String xjnrcreate(@PathVariable("id1") Long id,@PathVariable("qyid") Long qyid, Model model,HttpServletRequest request) {
		model.addAttribute("id1", id);
		model.addAttribute("qyid", qyid);
		return "wghgl/wgd/xjnrall";
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
				wghglWgdService.bulidCheckContent(Long.parseLong(id1),Long.parseLong(arrids[i]));
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
			wghglWgdService.deleteXjnr(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 根据企业id获取企业平面图显示
	 */
	@RequestMapping(value="qypmt")
	@ResponseBody
	public String findpmt(String qyid) {
		String url="";
		try {
			url = wghglWgdService.findpmtByqyid(qyid);
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
		map.put("id1", request.getParameter("id1"));//风险点id
		map.put("qyid", request.getParameter("qyid"));
		//map.put("checktitle", request.getParameter("fxgk_xjnrall_checktitle"));
		return wghglWgdService.xjnralldataGrid(map);

	}
	
	/**
	 * 导出网格员巡检告知卡
	 */
	@RequiresPermissions("wghgl:wgd:export") 
	@RequestMapping(value = "exportgzk/{id}")
	@ResponseBody
	public String viewka(@PathVariable("id") Long id, Model model,HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		//基本信息
		Map<String,Object> remap=wghglWgdService.getExportgzk(id);
		map.put("qyname", remap.get("qyname"));
		map.put("M5", remap.get("M5"));
		map.put("M6", remap.get("M6"));
		map.put("M12", remap.get("cname"));
		map.put("M19", remap.get("M19"));
		map.put("M25", remap.get("M25"));
		map.put("wgname", remap.get("wgname"));
		// 生成二维码
		String text = remap.get("bindcontent")+"";
		String dowmloadPath = request.getSession().getServletContext().getRealPath("/")+"/download";
		map.put("ewmimage", WordUtil.getImageStr(dowmloadPath+"/"+QRCode.encode(text, dowmloadPath)));
		//风险点循环
		long qyid=Long.parseLong(remap.get("qyid")+"");
		List<Map<String,Object>> list=fxgkFxxxService.getFXByqyid(qyid);
		map.put("fxdlist", list);
		//危险标志图片循环
		List <String> list2 = new ArrayList<String>();
		if (list.size() > 0) {
			int i = 0;
			outer: for (Map<String, Object> m : list) {
				Object m17= m.get("m17");
				if (m17!=null &&StringUtils.isNotBlank(m17.toString())) {
					String[] urls = m17.toString().split(",");
					for (String u : urls) {
						if (i < 5) {
							String[] url2 = u.split("[||]");
							String image2 = WordUtil.getImageStr(request
									.getSession().getServletContext()
									.getRealPath("/")
									+ url2[0]);
							if (StringUtils.isNoneEmpty(image2)
									&& !list2.contains(image2)) {
								list2.add(image2);
								i++;
							}
						} else {
							break outer;
						}
					}
				}
			}
		}
		map.put("imagelist", list2);
		
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("usertype", 0);
		map1.put("eqxzqy", remap.get("xzqy"));
		map1.put("role", "ajtownadmin");
		List<Map<String, Object>> list1=barrioService.getWgUser(map1);
		if(list1.size()>0){
			map.put("admin", list1.get(0).get("name"));
			map.put("adminp", list1.get(0).get("phone"));
		}
		map1.put("role", "ajtown");
		List<Map<String, Object>> list3=barrioService.getWgUser(map1);
		if(list3.size()>0){
			map.put("normal", list3.get(0).get("name"));
			map.put("normalp", list3.get(0).get("phone"));
		}
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		WordUtil.ireportWord(map, "xjgzk.ftl", dowmloadPath, filename, request);
		return "/download/" + filename;
	}
}
