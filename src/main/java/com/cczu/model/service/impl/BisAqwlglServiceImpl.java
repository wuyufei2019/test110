package com.cczu.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IBisAqwlglDao;
import com.cczu.model.entity.BIS_T_Safetynetenterprise;
import com.cczu.model.entity.dto.Tree_SafetyNetDto;
import com.cczu.model.entity.dto.Tree_SafetyNetEnterprise;
import com.cczu.model.service.IBisAqwlglService;
import com.cczu.sys.comm.mapper.JsonMapper;

@Transactional(readOnly=true)
@Service("BisAqwlglService")
public class BisAqwlglServiceImpl implements IBisAqwlglService  {
	
	@Resource
	private IBisAqwlglDao bisAqwlglDao;

	@Override
	public List<BIS_T_Safetynetenterprise> getSafetynets(Long uid) {
		return bisAqwlglDao.findAll(uid);
	}

	@Override
	public BIS_T_Safetynetenterprise get(Long id) {
		return bisAqwlglDao.findById(id);
	}

	@Override
	@Transactional(readOnly=false)
	public void save(BIS_T_Safetynetenterprise bis) {
		bisAqwlglDao.save(bis);
	}

	@Override
	@Transactional(readOnly=false)
	public void delete(Long id) {
		bisAqwlglDao.delete(id);
	}

	@Override
	public String getWltson(Long qyid) {
		// TODO Auto-generated method stub
		List<BIS_T_Safetynetenterprise> list = bisAqwlglDao.findAll(qyid);
		List<Tree_SafetyNetDto> list2=new ArrayList<Tree_SafetyNetDto>();
		if(list.size()>0){
			for(BIS_T_Safetynetenterprise wl : list){
				Tree_SafetyNetDto dto=new Tree_SafetyNetDto();
				dto.setId(wl.getId()+"");
				dto.setPid(wl.getPid()+"");
				dto.setName(wl.getM1()+"\n"+wl.getM2());
				dto.setValue("联系方式："+wl.getM3()+"<br/>主要职责："+wl.getM4());
				list2.add(dto);
			}
		}
		
		
		List<Tree_SafetyNetDto> nodeList = new ArrayList<Tree_SafetyNetDto>();  
		for(Tree_SafetyNetDto dto1 : list2){  
		    boolean mark = false;  
		    for(Tree_SafetyNetDto dto2 : list2){  
		        if(dto1.getPid()!=null && dto1.getPid().equals(dto2.getId())){  
		            mark = true;  
		            if(dto2.getChildren() == null)  
		            	dto2.setChildren(new ArrayList<Tree_SafetyNetDto>());  
		            dto2.getChildren().add(dto1);   
		            break;  
		        }  
		    }  
		    if(!mark){  
		        nodeList.add(dto1);   
		    }  
		}  
		return JsonMapper.getInstance().toJson(nodeList);
	}

	@Override
	public List<Tree_SafetyNetEnterprise> getAllTreeList(Long qyid) {
		List<BIS_T_Safetynetenterprise> list=bisAqwlglDao.findAll(qyid);
		
		List<Tree_SafetyNetEnterprise> list2=new ArrayList<Tree_SafetyNetEnterprise>();
		if(list.size()>0){
			for(BIS_T_Safetynetenterprise wl : list){
				Tree_SafetyNetEnterprise dto=new Tree_SafetyNetEnterprise();
				dto.setId(wl.getId());
				dto.setPid(wl.getPid());
				dto.setText(wl.getM1());
				dto.setM2(wl.getM2());
				dto.setM3(wl.getM3());
				dto.setM4(wl.getM4());
				dto.setM5(wl.getM5());
				dto.setId1(wl.getId1());
				list2.add(dto);
			}
		}
		
		List<Tree_SafetyNetEnterprise> nodeList = new ArrayList<Tree_SafetyNetEnterprise>();  
		for(Tree_SafetyNetEnterprise dto1 : list2){  
			dto1.getPid();
		    boolean mark = false;  
		    for(Tree_SafetyNetEnterprise dto2 : list2){  
		        if(dto1.getPid()==dto2.getId()){  
		            mark = true;  
		            if(dto2.getChildren() == null)  
		            	dto2.setChildren(new ArrayList<Tree_SafetyNetEnterprise>());  
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

}
