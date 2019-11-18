package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.Sbgl_SbyfxbyglEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.SbglSbyfxbyglService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 设备管理-设备预防性保养管理controller
 * 
 * @author xj
 * @date 2018年8月13日
 */
@Controller
@RequestMapping("sbgl/yfxbygl")
public class PageSbglSbyfxbyglController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private SbglSbyfxbyglService sbglSbyfxbyglService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		if(request.getParameter("sys")!=null &&"1".equals(request.getParameter("sys")))
			model.addAttribute("sys", "sys");
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1){//判断是否为集团公司
					model.addAttribute("type", 2);
					return "sbgl/yfxbygl/index";
				}
				else{
					model.addAttribute("type", 1);
					return "sbgl/yfxbygl/index";
				}
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			model.addAttribute("type", 2);
			return "sbgl/yfxbygl/index";
		}	
	}


	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("sbgl:yfxbygl:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("qyname", request.getParameter("view_qyname"));
		map.put("sbname", request.getParameter("sbname"));
		map.put("m1", request.getParameter("M1"));
		map.put("m4", request.getParameter("M4"));
		return sbglSbyfxbyglService.dataGrid(map);
	}


	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("sbgl:yfxbygl:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "sbgl/yfxbygl/form";
	}

	/**
	 * 添加信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("sbgl:yfxbygl:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(Sbgl_SbyfxbyglEntity e) {
		String datasuccess = "success";
		String[] m1=e.getM1().split(",");
		String[] m2=e.getM2().split(",");
		String[] m3=e.getM3().split(",");
		String[] m4=e.getM4().split(",");
		String[] m5=e.getM5().split(",");
		String[] m6=e.getM6().split(",");
		for(int i=0;i<m1.length;i++){
			e.setID(null);
			e.setM1(m1[i]);
			e.setM2(m2[i]);
			e.setM3(m3[i]);
			e.setM4(m4[i]);
			e.setM5(m5[i]);
			e.setM6(m6[i]);
			try {
				sbglSbyfxbyglService.addInfo(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				datasuccess="error";
			}
		}
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("sbgl:yfxbygl:update")
	@RequestMapping(value = "updata/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		Sbgl_SbyfxbyglEntity bs = sbglSbyfxbyglService.findById(id);
		
		model.addAttribute("sbgl", bs);
		// 返回页面
		model.addAttribute("action", "updata");
		return "sbgl/yfxbygl/form";
	}

	/**
	 * 修改信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("sbgl:yfxbygl:update")
	@RequestMapping(value = "updata")
	@ResponseBody
	public String update(Sbgl_SbyfxbyglEntity bs, Model model, HttpServletRequest request) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		bs.setS2(t);
		try {
			sbglSbyfxbyglService.updateInfo(bs);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除信息
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException
	 */
	@RequiresPermissions("sbgl:yfxbygl:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			sbglSbyfxbyglService.deleteInfo(Long.parseLong(arrids[i]));
		}

		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("sbgl:yfxbygl:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Sbgl_SbyfxbyglEntity bs = sbglSbyfxbyglService.findById(id);
		
		model.addAttribute("sbgl", bs);
		// 返回页面
		return "sbgl/yfxbygl/view";
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("sbgl:yfxbygl:export")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyid", UserUtil.getCurrentShiroUser().getQyid());
		map.put("sbname", request.getParameter("sbname"));
		map.put("m1", request.getParameter("M1"));
		map.put("m4", request.getParameter("M4"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		sbglSbyfxbyglService.exportExcel(response, map);

	}
	
	/**
	 * 显示所有列
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("sbgl:yfxbygl:export")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","sbgl/yfxbygl/export");
		return "/common/formexcel";
	}
}
