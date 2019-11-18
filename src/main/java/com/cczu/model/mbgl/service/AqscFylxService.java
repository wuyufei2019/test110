package com.cczu.model.mbgl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.entity.Tree_ExpenseType;
import com.cczu.model.entity.dto.Tdic_NoteTreeDto;
import com.cczu.model.mbgl.dao.AqscFylxDao;
import com.cczu.model.mbgl.entity.AQSC_ExpenseType;
import com.cczu.sys.comm.mapper.JsonMapper;

/**
 *  安全生产-费用类型Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("AqscFylxService")
public class AqscFylxService {
	@Resource
	private AqscFylxDao aqscFylxDao;
	
	/**
	 * 查询树状图
	 * @return
	 */
	public String dataGrid(Map<String, Object> mapData) {
		List<AQSC_ExpenseType> list= aqscFylxDao.dataGrid(mapData);
		List<Tree_ExpenseType> list2=new ArrayList<Tree_ExpenseType>();
		if(list.size()>0){
			for(AQSC_ExpenseType bar : list){
				Tree_ExpenseType dto=new Tree_ExpenseType();
				dto.setId(bar.getID());
				dto.setFid(bar.getFid());
				dto.setM1(bar.getM1());
				dto.setM2(bar.getM2());
				dto.setM3(bar.getM3());
				dto.setM4(bar.getM4());
				list2.add(dto);
			}
		}
		return commJsonListTree(list2);
	}
	
	/**
	 * 网格区域json   {网格id   ： 网格名称}
	 * @return
	 */
	public String jsonListBycode() {
		Map<String, Object> mapData=new HashMap<String, Object>();
		List<AQSC_ExpenseType> list= aqscFylxDao.dataGrid(mapData);
		List<Tdic_NoteTreeDto> list2=new ArrayList<Tdic_NoteTreeDto>();
		if(list.size()>0){
			for(AQSC_ExpenseType bar : list){
				Tdic_NoteTreeDto dto=new Tdic_NoteTreeDto();
				dto.setId(bar.getID().toString());
				dto.setText(bar.getM1());
				dto.setPid(String.valueOf(bar.getFid()));
				list2.add(dto);
			}
		}
		return commJsonTree(list2);
	}
	
	/**
	 * 迭代共通处理
	 * @return
	 */
	public String commJsonTree(List<Tdic_NoteTreeDto> list){
		List<Tdic_NoteTreeDto> nodeList = new ArrayList<Tdic_NoteTreeDto>();  
		for(Tdic_NoteTreeDto dto1 : list){  
		    boolean mark = false;  
		    for(Tdic_NoteTreeDto dto2 : list){  
		        if(dto1.getPid()!=null && dto1.getPid().equals(dto2.getId())){  
		            mark = true;  
		            if(dto2.getChildren() == null)  
		            	dto2.setChildren(new ArrayList<Tdic_NoteTreeDto>());  
		            dto2.getChildren().add(dto1);   
		            break;  
		        }  
		    }  
		    if(!mark){  
		        nodeList.add(dto1);   
		    }  
		}  
		//转为json格式 
		return JsonMapper.getInstance().toJson(nodeList);
	}
	
	/**
	 * 添加费用类型
	 */
	public void addInfo(AQSC_ExpenseType bis) {
		aqscFylxDao.save(bis);
	}

	/**
	 * 修改费用类型
	 */
	public void updateInfo(AQSC_ExpenseType bis) {
		aqscFylxDao.save(bis);
	}
	
	/**
	 * 删除费用类型同时删除子节点
	 */
	public void deleteInfo(long id) {
		aqscFylxDao.deleteInfo(id);
	}

	public AQSC_ExpenseType findById(Long id) {
		return aqscFylxDao.find(id);
	}
	
	/**
	 * 迭代共通处理
	 * @return
	 */
	public String commJsonListTree(List<Tree_ExpenseType> list){
		List<Tree_ExpenseType> nodeList = new ArrayList<Tree_ExpenseType>();  
		for(Tree_ExpenseType dto1 : list){  
		    boolean mark = false;  
		    for(Tree_ExpenseType dto2 : list){  
		        if( dto1.getFid()==dto2.getId()){  
		            mark = true;  
		            if(dto2.getChildren() == null)  
		            	dto2.setChildren(new ArrayList<Tree_ExpenseType>());  
		            dto2.getChildren().add(dto1);   
		            break;  
		        }  
		    }  
		    if(!mark){  
		        nodeList.add(dto1);   
		    }  
		}  
		//转为json格式 
		return JsonMapper.getInstance().toJson(nodeList);
	}
}
