package com.cczu.model.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import com.cczu.model.entity.Sbgl_SbbyjhEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.SbglSbbyjhService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 设备管理-设备保养计划controller
 * 
 * @author xj
 * @date 2018年8月9日
 */
@Controller
@RequestMapping("sbgl/sbbyjh")
public class PageSbglSbbyjhController extends BaseController {

	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private SbglSbbyjhService sbglSbbyjhService;

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
					return "sbgl/sbbyjh/index";
				}
				else{
					model.addAttribute("type", 1);
					return "sbgl/sbbyjh/index";
				}
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			model.addAttribute("type", 2);
			return "sbgl/sbbyjh/index";
		}	
	}


	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequiresPermissions("sbgl:sbbyjh:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);
		map.putAll(getAuthorityMap());
		map.put("qyname", request.getParameter("view_qyname"));
		map.put("sbname", request.getParameter("sbname"));
		map.put("byjhname", request.getParameter("byjhname"));
		map.put("byjb", request.getParameter("byjb"));
		return sbglSbbyjhService.dataGrid(map);
	}


	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("sbgl:sbbyjh:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		return "sbgl/sbbyjh/form";
	}

	/**
	 * 添加信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("sbgl:sbbyjh:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(Sbgl_SbbyjhEntity e) {
		String datasuccess = "success";
		String[] m1=e.getM1().split(",");
		String[] m2=e.getM2().split(",");
		for(int i=0;i<m1.length;i++){
			e.setID(null);
			e.setM1(m1[i]);
			e.setM2(m2[i]);
			try {
				sbglSbbyjhService.addInfo(e);
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
	@RequiresPermissions("sbgl:sbbyjh:update")
	@RequestMapping(value = "update/{id}/{m6}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, @PathVariable("m6") String name, Model model) {
		List<Map<String,Object>> bs = sbglSbbyjhService.findById(id);
		List<Map<String,Object>> bs1 = sbglSbbyjhService.findBynr(id,name);
		
		model.addAttribute("sbgl1", JsonMapper.getInstance().toJson(bs1));
		model.addAttribute("sbgl", bs.get(0));
		// 返回页面
		model.addAttribute("action", "update");
		return "sbgl/sbbyjh/form";
	}

	/**
	 * 修改信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("sbgl:sbbyjh:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Sbgl_SbbyjhEntity bs, Model model, HttpServletRequest request) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		String[] m1=bs.getM1().split(",");
		String[] m2=bs.getM2().split(",");
		String[] id=request.getParameterValues("ID");
		String[] arrid=request.getParameter("arrid").split(",");
		for(int i=0;i<m1.length;i++){
			if(!id[i].equals("0")) {
				bs.setID(Long.valueOf(id[i]));
			}else {
				bs.setID(null);
			}
			bs.setS2(t);
			bs.setM1(m1[i]);
			bs.setM2(m2[i]);
			try {
				sbglSbbyjhService.updateInfo(bs);
				for (int j = 0; j < arrid.length; j++) {
					boolean isEq=false;
					for (int j2 = 0; j2 < id.length; j2++) {
						if (arrid[j].equals(id[j2])) {  
							isEq=true;  
							break;
						}
					}
					if (!isEq) {
						sbglSbbyjhService.deleteInfo(Long.valueOf(arrid[j]));
					}
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				datasuccess="error";
			}
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
	@RequiresPermissions("sbgl:jwx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			sbglSbbyjhService.deleteInfo(Long.parseLong(arrids[i]));
		}

		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("sbgl:sbbyjh:view")
	@RequestMapping(value = "view/{id}/{m6}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, @PathVariable("m6") String name, Model model) {
		List<Map<String,Object>> bs = sbglSbbyjhService.findById(id);
		List<Map<String,Object>> bs1 = sbglSbbyjhService.findBynr(id, name);
		
		model.addAttribute("sbgl1", bs1);
		model.addAttribute("sbgl", bs.get(0));
		// 返回页面
		return "sbgl/sbbyjh/view";
	}
	
	/**
	 * 导出word
	 * 
	 */
	@RequiresPermissions("sbgl:sbbyjh:export")
	@RequestMapping(value = "exportword/{id}/{m6}")
	@ResponseBody
	public String exportWord(@PathVariable("id") Long id, @PathVariable("m6") String name,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> remap = new HashMap<String, Object>();
		
		List<Map<String,Object>> bs = sbglSbbyjhService.findById(id);
		List<Map<String,Object>> bs1 = sbglSbbyjhService.findBynr(id, name);
		for (Map<String, Object> map : bs) {
			if (map.get("m8")!=null&&!(map.get("m8").toString().equals(""))) {
				//将日期" 年-月-日     时：分：秒  "转换为 " 年-月-日    "格式
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Timestamp m8 = (Timestamp)map.get("m8");
				String sub_m8 = format.format(m8);
				//将转换后的日期放入map中
				map.put("m8", sub_m8);
			}else {
				map.put("m8", "");
			}
		}
		for (Map<String, Object> map : bs1) {
			if (map.get("m2").equals("0")) {
				map.put("m2", "例行保养");
			}else if (map.get("m2").equals("1")) {
				map.put("m2", "一级保养");
			}else if (map.get("m2").equals("2")) {
				map.put("m2", "二级保养");
			}
		}
		remap.put("list", bs1);
		remap.put("head", bs.get(0));
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(remap, "sbgl_sbbyjh.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	/**
	 * 保养计划名称验证
	 */
	@RequestMapping(value = "jhnameyz", method = RequestMethod.POST)
	@ResponseBody
	public String sbbyjhnameyz(HttpServletRequest request) {
		String name = request.getParameter("m6");
		
		return sbglSbbyjhService.findBySbbyjhname(name);
	}
}
