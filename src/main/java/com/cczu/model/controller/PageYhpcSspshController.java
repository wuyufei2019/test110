package com.cczu.model.controller;

import java.sql.Timestamp;
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
import com.cczu.model.entity.YHPC_CheckHiddenInfoApproveEntity;
import com.cczu.model.entity.YHPC_CheckHiddenInfoEntity;
import com.cczu.model.service.YhpcSspService;
import com.cczu.model.service.YhpcSspshService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.TargetPointUtil;
import com.cczu.sys.system.utils.UserUtil;


/**
 * 随手拍审核Controller
 * @author zpc
 */
@Controller
@RequestMapping("yhpc/sspsh")
public class PageYhpcSspshController extends BaseController {

	@Autowired
	private YhpcSspshService yhpcSspshService;
	@Autowired
	private YhpcSspService yhpcSspService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	
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
		}else {//非企业用户页面
			model.addAttribute("type","2");//其他
		}
		return "yhpc/sspsh/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("qyname", request.getParameter("yhpc_sspsh_qyname"));//企业名称
		map.put("starttime", request.getParameter("yhpc_sspsh_starttime"));//创建开始时间
		map.put("finishtime", request.getParameter("yhpc_sspsh_finishtime"));//创建结束时间
		map.put("approvestatue", request.getParameter("shzt"));//审核状态
		map.put("dangerlevel", request.getParameter("yhdj"));//隐患等级
		return yhpcSspshService.dataGrid(map);
	}
	
	/**
	 * 删除巡检记录信息
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			yhpcSspshService.deleteById(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> sspsh = yhpcSspshService.findInforById(id);
		model.addAttribute("sspsh", sspsh);
		return "yhpc/sspsh/view";
	}
	
	/**
	 * 审核页面跳转
	 * @param model
	 */
	@RequestMapping(value = "sh/{id}" , method = RequestMethod.GET)
	public String sh(@PathVariable("id") Long id,Model model) {
		Map<String,Object> sspsh = yhpcSspshService.findInforById(id);
		model.addAttribute("sspsh", sspsh);
		model.addAttribute("action", "sh");
		return "yhpc/sspsh/form";
	}
	
	/**
	 * 添加审核信息
	 * @param request,model
	 */
	@RequestMapping(value = "sh")
	@ResponseBody
	public String sh(String hiddentype,String violationlevel,YHPC_CheckHiddenInfoApproveEntity sspsh, Model model) {
		String datasuccess="success";
		sspsh.setApproveduser((long)UserUtil.getCurrentUser().getId());
		sspsh.setApprovestatue("1");
		yhpcSspshService.updatesspsh(sspsh);
		if(!sspsh.getDangerlevel().equals("0")){
			TargetPointUtil.sendTp(UserUtil.getCurrentUser().getId2(), sspsh.getUserid(), "2", "随手拍审核通过，获得");
			//添加随手拍
			YHPC_CheckHiddenInfoEntity ssp = new YHPC_CheckHiddenInfoEntity();
			ssp.setSechandletime(sspsh.getSechandletime());
			ssp.setDangerphoto(sspsh.getDangerphoto());
			ssp.setCreatetime(sspsh.getCreatetime());
			ssp.setDangerdesc(sspsh.getDangerdesc());
			ssp.setQyid(sspsh.getQyid());
			ssp.setDangerstatus(sspsh.getDangerstatus());
			ssp.setDangerorigin(sspsh.getDangerorigin());
			ssp.setUserid(sspsh.getUserid());
			ssp.setApproveduser(sspsh.getApproveduser());
			ssp.setDangerlevel(sspsh.getDangerlevel());
			ssp.setHandlepersons(sspsh.getHandlepersons());
			//ssp.setHiddentype(hiddentype);
			//ssp.setViolationlevel(violationlevel);
			yhpcSspService.addssp(ssp);
		}
		return datasuccess;
	}	
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "yhpc/sspsh/addform";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public String createSub(YHPC_CheckHiddenInfoApproveEntity sspsh, Model model) {
		String datasuccess="success";
		User user = UserUtil.getCurrentUser();
		Timestamp t=DateUtils.getSysTimestamp();
		sspsh.setQyid(user.getId2());
		sspsh.setCreatetime(t);
		sspsh.setDangerorigin("3");
		sspsh.setDangerstatus("0");
		sspsh.setApprovestatue("0");
		sspsh.setUserid((long)user.getId());
		//处理照片地址
		if(sspsh.getDangerphoto()!=null){
			String dangerphoto = sspsh.getDangerphoto();
			dangerphoto = dangerphoto.replace(",","");
			sspsh.setDangerphoto(dangerphoto);
		}
		yhpcSspshService.addsspsh(sspsh);
		return datasuccess;
	}	
	
	/**
	 * 修改页面跳转
	 * @param model
	 */
	@RequestMapping(value = "update/{id}" , method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id,Model model) {
		model.addAttribute("action", "update");
		YHPC_CheckHiddenInfoApproveEntity sspsh = yhpcSspshService.findById(id);
		model.addAttribute("sspsh", sspsh);
		return "yhpc/sspsh/addform";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String updateSub(YHPC_CheckHiddenInfoApproveEntity sspsh, Model model) {
		String datasuccess="success";
		//处理照片地址
		if(sspsh.getDangerphoto()!=null){
			String dangerphoto = sspsh.getDangerphoto();
			dangerphoto = dangerphoto.replace(",","");
			sspsh.setDangerphoto(dangerphoto);
		}
		yhpcSspshService.updatesspsh(sspsh);
		return datasuccess;
	}	
	
}
