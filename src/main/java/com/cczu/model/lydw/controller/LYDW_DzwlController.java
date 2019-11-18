package com.cczu.model.lydw.controller;

import com.cczu.model.lydw.entity.LYDW_DZWL;
import com.cczu.model.lydw.service.LYDW_DzwlService;
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
import java.util.HashMap;
import java.util.Map;

/**
 * 蓝牙定位----电子围栏
 */
@Controller
@RequestMapping("lydw/dzwl")
public class LYDW_DzwlController extends BaseController {

	@Autowired
	private LYDW_DzwlService lydw_dzwlService;

    /**
     * 首页
     * @param model
     */
    @RequestMapping(value="index")
    public String index(Model model) {
		return "lydw/dzwl/index2";
    }

	/**
	 * listjson
	 * @param request
	 */
	@RequestMapping(value="listjson")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("name", request.getParameter("lydw_dzwl_cx_name"));
		map.put("floor", request.getParameter("lydw_dzwl_cx_floor"));
		map.put("type", request.getParameter("type"));
		map.putAll(getAuthorityMap());
		return lydw_dzwlService.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model,HttpServletRequest request) {
		model.addAttribute("action", "create");
        model.addAttribute("type", request.getParameter("type"));
		return "lydw/dzwl/form";
	}

	/**
	 * 添加电子围栏信息
	 * @param dzwl,model
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid LYDW_DZWL dzwl, Model model) {
		String datasuccess="success";
		dzwl.setID1(UserUtil.getCurrentShiroUser().getQyid());
		lydw_dzwlService.addInfo(dzwl);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  蓝牙定位电子围栏信息  【添加操作】");
		return datasuccess;
	}


	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//查询电子围栏
		LYDW_DZWL dzwl = lydw_dzwlService.findById(id);
		model.addAttribute("dzwl", dzwl);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		//返回页面
		model.addAttribute("action", "update");
		return "lydw/dzwl/form";
	}

	/**
     * 修改电子围栏信息
     * @param entity,model
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public String update(LYDW_DZWL entity, Model model){
        String datasuccess="success";
        LYDW_DZWL wl = lydw_dzwlService.findById(entity.getID());
		wl.setName(entity.getName());
		wl.setID1(UserUtil.getCurrentShiroUser().getQyid());
		wl.setAllowids(entity.getAllowids());
		wl.setBanids(entity.getBanids());
        lydw_dzwlService.updateInfo(wl);
        log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  蓝牙定位电子围栏信息  【修改操作】");
        //返回结果
        return datasuccess;
    }

    /**
     * 修改电子围栏信息
     * @param entity,model
     */
    @RequestMapping(value = "update2")
    @ResponseBody
    public String update2(LYDW_DZWL entity){
        String datasuccess="success";
        LYDW_DZWL wl = lydw_dzwlService.findById(entity.getID());
		wl.setFloor(entity.getFloor());
		wl.setFloorname(entity.getFloorname());
		wl.setBuilding(entity.getBuilding());
		wl.setBuildingname(entity.getBuildingname());
		wl.setMappoint(entity.getMappoint());
		wl.setPoints(entity.getPoints());
        lydw_dzwlService.updateInfo(wl);
        log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  蓝牙定位电子围栏信息  【修改操作】");
        //返回结果
        return datasuccess;
    }

	/**
	 * 删除电子围栏信息
	 *
	 * @param ids
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功！";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  蓝牙定位电子围栏信息  【删除操作】");
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			lydw_dzwlService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 添加电子围栏
	 */
	@RequestMapping(value = "adddzwl")
	@ResponseBody
	public String adddzwl(HttpServletRequest request) {
		String datasuccess = "success";
		String wlid = request.getParameter("wlid");
		String mappoint = request.getParameter("mappoint");
		String floor = request.getParameter("floor");
		String xbids = request.getParameter("xbids");
		String type = request.getParameter("type");

		LYDW_DZWL dzwl = lydw_dzwlService.findById(Long.parseLong(wlid));

		dzwl.setMappoint(mappoint);
		dzwl.setFloor(floor);
		dzwl.setXbids(xbids);
		dzwl.setType(type);
		lydw_dzwlService.addInfo(dzwl);
		return datasuccess;
	}

	/**
	 * 电子围栏总览
	 */
	@RequestMapping(value="dzwllist")
	@ResponseBody
	public String getDzwlData(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
        map.put("type", request.getParameter("type"));
	    return lydw_dzwlService.dzwlDatabyMap(map);
	}

	/**
	 * 围栏名称json
	 */
	@RequestMapping(value = "wljson")
	@ResponseBody
	public String wlJson() {
		return lydw_dzwlService.wljson();
	}
}
