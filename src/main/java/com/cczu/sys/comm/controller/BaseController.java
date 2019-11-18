package com.cczu.sys.comm.controller;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Department;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础控制器 其他控制器继承此控制器获得日期字段类型转换和防止XSS攻击的功能
 * 
 * @description
 * @author jason
 */
public class BaseController {
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	
	/**
	 * 日志对象
	 */
	protected final Log log = LogFactory.getLog(getClass());

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
//		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
//			@Override
//			public void setAsText(String text) {
//				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
//			}
//
//			@Override
//			public String getAsText() {
//				Object value = getValue();
//				return value != null ? value.toString() : "";
//			}
//		});

		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
		});

		// Timestamp 类型转换
		binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				Date date = DateUtils.parseDate(text);
				setValue(date == null ? null : new Timestamp(date.getTime()));
			}
		});
	}

	/**
	 * 获取page对象
	 * 
	 * @param request
	 * @return page对象
	 */
	public <T> Page<T> getPage(HttpServletRequest request) {
		int pageNo = 1; // 当前页码
		int pageSize = 200; // 每页行数
		String orderBy = "id"; // 排序字段
		String order = "asc"; // 排序顺序
		if (StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo = Integer.valueOf(request.getParameter("page"));
		if (StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize = Integer.valueOf(request.getParameter("rows"));
		if (StringUtils.isNotEmpty(request.getParameter("sort")))
			orderBy = request.getParameter("sort").toString();
		if (StringUtils.isNotEmpty(request.getParameter("order")))
			order = request.getParameter("order").toString();
		return new Page<T>(pageNo, pageSize, orderBy, order);
	}

	/**
	 * 获取easyui分页数据
	 * 
	 * @param page
	 * @return map对象
	 */
	public <T> Map<String, Object> getEasyUIData(Page<T> page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", page.getResult());
		map.put("total", page.getTotalCount());
		return map;
	}

	public <T> Map<String, Object> getEasyUIByList(List<T> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", list.size());
		return map;
	}


	/**
	 * 获取request对象
	 * 
	 * @param request
	 * @return page对象
	 */
	public Map<String, Object> getPageMap(HttpServletRequest request) {
		int pageNo = 1; // 当前页码
		int pageSize = 50; // 每页行数
		String orderBy = ""; // 排序字段
		String order = ""; // 排序顺序
		if (StringUtils.isNotEmpty(request.getParameter("page")))
			pageNo = Integer.valueOf(request.getParameter("page"));
		if (StringUtils.isNotEmpty(request.getParameter("rows")))
			pageSize = Integer.valueOf(request.getParameter("rows"));
		if (StringUtils.isNotEmpty(request.getParameter("sort")))
			orderBy = request.getParameter("sort").toString();
		if (StringUtils.isNotEmpty(request.getParameter("order")))
			order = request.getParameter("order").toString();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		map.put("orderBy", orderBy);
		map.put("order", order);
		return map;
	}
	/**
	 * 获取权限分析获取map对象
	 * @param 
	 * @return Map对象
	 */
	public Map<String, Object> getAuthorityMap() {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String,Object> authoritymap= new HashMap<>();
		//安监
		if("0".equals(sessionuser.getUsertype())){
			authoritymap.put("xzqy", sessionuser.getXzqy());	
			if(sessionuser.getUserroleflg()!=null&&sessionuser.getUserroleflg()!=0){
				authoritymap.put("jglx",sessionuser.getUserroleflg());
			}
		}else if("5".equals(sessionuser.getUsertype())){//第三方
			authoritymap.put("cjz", sessionuser.getId());
		}else if("1".equals(sessionuser.getUsertype())){//企业
			BIS_EnterpriseEntity be = bisQyjbxxService.findInfoById(sessionuser.getQyid());
			if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
				authoritymap.put("fid", sessionuser.getQyid());
			else 
				authoritymap.put("qyid", sessionuser.getQyid());
		}

		//判断用户部门权限
		Department dep=sessionuser.getDep();
		if(dep!=null){
			String qxbs=dep.getM4();
			if(qxbs.equals("1"))//本级
				authoritymap.put("depcode1", dep.getCode());
			if(qxbs.equals("2"))//下级
				authoritymap.put("depcode2", dep.getCode());
		}

		//判断用户部门范围
		String depids="";
		if(sessionuser.getDepIdList()!=null&&sessionuser.getDepIdList().size()>0) {
			List<Long> depIdList = sessionuser.getDepIdList();
			if (depIdList.size() > 0) {
				for (int i = 0; i <= depIdList.size() - 1; i++) {
					depids += depIdList.get(i) + ",";
				}
				depids = depids.substring(0,depids.length() - 1);
			}
			String depid=sessionuser.getDep().getId().toString();
			if(!depids.equals(depid))
				authoritymap.put("depids", depids);
		}
		return authoritymap;
	}

	public Map<String,Object> uploadExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map=new HashMap<String, Object>();
		InputStream istream = null;
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 获得文件：
			MultipartFile file = multipartRequest.getFile("file");
			// 获得文件名：
			String filename = file.getOriginalFilename();
			// 获得输入流：
			istream = file.getInputStream();
			map.put("filename", filename);
			map.put("content", istream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			istream=null;
			e.printStackTrace();
		}
		if (istream != null) {
			return map;
		} else {
			return null;
		}
	}

	public  void setBaseInfo(BaseEntity baseInfo){
		Timestamp t=DateUtils.getSysTimestamp();
		baseInfo.setS1(t);
		baseInfo.setS2(t);
		baseInfo.setS3(0);
	}
}
