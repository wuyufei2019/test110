package com.cczu.model.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.FXGK_AccidentRisk;
import com.cczu.sys.comm.utils.QRCode;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.util.common.WordUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.FxgkFxxxService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.entity.Barrio;
import com.cczu.sys.system.service.BarrioService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
/**
 * 风险管控-风险分布图controller
 * @author jason
 * @date 2017年6月23日
 */
@Controller
@RequestMapping("fxgk/fxfb")
public class PageFxgkFxfbtController extends BaseController {
	@Autowired
	private BarrioService barrioservice;
	@Autowired
	private FxgkFxxxService fxgkFxxxService;
	@Autowired
	private BisQyjbxxServiceImpl bisQyjbxxService;
	@Autowired
	private FxgkFxxxService sxgkfxxxService;

	private static final int FTL_A3_HEIGHT = 8600;//模板导出高度
	private static final int FTL_A2_HEIGHT = 10900;

	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type", "2");
				else
					model.addAttribute("type", "1");
			}
		}else {//非企业用户页面
			model.addAttribute("type", "2");
		}	
		if(UserUtil.getCurrentShiroUser().getUsertype().equals("1")){
			long qyid = UserUtil.getCurrentShiroUser().getQyid();
			//企业平面图
			BIS_EnterpriseEntity bis = bisQyjbxxService.findInfoById(qyid);
			model.addAttribute("bis", bis);
			//风险点信息
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("qyid", qyid);
			List<Map<String,Object>> list = fxgkFxxxService.getAllByQyid(map);
			model.addAttribute("fxdlist", JsonMapper.getInstance().toJson(list));
			return "fxgk/fxyt/mapindex-new";
		}else{
			Barrio bro=barrioservice.findPointBycode(UserUtil.getCurrentShiroUser().getXzqy());
			if(bro!=null)
			model.addAttribute("mappoint", bro.getMappoint());
			return "fxgk/fxfbt/mapindex";
		}
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="fxdslist")
	@ResponseBody
	public Map<String, Object> getFxdslist(HttpServletRequest request) {
		Map<String,Object> mapdate = new HashMap<>();
		Map<String,Object> map = new HashMap<>();
		long qyid = UserUtil.getCurrentShiroUser().getQyid();
		map.put("m1", request.getParameter("keyword"));
		map.put("qyid", qyid);
		List<Map<String,Object>> list = fxgkFxxxService.getAllByQyid(map);
		mapdate.put("fxdlist", JsonMapper.getInstance().toJson(list));
//		//企业平面图
//		BIS_EnterpriseEntity bis = bisQyjbxxService.findInfoById(qyid);
//		mapdate.put("pmt", bis.getM33_3());
		return mapdate;
	}
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="timemap")
	public String timemap(Model model) {
		Barrio bro=barrioservice.findPointBycode(UserUtil.getCurrentShiroUser().getXzqy());
		if(bro!=null)
			model.addAttribute("mappoint", bro.getMappoint());
		return "fxgk/fxfbt/timemapindex";
	}
	/**
	 * 企业云图
	 */
	@RequestMapping(value="showfxyt")
	@ResponseBody
	public String showQyLocation(Model model) {
		Map<String, Object> mapdata=getAuthorityMap();
		return JsonMapper.getInstance().toJson(fxgkFxxxService.getQyfxzJson(mapdata));
	}
	/**
	 * 重点重大（风险云图用）
	 */
	@RequestMapping(value="showzdzd")
	@ResponseBody
	public String showZdzdJson(Model model) {
			Map<String, Object> mapdata = getAuthorityMap();
			List<Map<String,Object>> list=bisQyjbxxService.findZdzdMapList(mapdata);
			return JsonMapper.getInstance().toJson(list);
		}
	/**
	 * 重点重大（风险云图用）
	 */
	@RequestMapping(value="barrocolor")
	@ResponseBody
	public String showBarrioJson(Model model) {
		Map<String, Object> mapdata = getAuthorityMap();
		return JsonMapper.getInstance().toJson(fxgkFxxxService.getBarriofxzJson(mapdata));
	}
	
	/**
	 * 获取企业名称集合
	 * @return
	 */
	@RequestMapping(value="qynamelist")
	@ResponseBody
	public List<BIS_EnterpriseEntity> getQynameList() {
		Map<String, Object> map = new HashMap<>();
		BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(UserUtil.getCurrentShiroUser().getQyid());
		map.put("fid", be.getID());
		List<BIS_EnterpriseEntity> list = bisQyjbxxService.dataListE(map);
		return list;
	}
	
	
	/**
	 * 根据企业id获取对应的风险平面图
	 * @return
	 */
	@RequestMapping(value="fxpmt/{qyid}")
	@ResponseBody
	public String getfxpmtByQyid(@PathVariable(value="qyid") Long qyid) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", qyid);
		List<BIS_EnterpriseEntity> list = bisQyjbxxService.dataListE(map);
		String fxpmtUrl = "";
		if (list.size() > 0) {
			BIS_EnterpriseEntity be = list.get(0);
			fxpmtUrl = be.getM33_4();//风险平面图
		}
		return fxpmtUrl;
	}

	/**
	 * 查看页面跳转
	 *
	 * @param id
	 *  ,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, HttpServletRequest request, Model model) {

		Map<String, Object> sgfx = sxgkfxxxService.findInforById(id);
		BIS_EnterpriseEntity entity = bisQyjbxxService.findInfoById(Long.parseLong(sgfx.get("id1").toString()));
		sgfx.put("pmt", entity.getM33_3());// 企业平面图

		//获取风险告知卡下载路径
		String wordpath = "";
		try {
			wordpath=viewka(id,request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sgfx.put("fxgzk", wordpath);

		model.addAttribute("sgfx", sgfx);
		model.addAttribute("usertype",UserUtil.getCurrentShiroUser().getUsertype());
		model.addAttribute("qyname", entity.getM1());
		// 返回页面
		return "fxgk/fxyt/view";
	}

	/**
	 * 根据id导出其告知卡返回下载的路径
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String viewka(@PathVariable("id") Long id, HttpServletRequest request)throws Exception {
		//项目所在物理路径
		String webAddress= request.getSession().getServletContext().getRealPath("/");

		String type = request.getParameter("type");

		Map<String,Object> map = new HashMap<>();
		FXGK_AccidentRisk sgfx = sxgkfxxxService.find_sgfx_ById(id);
		//反转html字符并替换<>&字符串
		com.cczu.util.common.StringUtils.parseBeanForWord(sgfx);
		BIS_EnterpriseEntity bis=bisQyjbxxService.findInfoById(sgfx.getID1());

		//匹配（d）的字符在"（"加上freemarker的换行符
		sgfx.setM10(sgfx.getM10().replaceAll("(\\(|（|\\b)(\\d{1,2}\\s*[\\)）]\\b)", "<w:br/>$1$2").replaceFirst("<w:br/>", ""));
		sgfx.setM11(sgfx.getM11().replaceAll("(\\(|（|\\b)(\\d{1,2}\\s*[\\)）]\\b)", "<w:br/>$1$2"));
		map.put("sgfx", sgfx);
		map.put("qyname", bis.getM1());

		// 获取图片地址
		String image = "", image2 = "";
		List<String> list = new ArrayList<String>();
		List <String>list2 = new ArrayList<String>();
		if (sgfx.getM16() != null && !sgfx.getM16().equals("")) {
			String[] url = sgfx.getM16().split(",");
			for (String u : url) {
				String[] url2 = u.split("[||]");
				//服务器没有项目名，本地需要把 str的项目名去掉

				image = WordUtil.getImageStr(webAddress + url2[0]);
				list.add(image);
			}
		}else{//未上传现场照片的用 默认图片
			image = WordUtil.getImageStr(webAddress+"/static/model/images/fxbs_nopic.jpg");
			list.add(image);
		}

		if (!StringUtils.isEmpty(sgfx.getM17())) {
			String[] url = sgfx.getM17().split(",");
			int j=("A2".equals(type))?3:4;
			int i=0;
			for (String u : url) {
				if(i<j){
					String[] url2 = u.split("[||]");
					image2 = WordUtil.getImageStr(webAddress + url2[0]);
					list2.add(image2);
					i++;
				}
			}
		}

		try {
			map.put("image", list.size()==0?"":list.get(0));
			map.put("image2", list2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 生成二维码
		String text = sgfx.getBindcontent();
		if (StringUtils.isEmpty(text)) {
			text = " ";
		}
		// 生成二维码
		String dowmloadPath = webAddress+"/download";
		map.put("orimg", WordUtil.getImageStr(dowmloadPath+"/"+ QRCode.encode(text, dowmloadPath)));

		String color="";
		String colorcode="";
		String fillcolor="000000";
		switch (sgfx.getM9()) {
			case "1":
				color="红";
				colorcode="C00000";
				fillcolor="FFFFFF";
				break;
			case "2":
				color="橙";
				colorcode="FFA042";
				break;
			case "3":
				color="黄";
				colorcode="FFD700";
				break;
			case "4":
				color="蓝";
				colorcode="2894FF";
			default :
				color="蓝";
				colorcode="2894FF";
				break;
		}
		map.put("color", color);
		map.put("colorcode", colorcode);
		map.put("fillcolor", fillcolor);

		String M1=sgfx.getM1();
		if(M1.indexOf("/") != -1){
			M1=M1.replace("/", "");
		}
		//设置导出的文件名
		String filename =  bis.getM1()+ "_" + M1 + ".doc";

		int h1=sgfx.getM10().length();
		int h2=sgfx.getM11().length();
		if("A3".equals(type)){
			if(StringUtils.isEmpty(sgfx.getM10())||StringUtils.isEmpty(sgfx.getM11())){
				map.put("h1", FTL_A3_HEIGHT>>1);
				map.put("h2", FTL_A3_HEIGHT>>1);
			}else{
				int WEIGHT = 0;//优化高度权值
				map.put("h1", (int)(FTL_A3_HEIGHT*(((float)h1/(h1+h2)))+WEIGHT));
				map.put("h2", (int)(FTL_A3_HEIGHT*(((float)h2/(h1+h2)))-WEIGHT));
			}
			WordUtil.ireportWord(map, "sgfxA3.ftl", dowmloadPath, filename, request);
		}
		return "download/"+filename;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
