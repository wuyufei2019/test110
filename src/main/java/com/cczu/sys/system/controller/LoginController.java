package com.cczu.sys.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IAqpxKCxxService;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.model.service.YhpcJcyhService;
import com.cczu.model.service.YhpcTjfxService;
import com.cczu.sys.comm.utils.Global;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.entity.User_Define;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 登录controller
 *
 * @author jason
 * @date 2017年5月31日
 */
@Controller
@RequestMapping(value = "${adminPath}")
public class LoginController {
    @Autowired
    private IBisQyjbxxService bisQyjbxxService;
    @Autowired
    private YhpcTjfxService yhpcTjfxService;
    @Autowired
    private UserService userService;
    @Autowired
    private IAqpxKCxxService aqpxKCxxService;
    @Autowired
    private YhpcJcyhService yhpcJcyhService;


    protected final Log log = LogFactory.getLog(getClass());


    /**
     * 默认页面
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() || subject.isRemembered()) {
            User user = (User) subject.getSession().getAttribute("user");
            if (user == null)
                return "system/login";
            else if (user.getUsertype().equals("1"))
                return "system/hgqy/index_hgqy";
            else
                return "system/index";
        }
        return "system/login";
    }


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getSession().getAttribute(LoginChooseController.DEFAULT_SESSION_LOGINURL) != null)
            subject.getSession().removeAttribute(LoginChooseController.DEFAULT_SESSION_LOGINURL);
        if (subject.isAuthenticated() || subject.isRemembered()) {
            return "redirect:" + Global.getAdminPath();
        }
        return "system/login";
    }

    /**
     * 登录失败
     *
     * @param userName
     * @param model
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes attributes) {
//		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
//		// 如果已经登录，则跳转到管理首页
//		if(sessionuser.getId() != null){
//			return "redirect:"+Global.getAdminPath();
//		}
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() || subject.isRemembered()) {
            return "redirect:" + Global.getAdminPath();
        }
        model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);

        Session session = subject.getSession();
        String loginurl = "system/login";
        Object object = session.getAttribute(LoginChooseController.DEFAULT_SESSION_LOGINURL);
        if (object != null) {
            attributes.addFlashAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
            attributes.addFlashAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME));
            loginurl = "redirect:" + object.toString();
        }
        return loginurl;
    }

    /**
     * 退出
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "logout")
    public String logout(Model model) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String loginurl = "system/login";
        Object object = session.getAttribute("loginurl");
        if (object != null)
            loginurl = "redirect:" + object.toString();

        log.info(subject.getPrincipal() + "【退出登录】");
        if (subject.isAuthenticated()) {
            subject.logout();
        }

        return loginurl;
    }


    /**
     * 首页
     */
    @RequestMapping(value = "home")
    public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
        ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
        if (sessionuser.getUsertype().equals("0")) {  //安监首页
            return "redirect:/syshome/map";
        } else if (sessionuser.getUsertype().equals("1")) {//企业首页
            BIS_EnterpriseEntity bis = bisQyjbxxService.findInfoById(sessionuser.getQyid());
            model.addAttribute("company", bis);
            //查询用户自定义界面内容
            User_Define ud = userService.findMenuByUserid(sessionuser.getId());
            model.addAttribute("userdefine", ud);
            model.addAttribute("usertype", sessionuser.getUsertype());

            //查询用户是否需要学习
            User user = userService.getUserById(sessionuser.getId());
            //日常培训
            List<Map<String, Object>> list = aqpxKCxxService.getKclist(sessionuser.getQyid(), user.getDepartmen());
            if (list.size() > 0) {
                model.addAttribute("study", "有");
            } else {
                model.addAttribute("study", "无");
            }

            //查询用户是否有隐患需要整改
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("qyid", sessionuser.getQyid());
            map.put("zgr", sessionuser.getId());
            map.put("userid", sessionuser.getId());
            List<Map<String, Object>> yhlist = yhpcJcyhService.dataGrid2(map);
            if (yhlist.size() > 0) {
                model.addAttribute("yhzg", "有");
            } else {
                model.addAttribute("yhzg", "无");
            }

            if (bis.getIsbloc() != null && bis.getIsbloc() == 1) {//判断是否为集团公司
                Map<String, Object> mapData = new HashMap<>();
                mapData.put("fid", sessionuser.getQyid());
                List<Map<String, Object>> jtgslist = yhpcTjfxService.getJtData(mapData);
                int allxjds = 0;
                int allyxjds = 0;
                int allyhs = 0;
                int allzgyhs = 0;
                for (Map<String, Object> jtgsmap : jtgslist) {
                    allxjds += (int) jtgsmap.get("xjds");
                    allyxjds += (int) jtgsmap.get("yxjds");
                    allyhs += (int) jtgsmap.get("yhs");
                    allzgyhs += (int) jtgsmap.get("zgyhs");
                }
                model.addAttribute("allxjds", allxjds);
                model.addAttribute("allyxjds", allyxjds);
                model.addAttribute("allwxjds", allxjds - allyxjds);
                model.addAttribute("allyhs", allyhs);
                model.addAttribute("allzgyhs", allzgyhs);
                model.addAttribute("jtgslist", jtgslist);
                return "system/qyGroupHome";
            } else {
                Map<String, Object> mapData = new HashMap<>();
                mapData.put("qyid", sessionuser.getQyid());
                List<Map<String, Object>> xjlist = yhpcTjfxService.getJtData(mapData);
                if (xjlist != null && xjlist.size() > 0)
                    model.addAttribute("xjlist", xjlist.get(0));
                return "system/qyHome";
            }
        } else {//其他类型用户首页
            return "redirect:/syshome/map";
        }
    }

    /**
     * 返回菜单
     */
    @RequestMapping(value = "mainmenu")
    public String success(HttpServletRequest request, HttpServletResponse response, Model model) {
        ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
        if (sessionuser.getUsertype().equals("0")) {  //安监菜单页
            if (sessionuser.getXzqy().contentEquals("10-41-"))
                return "/system/newframe/ajindex";
            else
                return "/system/newframe/sysindex";

        } else {
            return "/system/newframe/sysindex";
        }
    }


    /**
     * 首页
     */
    @RequestMapping(value = "en/home")
    public String ENhome(HttpServletRequest request, HttpServletResponse response, Model model) {
        ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("qyid", sessionuser.getQyid());
        List<Map<String, Object>> xjlist = yhpcTjfxService.getJtData(mapData);
        if (xjlist != null && xjlist.size() > 0)
            model.addAttribute("xjlist", xjlist.get(0));
        return "system/qyHome_en";
    }


    /**
     * 主页main页面跳转
     */
    @RequestMapping(value = "nav")
    public String mainNav(Model model) {
        return "/system/nav";
    }

    /**
     * 大数据
     */
    @RequestMapping(value = "vd_com")
    public String vd_com(Model model) {
        return "/system/vd2/com";
    }

    /**
     * 大数据
     */
    @RequestMapping(value = "vd_gov")
    public String vd_gov(Model model) {
        return "/system/vd2/gov";
    }
}
