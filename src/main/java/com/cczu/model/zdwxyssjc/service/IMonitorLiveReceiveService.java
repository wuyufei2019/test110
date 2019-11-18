package com.cczu.model.zdwxyssjc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface IMonitorLiveReceiveService {
	
	/**
	 * 添加实时数据
	 * @param request
	 * @return
	 */
	public String add(HttpServletRequest request);
}
