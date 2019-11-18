package com.cczu.model.controller;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.BIS_HazardEntity;
import com.cczu.model.service.IBisHazardIdentityService;
import com.cczu.model.service.IBisHazardService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

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

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 重大危险源信息controller
 * @author jason
 * @date 2017年6月15日
 */
@Controller
@RequestMapping("bis/hazard")
public class PageBisHazardController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IBisHazardService bishazardservice;
	@Autowired
	private IBisHazardIdentityService biszdwxysbxx;

	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {

		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		if(be!=null && be.getM1() != null){
			if(!"0".equals(be.getM39())){
				/*BIS_HazardEntity bh = bishazardservice.findqyid(be.getID());
				if(bh!=null){
					List<BIS_HazardIdentityEntity> list = biszdwxysbxx.findListHdid(bh.getID());
					if(list.size()>0){
						double Qnum=0,a=1;//a校正系数
						for(BIS_HazardIdentityEntity bis:list){
							if( StringUtils.isNotEmpty(bis.getM5()) ){
								Qnum=Qnum+Double.valueOf(bis.getM5());
							}
						}
						if(bh.getM3()==null){}
						else if(bh.getM3()<=0) a=0.5;
						else if(0<bh.getM3()&&bh.getM3()<30) a=1;
						else if(30<=bh.getM3()&&bh.getM3()<50) a=1.2;
						else if(50<=bh.getM3()&&bh.getM3()<100) a=1.5;
						else a=2;

						bh.setM9(String.valueOf(Qnum*a));
						bishazardservice.updateInfo(bh);
					}
					model.addAttribute("qylist", bh);
					model.addAttribute("action", "update");
				}else{
					model.addAttribute("action", "create");
				}*/
				return "qyxx/hazard/qyindex";
			}else{
				return "qyxx/hazard/noform";
			}

		}else{
			// 获取用户角色
			List<Role> list = roleService.findRoleById(sessionuser.getId());
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					//判断用户角色，如果包含有企业用户角色，则转到企业用户显示的界面
					if (list.get(i).getRoleCode().equals("company")||list.get(i).getRoleCode().equals("companyadmin"))
						return "../error/001";
				}
			}
			return "qyxx/hazard/adminindex";

		}
	}
	/**
	 * list页面
	 *
	 * @param request
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getDataList(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);

		map.put("wuzhi", request.getParameter("bis_hazard_wuzhi_name"));
		map.put("wxydj", request.getParameter("dj"));

		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		if(be!=null&&be.getM1()!=null){
			map.put("qyid2", be.getID());
		}
		return bishazardservice.dataGrid(map);
	}

	/**
	 * list页面
	 */
	@RequestMapping(value="ajlist")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {

		Map<String, Object> map = getPageMap(request);

		map.put("wuzhi", request.getParameter("bis_hazard_wuzhi_name"));
		map.put("wxydj", request.getParameter("dj"));

		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		if(be!=null&&be.getM1()!=null){
			map.put("qyid2", be.getID());
		}
		return bishazardservice.dataGrid(map);
	}

	/**
	 * 添加页面跳转
	 *
	 * @param model
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		//获取企业id
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		model.addAttribute("action", "create");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("inindustrialpark", be.getM46());
		model.addAttribute("parkid", be.getParkid());
		model.addAttribute("industrialparkname", be.getM46_1());
		return "qyxx/hazard/form";
	}
	
	@RequiresPermissions("bis:hazard:add")
	@RequestMapping(value = "create")
	@ResponseBody
	public String create(BIS_HazardEntity bh, Model model) throws ParseException {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		String companycode =be.getCompanycode();//获取当前用户所在企业的企业编码
		if(be!=null){
			Timestamp t= DateUtils.getSysTimestamp();
			bh.setS1(t);
			bh.setS2(t);
			bh.setS3(0);
			bh.setID1(be.getID());
			bh.setCompanycode(companycode);//设置企业编码
			bh.setCreateby(UserUtil.getCurrentUser().getName());//创建人
			bh.setUpdateby(UserUtil.getCurrentUser().getName());//最后修改人
			bishazardservice.addInfo(bh);
			return bh.getID()+"";
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--重大危险源信息  【添加操作】");

		return "请完善企业基本信息！";
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		//查询选择的车间信息

		long id1 = id;
		BIS_HazardEntity bh = bishazardservice.findByid(id1);
		model.addAttribute("bh", bh);
		model.addAttribute("wxyId",id1);
		model.addAttribute("action", "update");
		return "qyxx/hazard/form";
	}

	/**
	 * 修改
	 * @param ,
	 */
	@RequiresPermissions("bis:hazard:update")
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(BIS_HazardEntity bh, Model model){
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
		if(be!=null){
//			BIS_HazardEntity bhy=bishazardservice.findqyid(be.getID());
//			
//			List<BIS_HazardIdentityEntity> list = biszdwxysbxx.findListHdid(bhy.getID());
//			float Qnum=0;
//			for(BIS_HazardIdentityEntity bis:list){
//				if( StringUtils.isNotEmpty(bis.getM5()) ){
//					Qnum=Qnum+Float.valueOf(bis.getM5());
//				}
//			}
			Timestamp t= DateUtils.getSysTimestamp();
			bh.setS2(t);
			bh.setUpdateby(UserUtil.getCurrentUser().getName());//最后修改人
			bishazardservice.updateInfo(bh);
			return "success";
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--重大危险源信息  【修改操作】");

		//返回结果
		return "success";
	}

	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "qyview/{id}", method = RequestMethod.GET)
	public String qyview(@PathVariable("id") Long id, Model model) {
		//查询选择的产品信息
		BIS_HazardEntity bh = bishazardservice.findById(id);
		model.addAttribute("wxyId",id);
		model.addAttribute("bh", bh);
		//返回页面
		model.addAttribute("action", "view");

		model.addAttribute("wxyid", id);

		return "qyxx/hazard/form";
	}

	//-------------------------------------------------------------------------
	/* 安监局的修改和删除方法 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ */
	//-------------------------------------------------------------------------
	/**
	 * 修改页面跳转
	 * 
	 * @param id,model
	 */
	@RequestMapping(value = "ajupdate/{id}", method = RequestMethod.GET)
	public String ajupdate(@PathVariable("id") Integer id, Model model) {
		// 查询选择的特种设备信息

		long id1 = id;
		BIS_HazardEntity bs = bishazardservice.findqyid(id1);
		model.addAttribute("qylist", bs);
		// 返回页面
		model.addAttribute("action", "ajupdate");
		model.addAttribute("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return "qyxx/hazard/ajform";
	}

	/**
	 * 修改重大危险源信息
	 * 
	 * @param
	 */
	@RequestMapping(value = "ajupdate")
	@ResponseBody
	public String ajupdate(BIS_HazardEntity bs, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		bs.setS2(t);
		bishazardservice.updateInfo(bs);
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--重大危险源信息  【修改操作】");

		// 返回结果
		return datasuccess;
	}

	/**
	 * 删除重大危险源信息
	 * 
	 * @param
	 * @param
	 * @throws ParseException
	 */
	@RequestMapping(value = "ajdelete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			bishazardservice.deleteInfo(Long.parseLong(arrids[i]));
		}
		log.info(UserUtil.getCurrentShiroUser().getLoginName()+":  一企一档--重大危险源信息  【删除操作】");

		return datasuccess;
	}
 
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
//	@RequiresPermissions("bis:hazard:view")
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的产品信息
		BIS_HazardEntity bh = bishazardservice.findqyid(id);
		model.addAttribute("qylist", bh);
		//返回页面
		model.addAttribute("action", "view");
		
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(id);
		model.addAttribute("qyid", be.ID);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", 1);
		map.put("pageSize", 100);
		map.put("qyid", id);
		
		return "qyxx/hazard/adminview";
	}
	
	/**
	 * 查看页面跳转
	 * @param model
	 */
	@RequestMapping(value="tabindex/{qyid}")
	public String indexTab(@PathVariable("qyid") Integer qyid, Model model) {
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(qyid);
		if(be!=null){
			if(!be.getM39().equals("0")){
				Map<String,Object> bh = bishazardservice.findMapByQyId((long)qyid);
				if(bh!=null){
					model.addAttribute("qylist", bh);
				}
			}else{
				return "qyxx/hazard/noform";
			}
		}
		return "qyxx/hazard/formTab";
		
	}

	/**
	 * 导出excel
	 * 
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("bis_hazard_qy_name"));
		map.put("wuzhi", request.getParameter("bis_hazard_wuzhi_name"));
		map.put("wxydj", request.getParameter("dj"));
		map.put("bis_ids", request.getParameter("bis_ids"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		bishazardservice.exportExcel(response ,map);
	}
	
	/**
	 * 显示所有列
	 * @param
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url", "bis/hazard/export");
		return "common/formexcel";
	}
	
	/**
	 * 统计页面跳转
	 * @param
	 */
	@RequestMapping(value = "statistics", method = RequestMethod.GET)
	public String statistics(Model model) {
		List<Integer>list = bishazardservice.statistics(getAuthorityMap());
		model.addAttribute("data", JsonMapper.getInstance().toJson(list));
		return "qyxx/hazard/statistics";
	}
	
	
	
	/**
	 * 查询最新流水号
	 * @param model
	 * @return
	 */
	public String FindWaterCode(Long qyid) {
		String waterCode=bishazardservice.FindWaterCode(qyid);
		return waterCode;
	}
	
	
	/**
	 * 重大危险源编码json
	 * @param model
	 * @return
	 */
	@RequestMapping(value="hazardCodeJson")
	@ResponseBody
	public List<Map<String,Object>> getJson() {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Long qyid =sessionuser.getQyid();
		return bishazardservice.findhazardCode(qyid);
	}
	
	
	
	
}
