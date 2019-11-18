package com.cczu.model.zdgl.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cczu.model.zdgl.entity.ZDGL_CDJSEntity;
import com.cczu.model.zdgl.entity.ZDGL_WJFBEntity;
import com.cczu.model.zdgl.service.ZdglWjcdjsService;
import com.cczu.model.zdgl.service.ZdglWjfbService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.wordToPDF;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.DepartmentService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.MessageUtil;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;

/**
 * 制度管理-文件发布controller
 */
@Controller
@RequestMapping("zdgl/wjfb")
public class PageZdglWjfbController extends BaseController {

	@Autowired
	private ZdglWjfbService zdglWjfbService;
	@Autowired
	private ZdglWjcdjsService zdglWjcdjsService;
	@Autowired
	private DepartmentService departmentService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
			return "zdgl/aqwjgl/wjfb/index";
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequiresPermissions("zdgl:wjfb:view")
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("zdgl_wjfb_m1"));
		map.put("m3", request.getParameter("zdgl_wjfb_m3"));
		map.put("m4", request.getParameter("zdgl_wjfb_m4"));
		map.put("qyid", UserUtil.getCurrentUser().getId2());
		return zdglWjfbService.dataGrid(map);
	}

	/**
	 * 删除文件
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			//删除原先的传递接收数据
			zdglWjcdjsService.deleteByid1(Long.parseLong(aids[i]));
			zdglWjfbService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}

	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zdgl:wjfb:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		Map<String,Object> map = new HashMap<>();
		map.put("m2", "AQWJ_"+DateUtils.getDateRandom() + "_" + new Random().nextInt(100));
		model.addAttribute("wjfb", map);
 		model.addAttribute("action", "create");
		return "zdgl/aqwjgl/wjfb/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("zdgl:wjfb:add")
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(ZDGL_WJFBEntity wjfb, Model model,HttpServletRequest request) {
		String datasuccess="success";
		//设置pdf，swg
		String url=wjfb.getM6();
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
			wjfb.setM14(purl);
			wjfb.setM15(surl);
		}
		zdglWjfbService.addInfo(wjfb);
		ShiroUser shirouser = UserUtil.getCurrentShiroUser();
		
		Map<String,Object>  msgmap = new HashMap<String,Object>();
		msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zdgl/zdglmain.jsp");
		msgmap.put(Message.MSGTARGET_PC,"zdgl/wjfb/update/sh/"+wjfb.getID());
		Message msg = new Message(null, shirouser.getId()+"", "安全文件发布审核", Message.MessageType.DSH.getMsgType(), "安全文件发布审核",JSON.toJSONString(msgmap));
		MessageUtil.sendMsgByPermission(msg, shirouser.getQyid(), "zdgl:wjfb:sh");
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{type}/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, @PathVariable("type") String type, Model model) {
		ZDGL_WJFBEntity wjfb = zdglWjfbService.find(id);
		model.addAttribute("wjfb", wjfb);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("type", type);
		return "zdgl/aqwjgl/wjfb/form";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(String role,ZDGL_WJFBEntity wjfb, Model model,HttpServletRequest request){
		String datasuccess="success";	
		String url=wjfb.getM6();
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
			wjfb.setM14(purl);
			wjfb.setM15(surl);
		}else{
			wjfb.setM14(null);
			wjfb.setM15(null);
		}
		
		if(!StringUtils.isEmpty(role)){
			if(role.equals("2")){
				//审核
				wjfb.setM7(UserUtil.getCurrentUser().getId().toString());
				Timestamp t=DateUtils.getSysTimestamp();
				wjfb.setM9(t);
				//发送消息
				ShiroUser shirouser = UserUtil.getCurrentShiroUser();
				Map<String,Object>  msgmap = new HashMap<String,Object>();
				msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zdgl/zdglmain.jsp");
				msgmap.put(Message.MSGTARGET_PC,"zdgl/wjfb/update/pz/"+wjfb.getID());
				Message msg = new Message(null, shirouser.getId()+"", "安全文件发布批准", Message.MessageType.DSP.getMsgType(), "安全文件发布批准",JSON.toJSONString(msgmap));
				MessageUtil.sendMsgByPermission(msg, shirouser.getQyid(), "zdgl:wjfb:sp");
			}else if(role.equals("3")){
				//删除原先的传递接收数据
				zdglWjcdjsService.deleteByid1(wjfb.getID());
				//审批
				wjfb.setM10(UserUtil.getCurrentUser().getId().toString());
				Timestamp t=DateUtils.getSysTimestamp();
				wjfb.setM12(t);
				if(wjfb.getM11().equals("1")){
					//审核通过
					if(!StringUtils.isEmpty(wjfb.getM13())){
						String[] bmids = wjfb.getM13().split(",");
						Map<String,Object>  msgmap = new HashMap<String,Object>();
						msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zdgl/zdglmain.jsp");
						msgmap.put(Message.MSGTARGET_PC,"zdgl/wjfb/view2/"+wjfb.getID());
						for(int i=0;i<bmids.length;i++){
							//添加传递接收
							List<User> list = zdglWjfbService.findBybmid(Long.parseLong(bmids[i]));
							if(list != null){
								for (User user : list) {
									ZDGL_CDJSEntity cdjs = new ZDGL_CDJSEntity();
									cdjs.setID1(wjfb.getID());
									cdjs.setM1(user.getId().toString());
									cdjs.setM2("0");
									cdjs.setM3("0");
									zdglWjcdjsService.addinfo(cdjs);
									MessageUtil.sendMsg(user.getId().toString(), UserUtil.getCurrentShiroUser().getId()+"", "你有一份新文件待查阅！", Message.MessageType.XWJ.getMsgType(),JSON.toJSONString(msgmap), "你有一份新文件("+wjfb.getM1()+")待查阅！");
								}
							}
						}
					}
				}
			}
		}
		zdglWjfbService.updateInfo(wjfb);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> wjfb = zdglWjfbService.findById(id);
		model.addAttribute("wjfb", wjfb);
		return "zdgl/aqwjgl/wjfb/view";
	}
	
	/**
	 * 在线查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view2/{id}", method = RequestMethod.GET)
	public String view2(@PathVariable("id") Long id, Model model) {
		ZDGL_WJFBEntity wjfb = zdglWjfbService.find(id);		
		model.addAttribute("wjfb", wjfb);
		return "zdgl/aqwjgl/wjfb/view2";
	}
}
