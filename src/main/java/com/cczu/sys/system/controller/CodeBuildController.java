package com.cczu.sys.system.controller;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.entity.Permission;
import com.cczu.sys.system.service.PermissionService;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.WordUtil;

/**
 * 代码自动创建工具controller
 * @author jason
 * @date 2017年5月31日
 */
@Controller
@RequestMapping("system/buildcode")
public class CodeBuildController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private PermissionService permissionService;
	/**
	 * 默认页面
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(Model model) {
		if ("9".equals(UserUtil.getCurrentShiroUser().getUsertype())) {
			return "system/codebuild/form";
		} else {
			return "../error/403";
		}

	}

	@RequestMapping(value = "addcolumn", method = RequestMethod.GET)
	public String addcolumn(Model model) {
		if ("9".equals(UserUtil.getCurrentShiroUser().getUsertype())) {
			return "system/codebuild/columnform";
		} else {
			return "../error/403";
		}

	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String buildcode(String set,HttpServletRequest request) {
		String datasuccess = "success";
		@SuppressWarnings("unchecked")
		Map<String,Object> map = JsonMapper.getInstance().fromJson(set, Map.class);
		//生成菜单、权限
		if(map.get("code_sysmeun").equals("true")){
			createMenuPer(map);
		}
		String filePath = map.get("code_fileurl").toString();//存放文件路径
		File file = new File(filePath);
		if(!file.isDirectory()){
			datasuccess = "文件存放路径错误!"; 
			
		} else {
			if(!file.exists()){
				file.mkdir();
			}
			if (file.isDirectory()) {
				try {
					delFolder(file);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					datasuccess = "删除文件出错！";
					throw e;
				}
			}
		}
		//设置导出的文件名
		String pack=map.get("code_classpack").toString().replace(".", File.separator)+File.separator;
		String viewdic=map.get("code_viewpath").toString()+File.separator;
		String entityfile = map.get("code_classname") + ".java";
		String daofile = map.get("code_classname").toString().replace("_", "") + "Dao.java";
		String servicefile = map.get("code_classname").toString().replace("_", "") + "Service.java";
		String controllerfile = "Page"+map.get("code_classname").toString().replace("_", "") + "Controller.java";
		String indexfile = "index.jsp";
		String jsfile = "index2.js";
		String formfile = "form.jsp";
		String viewfile = "view.jsp";
		//导出文件
		try {
			createCVFile(map, "codebuild_entity.ftl", filePath, entityfile, request,pack+File.separator+"entity");
			createCVFile(map, "codebuild_dao.ftl", filePath, daofile, request,pack+File.separator+"dao");
			createCVFile(map, "codebuild_service.ftl", filePath, servicefile, request,pack+File.separator+"service");
			createCVFile(map, "codebuild_controller.ftl", filePath, controllerfile, request,pack+File.separator+"controller");
			createCVFile(map, "codebuild_index.ftl", filePath, indexfile, request,viewdic);
			createCVFile(map, "codebuild_js.ftl", filePath, jsfile, request,viewdic);
			createCVFile(map, "codebuild_form.ftl", filePath, formfile, request,viewdic);
			createCVFile(map, "codebuild_view.ftl", filePath, viewfile, request,viewdic);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			datasuccess ="生成文件出错！";
			throw e;
		}
		return datasuccess;
		
	}
	
	//删除文件和文件夹
	private void delFolder(File file){
		File[] files = file.listFiles();  
		for(File f : files){
			if(f.isFile()){
				f.delete();
			}else {
				delFolder(f);
				f.delete();
			}
		}
	}
	
	private void createCVFile(Map<String,Object> map,String ftl,String filePath,String entityfile,HttpServletRequest request,String pack){
		WordUtil.ireportWord(map,ftl, filePath, entityfile, request);
		WordUtil.ireportWord(map, ftl,filePath +File.separator+ pack, entityfile, request);
	}
	
	private void createMenuPer(Map<String, Object> map) {

		System.err.println(JsonMapper.getInstance().toJson(map.get("meun")));
		Permission meun = JsonMapper.getInstance().fromJson(JsonMapper.getInstance().toJson(map.get("meun")),Permission.class);
		permissionService.save(meun);
		int id = meun.getId();
		if (id > 0) {
			// 生成权限
			if (map.get("code_permisson").equals("true")) {
				@SuppressWarnings("unchecked")
				Map<String, Object> m = (Map<String, Object>) map.get("permisson");
				if (m.get("persyncdatabase").equals("true")) {
					Permission per = JsonMapper.getInstance().fromJson(JsonMapper.getInstance().toJson(map.get("permisson")), Permission.class);
					per.setType("O");
					per.setPid(id);
					permissionService.initPer(per);
				}
			}
		}
	}
}
