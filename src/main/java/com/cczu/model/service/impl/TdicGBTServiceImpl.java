package com.cczu.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.ITdicBisAJFLDao;
import com.cczu.model.dao.ITdicGBTDao;
import com.cczu.model.entity.Tdic_BisjgflEntity;
import com.cczu.model.entity.Tdic_GbtbusinessEntity;
import com.cczu.model.entity.dto.Tdic_NoteTreeDto;
import com.cczu.model.service.ITdicGBTService;
import com.cczu.sys.comm.mapper.JsonMapper;

@Transactional(readOnly=true)
@Service("ITdicGBTService")
public class TdicGBTServiceImpl implements ITdicGBTService  {
	
	@Resource
	private ITdicGBTDao tdicGBTDao;
	@Resource
	private ITdicBisAJFLDao tdicBisAJFLDao;

	@Override
	public List<Tdic_NoteTreeDto> getGBTAll() {
		List<Tdic_GbtbusinessEntity> list= tdicGBTDao.findListAll();
		
		List<Tdic_NoteTreeDto> list2=new ArrayList<Tdic_NoteTreeDto>();
		if(list.size()>0){
			for(Tdic_GbtbusinessEntity gbt : list){
				Tdic_NoteTreeDto dto=new Tdic_NoteTreeDto();
				dto.setId(gbt.getCID());
				dto.setText(gbt.getCNAME());
				dto.setPid(gbt.getCPID());
				list2.add(dto);
			}
		}
		
		return list2;
	}

	@Override
	public String gbtjson() {
		List<Tdic_NoteTreeDto>  list=getGBTAll();
		return commJsonTree(list);
	}

	@Override
	public String gbtdljson() {
		List<Map<String,Object>>  list=tdicBisAJFLDao.findDlLList();
		return JsonMapper.getInstance().toJson(list);
	}

	
	@Override
	public List<Tdic_NoteTreeDto> getAJFLAll() {
		List<Tdic_BisjgflEntity> list= tdicBisAJFLDao.findAJFLList();
		
		List<Tdic_NoteTreeDto> list2=new ArrayList<Tdic_NoteTreeDto>();
		if(list.size()>0){
			for(Tdic_BisjgflEntity gbt : list){
				Tdic_NoteTreeDto dto=new Tdic_NoteTreeDto();
				dto.setId(gbt.getCID());
				dto.setText(gbt.getCNAME());
				dto.setPid(gbt.getCPID());
				list2.add(dto);
			}
		}
		
		return list2;
	}
	@Override
	public String ajfljson() {
		List<Tdic_NoteTreeDto>  list=getAJFLAll();
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
