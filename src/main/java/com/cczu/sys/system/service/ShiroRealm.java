package com.cczu.sys.system.service;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.utils.security.Encodes;
import com.cczu.sys.system.entity.*;
import com.cczu.sys.system.utils.CaptchaException;
import com.cczu.sys.system.utils.UsernamePasswordCaptchaToken;
import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
/**
 * 用户登录授权service(shrioRealm)
 * @author jason
 * @date 2017年5月31日
 */
@Service
@DependsOn({"userDao","permissionDao","rolePermissionDao"})
public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;

	@Autowired
	private BarrioService barrioService;
	
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;

	@Autowired
	private CompUserDepService compUserDepService;

	@Autowired
	private CompRolePermissionService compRolePermissionService;
	
	@Autowired
	private DepartmentService departmentService;
	
	protected final Log log = LogFactory.getLog(getClass());
	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken; 
		Session session =SecurityUtils.getSubject().getSession();
		System.out.println(token.getUsername());
		User user = userService.getUser(token.getUsername());
		
		Long qyid=(long) 0;

		if (user != null&&doCaptchaValidate(token)) {
			if(StringUtils.isNotBlank(user.getDelFlag())&&"1".equals(user.getDelFlag())){
				throw new DisabledAccountException("用户已禁用！");
			}
			
			byte[] salt = Encodes.decodeHex(user.getSalt());
			List<Role> roles=roleService.findRoleById(user.getId());

			Barrio bar=new Barrio();
			if(user.getXzqy()!=null){
				bar=barrioService.getBarrioByCode(user.getXzqy());
			}
			
			Department dep=null;
			List<Long> depIdList=null;
			if(user.getUsertype().equals("1")){
				if(user.getId2()!=null){
					try {
						BIS_EnterpriseEntity bis=bisQyjbxxService.findInfoById(user.getId2());
						if ("0".equals(bis.getState())) {
							throw new DisabledAccountException("用户已禁用！");
						}
						if(bis!=null){
							qyid=bis.getID();
							session.setAttribute("logotitle",bis.getM1());
							session.setAttribute("logoimg",bis.getQylogo());
						}
					}catch (Exception e){
						System.out.println(e);
					}
				}
				if(user.getDepartmen()!=null){
					dep=departmentService.getDepartmentById(user.getDepartmen());
					depIdList=compUserDepService.getCompDepIdList(user.getId());
				}
			}else{
//				session.setAttribute("logotitle","高港区城市安全与应急管理一体化云平台");
//				session.setAttribute("logoimg","/static/model/main/images/logo.png");
			}
			
			ShiroUser shiroUser=new ShiroUser(user.getId(), user.getLoginName(), user.getName(),user.getUsertype(),user.getXzqy(),roles,bar,qyid,user.getUserroleflg(),dep,depIdList, user.getElecsignature(),user.getSeal());

			
//			//设置登录次数、时间
//			System.out.println("doGetAuthenticationInfo--updateUserLogin设置登录次数、时间");
			userService.updateUserLogin(user);
			log.info(user.getLoginName()+"【登录成功】");
			
			//设置用户session
			session.setAttribute("user", user);
			
//			return info;
			return new SimpleAuthenticationInfo(shiroUser,user.getPassword(), ByteSource.Util.bytes(salt), getName());
		} else {
			return null;
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		User user = userService.getUser(shiroUser.loginName);
		
		//把principals放session中 key=userId value=principals
		SecurityUtils.getSubject().getSession().setAttribute(String.valueOf(user.getId()),SecurityUtils.getSubject().getPrincipals());
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//赋予角色
		for(UserRole userRole:user.getUserRoles()){
//			info.addRole(userRole.getRole().getName());
			info.addRole(userRole.getRole().getRoleCode());
		}
		//赋予权限
		for(Permission permission:permissionService.getPermissions(user.getId())){
			if(StringUtils.isNotBlank(permission.getPermCode()))
			info.addStringPermission(permission.getPermCode());
		}
		
		//如果用户类型是企业用户，则需要查询该用户在企业的内部角色信息
		if(user.getUsertype().equals("1")){
			//赋予权限
			for(Permission permission:compRolePermissionService.getPermissionList(user.getId())){
				if(StringUtils.isNotBlank(permission.getPermCode()))
				info.addStringPermission(permission.getPermCode());
			}
		}
		
		
		//设置登录次数、时间
//		userService.updateUserLogin(user);
//		System.out.println("设置登录次数、时间");
		return info;
	}
	
	/**
	 * 验证码校验
	 * @param token
	 * @return boolean
	 */
	protected boolean doCaptchaValidate(UsernamePasswordCaptchaToken token)
	{
		String captcha = (String) SecurityUtils.getSubject().getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (captcha != null &&!captcha.equalsIgnoreCase(token.getCaptcha())){
			throw new CaptchaException("验证码错误！");
		}
		return true;
	}
		

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@SuppressWarnings("static-access")
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(userService.HASH_ALGORITHM);
		matcher.setHashIterations(userService.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		public Integer id;
		public String loginName;
		public String name;
		public String usertype;
		public String xzqy;
		public Long qyid;
		public List<Role> roles;
		public Barrio bar;
		public Integer userroleflg;
		public Department dep;
		public List<Long> depIdList;
		public String elecsignature;// 电子签章
		public String seal;

		public ShiroUser(Integer id, String loginName, String name,String usertype,String xzqy,List<Role> roles,Barrio bar,Long qyid,Integer userroleflg,Department dep,List<Long> depIdList, String elecsignature,String seal) {
			this.id = id;
			this.loginName = loginName;
			this.name = name;
			this.usertype=usertype;
			this.xzqy=xzqy;
			this.roles=roles;
			this.bar=bar;
			this.qyid=qyid;
			this.userroleflg=userroleflg;
			this.dep=dep;
			this.depIdList=depIdList;
			this.seal=seal;
			this.elecsignature=elecsignature;
		}
		
		public String getElecsignature() {
			return elecsignature;
		}
		
		public Integer getId(){
			return id;
		}

		public String getName() {
			return name;
		}

		public String getLoginName() {
			return loginName;
		}
		
		public String getUsertype(){
			return usertype;
		}
		
		public String getXzqy(){
			return xzqy;
		}
		
		public Long getQyid(){
			return qyid;
		}
		
		public List<Role> getRoles() {
			return roles;
		}
		
		public Barrio getBar() {
			return bar;
		}
		public Integer getUserroleflg(){
			return userroleflg;
		}

		public Department getDep() {
			return dep;
		}

		public List<Long> getDepIdList() {
			return depIdList;
		}

		public String getSeal() {
			return seal;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return loginName;
		}

		/**
		 * 重载hashCode,只计算loginName;
		 */
		@Override
		public int hashCode() {
			return Objects.hashCode(loginName);
		}

		/**
		 * 重载equals,只计算loginName;
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ShiroUser other = (ShiroUser) obj;
			if (loginName == null) {
				if (other.loginName != null) {
					return false;
				}
			} else if (!loginName.equals(other.loginName)) {
				return false;
			}
			return true;
		}
	}
	
	@Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
