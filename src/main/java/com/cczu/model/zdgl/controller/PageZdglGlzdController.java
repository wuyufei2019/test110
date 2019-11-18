package com.cczu.model.zdgl.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cczu.model.zdgl.entity.ZDGL_GLZDEntityHistory;
import com.cczu.model.zdgl.service.ZdglGlzdHistoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cczu.model.zdgl.entity.ZDGL_GLZDEntity;
import com.cczu.model.zdgl.service.ZdglGlzdService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.wordToPDF;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.MessageUtil;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;

/**
 * 制度管理-安全管理制度controller
 */
@Controller
@RequestMapping("zdgl/glzd")
public class PageZdglGlzdController extends BaseController {

	@Autowired
	private ZdglGlzdService zdglGlzdService;
	@Autowired
	private UserService userService;
	@Autowired
    private ZdglGlzdHistoryService zdglGlzdHistoryService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		model.addAttribute("qyid", request.getParameter("qyid"));
		return "zdgl/aqglzd/glzd/index";
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("zdgl_glzd_m1"));
		
		String qyid = request.getParameter("qyid");
		if(StringUtils.isEmpty(qyid)){
			map.put("qyid", UserUtil.getCurrentUser().getId2());
		}else{
			map.put("qyid", qyid);
		}
		return zdglGlzdService.dataGrid(map);
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
			zdglGlzdService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zdgl:glzd:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		Map<String,Object> map = new HashMap<>();
		map.put("m2", "AQZD_"+DateUtils.getDateRandom() + "_" + new Random().nextInt(100));
		model.addAttribute("glzd", map);
		model.addAttribute("action", "create");
		return "zdgl/aqglzd/glzd/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("zdgl:glzd:add")
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(ZDGL_GLZDEntity glzd, Model model,HttpServletRequest request) {
		String datasuccess="success";
		//设置pdf，swg
		String url=glzd.getM6();
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
			glzd.setM15(purl);
			glzd.setM16(surl);
		}
		zdglGlzdService.addInfo(glzd);

		Map<String,Object>  msgmap = new HashMap<String,Object>();
		msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zdgl/zdglmain.jsp");
		msgmap.put(Message.MSGTARGET_PC,"zdgl/glzd/update/sh/"+glzd.getID());
		List<User> userlist = userService.findUserByPermission(UserUtil.getCurrentShiroUser().getQyid(), "zdgl:glzd:sh");
		for (User user : userlist) {
			MessageUtil.sendMsg(UserUtil.getCurrentShiroUser().getId()+"", user.getId()+"", "安全管理制度审核", Message.MessageType.DSH.getMsgType(),JSON.toJSONString(msgmap), "安全管理制度审核");
		}
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{type}/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, @PathVariable("type") String type, Model model) {
		ZDGL_GLZDEntity glzd = zdglGlzdService.find(id);
		model.addAttribute("glzd", glzd);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("type", type);
		return "zdgl/aqglzd/glzd/form";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(String role,ZDGL_GLZDEntity glzd, Model model,HttpServletRequest request){
		String datasuccess="success";
		String url=glzd.getM6();
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
			glzd.setM15(purl);
			glzd.setM16(surl);
		}else{
			glzd.setM15(null);
			glzd.setM16(null);
		}
		if(!StringUtils.isEmpty(role)){
			if(role.equals("2")){
				//审核
				glzd.setM9(UserUtil.getCurrentUser().getId().toString());
				Timestamp t=DateUtils.getSysTimestamp();
				glzd.setM11(t);
				
				Map<String,Object>  msgmap = new HashMap<String,Object>();
				msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zdgl/zdglmain.jsp");
				msgmap.put(Message.MSGTARGET_PC,"zdgl/glzd/update/pz/"+glzd.getID());
				List<User> userlist = userService.findUserByPermission(UserUtil.getCurrentShiroUser().getQyid(), "zdgl:glzd:sp");
				for (User user : userlist) {
					MessageUtil.sendMsg(UserUtil.getCurrentShiroUser().getId()+"", user.getId()+"", "安全管理制度批准", Message.MessageType.DSP.getMsgType(),JSON.toJSONString(msgmap), "安全管理制度批准");
				}
			}else if(role.equals("3")){
				//审批
				glzd.setM12(UserUtil.getCurrentUser().getId().toString());
				Timestamp t=DateUtils.getSysTimestamp();
				glzd.setM14(t);
			}
		}
		zdglGlzdService.updateInfo(glzd);
		//返回结果
		return datasuccess;
	}


	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> glzd = zdglGlzdService.findById(id);
		model.addAttribute("glzd", glzd);
		return "zdgl/aqglzd/glzd/view";
	}
	
	/**
	 * 导出excel
	 */
	@RequiresPermissions("zdgl:glzd:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m1", request.getParameter("zdgl_glzd_m1"));
		map.put("qyid", UserUtil.getCurrentUser().getId2());
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		zdglGlzdService.exportExcel(response, map);
	}

	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequiresPermissions("zdgl:glzd:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","zdgl/glzd/export");
		return "/common/formexcel";
	}
	
	/**
	 * 在线查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view2/{id}", method = RequestMethod.GET)
	public String view2(@PathVariable("id") Long id, Model model) {
		ZDGL_GLZDEntity glzd = zdglGlzdService.find(id);		
		model.addAttribute("glzd", glzd);
		return "zdgl/aqglzd/glzd/view2";
	}

    /**
     * 修订页面跳转
     * @param id,model
     */
    @RequestMapping(value = "revise/{id}", method = RequestMethod.GET)
    public String revise(@PathVariable("id") Long id, Model model) {
        ZDGL_GLZDEntity glzd = zdglGlzdService.find(id);
        if (!zdglGlzdHistoryService.findById2(glzd.getID())){//修订历史记录中没有对应记录时增加原始记录
            zdglGlzdHistoryService.addInfo(zdglGlzdService.changeModel(glzd));
        }
        model.addAttribute("glzd", glzd);
        //返回页面
        model.addAttribute("action", "revise");
        return "zdgl/aqglzd/glzd/form";
    }

    /**
     * 修订信息
     * @param request,model
     */
    @RequestMapping(value = "revise")
    @ResponseBody
    public String revise(ZDGL_GLZDEntity glzd, Model model,HttpServletRequest request){
        String datasuccess="success";
        String url=glzd.getM6();
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
            glzd.setM15(purl);
            glzd.setM16(surl);
        }else{
            glzd.setM15(null);
            glzd.setM16(null);
        }
        zdglGlzdService.updateInfo(zdglGlzdService.zdglGlzdEntityChange(glzd));
        //增加修订记录
        zdglGlzdHistoryService.addInfo(zdglGlzdService.changeModel(glzd));
        //返回结果
        return datasuccess;
    }

    /**
     * 列表显示页面
     * @param model
     */
    @RequestMapping(value="hisindex/{id}")
    public String hisindex(@PathVariable("id") Long id,Model model) {
        model.addAttribute("id2", id);
        return "zdgl/aqglzd/glzd/hisindex";
    }

    /**
     * list页面
     * @param request
     */
    @RequestMapping(value="hislist")
    @ResponseBody
    public Map<String, Object> getHistoryData(HttpServletRequest request) {
        Map<String, Object> map = getPageMap(request);

        String id2 = request.getParameter("id2");
        map.put("id2",id2);
        return zdglGlzdHistoryService.dataGrid(map);
    }

    /**
     * 修订记录查看页面跳转
     * @param id,model
     */
    @RequestMapping(value = "hisview/{id}", method = RequestMethod.GET)
    public String hisview(@PathVariable("id") Long id, Model model) {
        Map<String, Object> glzd = zdglGlzdHistoryService.findById(id);
        model.addAttribute("glzd", glzd);
        model.addAttribute("his", true);
        return "zdgl/aqglzd/glzd/view";
    }

	/**
	 * 作废
	 * @param request,model
	 */
	@RequestMapping(value = "cancel/{id}")
	@ResponseBody
	public String cancel(@PathVariable("id") Long id,  Model model){
		String datasuccess="操作成功";
		ZDGL_GLZDEntity glzd = zdglGlzdService.find(id);
		glzd.setZt("-1");
		glzd.setS2(new Timestamp(new java.util.Date().getTime()));

		zdglGlzdService.updateInfo(glzd);
		//返回结果
		return datasuccess;
	}
}
