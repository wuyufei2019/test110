package com.cczu.model.service.impl;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IBisOccupharmDao;
import com.cczu.model.dao.IBisYgxxDao;
import com.cczu.model.dao.ITdicZybflDao;
import com.cczu.model.dao.impl.BisYgxxDaoImpl;
import com.cczu.model.entity.BIS_ConfinedSpaceEntity;
import com.cczu.model.entity.BIS_EmployeeEntity;
import com.cczu.model.entity.BIS_OccupharmExamEntity;
import com.cczu.model.service.IBisOccupharmService;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.dao.DepartmentDao;
import com.cczu.sys.system.entity.Department;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.utils.UserUtil;

@Transactional(readOnly=true)
@Service("BisOccupharmService")
public class BisOccupharmServiceImpl implements IBisOccupharmService {
	
	@Resource
	private IBisOccupharmDao biszybDao;
	@Resource
	private ITdicZybflDao tdiczybfldao;
	@Resource
	private BisYgxxDaoImpl bisYgxxDaoImpl;
	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<BIS_OccupharmExamEntity> list = biszybDao.dataGrid(mapData);
		
		for (BIS_OccupharmExamEntity bis : list) {
			String m9 = bis.getM9();
			//按逗号分隔带有员工id的字符串，根据每个id得到员工姓名，将得到的结果进行拼接，再封装到实体中
			bis.setM9(getEmployeeName(m9));
		}
		
		int getTotalCount = biszybDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public Map<String, Object> ajdataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list = biszybDao.ajdataGrid(mapData);
		int getTotalCount = biszybDao.ajgetTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	
	@Override
	public List<BIS_OccupharmExamEntity> findAll(Long qyid) {
		return biszybDao.findAll(qyid);
	}

	@Override
	public void addInfo(BIS_OccupharmExamEntity bis) {
		biszybDao.addInfo(bis);
	}

	@Override
	public BIS_OccupharmExamEntity findById(Long id) {
		BIS_OccupharmExamEntity bis = biszybDao.findById(id);
		if(StringUtils.isNotBlank(bis.getM9()))
			bis.setM9(bis.getM9() + "||" + getEmployeeName(bis.getM9()));
		return bis;
	}

	@Override
	public void updateInfo(BIS_OccupharmExamEntity bis) {
		biszybDao.updateInfo(bis);
	}

	@Override
	public void deleteInfo(Long id) {
		biszybDao.deleteInfo(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="职业病危害因素信息表.xls"; 
		List<Map<String, Object>> list=biszybDao.getExport(mapData);
		for (Map<String, Object> map : list) {
			String m9 = (String)map.get("m9");
			map.put("m9", getEmployeeName(m9));
		}
		if("1".equals(mapData.get("usertype").toString())){
			String[] title={"职业病危害类别","职业病危害名称","危害程度","可能导致的职业病","存在部门","存在岗位","接触人数","接触名单","备注"};  
			String[] keys={"m1","m2","m3","m5","m6","m7","m8","m9","m4"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			String[] title={"企业","职业病危害类别","职业病危害名称","危害程度","可能导致的职业病","存在部门","存在岗位","接触人数","接触名单","备注"};  
			String[] keys={"qynm","m1","m2","m3","m5","m6","m7","m8","m9","m4"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = ("企业名称,"+mapData.get("coltext").toString()).split(",") ;
				keys = ("qynm,"+mapData.get("colval").toString()).split(",") ;
			}
			new ExportExcel(fileName, title, keys, list, response, true);
		}
	}

	@Override
	public BIS_OccupharmExamEntity findById2(Long id) {
		BIS_OccupharmExamEntity bis = biszybDao.findById2(id);
		String m9 = bis.getM9();
		bis.setM9(getEmployeeName(m9));
		return bis;
	}

	@Override
	public Map<String, Object> statistics(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String,Object> remap = new HashMap<String,Object>();
		List<Integer> list = new ArrayList<>();
		remap.put("whys", biszybDao.statistics(map));
		for(int i=1;i<4;i++){
			map.put("m3", i+"");
			list.add(biszybDao.ajgetTotalCount(map));
			map.remove("m3");
		}
		remap.put("yzcd", list);
		return remap;
	}
	
	@Override
	public List<Map<String, Object>> getJcmd(Map<String, Object> map) {
		
		return  bisYgxxDaoImpl.findInfoByQyID(map);
	}
	
	@Override
	public Map<String, Object> getJcmdDatagrid(Map<String, Object> map) {
		List<Map<String, Object>> list = bisYgxxDaoImpl.findInfoByQyID(map);
		int total = bisYgxxDaoImpl.getTotalCountByQyid(map);
		map.put("rows", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 按逗号分割带有员工id的字符串，根据每个id获取相应的员工名称，使用逗号拼接员工名称
	 * 
	 * @param m9  带有员工id和逗号的字符串   (id,id1,id2...)
	 * 
	 * @return 使用逗号拼接员工名称的字符串  (小红, 小明， 小军...)
	 */
	public String getEmployeeName(String m9) {
		String m9str = "";
		if (StringUtils.isNotBlank(m9)) {
			String[] arr_m9 = m9.split(",");
			if (arr_m9.length > 1) {
				for (int i = 0; i < arr_m9.length; i++) {
					BIS_EmployeeEntity entity = bisYgxxDaoImpl.find(Long.parseLong(arr_m9[i]));
					if (i != (arr_m9.length-1)) {
						m9str += (entity.getM1()+",");
					} else {
						m9str += entity.getM1();
					}
				}
			} else if (arr_m9.length == 1) {
				BIS_EmployeeEntity entity = bisYgxxDaoImpl.find(Long.parseLong(arr_m9[0]));
				m9str += entity.getM1();
			}
		}
		return m9str;
    }
	/**
	 * 导入
	 */
	@Override
	public Map<String, Object> exinExcel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		
				//查找所有部门
				Map<String ,Object> map2=new HashMap<>();
				map2.put("id2", UserUtil.getCurrentShiroUser().getQyid());
				List<Department> deplist=departmentDao.findJson(map2);
		
		
				Map<String,Object> resultmap = new HashMap<String, Object>();
				int result = 0;
				ExinExcel exin = new ExinExcel();
				List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
				int i = 0, error = 0;
				if (list.size() > 3) {
					result = 0;
					resultmap.put("total", list.size()-3);
					resultmap.put("returncode", "success");
					for (List<Object> bis : list) {
						if(i<=2){ //跳过前三行
							i++;
							continue;
						}
						try{
							BIS_OccupharmExamEntity oc = new BIS_OccupharmExamEntity();
							Timestamp t = DateUtils.getSysTimestamp();
							oc.setS1(t);
							oc.setS2(t);
							oc.setS3(0);
							oc.setID1(UserUtil.getCurrentShiroUser().getQyid());//当前用户的企业id
							oc.setM1(bis.get(0).toString());//职业病危害因素类别
							oc.setM2(bis.get(1).toString());//职业病危害因素名称
							if(bis.get(2)!=null&&bis.get(2).toString()!=""){//危害程度
								String m3=bis.get(2).toString();
								switch (m3) {
									case "轻度危险性":oc.setM3("1");break;
									case "一般危险性":oc.setM3("2");break;
									case "严重危险性":oc.setM3("3");break;
									default:
										break;
								}
							}
							for (Department dep : deplist) {
								String depname=bis.get(3).toString();//模版里的部门名称
								if(depname.equals(dep.getM1())){
									oc.setM6(bis.get(3).toString());//存在部门
									break;
								}
							}
							oc.setM7(bis.get(4).toString());//存在岗位
							oc.setM4(bis.get(5).toString());//备注
							biszybDao.addInfo(oc);
							result++;
						}catch(Exception e){
							error++;
						}
						resultmap.put("success",result);
						resultmap.put("error", error);
					}
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
	
}
