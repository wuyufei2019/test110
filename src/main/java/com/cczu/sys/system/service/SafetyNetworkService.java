package com.cczu.sys.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IBisQyjbxxDao;
import com.cczu.model.entity.dto.Tdic_NoteTreeDto;
import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.sys.comm.utils.TreeUtils;
import com.cczu.sys.system.dao.SafetyNetWorkDao;
import com.cczu.sys.system.entity.SafetyNetwork;

/**
 * 安全管理网络service
 * @author jason
 * @date 2017年11月24日
 */
@Service
@Transactional(readOnly = true)
public class SafetyNetworkService extends BaseService<SafetyNetwork, Long> {

	@Autowired
	private SafetyNetWorkDao safetyNetWorkDao;

	@Resource
	private IBisQyjbxxDao bisQyjbxx;
	
	@Override
	public HibernateDao<SafetyNetwork, Long> getEntityDao() {
		return safetyNetWorkDao;
	}

	/**
	 * 保存
	 * @param SafetyNetwork
	 */
	@Transactional(readOnly=false)
	public void save(SafetyNetwork dt) {
		safetyNetWorkDao.save(dt);
	}

	/**
	 * 修改
	 * @param SafetyNetwork
	 */
	@Transactional(readOnly=false)
	public void update(SafetyNetwork dt) {
		safetyNetWorkDao.save(dt);
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@Transactional(readOnly=false)
	public void delete(Long id){
		safetyNetWorkDao.delete(id);
	}
	
	/**
	 * 网络结构json tree
	 * @return
	 */
	public String getlist(long qyid) {
		List<SafetyNetwork> list=safetyNetWorkDao.findBy("ID1", qyid);	
		
		List<Tdic_NoteTreeDto> list2=new ArrayList<Tdic_NoteTreeDto>();
		if(list.size()>0){
			for(SafetyNetwork dep : list){
				Tdic_NoteTreeDto dto=new Tdic_NoteTreeDto();
				dto.setId(String.valueOf( dep.getId() ) );
				dto.setText(dep.getM1());
				dto.setPid(String.valueOf( dep.getPid() ) );
				list2.add(dto);
			}
		}
		
		return TreeUtils.commJsonTree(list2);
	}
	
	/**
	 * 按ID查询
	 * @param id
	 * @return 部门对象
	 */
	public SafetyNetwork getSafetyNetworkById(Long id) {
		return safetyNetWorkDao.find(id);
	}

	public Map<String, Object> searchData(Map<String, Object> map) {
		List<SafetyNetwork> list=safetyNetWorkDao.dataGrid(map);
		int getTotalCount=safetyNetWorkDao.getTotalCount(map);
		Map<String, Object> map2 = new HashMap<String, Object>();
		List<Map<String, Object>> list2=new ArrayList<>();
		for(SafetyNetwork dep:list){
			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("id", dep.getId());
			if(dep.getPid()!=0)
				temp.put("_parentId", dep.getPid());
			temp.put("m1", dep.getM1());
			temp.put("m2", dep.getM2());
			temp.put("m3", dep.getM3());
			temp.put("m4", dep.getM4());
			temp.put("m5", dep.getM5());
			list2.add(temp);
		}
		map2.put("rows", list2);
		map2.put("total", getTotalCount);
		return map2;
	}
	
	/**
	 * 根据id集合查询实体
	 * @param ids  
	 * @return  
	 */
	public List<SafetyNetwork> findListByBmids(String ids) {
		return safetyNetWorkDao.findListByBmids(ids);
	}
}
