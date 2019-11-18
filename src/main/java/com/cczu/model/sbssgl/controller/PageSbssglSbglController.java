package com.cczu.model.sbssgl.controller;

import java.util.HashMap;
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

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBGLEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBJFEntity;
import com.cczu.model.sbssgl.entity.SBSSGL_SBYSEntity;
import com.cczu.model.sbssgl.service.SBSSGLQzjyService;
import com.cczu.model.sbssgl.service.SBSSGLSbglService;
import com.cczu.model.sbssgl.service.SBSSGLSbjfService;
import com.cczu.model.sbssgl.service.SBSSGLSbysService;
import com.cczu.model.service.impl.BisQyjbxxServiceImpl;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 设备设施管理-设备管理controller
 */
@Controller
@RequestMapping("sbssgl/sbgl")
public class PageSbssglSbglController extends BaseController {

	@Autowired
	private SBSSGLSbglService sBSSGLSbglService;
	@Autowired
	private BisQyjbxxServiceImpl qyjbxxServiceImpl;
	@Autowired
	private SBSSGLSbjfService sBSSGLSbjfService;
	@Autowired
	private SBSSGLSbysService sBSSGLSbysService;
	@Autowired
	private SBSSGLQzjyService sBSSGLQzjyService;
	
	/**
	 * 列表显示页面
	 * @param model
	 */
	@RequestMapping(value="index")
	public String index(Model model, HttpServletRequest request) {
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
		
		if ("tzsb".equals(request.getParameter("sbtype"))) {
			model.addAttribute("sbtype","1");//特种设备
		} else {
			model.addAttribute("sbtype","0");//普通设备
		}
		return "sbssgl/sbgl/index";
	}
	
	/**
	 * list页面
	 * @param request
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Map<String, Object> map = getPageMap(request);
		map.put("sbtype", request.getParameter("sbtype"));
		map.put("qyname", request.getParameter("qyname"));
		map.put("m1", request.getParameter("m1"));
		map.put("m2", request.getParameter("m2"));
		map.put("m6", request.getParameter("m6"));
		map.put("m9", request.getParameter("m9"));
		map.put("m16", request.getParameter("m16"));
		map.put("m19", request.getParameter("m19"));
		map.put("m23", request.getParameter("m23"));
		map.putAll(getAuthorityMap());
		return sBSSGLSbglService.dataGrid2(map);
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "delete/{ids}")
	@ResponseBody
	public String delete(@PathVariable("ids") String ids, HttpServletRequest request) {
		String datasuccess="删除成功";
		String sbtype = request.getParameter("sbtype");
		//可以批量删除
		String[] aids = ids.split(",");
		for(int i=0;i<aids.length;i++){
			//删除设备管理信息
			sBSSGLSbglService.deleteSbglById(Long.parseLong(aids[i]));
			if ("1".equals(sbtype)) {//如果是特种设备 
				//删除特种设备关联的强制检验信息
				sBSSGLQzjyService.deleteInfoBySbid(Long.parseLong(aids[i]));
			}
			SBSSGL_SBGLEntity sbgl = sBSSGLSbglService.find(Long.parseLong(aids[i]));
			if(sbgl.getSbjfid()!=0){
				//修改设备交付状态
				SBSSGL_SBJFEntity sbjf = sBSSGLSbjfService.find(sbgl.getSbjfid());//设备交付信息	
				sbjf.setM20("1");
				sBSSGLSbjfService.updateInfo(sbjf);
			}
		}
		return datasuccess;
	}
	
	/**
	 * 查看页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		Map<String, Object> sbgl = sBSSGLSbglService.findById(id);	
		model.addAttribute("sbgl", sbgl);
		model.addAttribute("sbtype", request.getParameter("sbtype"));
		return "sbssgl/sbgl/view";
	}
	
	/**
	 * 设备交付页面添加信息页面跳转
	 * @param sbjfid 设备交付id
	 * @param model
	 */
	@RequestMapping(value = "create1/{sbjfid}" , method = RequestMethod.GET)
	public String create(@PathVariable("sbjfid") Long sbjfid,Model model) {
		model.addAttribute("action", "create");
		Map<String, Object> map = new HashMap<>();
		map.putAll(getAuthorityMap());
		map.put("sbjfid", sbjfid);
		String deptId = sBSSGLSbjfService.getDeptid(map);//部门id
		SBSSGL_SBJFEntity sbjf = sBSSGLSbjfService.find(sbjfid);//设备交付信息	
		SBSSGL_SBYSEntity sbys = sBSSGLSbysService.find(sbjf.getSbysid());//设备验收信息
		SBSSGL_SBGLEntity sbgl = new SBSSGL_SBGLEntity();
		sbgl.setQyid(sbjf.getQyid());
		sbgl.setSbjfid(sbjfid);
		sbgl.setM1(sbjf.getM4());
		sbgl.setM2(sbys.getM4());
		sbgl.setM3(sbys.getM5());
		sbgl.setM4(sbjf.getM6());
		sbgl.setM5(sbys.getM7());
		sbgl.setM7(sbjf.getM7());
		sbgl.setM8(sbys.getM9());
		sbgl.setM16(sbjf.getM15());
		sbgl.setM23(Long.parseLong(deptId));
		model.addAttribute("sbgl", sbgl);
		return "sbssgl/sbgl/form";
	}
	
	/**
	 * 添加信息页面跳转
	 * @param model
	 */
	@RequestMapping(value = "create2" , method = RequestMethod.GET)
	public String create2(Model model, HttpServletRequest request) {
		model.addAttribute("action", "create");
		SBSSGL_SBGLEntity sbgl = new SBSSGL_SBGLEntity();
		long sbjfid = 0;
		sbgl.setSbjfid(sbjfid);
		model.addAttribute("sbgl", sbgl);
		model.addAttribute("sbtype", request.getParameter("sbtype"));
		return "sbssgl/sbgl/form";
	}
	
	/**
	 * 添加信息
	 * @param request,model
	 */
	@RequestMapping(value = "create" , method = RequestMethod.POST)
	@ResponseBody
	public String create(SBSSGL_SBGLEntity entity, Model model,HttpServletRequest request) {
		String datasuccess="success";
		entity.setM19("0"); //状态改为待上传附件
		if ("0".equals(request.getParameter("sbtype"))) {//如果是普通设备
			entity.setM21("0");
		} else if ("1".equals(request.getParameter("sbtype"))) {//如果是特种设备
			entity.setM21("1");
		}
		String type = request.getParameter("type");
		ShiroUser user = UserUtil.getCurrentShiroUser();
		if ("1".equals(type)) {//子公司
			entity.setQyid(user.getQyid());
		} 
		entity.setM26("0"); //此时未有关联的备品备件信息
		sBSSGLSbglService.addInfo(entity);
		if(entity.getSbjfid()!=0){
			//修改设备交付状态
			SBSSGL_SBJFEntity sbjf = sBSSGLSbjfService.find(entity.getSbjfid());//设备交付信息	
			sbjf.setM20("2");
			sBSSGLSbjfService.updateInfo(sbjf);
		}
		
		//返回结果
		return datasuccess;
	}

	/**
	 * 修改页面跳转
	 * @param id,model
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		SBSSGL_SBGLEntity sbgl = sBSSGLSbglService.find(id);	
		model.addAttribute("sbgl", sbgl);
		model.addAttribute("action", "update");
		model.addAttribute("sbtype", request.getParameter("sbtype"));
		//返回页面
		return "sbssgl/sbgl/form";
	}
	
	/**
	 * 修改信息
	 * @param request,model
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(SBSSGL_SBGLEntity entity, Model model,HttpServletRequest request){
		String datasuccess="success";
		sBSSGLSbglService.updateInfo(entity);
		//返回结果
		return datasuccess;
	}
	
	/**
	 * 显示所有列
	 * @param id,model 
	 */
	@RequestMapping(value = "colindex", method = RequestMethod.GET)
	public String colindex(Model model) {
		model.addAttribute("url","sbssgl/sbgl/export");
		return "common/formexcel";
	}
	
	/**
	 * 显示所有列
	 * @param id,model 
	 */
	@RequestMapping(value = "colindex2", method = RequestMethod.GET)
	public String colindex2(Model model) {
		model.addAttribute("url","sbssgl/sbgl/exporttzsb");
		return "common/formexcel";
	}
	
	/**
	 * 导出普通设备清单
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("qyname"));
		map.put("m1", request.getParameter("m1"));
		map.put("m2", request.getParameter("m2"));
		map.put("m6", request.getParameter("m6"));
		map.put("m9", request.getParameter("m9"));
		map.put("m16", request.getParameter("m16"));
		map.put("m19", request.getParameter("m19"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		sBSSGLSbglService.exportExcel(response, map);
	}
	
	/**
	 * 导出特种设备清单
	 */
	@RequestMapping(value = "exporttzsb")
	@ResponseBody
	public void exportTzsb(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qyname", request.getParameter("qyname"));
		map.put("m1", request.getParameter("m1"));
		map.put("m2", request.getParameter("m2"));
		map.put("m6", request.getParameter("m6"));
		map.put("m9", request.getParameter("m9"));
		map.put("m16", request.getParameter("m16"));
		map.put("m19", request.getParameter("m19"));
		map.put("colval", request.getParameter("colval"));
		map.put("coltext", request.getParameter("coltext"));
		map.putAll(getAuthorityMap());
		sBSSGLSbglService.exportExcel2(response, map);
	}
	
	/**
	 * 根据该用户的qyid 获取非报废普通设备id、设备名称的 json 数据（使用在combobox中）
	 * @param request,model
	 */
	@RequestMapping(value = "sbjson/")
	@ResponseBody
	public String getSbJson(){
		return sBSSGLSbglService.getSbJson();
	}
	
	/**
	 * 根据qyid 获取非报废普通设备id、设备名称的 json 数据（使用在combobox中）
	 * @param request,model
	 */
	@RequestMapping(value = "sbjson/{qyid}")
	@ResponseBody
	public String getSbJsonByQyid(@PathVariable("qyid") Long qyid){
		return sBSSGLSbglService.getSbJsonByQyid(qyid);
	}
	
	/**
	 * 根据该用户的qyid 获取非报废普通设备编号、设备名称的 json 数据（使用在combobox中）
	 * @param request,model
	 */
	@RequestMapping(value = "sbjson2/")
	@ResponseBody
	public String getSbJson2(){
		return sBSSGLSbglService.getSbJson2();
	}
	
	/**
	 * 根据qyid 获取非报废普通设备编号设备名称的 json 数据（使用在combobox中）
	 * @param request,model
	 */
	@RequestMapping(value = "sbjson2/{qyid}")
	@ResponseBody
	public String getSbJsonByQyid2(@PathVariable("qyid") Long qyid){
		return sBSSGLSbglService.getSbJsonByQyid2(qyid);
	}
	
	/**
	 * 根据设备id 获取非报废普通设备信息的 json 数据
	 * @param request,model
	 */
	@RequestMapping(value = "sbinfojson/{sbid}")
	@ResponseBody
	public String getSbJsonBySbid(@PathVariable("sbid") Long sbid){
		return sBSSGLSbglService.getSbInfoJsonBySbid(sbid);
	}
	
	/**
	 * 根据设备编号 获取非报废普通设备信息的 json 数据
	 * @param request,model
	 */
	@RequestMapping(value = "sbinfojson2/{sbbh}")
	@ResponseBody
	public Map<String, Object> getSbJsonBySbbh(@PathVariable("sbbh") String sbbh){
		return sBSSGLSbglService.getSbInfoJsonBySbbh(sbbh);
	}
	
	/**
	 * 根据该用户的qyid 获取非报废普通设备id、设备编号的 json 数据（使用在combobox中）
	 * @param request,model
	 */
	@RequestMapping(value = "sbjson3/")
	@ResponseBody
	public String getSbJson3(){
		return sBSSGLSbglService.getSbJson3();
	}
	
	/**
	 * 根据qyid 获取非报废普通设备id、设备编号的 json 数据
	 * @param request,model
	 */
	@RequestMapping(value = "sbjson3/{qyid}")
	@ResponseBody
	public String getSbJsonByQyid3(@PathVariable("qyid") Long qyid){
		return sBSSGLSbglService.getSbJsonByQyid3(qyid);
	}
	
	/**
	 * 根据该用户的deptid 获取非报废普通设备id、设备名称的 json 数据（使用在combobox中）
	 * @param request,model
	 */
	@RequestMapping(value = "sbjson4/")
	@ResponseBody
	public String getSbJson4(){
		return sBSSGLSbglService.getSbJson4();
	}
	
	/**
	 * 根据deptid 获取非报废普通设备id、设备名称的 json 数据
	 * @param request,model
	 */
	@RequestMapping(value = "sbjson4/{deptid}")
	@ResponseBody
	public String getSbJsonByQyid4(@PathVariable("deptid") Long deptid){
		return sBSSGLSbglService.getSbJsonByDeptid4(deptid);
	}
	
	/**
	 * 根据该用户的deptid 获取非报废普通设备名称、设备编号的 json 数据（使用在combobox中）
	 * @param request,model
	 */
	@RequestMapping(value = "sbjson5/")
	@ResponseBody
	public String getSbJson5(){
		return sBSSGLSbglService.getSbJson5();
	}
	
	/**
	 * 根据deptid 获取非报废普通设备名称、设备编号的 json 数据
	 * @param request,model
	 */
	@RequestMapping(value = "sbjson5/{deptid}")
	@ResponseBody
	public String getSbJsonByQyid5(@PathVariable("deptid") Long deptid){
		return sBSSGLSbglService.getSbJsonByDeptid5(deptid);
	}
	
	/**
	 * 根据该用户的qyid 获取非报废特种设备id、设备名称的 json 数据
	 * @param request,model
	 */
	@RequestMapping(value = "tzsbjson/")
	@ResponseBody
	public String getTzsbJson(){
		return sBSSGLSbglService.getTzsbJson();
	}
	
	/**
	 * 根据qyid 获取非报废特种设备id、设备名称的 json 数据（使用在combobox中）
	 * @param request,model
	 */
	@RequestMapping(value = "tzsbjson/{qyid}")
	@ResponseBody
	public String getTzsbJsonByQyid(@PathVariable("qyid") Long qyid){
		return sBSSGLSbglService.getTzsbJsonByQyid(qyid);
	}
	
	/**
	 * 根据该用户的qyid 获取非报废特种设备编号、设备名称的 json 数据（使用在combobox中）
	 * @param request,model
	 */
	@RequestMapping(value = "tzsbjson2/")
	@ResponseBody
	public String getTzsbJson2(){
		return sBSSGLSbglService.getTzsbJson2();
	}
	
	/**
	 * 根据qyid 获取非报废特种设备编号、设备名称的 json 数据（使用在combobox中）
	 * @param request,model
	 */
	@RequestMapping(value = "tzsbjson2/{qyid}")
	@ResponseBody
	public String getTzsbJsonByQyid2(@PathVariable("qyid") Long qyid){
		return sBSSGLSbglService.getTzsbJsonByQyid2(qyid);
	}
	
	/**
	 * 根据该用户的deptid 获取非报废特种设备id、设备名称的 json 数据（使用在combobox中）
	 * @param request,model
	 */
	@RequestMapping(value = "tzsbjson4/")
	@ResponseBody
	public String getTzsbJson4(){
		return sBSSGLSbglService.getTzsbJson4();
	}
	
	/**
	 * 根据deptid 获取非报废特种设备id、设备名称的 json 数据（使用在combobox中）
	 * @param request,model
	 */
	@RequestMapping(value = "tzsbjson4/{deptid}")
	@ResponseBody
	public String getTzsbJsonByQyid4(@PathVariable("deptid") Long deptid){
		return sBSSGLSbglService.getTzsbJsonByQyid4(deptid);
	}
	
	/**
	 * 根据该用户的deptid 获取非报废特种设备编号、设备名称的 json 数据（使用在combobox中）
	 * @param request,model
	 */
	@RequestMapping(value = "tzsbjson5/")
	@ResponseBody
	public String getTzsbJson5(){
		return sBSSGLSbglService.getTzsbJson5();
	}
	
	/**
	 * 根据deptid 获取非报废特种设备编号、设备名称的 json 数据（使用在combobox中）
	 * @param request,model
	 */
	@RequestMapping(value = "tzsbjson5/{deptid}")
	@ResponseBody
	public String getTzsbJsonByQyid5(@PathVariable("deptid") Long deptid){
		return sBSSGLSbglService.getTzsbJsonByQyid5(deptid);
	}
}
