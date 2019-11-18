package com.cczu.model.xfssgl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.impl.BisQyjbxxDaoImpl;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.xfssgl.dao.XfssglXfssdjDao;
import com.cczu.model.xfssgl.entity.Tree_XfssdjEntity;
import com.cczu.model.xfssgl.entity.XFSSGL_XfssdjEntity;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.StringUtils;


/**
 * 
 * @Description:消防设施登记Service
 * @author: wbth
 * @date: 2018年4月21日
 */
@Transactional(readOnly=true)
@Service("XfssglXfssdjService")
public class XfssglXfssdjService {
	@Resource
	private XfssglXfssdjDao xfssglXfssdjDao;
	/*@Resource
	private YhpcCheckPointContentDao yhpcCheckPointContentDao;
	*/
	@Resource
	private BisQyjbxxDaoImpl bisQyjbxxDaoImpl;
	
	/**
	 * 查询消防设施信息list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=xfssglXfssdjDao.dataGrid(mapData);
		int getTotalCount=xfssglXfssdjDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 获取消防设施类别Json数据
	 * @param mapData
	 * @return
	 */
	/*public String xfsscjson(){
		List<XFSSGL_XfssCategoryEntity> list = xfssglXfssdjDao.xfsscdataGrid();
		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
		for(XFSSGL_XfssCategoryEntity xc:list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", xc.getID());
			map.put("text", xc.getName());
			arrylist.add(map);
		}
		return JsonMapper.getInstance().toJson(arrylist);
	}*/
	
	//添加
	public String addInfo(XFSSGL_XfssdjEntity entity) {	
		String datasuccess = "success";
		xfssglXfssdjDao.save(entity);
		return datasuccess;
	}
	
	//根据id查询详细信息
	public Map<String, Object> findInforById(Long id) {
		return xfssglXfssdjDao.findInforById(id);
	}
	
	//更新信息
	public String updateInfo(XFSSGL_XfssdjEntity entity) {
		String datasuccess = "";
		xfssglXfssdjDao.save(entity);
		if ("0".equals(entity.getType())) {//0代表类别
			if (StringUtils.isNotBlank(entity.getIcon())) {
				//如果类别的实体中icon(图标)不为空，则对应的设施也修改成同样的icon
				Map<String, Object> map = new HashMap<>();
				map.put("pid", entity.getID());
				map.put("icon", entity.getIcon());
				int result = xfssglXfssdjDao.updToAddIcon(map);
				if (result > 0) {
					datasuccess = "success";
				} else {
					datasuccess = "failed";
				}
			}
		}
		return datasuccess;
	}
	
	//删除信息
	public void deleteInfo(long id) {
		xfssglXfssdjDao.deleteInfo(id);
	}
	
	
	/**
	 * 根据企业id查询企业平面图地址
	 * @return
	 */
	public String findpmtByqyid(long qyid) {
		BIS_EnterpriseEntity bis =bisQyjbxxDaoImpl.find(qyid);
		return StringUtils.defaultString(bis.getM33_3());
	}
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="消防设施信息表.xls";
		List<Map<String, Object>> list=xfssglXfssdjDao.getExportInfo(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response, true);
	}

	public List<Tree_XfssdjEntity> getWltson(Long qyid) {
		List<XFSSGL_XfssdjEntity> list=xfssglXfssdjDao.findAllInfo(qyid);
		
		List<Tree_XfssdjEntity> list2=new ArrayList<Tree_XfssdjEntity>();
		if(list.size()>0){
			for(XFSSGL_XfssdjEntity xfss : list){
				Tree_XfssdjEntity dto=new Tree_XfssdjEntity();
				dto.setID(xfss.getID());
				dto.setPid(xfss.getPid());
				dto.setText(xfss.getName());
				list2.add(dto);
			}
		}
		
		List<Tree_XfssdjEntity> nodeList = new ArrayList<Tree_XfssdjEntity>();  
		for(Tree_XfssdjEntity dto1 : list2){  
		    boolean mark = false;
		    for(Tree_XfssdjEntity dto2 : list2){  
		        if(dto1.getPid()==dto2.getID()){  
		            mark = true;  
		            if(dto2.getChildren() == null)  
		            	dto2.setChildren(new ArrayList<Tree_XfssdjEntity>());  
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
