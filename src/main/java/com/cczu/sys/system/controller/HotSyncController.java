package com.cczu.sys.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cczu.model.controller.CommKEUploadController;
import com.cczu.model.entity.YHPC_CheckHiddenInfo2Entity;
import com.cczu.model.entity.YHPC_CheckResult2Entity;
import com.cczu.model.lbyp.service.LbypApplyService;
import com.cczu.model.lbyp.service.LbypFfjlService;
import com.cczu.model.service.BisGzxxService;
import com.cczu.model.service.YhpcXjjl2Service;
import com.cczu.model.service.YhpcXjjlService;
import com.cczu.model.service.YhpcYhpcjl2Service;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.service.CompUserRoleService;
import com.cczu.sys.system.service.DepartmentService;
import com.cczu.sys.system.service.UserService;
import com.cczu.util.common.StringUtils;
import com.cczu.util.common.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 人员同步接口controller
 * @author LL
 * @date 2019年8月31日
 */
@Controller
@RequestMapping("system/hotsync")
public class HotSyncController extends BaseController {

	@Autowired
	private YhpcXjjl2Service yhpcXjjlService;
	@Autowired
	private YhpcYhpcjl2Service yhpcYhpcjlService;

	/**
	 * 巡检记录同步接口请求
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "xjjl", method = RequestMethod.POST)
	@ResponseBody
	public String list(HttpServletRequest request) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		String reqBody = sb.toString();
		List<Map> datalist = JSON.parseArray(reqBody, Map.class);

		Map<String,Object> result=new HashMap<>();
		result.put("status",true);
		result.put("message","同步成功");
		if(datalist.size()==0){
			result.put("status",false);
			result.put("message","同步失败，未接收到数据");
		}

		for(Map<String,Object> map:datalist){
			YHPC_CheckResult2Entity xjjl=new YHPC_CheckResult2Entity();
			if(map.get("checkpointname")!=null&&!map.get("checkpointname").toString().equals(""))
				xjjl.setCheckpointname(map.get("checkpointname").toString());//检查点名称
			if(map.get("checkorder")!=null&&!map.get("checkorder").toString().equals(""))
				xjjl.setCheckorder(map.get("checkorder").toString());//所属班次名称
			if(map.get("checkperson")!=null&&!map.get("checkperson").toString().equals(""))
				xjjl.setCheckperson(map.get("checkperson").toString());//检查人
			if(map.get("checkfj")!=null&&!map.get("checkfj").toString().equals(""))
				xjjl.setCheckphoto(map.get("checkfj").toString());//现场附件
			if(map.get("checkresult")!=null&&!map.get("checkresult").toString().equals(""))
				xjjl.setCheckresult(map.get("checkresult").toString());//检查结果（1有隐患/0无隐患）
			String date=map.get("createtime").toString();
			if(date!=null&&!date.equals(""))
				xjjl.setCreatetime(Timestamp.valueOf(date));//检查时间
			if(map.get("flag")!=null&&!map.get("flag").toString().equals(""))
				xjjl.setFlag(map.get("flag").toString());//标识（用于跟隐患关联）
			if(map.get("note")!=null&&!map.get("note").toString().equals(""))
				xjjl.setNote(map.get("note").toString());//巡检备注
			yhpcXjjlService.add(xjjl);//保存
		}
		return JsonMapper.getInstance().toJson(result);
	}

	/**
	 * 巡检隐患同步接口请求
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "yhjl", method = RequestMethod.POST)
	@ResponseBody
	public String list2(HttpServletRequest request) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		String reqBody = sb.toString();
		List<Map> datalist = JSON.parseArray(reqBody, Map.class);

		Map<String,Object> result=new HashMap<>();
		result.put("status",true);
		result.put("message","同步成功");
		if(datalist.size()==0){
			result.put("status",false);
			result.put("message","同步失败，未接收到数据");
		}

		for(Map<String,Object> map:datalist){
			YHPC_CheckHiddenInfo2Entity yhjl=new YHPC_CheckHiddenInfo2Entity();
			if(map.get("checkresult_flag")!=null&&!map.get("checkresult_flag").toString().equals(""))
				yhjl.setCheckresult_flag(map.get("checkresult_flag").toString());//巡查记录标识
			if(map.get("checkpointname")!=null&&!map.get("checkpointname").toString().equals(""))
				yhjl.setCheckpointname(map.get("checkpointname").toString());//检查点名称
			if(map.get("checkcontent")!=null&&!map.get("checkcontent").toString().equals(""))
				yhjl.setCheckcontent(map.get("checkcontent").toString());//检查内容
			if(map.get("dangerstatus")!=null&&!map.get("dangerstatus").toString().equals(""))
				yhjl.setDangerstatus(map.get("dangerstatus").toString());//隐患状态(初始  0未整改   1整改完成   2整改未完成   3已完成 )
			if(map.get("findperson")!=null&&!map.get("findperson").toString().equals(""))
				yhjl.setFindperson(map.get("findperson").toString());//隐患发现人
			String date=map.get("createtime").toString();
			if(date!=null&&!date.equals(""))
				yhjl.setCreatetime(Timestamp.valueOf(date));//隐患发现时间
			String date2=map.get("handletime").toString();
			if(date2!=null&&!date2.equals(""))
				yhjl.setSechandletime(Timestamp.valueOf(date2));//计划整改时间
			if(map.get("handlepersons")!=null&&!map.get("handlepersons").toString().equals(""))
				yhjl.setHandlepersons(map.get("handlepersons").toString());//指定隐患整改人
			if(map.get("dangerdesc")!=null&&!map.get("dangerdesc").toString().equals(""))
				yhjl.setDangerdesc(map.get("dangerdesc").toString());//隐患备注
			if(map.get("dangerfj")!=null&&!map.get("dangerfj").toString().equals(""))
				yhjl.setDangerphoto(map.get("dangerfj").toString());//隐患附件
			if(map.get("reformperson")!=null&&!map.get("reformperson").toString().equals(""))
				yhjl.setReformpersons(map.get("reformperson").toString());//整改人
			String date3=map.get("reformtime").toString();
			yhjl.setReformtime(Timestamp.valueOf(date3));
			if(map.get("reformfj")!=null&&!map.get("reformfj").toString().equals(""))
				yhjl.setReformphoto(map.get("reformfj").toString());//整改附件
			if(map.get("reformdesc")!=null&&!map.get("reformdesc").toString().equals(""))
				yhjl.setReformdesc(map.get("reformdesc").toString());//整改备注
			yhpcYhpcjlService.add(yhjl);//保存
		}
		return JsonMapper.getInstance().toJson(result);
	}
}
