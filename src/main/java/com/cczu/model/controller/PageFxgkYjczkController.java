package com.cczu.model.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.FXGK_Yjczk;
import com.cczu.model.entity.FXGK_Yjczk;
import com.cczu.model.service.FxgkFxxxService;
import com.cczu.model.service.FxgkYjczkService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Department;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * 两单三卡管理---应急处置卡
 */
@Controller
@RequestMapping("fxgk/yjczk")
public class PageFxgkYjczkController extends BaseController {

	@Autowired
	private FxgkYjczkService sxgkyjczkService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;

	/**
	 * 列表显示页面
	 *
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("usertype", sessionuser.usertype);
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("bistype", "0");//集团用户
				else
					model.addAttribute("bistype", "1");
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}
		return "fxgk/yjczk/index";
	}

	/**
	 * list页面
	 *
	 * @param request
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("m1", request.getParameter("fxgk_yjczk_m1"));
		map.putAll(getAuthorityMap());
		return sxgkyjczkService.dataGrid(map);

	}

	/**
	 * 风险分区大数据页面两单三卡中应急处置卡显示页面
	 *
	 * @param model
	 */
	@RequestMapping(value = "index2")
	public String index2(Model model,HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("usertype", sessionuser.usertype);
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("bistype", "0");//集团用户
				else
					model.addAttribute("bistype", "1");
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}
		return "fxgk/yjczk/index2";
	}


	/**
	 * 添加页面跳转
	 *
	 * @param model
	 */
	@RequiresPermissions("fxgk:yjczk:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		model.addAttribute("usertype",sessionuser.getUsertype());
		model.addAttribute("action", "create");
		return "fxgk/yjczk/form";
	}

	/**
	 * 添加信息
	 *
	 * @param request
	 * model
	 */
	@RequiresPermissions("fxgk:yjczk:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(FXGK_Yjczk entity, HttpServletRequest request) {
		String datasuccess = "success";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		entity.setQyid(sessionuser.getQyid());
		Timestamp t = DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		sxgkyjczkService.addInfo(entity);

		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  两单三卡管理--应急处置卡  【增加操作】");
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 *
	 * @param id
	 *            ,model
	 */
	@RequiresPermissions("fxgk:yjczk:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		long id1 = id;
		FXGK_Yjczk entity = sxgkyjczkService.findById(id1);
		model.addAttribute("entity", entity);
		// 返回页面
		model.addAttribute("action", "update");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "fxgk/yjczk/form";
	}

	/**
	 * 修改演练记录信息
	 *
	 * @param
	 *            ,model
	 */
	@RequiresPermissions("fxgk:yjczk:update")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(FXGK_Yjczk entity, Model model) throws UnsupportedEncodingException {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		entity.setS2(t);
		if (StringUtils.isNotEmpty(entity.getM2())) {
			String m2 = entity.getM2();
			// 把空格转换为&nbsp;
			byte[] space = new byte[]{(byte) 0xc2,(byte) 0xa0};
			String UTFSpace  = new String( space,"UTF-8" );
			m2 = m2.replaceAll(UTFSpace, "&nbsp;");
			m2 = m2.replace("?", "&nbsp;");
			entity.setM2(m2);
		}
		if (StringUtils.isNotEmpty(entity.getM3())) {
			String m3 = entity.getM3();
			// 把空格转换为&nbsp;
			byte[] space = new byte[]{(byte) 0xc2,(byte) 0xa0};
			String UTFSpace  = new String( space,"UTF-8" );
			m3 = m3.replaceAll(UTFSpace, "&nbsp;");
			m3 = m3.replace("?", "&nbsp;");
			entity.setM3(m3);
		}
		sxgkyjczkService.updateInfo(entity);

		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  两单三卡管理--应急处置卡  【修改操作】");
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除演练记录信息
	 *
	 * @param
	 * @param
	 * @throws
	 */
	@RequiresPermissions("fxgk:yjczk:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			sxgkyjczkService.deleteInfo(Long.parseLong(arrids[i]));
		}

		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  两单三卡管理--应急处置卡  【删除操作】");
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequiresPermissions("fxgk:yjczk:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		//查询选择的产品信息

		long id1 = id;
		FXGK_Yjczk entity = sxgkyjczkService.findById(id1);
		model.addAttribute("entity", entity);
		//返回页面
		model.addAttribute("action", "view");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "fxgk/yjczk/view";
	}

	/**
	 * 导出word
	 *
	 */
	@RequiresPermissions("fxgk:yjczk:exportword")
	@RequestMapping(value = "exportword/{id}")
	@ResponseBody
	public String exportWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map=sxgkyjczkService.getWord(id);
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "yjczk.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
}
