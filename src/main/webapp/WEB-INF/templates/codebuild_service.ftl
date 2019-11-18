package ${code_classpack }.service;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${code_classpack }.dao.${code_classname?replace('_', '' ) }Dao;
import ${code_classpack }.entity.${code_classname };

/**
 * ${code_description }Service
 * @author 
 * @date ${.now?string["yyyy年MM月dd日"] }
 */
@Transactional(readOnly=true)
@Service("${code_classname?replace('_', '' ) }Service")
public class ${code_classname?replace('_', '' ) }Service {
	
	@Resource
	private ${code_classname?replace('_', '' ) }Dao ${code_classname?replace('_', '' )?uncap_first }Dao;
	
	public void addInfo(${code_classname } entity) {
		// TODO Auto-generated method stub
		${code_classname?replace('_', '' )?uncap_first }Dao.save(entity);
	}
   
	public long addInfoReId(${code_classname } entity) {
		// TODO Auto-generated method stub
		${code_classname?replace('_', '' )?uncap_first }Dao.save(entity);
         return entity.getID();
	}
	
	public void updateInfo(${code_classname } entity) {
		${code_classname?replace('_', '' )?uncap_first }Dao.save(entity);
	}

	
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		${code_classname?replace('_', '' )?uncap_first }Dao.deleteInfo(id);
	}

	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<${code_classname }> list=${code_classname?replace('_', '' )?uncap_first }Dao.dataGrid(mapData);
		int getTotalCount=${code_classname?replace('_', '' )?uncap_first }Dao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	
	public ${code_classname } findById(Long id) {
		// TODO Auto-generated method stub
		return ${code_classname?replace('_', '' )?uncap_first }Dao.find(id);
	}
	
}


