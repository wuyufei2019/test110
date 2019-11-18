package com.cczu.model.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.FxgkTjfxService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 风险管控-统计分析
 * @author zpc
 * @date 2017/08/09
 */
@Controller
@RequestMapping("fxgk/tjfx")
public class PageFxgkTjfxController extends BaseController{
	
	@Autowired
	private FxgkTjfxService fxgkTjfxService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	
	public static String[] fenlei = {"危险化学品","爆炸性粉尘","重大危险源","受限空间","涉氨场所","生产系统","设备设施","输送管线","操作行为","职业健康","环境条件","施工场所","安全管理","其他"};
	
	public static String[] shigu = {"物体打击","机械伤害","车辆伤害","起重伤害","高处坠落","中毒和窒息","触电","淹溺","灼烫","火灾","坍塌","透水","放炮","冒顶片帮","火药爆炸","瓦斯爆炸","锅炉爆炸","容器爆炸","其它爆炸","其它伤害"};
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			model.addAttribute("type","2");//其他
		}	
		return "fxgk/tjfx/riskIndex";
	}	

	/**
	 * list页面(根据风险等级统计风险点--echart)
	 * 
	 * @param request
	 */
	@RequestMapping(value = "FXDjFXD")
	@ResponseBody
	public Map<String, Object> FXDjFXD(HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(getAuthorityMap());
		Map<String, Object> yanse = new HashMap<String, Object>();
		map.put("m9", "1");
		int hong = fxgkTjfxService.getFXDCountSer(map);
		yanse.put("hong", hong);

		map.put("m9", "2");
		int cheng = fxgkTjfxService.getFXDCountSer(map);
		yanse.put("cheng", cheng);

		map.put("m9", "3");
		int huang = fxgkTjfxService.getFXDCountSer(map);
		yanse.put("huang", huang);

		map.put("m9", "4");
		int lan = fxgkTjfxService.getFXDCountSer(map);
		yanse.put("lan", lan);

		return yanse;

	}
	
	/**
	 * list页面(根据乡镇统计风险点--echart)
	 * 
	 * @param request
	 */
	@RequestMapping(value = "FXDjXZ")
	@ResponseBody
	public Map<String, Object> FXDjXZ(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("xzqy","");
		map.putAll(getAuthorityMap());
		Map<String, Object> mapO = new HashMap<String, Object>();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		List<Role> list=roleService.findRoleById(sessionuser.getId());
		List<Map<String,Object>> xz=new ArrayList<Map<String,Object>>();
		List<Object> xzlist=new ArrayList<Object>();
		if(list.size()>0){
			if(list.get(0).getRoleCode().equals("admin")||list.get(0).getRoleCode().equals("superadmin")||list.get(0).getRoleCode().equals("ajcountyadmin")||list.get(0).getRoleCode().equals("ajcounty")){
				//市级统计
				xz = fxgkTjfxService.FXDjXZSer(map);
				xzlist=fxgkTjfxService.xzlist(map);
			}else{
				//镇级统计
				xz = fxgkTjfxService.FXDjXZSer2(map);
				xzlist=fxgkTjfxService.xzlist2(map);
			}
		}
		int size = xzlist.size();  
		String[] arr = (String[])xzlist.toArray(new String[size]);
		for (int j = 0; j < arr.length; j++) {
			mapO.put("xz" + (j + 1) + "_" + 1, 0);
			mapO.put("xz" + (j + 1) + "_" + 2, 0);
			mapO.put("xz" + (j + 1) + "_" + 3, 0);
			mapO.put("xz" + (j + 1) + "_" + 4, 0);
			for (int i = 0; i <xz.size(); i++) {
				Map<String,Object> map2 = (Map<String,Object>) xz.get(i);
				if (arr[j].equals(map2.get("street"))) {
					if(map2.get("yanse").toString().equals("1")){
						mapO.put("xz" + (j + 1) + "_" + 1,
								map2.get("num"));
					}
					if(map2.get("yanse").toString().equals("2")){
						mapO.put("xz" + (j + 1) + "_" + 2,
								map2.get("num"));
					}
					if(map2.get("yanse").toString().equals("3")){
						mapO.put("xz" + (j + 1) + "_" + 3,
								map2.get("num"));
					}
					if(map2.get("yanse").toString().equals("4")){
						mapO.put("xz" + (j + 1) + "_" + 4,
								map2.get("num"));
					}
				}
				
			}
		}
		mapO.put("xzlist", arr);
		mapO.put("listsize", arr.length);
		return mapO;
	}
	
	/**
	 * list页面(根据风险分类统计风险点--echart)
	 * 
	 * @param request
	 */
	@RequestMapping(value = "FXDjFL")
	@ResponseBody
	public Map<String, Object> FXDjFL(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(getAuthorityMap());
		Map<String, Object> mapO = new HashMap<String, Object>();
		
		
		List<Map<String,Object>> fl = fxgkTjfxService.FXDjFLSer(map);

		for (int j = 0; j < fenlei.length; j++) {
			mapO.put("xz" + (j + 1) + "_" + 1, 0);
			mapO.put("xz" + (j + 1) + "_" + 2, 0);
			mapO.put("xz" + (j + 1) + "_" + 3, 0);
			mapO.put("xz" + (j + 1) + "_" + 4, 0);

			for (int i = 0; i < fl.size(); i++) {
				Map<String,Object> map2 = (Map<String,Object>) fl.get(i);
				if (fenlei[j].equals(map2.get("fenlei"))) {
					if(map2.get("yanse").toString().equals("1")){
						mapO.put("xz" + (j + 1) + "_" + 1,
								map2.get("num"));
					}
					if(map2.get("yanse").toString().equals("2")){
						mapO.put("xz" + (j + 1) + "_" + 2,
								map2.get("num"));
					}
					if(map2.get("yanse").toString().equals("3")){
						mapO.put("xz" + (j + 1) + "_" + 3,
								map2.get("num"));
					}
					if(map2.get("yanse").toString().equals("4")){
						mapO.put("xz" + (j + 1) + "_" + 4,
								map2.get("num"));
					}
				}
			}
		}
		return mapO;
	}

	/**
	 * list页面(根据易发事故类型统计风险点--echart)
	 * 
	 * @param request
	 */
	@RequestMapping(value = "FXDjSg")
	@ResponseBody
	public Map<String, Object> FXDjSg(HttpServletRequest request) {
		Map<String, Object> map = getAuthorityMap();
		Map<String, Object> mapO = new HashMap<String, Object>();
		List<Map<String,Object>> fl = fxgkTjfxService.FXDjSgSer(map);
		for (int j = 0; j < shigu.length; j++) {
			mapO.put("xz" + (j + 1) + "_" + 1, 0);
			int num1=0,num2=0,num3=0,num4=0;
			for (int i = 0; i < fl.size(); i++) {
				Map<String,Object> map2 = (Map<String,Object>) fl.get(i);
				String shigu1=shigu[j];
				if ((map2.get("shigu").toString()).indexOf(shigu1)!=-1) {
					if(map2.get("yanse").toString().equals("1")){
						num1=num1+Integer.parseInt(map2.get("num").toString());
						mapO.put("xz" + (j + 1) + "_" + 1,
								num1);
					}
					if(map2.get("yanse").toString().equals("2")){
						num2=num2+Integer.parseInt(map2.get("num").toString());
						mapO.put("xz" + (j + 1) + "_" + 2,
								num2);
					}
					if(map2.get("yanse").toString().equals("3")){
						num3=num3+Integer.parseInt(map2.get("num").toString());
						mapO.put("xz" + (j + 1) + "_" + 3,
								num3);
					}
					if(map2.get("yanse").toString().equals("4")){
						num4=num4+Integer.parseInt(map2.get("num").toString());
						mapO.put("xz" + (j + 1) + "_" + 4,
								num4);
					}
				}
			}
		}
		return mapO;
	}

	/**
	 * 主页根据易发事故类型统计风险点
	 * 
	 * @param request
	 */
	@RequestMapping(value = "FXDjSg2")
	@ResponseBody
	public Map<String, Object> FXDjSg2(HttpServletRequest request) {
		Map<String, Object> map = getAuthorityMap();
		Map<String, Object> mapO = new HashMap<String, Object>();
		List<Integer> list=new ArrayList<Integer>();
		List<Map<String,Object>> list2=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> fl = fxgkTjfxService.FXDjSgSer(map);
		for (int j = 0; j < shigu.length; j++) {
			int num=0;
			for (int i = 0; i < fl.size(); i++) {
				Map<String,Object> map2 = (Map<String,Object>) fl.get(i);
				String shigu1=shigu[j];
				if ((map2.get("shigu").toString()).indexOf(shigu1)!=-1) {
						num=num+Integer.parseInt(map2.get("num").toString());
				}
			}
			list.add(num);
			Map<String, Object> map3 =new HashMap<>();
			map3.put("text", shigu[j]);
			list2.add(map3);
		}
		int max=Collections.max(list);
		Map<String, Object> map4=list2.get(0);
		map4.put("max", max);
		list2.add(map4);
		mapO.put("shigu", list2);
		mapO.put("num", list);
		return mapO;
	}
	
	/**
	 * list页面(根据风险等级统计企业)
	 * 
	 * @param request
	 */
	@RequestMapping(value = "FXDjajlist")
	@ResponseBody
	public Map<String, Object> FXDjajlist(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy","");
		map.putAll(getAuthorityMap());
		String type=request.getParameter("type");
		if(StringUtils.isBlank(type))
			type="4";
		else
			type=("红橙黄蓝".indexOf(type)+1)+"";
		map.put("type",type);
		return fxgkTjfxService.dataGrid0(map);
	}
	
	/**
	 * list页面(根据乡镇统计企业)
	 * 
	 * @param request
	 */
	@RequestMapping(value = "XZjajlist")
	@ResponseBody
	public Map<String, Object> XZjajlist(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		List<Role> list=roleService.findRoleById(sessionuser.getId());
		map.put("xzqy","");
		if(!(list.get(0).getRoleCode().equals("admin")||list.get(0).getRoleCode().equals("superadmin"))){
			map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		}
		//安监用户添加监管类型查询条件 当为0时 监管全部类型  无须添加该条件
		if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0)
			map.put("jglx",sessionuser.getUserroleflg());
		if(list.size()>0){
			if(list.get(0).getRoleCode().equals("admin")||list.get(0).getRoleCode().equals("superadmin")||list.get(0).getRoleCode().equals("ajcountyadmin")||list.get(0).getRoleCode().equals("ajcounty")){
				return fxgkTjfxService.dataGrid00(map);
			}else{
				return fxgkTjfxService.dataGrid01(map);
			}
		}
		return fxgkTjfxService.dataGrid00(map);
	}
	
	/**
	 * list页面(根据风险分类统计企业)
	 * 
	 * @param request
	 */
	@RequestMapping(value = "FLjajlist")
	@ResponseBody
	public Map<String, Object> FLjajlist(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy","");
		map.putAll(getAuthorityMap());
		String type=request.getParameter("type");
		if(StringUtils.isBlank(type))
			type=fenlei[0];
		map.put("type",type);
		return fxgkTjfxService.dataGrid000(map);
	}
	
	/**
	 * list页面(根据易发生事故类型统计企业)
	 * 
	 * @param request
	 */
	@RequestMapping(value = "Sgjajlist")
	@ResponseBody
	public Map<String, Object> Sgjajlist(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy","");
		map.putAll(getAuthorityMap());
		String type=request.getParameter("type");
		if(StringUtils.isBlank(type))
			type=shigu[0];
		map.put("type",type);
		return fxgkTjfxService.dataGrid0000(map);
	}
}
