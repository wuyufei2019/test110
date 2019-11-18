package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.AqzfFcyjDao;
import com.cczu.model.dao.AqzfJcfaDao;
import com.cczu.model.dao.AqzfJcjhQyDao;
import com.cczu.model.dao.AqzfJcjlDao;
import com.cczu.model.dao.AqzfSetNumberDao;
import com.cczu.model.dao.AqzfZlzgDao;
import com.cczu.model.dao.IAqzfJcbkDao;
import com.cczu.model.dao.impl.AqzfJcbkDaoImpl;
import com.cczu.model.dao.impl.AqzfJcdyDaoImpl;
import com.cczu.model.entity.AQZF_Plan_EnterpriseEntity;
import com.cczu.model.entity.AQZF_SafetyCheckItemEntity;
import com.cczu.model.entity.AQZF_SafetyCheckSchemeEntity;
import com.cczu.model.entity.AQZF_SafetyCheckUnitEntity;
import com.cczu.model.entity.AQZF_SetNumberEntity;
import com.cczu.model.entity.dto.Tree_CheckContent;
import com.cczu.sys.comm.utils.DateUtils;

/**
 *  安全执法_检查方案Service
 *
 */
@Transactional(readOnly=true)
@Service("AqzfJcfaService")
public class AqzfJcfaService {

	@Resource
	private AqzfJcfaDao aqzfJcfaDao;
	@Resource
	private AqzfSetNumberDao aqzfSetNumberDao;
	@Resource
	private AqzfJcbkDaoImpl aqzfJcbkDao; 
	@Resource
	private AqzfJcdyDaoImpl aqzfJcdyDao;
	@Resource
	private AqzfJcjhQyDao aqzfJcjhQyDao;	
	@Resource
	private AqzfJcnrService aqzfJcnrService;
	@Resource
	private AqzfZlzgDao aqzfZlzgDao;
	@Resource
	private AqzfFcyjDao aqzfFcyjDao;
	@Resource
	private AqzfJcjlDao aqzfJcjlDao;
	@Resource
	private IAqzfJcbkDao AqzfJcbkDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=aqzfJcfaDao.dataGrid(mapData);
		int getTotalCount=aqzfJcfaDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//添加信息
	public void addInfo(AQZF_SafetyCheckSchemeEntity jcfa) {
		//修改检查计划状态，检查计划index页面将不会出现添加方案按钮
		AQZF_Plan_EnterpriseEntity a = aqzfJcjhQyDao.find1(jcfa);
		a.setM1("1");
		aqzfJcjhQyDao.save(a);
		
		//添加检查方案
		Timestamp t=DateUtils.getSysTimestamp();
		jcfa.setS1(t);
		jcfa.setS2(t);
		jcfa.setS3(0);
		jcfa.setM11("0");
		
		//修改文书编号
		AQZF_SetNumberEntity ws = aqzfSetNumberDao.findInfor();
		int fanum=ws.getM1()+1;//编号加1
		ws.setM1(fanum);
		aqzfSetNumberDao.save(ws);
		
		aqzfJcfaDao.save(jcfa);
	}
	
	//添加信息
	public long addInfo2(AQZF_SafetyCheckSchemeEntity jcfa) {
		//修改检查计划状态，检查计划index页面将不会出现添加方案按钮
		AQZF_Plan_EnterpriseEntity a = aqzfJcjhQyDao.find1(jcfa);
		a.setM1("1");
		aqzfJcjhQyDao.save(a);
		
		//添加检查方案
		Timestamp t=DateUtils.getSysTimestamp();
		jcfa.setS1(t);
		jcfa.setS2(t);
		jcfa.setS3(0);
		jcfa.setM11("0");
		
		//修改文书编号
		AQZF_SetNumberEntity ws = aqzfSetNumberDao.findInfor();
		int fanum=ws.getM1()+1;//编号加1
		ws.setM1(fanum);
		aqzfSetNumberDao.save(ws);
		
		aqzfJcfaDao.save(jcfa);
		return jcfa.getID();
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
	
	/**
	 * 查找安全执法的文书编号
	 * @param jcfa
	 */
	public AQZF_SetNumberEntity findWsbh() {
		AQZF_SetNumberEntity a = aqzfSetNumberDao.findInfor();
		return a;

	}
	
	public long addInfoReturnID(AQZF_SafetyCheckSchemeEntity jcfa) {
		Timestamp t=DateUtils.getSysTimestamp();
		jcfa.setS1(t);
		jcfa.setS2(t);
		jcfa.setS3(0);
		aqzfJcfaDao.save(jcfa);
		
		//修改文书编号
		AQZF_SetNumberEntity ws = aqzfSetNumberDao.findInfor();
		int fanum=ws.getM1()+1;//编号加1
		ws.setM1(fanum);
		aqzfSetNumberDao.save(ws);
		
		return jcfa.getID();
	}
	
	//更新信息
	public void updateInfo(AQZF_SafetyCheckSchemeEntity jcfa) {
		Timestamp t=DateUtils.getSysTimestamp();
		jcfa.setS2(t);
		jcfa.setS3(0);
		AQZF_SafetyCheckSchemeEntity a = aqzfJcfaDao.findbyid2(jcfa.getID());
		jcfa.setM11(a.getM11());
		aqzfJcfaDao.save(jcfa);
	}
	
	//检查方案更新信息
		public void updateInfo1(AQZF_SafetyCheckSchemeEntity jcfa) {
			Timestamp t=DateUtils.getSysTimestamp();
			jcfa.setS2(t);
			jcfa.setS3(0);
			AQZF_SafetyCheckSchemeEntity a = aqzfJcfaDao.findbyid2(jcfa.getID());
			jcfa.setM11(a.getM11());
			aqzfJcfaDao.save(jcfa);
		}
		
	//删除信息
	public void deleteInfo(long id) {
		//将检查计划关系表状态改为未添加
		AQZF_Plan_EnterpriseEntity a = aqzfJcjhQyDao.find1(aqzfJcfaDao.findbyid2(id));
		a.setM1("0");
		//删除检查方案信息
		aqzfJcfaDao.deleteInfo(id);
		aqzfJcjhQyDao.save(a);
		
	}

	//根据id查找详细信息
	public Map<String, Object> findById(Long id) {
		return aqzfJcfaDao.findById(id);
	}
	
	/*
	 * 获取检查内容树形图所需要的数据
	 */
	public List<Tree_CheckContent> getAllTreeList( ) {
		List<Tree_CheckContent> treelist=new ArrayList<Tree_CheckContent>();
		//检查单元list
		List<AQZF_SafetyCheckUnitEntity> dylist=aqzfJcfaDao.findDy();
		
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
		return nodeList;
	}
	
	//根据id查找符合word表的数据
	public Map<String,Object> findInfo(Long id) {
		return aqzfJcfaDao.findInfo(id);
	}
	
	
	/**
	 * 根据计划id找到计划关系类
	 * @param jcfa
	 */
	public AQZF_SafetyCheckSchemeEntity findfa(Long id) {
		//获取中间表字段并修改操作状态
		AQZF_SafetyCheckSchemeEntity a = aqzfJcfaDao.find(id);
		return a;

	}
	
	//根据检查方案id获取检查内容 app
	public List<AQZF_SafetyCheckItemEntity> getJcnrForApp(Long id){
		Map<String, Object> jcfa = aqzfJcfaDao.findById(id);
		String[] jcnrids = jcfa.get("m6").toString().split(",");
		List<AQZF_SafetyCheckItemEntity> list = new ArrayList<>();
		for(int i=0;i<jcnrids.length;i++){
			AQZF_SafetyCheckItemEntity jcbk = AqzfJcbkDao.findInfoByIdForApp(Long.parseLong(jcnrids[i]));
			list.add(jcbk);
		}
		return list;
	}
	
	//根据id获得检查方案word表数据
	public Map<String, Object> getAjWord(long id){
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> mapret = aqzfJcfaDao.findInfo(id);
		String m6 = ""+"<w:p />";
		if(mapret.get("m6")!=null&&!mapret.get("m6").toString().equals("")){
			String[] mids = mapret.get("m6").toString().split(",");
			for(int i=0;i<mids.length;i++){
				AQZF_SafetyCheckItemEntity a = AqzfJcbkDao.findInfoById(Long.parseLong(mids[i]));
				if(a!=null)
				m6 = m6 + (i+1) + "、" + a.getM2()+"<w:p />";
			}
		}
		map.put("m0", mapret.get("m0")==null||mapret.get("m0").toString().equals("")?"":mapret.get("m0").toString());
		map.put("qyname", mapret.get("qyname")==null||mapret.get("qyname").toString().equals("")?"":mapret.get("qyname").toString());
		map.put("m1", mapret.get("m1")==null||mapret.get("m1").toString().equals("")?"":mapret.get("m1").toString());
		map.put("m2", mapret.get("m2")==null||mapret.get("m2").toString().equals("")?"":mapret.get("m2").toString());
		map.put("m3", mapret.get("m3")==null||mapret.get("m3").toString().equals("")?"":mapret.get("m3").toString());
		map.put("m4", mapret.get("m4")==null||mapret.get("m4").toString().equals("")?"":mapret.get("m4").toString().substring(0, 10));
		map.put("m5", mapret.get("m5")==null||mapret.get("m5").toString().equals("")?"":mapret.get("m5").toString());
		map.put("m6", m6);
		map.put("m7", mapret.get("m7")==null||mapret.get("m7").toString().equals("")?"":mapret.get("m7").toString());
		//审核意见
		map.put("m8", mapret.get("m8")==null||mapret.get("m8").toString().equals("0")?"不同意":"同意");
		//审批意见
		map.put("m9", mapret.get("m9")==null||mapret.get("m9").toString().equals("0")?"不同意":"同意");
		//备注
		map.put("m10", mapret.get("m10")==null||mapret.get("m10").toString().equals("")?"":mapret.get("m10").toString());
		return map;
	}
}
