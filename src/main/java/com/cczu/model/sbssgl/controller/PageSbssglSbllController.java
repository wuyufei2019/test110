package com.cczu.model.sbssgl.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.sbssgl.service.SBSSGLSbllService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.sys.system.utils.ZipUtils;
import com.cczu.util.common.WordUtil;

/**
 * 设备设施管理-设备履历controller
 */
@Controller
@RequestMapping("sbssgl/sbll")
public class PageSbssglSbllController extends BaseController {

	@Autowired
	private SBSSGLSbllService sBSSGLSbllService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}else//未添加企业基本信息错误提示页面
				return "../error/001";
		}else{
			return "../error/403";
		}
		return "sbssgl/sbll/index";
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("qyname", request.getParameter("qyname"));
		map.put("m1", request.getParameter("m1"));
		map.put("m2", request.getParameter("m2"));
		map.put("m6", request.getParameter("m6"));
		map.put("m19", request.getParameter("m19"));
		map.putAll(getAuthorityMap());
		return sBSSGLSbllService.dataGrid(map);
	}

	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("1")){//企业用户
			BIS_EnterpriseEntity be = qyjbxxServiceImpl.findInfoById(sessionuser.getQyid());
			if(be!=null&&be.getM1()!=null){//判断是否添加了企业基本信息
				if(be.getIsbloc()!=null&&be.getIsbloc()==1)//判断是否为集团公司
					model.addAttribute("type","2");//集团公司
				else
					model.addAttribute("type","1");//子公司
			}
		}
		//获取点巡检信息
		Map<String,Object> sbll = sBSSGLSbllService.findById(id);	
		model.addAttribute("sbll", sbll);
		return "sbssgl/sbll/view";
	}
	
	/**
	 * 导出设备履历word
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "export/{id}")
	public void getWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		String zipName = "BB.zip";// 压缩包名称
		
		Map<String, Object> map = sBSSGLSbllService.getWord(id);
		//设置导出的文件名
		String filename = "设备履历_" + DateUtils.getDateRandom() + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		WordUtil.ireportWord(map, "sbll1.ftl", filePath, filename, request);
		/*return "/download/" + filename;*/
		
		Map<String, Object> fileList = sBSSGLSbllService.getFileList(id);//查询数据库中记录
        response.setContentType("APPLICATION/OCTET-STREAM");  
        response.setHeader("Content-Disposition","attachment; filename="+zipName);
        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
        try {
        	ZipUtils.doCompress(filePath + filename, out);
        	
        	String fjFilePath = request.getSession().getServletContext().getRealPath("/");
        	
        	if (fileList.get("sbjffj") != null) {
        		String sbjfFileUrl = fileList.get("sbjffj").toString();
        		ZipUtils.doCompress(fjFilePath + sbjfFileUrl, out);
			}
        	
        	if (fileList.get("sbysfj") != null) {
        		String sbysFileUrl = fileList.get("sbysfj").toString();
        		ZipUtils.doCompress(fjFilePath + sbysFileUrl, out);
			}
    		
        	if (fileList.get("sbsqfj") != null) {
        		String sbsqFileUrl = fileList.get("sbsqfj").toString();
        		ZipUtils.doCompress(fjFilePath + sbsqFileUrl, out);
			}
        	
        	if (fileList.get("sbgzwxfj") != null) {
        		String sbgzwxFileUrl = fileList.get("sbgzwxfj").toString();
        		ZipUtils.doCompress(fjFilePath + sbgzwxFileUrl, out);
			}
    		
        	if (fileList.get("sbgzxqfj") != null) {
        		String sbgzxqFileUrl = fileList.get("sbgzxqfj").toString();
        		ZipUtils.doCompress(fjFilePath + sbgzxqFileUrl, out);
			}
    		
    		
    		List<Map<String, Object>> firFileList  = (List<Map<String, Object>>) fileList.get("firfjlist");
    		if (firFileList != null) {
	    		for (Map<String, Object> map2 : firFileList) {
	    			if (map2.get("fj") != null) {
	    				String firFileUrl = map2.get("fj").toString();
	            		ZipUtils.doCompress(fjFilePath + firFileUrl, out);
					}
				}
    		}
    		
    		List<Map<String, Object>> secFileList  = (List<Map<String, Object>>) fileList.get("secfjlist");
    		if (secFileList != null) {
	    		for (Map<String, Object> map3 : secFileList) {
	    			if (map3.get("fj") != null) {
	    				String secFileUrl = map3.get("fj").toString();
	            		ZipUtils.doCompress(fjFilePath + secFileUrl, out);
					}
				}
    		}
    		
    		if (fileList.get("sbbffj") != null) {
    			String sbbfFileUrl = fileList.get("sbbffj").toString();
        		ZipUtils.doCompress(fjFilePath + sbbfFileUrl, out);
			}
    		
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            out.close();
        }
	}
}
