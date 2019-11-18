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
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.dto.Tdic_NoteTreeDto;
import com.cczu.model.entity.dto.Tree_Department;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.sys.system.dao.DepartmentDao;
import com.cczu.sys.system.entity.Department;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 部门service
 * @author jason
 */
@Service
@Transactional(readOnly = true)
public class DepartmentService extends BaseService<Department, Long> {

	@Autowired
	private DepartmentDao departmentDao;

	@Resource
	private IBisQyjbxxDao bisQyjbxx;
	
	@Override
	public HibernateDao<Department, Long> getEntityDao() {
		return departmentDao;
	}
	
	/**
	 * 删除部门
	 * @param id
	 */
	@Transactional(readOnly=false)
	public void delete(Long id){
		departmentDao.delete(id);
	}
	
	/**
	 * 查询部门
	 * @return
	 */
	public String getAlllist() {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> mapd = new HashMap<String, Object>();
		mapd.put("flag", sessionuser.getUsertype());
		
		if(sessionuser.getUsertype().equals("1")){//如果是企业
			BIS_EnterpriseEntity be = bisQyjbxx.findInfoById(sessionuser.getQyid());
			if(be!=null){
				mapd.put("id2", be.getID());
			}
		}else{//如果是安监
			mapd.put("id3", sessionuser.xzqy);
		}
		
		List<Department> list=departmentDao.findJson(mapd);
		
		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
		for(Department dep:list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", dep.getId());
			map.put("text", dep.getM1());
			arrylist.add(map);
		}
		return JsonMapper.getInstance().toJson(arrylist);
	}

	/**
	 * 查询部门2
	 * @return
	 */
	public String getAlllist2() {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Map<String, Object> mapd = new HashMap<String, Object>();
		mapd.put("flag", sessionuser.getUsertype());
		
		BIS_EnterpriseEntity be = bisQyjbxx.findInfoById(sessionuser.getQyid());
		if(be!=null){
			mapd.put("id2", be.getID());
		}
		
		List<Department> list=departmentDao.findJson(mapd);
		
		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
		for(Department dep:list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", dep.getId());
			map.put("text", dep.getM1());
			arrylist.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "");
		map.put("text", "全部");
		arrylist.add(map);
		return JsonMapper.getInstance().toJson(arrylist);
	}
	
	/**
	 * 查询部门（app端）
	 * @return
	 */
	public List<Map<String,Object>> getAlllistForApp(String qyid,String usertype) {
		Map<String, Object> mapd = new HashMap<String, Object>();
		mapd.put("flag", usertype);
		mapd.put("id2", qyid);
		
		List<Map<String,Object>> list=departmentDao.findJsonForApp(mapd);
		return list;
	}
	
	/**
	 * 按ID查询部门
	 * @param id
	 * @return 部门对象
	 */
	public Department getDepartmentById(Long id) {
		return departmentDao.find(id);
	}

	public Map<String, Object> searchData(Map<String, Object> map) {
		List<Department> list=departmentDao.dataGrid(map);
		int getTotalCount=departmentDao.getTotalCount(map);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("rows", list);
		map2.put("total", getTotalCount);
		return map2;
	}
	
	/**
	 * 部门页面list内容
	 * @param map
	 * @return
	 */
	public String getJson_list(Map<String, Object> map) {
		List<Department> list=departmentDao.dataGrid(map);
		List<Tree_Department> list2=new ArrayList<Tree_Department>();
		if(list.size()>0){
			for(Department dep : list){
				Tree_Department dto=new Tree_Department();
				dto.setId(dep.getId());
				if(dep.getFid()==null)
					dto.setFid(0l);
				else
					dto.setFid(dep.getFid());
				dto.setM1(dep.getM1());//部门名称
				dto.setM2(dep.getM2());//排序
				dto.setM3(dep.getM3());//备注
				dto.setFlag(dep.getFlag());
				dto.setCode(dep.getCode());
				list2.add(dto);
			}
		}
		return commJsonListTree(list2);
	}

	/**
	 * 迭代共通处理
	 * @return
	 */
	public String commJsonListTree(List<Tree_Department> list){
		List<Tree_Department> nodeList = new ArrayList<Tree_Department>();  
		for(Tree_Department dto1 : list){  
		    boolean mark = false;  
		    for(Tree_Department dto2 : list){  
		        if( dto1.getFid()==dto2.getId()){  
		            mark = true;  
		            if(dto2.getChildren() == null)  
		            	dto2.setChildren(new ArrayList<Tree_Department>());  
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
	 * 部门json   {部门id   ： 部门名称}
	 * @return
	 */
	public String jsonListBycode(Map<String ,Object> map) {
		List<Department> list=departmentDao.dataGrid(map);
		
		List<Tdic_NoteTreeDto> list2=new ArrayList<Tdic_NoteTreeDto>();
		if(list.size()>0){
			for(Department bar : list){
				Tdic_NoteTreeDto dto=new Tdic_NoteTreeDto();
				dto.setId(String.valueOf( bar.getId() ) );
				dto.setText(bar.getM1());
				dto.setPid(String.valueOf( bar.getFid() ) );
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
	 * 根据部门id集合查询部门
	 * @param ids  
	 * @return  
	 */
	public List<Department> findListByBmids(String ids) {
		return departmentDao.findListByBmids(ids);
	}
	
	/**
	 * 按部门名称查询部门
	 * @param id
	 * @return 部门对象
	 */
	public Department findInfoByName(String deptname) {
		return departmentDao.findInfoByName(deptname);
	}

	/**
	 * 保存部门
	 * @param Department
	 */
	@Transactional(readOnly=false)
	public void save(Department dep) {
		departmentDao.save(dep);
		//生成编码
		if(dep.getFid()!=0){
			Department Fdep=departmentDao.find(dep.getFid());
			dep.setCode(Fdep.getCode()+dep.getId()+"-");
		}else
			dep.setCode(dep.getId()+"-");
		departmentDao.saveUp(dep);
	}
	
	/**
	 * 修改部门
	 * @param user
	 */
	@Transactional(readOnly=false)
	public void update(Department dep) {
		departmentDao.saveUp(dep);
		String oldpcode=dep.getCode();//获取原网格编码
		if(oldpcode==null)
			oldpcode="";
		String newpcode;
		//生成新编码
		if(dep.getFid()!=null&&dep.getFid()!=0){
			Department Fdep=departmentDao.find(dep.getFid());
			newpcode=Fdep.getCode()+dep.getId()+"-";
		}else
			newpcode=dep.getId()+"-";
		//如果新编码和原编码不同，则修改该部门网格以及该部门网格的所有子网格的编码
		if(!oldpcode.equals(newpcode))
			departmentDao.updateFcode(oldpcode, newpcode);
	}
}
