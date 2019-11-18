package com.cczu.model.service.impl;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.AqpxSjjyHistoryDao;
import com.cczu.model.dao.BisYgtjxxDao;
import com.cczu.model.dao.BisYgxxDao2;
import com.cczu.model.dao.impl.AqpxKscjDaoImpl;
import com.cczu.model.dao.impl.AqpxXxjlDaoImpl;
import com.cczu.model.dao.impl.BisOccupharmDaoImpl;
import com.cczu.model.dao.impl.BisYgxxDaoImpl;
import com.cczu.model.dao.impl.TsCardDaoImpl;
import com.cczu.model.entity.AQPX_ThreeLevelEducationHistoryEntity;
import com.cczu.model.entity.BIS_Employee2Entity;
import com.cczu.model.entity.BIS_EmployeeEntity;
import com.cczu.model.entity.TS_Card;
import com.cczu.model.service.IBisYgxxService;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.security.Digests;
import com.cczu.sys.comm.utils.security.Encodes;
import com.cczu.sys.system.dao.DepartmentDao;
import com.cczu.sys.system.dao.RoleDao;
import com.cczu.sys.system.dao.UserDao;
import com.cczu.sys.system.dao.UserRoleDao;
import com.cczu.sys.system.entity.Department;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.entity.UserRole;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;

@Transactional(readOnly=true)
@Service("BisYgxxService")
public class BisYgxxServiceImpl implements IBisYgxxService {
	
	/**加密方法*/
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;	//盐长度
	
	@Resource
	private BisYgxxDaoImpl bisYgxxDao;
	@Resource
	private BisYgxxDao2 bisYgxxDao2;
	@Resource
	private TsCardDaoImpl cardDaoImpl;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private	DepartmentDao departmentDao;
	@Resource
	private AqpxSjjyHistoryDao aqpxSjjyHistoryDao;//三级教育
	@Resource
	private BisYgtjxxDao bisYgtjxxDao;//员工体检
	@Resource
	private AqpxXxjlDaoImpl aqpxxxdao;//学习记录
	@Resource
	private AqpxKscjDaoImpl aqpxkscjdao;//考试记录
	@Resource
	private BisOccupharmDaoImpl bisOccupharmDao;//职业病因素


	@Override
	public void addInfo(BIS_EmployeeEntity em) {
		/*//添加对应的用户信息
		User user=new User();
		user.setLoginName(em.getM2());//登录名(工号)
		user.setName(em.getM1());//昵称
		user.setPlainPassword("000000");//密码
		entryptPassword(user);
		user.setCreateDate(DateUtils.getSysTimestamp());
		user.setUsertype("1");
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		int l=userDao.saveback(user);//保存用户信息
		//用户保存成功添加，为用户添加角色
		if(l>0){
			Role role=new Role();
			UserRole ur = new UserRole();
			ur.setUser(new User(l));
			//设置添加的用户角色为企业普通用户
			role=roleDao.findRoleByRoleCode("company");
			ur.setRole(role);
			User user2=userDao.find(l);
			user2.setId2(sessionuser.getQyid());
			userDao.save(user2);
			userRoleDao.save(ur);
		}
		em.setID1((long) l);*/
		bisYgxxDao.save(em);
	}

	@Override
	public void addInfo2(BIS_EmployeeEntity em) {
		bisYgxxDao.save(em);
	}
	
	@Override
	public long addInforReturnID(BIS_EmployeeEntity em) {
		bisYgxxDao.save(em);
		return em.getID();
	}

	@Override
	public void updateInfo(BIS_EmployeeEntity em) {
		// 修改员工信息
		bisYgxxDao.save(em);
		// 修改用户信息
		if (em.getID1() != null) {
			User user = userDao.findById(Integer.parseInt(em.getID1().toString()));
			if (user != null) {
				user.setBirthday(em.getM10());
				if (StringUtils.isNotBlank(em.getM3())) {
					user.setGender((em.getM3().equals("男")) ? (short) 1: (short) 0);
				}
				user.setPhone(em.getM9());
				user.setDepartmen(em.getID4());
				userDao.save(user);
			}
		}
	}

	
	@Override
	public void deleteInfo(Long id) {
		//删除员工
		bisYgxxDao.deleteInfo(id);
		//根据工号删除对应的用户
		BIS_EmployeeEntity yg=bisYgxxDao.find(id);
		bisYgxxDao.deleteUserByM2(yg.getM2());
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=bisYgxxDao.dataGrid(mapData);
		int getTotalCount=bisYgxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public Map<String, Object> swrylist(Map<String, Object> mapData) {
		List<Map<String, Object>> list=bisYgxxDao.swrylist(mapData);
		int getTotalCount=bisYgxxDao.getSwryTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	@Override
	public Map<String, Object> swrylist2(Map<String, Object> mapData) {
		List<BIS_EmployeeEntity> list=bisYgxxDao.swrylist2(mapData);
		int getTotalCount=bisYgxxDao.getSwryTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	@Override
	public BIS_EmployeeEntity findInfoByID(Long id) {
		return bisYgxxDao.findByID(id);
}
	
	@Override
	public Map<String, Object> findAllByID(Long id){
		return bisYgxxDao.findAllByID(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response,Map<String, Object> mapData) {
		String fileName="员工信息表.xls";
		List<Map<String, Object>> list=bisYgxxDao.getExcel(mapData);
		//格式化时间，让其不显示微秒
		for (Map<String, Object> map : list) {
			Timestamp m10 = (Timestamp)map.get("m10");
			Timestamp m14 = (Timestamp)map.get("m14");
			if (!"".equals(m10) && m10 != null) {
				String birthday = DateUtils.formatDate(new Date(m10.getTime()));
				map.put("m10", birthday);
			}
			if (!"".equals(m14) && m14 != null) {
				String rz_date = DateUtils.formatDate(new Date(m14.getTime()));
				map.put("m14", rz_date);
			}
		}
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response, true);
	}

	@Override
	public Map<String,Object> exinExcel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String,Object> resultmap = new HashMap<String, Object>();
		int result = 0;
		ExinExcel exin = new ExinExcel();
		List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
		int i = 0, error = 0;
		if (list.size() > 3) {
			result = 0;
			resultmap.put("total", list.size()-3);
			resultmap.put("returncode", "success");
			//查找所有部门
			Map<String ,Object> map2=new HashMap<>();
			map2.put("id2", UserUtil.getCurrentShiroUser().getQyid());
			List<Department> deplist=departmentDao.findJson(map2);
			for (List<Object> bis : list) {
				if(i<=2){ //跳过前三行
					i++;
					continue;
				}
				try{
					BIS_EmployeeEntity em = new BIS_EmployeeEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					em.setS1(t);
					em.setS2(t);
					em.setS3(0);
					em.setID3(Long.valueOf(map.get("qyid").toString()));
					String gh=bis.get(0).toString().replace("\\s+", "");
					em.setM2(gh);//工号
					em.setM1(bis.get(1).toString());//姓名
					em.setM3(bis.get(2).toString());//性别
					for (Department dep : deplist) {
						String depname=bis.get(8).toString();//模版里的部门名称
						if(depname.equals(dep.getM1())){
							em.setID4(dep.getId());//部门ID
							break;
						}
					}
					//如果没有对应的部门id，则终止当前插入
					if(em.getID4()==null){
						continue;
					}
					em.setM4(bis.get(9).toString());//岗位、工种（职位）
					em.setM5(bis.get(11).toString());//学历
					em.setM6(bis.get(14).toString());//专业
					em.setM7(bis.get(10).toString());//职称（职务）
					em.setM8(bis.get(3).toString());//身份证号
					em.setM9(bis.get(15).toString());//联系方式
					em.setM11(bis.get(7).toString());//婚姻状况
					em.setM12(bis.get(12).toString());//贯籍
					em.setM13(bis.get(13).toString());//民族
					em.setM14(StringUtils.isBlank(bis.get(6).toString())?null:Timestamp.valueOf(bis.get(6).toString()));//入职日期
					em.setM10(StringUtils.isBlank(bis.get(5).toString())?null:Timestamp.valueOf(bis.get(5).toString()));//出生日期
					em.setM16(bis.get(4).toString());//人员类别
					em.setM17(UUID.randomUUID().toString().replaceAll("-", ""));//二维码
					em.setStatus(1);//人员状态
					
					//添加员工信息
					bisYgxxDao.save(em);
					result++;
				}catch(Exception e){
					error++;
				}
			}
			resultmap.put("success",result);
			resultmap.put("error", error);
		}else if(list.size()==3){
			resultmap.put("success",result);
			resultmap.put("error", error);
			resultmap.put("returncode", "warn");
		}else if(list.size()<3){
			resultmap.put("success",result);
			resultmap.put("error", error);
			resultmap.put("returncode", "ext");
		}
		if(Integer.valueOf(resultmap.get("success").toString())==0){
			resultmap.put("returncode", "warn");
		}
		return resultmap;

	}
	
	@Override
	public long CardIDBycardNo(String cardNo) {
		// TODO Auto-generated method stub
		TS_Card card= cardDaoImpl.findCardByCardNO(cardNo);
		if(card!=null){
			return card.getID();
		}
		return 0;
	}

	@Override
	public Map<String, Object> dataGridAJ(Map<String, Object> mapData) {
		List<Map<String, Object>> list=bisYgxxDao.dataGridAJ(mapData);
		int getTotalCount=bisYgxxDao.getTotalCountAJ(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public String findYgxxList(String idcard ,String qyid){
		List<Map<String, Object>> list=bisYgxxDao.findYgxxList(idcard ,qyid);
		if(list.size()<=0){
			return "true";
		}
		return "false";
	}

	@Override
	public List<Map<String, Object>> findYgnmList(String idcard, String qyid) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=bisYgxxDao.findYgxxList(idcard ,qyid);
		return list;
	}

	@Override
	public Map<String, Object> findQyYgInfoByID(Long id) {
		// TODO Auto-generated method stub
		return bisYgxxDao.findQyYgInfoByID(id);
	}

	@Override
	public BIS_EmployeeEntity findInfoByUserID(Long userid) {
		// TODO Auto-generated method stub
		return bisYgxxDao.findInfoByUserID(userid);
	}
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(),salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

	@Override
	public Map<String, Object> getExportWord(long id) {
		Map<String, Object> map = new HashMap<>();
		//基本信息
		Map<String, Object> mapret = bisYgxxDao.findAllByID(id);
		mapret.put("m10", mapret.get("m10")==null||"".equals(mapret.get("m10").toString())?"":mapret.get("m10").toString().substring(0, 10));
		mapret.put("m14", mapret.get("m14")==null||"".equals(mapret.get("m14").toString())?"":mapret.get("m14").toString().substring(0, 10));

		//体检信息
		if (mapret.get("m8")!=null || !"".equals(mapret.get("m8").toString())) {
			map.put("sfz", mapret.get("m8"));
		}
		List<Map<String,Object>> tjlist=bisYgtjxxDao.getAllInfo(map);
		for (Map<String,Object> tj : tjlist) {
			//截取体检时间到"年-月-日"
			String m3 = tj.get("m3").toString().substring(0, 10);
			tj.put("m3", m3);
		}

		//学习记录
		map.clear();
		if (mapret.get("id")!=null || !"".equals(mapret.get("id").toString())) {
			map.put("ygid", mapret.get("id"));
		}
		List<Map<String, Object>> xxlist=aqpxxxdao.getExport(map);

		//考试记录
		List<Map<String, Object>> kslist=aqpxkscjdao.getExport(map);

		//三级教育培训信息
		AQPX_ThreeLevelEducationHistoryEntity sjjy = aqpxSjjyHistoryDao.findAllById2(id);

		mapret.put("tjlist",tjlist);
		mapret.put("xxlist",xxlist);
		mapret.put("kslist",kslist);
		mapret.put("sjjy",sjjy);

		return mapret;

	}


	@Override
	public void removeBind(long id) {
		bisYgxxDao.removeBind(id);
	}

	@Override
	public void connectUserAccount(long id, long userid) {
		bisYgxxDao.connectUserAccount(id,userid);
	}

	@Override
	public int getBindCount(long userid) {
		int count=bisYgxxDao.getBindCount(userid);
		return count;
	}
	
	@Override
	public Map<String, Object> getExportWord2(long ygid) {
		BIS_EmployeeEntity ygxx=bisYgxxDao.find(ygid);
		//劳动者基本情况
		Map<String, Object> mapret = bisYgxxDao.findBaseInfor(ygid);
		//截取体检时间到"年-月-日"
		if(mapret.get("birth")!=null){
			String birth = mapret.get("birth").toString().substring(0, 10);	
			mapret.put("birth", birth);
		}else{
			mapret.put("birth", "");
		}
		
		//职业病危害接触史
		String occuharmHis=bisOccupharmDao.findOccuharmHis(ygid);
		
		//岗前体检信息
		List<Map<String, Object>> gqlist=bisYgtjxxDao.findTestInfor("岗前", ygxx.getM8());
		for (Map<String,Object> tj : gqlist) {
			//截取体检时间到"年-月-日"
			String jcrq="";
			if(tj.get("jcrq")!=null){
				jcrq = tj.get("jcrq").toString().substring(0, 10);
			}
			tj.put("jcrq", jcrq);
		}
		
		//岗中体检信息
		List<Map<String, Object>> gzlist=bisYgtjxxDao.findTestInfor("岗中", ygxx.getM8());
		for (Map<String,Object> tj : gzlist) {
			//截取体检时间到"年-月-日"
			String jcrq="";
			if(tj.get("jcrq")!=null){
				jcrq = tj.get("jcrq").toString().substring(0, 10);
			}
			tj.put("jcrq", jcrq);
		}
		
		//离岗体检信息
		List<Map<String, Object>> lglist=bisYgtjxxDao.findTestInfor("离岗", ygxx.getM8());
		for (Map<String,Object> tj : lglist) {
			//截取体检时间到"年-月-日"
			String jcrq="";
			if(tj.get("jcrq")!=null){
				jcrq = tj.get("jcrq").toString().substring(0, 10);
			}
			tj.put("jcrq", jcrq);
		}
		
		//作业场所职业病危害因素检测情况表
		List<Map<String, Object>> qklist=bisYgtjxxDao.findJcqk(occuharmHis, ygxx.getID3()+"");
		for (Map<String,Object> tj : qklist) {
			//截取体检时间到"年-月-日"
			String jcrq="";
			if(tj.get("jcrq")!=null){
				jcrq = tj.get("jcrq").toString().substring(0, 10);
			}
			tj.put("jcrq", jcrq);
		} 
		
		mapret.put("occuharmHis",occuharmHis);//职业病危害接触史
		mapret.put("gqlist",gqlist);//岗前体检信息
		mapret.put("gzlist",gzlist);//岗中体检信息
		mapret.put("lglist",lglist);//离岗体检信息
		mapret.put("qklist",qklist);//作业场所职业病危害因素检测情况

		return mapret;

	}

	@Override
	public void save(BIS_EmployeeEntity yg) {
		bisYgxxDao.save(yg);
	}

	@Override
	public boolean checkSameEwm(long id, String ewm,long qyid) {
		return bisYgxxDao.checkSameEwm(id,ewm,qyid);
	}

	/**
	 * 获取员工数量
	 * @param mapData
	 * @return
	 */
	public int getYgCount(Map<String, Object> mapData) {
		int getTotalCount = bisYgxxDao.getTotalCount(mapData);
		return getTotalCount;
	}
}
