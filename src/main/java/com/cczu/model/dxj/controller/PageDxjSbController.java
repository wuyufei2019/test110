package com.cczu.model.dxj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.dxj.entity.DXJ_SbEntity;
import com.cczu.model.dxj.service.DxjSbService;
import com.cczu.model.dxj.service.DxjSbxjdService;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.QRCode;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;


/**
 * 设备管理Controller
 * @author ZPC
 */
@Controller
@RequestMapping("dxj/sb")
public class PageDxjSbController extends BaseController {

	@Autowired
	private DxjSbService dxjSbService;
	@Autowired
	private DxjSbxjdService dxjSbxjdService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	
	/**
	 * 默认页面
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
		}else {//非企业用户页面
			model.addAttribute("type","2");//其他
		}
		return "dxj/dxjsb/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("qyname",request.getParameter("dxj_dxjsb_qyname"));
		map.put("sbname",request.getParameter("dxj_dxjsb_sbname"));
		return dxjSbService.dataGrid(map);
	}
	
	@RequestMapping(value="sblist")
	@ResponseBody
	public List<Map<String, Object>> getSblist(Model model) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.putAll(getAuthorityMap());
		return dxjSbService.getsblist(map);
	}
	
	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		Map<String,Object> sb = new HashMap<>();
		//生成二维码编码
		sb.put("bindcontent",UUID.randomUUID().toString().replaceAll("-", ""));
		model.addAttribute("sb", sb);
		model.addAttribute("action", "create");
		return "dxj/dxjsb/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(DXJ_SbEntity sb, HttpServletRequest request) {
		ShiroUser su = UserUtil.getCurrentShiroUser();
		sb.setID1(su.getQyid());
		return dxjSbService.addInfo(sb,0);
	}
	
	/**
	 * 删除
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			dxjSbxjdService.deleteById2(Long.parseLong(aids[i]));
			dxjSbService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		DXJ_SbEntity sb  = dxjSbService.findById(id);
		model.addAttribute("sb", sb);
		//返回页面
		model.addAttribute("action", "update");
		return "dxj/dxjsb/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(DXJ_SbEntity sb, Model model){
		return dxjSbService.addInfo(sb,sb.getID());
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		DXJ_SbEntity sb = dxjSbService.findById(id);
		model.addAttribute("sb", sb);
		return "dxj/dxjsb/view";
	}
	
	/**
	 * 生成二维码图片
	 */
	@RequestMapping(value="erm")
	@ResponseBody
	public String erweima(Long id,HttpServletResponse response,HttpServletRequest request) {
		DXJ_SbEntity sb = dxjSbService.findById(id);
		String text=" ";
		if(!StringUtils.isEmpty(sb.getBindcontent()))
			text=sb.getBindcontent();
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
     * 树形结构显示设备
     * @param {json}
     */
    @RequestMapping(value="sbxx" , method = RequestMethod.GET)
    @ResponseBody
    public String gbtjson() {
        ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
        return dxjSbService.gbtjson(sessionuser.getQyid());
    }
}
