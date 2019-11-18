package com.cczu.model.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
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

import com.cczu.model.dao.AqzfZfryDao;
import com.cczu.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.model.entity.AQZF_TipstaffEntity;
import com.cczu.model.entity.XZCF_InterrogationRecordEntity;
import com.cczu.model.service.AqzfSetBasicInfoService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.IXzcfCommonLaspService;
import com.cczu.model.service.XzcfXwblService;
import com.cczu.model.service.XzcfXwtzService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;
import com.cczu.util.common.WordUtil;

/**
 * 询问笔录
 * @author zpc
 * @date 2017/08/05
 */
@Controller
@RequestMapping("xzcf/ybcf/xwbl")
public class PageXzcfXwblController extends BaseController{

	@Autowired
	private XzcfXwblService xzcfXwblService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService; 
	@Autowired
	private XzcfXwtzService xzcfXwtzService;
	@Autowired
	private IXzcfCommonLaspService punishcommonlaspservice;
	@Resource
	private AqzfZfryDao aqzfZfryDao;
	@Autowired
	private AqzfSetBasicInfoService setbasicservice;
	
	/**
	 * 默认页面
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/xwbl/index";
	}
	
	/**
	 * list页面
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("xzqy", UserUtil.getCurrentShiroUser().getXzqy());
		map.put("qyname", request.getParameter("aqzf_xwbl_qyname"));
		map.put("m1", request.getParameter("aqzf_xwbl_M1"));
		map.put("m2", request.getParameter("aqzf_xwbl_M2"));
		return xzcfXwblService.dataGrid(map);
	}
	
	/**
	 * 删除询问笔录
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			xzcfXwblService.deleteInfo(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}

	/**
	 * 添加询问笔录页面跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "create/{id}" , method = RequestMethod.GET)
	public String create(@PathVariable("id") Long id,Model model) {
		model.addAttribute("action", "create");
		Map<String,Object> map = xzcfXwtzService.findById3(id);
		XZCF_InterrogationRecordEntity xwbl = new XZCF_InterrogationRecordEntity();
		xwbl.setID2(id);//立案ID
		xwbl.setM3(map.get("m3").toString());//询问地点
		xwbl.setM8(map.get("qyname").toString());//工作单位
		xwbl.setM16(map.get("m1").toString());//案由
		model.addAttribute("xwbl", xwbl);
		return "aqzf/xzcf/ybcf/xwbl/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" )
	@ResponseBody
	public String create(XZCF_InterrogationRecordEntity xwbl, HttpServletRequest request) {
		String datasuccess="success";
		String zw = request.getParameter("zw");
		if(!StringUtils.isEmpty(xwbl.getM9())){
			xwbl.setM9(zw+":"+xwbl.getM9());
		}
		xwbl.setM15(xwbl.getM12());
		ShiroUser su = UserUtil.getCurrentShiroUser();
		xwbl.setID1(su.getId());
		xwbl.setM17(request.getParameter("M17"));
		xzcfXwblService.addInfo(xwbl);
		return datasuccess;
	}
	
	
	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		XZCF_InterrogationRecordEntity xwbl  = xzcfXwblService.findById(id);
		if(!StringUtils.isEmpty(xwbl.getM9())){
			String[] a = xwbl.getM9().split(":");
			model.addAttribute("zw",a[0]);
            xwbl.setM9(a[1]);
		}else{
			model.addAttribute("zw",1);
		}
		model.addAttribute("xwbl", xwbl);
		model.addAttribute("action", "update");
		return "aqzf/xzcf/ybcf/xwbl/form";
	}
	
	/**
	 * 修改
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(XZCF_InterrogationRecordEntity zfry, Model model, HttpServletRequest request){
		String datasuccess="success";	
		String zw = request.getParameter("zw");
		if(!StringUtils.isEmpty(zfry.getM9())){
			zfry.setM9(zw+":"+zfry.getM9());
		}
		ShiroUser su = UserUtil.getCurrentShiroUser();
		zfry.setID1(su.getId());
		zfry.setM15(zfry.getM12());
		zfry.setM17(request.getParameter("M17"));
		xzcfXwblService.updateInfo(zfry);
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		//查询选择的询问笔录信息
		XZCF_InterrogationRecordEntity xwbl = xzcfXwblService.findById(id);	
		if(!StringUtils.isEmpty(xwbl.getM9())){
			String[] a = xwbl.getM9().split(":");
            xwbl.setM9(a[1]);
		}
		model.addAttribute("xwbl", xwbl);
		return "aqzf/xzcf/ybcf/xwbl/view";
	}
	
	/**
	 * 导出询问笔录书word
	 * 
	 */
	@RequiresPermissions("aqzf:xwbl:export")
	@RequestMapping(value = "export/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		XZCF_InterrogationRecordEntity xwbl = xzcfXwblService.findById(id);
		if(!StringUtils.isEmpty(xwbl.getM9())){
			String[] a = xwbl.getM9().split(":");
            xwbl.setM9(a[1]);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		//询问起始时间解析
		if(xwbl.getM1()!=null&&!xwbl.getM1().toString().equals("")){
			String a = xwbl.getM1().toString();
			String[] as1 = a.substring(0,10).split("-");
			map.put("year", as1[0]);
			map.put("month1", as1[1]);
			map.put("day1", as1[2]);
			String[] bs1 = a.substring(11,16).split(":");
			map.put("hour1", bs1[0]);
			map.put("min1", bs1[1]);
		}else{
			map.put("year", "    ");
			map.put("month1", "   ");
			map.put("day1", "   ");
			map.put("hour1", "   ");
			map.put("min1", "   ");
		}
		//询问结束时间解析
		if(xwbl.getM2()!=null&&!xwbl.getM2().toString().equals("")){
			String b = xwbl.getM2().toString();
			String[] as2 = b.substring(0,10).split("-");
			map.put("month2", as2[1]);
			map.put("day2", as2[2]);
			String[] bs2 = b.substring(11,16).split(":");
			map.put("hour2", bs2[0]);
			map.put("min2", bs2[1]);
		}else{
			map.put("month2", "   ");
			map.put("day2", "  ");
			map.put("hour2", "   ");
			map.put("min2", "   ");
		}
		//检查人员解析
		if(!StringUtils.isEmpty(xwbl.getM15())){
			String c = xwbl.getM15();
			String[] as3 = c.split(",");
			//证件号
			String zjh = "";
			String m15= "";
			for(int i=0;i<as3.length;i++){
				m15 = m15 + as3[i] + "、";
				AQZF_TipstaffEntity a = aqzfZfryDao.findByM1(as3[i]);
	            zjh = zjh + a.getM3() + "、";		
			}
			zjh=zjh.substring(0,zjh.length()-1);
			m15=m15.substring(0,m15.length()-1);
			map.put("m15", m15);
		    map.put("zjh", zjh);
		}else{
			map.put("m15", "                 ");
			 map.put("zjh", "                 ");
		}
		map.put("m0", StringUtils.isEmpty(xwbl.getM0())?"   ":xwbl.getM0());
		map.put("m3", StringUtils.isEmpty(xwbl.getM3())?"                   ":xwbl.getM3());
		map.put("m4", StringUtils.isEmpty(xwbl.getM4())?"      ":xwbl.getM4());
		map.put("m5", StringUtils.isEmpty(xwbl.getM5())?"   ":xwbl.getM5());
		map.put("m6", StringUtils.isEmpty(xwbl.getM6())?"   ":xwbl.getM6());
		map.put("m7", StringUtils.isEmpty(xwbl.getM7())?"              ":xwbl.getM7());
		map.put("m8", StringUtils.isEmpty(xwbl.getM8())?"                             ":xwbl.getM8());
		map.put("m9", StringUtils.isEmpty(xwbl.getM9())?"           ":xwbl.getM9());
		map.put("m10", StringUtils.isEmpty(xwbl.getM10())?"                                   ":xwbl.getM10());
		map.put("m11", StringUtils.isEmpty(xwbl.getM11())?"              ":xwbl.getM11());
		
		AQZF_SetBasicInfoEntity bh =setbasicservice.findInfor();
		map.put("ssqmc", bh.getSsqmc());
		//询问人单位职务
		if(!StringUtils.isEmpty(xwbl.getM12())){
			String d = xwbl.getM12();
			String[] ds = d.split(",");
			String ddd="";
			for(int i=0;i<ds.length;i++){
				ddd = ddd + "<w:p><w:pPr><w:spacing w:line-rule='auto' w:line='360'/><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/></w:rPr></w:pPr><w:r><w:rPr>"
						+ "<w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/></w:rPr><w:t>询问人</w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "<w:u w:val='single'/></w:rPr><w:t> </w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "<w:u w:val='single'/><w:lang w:fareast='ZH-CN' w:val='EN-US'/></w:rPr><w:t>  "+ds[i]+"</w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "<w:u w:val='single'/></w:rPr><w:t> </w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "<w:u w:val='single'/><w:lang w:fareast='ZH-CN' w:val='EN-US'/></w:rPr><w:t> </w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "<w:u w:val='single'/></w:rPr><w:t> </w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "</w:rPr><w:t>单位及职务</w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "<w:u w:val='single'/></w:rPr><w:t> </w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "<w:u w:val='single'/><w:lang w:fareast='ZH-CN' w:val='EN-US'/></w:rPr><w:t> "+bh.getSsqmc()+"安全生产监督管理局"+aqzfZfryDao.findByM1(ds[i]).getM4()+"                    </w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/><w:u w:val='single'/></w:rPr><w:t> </w:t></w:r></w:p>";
			}
			map.put("m12",ddd);
		}else{
			map.put("m12","<w:p><w:pPr><w:spacing w:line-rule='auto' w:line='360'/><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/></w:rPr></w:pPr><w:r><w:rPr>"
						+ "<w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/></w:rPr><w:t>询问人</w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "<w:u w:val='single'/></w:rPr><w:t> </w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "<w:u w:val='single'/><w:lang w:fareast='ZH-CN' w:val='EN-US'/></w:rPr><w:t>             </w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "<w:u w:val='single'/></w:rPr><w:t> </w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "<w:u w:val='single'/><w:lang w:fareast='ZH-CN' w:val='EN-US'/></w:rPr><w:t> </w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "<w:u w:val='single'/></w:rPr><w:t> </w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "</w:rPr><w:t>单位及职务</w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "<w:u w:val='single'/></w:rPr><w:t> </w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "<w:u w:val='single'/><w:lang w:fareast='ZH-CN' w:val='EN-US'/></w:rPr><w:t>                                                     </w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/><w:u w:val='single'/></w:rPr><w:t> </w:t></w:r></w:p>"
						+ "<w:p><w:pPr><w:spacing w:line-rule='auto' w:line='360'/><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/></w:rPr></w:pPr><w:r><w:rPr>"
						+ "<w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/></w:rPr><w:t>询问人</w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "<w:u w:val='single'/></w:rPr><w:t> </w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "<w:u w:val='single'/><w:lang w:fareast='ZH-CN' w:val='EN-US'/></w:rPr><w:t>             </w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "<w:u w:val='single'/></w:rPr><w:t> </w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "<w:u w:val='single'/><w:lang w:fareast='ZH-CN' w:val='EN-US'/></w:rPr><w:t> </w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "<w:u w:val='single'/></w:rPr><w:t> </w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "</w:rPr><w:t>单位及职务</w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "<w:u w:val='single'/></w:rPr><w:t> </w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/>"
						+ "<w:u w:val='single'/><w:lang w:fareast='ZH-CN' w:val='EN-US'/></w:rPr><w:t>                                                       </w:t></w:r><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:fareast='宋体' w:ascii='宋体' w:hint='fareast'/><w:u w:val='single'/></w:rPr><w:t> </w:t></w:r></w:p>");
		}	
		//记录人单位职务
		if(!StringUtils.isEmpty(xwbl.getM13())){
			map.put("m13",xwbl.getM13());
			map.put("m13_1"," "+bh.getSsqmc()+"安全生产监督管理局"+aqzfZfryDao.findByM1(xwbl.getM13()).getM4());
		}else{
			map.put("m13","           ");
			map.put("m13_1","                                    ");
		}	
		map.put("m14", StringUtils.isEmpty(xwbl.getM14())?"                           ":xwbl.getM14());
		map.put("m16", StringUtils.isEmpty(xwbl.getM16())?"                      ":xwbl.getM16());
		//处理m17
		String z = xwbl.getM17();
		String M17 = "";
		if(!StringUtils.isEmpty(z)){
			String[] zs = z.split("<br />");
			for(int i=0;i<zs.length;i++){
			   	M17 = M17+"<w:p><w:pPr><w:spacing w:line-rule='exact' w:line='520'/><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:ascii='宋体' w:hint='fareast'/><w:u w:val='single'/><w:lang w:fareast='ZH-CN' w:val='EN-US'/></w:rPr></w:pPr><w:r><w:rPr><w:rFonts w:cs='宋体' w:h-ansi='宋体' w:ascii='宋体' w:hint='fareast'/><w:u w:val='single'/><w:lang w:fareast='ZH-CN' w:val='EN-US'/></w:rPr>"
						+"<w:t>" + zs[i] +"</w:t></w:r></w:p>";
			}
		}
		map.put("m17", M17);
		
		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "xwbl.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
}
