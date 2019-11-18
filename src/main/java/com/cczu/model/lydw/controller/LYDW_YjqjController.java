package com.cczu.model.lydw.controller;

import com.cczu.model.lydw.entity.LYDW_YJQJ;
import com.cczu.model.lydw.service.LYDW_YjqjService;
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
 * 蓝牙定位----一键求救
 */
@Controller
@RequestMapping("lydw/yjqj")
public class LYDW_YjqjController extends BaseController {

	@Autowired
	private LYDW_YjqjService lydw_yjqjService;

	/**
     * 首页
     * @param model
     */
    @RequestMapping(value="index")
    public String index(Model model) {
        return "lydw/yjqj/index";
    }

    /**
     * 一键求救listjson
     * @param request
     */
    @RequestMapping(value="list")
    @ResponseBody
    public Map<String, Object> getRywzList(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		ShiroRealm.ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		map.put("qyid", sessionuser.qyid);
		map.put("rq", request.getParameter("lydw_yjqj_cx_rq"));
		return lydw_yjqjService.dataGrid(map);
    }

	/**
	 * 添加
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public String update(HttpServletRequest request, Model model){
		String datasuccess="success";
		Timestamp t= DateUtils.getSysTimestamp();
		LYDW_YJQJ entity = new LYDW_YJQJ();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setXbid(Long.valueOf(request.getParameter("xbid")));
		entity.setGkid(Long.valueOf(request.getParameter("gkid")));
		lydw_yjqjService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}

	/**
	 * 删除求救信息
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功！";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一键求救信息  【删除操作】");
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			lydw_yjqjService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 地图弹窗，显示警报点
	 * @param model
	 */
	@RequestMapping(value = "warningMarker" , method = RequestMethod.GET)
	public String warningMarker(Model model,HttpServletRequest request) {
	    String ygid = request.getParameter("ygid");
		model.addAttribute("action", "warningMarker");
		model.addAttribute("ygid", ygid);
		return "lydw/yjqj/mapWindow";
	}

}
