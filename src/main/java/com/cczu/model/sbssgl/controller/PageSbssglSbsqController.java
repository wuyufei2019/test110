package com.cczu.model.sbssgl.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import com.cczu.model.sbssgl.entity.SBSSGL_QGSBEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBSQEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SHJLEntity;
import com.cczu.model.sbssgl.service.SBSSGLSbsqService;
import com.cczu.model.sbssgl.service.SBSSGLSbysService;
import com.cczu.model.sbssgl.service.SBSSGLShjlService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;
import com.cczu.util.common.WordUtil;

/**
 * 设备设施管理-设备申请controller
 */
@Controller
@RequestMapping("sbssgl/sbsq")
public class PageSbssglSbsqController extends BaseController {

	@Autowired
	private SBSSGLSbsqService sBSSGLSbsqService;
	@Autowired
	private SBSSGLSbysService sBSSGLSbysService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private SBSSGLShjlService sBSSGLShjlService;
	
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
		return "sbssgl/sbsq/index";
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
		map.put("sqbmname", request.getParameter("sqbmname"));
		map.put("sqrname", request.getParameter("sqrname"));
		map.put("m3", request.getParameter("m3"));
		map.put("m10", request.getParameter("m10"));
		map.putAll(getAuthorityMap());
		return sBSSGLSbsqService.dataGrid(map);
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids) {
		String datasuccess="删除成功";
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			//删除设备申请信息
			sBSSGLSbsqService.deleteSbsqById(Long.parseLong(aids[i]));
			//删除关联的请购设备信息
			sBSSGLSbsqService.deleteQgsbBySbsqid(Long.parseLong(aids[i]));
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Map<String,Object> sbsq = sBSSGLSbsqService.findById(id);	
		List<SBSSGL_QGSBEntity> qgsblist = sBSSGLSbsqService.findInfoBySbsqid(id);
		model.addAttribute("sbsq", sbsq);
		model.addAttribute("qgsblist", qgsblist);
		return "sbssgl/sbsq/view";
	}
	
	/**
	 * 添加页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("action", "create");
		Map<String,Object> sbsq = new HashMap<String, Object>();
		User user = UserUtil.getCurrentUser();
		sbsq.put("m1", user.getDepartmen());
		sbsq.put("m2", user.getId());
		sbsq.put("m3", DateUtils.getDate());
		model.addAttribute("sbsq", sbsq);
		return "sbssgl/sbsq/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(SBSSGL_SBSQEntity entity, Model model,HttpServletRequest request) {
		String datasuccess="success";
		if(!StringUtils.isEmpty(entity.getM7())){
			if(entity.getM7().equals("1")){
				entity.setM9("");
			}else{
				entity.setM8("");
			}
		}
		entity.setQyid(UserUtil.getCurrentShiroUser().getQyid());
		entity.setM10("0");
		entity.setM12("0");
		sBSSGLSbsqService.addInfo(entity);
			
		//添加请购设备
		addQgsb(entity,request);
		
		//返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		SBSSGL_SBSQEntity sbsq = sBSSGLSbsqService.find(id);	
		List<SBSSGL_QGSBEntity> qgsblist = sBSSGLSbsqService.findInfoBySbsqid(id);
		model.addAttribute("sbsq", sbsq);
		model.addAttribute("qgsblist", JsonMapper.getInstance().toJson(qgsblist));
		//返回页面
		model.addAttribute("action", "update");
		return "sbssgl/sbsq/form";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(SBSSGL_SBSQEntity entity, Model model,HttpServletRequest request){
		String datasuccess="success";
		if(!StringUtils.isEmpty(entity.getM7())){
			if(entity.getM7().equals("1")){
				entity.setM9("");
			}else{
				entity.setM8("");
			}
		}
		sBSSGLSbsqService.updateInfo(entity);
		
		//删除关联的请购设备信息
		sBSSGLSbsqService.deleteQgsbBySbsqid(entity.getID());
		
		//添加请购设备
		addQgsb(entity,request);
		
		//返回结果
		return datasuccess;
	}
	
	public void addQgsb(SBSSGL_SBSQEntity entity, HttpServletRequest request){
		String zcmc[] = request.getParameterValues("zcmc");//资产名称
		String ggxh[] = request.getParameterValues("ggxh");//规格型号
		String zzs[] = request.getParameterValues("zzs");//制造商/品牌
		String sl[] = request.getParameterValues("sl");//数量
		String dj[] = request.getParameterValues("dj");//单价
		String zj[] = request.getParameterValues("zj");//总价
		
		//循环插入请购设备表
		if(zcmc != null){
			for(int i=0;i<zcmc.length;i++){
				SBSSGL_QGSBEntity qgsb = new SBSSGL_QGSBEntity();
				qgsb.setSbsqid(entity.getID());
				qgsb.setM1(zcmc[i]);
				qgsb.setM2(ggxh[i]);
				qgsb.setM3(zzs[i]);
				qgsb.setM4(sl[i]);
				qgsb.setM5(dj[i]);
				qgsb.setM6(zj[i]);
				qgsb.setM7("0");//设置未验收状态
				sBSSGLSbsqService.addQgsb(qgsb);
			}
		}
	}
	
	@RequestMapping(value = "export/{id}")
	@ResponseBody
	public String getWord(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = sBSSGLSbsqService.getWord(id);
		//设置导出的文件名
		String filename = "设备请购单_" + DateUtils.getDateRandom() + new Random().nextInt(100) + ".doc";
		//设置模板路径
		String filePath = request.getSession().getServletContext().getRealPath("/") + "download/";
		/*WordUtil.ireportWord(map, "gdzcqgd.ftl", filePath, filename, request);*/
		WordUtil.ireportWord(map, "sbsq.ftl", filePath, filename, request);
		return "/download/" + filename;
	}
	
	
	/**
	 * 上传附件页面跳转
	 */
	@RequestMapping(value = "uploadfile/{id}")
	public String tgindex(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		return "sbssgl/sbsq/upload";
	}
	
	/**
	 * 保存附件
	 */
	@RequestMapping(value = "upload")
	@ResponseBody
	public String tgzt(long id,String m11) {
		String datasuccess="success";
		SBSSGL_SBSQEntity sbsq = sBSSGLSbsqService.find(id);
		sbsq.setM10("1");
		sbsq.setM11(m11);
		sBSSGLSbsqService.updateInfo(sbsq);
		return datasuccess;
	}
	
	/**
	 * 审核结果页面跳转
	 */
	@RequestMapping(value = "shjg/{id}")
	public String shjg(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		return "sbssgl/sbsq/shjg";
	}
	
	/**
	 * 保存审核结果
	 */
	@RequestMapping(value = "shjg")
	@ResponseBody
	public String bhzt(HttpServletRequest request) {
		String datasuccess="success";
		Long id = Long.parseLong(request.getParameter("id")); 
		String shjg = request.getParameter("shjg");
		String btgyy = request.getParameter("btgyy");
		SBSSGL_SBSQEntity sbsq = sBSSGLSbsqService.find(id);
		if ("0".equals(shjg)) {//不通过
			sbsq.setM10("3");  //将状态改为"不通过"
		} else if ("1".equals(shjg)) {//通过
			sbsq.setM10("2");  //将状态改为"通过"
		}
		sBSSGLSbsqService.updateInfo(sbsq);//修改设备申请表的状态
		
		SBSSGL_SHJLEntity shjl = new SBSSGL_SHJLEntity();
		Timestamp t = DateUtils.getSystemTime();
		shjl.setS1(t);
		shjl.setS2(t);
		shjl.setS3(0);
		shjl.setM1(shjg);//审核结果
		shjl.setShrid(Long.parseLong(UserUtil.getCurrentShiroUser().getId()+""));//审核人id
		shjl.setM2(btgyy);//不通过原因
		shjl.setM3(sbsq.getM11());//附件
		shjl.setId2(id);//设备申请id
		shjl.setM4("0");//类别，0代表设备申请的审核记录
		sBSSGLShjlService.addShJlInfo(shjl);//保存审核记录信息
		
		return datasuccess;
	}
	
	/**
	 * 查看审核记录页面跳转
	 */
	@RequestMapping(value = "viewshjl/{sbsqid}")
	public String viewshjl(@PathVariable("sbsqid") Long sbsqid, Model model) {
		model.addAttribute("sbsqid", sbsqid);
		return "sbssgl/sbsq/viewshjl";
	}
	
	/**
	 * 审核记录list
	 * @param request
	 * @return
	 */
	@RequestMapping(value="shjllist/{sbsqid}")
	@ResponseBody
	public Map<String, Object> shjlList(@PathVariable("sbsqid") Long sbsqid, HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("sbsqid",sbsqid);
		return sBSSGLShjlService.shjlDataGrid(map);
	}
	
	
	/**
	 * 请购资产页面跳转
	 * @param id  sbsqid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "qgsb/{sbsqid}")
	public String createys(@PathVariable("sbsqid") Long sbsqid, Model model) {
		model.addAttribute("sbsqid", sbsqid);//设备申请id
		return "sbssgl/sbsq/qgsb";
	} 
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="qgsblist/{sbsqid}")
	@ResponseBody
	public Map<String, Object> qgzcList(@PathVariable("sbsqid") Long sbsqid,HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("sbsqid", sbsqid);
		return sBSSGLSbsqService.qgzcDataGrid(map);
	}
	
}
