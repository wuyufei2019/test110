package com.cczu.model.controller;

import java.sql.Timestamp;
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

import com.cczu.model.entity.AQPX_CoursewareEntity;
import com.cczu.model.entity.AQPX_StudyhistoryEntity;
import com.cczu.model.service.IAqpxJhxxService;
import com.cczu.model.service.IAqpxKCxxService;
import com.cczu.model.service.IAqpxKjkxxService;
import com.cczu.model.service.IAqpxXxjlService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.DepartmentService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 安全培训管理——在线学习Controller
 * @author jason
 */
@Controller
@RequestMapping("aqpx/zxxx")
public class PageAqpxZxxxController extends BaseController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private IAqpxJhxxService aqpxpjhxxService;
	@Autowired
	private IAqpxKCxxService aqpxKCxxService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private IAqpxKjkxxService aqpxKjkxxService;
	@Autowired
	private IAqpxXxjlService aqpxXxjlService;
	
	/**
	 * 首页显示学习课程
	 * @param model	
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		User user=userService.getUserById(sessionuser.getId());
			List<Map<String ,Object>> list=aqpxKCxxService.getKclist(sessionuser.getQyid(), user.getDepartmen());
			model.addAttribute("kclist", list);
		return "aqpx/zxxx/index";
	}
	

	/**
	 * 显示所选课程的课件
	 * @param request
	 */
	@RequestMapping(value = "showkjlist/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<AQPX_CoursewareEntity> showKJList(@PathVariable("id") Long id, Model model) {
		List<AQPX_CoursewareEntity> list=aqpxKjkxxService.getListKcid(id);
		return list;
			
	}
	
	/**
	 * 显示课程信息
	 * @param request
	 */
	@RequestMapping(value = "showkj/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> showKJ(@PathVariable("id") Long id, Model model) {
		AQPX_CoursewareEntity c=aqpxKjkxxService.findByID(id);
		Map<String, Object> map=new HashMap<>();
		map.put("name", c.getM1());
		map.put("url", c.getM4());
		String str[]=c.getM4().split("\\.");
		String type=str[str.length-1];
		if(type.equals("ppt")||type.equals("pdf"))
			map.put("type", "file");
		else if(type.equals("mp4")||type.equals("avi"))
			map.put("type", "movie");
		return map;
			
	}
    /**
     * 显示课程信息
     * @param
     */
    @RequestMapping(value = "pdfView")
    public String pdfView( Model model,HttpServletRequest request) {
        String url = request.getParameter("url");
        model.addAttribute("url", url);
        return "aqpx/zxxx/pdfView";

    }
	
	/**
	 * 跳转显示课程课件
	 * @param model	
	 */
	@RequestMapping(value="kjindex")
	public String kjindex(Model model) {
		return "aqpx/zxxx/view";
	}
	
	/**
	 * 提交课程学习时间
	 * @param request
	 */
	@RequestMapping(value = "studytime", method = RequestMethod.POST)
	@ResponseBody
	public void studytime(HttpServletRequest request , Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		AQPX_StudyhistoryEntity studyhistoryEntity=new AQPX_StudyhistoryEntity();

		studyhistoryEntity.setID1(sessionuser.getQyid());
		studyhistoryEntity.setID2((long)sessionuser.getId());
		studyhistoryEntity.setID3(Long.parseLong(request.getParameter("kcid")));
		studyhistoryEntity.setM2( new Timestamp(new Date(Long.parseLong(request.getParameter("start"))).getTime()));
		studyhistoryEntity.setM3( new Timestamp(new Date(Long.parseLong(request.getParameter("end"))).getTime()));
		
		aqpxXxjlService.addInfo(studyhistoryEntity);
	
	}
	

}
