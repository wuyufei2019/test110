package com.cczu.sys.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.entity.Log;
import com.cczu.sys.system.service.LogService;

/**
 * 日志controller
 * @author jason
 * @date 2017年5月31日
 */
@Controller
@RequestMapping("system/log")
public class LogController extends BaseController{

	@Autowired
	private LogService logService;
	
	/**
	 * 默认页面
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(){
		return "system/logList";
	}
	
	/**
	 * 获取日志json
	 */
	@RequiresPermissions("sys:log:view")
	@RequestMapping("json")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request) {
		Page<Log> logPage=getPage(request);
		logPage.setOrder("desc");
		logPage.setOrderBy("createDate");
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		logPage = logService.search(logPage, filters);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("rows", logPage.getResult());
		map.put("total", logPage.getTotalCount());
		return map;
	}
	
	
	/**
	 * 批量删除日志
	 * @param idList
	 */
	@RequiresPermissions("sys:log:delete")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestBody List<Integer> idList) {
		logService.deleteLog(idList);
		return "success";
	}

	/**
	 * 导出excel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		/*response.setContentType("application/msexcel;charset=GBK");
        
        List<Log> list = logService.getAll();//获取数据
        
        String title = "log";
        String[] hearders = new String[] {"操作编码", "详细描述", "执行时间(mm)", "操作系统", "浏览器", "IP","MAC","操作者","操作时间"};//表头数组
        String[] fields = new String[] {"operationCode", "description", "executeTime", "os", "browser", "ip","mac","creater","createDate"};//People对象属性数组
        TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders),fields);
        JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportToExcel(title, "admin", td);*/
	}
}
