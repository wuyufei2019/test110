package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.AqzfJcjhQyDao;
import com.cczu.model.dao.AqzfJcjlDao;
import com.cczu.model.dao.AqzfJcnrDao;
import com.cczu.model.dao.AqzfSetBasicInfoDao;
import com.cczu.model.dao.AqzfSetNumberDao;
import com.cczu.model.dao.AqzfWfxwDao;
import com.cczu.model.dao.AqzfZfryDao;
import com.cczu.model.dao.impl.AqzfJcbkDaoImpl;
import com.cczu.model.dao.impl.AqzfJcdyDaoImpl;
import com.cczu.model.entity.AQZF_Plan_EnterpriseEntity;
import com.cczu.model.entity.AQZF_SafetyCheckContentEntity;
import com.cczu.model.entity.AQZF_SafetyCheckItemEntity;
import com.cczu.model.entity.AQZF_SafetyCheckRecordEntity;
import com.cczu.model.entity.AQZF_SafetyCheckUnitEntity;
import com.cczu.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.model.entity.AQZF_SetNumberEntity;
import com.cczu.model.entity.AQZF_TipstaffEntity;
import com.cczu.model.entity.AQZF_WfxwEntity;
import com.cczu.model.entity.dto.Tree_CheckContent;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;

/**
 *  安全执法_检查记录Service
 *
 */
@Transactional(readOnly=true)
@Service("AqzfJcjlService")
public class AqzfJcjlService {

	@Resource
	private AqzfSetNumberDao aqzfSetNumberDao;
	@Resource
	private AqzfJcjlDao aqzfJcjlDao;
	@Resource
	private AqzfJcnrDao aqzfJcnrDao;
	@Resource
	private AqzfJcbkDaoImpl aqzfJcbkDao; 
	@Resource
	private AqzfJcdyDaoImpl aqzfJcdyDao;
	@Resource
	private AqzfJcjhQyDao aqzfJcjhQyDao;
	@Resource
	private AqzfZfryDao aqzfZfryDao;
	@Resource
	private AqzfWfxwDao aqzfWfxwDao;
	@Resource
	private AqzfSetBasicInfoDao setbasicdao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=aqzfJcjlDao.dataGrid(mapData);
		int getTotalCount=aqzfJcjlDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 根据检查内容的id去检查表库查询list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridNr(String id) {
		
		List<Map<String,Object>> list=aqzfJcjlDao.dataGridNr(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 根据检查内容的id去检查表库查询存在问题list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridCzwt(Long id) {
		
		List<Map<String,Object>> list=aqzfJcjlDao.dataGridCzwt(id);
		return list;
	}
	
	/**
	 * 根据检查内容的id去检查表库查询list
	 * @param mapData
	 * @return
	 */
	public String getNr(String id) {
		
		List<Map<String,Object>> list=aqzfJcjlDao.dataGridNr2(id);
		String nr="";
		int i=1;
		for (Map<String, Object> m : list)
	    {
			if(m.get("jcwt")!=null&&m.get("jcwt").toString()!=""){
				nr=i+":"+m.get("jcwt").toString();
				i++;
			}
	    }
		return nr;
	}

	/**
	 * 根据检查记录的id查询检查问题
	 * @param mapData
	 * @return
	 */
	public String findJcwt(String id) {
		int i=1;
		List<Map<String,Object>> list=aqzfJcjlDao.findJcwt(id);
		String nr="";
		for (Map<String, Object> m : list)
	    {
			if((m.get("jcwt"))!=null&&!(m.get("jcwt").toString()).equals("")){
				System.out.println(m.get("jcwt"));
				nr=nr+i+":"+m.get("jcwt").toString()+" ";
				i++;
			}
	    }
		return nr;
	}

	/**
	 * 根据检查内容的id去检查表库查询list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridNr2(String id) {
		
		List<Map<String,Object>> list=aqzfJcjlDao.dataGridNr2(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		return map;
	}
	
	//添加信息
	public void addInfo(AQZF_SafetyCheckRecordEntity jcjl) {
		//修改检查方案状态，检查方案index页面将不会出现添加方案按钮
		AQZF_Plan_EnterpriseEntity a = aqzfJcjhQyDao.find(jcjl.getID1());
		a.setM1("1");
		aqzfJcjhQyDao.save(a);

		//修改文书编号
		AQZF_SetNumberEntity ws = aqzfSetNumberDao.findInfor();
		int fanum=ws.getM2()+1;//编号加1
		ws.setM2(fanum);
		aqzfSetNumberDao.save(ws);		
		
		//添加检查记录
		Timestamp t=DateUtils.getSysTimestamp();
		jcjl.setS1(t);
		jcjl.setS2(t);
		jcjl.setS3(0);
		aqzfJcjlDao.save(jcjl);
	}

	/**
	 * 根据计划id找到计划关系类
	 * @param jcfa
	 */
	public AQZF_Plan_EnterpriseEntity findPlan(Long id) {
		//获取中间表字段并修改操作状态
		AQZF_Plan_EnterpriseEntity a = aqzfJcjhQyDao.find(id);
		return a;

	}
	
	public long addInfoReturnID(AQZF_SafetyCheckRecordEntity jcjl) {
		Timestamp t=DateUtils.getSysTimestamp();
		jcjl.setS1(t);
		jcjl.setS2(t);
		jcjl.setS3(0);
		jcjl.setM12("0");
		jcjl.setM13("0");
		jcjl.setM14("0");
		aqzfJcjlDao.save(jcjl);
		return jcjl.getID();
	}
	
	//更新信息
	public void updateInfo(AQZF_SafetyCheckRecordEntity jcjl) {
		Timestamp t=DateUtils.getSysTimestamp();
		jcjl.setS2(t);
		jcjl.setS3(0);
		aqzfJcjlDao.save(jcjl);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		aqzfJcjlDao.deleteInfo(id);
	}

	
	//修改检查方案检查状态1
	public void updateState(Long id) {
		aqzfJcjlDao.updateState(id);
	}
	
	//修改检查方案检查状态0
	public void updatefa(Long id) {
		aqzfJcjlDao.updatefa(id);
	}
	
	//根据id查找详细信息
	public Map<String, Object> findById(Long id) {
		return aqzfJcjlDao.findById(id);
	}
	
	/*
	 * 获取检查内容树形图所需要的数据
	 */
	public List<Tree_CheckContent> getAllTreeList( ) {
		List<Tree_CheckContent> treelist=new ArrayList<Tree_CheckContent>();
		//检查单元list
		List<AQZF_SafetyCheckUnitEntity> dylist=aqzfJcjlDao.findDy();
		
		for(AQZF_SafetyCheckUnitEntity dy : dylist){
			Tree_CheckContent ny=new Tree_CheckContent();
			ny.setText(dy.getM1());
			ny.setPtext("");
			treelist.add(ny);
			//根据检查单元查询检查项
			List<AQZF_SafetyCheckItemEntity> list=aqzfJcbkDao.findJcx(dy.getID());
			for(AQZF_SafetyCheckItemEntity jcx:list){
				Tree_CheckContent ny2=new Tree_CheckContent();
				ny2.setId(jcx.getID());
				ny2.setText(jcx.getM2());
				ny2.setPtext(dy.getM1());
				treelist.add(ny2);
			}
		}
		System.out.println(JsonMapper.toJsonString(treelist));
	
		List<Tree_CheckContent> nodeList = new ArrayList<Tree_CheckContent>();  
		for(Tree_CheckContent dto1 : treelist){  
		    boolean mark = false;  
		    for(Tree_CheckContent dto2 : treelist){  
		        if(dto1.getPtext()==dto2.getText()){  
		            mark = true;  
		            if(dto2.getChildren() == null)  
		            	dto2.setChildren(new ArrayList<Tree_CheckContent>());  
		            dto2.getChildren().add(dto1);   
		            break;  
		        }  
		    }  
		    if(!mark){  
		        nodeList.add(dto1);   
		    }  
		}  
		System.out.println(JsonMapper.toJsonString(nodeList));
		return nodeList;
	}

	//根据id获得现场检查记录word表数据
		public Map<String, Object> getAjWord(long id){
			Map<String, Object> mapret = aqzfJcjlDao.findInfo(id);
			Map<String, Object> map=new HashMap<String, Object>();
			//检查起始时间解析
			if(mapret.get("m6")!=null&&!mapret.get("m6").toString().equals("")){
				String a = mapret.get("m6").toString();
				String[] as1 = a.substring(0,10).split("-");
				map.put("year", as1[0]);
				map.put("month1", as1[1]);
				map.put("day1", as1[2]);
				String[] bs1 = a.substring(11,16).split(":");
				map.put("hour1", bs1[0]);
				map.put("min1", bs1[1]);
			}else{
				map.put("year", "    ");
				map.put("month1", "  ");
				map.put("day1", "  ");
				map.put("hour1", "  ");
				map.put("min1", "  ");
			}
			//检查结束时间解析
			if(mapret.get("m7")!=null&&!mapret.get("m7").toString().equals("")){
				String b = mapret.get("m7").toString();
				String[] as2 = b.substring(0,10).split("-");
				map.put("month2", as2[1]);
				map.put("day2", as2[2]);
				String[] bs2 = b.substring(11,16).split(":");
				map.put("hour2", bs2[0]);
				map.put("min2", bs2[1]);
			}else{
				map.put("month2", "  ");
				map.put("day2", "  ");
				map.put("hour2", "  ");
				map.put("min2", "  ");
			}
			//检查人员解析
			if(mapret.get("m8")!=null&&!mapret.get("m8").toString().equals("")){
				
				String c = mapret.get("m8").toString();
				String[] as3 = c.split(",");
				//证件号
				String zjh = "";
				String m8= "";
				for(int i=0;i<as3.length;i++){
					m8 = m8 + as3[i] + "、";
					AQZF_TipstaffEntity a = aqzfZfryDao.findByM1(as3[i]);
		            zjh = zjh + a.getM3() + "、";		
				}
				zjh=zjh.substring(0,zjh.length()-1);
				m8=m8.substring(0,m8.length()-1);
				map.put("m8", m8);
			    map.put("zjh", zjh);
			}else{
				map.put("m8", "           ");
			    map.put("zjh", "                 ");
			}
			//检查情况
			String jcqk = "";
			int i = 1;
			List<AQZF_SafetyCheckContentEntity> list = aqzfJcnrDao.findByJlid(id);
			for (AQZF_SafetyCheckContentEntity scc : list) {
				AQZF_WfxwEntity wfxw = new AQZF_WfxwEntity();
				try {
					//根据id查找违法行为
					wfxw = aqzfWfxwDao.findInfoById(Long.parseLong(scc.getM2()));
					jcqk = jcqk+ i+". "+wfxw.getM1()+" ";
					
				}catch (Exception e) {
					jcqk += i+". "+scc.getM2()+" ";
				}
				i++;
			}
			map.put("jcqk", jcqk);
			
			//设置省市区名称
			AQZF_SetBasicInfoEntity asb = setbasicdao.findInfor();
			map.put("ssqmc", asb.getSsqmc());
			
			map.put("m0", mapret.get("m0")==null||mapret.get("m0").toString().equals("")?"":mapret.get("m0").toString());
			map.put("qyname", mapret.get("qyname")==null||mapret.get("qyname").toString().equals("")?"":mapret.get("qyname").toString());
			map.put("m1", mapret.get("m1")==null||mapret.get("m1").toString().equals("")?"":mapret.get("m1").toString());
			map.put("m2", mapret.get("m2")==null||mapret.get("m2").toString().equals("")?"      ":mapret.get("m2").toString());
			map.put("m3", mapret.get("m3")==null||mapret.get("m3").toString().equals("")?"      ":mapret.get("m3").toString());
			map.put("m4", mapret.get("m4")==null||mapret.get("m4").toString().equals("")?"          ":mapret.get("m4").toString());
			map.put("m5", mapret.get("m5")==null||mapret.get("m5").toString().equals("")?"":mapret.get("m5").toString());
			return map;
		}
		
	//根据计划id查找方案里的内容id
	public Map<String,Object> findNrid(Long id) {
		return aqzfJcjlDao.findNrid(id);
	}
	
	/**
	 * 根据计划id找到计划关系类
	 * @param jcfa
	 */
	public AQZF_SafetyCheckRecordEntity findjl(Long id) {
		//获取中间表字段并修改操作状态
		AQZF_SafetyCheckRecordEntity a = aqzfJcjlDao.find(id);
		return a;

	}
}
