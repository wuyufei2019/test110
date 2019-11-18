package com.cczu.model.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.model.entity.XZCF_CfjdInfoEntity;
import com.cczu.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.model.entity.XZCF_YbcfSxcgEntity;
import com.cczu.model.service.AqzfSetBasicInfoService;
import com.cczu.model.service.AqzfSetNumberService;
import com.cczu.model.service.IXzcfCfjdService;
import com.cczu.model.service.IXzcfCommonLaspService;
import com.cczu.model.service.XzcfCommonSxcgService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 行政处罚--事先催告controller
 * 
 * @author jason
 * 
 */
@Controller
@RequestMapping("xzcf/ybcf/sxcg")
public class PageXzcfCommonSxcgController extends BaseController {

	@Autowired
	private XzcfCommonSxcgService  xzcfcommonsxcgservice;
	@Autowired
	private IXzcfCommonLaspService xzcfcommonlaspservice;
	@Autowired
	private IXzcfCfjdService xzcfcfjdservice;
	@Autowired
	private AqzfSetNumberService setNumberService;
	@Autowired
	private AqzfSetBasicInfoService aqzfsetbasicinfoservice;
	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "aqzf/xzcf/ybcf/sxcg/index";
	}

	/**
	 * list页面
	 */
	@RequiresPermissions("xzcf:sxcg:view")
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("number", request.getParameter("xzcf_sxcg_number"));
		map.put("name", request.getParameter("xzcf_sxcg_name"));
		map.put("type", request.getParameter("xzcf_sxcg_type"));
		map.put("xzqy",UserUtil.getCurrentShiroUser().getXzqy());
		return xzcfcommonsxcgservice.dataGrid(map);
	}
	

	/**
	 * 添加事先催告信息页面跳转
	 * id:处罚决定id
	 * @param model
	 */
	@RequiresPermissions("xzcf:sxcg:add")
	@RequestMapping(value = "create/{id}")
	public String create(@PathVariable("id") long id,Model model) {
		
		XZCF_CfjdInfoEntity cfjd= xzcfcfjdservice.findInfoById(id);
		model.addAttribute("id1", cfjd.getId1());
		XZCF_YbcfSxcgEntity yse =new XZCF_YbcfSxcgEntity();
		yse.setQyname(cfjd.getPunishname());
		yse.setXzjd(cfjd.getPunishresult());
		model.addAttribute("yse", yse);
		model.addAttribute("action", "createSub");
		return "aqzf/xzcf/ybcf/sxcg/form";
	}

	/**
	 * 添加
	 * @param request
	 */
	@RequiresPermissions("xzcf:sxcg:add")
	@RequestMapping(value = "createSub", method = RequestMethod.POST)
	@ResponseBody
	public String createSub(XZCF_YbcfSxcgEntity yse,
			HttpServletRequest request, Model model) throws IOException {
		String datasuccess = "failed";
		Timestamp t = DateUtils.getSysTimestamp();
		yse.setS1(t);
		yse.setS2(t);
		yse.setS3(0);
		
		//设置编号
		AQZF_SetBasicInfoEntity bh =aqzfsetbasicinfoservice.findInfor();
		XZCF_YbcfLaspEntity ybcf = xzcfcommonlaspservice.findInfoById(yse.getId1());
		String m0 = ybcf.getNumber();
		yse.setNumber("（"+bh.getSsqjc()+"）安监执行催告〔"+DateUtils.getYear()+"〕"+m0.substring(m0.indexOf("〕")+1, m0.length()));
		
		if (xzcfcommonsxcgservice.addInfoReturnID(yse) > 0) {
			XZCF_YbcfLaspEntity yle= xzcfcommonlaspservice.findInfoById(yse.getId1());
			yle.setCgflag("1");
			xzcfcommonlaspservice.updateInfo(yle);
			datasuccess = "success";
		}
		return datasuccess;
	}

	/**
	 * 修改事先催告信息页面跳转
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:sxcg:update")
	@RequestMapping(value = "update/{id}")
	public String update(@PathVariable("id") long id, Model model) {

		XZCF_YbcfSxcgEntity yse = xzcfcommonsxcgservice.findInfoById(id);
		model.addAttribute("yse", yse);
		//model.addAttribute("jid", id);
		model.addAttribute("action", "updateSub");
		return "aqzf/xzcf/ybcf/sxcg/form";
	}

	/**
	 * 修改事先催告信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:sxcg:update")
	@RequestMapping(value = "updateSub")
	@ResponseBody
	public String updateSub(XZCF_YbcfSxcgEntity yse,
			HttpServletRequest request, Model model) {
		String datasuccess = "success";
		Timestamp t = DateUtils.getSysTimestamp();
		yse.setS2(t);
		try {
			xzcfcommonsxcgservice.updateInfo(yse);
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="failed";
		}
		return datasuccess;
	}

	/**
	 * 查看事先催告信息
	 * 
	 * @param model
	 */
	@RequiresPermissions("xzcf:sxcg:view")
	@RequestMapping(value = "view/{id}")
	public String view(@PathVariable("id") long id, Model model) {
		XZCF_YbcfSxcgEntity yse = xzcfcommonsxcgservice.findInfoById(id);
		model.addAttribute("yse", yse);
		return "aqzf/xzcf/ybcf/sxcg/view";
	}

	/**
	 * 删除事先催告信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("xzcf:sxcg:delete")
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		try {
			for (int i = 0; i < arrids.length; i++) {
				xzcfcommonsxcgservice.deleteInfo(Long.parseLong(arrids[i]));
				xzcfcommonsxcgservice.updateLaspInfo(Long.parseLong(arrids[i]));
			}
		} catch (Exception e) {
			// TODO: handle exception
			datasuccess="删除失败";
		}
		
		return datasuccess;
	}
	
	/**
     * 数字金额大写转换，思想先写个完整的然后将如零拾替换成零
     * 要用到正则表达式
     */
    public static String digitUppercase(double n){
        String fraction[] = {"角", "分"};
        String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String unit[][] = {{"元", "万", "亿"},
                     {"", "拾", "佰", "仟"}};
 
        n = Math.abs(n);
         
        String s = "";
        for (int i = 0; i < fraction.length; i++) {
            s += (digit[(int)(Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
        }
        if(s.length()<1){
            s = "整";    
        }
        int integerPart = (int)Math.floor(n);
 
        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String p ="";
            for (int j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[integerPart%10]+unit[1][j] + p;
                integerPart = integerPart/10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
        }
        return s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }

	
	/**
	 * 导出事先催告记录word
	 * 
	 */
	@RequiresPermissions("xzcf:sxcg:export")
	@RequestMapping(value = "exportsxcg/{flag}/{id}")
	@ResponseBody
	public String getAjWord(@PathVariable("flag") String  flag,@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		XZCF_YbcfSxcgEntity yse;
		AQZF_SetBasicInfoEntity sbe=aqzfsetbasicinfoservice.findInfor();
		
		if("la".equals(flag)){
			yse=xzcfcommonsxcgservice.findInfoByLaId(id);
		}
		else
			yse= xzcfcommonsxcgservice.findInfoById(id);
		XZCF_CfjdInfoEntity cfjd=xzcfcfjdservice.findInfoByLaId(yse.getId1());
		String html="<w:sym w:font=\"Wingdings 2\" w:char=\"0052\"/>";
		String html2="<w:t>□</w:t>";
	    Calendar cal = Calendar.getInstance();
		cal.setTime(cfjd.getPunishdate());
		Map<String, Object> map=new HashMap<String, Object>();
	     map.put("dw",(yse.getQyname().length()>=4?"你单位":"你"));
		 map.put("number", yse.getNumber());
		 map.put("qyname", yse.getQyname());
		 map.put("year",String.valueOf(cal.get(cal.YEAR)));
		 map.put("month",cal.get(cal.MONTH));
		 map.put("day",cal.get(cal.DAY_OF_MONTH));
		 map.put("xzjd",yse.getXzjd());
		 cal.setTime(yse.getFinedate());
		 map.put("fineyear",String.valueOf(cal.get(cal.YEAR)));
		 map.put("finemonth",cal.get(cal.MONTH));
		 map.put("fineday",cal.get(cal.DAY_OF_MONTH));
		 map.put("fine", digitUppercase(Double.parseDouble(yse.getFine())));
		 map.put("extrafine",digitUppercase(Double.parseDouble(yse.getExtrafine())));
		 map.put("allfine",digitUppercase(Double.parseDouble(yse.getAllfine())));
		 if(StringUtils.isNotBlank(yse.getExtraxzjd()))
				map.put("qt",html);
			else
				map.put("qt",html2);
		 map.put("extraxzjd", yse.getExtraxzjd());
		 map.put("bankname", sbe.getBankname());
		 map.put("account", sbe.getAccount());
		 map.put("ssqmc", sbe.getSsqmc());

		//设置导出的文件名
		String filename = DateUtils.getDateRandom() + "_" + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "xzcfsxcg.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
}
