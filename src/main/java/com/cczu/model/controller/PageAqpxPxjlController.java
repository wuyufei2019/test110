package com.cczu.model.controller;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.AQPX_ItembankEntity;
import com.cczu.model.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.AQPX_TestguizeEntity;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全培训管理——安全培训记录Controller
 * @author jason
 */
@Controller
@RequestMapping("aqpx/aqpxjl")
public class PageAqpxPxjlController extends BaseController {
	
	@Autowired
	private IAqpxKscjService aqpxKscjService;
	@Autowired
	private IAqpxXxjlService aqpxXxjlService;
	@Autowired
	private IAqpxKsjlService aqpxKsjlService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private IAqpxGzxxService aqpxgzxxservice;
    @Autowired
    private IAqpxStkxxService aqpxStkxxService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					return "aqpx/aqpxjl/ajindex";
				else
					return "aqpx/aqpxjl/index";
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else {//非企业用户页面
			return "aqpx/aqpxjl/ajindex";
		}
	}
	

	/**
	 * 考试记录list页面(企业普通用户)
	 * @param request
	 */
	@RequiresPermissions("aqpx:pxjl:view")
	@RequestMapping(value="kslistyg")
	@ResponseBody
	public Map<String, Object> getksData(HttpServletRequest request) {
		
		int pageNo=1;	//当前页码
		int pageSize=20;	//每页行数
		if(StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo=Integer.valueOf(request.getParameter("page"));
		if(StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize=Integer.valueOf(request.getParameter("rows"));
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		map.put("ygid", sessionuser.getId());
		map.put("ygid2", request.getParameter("aqpx_pxtj_cx_id"));
		map.put("jhid", request.getParameter("jhid"));
		map.put("sort", request.getParameter("sort"));
		map.put("order", request.getParameter("order"));
		map.put("kcname", request.getParameter("aqpx_pxjl_cx_m1"));
		map.put("ksjg", request.getParameter("aqpx_pxjl_cx_m3"));
		map.put("type", request.getParameter("type"));
		map.put("kclx", request.getParameter("kclx"));//课程类型
		return aqpxKscjService.dataGrid(map);
		
	}	
	
	/**
	 * 考试记录list页面(企业管理员用户)
	 * @param request
	 */
	@RequestMapping(value="kslist")
	@ResponseBody
	public Map<String, Object> getksDataCompanyAdmin(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("aqpx_pxjl_qyname"));
		map.put("kcname", request.getParameter("aqpx_pxjl_cx_m1"));
		map.put("ygname", request.getParameter("aqpx_pxjl_cx_m2"));
		map.put("ksjg", request.getParameter("aqpx_pxjl_cx_m3"));
		map.put("kclx", request.getParameter("kclx"));//课程类型
		map.put("starttime", request.getParameter("starttime"));
		map.put("finishtime", request.getParameter("finishtime"));
		map.putAll(getAuthorityMap());
		return aqpxKscjService.dataGridCompanyAdmin(map);
	}

	/**
	 * 外来方考试记录list页面(企业管理员用户)
	 * @param request
	 */
	@RequestMapping(value="wlfkslist")
	@ResponseBody
	public Map<String, Object> getWlfksDataCompanyAdmin(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("aqpx_pxjl_qyname"));
		map.put("kcname", request.getParameter("aqpx_pxjl_cx_m1"));
		map.put("wlfname", request.getParameter("aqpx_pxjl_cx_m4"));
		map.put("ksjg", request.getParameter("aqpx_pxjl_cx_m3"));
		map.put("kclx", request.getParameter("kclx"));//课程类型
		map.putAll(getAuthorityMap());
		return aqpxKscjService.dataGridCompanyAdmin2(map);
	}
	
	/**
	 * 学习记录list页面(企业普通用户)
	 * @param request
	 */
	@RequiresPermissions("aqpx:pxjl:view")
	@RequestMapping(value="xxlistyg")
	@ResponseBody
	public Map<String, Object> getxxData(HttpServletRequest request) {
		
		int pageNo=1;	//当前页码
		int pageSize=20;	//每页行数
		if(StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo=Integer.valueOf(request.getParameter("page"));
		if(StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize=Integer.valueOf(request.getParameter("rows"));
		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		map.put("ygid", sessionuser.getId());
		map.put("sort", request.getParameter("sort"));
		map.put("order", request.getParameter("order"));
		map.put("kcname", request.getParameter("aqpx_pxjl_cx_m1"));

		return aqpxXxjlService.dataGrid(map);
		
	}

	
	/**
	 * 学习记录list页面(企业管理员用户)
	 * @param request
	 */
	@RequestMapping(value="xxlist")
	@ResponseBody
	public Map<String, Object> getxxDataCompanyAdmin(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("aqpx_pxjl_qyname"));
		map.put("kcname", request.getParameter("aqpx_pxjl_cx_m1"));
		map.put("ygname", request.getParameter("aqpx_pxjl_cx_m2"));
		map.put("starttime", request.getParameter("starttime"));
		map.put("finishtime", request.getParameter("finishtime"));
		map.putAll(getAuthorityMap());
		return aqpxXxjlService.dataGridCompanyAdmin(map);
		
	}
	
	/**
     *  查看考试试卷记录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "view/{h}", method = RequestMethod.GET)
    public String viewSJ(@PathVariable("h") String h, Model model) {
        List<Map<String, Object>> list= aqpxKsjlService.getsjxx(h);
        String jhid=list.get(0).get("id4").toString();
        AQPX_TestguizeEntity stgz=aqpxgzxxservice.findbyid(Long.parseLong(jhid));
        model.addAttribute("stgz",stgz);
        model.addAttribute("list",list);
        return "aqpx/aqpxjl/view";
    }


    /**
     *  错题订正
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "viewdz/{h}", method = RequestMethod.GET)
    public String viewDZ(@PathVariable("h") String h, Model model) {
        List<Map<String, Object>> list= aqpxKsjlService.getctxx(h);
        AQPX_TestguizeEntity stgz = new AQPX_TestguizeEntity();
        if (list.size()>0) {
            String jhid=list.get(0).get("id4").toString();
            stgz = aqpxgzxxservice.findbyid(Long.parseLong(jhid));
        }
        model.addAttribute("stgz",stgz);
        model.addAttribute("list",list);
        return "aqpx/aqpxjl/viewdz";
    }

    /**
     * 提交错题订正
     * @param request
     */
    @RequestMapping(value = "submit", method = RequestMethod.POST)
    @ResponseBody
    public List<String> submitKS(HttpServletRequest request, Model model) {
        List<String> list = new ArrayList<>();
        //获取所有试题结果
        Map< String, String[]> map=request.getParameterMap();
        for(Map.Entry<String, String[]> entry:map.entrySet()){
            String key=entry.getKey();
            String values[]=entry.getValue();

            if(key.indexOf("dx_")==0||key.indexOf("tk_")==0||key.indexOf("pd_")==0){
                String id=key.substring(3).trim();
                String val =values[0];
                AQPX_ItembankEntity itembankEntity=aqpxStkxxService.findByid(Long.parseLong(id));
                if(itembankEntity.getM8().equalsIgnoreCase(val)){//判断答案是否正确 不分大小写
                    list.add(key);
                }
            }else if(key.indexOf("dsx_")==0){
                String id=key.substring(4).trim();
                AQPX_ItembankEntity itembankEntity=aqpxStkxxService.findByid(Long.parseLong(id));
                if(itembankEntity.getM8().equalsIgnoreCase(StringUtils.join(values))){//判断多选答案是否正确 不分大小写
                    list.add(key);
                }
            }
        }
        return list;

    }
	
	//根据id获取学习信息
	@RequestMapping(value="xxlistyg/{ygid}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getygxxData(@PathVariable("ygid") String ygid,HttpServletRequest request) {
		int pageNo=1;	//当前页码
		int pageSize=20;	//每页行数
		if(StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo=Integer.valueOf(request.getParameter("page"));
		if(StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize=Integer.valueOf(request.getParameter("rows"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		map.put("ygid", ygid);
		map.put("sort", request.getParameter("sort"));
		map.put("order", request.getParameter("order"));
		return aqpxXxjlService.dataGrid(map);
		
	}
	
	//根据id获取考试信息
	@RequestMapping(value="kslistyg/{ygid}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getygksData(@PathVariable("ygid") String ygid,HttpServletRequest request) {
		int pageNo=1;	//当前页码
		int pageSize=20;	//每页行数
		if(StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo=Integer.valueOf(request.getParameter("page"));
		if(StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize=Integer.valueOf(request.getParameter("rows"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		map.put("ygid", ygid);
		map.put("sort", request.getParameter("sort"));
		map.put("order", request.getParameter("order"));
		return aqpxKscjService.dataGrid(map);
		
	}	
	
	/**
	 * 显示所有列
	 * @param id,model 
	 */
	@RequiresPermissions("aqpx:pxjl:view")
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","aqpx/aqpxjl/export");
		return "common/formexcel";
	}
	/**
	 * 显示所有列
	 * @param id,model 
	 */
	@RequiresPermissions("aqpx:pxjl:view")
	@RequestMapping(value = "colindex2", method = RequestMethod.GET)
	public String colindex2(Model model) {
		model.addAttribute("url","aqpx/aqpxjl/export2");
		return "common/formexcel";
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("aqpx:pxjl:view")
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("aqpx_pxjl_qyname"));
		map.put("kcname", request.getParameter("aqpx_pxjl_cx_m1"));
		map.put("ygname", request.getParameter("aqpx_pxjl_cx_m2"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		aqpxXxjlService.exportExcel(response, map);
	}
	
	/**
	 * 导出excel
	 * 
	 */
	@RequiresPermissions("aqpx:pxjl:view")
	@RequestMapping(value = "export2")
	@ResponseBody
	public void export2(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("aqpx_pxjl_qyname"));
		map.put("kcname", request.getParameter("aqpx_pxjl_cx_m1"));
		map.put("ygname", request.getParameter("aqpx_pxjl_cx_m2"));
		map.put("ksjg", request.getParameter("aqpx_pxjl_cx_m3"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		aqpxKscjService.exportExcel(response, map);
	}
	
	/**
	 * 删除考试记录
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequiresPermissions("aqpx:pxjl:delete")
	@RequestMapping(value = "deleteKs/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功！";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqpxKscjService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 删除学习记录
	 * 
	 * @param user
	 * @param model
	 * @throws ParseException 
	 */
	@RequiresPermissions("aqpx:pxjl:delete")
	@RequestMapping(value = "deleteXx/{ids}")
	@ResponseBody
	public String deleteXx(@PathVariable("ids") String ids) {
		String datasuccess="删除成功！";
		//可以批量删除
		String[] arrids = ids.split(",");
		for(int i=0;i<arrids.length;i++){
			aqpxXxjlService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 根据考试计划统计员工考试完成情况
	 * @param jhid 计划id
	 * @param type 选择类型（）
	 * @param request
	 * @return
	 */
		@RequestMapping(value="jllistbyjh", method = RequestMethod.POST)
		@ResponseBody
		public List<Map<String, Object>> getksjlByJH( String jhid,String type,HttpServletRequest request) {
			int pageNo=1;	//当前页码
			int pageSize=20;	//每页行数
			if(StringUtils.isNotEmpty(request.getParameter("page")))
				pageNo=Integer.valueOf(request.getParameter("page"));
			if(StringUtils.isNotEmpty(request.getParameter("rows")))
				pageSize=Integer.valueOf(request.getParameter("rows"));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageNo", pageNo);
			map.put("pageSize", pageSize);
			map.put("jhid", jhid);
			map.put("type", type);
			return aqpxKscjService.getksjlByJH(map);
			
		}	
		
	
	
}
