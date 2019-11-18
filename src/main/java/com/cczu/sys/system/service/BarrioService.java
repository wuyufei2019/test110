package com.cczu.sys.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.entity.dto.Tdic_NoteTreeDto;
import com.cczu.model.entity.dto.Tree_BarrioDto;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.sys.system.dao.BarrioDao;
import com.cczu.sys.system.dao.UserDao;
import com.cczu.sys.system.entity.Barrio;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 网格区域service
 * @author jason
 */
@Service
@Transactional(readOnly = true)
public class BarrioService extends BaseService<Barrio, Long> {

	@Autowired
	private BarrioDao barrioDao;
	@Autowired
	private UserDao userDao;

	@Override
	public HibernateDao<Barrio, Long> getEntityDao() {
		return barrioDao;
	}

	/**
	 * 保存网格区域
	 * @param Barrio
	 */
	@Transactional(readOnly=false)
	public void save(Barrio bar) {
		barrioDao.save(bar);
		//生成编码
		if(bar.getFid()!=0){
			Barrio Fbar=barrioDao.find(bar.getFid());
			bar.setCode(Fbar.getCode()+bar.getId()+"-");
		}else
			bar.setCode(bar.getId()+"-");
		barrioDao.saveUp(bar);
	}

	/**
	 * 修改网格
	 * @param user
	 */
	@Transactional(readOnly=false)
	public void update(Barrio bar) {
		barrioDao.saveUp(bar);
		String oldpcode=bar.getCode();//获取原网格编码
		String newpcode;
		//生成新编码
		if(bar.getFid()!=0){
			Barrio Fbar=barrioDao.find(bar.getFid());
			newpcode=Fbar.getCode()+bar.getId()+"-";
		}else
			newpcode=bar.getId()+"-";
		//如果新编码和原编码不同，则修改该网格以及该网格的所有子网格的编码
		if(!oldpcode.equals(newpcode))
			barrioDao.updateFcode(oldpcode, newpcode);
	}
	
	/**
	 * 根据code查询网格
	 * @param code
	 * @return
	 */
	public Barrio findPointBycode(String code){
		return barrioDao.findPointBycode(code);
	}
	/**
	 * 根据id修改网格的mappoint
	 * @param user
	 */
	@Transactional(readOnly=false)
	public void updateMapPoint(long id,String mappoint) {
		barrioDao.updateMapPoint(id, mappoint);
	}
	/**
	 * 修改企业的行政区域
	 * @param ids 企业id拼接；xzqy 页面获取的网格编码； userxzqy 当前用户的行政区域
	 */
	@Transactional(readOnly=false)
	public String updateQyAndUserXzqy(String ids,String xzqy,String userxzqy) {
		String result="success";
		try {
			barrioDao.updateQyAndUserXzqy(ids, xzqy,userxzqy);//企业的行政区域
		//barrioDao.updateUserXzqy(ids, xzqy,userxzqy);//用户的行政区域
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="error";
		}
		return result;
	}
	/**
	 * 绑定企业时查询企业list
	 * @param user
	 */
	@Transactional(readOnly=false)
	public Map<String, Object> findQy(Map<String,Object> map) {
		List<Map<String,Object>> list = barrioDao.findQy(map);
		Map<String, Object> mapr = new HashMap<String, Object>();
		mapr.put("rows", list);
		return mapr;
	}
	/**
	 * 查询所属网格的企业
	 * @param user
	 */
	@Transactional(readOnly=false)
	public List<Map<String, Object>> findQyByXzqy(String map) {
		List<Map<String,Object>> list = barrioDao.findQyByXzqy(map);
		return list;
	}
	
	
     /**
	 * 删除网格
	 * @param id
	 */
	@Transactional(readOnly=false)
	public void delete(Long id){
		Barrio bar=barrioDao.find(id);
		//删除网格同时删除子网格
		barrioDao.deleteByCode(bar.getCode());
	}
	
	/**
	 * 查询网格
	 * @return
	 */
	public List<Barrio> getAlllist() {
		return barrioDao.findAll();
	}
	
	/**
	 * 查询网格mappoint 不为空的数据
	 * @return
	 */
	public List<Barrio> getNotNonelist(String code) {
		return barrioDao.getNotNonelist(code);
	}
	/**
	 * 查询该网格区域下的所有子网格（网格总览时用）
	 * @return
	 */
	public List<Map<String,Object>> getChildlist(String code) {
		List<Map<String, Object>> list=null;
		try {
			list = barrioDao.getChildlist(code,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list.size()==1) return list;//如果没有子节点，直接返回list
		else{
			//为避免死循环，只循环4次
			for(int i=1;i<5;i++){
				list=barrioDao.getChildlist(code,i+"");
				if(list.size()>1) break;
			}
			return list;
		}
	}
	/**
	 * 查询该网格区域下的所有直属父网格
	 * @return
	 */
	public List<Map<String,Object>> getParentlist(String code,String curcode) {
		List<Map<String, Object>> list=barrioDao.getParentlist(code,curcode);
		return list;
	}
	/**
	 * 查询所有子网格（风险管控-风险分布图）
	 * @return
	 */
	public List<Map<String, Object>> getAllChildlist(String code) {
		return barrioDao.getAllChildlist(code);
	}
	
	/**
	 * 查询选中网格区域下的统计数据（网格总览时用）
	 * @return
	 */
	public Map<String, Object> getTjDatalist(Map<String, Object> map) {
		return barrioDao.getTjDatalist(map);
	}
	
	/**
	 * 查询网格
	 * @return
	 */
	public String getJson_list() {
		String code="";
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if(sessionuser.getUsertype().equals("0")){//安监局
			code=sessionuser.getXzqy();
		}
		List<Barrio> list= barrioDao.findAllBycode(code);
		List<Tree_BarrioDto> list2=new ArrayList<Tree_BarrioDto>();
		if(list.size()>0){
			for(Barrio bar : list){
				Tree_BarrioDto dto=new Tree_BarrioDto();
				dto.setId(bar.getId());
				dto.setFid(bar.getFid());
				dto.setM1(bar.getM1());
				dto.setM2(bar.getM2());
				dto.setM3(bar.getM3());
				dto.setM4(bar.getM4());
				dto.setCode(bar.getCode());
				dto.setMappoint(bar.getMappoint());
				list2.add(dto);
			}
		}
		return commJsonListTree(list2);
	}
	
	/**
	 * 按ID查询网格
	 * @param id
	 * @return 网格对象
	 */
	public Barrio getBarrioById(Long id) {
		return barrioDao.find(id);
	}
	
	/**
	 * 按编码查询网格
	 * @param id
	 * @return 网格对象
	 */
	public Barrio getBarrioByCode(String code) {
		List<Barrio> list =barrioDao.findBy("code", code);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 网格区域json   {网格编码   ： 网格名称}
	 * @return
	 */
	public String jsonListCode(String code) {
		List<Barrio> list= barrioDao.findAllBycode(code);
		List<TBarrioDto> list2=new ArrayList<TBarrioDto>();
		if(list.size()>0){
			for(Barrio bar : list){
				TBarrioDto dto=new TBarrioDto();
				dto.setId(bar.getCode() );
				dto.setText(bar.getM1());
				dto.setS(String.valueOf( bar.getId() ) );
				dto.setPs(String.valueOf( bar.getFid() ) );
				list2.add(dto);
			}
		}  
		
		List<TBarrioDto> nodeList = new ArrayList<TBarrioDto>();  
		for(TBarrioDto dto1 : list2){  
		    boolean mark = false;  
		    for(TBarrioDto dto2 : list2){  
		        if(dto1.getPs()!=null && dto1.getPs().equals(dto2.getS())){  
		            mark = true;  
		            if(dto2.getChildren() == null)  
		            	dto2.setChildren(new ArrayList<TBarrioDto>());  
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
	
	/**
	 * 网格区域json   {网格id   ： 网格名称}
	 * @return
	 */
	public String jsonListBycode(String code) {
		List<Barrio> list= barrioDao.findAllBycode(code);
		
		List<Tdic_NoteTreeDto> list2=new ArrayList<Tdic_NoteTreeDto>();
		if(list.size()>0){
			for(Barrio bar : list){
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
	 * 根据网格编码查看网格成员，包括用户的角色
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getWgUser(Map<String, Object> mapData) {
		return userDao.getWgUser(mapData);
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
	 * 迭代共通处理
	 * @return
	 */
	public String commJsonListTree(List<Tree_BarrioDto> list){
		List<Tree_BarrioDto> nodeList = new ArrayList<Tree_BarrioDto>();  
		for(Tree_BarrioDto dto1 : list){  
		    boolean mark = false;  
		    for(Tree_BarrioDto dto2 : list){  
		        if( dto1.getFid()==dto2.getId()){  
		            mark = true;  
		            if(dto2.getChildren() == null)  
		            	dto2.setChildren(new ArrayList<Tree_BarrioDto>());  
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
	
	//生成网格编码treejson工具类
	class TBarrioDto{
		@Setter
		@Getter
		private String id;//编码
		
		@Setter
		@Getter
		private String text;//网格名称
		
		@Setter
		@Getter
		private String s;//id
		
		@Setter
		@Getter
		private String ps;//父ID
		
		@Setter
		@Getter
		private List<TBarrioDto> children;//子
 
		
	}
}
