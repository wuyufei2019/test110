package com.cczu.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.ITdicGBTDao;
import com.cczu.model.dao.TdicZclxDao;
import com.cczu.model.entity.Tdic_ExpenseCategoryEntity;
import com.cczu.model.entity.dto.Tdic_NoteTreeDto;
import com.cczu.sys.comm.mapper.JsonMapper;

/**
 * 字典---支出类型Service
 * @author jason
 * @date 2017年8月8日
 */
@Transactional(readOnly=true)
@Service("TdicZclxService")
public class TdicZclxService {
	
	@Resource
	private ITdicGBTDao tdicGBTDao;	
	@Resource
	private TdicZclxDao zclxDao;
	
	/**
	 * 获取所有支出项目类别
	 * @return 集合
	 */
	public List<Tdic_NoteTreeDto> getGBTAll() {
		List<Tdic_ExpenseCategoryEntity> list= zclxDao.findListAll();
		
		List<Tdic_NoteTreeDto> list2=new ArrayList<Tdic_NoteTreeDto>();
		if(list.size()>0){
			for(Tdic_ExpenseCategoryEntity gbt : list){
				Tdic_NoteTreeDto dto=new Tdic_NoteTreeDto();
				dto.setId(gbt.getID()+"");
				dto.setText(gbt.getNAME());
				dto.setPid(gbt.getPID());
				list2.add(dto);
			}
		}
		
		return list2;
	}
	
	public String gbtjson() {
		List<Tdic_NoteTreeDto>  list=getGBTAll();
		return commJsonTree(list);
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
}
