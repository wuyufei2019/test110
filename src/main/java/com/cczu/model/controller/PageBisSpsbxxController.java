package com.cczu.model.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.Bis_VideoEquipmentEntity;
import com.cczu.model.entity.TS_Video;
import com.cczu.model.service.BisSpsbxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 视频设备信息controller
 * 
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("bis/spsbxx")
public class PageBisSpsbxxController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private BisSpsbxxService bisSpsbxxService;

	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(HttpServletRequest request, Model model) {
		model.addAttribute("zdwxy", request.getParameter("zdwxy"));
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				model.addAttribute("qyid", be.getID());
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "qyxx/spsbxx/ajindex";
				else
					return "qyxx/spsbxx/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "qyxx/spsbxx/ajindex";
		}	
	}

	/**
	 * list页面
	 * 
	 * @param request
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		// 获取企业id
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());

		Map<String, Object> map = getPageMap(request);
		if (be != null) {
			map.put("qyid", be.getID());
		}

		map.put("zdwxy", request.getParameter("zdwxy"));// 重大危险源视频
		map.put("qyname", request.getParameter("bis_spsbxx_cx_qyname"));
		map.put("m1", request.getParameter("bis_spsbxx_cx_m1"));// 摄像头编码
		map.put("m3", request.getParameter("bis_spsbxx_cx_m3"));// 摄像头名称
		return bisSpsbxxService.dataGrid(map);
	}

	/**
	 * 列表显示页面
	 *
	 * @param model
	 */
	@RequestMapping(value = "index2")
	public String index2(HttpServletRequest request, Model model) {
		model.addAttribute("zdwxy", request.getParameter("zdwxy"));
		return "qyxx/spsbxx/index2";
	}

	/**
	 * 添加页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("bis:spsbxx:add")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("qyid",UserUtil.getCurrentShiroUser().getQyid());

		return "qyxx/spsbxx/form";
	}

	/**
	 * 添加传感器设备信息
	 * 
	 * @param request,model
	 */
	@RequiresPermissions("bis:spsbxx:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(Bis_VideoEquipmentEntity bs, Model model,HttpServletRequest request) {
		String datasuccess = "success";
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		bs.setVideoid(UUID.randomUUID().toString().replaceAll("-", ""));
		if(sessionuser.getUsertype().equals("1")){
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			long ID = be.getID();//获取到企业id
			bs.setID1(ID);
		}
		if (!bisSpsbxxService.isexist(bs.getM3(), 0)) {
			Timestamp t=DateUtils.getSysTimestamp();
			bs.setS1(t);
			bs.setS2(t);
			bs.setS3(0);
			bisSpsbxxService.addInfo(bs);
		} else {
			datasuccess="error";
		}
		log.info(sessionuser.getLoginName()+":  一企一档--传感器设备信息  【添加操作】");
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:spsbxx:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		// 查询选择的传感器设备信息
		long id1 = id;
		Bis_VideoEquipmentEntity bs = bisSpsbxxService.findById(id1);

		model.addAttribute("spsbxx", bs);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("qyid",UserUtil.getCurrentShiroUser().getQyid());
		// 返回页面
		model.addAttribute("action", "update");
		return "qyxx/spsbxx/form";
	}

	/**
	 * 修改传感器设备信息
	 * 
	 * @param bs,model
	 */
	@RequiresPermissions("bis:spsbxx:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Bis_VideoEquipmentEntity bs, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		bs.setS2(t);
		if(StringUtils.isBlank(bs.getVideoid()))
			bs.setVideoid(UUID.randomUUID().toString().replaceAll("-", ""));
		bisSpsbxxService.updateInfo(bs);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--传感器设备信息  【修改操作】");
		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除传感器设备信息
	 * @param ids
	 */
	@RequiresPermissions("bis:spsbxx:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--传感器设备信息  【删除操作】");
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			bisSpsbxxService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:qyjbxx:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		
		Map<String, Object> bs = bisSpsbxxService.findById2(id);
		model.addAttribute("spsbxx", bs);
		// 返回页面
		return "qyxx/spsbxx/view";
	}

	/**
	 * 查看页面跳转
	 * 
	 * @param id,model
	 */
	@RequiresPermissions("bis:spsbxx:view")
	@RequestMapping(value = "sview/{id}", method = RequestMethod.GET)
	public String sview(@PathVariable("id") Integer id, Model model) {
		// 查询选择的产品信息
		long id1 = id;
		Bis_VideoEquipmentEntity bs = bisSpsbxxService.findById(id1);

		model.addAttribute("spsbxx", bs);
		// 返回页面
		model.addAttribute("action", "view");
		return "qyxx/spsbxx/view";
	}

    /**
     *
     * 获取三台重大危险源视频信息
     *
     */
    @RequestMapping(value = "zdwxySpUrls", method = RequestMethod.POST)
    @ResponseBody
    public String zdwxySpUrls(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        ShiroUser user = UserUtil.getCurrentShiroUser();
		Long qyid = Long.parseLong(request.getParameter("qyid"));
		map.put("qyid", qyid);
        map.put("zdwxy", "1");
        return JsonMapper.toJsonString(bisSpsbxxService.getZdwxySpUrlsJson(map));
    }

	/**
	 * 根据摄像头id  查看直播视频
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="showsp/{id}")
	public String showSP(@PathVariable Long id, Model model) {
		Bis_VideoEquipmentEntity ts= bisSpsbxxService.findById(id);
		model.addAttribute("video", ts);
		return "qyxx/spsbxx/showone";
	}

	/**
	 * 生产视频流
	 * @param
	 */
	@RequiresPermissions("bis:spsbxx:reset")
	@RequestMapping(value = "reset", method = RequestMethod.GET)
	@ResponseBody
	public String reset(Model model) {
		bisSpsbxxService.reset();
		return "操作完成！";
	}

}
