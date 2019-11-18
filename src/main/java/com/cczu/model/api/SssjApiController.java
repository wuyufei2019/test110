package com.cczu.model.api;

import com.cczu.model.service.BisCgjcwhsjService;
import com.cczu.model.zdwxyssjc.service.*;
import com.cczu.sys.comm.controller.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * 在线监控预警-实时数据controller
 * @author wbth
 * @date 2019年8月20日
 */
@Controller
@RequestMapping("api")
public class SssjApiController extends BaseController {

	@Autowired
	private IMonitorTemperatureService monitorTempService;
	@Autowired
	private IMonitorPressureService MonitorPressService;
	@Autowired
	private IMonitorLiquidLevelService monitorLiquidLevelService;
	@Autowired
	private IMonitorCombustibleGasService monitorCombusGasService;
	@Autowired
	private IMonitorToxicGasService monitorToxicGasService;
	@Autowired
	private IMonitorLiveReceiveService monitorLiveReceiveService;
	@Autowired
	private MonitorEdmInfoService monitorEdmService;
	@Autowired
	private MonitorEdmsummaryService monitorEdmsummaryService;



	/**
	 * 储罐温度传感器实时数据添加接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value="livereceive/cgwd", method = RequestMethod.POST)
	@ResponseBody
	public String addEdmjcInfo(HttpServletRequest request) {
		String username = request.getHeader("username");
		String password = request.getHeader("password");
		//if ("WDCGQ".equals(username) && "Create".equals(password)) {

			return monitorTempService.add(request);
		//}
		/*Map<String, Object> result = new HashMap<>();
		result.put("code", "201");// Http Header参数验证失败
		result.put("status", "fail");
		return JsonMapper.toJsonString(result);*/
	}

	/**
	 * 储罐压力传感器实时数据添加接口
	 */
	@RequestMapping(value="livereceive/cgyl", method = RequestMethod.POST)
	@ResponseBody
	public String addQtndjcInfo(HttpServletRequest request) {
		String username = request.getHeader("username");
		String password = request.getHeader("password");
		//if ("YLCGQ".equals(username) && "Create".equals(password)) {

			return MonitorPressService.add(request);
		//}
		/*Map<String, Object> result = new HashMap<>();
		result.put("code", "201");// Http Header参数验证失败
		result.put("status", "fail");
		return JsonMapper.toJsonString(result);*/
	}

	/**
	 * 液位传感器实时数据添加接口
	 */
	@RequestMapping(value="livereceive/cgyw", method = RequestMethod.POST)
	@ResponseBody
	public String addGwgyjcInfo(HttpServletRequest request) {
		String username = request.getHeader("username");
		String password = request.getHeader("password");
		//if ("YWCGQ".equals(username) && "Create".equals(password)) {

			return monitorLiquidLevelService.add(request);
		//}
		/*Map<String, Object> result = new HashMap<>();
		result.put("code", "201");// Http Header参数验证失败
		result.put("status", "fail");
		return JsonMapper.toJsonString(result);*/
	}

	/**
	 * 可燃气体传感器实时数据添加信息接口
	 */
	@RequestMapping(value="livereceive/krqt", method = RequestMethod.POST)
	@ResponseBody
	public String addSdyljcInfo(HttpServletRequest request) {
		String username = request.getHeader("username");
		String password = request.getHeader("password");
		//if ("KRQTCGQ".equals(username) && "Create".equals(password)) {

			return monitorCombusGasService.add(request);
		//}
		/*Map<String, Object> result = new HashMap<>();
		result.put("code", "201");// Http Header参数验证失败
		result.put("status", "fail");
		return JsonMapper.toJsonString(result);*/
	}

	/**
	 * 有毒气体实时数据添加信息接口
	 */
	@RequestMapping(value="livereceive/ydqt", method = RequestMethod.POST)
	@ResponseBody
	public String addCgssjcInfo(HttpServletRequest request) {
		String username = request.getHeader("username");
		String password = request.getHeader("password");
		//if ("YDQTCGQ".equals(username) && "Create".equals(password)) {

			return monitorToxicGasService.add(request);
		//}
		/*Map<String, Object> result = new HashMap<>();
		result.put("code", "201");// Http Header参数验证失败
		result.put("status", "fail");
		return JsonMapper.toJsonString(result);*/
	}

	/**
	 * 在岗人员二道门进出记录接口
	 */
	@RequestMapping(value="livereceive/edm", method = RequestMethod.POST)
	@ResponseBody
	public String addEdmInfo(HttpServletRequest request) {
		return monitorEdmService.add(request);
	}
	
	/**
	 * 在岗人员实时汇总接口
	 */
	@RequestMapping(value="livereceive/edmsummary", method = RequestMethod.POST)
	@ResponseBody
	public String addEdmsummaryInfo(HttpServletRequest request) {
		return monitorEdmsummaryService.add(request);
	}


	/**
	 * 传感器实时数据添加接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value="livereceive", method = RequestMethod.POST)
	@ResponseBody
	public String addSssj(HttpServletRequest request) {
		String username = request.getHeader("username");
		String password = request.getHeader("password");
		//if ("WDCGQ".equals(username) && "Create".equals(password)) {

		return monitorLiveReceiveService.add(request);
		//}
		/*Map<String, Object> result = new HashMap<>();
		result.put("code", "201");// Http Header参数验证失败
		result.put("status", "fail");
		return JsonMapper.toJsonString(result);*/
	}
}
