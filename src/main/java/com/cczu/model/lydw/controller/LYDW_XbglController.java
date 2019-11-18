package com.cczu.model.lydw.controller;

import com.cczu.model.lydw.entity.LYDW_XBGL_Zb;
import com.cczu.model.lydw.service.LYDW_RyglService;
import com.cczu.model.lydw.service.LYDW_XbglService;
import com.cczu.model.lydw.service.LYDW_Xbgl_ZbService;
import com.cczu.sys.comm.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 蓝牙定位---信标管理
 */
@Controller
@RequestMapping("lydw/xbgl")
public class LYDW_XbglController extends BaseController {

    @Autowired
    private LYDW_XbglService lydw_xbglService;
    @Autowired
	private LYDW_Xbgl_ZbService lydw_xbgl_zbService;

    /**
     * 首页
     * @param model
     */
    @RequestMapping(value="index")
    public String index(Model model) {
        return "lydw/xbgl/index";
    }

    /**
     * 信标信息listjson
     * @param request
     */
    @RequestMapping(value="listjson")
    @ResponseBody
    public Map<String, Object> getData(HttpServletRequest request) {
        Map<String, Object> map = getPageMap(request);
        map.put("readerid", request.getParameter("lydw_xbgl_readerid"));
        map.put("readercode", request.getParameter("lydw_xbgl_readercode"));
        map.put("online", request.getParameter("lydw_xbgl_online"));
        map.putAll(getAuthorityMap());
        return lydw_xbglService.dataGrid(map);
    }

	/**
	 * 信标总览
	 */
	@RequestMapping(value="xblist")
	@ResponseBody
	public String getXbData() {
		return lydw_xbglService.xbData();
	}

	/**
	 * 添加信标标注点坐标
	 */
	@RequestMapping(value = "addzb")
	@ResponseBody
	public String addzb(HttpServletRequest request) {
		String datasuccess = "success";
		String zbid = request.getParameter("zbid");
		String xbid = request.getParameter("xbid");
		String X = request.getParameter("X");
		String Y = request.getParameter("Y");
		String Z = request.getParameter("Z");
		if(zbid != null && !zbid.equals("")) {
			lydw_xbgl_zbService.delInfo(Long.parseLong(zbid));
		}
		LYDW_XBGL_Zb xbzb = new LYDW_XBGL_Zb();
		xbzb.setXbid(Long.parseLong(xbid));
		xbzb.setX(X);
		xbzb.setY(Y);
		xbzb.setZ(Z);
		lydw_xbgl_zbService.addInfo(xbzb);
		return datasuccess;
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "deletezb/{id}")
	@ResponseBody
	public String delzv(@PathVariable("id") Long id) {
		String datasuccess = "success";
		lydw_xbgl_zbService.delInfo(id);
		return datasuccess;
	}

}
