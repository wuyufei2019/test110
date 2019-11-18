package com.cczu.sys.system.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cczu.model.service.*;
import com.cczu.model.service.impl.BisCgxxServiceImpl;
import com.cczu.model.service.impl.BisGwgyServiceImpl;
import com.cczu.model.service.impl.BisYgxxServiceImpl;
import com.cczu.model.zdwxyssjc.service.*;
import com.cczu.model.zyaqgl.service.AqglBgsqService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.Global;
import com.cczu.util.common.StringUtils;
import freemarker.ext.beans.MapModel;
import freemarker.ext.beans.StringModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.sys.system.entity.User_Define;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 化工企业系统跳转controller
 * @author zpc
 * @date 2019年8月14日
 */
@Controller
@RequestMapping(value = "hgqyIndex")
public class HgqyIndexController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Autowired
	private BisCgxxServiceImpl bisCgxxService;
	@Autowired
	private BisGwgyServiceImpl bisGwgyService;
	@Autowired
	private BisSpsbxxService bisSpsbxxService;
	@Autowired
	private BisYgxxServiceImpl bisYgxxService;
	@Autowired
	private FxgkFxxxService fxgkFxxxService;
	@Autowired
	private YhpcDsjService yhpcDsjService;
	@Autowired
	private BisCgjcwhsjService bisCgjcwhsjService;
	@Autowired
	private MonitorZdwxyBjDataService monitorZdwxyBjDataService;
	@Autowired
	private MonitorZdwxyQtService monitorZdwxyQtService;
	@Autowired
	private MonitorZdwxyCgService monitorZdwxyCgService;
	@Autowired
	private MonitorZdwxyGwgyService monitorZdwxyGwgyService;
    @Autowired
    private AqglBgsqService aqglBgsqService;
    @Autowired
    private YhpcYhpcjlService yhpcYhpcjlService;

	/**
	 * 主菜单跳转
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(Model model) {
		return "system/hgqy/index_hgqy";
	}
	
	/**
	 * 大数据页面跳转
	 * @return
	 */
	@RequestMapping(value = "bigdata/{type}")
	public String bigdataIndex(@PathVariable("type") Integer type, HttpServletRequest request, Model model) {
		Long qyid = 0L;
		BIS_EnterpriseEntity bis = null;
		if (StringUtils.isNotEmpty(request.getParameter("qyid"))) {
			qyid = Long.parseLong(request.getParameter("qyid"));
		} else {
			ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
			qyid = sessionuser.getQyid();
		}
        model.addAttribute("qyid", qyid);
		bis= bisQyjbxxService.findInfoById(qyid);
		model.addAttribute("bis", bis);
        Map<String, Object> map = new HashMap<>();
        map.put("qyid", qyid);
		if (type == 1) {//重大危险源大数据
		    model = this.getZdwxydsjData(map, qyid, model);

			return "system/vd2/zdwxy2";
		} else if (type == 2) {//人员定位大数据

            return "lydw/rydw/wbindex_vd";
			//return "lydw/rydw/cap-index";
		} else if (type == 3) {//双重预防机制大数据
			model.addAttribute("bisenterprise",bis);
			model.addAttribute("jryhlist",yhpcDsjService.jryh(qyid));
			//风险点列表
			List<Map<String,Object>> fxdlist = fxgkFxxxService.bigDataGetFxdList(map);
			model.addAttribute("fxdlist",fxdlist);

			return "system/vd2/com";
		} else if (type == 4) {//风险分区大数据
            model = this.getFxfqdsjData(map, model);

			//return "system/hgqy/fxfqglhome_new";
            return "system/vd2/fxfq";
		} else {
			return "system/hgqy/index_hgqy";
		}
	}
	
	/**
	 * 化工企业系统跳转
	 * @return
	 */
	@RequestMapping(value = "sys/{type}/{pid}")
	public String hgqyIndex(@PathVariable("type") Integer type,@PathVariable("pid") Integer pid,Model model) {
		model.addAttribute("pid", pid);
		if(type == 1){
			return "system/hgqy/index_zdwxyjkyj";
		}else if(type == 2){
			return "system/hgqy/index_qyaqfxfqgl-new";
		}else if(type == 3){
			return "system/hgqy/index_ryzgzwgl";
		}else if(type == 4){
			return "system/hgqy/index_qyaqscqlcgl";
		}else{
			return "system/hgqy/index_hgqy";
		}
	}
	
	/**
	 * 重大危险源监测预警首页跳转
	 * @return
	 */
	@RequestMapping(value = "/zdwxyhome")
	public String zdwxyhome(HttpServletRequest request, Model model) {
		Long qyid = null;
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();

		if (StringUtils.isNotEmpty(request.getParameter("qyid"))) {
			qyid = Long.parseLong(request.getParameter("qyid"));
		} else {
			qyid = sessionuser.getQyid();
		}
		List<Map<String,Object>> bjxxList = bisCgjcwhsjService.findAllnewBjxx(qyid, null);
		model.addAttribute("bjxxList", bjxxList);
		model.addAttribute("qyid", qyid);
		return "system/hgqy/zdwxyhome";
	}
	
	/**
	 * 企业安全风险分区首页跳转
	 * @return
	 */
	@RequestMapping(value = "/fxfqglhome")
	public String fxfqglhome(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity bis= bisQyjbxxService.findInfoById(sessionuser.getQyid());
		model.addAttribute("bis", bis);
		return "system/hgqy/fxfqglhome_new";
	}

	/**
	 * 企业安全风险分区首页跳转
	 * @return
	 */
	@RequestMapping(value = "/ldsk")
	public String ldsk(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity bis= bisQyjbxxService.findInfoById(sessionuser.getQyid());
		model.addAttribute("bis", bis);
		return "system/hgqy/ldsk";
	}
	
	/**
	 * 企业安全生产全流程首页跳转
	 * @return
	 */
	@RequestMapping(value = "/aqscqlchome")
	public String aqscqlchome(Model model) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		BIS_EnterpriseEntity bis= bisQyjbxxService.findInfoById(sessionuser.getQyid());
		model.addAttribute("company", bis);
		//查询用户自定义界面内容
		User_Define ud=userService.findMenuByUserid(sessionuser.getId());
		model.addAttribute("userdefine", ud);
		return "system/hgqy/aqscqlchome";
	}

	/**
	 * 根据配置文件中地图类型判断地图页面跳转
	 * @return
	 */
	@RequestMapping(value = "/map")
	public String mapPage(Model model) {
		ShiroUser user = UserUtil.getCurrentShiroUser();
		String mapType = Global.getConfig("mapType");
		if ("thingjs".equals(mapType)) {
			return "system/vd2/map_thingjs";
		}else if ("fengmap".equals(mapType)) {
			return "system/vd2/map_fengmap";
		}else {
			BIS_EnterpriseEntity bis= bisQyjbxxService.findInfoById(user.getQyid());
			Map<String, Object> map = new HashMap<>();
			map.put("qyid", bis.getID());
			List<Map<String, Object>> cgLngLatList = bisCgxxService.getCgLngLatList(map);// 储罐经纬度集合
			model.addAttribute("qylng", bis.getM16());// 企业经度
			model.addAttribute("qylat", bis.getM17());// 企业纬度
			model.addAttribute("cgLngLatList", JsonMapper.toJsonString(cgLngLatList));
			return "system/vd2/map_baidu";
		}
	}

	/**
	 * 主菜单页面填充数据
	 * @return
	 */
	@RequestMapping(value = "/getmenudata")
	@ResponseBody
	public String getMenuData(Model model) {
	    Map<String, Object> rtnMap = new HashMap<>();
        ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
        if ("1".equals(sessionuser.getUsertype())) {
            Map<String, Object> map = new HashMap<>();
            map.put("qyid", sessionuser.getQyid());
            int cgCount = bisCgxxService.getCgCount(map);// 储罐数量
            int qtCount = bisCgjcwhsjService.getQtCount(map);// 气体点位数量
			List<Map<String, Object>> ggList = bisCgjcwhsjService.findAllnewBjxx(sessionuser.getQyid(), null);
			int ggslCount = ggList.size();// 告警数量
            int spCount = bisSpsbxxService.getSpCount(map);// 视频数量
            int ygCount = bisYgxxService.getYgCount(map);// 员工数量
            map.put("fxfj", "1");
            List<Map<String, Object>> list = fxgkFxxxService.getListByMap(map);
            map.put("fxfj", "2");
            List<Map<String, Object>> list1 = fxgkFxxxService.getListByMap(map);
            map.put("fxfj", "3");
            List<Map<String, Object>> list2 = fxgkFxxxService.getListByMap(map);
            map.put("fxfj", "4");
            List<Map<String, Object>> list3 = fxgkFxxxService.getListByMap(map);

            rtnMap.put("cgCount", cgCount);
            rtnMap.put("qtCount", qtCount);
			rtnMap.put("ggslCount", ggslCount);
            rtnMap.put("spCount", spCount);
            rtnMap.put("ygCount", ygCount);
            rtnMap.put("redCount", list.size());
            rtnMap.put("oraCount", list1.size());
            rtnMap.put("yelCount", list2.size());
            rtnMap.put("bluCount", list3.size());
        }
		return JsonMapper.toJsonString(rtnMap);
	}

    /**
     * 重大危险源大数据页面数据
     * @param map
     * @param qyid
     * @param model
     * @return
     */
	public Model getZdwxydsjData(Map<String, Object> map, Long qyid, Model model) {
        // 储罐数量
        int cgCount = bisCgxxService.getCgCount(map);
		// 有实时数据的储罐数量
		int cgSsCount = 0;
		List<Map<String, Object>> cjSsList = monitorZdwxyCgService.getJcdxqInfo(qyid, null);
		if (cjSsList != null && cjSsList.size() > 0) {
			cgSsCount = cjSsList.size();
		}
        // 储罐报警数量
        int cjBjCount = 0;
        List<Map<String, Object>> cjBjList = monitorZdwxyCgService.getJcdxqInfo(qyid, "bj");
        if (cjBjList != null && cjBjList.size() > 0) {
            cjBjCount = cjBjList.size();
        }
		// 储罐正常数量
		int cjZcCount = 0;
		List<Map<String, Object>> cjZcList = monitorZdwxyCgService.getJcdxqInfo(qyid, "zc");
		if (cjZcList != null && cjZcList.size() > 0) {
			cjZcCount = cjZcList.size();
		}
        // 储罐离线数量
        int cjLxCount = 0;
        List<Map<String, Object>> cjLxList = monitorZdwxyCgService.getJcdxqInfo(qyid, "lx");
        if (cjLxList != null && cjLxList.size() > 0) {
            cjLxCount = cjLxList.size();
        }
        // 监测点总数量
        int zCount = monitorZdwxyCgService.getGjCount(qyid, null);
        // 监测点告警数量
        int gjCount = monitorZdwxyCgService.getGjCount(qyid, "gj");
        // 监测点离线数量
        int lxCount = monitorZdwxyCgService.getLxCount(qyid, "lx");
        // 监测点温度告警、离线数量
        Map<String, Object> wdMap = monitorZdwxyCgService.getCountByType(qyid, "WD");
        // 监测点压力告警、离线数量
        Map<String, Object> ylMap = monitorZdwxyCgService.getCountByType(qyid, "YL");
        // 监测点液位告警、离线数量
        Map<String, Object> ywMap = monitorZdwxyCgService.getCountByType(qyid, "YW");
        // 最新的报警数据
        List<Map<String,Object>> bjxxList = bisCgjcwhsjService.findAllnewBjxx(qyid, null);
        // 气体实时监测图形展示数据
        List<Map<String,Object>> qttxInfoList = monitorZdwxyQtService.getDsjQttxInfo(map);
        // 气体数据列表
        List<Map<String,Object>> qtsjlbList = monitorZdwxyQtService.getAllQtInfo(map);
        // 高危工艺数据列表
        List<Map<String,Object>> gwgylbList = monitorZdwxyGwgyService.findInfoByQyid(qyid);
        // 当前日期
        Date now = new Date();

        model.addAttribute("now", now);
        model.addAttribute("cgCount", cgCount);
		model.addAttribute("cgSsCount", cgSsCount);
		model.addAttribute("cjZcCount", cjZcCount);
        model.addAttribute("cjBjCount", cjBjCount);
        model.addAttribute("cjLxCount", cjLxCount);
        model.addAttribute("zCount", zCount);
        model.addAttribute("gjCount", gjCount);
        model.addAttribute("lxCount", lxCount);
        model.addAttribute("wdMap", wdMap);
        model.addAttribute("ylMap", ylMap);
        model.addAttribute("ywMap", ywMap);
        model.addAttribute("bjxxList", bjxxList);
        model.addAttribute("qttxInfoList", qttxInfoList);
        model.addAttribute("qtsjlbList", qtsjlbList);
        model.addAttribute("gwgylbList", gwgylbList);
        return model;
    }

    /**
     * 风险分区大数据页面数据
     * @param map
     * @param model
     * @return
     */
    public Model getFxfqdsjData(Map<String, Object> map, Model model) {
        // 风险清单
        List<Map<String, Object>> list = fxgkFxxxService.getListByMap(map);
        // 储罐数量
        int cgCount = bisCgxxService.getCgCount(map);
        // 气体数量
        int qtCount = monitorZdwxyQtService.getQtCount(map);
        // 高危工艺数量
        int gwgyCount = monitorZdwxyGwgyService.getGwgyCount(map);
        // 视频数量
        int spCount = bisSpsbxxService.getSpCount(map);

        // 红色风险点数量
        int redCount = 0;
        map.put("fxfj", "1");
        List<Map<String, Object>> redList = fxgkFxxxService.getListByMap(map);
        if (redList != null && redList.size() > 0) {
            redCount = redList.size();
        }
        // 橙色风险点数量
        int orangeCount = 0;
        map.put("fxfj", "2");
        List<Map<String, Object>> orangeList = fxgkFxxxService.getListByMap(map);
        if (orangeList != null && orangeList.size() > 0) {
            orangeCount = orangeList.size();
        }
        // 风险预警数量
        int fxyjCount = redCount + orangeCount;
        // 变更预警数量
        int bgyjCount = aqglBgsqService.getBgxxCount(map);
        // 当前隐患数量
        int dqyhCount = 0;
        List<Map<String, Object>> dqyhList = yhpcYhpcjlService.getYhInfoByMap(map);
        if (dqyhList != null && dqyhList.size() > 0) {
            dqyhCount = dqyhList.size();
        }

        // 当前日期
        Date now = new Date();

		long qyid = UserUtil.getCurrentShiroUser().getQyid();
		//企业平面图
		BIS_EnterpriseEntity bis = bisQyjbxxService.findInfoById(qyid);
		//风险点信息
		List<Map<String,Object>> list2 = fxgkFxxxService.getAllByQyid(map);

        model.addAttribute("now", new SimpleDateFormat("yyyy年MM月dd日").format(now));
        model.addAttribute("fxqd", list);
        model.addAttribute("cgCount", cgCount);
        model.addAttribute("qtCount", qtCount);
        model.addAttribute("gwgyCount", gwgyCount);
        model.addAttribute("spCount", spCount);
        model.addAttribute("fxyjCount", fxyjCount);
        model.addAttribute("bgyjCount", bgyjCount);
        model.addAttribute("dqyhCount", dqyhCount);
		model.addAttribute("bis", bis);
		model.addAttribute("fxdlist", JsonMapper.getInstance().toJson(list2));
        return model;
    }

}
