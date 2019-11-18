package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAqpxKscjDao;
import com.cczu.model.dao.impl.AqpxKscjDaoImpl;
import com.cczu.model.entity.AQPX_ExamresultsEntity;
import com.cczu.model.service.IAqpxKscjService;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.util.common.Parameter;

@Transactional(readOnly=true)
@Service("AqpxKscjService")
public class AqpxKscjServiceImpl implements IAqpxKscjService {
	
	@Resource
	private AqpxKscjDaoImpl aqpxkscjdao;

	@Override
	public void addInfo(AQPX_ExamresultsEntity ae) {
		// TODO Auto-generated method stub
		Timestamp t = DateUtils.getSystemTime();
		ae.setS1(t);
		ae.setS2(t);
		aqpxkscjdao.addInfo(ae);
	}
	
	@Override
	public long addInfoReId(AQPX_ExamresultsEntity ae) {
		// TODO Auto-generated method stub
		Timestamp t = DateUtils.getSystemTime();
		ae.setS1(t);
		ae.setS2(t);
		return aqpxkscjdao.addInfo(ae);
	}

	@Override
	public void updateInfo(AQPX_ExamresultsEntity ae) {
		// TODO Auto-generated method stub
		aqpxkscjdao.updateInfo(ae);
	}

	@Override
	public List<AQPX_ExamresultsEntity> getlist(Long ygid, Long kcid) {
		// TODO Auto-generated method stub
		return aqpxkscjdao.getlist(ygid, kcid);
	}

	@Override
	public List<AQPX_ExamresultsEntity> findid(Long ygid, String H) {
		// TODO Auto-generated method stub
		return aqpxkscjdao.findid(ygid, H);
	}

	@Override
	public List<AQPX_ExamresultsEntity> listall(Long ygid) {
		// TODO Auto-generated method stub
		return aqpxkscjdao.listall(ygid);
	}

	@Override
	public List<Map<String, Object>> listmax(Long ygid) {
		// TODO Auto-generated method stub
		return aqpxkscjdao.listmax(ygid);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=aqpxkscjdao.dataGrid(mapData);
		int getTotalCount=aqpxkscjdao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public Map<String, Object> dataGridCompanyAdmin(Map<String, Object> mapData) {
		List<Map<String, Object>> list=aqpxkscjdao.dataGridCompanyAdmin(mapData);
		int getTotalCount=aqpxkscjdao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public Map<String, Object> dataGridCompanyAdmin2(Map<String, Object> mapData) {
		List<Map<String, Object>> list=aqpxkscjdao.dataGridCompanyAdmin2(mapData);
		int getTotalCount=aqpxkscjdao.getTotalCount3(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void exportExcel(HttpServletResponse response,Map<String, Object> mapData) {
		String fileName="考试记录.xls";
		List<Map<String, Object>> list=aqpxkscjdao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response);
	}
	
	@Override
	public void deleteInfo(long id) {
		aqpxkscjdao.deleteInfo(id);
	}

	@Override
	public List<Map<String, Object>> getksjlByJH(Map<String, Object> mapData) {
		String sql="";
		Parameter parameter=null;
		if(mapData.get("type").equals("1")){//参加考试
			sql="SELECT * FROM ( SELECT a.*, ROW_NUMBER () OVER ( PARTITION BY a.id2 ORDER BY CONVERT ( NUMERIC, a.m1 )  DESC ) AS rn FROM (SELECT c.name username,a.*,b.m1 kcname from aqpx_examresults a  left join aqpx_course b  on a.id3=b.id left join t_user c on a.id2=c.id "
					+"where c.id in ( select DISTINCT(a.id2) from aqpx_examresults a where a.id4 =:p1 ) and id4=:p1 ) a ) AS t WHERE t.rn=1";
			 parameter=new Parameter(mapData.get("jhid"));
			
		}else if(mapData.get("type").equals("2")){//未参加考试
			sql="select a.id ,a.name from t_user a  left join aqpx_plan b  on ','+b.id3+',' like '%,'+CAST(a.departmen as varchar(200))+',%' "
		         +"where b.id=:p1 and a.id not in (select DISTINCT(a.id2) from aqpx_examresults a where a.id4 =:p2)  order by a.name";
			 parameter=new Parameter(mapData.get("jhid"),mapData.get("jhid"));
			List<Map<String, Object>> list= aqpxkscjdao.findBySql(sql,parameter,Map.class);
			return list;
			
		}else if(mapData.get("type").equals("3")){//考试合格
            sql="SELECT s.* FROM ( SELECT * FROM ( SELECT a.*, ROW_NUMBER () OVER ( PARTITION BY a.id2 ORDER BY CONVERT ( NUMERIC, a.m1 )  DESC ) AS rn FROM (SELECT c.name username,a.*,b.m1 kcname from aqpx_examresults a  left join aqpx_course b  on a.id3=b.id left join t_user c on a.id2=c.id "
                    +"where c.id in ( select DISTINCT(a.id2) from aqpx_examresults a where a.id4 =:p1 ) and id4=:p1 ) a ) AS t WHERE t.rn=1 ) s WHERE s.m3 ='合格'";
			 parameter=new Parameter(mapData.get("jhid"));
			List<Map<String, Object>> list= aqpxkscjdao.findBySql(sql,parameter,Map.class);
			return list;
		}else if(mapData.get("type").equals("4")){//考试不合格
            sql="SELECT s.* FROM ( SELECT * FROM ( SELECT a.*, ROW_NUMBER () OVER ( PARTITION BY a.id2 ORDER BY CONVERT ( NUMERIC, a.m1 )  DESC ) AS rn FROM (SELECT c.name username,a.*,b.m1 kcname from aqpx_examresults a  left join aqpx_course b  on a.id3=b.id left join t_user c on a.id2=c.id "
                    +"where c.id in ( select DISTINCT(a.id2) from aqpx_examresults a where a.id4 =:p1 ) and id4=:p1 ) a ) AS t WHERE t.rn=1 ) s WHERE s.m3 ='不合格'";
			 parameter=new Parameter(mapData.get("jhid"));
			List<Map<String, Object>> list= aqpxkscjdao.findBySql(sql,parameter,Map.class);
			return list;
		}
		 List<Map<String, Object>> list= aqpxkscjdao.findBySql(sql,parameter,Map.class);
		 	return list;
		
		
	}
	
}
