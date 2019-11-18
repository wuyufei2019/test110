package com.cczu.sys.comm.utils;

import java.util.ArrayList;
import java.util.List;

import com.cczu.model.entity.dto.Tdic_NoteTreeDto;
import com.cczu.sys.comm.mapper.JsonMapper;

public class TreeUtils {
	
	/**
	 * 迭代共通处理
	 * @return
	 */
	public static String commJsonTree(List<Tdic_NoteTreeDto> list){
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
