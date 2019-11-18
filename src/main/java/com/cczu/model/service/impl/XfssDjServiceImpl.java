package com.cczu.model.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IXfssDjDao;
import com.cczu.model.entity.Tree_XFSS_RegistEntity;
import com.cczu.model.entity.XFSS_RegisterEntity;
import com.cczu.model.service.IXfssDjService;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.QRCode;


@Transactional(readOnly=true)
@Service("XfssDjService")
public class XfssDjServiceImpl implements IXfssDjService {
	
	@Resource
	private IXfssDjDao xfssDjDao;

	@Override
	public void addInfo(XFSS_RegisterEntity xfss) {
		xfssDjDao.addInfo(xfss);
		
	}

	@Override
	public void updateInfo(XFSS_RegisterEntity xfss) {
		xfssDjDao.updateInfo(xfss);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=xfssDjDao.dataGrid(mapData);
		int getTotalCount=xfssDjDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		xfssDjDao.deleteInfo(id);
	}
	
	@Override
	public String createQr(String path, String name, String text) {
		
		try {
			String fileName = QRCode.encode(text, null, path, true, name);
			return fileName;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public XFSS_RegisterEntity findById(Long id) {
		return xfssDjDao.findById(id);
	}
	
	@Override
	public List<Map<String,Object>> findAllInfo(Long qyid) {
		return xfssDjDao.findAllInfo(qyid);
	}
	
	@Override
	public List<Map<String,Object>> findAllInfo(Long qyid, Long pmtId) {
		return xfssDjDao.findAllInfo(qyid, pmtId);
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			String[] title = mapData.get("coltext").toString().split(",") ;
			String[] keys = mapData.get("colval").toString().split(",") ;
			String fileName="消防设施登记信息.xls";
			List<Map<String, Object>> list=xfssDjDao.getExcel(mapData);
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			String[] title={"消防设施类别","名称","规格型号","投用时间","换验周期","生产厂商","状态","登记人员","备注"};  
			String[] keys={"m1","m2","m3","m6","m7","m8","m9","djnm","m10"};
			String fileName="消防设施登记信息.xls";
			List<Map<String, Object>> list=xfssDjDao.getExcel(mapData);
			new ExportExcel(fileName, title, keys, list, response);
		}
	}
	
	@Override
	public List<Tree_XFSS_RegistEntity> getWltson(Long qyid) {
		List<Map<String,Object>> list=xfssDjDao.findAllInfo(qyid);
		
		List<Tree_XFSS_RegistEntity> list2=new ArrayList<Tree_XFSS_RegistEntity>();
		if(list.size()>0){
			for(Map<String,Object> xfss : list){
				Tree_XFSS_RegistEntity dto=new Tree_XFSS_RegistEntity();
				dto.setID(Long.parseLong(xfss.get("id").toString()));
				dto.setPid(Long.parseLong(xfss.get("pid").toString()));
				dto.setText(xfss.get("m1").toString());
				list2.add(dto);
			}
		}
		
		List<Tree_XFSS_RegistEntity> nodeList = new ArrayList<Tree_XFSS_RegistEntity>();  
		for(Tree_XFSS_RegistEntity dto1 : list2){  
			dto1.getPid();
		    boolean mark = false;  
		    for(Tree_XFSS_RegistEntity dto2 : list2){  
		        if(dto1.getPid()==dto2.getID()){  
		            mark = true;  
		            if(dto2.getChildren() == null)  
		            	dto2.setChildren(new ArrayList<Tree_XFSS_RegistEntity>());  
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
