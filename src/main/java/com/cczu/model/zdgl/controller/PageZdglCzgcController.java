package com.cczu.model.zdgl.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cczu.model.zdgl.service.ZdglCzgcHistoryService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cczu.model.zdgl.entity.ZDGL_CZGCEntity;
import com.cczu.model.zdgl.service.ZdglCzgcService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.wordToPDF;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.MessageUtil;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;

/**
 * 制度管理-安全操作规程controller
 */
@Controller
@RequestMapping("zdgl/czgc")
public class PageZdglCzgcController extends BaseController {

	@Autowired
	private ZdglCzgcService zdglCzgcService;
	@Autowired
	private UserService userService;
	@Autowired
    private ZdglCzgcHistoryService zdglCzgcHistoryService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request) {
		model.addAttribute("qyid", request.getParameter("qyid"));
		return "zdgl/aqczgc/czgc/index";
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("zdgl_czgc_m1"));
		String qyid = request.getParameter("qyid");
		if(StringUtils.isEmpty(qyid)){
			map.put("qyid", UserUtil.getCurrentUser().getId2());
		}else{
			map.put("qyid", qyid);
		}
		return zdglCzgcService.dataGrid(map);
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
			zdglCzgcService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequiresPermissions("zdgl:czgc:add")
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		Map<String,Object> map = new HashMap<>();
		map.put("m2", "CZGC_"+DateUtils.getDateRandom() + "_" + new Random().nextInt(100));
		model.addAttribute("czgc", map);
		model.addAttribute("action", "create");
		return "zdgl/aqczgc/czgc/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequiresPermissions("zdgl:czgc:add")
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(ZDGL_CZGCEntity czgc, Model model,HttpServletRequest request) {
		String datasuccess="success";
		//设置pdf，swg
		String url=czgc.getM6();
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
			czgc.setM15(purl);
			czgc.setM16(surl);
		}
		zdglCzgcService.addInfo(czgc);
		
		Map<String,Object>  msgmap = new HashMap<String,Object>();
		msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zdgl/zdglmain.jsp");
		msgmap.put(Message.MSGTARGET_PC,"zdgl/czgc/update/sh/"+czgc.getID());
		List<User> userlist = userService.findUserByPermission(UserUtil.getCurrentShiroUser().getQyid(), "zdgl:czgc:sh");
		for (User user : userlist) {
			MessageUtil.sendMsg(UserUtil.getCurrentShiroUser().getId()+"", user.getId()+"", "安全操作规程审核", Message.MessageType.DSH.getMsgType(),JSON.toJSONString(msgmap), "安全操作规程审核");
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
		ZDGL_CZGCEntity czgc = zdglCzgcService.find(id);
		model.addAttribute("czgc", czgc);
		//返回页面
		model.addAttribute("action", "update");
		model.addAttribute("type", type);
		return "zdgl/aqczgc/czgc/form";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(String role,ZDGL_CZGCEntity czgc, Model model,HttpServletRequest request){
		String datasuccess="success";	
		String url=czgc.getM6();
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
			czgc.setM15(purl);
			czgc.setM16(surl);
		}else{
			czgc.setM15(null);
			czgc.setM16(null);
		}
		if(!StringUtils.isEmpty(role)){
			if(role.equals("2")){
				//审核
				czgc.setM9(UserUtil.getCurrentUser().getId().toString());
				Timestamp t=DateUtils.getSysTimestamp();
				czgc.setM11(t);
				
				Map<String,Object>  msgmap = new HashMap<String,Object>();
				msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/zdgl/zdglmain.jsp");
				msgmap.put(Message.MSGTARGET_PC,"zdgl/czgc/update/pz/"+czgc.getID());
				List<User> userlist = userService.findUserByPermission(UserUtil.getCurrentShiroUser().getQyid(), "zdgl:czgc:sp");
				for (User user : userlist) {
					MessageUtil.sendMsg(UserUtil.getCurrentShiroUser().getId()+"", user.getId()+"", "安全操作规程批准", Message.MessageType.DSP.getMsgType(),JSON.toJSONString(msgmap), "安全操作规程批准");
				}
			}else if(role.equals("3")){
				//审批
				czgc.setM12(UserUtil.getCurrentUser().getId().toString());
				Timestamp t=DateUtils.getSysTimestamp();
				czgc.setM14(t);
			}
		}
		zdglCzgcService.updateInfo(czgc);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> czgc = zdglCzgcService.findById(id);
		model.addAttribute("czgc", czgc);
		return "zdgl/aqczgc/czgc/view";
	}
	

	/**
	 * 导出excel
	 */
	@RequiresPermissions("zdgl:czgc:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m1", request.getParameter("zdgl_czgc_m1"));
		map.put("qyid", UserUtil.getCurrentUser().getId2());
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		zdglCzgcService.exportExcel(response, map);
	}

	/**
	 * 显示所有列
	 * @param model
	 */
	@RequiresPermissions("zdgl:czgc:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","zdgl/czgc/export");
		return "/common/formexcel";
	}
	
	/**
	 * 在线查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view2/{id}", method = RequestMethod.GET)
	public String view2(@PathVariable("id") Long id, Model model) {
		ZDGL_CZGCEntity czgc = zdglCzgcService.find(id);		
		model.addAttribute("czgc", czgc);
		return "zdgl/aqczgc/czgc/view2";
	}

    /**
     * 修订页面跳转
     * @param id,model
     */
    @RequestMapping(value = "revise/{id}", method = RequestMethod.GET)
    public String revise(@PathVariable("id") Long id, Model model) {
        ZDGL_CZGCEntity czgc = zdglCzgcService.find(id);
        if (!zdglCzgcHistoryService.findById2(czgc.getID())){//修订历史记录中没有对应记录时增加原始记录
            zdglCzgcHistoryService.addInfo(zdglCzgcService.changeModel(czgc));
        }
        model.addAttribute("czgc", czgc);
        //返回页面
        model.addAttribute("action", "revise");
        return "zdgl/aqczgc/czgc/form";
    }

    /**
     * 修订信息
     * @param request,model
     */
    @RequestMapping(value = "revise")
    @ResponseBody
    public String revise(ZDGL_CZGCEntity czgc, Model model,HttpServletRequest request){
        String datasuccess="success";
        String url=czgc.getM6();
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
            czgc.setM15(purl);
            czgc.setM16(surl);
        }else{
            czgc.setM15(null);
            czgc.setM16(null);
        }
        zdglCzgcService.updateInfo(zdglCzgcService.zdglczgcEntityChange(czgc));
        //增加修订记录
        zdglCzgcHistoryService.addInfo(zdglCzgcService.changeModel(czgc));
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
        return "zdgl/aqczgc/czgc/hisindex";
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
        return zdglCzgcHistoryService.dataGrid(map);
    }

    /**
     * 修订记录查看页面跳转
     * @param id,model
     */
    @RequestMapping(value = "hisview/{id}", method = RequestMethod.GET)
    public String hisview(@PathVariable("id") Long id, Model model) {
        Map<String, Object> czgc = zdglCzgcHistoryService.findById(id);
        model.addAttribute("czgc", czgc);
        model.addAttribute("his", true);
        return "zdgl/aqczgc/czgc/view";
    }

    //导入页面跳转
   	@RequestMapping(value = "exinjump", method = RequestMethod.GET)
   	public String exin(Model model) {
   		model.addAttribute("action", "exin");
   		return "common/importForm";
   	}
   	
   	/**
   	 * 导入
   	 * @param response
   	 * @param request
   	 * @return
   	 */
   	@RequestMapping(value = "exin")
   	@ResponseBody
   	public String expiExcel(HttpServletResponse response, HttpServletRequest request) {
   		Map<String, Object> map = uploadExcel(request, response);
   		Map<String, Object> resultmap = new HashMap<String, Object>();
   		if (map.get("content") != null) {
   			resultmap = zdglCzgcService.exinExcel(map);
   		} else {
   			resultmap.put("returncode", -2);
   		}
   		return JsonMapper.toJsonString(resultmap);
   	}
}
