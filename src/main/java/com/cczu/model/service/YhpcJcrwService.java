package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cczu.model.dao.YhpcJcrwDao;
import com.cczu.model.dao.YhpcRcjcbkDao;
import com.cczu.model.entity.YHPC_DaliyCheckEntity;
import com.cczu.model.entity.YHPC_InspectionTaskEntity;
import com.cczu.model.entity.dto.Tree_CheckContent;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.utils.MessageUtil;

/**
 *  隐患排查_检查任务Service
 *
 */
@Transactional(readOnly=true)
@Service("YhpcJcrwService")
public class YhpcJcrwService {

	@Resource
	private YhpcJcrwDao yhpcJcrwDao;
	@Resource
	private YhpcRcjcbkDao yhpcRcjcbkDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=yhpcJcrwDao.dataGrid(mapData);
		int getTotalCount=yhpcJcrwDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//添加信息
	public void addInfo(YHPC_InspectionTaskEntity jcrw) {
		//添加检查任务
		Timestamp t=DateUtils.getSysTimestamp();
		jcrw.setS1(t);
		jcrw.setS2(t);
		jcrw.setS3(0);
		yhpcJcrwDao.save(jcrw);
		final long rwid = jcrw.getID();
		//发送msg消息
		Map<String,Object>  map = new HashMap<String,Object>(){{
			put(Message.MSGTARGET_H5,"aqjc/task/index.do");//h5页面
			put(Message.MSGTARGET_PC,"yhpc/jcrw/addJl/"+rwid);//后台页面
		}};
		String[] split = jcrw.getM2().split(",");
		for (String string : split) {
			MessageUtil.sendMsg(string, jcrw.getID2()+"", "您有新的检查任务", Message.MessageType.DJC.getMsgType(),JSON.toJSONString(map),"您有新的检查任务");
		}

	}

	//更新信息
	public void updateInfo(YHPC_InspectionTaskEntity jcrw) {
		Timestamp t=DateUtils.getSysTimestamp();
		jcrw.setS2(t);
		yhpcJcrwDao.save(jcrw);
	}
	
	//根据id查找详细信息
	public Map<String, Object> findById(Long id) {
		return yhpcJcrwDao.findById(id);
	}

	//根据id查找对象
	public YHPC_InspectionTaskEntity find(Long id) {
		return yhpcJcrwDao.find(id);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		yhpcJcrwDao.deleteInfo(id);
	}
	
	/*
	 * 获取检查内容树形图所需要的数据
	 */
	public List<Tree_CheckContent> getAllTreeList(String lx,Long qyid) {
		List<Tree_CheckContent> treelist=new ArrayList<Tree_CheckContent>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m1", lx);
		map.put("qyid",qyid);
		//检查单元list
		List<Map<String, Object>> dylist=yhpcRcjcbkDao.findUnitList(map);
		
		for(Map<String, Object> dy : dylist){
			Tree_CheckContent ny=new Tree_CheckContent();
			ny.setText(dy.get("text").toString());
			ny.setPtext("");
			treelist.add(ny);
			//根据检查单元查询检查项
			map.put("m2", dy.get("text").toString());
			List<YHPC_DaliyCheckEntity> list=yhpcRcjcbkDao.findContentList2(map);
			for(YHPC_DaliyCheckEntity jcx:list){
				Tree_CheckContent ny2=new Tree_CheckContent();
				ny2.setId(jcx.getID());
				ny2.setText(jcx.getM3());
				ny2.setPtext(dy.get("text").toString());
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
	
	/**
	 * 根据检查内容的id去检查表库查询list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridNr(String id) {
		
		List<Map<String,Object>> list=yhpcJcrwDao.dataGridNr(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		return map;
	}
}
