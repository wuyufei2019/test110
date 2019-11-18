package com.cczu.model.lydw.controller;

import com.cczu.model.lydw.entity.LYDW_QYQX;
import com.cczu.model.lydw.service.LYDW_DzwlService;
import com.cczu.model.lydw.service.LYDW_QyqxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * 蓝牙定位----区域权限
 */
@Controller
@RequestMapping("lydw/qyqx")
public class LYDW_QyqxController extends BaseController {

	@Autowired
	private LYDW_QyqxService lydw_qyqxService;

    /**
     * 首页
     * @param model
     */
    @RequestMapping(value="index")
    public String index(Model model) {
        return "lydw/qyqx/index";
    }

	/**
	 * listjson
	 * @param request
	 */
	@RequestMapping(value="listjson")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("name", request.getParameter("lydw_qyqx_cx_name"));
		map.put("floor", request.getParameter("lydw_qyqx_cx_floor"));
		map.putAll(getAuthorityMap());
		return lydw_qyqxService.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "lydw/qyqx/form";
	}

	/**
	 * 添加区域权限信息
	 * @param qyqx,model
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid LYDW_QYQX qyqx, Model model) {
		String datasuccess="success";
		qyqx.setID1(UserUtil.getCurrentShiroUser().getQyid());
		lydw_qyqxService.addInfo(qyqx);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  蓝牙定位区域权限信息  【添加操作】");
		return datasuccess;
	}


	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询区域权限
		LYDW_QYQX qyqx = lydw_qyqxService.findById(id);
		model.addAttribute("qyqx", qyqx);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		//返回页面
		model.addAttribute("action", "update");
		return "lydw/qyqx/form";
	}

	/**
	 * 修改区域权限信息
	 * @param entity,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(LYDW_QYQX entity, Model model){
		String datasuccess="success";
		LYDW_QYQX wl = lydw_qyqxService.findById(entity.getID());
		wl.setName(entity.getName());// 区域名称
		wl.setDeptnames(entity.getDeptnames());// 允许进出部门
		wl.setID1(UserUtil.getCurrentShiroUser().getQyid());
		lydw_qyqxService.updateInfo(wl);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  蓝牙定位区域权限信息  【修改操作】");
		//返回结果
		return datasuccess;
	}

	/**
	 * 删除区域权限信息
	 *
	 * @param ids
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功！";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  蓝牙定位区域权限信息  【删除操作】");
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			lydw_qyqxService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 添加区域权限
	 */
	@RequestMapping(value = "addqyqx")
	@ResponseBody
	public String addqyqx(HttpServletRequest request) {
		String datasuccess = "success";
		String wlid = request.getParameter("wlid");
		String mappoint = request.getParameter("mappoint");
		String floor = request.getParameter("floor");
		String xbids = request.getParameter("xbids");

		LYDW_QYQX qyqx = lydw_qyqxService.findById(Long.parseLong(wlid));

		qyqx.setMappoint(mappoint);
		qyqx.setFloor(floor);
		qyqx.setXbids(xbids);
		lydw_qyqxService.addInfo(qyqx);
		return datasuccess;
	}

	/**
	 * 区域权限总览
	 */
	@RequestMapping(value="qyqxlist")
	@ResponseBody
	public String getDzwlData() {
		return lydw_qyqxService.dzwlData();
	}

	/**
	 * 围栏名称json
	 */
	@RequestMapping(value = "wljson")
	@ResponseBody
	public String wlJson() {
		return lydw_qyqxService.wljson();
	}
}
