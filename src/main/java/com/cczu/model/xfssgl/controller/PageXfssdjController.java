package com.cczu.model.xfssgl.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.model.xfssgl.entity.Tree_XfssdjEntity;
import com.cczu.model.xfssgl.entity.XFSSGL_XfssdjEntity;
import com.cczu.model.xfssgl.service.XfssglXfssdjService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.QRCode;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;


/**
 * 消防设施管理Controller
 * @author wbth
 */
@Controller
@RequestMapping("xfssgl/xfssdj")
public class PageXfssdjController extends BaseController {

	@Autowired
	private XfssglXfssdjService xfssglXfssdjService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	
	/**
	 * 跳转到列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "xfssgl/xfssdj/index";
	}

	/**
	 * list集合(JSON)
	 */
	@RequiresPermissions("xfssgl:xfssdj:view")
	@RequestMapping(value="json") 
	@ResponseBody
	public String DataList(Model model){
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		List<Tree_XfssdjEntity> list= xfssglXfssdjService.getWltson(sessionuser.getQyid());
		
		return JsonMapper.getInstance().toJson(list);
	}
	
	/**
	 * 消防设施list页面 
	 * @param request
	 */
	@RequiresPermissions("xfssgl:xfssdj:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request, Model model) {
		Map<String, Object> map = getPageMap(request);
		map.put("category", request.getParameter("xfssgl_xfssdj_category"));
		map.put("name", request.getParameter("xfssgl_xfssdj_name"));
		map.put("yt", request.getParameter("yt"));
		map.put("pid", request.getParameter("pid"));
		map.putAll(getAuthorityMap());//企业用户登录会有qyid,集团用户登录会有fid,没有qyid
		return xfssglXfssdjService.dataGrid(map);	
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("xfssgl:xfssdj:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		Map<String,Object> xfssdj = new HashMap<>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		//如果是企业，获取企业平面图
		if(sessionuser.getUsertype().equals("1")){
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			xfssdj.put("pmt", StringUtils.defaultString(be.getM33_3()));
			xfssdj.put("id1", sessionuser.getQyid());
			//获取企业地图坐标
			model.addAttribute("lng", be.getM16());
			model.addAttribute("lat", be.getM17());
		}
		//生成二维码编码
		xfssdj.put("bindcontent",UUID.randomUUID().toString().replaceAll("-", ""));
		model.addAttribute("action", "create");
		model.addAttribute("xfssdj", xfssdj);
		model.addAttribute("pid", request.getParameter("pid"));
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "xfssgl/xfssdj/form";
	}

	/**
	 * 添加消防设施信息
	 * @param request,model
	 */
	@RequiresPermissions("xfssgl:xfssdj:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(XFSSGL_XfssdjEntity entity,HttpServletRequest request) {
		Timestamp t=DateUtils.getSysTimestamp();
		Long pid = Long.parseLong(request.getParameter("pid"));
		Map<String, Object> map = xfssglXfssdjService.findInforById(pid);
		//通过pid找到它的icon(图标)，再将icon设置到该类别对应的设施中
		entity.setIcon(map.get("icon").toString());
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){
			entity.setID1(sessionuser.getQyid());;
		}
		/*String bdnrids=request.getParameter("bdnrids");*/
		return xfssglXfssdjService.addInfo(entity);
		
	}	

	/**
	 * 添加设施类型页面跳转
	 * @param model
	 */
	@RequiresPermissions("xfssgl:xfssdj:add")
	@RequestMapping(value = "createLb" , method = RequestMethod.GET)
	public String createCategory(Model model) {
		model.addAttribute("bis", new XFSSGL_XfssdjEntity());
		model.addAttribute("action", "createLb");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "xfssgl/xfssdj/form2";
	}
	
	/**
	 * 添加消防设施类别
	 * @param request,model
	 */
	@RequiresPermissions("xfssgl:xfssdj:add")
	@RequestMapping(value = "createLb")
	@ResponseBody
	public String createCategory(XFSSGL_XfssdjEntity entity,HttpServletRequest request) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){
			entity.setID1(sessionuser.getQyid());;
		}
		return xfssglXfssdjService.addInfo(entity);
		
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("xfssgl:xfssdj:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的隐患排查点信息
		Map<String, Object> entity = xfssglXfssdjService.findInforById(id);
		model.addAttribute("entity", entity);
		
		//根据企业id获取企业对象
		BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(StringUtils.toLong(entity.get("id1")));
		//获取企业平面图
		entity.put("pmt", StringUtils.defaultString(be.getM33_3()));
		//如果无隐患点坐标获取企业坐标
		if(entity.get("lng")==null||entity.get("lng").equals("")){
			model.addAttribute("lng", be.getM16());
			model.addAttribute("lat", be.getM17());
		}
		 
		//返回页面
		model.addAttribute("action", "xfssupdate");
		model.addAttribute("xfssdj", entity);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		return "xfssgl/xfssdj/form";
	}
	
	/**
	 * 修改消防设施信息
	 * @param request,model
	 */
	@RequiresPermissions("xfssgl:xfssdj:update")
	@RequestMapping(value = "xfssupdate")
	@ResponseBody
	public String xfssupdate(XFSSGL_XfssdjEntity entity,HttpServletRequest request){
		Timestamp t= DateUtils.getSysTimestamp();
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		//必须设置，否则会出错
		entity.setS2(t);
		entity.setS3(0);
		entity.setID1(sessionuser.getQyid());
		String result = xfssglXfssdjService.updateInfo(entity);
		return result;
	}

	/**
	 * 修改设施类别页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("xfssgl:xfssdj:update")
	@RequestMapping(value = "updateLb/{id}", method = RequestMethod.GET)
	public String updateLb(@PathVariable("id") Long id, Model model) {
		//查询选择的隐患排查点信息
		Map<String, Object> entity = xfssglXfssdjService.findInforById(id);
		model.addAttribute("entity", entity);
		//返回页面
		model.addAttribute("action", "updateLb");
		return "xfssgl/xfssdj/form2";
	}
	
	/**
	 * 修改消防设施信息
	 * @param request,model
	 */
	@RequiresPermissions("xfssgl:xfssdj:update")
	@RequestMapping(value = "updateLb")
	@ResponseBody
	public String updatelb(XFSSGL_XfssdjEntity entity,HttpServletRequest request){
		String datasuccess = "failed";
		Timestamp t= DateUtils.getSysTimestamp();
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		//必须设置，否则会出错
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setID1(sessionuser.getQyid());
		xfssglXfssdjService.updateInfo(entity);
		datasuccess="success";
		return datasuccess;
	}
	
	/**
	 * 删除消防设施信息
	 */
	@RequiresPermissions("xfssgl:xfssdj:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="success";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			xfssglXfssdjService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("xfssgl:xfssdj:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String, Object> entity = xfssglXfssdjService.findInforById(id);
		//根据企业id获取企业对象
		BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(StringUtils.toLong(entity.get("id1")));
		//获取企业平面图
		entity.put("pmt", StringUtils.defaultString(be.getM33_3()));
		model.addAttribute("entity", entity);

		return "xfssgl/xfssdj/view";
	}
	
	/**
	 * 生成二维码图片
	 */
	@RequestMapping(value="erm")
	@ResponseBody
	public String erweima(Long id,HttpServletResponse response,HttpServletRequest request) {
		Map<String, Object> xfss = xfssglXfssdjService.findInforById(id);
		String text=" ";
		if(xfss.get("bindcontent")!=null)
			text=xfss.get("bindcontent").toString();
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
	
	/**
	 * 消防设施图标下拉选择
	 */
	@RequestMapping("geticons")
	@ResponseBody
	public String getBigIcons(HttpServletRequest request) {
		
		JSONArray  alist = new JSONArray();
		
		String bathpath = request.getServletContext().getRealPath("/");
		
		String imagedir = bathpath+"\\static\\model\\images\\xfssgl";
		
		File filedir = new File(imagedir);
		
		if(filedir.exists() && filedir.isDirectory())
		{
			File[] filelist = filedir.listFiles();
			if(filelist.length > 0)
			{
				for(File f : filelist)
				{
					JSONObject  result = new JSONObject();
					result.put("text", f.getName());
					result.put("value", f.getName());
					alist.add(result);
				}
			}
		}
		return alist.toJSONString();
	}
	
	/**
	 * 显示所有列
	 * @param id,model 
	 */
	@RequiresPermissions("xfssgl:xfssdj:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","xfssgl/xfssdj/export");
		return "common/formexcel";
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("bis:cjxx:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		xfssglXfssdjService.exportExcel(response, map);
	}
	
}
