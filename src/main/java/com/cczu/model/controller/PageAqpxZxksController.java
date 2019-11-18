package com.cczu.model.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.AQPX_CourseEntity;
import com.cczu.model.entity.AQPX_ExamresultsEntity;
import com.cczu.model.entity.AQPX_ItembankEntity;
import com.cczu.model.entity.AQPX_PlanEntity;
import com.cczu.model.entity.AQPX_TestguizeEntity;
import com.cczu.model.entity.AQPX_TestpaperEntity;
import com.cczu.model.service.AqpxSjjyHistoryService;
import com.cczu.model.service.AqpxWlfkscjService;
import com.cczu.model.service.AqpxWlfpxHistoryService;
import com.cczu.model.service.IAqpxGzxxService;
import com.cczu.model.service.IAqpxJhxxService;
import com.cczu.model.service.IAqpxKCxxService;
import com.cczu.model.service.IAqpxKscjService;
import com.cczu.model.service.IAqpxKsjlService;
import com.cczu.model.service.IAqpxStkxxService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全培训管理——在线考试Controller
 * @author jason
 */
@Controller
@RequestMapping("aqpx/zxks")
public class PageAqpxZxksController extends BaseController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private IAqpxJhxxService aqpxpjhxxService;
	@Autowired
	private IAqpxKCxxService aqpxKCxxService;
	@Autowired
	private IAqpxGzxxService aqpxGzxxService;
	@Autowired
	private IAqpxStkxxService aqpxStkxxService;
	@Autowired
	private IAqpxKsjlService aqpxKsjlService;
	@Autowired
	private IAqpxKscjService aqpxKscjService;
	@Autowired
	private AqpxSjjyHistoryService sjjyHistoryService;
	@Autowired
	private AqpxWlfpxHistoryService wlfpxHistoryService;
	@Autowired
	private AqpxWlfkscjService aqpxWlfkscjService;			
	
	/**
	 * 首页显示学习课程
	 * @param model	
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		User user=userService.getUserById(sessionuser.getId());
		List<AQPX_PlanEntity> plan=aqpxpjhxxService.findInfoByBmid(sessionuser.getQyid(), user.getDepartmen());
		List<Map<String, Object>> list=new ArrayList<>();
		if(plan!=null){
			for(AQPX_PlanEntity jh:plan){
				List<Map<String, Object>> temp= aqpxKCxxService.getlistByKcids2(jh.getID2(),jh.getM1(),jh.getID());
				list.addAll(temp);
			}
			model.addAttribute("kclist", list);
		}
		return "aqpx/zxks/index";
	}
	

	/**
	 * 随机生成试卷
	 * @param request
	 */
	@RequestMapping(value = "showsj/{id}", method = RequestMethod.GET)
	public String showKJList(@PathVariable("id") Long kcid, Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		AQPX_TestguizeEntity stgz=aqpxGzxxService.findkc(sessionuser.getQyid(), kcid);
		
		Map<String, List<AQPX_ItembankEntity>> map = aqpxStkxxService.getSjByGz(kcid, stgz.getM1(), stgz.getM2(), stgz.getM3(), stgz.getM4());
		AQPX_CourseEntity ac = aqpxKCxxService.findbyid(kcid); 
		model.addAttribute("stmap", map);
		model.addAttribute("kcid", kcid);
		model.addAttribute("stgz", stgz);
		model.addAttribute("kcname", ac.getM1());
		return "aqpx/zxks/view";
	}
	
	/**
	 * 提交考试记录
	 * @param request
	 */
	@RequestMapping(value = "submit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> submitKS(Long kcid,HttpServletRequest request, Model model) {
		int score=0;
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		AQPX_TestguizeEntity stgz=aqpxGzxxService.findkc(sessionuser.getQyid(), kcid);
		
		AQPX_TestpaperEntity st=new AQPX_TestpaperEntity();
		st.setID1(sessionuser.getQyid());
		st.setID3((long)sessionuser.getId());
		st.setID4(stgz.getID());
		st.setID5(kcid);
		st.setM3(new java.sql.Date(new Date().getTime()));
		String bs=DateUtils.getDateRandom();//试卷标识
		st.setH(bs);
		
		//获取所有试题结果 并保存
		Map< String, String[]> map=request.getParameterMap();
		for(Map.Entry<String, String[]> entry:map.entrySet()){  
			String key=entry.getKey();
			String values[]=entry.getValue();
			
			if(key.indexOf("dx_")==0||key.indexOf("tk_")==0||key.indexOf("pd_")==0){
				String id=key.substring(3).trim();
				String val =values[0];
				st.setID2(Long.parseLong(id));
				st.setM2(val);
				AQPX_ItembankEntity itembankEntity=aqpxStkxxService.findByid(Long.parseLong(id));
				if(itembankEntity.getM8().equalsIgnoreCase(val)){//判断答案是否正确 不分大小写
					switch (itembankEntity.getM1()) { //根据题目类型和出题规则打分
					case "1":st.setM1(stgz.getM5()); score+=stgz.getM5(); break;//单选
					case "3":st.setM1(stgz.getM7()); score+=stgz.getM7(); break;//填空
					case "4":st.setM1(stgz.getM8()); score+=stgz.getM8(); break;//判断
					}
				}
				aqpxKsjlService.addInfo(st);
			}else if(key.indexOf("dsx_")==0){
				String id=key.substring(4).trim();
				st.setID2(Long.parseLong(id));
				st.setM2(StringUtils.join(values));
				AQPX_ItembankEntity itembankEntity=aqpxStkxxService.findByid(Long.parseLong(id));
				if(itembankEntity.getM8().equalsIgnoreCase(StringUtils.join(values))){//判断多选答案是否正确 不分大小写
					st.setM1(stgz.getM6()); score+=stgz.getM6();
				}
				aqpxKsjlService.addInfo(st);
			}  
		}   
		AQPX_ExamresultsEntity examresultsEntity =new AQPX_ExamresultsEntity();
		examresultsEntity.setID1(sessionuser.getQyid());
		examresultsEntity.setID2((long)sessionuser.getId());
		examresultsEntity.setID3(kcid);
		if(!StringUtils.isEmpty(request.getParameter("planid")))
			examresultsEntity.setID4(Long.parseLong(request.getParameter("planid")));
		examresultsEntity.setH(bs);
		examresultsEntity.setM1(score);
		examresultsEntity.setM2(request.getParameter("time"));
		if(score>=stgz.getM9()){
			examresultsEntity.setM3("合格");
		}else{
			examresultsEntity.setM3("不合格");
		}
		AQPX_CourseEntity kc=aqpxKCxxService.findbyid(kcid);
		//如果外来方考试，添加到专门的成绩记录里
		if(kc.getM5().equals("3")){
			aqpxWlfkscjService.addInfo(examresultsEntity, request);
		}else{
			aqpxKscjService.addInfo(examresultsEntity);
		}
		//三级教育考试记录
		if(kc.getM5().equals("2"))
			sjjyHistoryService.CreateHistoryByOnlineTest(sessionuser.getId(), examresultsEntity,kc);
		//外来方考试记录
		if(kc.getM5().equals("3"))
			wlfpxHistoryService.CreateHistoryByOnlineTest(examresultsEntity,request);
		Map<String,Object> remap = new HashMap<String, Object>();
		remap.put("score", score);
		remap.put("id", bs);
		return remap;
	}
	
	/**
	 * 判断是否可以生成试卷
	 * @param request
	 */
	@RequestMapping(value = "createjy/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String isOrNotCreateSJ(@PathVariable("id") Long kcid, Model model) {
		String datasuccess="";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		AQPX_TestguizeEntity stgz=aqpxGzxxService.findkc(sessionuser.getQyid(), kcid);
		if (stgz==null){
			datasuccess="请联系管理员设置出题规则！";
		}else if(!aqpxStkxxService.STisOrNot(kcid, sessionuser.getQyid())){
			datasuccess="试题库题量不足请联系管理员添加！";
		}
		return datasuccess;
	}

}
