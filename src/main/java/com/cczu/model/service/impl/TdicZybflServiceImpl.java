package com.cczu.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.ITdicZybflDao;
import com.cczu.model.entity.Tdic_ZybAndFlEntity;
import com.cczu.model.entity.dto.Tdic_NoteTreeDto;
import com.cczu.model.service.ITdicZybflService;
import com.cczu.sys.comm.mapper.JsonMapper;

@Transactional(readOnly=true)
@Service("TdicZybflService")
public class TdicZybflServiceImpl implements ITdicZybflService {
	
	@Resource
	private ITdicZybflDao tdiczybfldao;
	
	@Override
	public List<Tdic_NoteTreeDto> getzybflAll() {
		// TODO Auto-generated method stub
		List<Tdic_ZybAndFlEntity> list= tdiczybfldao.findListAll();
		
		List<Tdic_NoteTreeDto> list2=new ArrayList<Tdic_NoteTreeDto>();
		if(list.size()>0){
			for(Tdic_ZybAndFlEntity tzf : list){
				Tdic_NoteTreeDto dto=new Tdic_NoteTreeDto();
				dto.setId(tzf.getCID());
				dto.setText(tzf.getCNAME());
				dto.setPid(tzf.getCPID());
				list2.add(dto);
			}
		}
		
		return list2;
	}

	@Override
	public String gbtjson() {
		// TODO Auto-generated method stub
		List<Tdic_NoteTreeDto>  list=getzybflAll();
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
