package com.cczu.model.lydw.service;

import com.cczu.model.lydw.dao.Pub_FileDao;
import com.cczu.model.lydw.entity.Pub_File;
import com.cczu.sys.comm.mapper.JsonMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 蓝牙定位-工卡管理
 */
@Transactional(readOnly=true)
@Service("Pub_FileService")
public class Pub_FileService{
	
	@Resource
	private Pub_FileDao pfDao;
	

	public void addInfo(Pub_File pf) {
		pfDao.saveInfo(pf);
	}

	public void updateInfo(Pub_File pf) {
		pfDao.saveUp(pf);
	}


	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Pub_File> list=pfDao.dataGrid(mapData);
		int getTotalCount=pfDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	public void deleteInfo(Long fileid) {
		// TODO Auto-generated method stub
		pfDao.delete(fileid);
	}

	public Pub_File findById(Long id) {
		return pfDao.findById(id);
	}

	public String findByTagId(String fileCode){
		Map<String , Object> mapDate = new HashMap<>();
		mapDate.put("filecode" , fileCode);
		List<Map<String, Object>> list = pfDao.findByTagId(mapDate);
		return list.get(0).get("fileid").toString();
	}

	/**
	 * 工卡号json
	 * @return
	 */
	public String jsonlist() {
		return JsonMapper.getInstance().toJson(pfDao.jsonlist());
	}
    /**
     * 工卡号json id :工卡号 text:绑定的员工姓名
     * @return
     */
    public String jsonlist2() {
        return JsonMapper.getInstance().toJson(pfDao.jsonlist2());
    }

}
