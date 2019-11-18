package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.cczu.model.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.impl.BisAqpxxxDaoImpl;
import com.cczu.model.dao.impl.BisCgxxDaoImpl;
import com.cczu.model.dao.impl.BisCjxxDaoImpl;
import com.cczu.model.dao.impl.BisCkxxDaoImpl;
import com.cczu.model.dao.impl.BisGwgyDaoImpl;
import com.cczu.model.dao.impl.BisHazardDaoImpl;
import com.cczu.model.dao.impl.BisOccupIllexamDaoImpl;
import com.cczu.model.dao.impl.BisOccupharmDaoImpl;
import com.cczu.model.dao.impl.BisOccupharmreportDaoImpl;
import com.cczu.model.dao.impl.BisQyjbxxDaoImpl;
import com.cczu.model.dao.impl.BisTzsbxxDaoImpl;
import com.cczu.model.dao.impl.BisWlxxDaoImpl;
import com.cczu.model.dao.impl.BisYgxxDaoImpl;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.service.IBisQyjbxxService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.comm.utils.security.Digests;
import com.cczu.sys.comm.utils.security.Encodes;
import com.cczu.sys.system.dao.RoleDao;
import com.cczu.sys.system.dao.UserDao;
import com.cczu.sys.system.dao.UserRoleDao;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.entity.UserRole;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;


@Transactional(readOnly = true)
@Service("BisQyjbxxService")
public class BisQyjbxxServiceImpl implements IBisQyjbxxService {
	
	@Autowired
	private IBisQyjbxxDao bisQyjbxx;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Resource
	private FxgkFxxxDao fxgkFxxxDao;
	@Resource
	private IBisHazardDao bishazardao;
	@Resource
	private YhpcYhpcjlDao yhpcYhpcjlDao;

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {

		List<Map<String,Object>> list = bisQyjbxx.dataGrid(mapData);
		int getTotalCount = bisQyjbxx.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public Map<String, Object> dataGridForApp(Map<String, Object> mapData) {

		List<Map<String,Object>> list = bisQyjbxx.dataGridForApp(mapData);
		int getTotalCount = bisQyjbxx.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public BIS_EnterpriseEntity findInfoById(long id) {
		return bisQyjbxx.findInfoById(id);
	}

	@Override
	public String addInfo(BIS_EnterpriseEntity bis) {
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Timestamp t = DateUtils.getSysTimestamp();
		bis.setS1(t);
		bis.setS2(t);
		bis.setS3(0);
		long l = bisQyjbxx.addInfore(bis);

		if (l > 0) {
			//如果是企业用户自己添加企业信息，只需修改企业用户的id2字段
			if(sessionuser.getUsertype().equals("1")){
				sessionuser.qyid = l;
				User user = userDao.find(sessionuser.getId());
				user.setId2(l);
				userDao.saveUp(user);
			}else{//如果是非企业用户添加企业信息，则要创建企业账户
				User tempUser=userDao.findUniqueBy("loginName", bis.getM1());
				if(tempUser!=null){//判断用户名是否存在
					tempUser.setId2(l);
					userDao.saveUp(tempUser);
				}else{//不存在则新增用户
					User user=new User(bis.getM1(), bis.getM1(), "");
					byte[] salt = Digests.generateSalt(8);
					user.setSalt(Encodes.encodeHex(salt));
					byte[] hashPassword = Digests.sha1("000000".getBytes(),salt, 1024);
					user.setPassword(Encodes.encodeHex(hashPassword));
					user.setUsertype("1");
					user.setGender((short)1);
					user.setId2(l);
					user.setXzqy(bis.getID2());
					userDao.save(user);
					
					//设置改用户为企业管理员
					Role role= roleDao.findRoleByRoleCode("companyadmin");
					UserRole ur = new UserRole(user, role);
					userRoleDao.save(ur);
				}
			}
			return "success";
		} else {
			return "error";
		}

	}

	@Override
	public void updateInfo(BIS_EnterpriseEntity bis) {
		Timestamp t = DateUtils.getSysTimestamp();
		bis.setS2(t);
		bis.setS3(0);
		bisQyjbxx.updateInfo(bis);
		
		//如果是安监或系统管理员修改企业信息，将该企业的所有用户的网格区域更新
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("0")||sessionuser.getUsertype().equals("9")){
			userDao.updateWGQY(bis.getID(), bis.getID2());
		}
	}

	@Override
	public void deleteInfo(long id) {
		bisQyjbxx.deleteInfo(id);
	}

	@Override
	public BIS_EnterpriseEntity findInfoByUserId(long userid) {
		return bisQyjbxx.findInfoByUserId(userid);
	}

	@Override
	public List<BIS_EnterpriseEntity> findAll() {
		return bisQyjbxx.findAlllist();
	}

	@Override
	public Map<String, Object> findAllQyList(Map<String, Object> map) {
		List<BIS_EnterpriseEntity> list = bisQyjbxx.findAlllist(map);
		Map<String, Object> mapr = new HashMap<String, Object>();
		mapr.put("rows", list);
		return mapr;
	}
	
	@Override
	public List<BIS_EnterpriseEntity> findAllByUserId(long userid) {
		return bisQyjbxx.findAllByUserId(userid);
	}

	@Override
	public List<Map<String, Object>> findAllQyAddressList() {
		return bisQyjbxx.findAllEFlist();
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> map) {
		List<Map<String, Object>> list = bisQyjbxx.dataObjectList(map);
		String fileName = "企业基本信息.xls";
		String[] title = { "企业名称", "母公司名称", "集团公司名称", "统一社会信用代码", "营业执照类别", "法定代表人", "联系人", "联系微信号", "联系QQ号", "联系电话",
				"电子邮件", "注册地址", "注册资金(万元)", "年销售收入(万元)", "年利润(万元)", "资产总额（万元）", "占地面积", "属地安监机构", "邮政编码", "经济类型代码",
				"行政区域(省)", "行政区域(市)", "行政区域(区)", "行政区域(镇或街道)", "行业类别", "企业行政隶属关系", "经营范围", "企业状态", "企业位置经度", "企业位置纬度", "标准化达标等级",
				"主要负责人", "主要负责人固定电话", "主要负责人移动电话", "主要负责人电子邮箱", "安全负责人", "安全负责人固定电话", "安全负责人移动电话", "安全负责人电子邮箱",
				"安全机构设置情况", "是否位于化工集中区", "化工集中区名称", "是否涉及重点监管危化品", "是否涉及高危工艺", "是否涉及剧毒品生产或使用", "从业人员数量", "特种作业人员数量",
				"专职安全生产管理人员数", "兼职安全生产管理人员数量", "专职应急管理人员数", "注册安全工程师人员数", "生产/经营地址", "企业官方网站地址", "单位传真", "企业平面图",
				"规模情况", "企业规模", "监管分类", "安全监管等级", "隐患排查治理制度", "隐患排查治理计划", "是否构成重大危险源", "重大危险源等级", "物料风险等级", "工艺风险等级",
				"物料储量风险等级", "企业风险等级", "备注" , "所属网格" };
		String[] keys = { "M1", "M1_1", "M1_2", "M3_1", "M2_1", "M5", "M6_1", "M6_2", "M6_3", "M6", "M7", "M8", "M8_1",
				"M8_2", "M8_3", "M8_4", "M8_5", "M8_6", "M9", "M10", "M11", "M11_1", "M11_2", "M11_3", "M12", "M13", "M14",
				"M15", "M16", "M17", "M18", "M19", "M20", "M21", "M22", "M23", "M24", "M25", "M26", "M27", "M46",
				"M46_1", "M47", "M48", "M49", "M28", "M29", "M30", "M30_1", "M31", "M32", "M33", "M33_1", "M33_2",
				"M33_3", "M34", "M35", "M36", "M36_1", "M37", "M38", "M39", "M40", "M41", "M42", "M43", "M44", "M45", "wgname"};
		if (StringUtils.isNotEmpty(map.get("colval").toString())) {
			title = map.get("coltext").toString().split(",");
			keys = map.get("colval").toString().split(",");
		}
		new ExportExcel(fileName, title, keys, list, response);
	}

	@Override
	public void exinExcel(Map<String, Object> map) {
		ExinExcel exin = new ExinExcel();
		List<List<Object>> list = exin.exinExcel(map.get("path").toString());
		int i = 0 ;
		if (list.size() > 0) {
			for (List<Object> bis : list) {
				if (i++ == 0)
					continue;
				BIS_EnterpriseEntity entity = new BIS_EnterpriseEntity();
				Timestamp t=DateUtils.getSysTimestamp();
				entity.setS1(t);
				entity.setS2(t);
				entity.setS3(0);
				entity.setM1(bis.get(0).toString());
				entity.setM1_1(bis.get(1).toString());
				entity.setM1_2(bis.get(2).toString());
				entity.setM3_1(bis.get(3).toString());
				entity.setM2_1(bis.get(4).toString());
				entity.setM5(bis.get(5).toString());
				entity.setM6_1(bis.get(6).toString());
				entity.setM6_2(bis.get(7).toString());
				entity.setM6_3(bis.get(8).toString());
				entity.setM6(bis.get(9).toString());
				entity.setM7(bis.get(9).toString());
				entity.setM8(bis.get(9).toString());
				entity.setM8_1(bis.get(9).toString());
				entity.setM8_2(bis.get(9).toString());
				entity.setM8_3(bis.get(9).toString());
				entity.setM8_4(bis.get(9).toString());
				entity.setM8_5(bis.get(9).toString());
				entity.setM8_6(bis.get(9).toString());
				entity.setM9(bis.get(9).toString());
				entity.setM10(bis.get(9).toString());
				entity.setM11(bis.get(9).toString());
				entity.setM11_1(bis.get(9).toString());
				entity.setM11_2(bis.get(9).toString());
				entity.setM12(bis.get(9).toString());
				entity.setM13(bis.get(9).toString());
				entity.setM14(bis.get(9).toString());
				entity.setM15(bis.get(9).toString());
				entity.setM16(bis.get(9).toString());
				entity.setM17(bis.get(9).toString());
				entity.setM18(bis.get(9).toString());
				entity.setM19(bis.get(9).toString());
				entity.setM20(bis.get(9).toString());
				entity.setM21(bis.get(9).toString());
				entity.setM22(bis.get(9).toString());
				entity.setM23(bis.get(9).toString());
				entity.setM24(bis.get(9).toString());
				entity.setM25(bis.get(9).toString());
				entity.setM26(bis.get(9).toString());
				entity.setM27(bis.get(9).toString());
				entity.setM46(bis.get(9).toString());
				entity.setM46_1(bis.get(9).toString());
				entity.setM24(bis.get(9).toString());
				
//				"", "M47", "M48", "M49", "M28", "M29", "M30", "M30_1", "M31", "M32", "M33", "M33_1", "M33_2",
//				"M33_3", "M34", "M35", "M36", "M36_1", "M37", "M38", "M39", "M40", "M41", "M42", "M43", "M44", "M45"
				bisQyjbxx.addInfore(entity);
			}
		}
	}

	@Override
	public List<Map<String, Object>> findMapList(Map<String, Object> mapdata) {
		return bisQyjbxx.findMapList(mapdata);
	}
	@Override
	public List<Map<String, Object>> findZdzdMapList(Map<String, Object> mapdata) {
		// TODO Auto-generated method stub
		return bisQyjbxx.findZdzdMapList(mapdata);
	}

	@Override
	public List<BIS_EnterpriseEntity> dataListE(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return bisQyjbxx.dataListE(map);
	}

	@Override
	public String getQyIdjson(Map<String, Object> map) {
		List<Map<String, Object>> list = bisQyjbxx.findQyIdTextList(map);
		return JsonMapper.getInstance().toJson(list);
	}

	@Override
	public String getQyTextjson(Map<String, Object> map) {
		List<Map<String, Object>> list = bisQyjbxx.findQyTextList(map);
		return JsonMapper.getInstance().toJson(list);
	}

	@Override
	public String findXYById(Map<String, Object> map) {
		Map<String, Object> mapdata = new HashMap<String, Object>();
		BIS_EnterpriseEntity be = bisQyjbxx.findInfoById(Long.valueOf(map.get("id").toString()));

		if (be != null) {
			mapdata.put("lng", be.getM16());
			mapdata.put("lat", be.getM17());
		}
		// return JsonMapper.getInstance().writeValueAsString(mapdata);
		return JsonMapper.getInstance().toJson(mapdata);
	}

	@Override
	public List<Map<String, Object>> findQynmList(String xzqy) {
		List<Map<String, Object>> qynmList = bisQyjbxx.findQynmList(xzqy);
		return qynmList;
	}
	
	@Override
	public List<Map<String, Object>> findQynmList2(String cjz) {
		List<Map<String, Object>> qynmList = bisQyjbxx.findQynmList2(cjz);
		return qynmList;
	}

	@Override
	public Map<String, Object> statistics(Map<String, Object> map) {
		Map<String, Object> statisticsData = bisQyjbxx.statistics(map);
		return statisticsData;
	}

	@Override
	public String qyxxTotalAll(Map<String, Object> map) {
		List<Map<String, Object>> list = bisQyjbxx.qyxxTotalAll(map);
		int getTotalCount = bisQyjbxx.getTotalCount(map);
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("rows", list);
		resMap.put("total", getTotalCount);
		return JsonMapper.getInstance().toJson(resMap);
	}
	@Override
	public String DsfnoDataModule(Map<String, Object> map) {
		List<Map<String, Object>> list = bisQyjbxx.qyListForDsfNoDataModule(map);
		return JsonMapper.getInstance().toJson(list);
	}

	@Override
	public void ExportQyxxTotalAll(HttpServletResponse response, Map<String, Object> map){
		List<Map<String, Object>> list = bisQyjbxx.ExportQyxxTotalAll(map);
		String fileName = "汇总统计.xls";
		String[] title = { "企业名称","所属乡镇","所属中介","车间", "仓库", "物料", "储罐", "特种设备", "安全培训", "生产设备", "公用工程", "员工",
				"职业病危险因素", "检测报告", "职业病体检", "危险工艺", "重大危险源","员工体检","安全设施","粉尘","受限空间","能源","承包商","冶金","作业班次","风险点"};
		String[] keys = { "m1","m11_3","name","cj", "ck", "wl", "cg", "tzsb", "aqpx", "scsb", "gygc", "yg", "zyb", "jcbg", "zybtj",
				"wxgy", "wxy" , "ygtj", "aqss","fc","sxkj","ny","cbs","yj","zybc","fxd"};
		new ExportExcel(fileName, title, keys, list, response);
	}

	@Override
	public List<BIS_EnterpriseEntity> findAllQy(String ids) {
		// TODO Auto-generated method stub
		List<BIS_EnterpriseEntity> list= bisQyjbxx.findAllQy(ids);
		return list;
	}

	@Override
	public Map<String, Object> FxdjForApp(Map<String, Object> tmap) {
		// TODO Auto-generated method stub
		return bisQyjbxx.FxdjForApp(tmap);
	}

	@Override
	public List<Map<String, Object>> XZFxdjForApp(Map<String, Object> tmap) {
		// TODO Auto-generated method stub
		return bisQyjbxx.XZFxdjForApp(tmap);
	}

	@Resource
	private BisQyjbxxDaoImpl bisQyjbxxDao;//企业基本信息
	@Resource
	private BisCjxxDaoImpl bisCjxxDao;//车间信息
	@Resource
	private BisWlxxDaoImpl bisWlxxDao;//物料信息
	@Resource
	private BisCkxxDaoImpl bisCkxxDao;//仓库信息
	@Resource
	private BisCgxxDaoImpl bisCgxxDao;//储罐信息
	@Resource
	private BisYjxxDao bisYjxxDao;//冶金信息
	@Resource
	private BisFcxxDao bisFcxxDao;//粉尘信息
	@Resource
	private BisSxkjDao bisSxkjDao;//受限空间
	@Resource
	private BisZybcDao bisZybcDao;//作业班次
	@Resource
	private BisTzsbxxDaoImpl bisTzsbxxDao;//特种设备信息
	@Resource
	private BisYgxxDaoImpl bisYgxxDao;//员工信息
	@Resource
	private BisAqpxxxDaoImpl bisAqpxxxDao;//安全培训信息
	@Resource
	private BisScsbDao bisScsbDao;//生产设备信息
	@Resource
	private BisGygcDao bisGygcDao;//公用工程信息
	@Resource
	private BisCbsxxDao bisCbsxxDao;//承包商信息
	@Resource
	private BisHxnyxxDao bisHxnyxxDao;//化学能源信息
	@Resource
	private BisAqssDao bisAqssDao;//化学能源信息
	@Resource
	private BisOccupharmDaoImpl bisOccupharmDao;//职业病危害因素
	@Resource
	private BisOccupharmreportDaoImpl bisOccupharmreportDao;//检测报告管理
	@Resource
	private BisOccupIllexamDaoImpl BisOccupIllexamDao;//职业病体检
	@Resource
	private BisYgtjxxDao bisYgtjxxDao;//员工体检信息
	@Resource
	private BisGwgyDaoImpl bisGwgyDao;//危险工艺
	@Resource
	private BisHazardDaoImpl bisHazardDao;//重大危险源信息信息
	
	@Override
	public Map<String, Object> getWordAll(Long qyid) {
		Map<String,Object> bis1 = bisQyjbxxDao.findByQyID(qyid);//企业基本信息
		List<Map<String,Object>> bis2 = bisCjxxDao.findByQyID2(qyid);//车间信息
		List<Map<String,Object>> bis3 = bisWlxxDao.findByQyID(qyid);//物料信息
		List<Map<String,Object>> bis4 = bisCkxxDao.findByQyID(qyid);//仓库信息
		List<Map<String,Object>> bis5 = bisCgxxDao.findByQyID(qyid);//储罐信息
		List<Map<String,Object>> bis6 = bisYjxxDao.findByQyID(qyid);//冶金信息
		List<Map<String,Object>> bis7 = bisFcxxDao.findByQyID(qyid);//粉尘信息
		List<Map<String, Object>> bis8 = bisSxkjDao.findByQyID(qyid);//受限空间
		List<Map<String, Object>> bis9 = bisZybcDao.findByQyID(qyid);//作业班次
		List<Map<String, Object>> bis10 = bisTzsbxxDao.findByQyID(qyid);//特种设备信息
		List<Map<String, Object>> bis11 = bisYgxxDao.findByQyID(qyid);//员工信息
		List<Map<String, Object>> bis12 = bisAqpxxxDao.findByQyID(qyid);//安全培训信息
		List<Map<String, Object>> bis13 = bisScsbDao.findByQyID(qyid);//生产设备信息
		List<Map<String, Object>> bis14 = bisGygcDao.findByQyID(qyid);//公用工程信息
		List<Map<String, Object>> bis15 = bisCbsxxDao.findByQyID(qyid);//承包商信息
		List<Map<String, Object>> bis16 = bisHxnyxxDao.findByQyID(qyid);//化学能源信息
		List<Map<String, Object>> bis17 = bisAqssDao.findByQyID(qyid);//安全设施
		List<Map<String, Object>> bis18 = bisOccupharmDao.findByQyID(qyid);//职业病危害因素
		List<Map<String, Object>> bis19 = bisOccupharmreportDao.findByQyID(qyid);//检测报告管理
		List<Map<String, Object>> bis20 = BisOccupIllexamDao.findByQyID(qyid);//职业病体检
		List<Map<String, Object>> bis21 = bisYgtjxxDao.findByQyID(qyid);//员工体检信息
		List<Map<String, Object>> bis22 = bisGwgyDao.findByQyID(qyid);//危险工艺
		List<Map<String, Object>> bis23 = bisHazardDao.findByQyID(qyid);//重大危险源信息信息
		Map<String,Object> map = new HashMap<>();
		map.put("bis1", bis1);
		map.put("Bis2", bis2);
		map.put("Bis3", bis3);
		map.put("Bis4", bis4);
		map.put("Bis5", bis5);
		map.put("Bis6", bis6);
		map.put("Bis7", bis7);
		map.put("Bis8", bis8);
		map.put("Bis9", bis9);
		map.put("Bis10", bis10);
		map.put("Bis11", bis11);
		map.put("Bis12", bis12);
		map.put("Bis13", bis13);
		map.put("Bis14", bis14);
		map.put("Bis15", bis15);
		map.put("Bis16", bis16);
		map.put("Bis17", bis17);
		map.put("Bis18", bis18);
		map.put("Bis19", bis19);
		map.put("Bis20", bis20);
		map.put("Bis21", bis21);
		map.put("Bis22", bis22);
		map.put("Bis23", bis23);
		return map;
	}

	@Override
	public boolean checkQyname(Map<String,Object> map){
		boolean flag=true;
		List<BIS_EnterpriseEntity>  list=bisQyjbxxDao.findAlllist(map);
		if(list!=null&&list.size()>0){
			BIS_EnterpriseEntity bis=list.get(0);
			if(map.get("qyid")!=null&&map.get("qyid").equals(bis.getID()))
				flag=true;
			else
				flag=false;
		}else{
			flag=true;
		}
		return flag;
	}

	@Override
	public void addDataForModule(Set<String> tables,long id) {
		// TODO Auto-generated method stub
		bisQyjbxxDao.addDataForModule(tables, id);
	}

	@Override
	public Map<String, Object> findQyInfoByPoint(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=bisQyjbxxDao.findQyInfoByPoint(map);
		if(list.size()>0)
			return list.get(0);
		else
			return null;
	}

	@Override
	public Map<String, Object> getTotalViewById(Long qyid) {
		// TODO Auto-generated method stub
		return bisQyjbxxDao.getTotalViewById(qyid);
	}

	@Override
	public Map<String, Object> findwcd(Long qyid) {
		// TODO Auto-generated method stub
		return bisQyjbxxDao.findwcd(qyid);
	}

	@Override
	public Map<String, Object> findsl(Long qyid) {
		// TODO Auto-generated method stub
		Map<String, Object> mapData=new HashMap<>();
		mapData.put("qyid",qyid);
		Map<String, Object> mapData2=new HashMap<>();
		Map<String, Object> mapData3=new HashMap<>();
		mapData3.put("qyid2",qyid);
		int fxdCount=fxgkFxxxDao.getTotalCount(mapData);
		int zdwxylCount = bishazardao.getTotalCount(mapData3);
		int yhpcCount=yhpcYhpcjlDao.getTotalCount(mapData);
		mapData2.put("fxdCount",fxdCount);
		mapData2.put("zdwxylCount",zdwxylCount);
		mapData2.put("yhpcCount",yhpcCount);
		return mapData2;
	}

}
