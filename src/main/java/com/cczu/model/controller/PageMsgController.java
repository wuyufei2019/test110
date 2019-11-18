package com.cczu.model.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.MSG_detailEntity;
import com.cczu.model.service.IMsgService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 消息管理controller
 * 
 * @author jason
 * @date 2017年12月28日
 */
@Controller
@RequestMapping("msg")
public class PageMsgController extends BaseController {
	@Autowired
	private IMsgService msgService;
	/**
	 * 列表显示页面
	 * 
	 * @param model
	 */
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "model/msg/index";
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
		map.put("type", request.getParameter("type"));
		map.put("status", request.getParameter("status"));
		map.put("userId", UserUtil.getCurrentUser().getId());
		return msgService.dataGrid(map);
	}
	/**
	 * 首页提醒
	 * 
	 * @param request
	 */
	@RequestMapping(value = "html")
	@ResponseBody
	public List<MSG_detailEntity>  getHtml(HttpServletRequest request, Model model) {
        Map<String, Object> map = getPageMap(request);
        map.put("userId", UserUtil.getCurrentUser().getId());
        List<MSG_detailEntity> list=msgService.findAllMsgList(map);
       return list;
	}
	
	/**
	 * 删除消息
	 * 
	 * @param request
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess = "删除成功";
		// 可以批量删除
		String[] arrids = ids.split(",");
		for (int i = 0; i < arrids.length; i++) {
			msgService.deleteInfo(Long.parseLong(arrids[i]));
		}
		return datasuccess;
	}
	/**
	 * 全部已读
	 * 
	 * @param request
	 */
	@RequestMapping(value = "readall")
	@ResponseBody
	public String readall() {
		String datasuccess = "";
		msgService.updateInfoByUserId((long)UserUtil.getCurrentUser().getId());
		return datasuccess;
	}
	/**
	 * 读取消息
	 * 
	 * @param request
	 */
	@RequestMapping(value = "read/{ids}")
	@ResponseBody
	public String read(@PathVariable("ids") String ids) {
		String datasuccess = "消息已读";
		// 可以批量读取
		MSG_detailEntity obj = msgService.findInfoById(Long.parseLong(ids));
		//更新时间
		Timestamp t=DateUtils.getSysTimestamp();
		obj.setS2(t);
		//更新状态为已读
		obj.setStatus("1");
		msgService.updateInfo(obj);
		return datasuccess;
	}

	
	/**
	 * 未读消息数量
	 * 
	 * @param request
	 */
	@RequestMapping(value = "msgCnt")
	@ResponseBody
	public String msgCnt(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", UserUtil.getCurrentUser().getId());
		int getTotalCount=msgService.msgCnt(map);
		return String.valueOf(getTotalCount);
	}
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 */
	@RequestMapping(value = "excel")
	@ResponseBody
	public void getExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", request.getParameter("excelcon1"));
		map.put("status", request.getParameter("excelcon2"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.put("userId", UserUtil.getCurrentUser().getId());
		msgService.exportExcel(response, map);
	}
	
	
	/**
	 * 显示所有列
	 * @param id,model
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		return "model/common/formexcel";
	}
}
