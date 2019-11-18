package com.cczu.model.lydw.controller;

import com.cczu.model.lydw.entity.LYDW_BJGL;
import com.cczu.model.lydw.service.LYDW_BjglService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Map;

/**
 * 蓝牙定位----报警管理
 */
@Controller
@RequestMapping("lydw/bjgl")
public class LYDW_BjglController extends BaseController {

	@Autowired
	private LYDW_BjglService lydw_bjglService;

	/**
     * 首页
     * @param model
     */
    @RequestMapping(value="index")
    public String index(Model model) {
        return "lydw/bjgl/index";
    }

    /**
     * 报警管理list
     * @param request
     */
    @RequestMapping(value="list")
    @ResponseBody
    public Map<String, Object> getRywzList(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		ShiroRealm.ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		map.put("qyid", sessionuser.getQyid());
		map.put("rq", request.getParameter("lydw_bjgl_cx_rq"));
		map.put("m1", request.getParameter("lydw_bjgl_cx_lb"));
		return lydw_bjglService.dataGrid(map);
    }

	/**
	 * 添加
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public String update(HttpServletRequest request, Model model){
		String datasuccess="success";
		Timestamp t= DateUtils.getSysTimestamp();
		LYDW_BJGL entity = new LYDW_BJGL();
		entity.setM1(request.getParameter("m1"));//报警类别
		entity.setM2(request.getParameter("m2"));//报警内容

		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setID1(UserUtil.getCurrentShiroUser().getQyid());
		lydw_bjglService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}

	/**
	 * 修改反馈信息
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询选择的工卡信息
		LYDW_BJGL entity = lydw_bjglService.findById(id);
		model.addAttribute("lydw_bjgl", entity);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		//返回页面
		model.addAttribute("action", "update");
		return "lydw/bjgl/form";
	}

	/**
	 * 修改反馈信息
	 * @param model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(LYDW_BJGL entity, Model model){
		String datasuccess="success";
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);
		entity.setM4(UserUtil.getCurrentShiroUser().getId().toString());
		lydw_bjglService.updateInfo(entity);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  报警信息  【修改操作】");
		//返回结果
		return datasuccess;
	}

	/**
	 * 删除报警信息
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功！";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  报警管理  【删除操作】");
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			lydw_bjglService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 报警类别
	 * @param {json}
	 */
	@RequestMapping(value="/bjlb")
	@ResponseBody
	public String bjlb() {
		Long qyid = UserUtil.getCurrentShiroUser().getQyid();
		return lydw_bjglService.bjlb(qyid);
	}
}
