package com.cczu.model.dxj.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cczu.model.entity.Tdic_ZybAndFlEntity;
import com.cczu.model.entity.dto.Tdic_NoteTreeDto;
import com.cczu.sys.comm.mapper.JsonMapper;
import org.springframework.stereotype.Service;

import com.cczu.model.dxj.dao.DxjSbDao;
import com.cczu.model.dxj.entity.DXJ_SbEntity;

/**
 *Service
 */
@Service("DxjSbService")
public class DxjSbService {
	
	@Resource
	private DxjSbDao dxjSbDao;
	
	/**
	 * 记录list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=dxjSbDao.dataGrid(mapData);
		int getTotalCount=dxjSbDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 得到设备list
	 * @return
	 */
	public List<Map<String, Object>> getsblist(Map<String, Object> mapData) {
		return dxjSbDao.getsblist(mapData);
	}

	/**
	 * 添加修改信息
	 * @param jcdy
	 */
	public String addInfo(DXJ_SbEntity jcdy,long id) {
		String datasuccess = "success";
		//判断绑定二维码rfid是否重复
		if(checkSameBuildContent(id, jcdy.getBindcontent())&&(!com.cczu.util.common.StringUtils.isEmpty(jcdy.getBindcontent()))){
			datasuccess = "ewmerror";
		}else if(checkSameRfid(id,jcdy.getRfid())&&(!com.cczu.util.common.StringUtils.isEmpty(jcdy.getRfid()))){
			datasuccess = "rfiderror";
		}else{
			try {
				dxjSbDao.save(jcdy);
			} catch (Exception e) {
				e.printStackTrace();
				datasuccess="error";
			}
		}
		return datasuccess;
	}
	
	//验证二维码重复
	public boolean checkSameBuildContent(long id,String bindcontent) {
		return dxjSbDao.checkSameBuildContent(id,bindcontent);
	}
	
	//验证rfid重复
	public boolean checkSameRfid(long id,String rfid) {
		return dxjSbDao.checkSameRfid(id,rfid);
	}

	/**
	 * 删除
	 * @param parseLong
	 */
	public void deleteInfo(long id) {
		dxjSbDao.deleteInfo(id);
	}

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public DXJ_SbEntity findById(Long id) {
		return dxjSbDao.find(id);
	}


    /**
     * 树形结构显示
     * @return
     */
    public String gbtjson(Long qyid) {
        List<Tdic_NoteTreeDto>  list=getsbAll(qyid);
        return commJsonTree(list);
    }

    public List<Tdic_NoteTreeDto> getsbAll(Long qyid) {
        List<Map<String,Object>> list= dxjSbDao.getsbAll(qyid);

        List<Tdic_NoteTreeDto> list2=new ArrayList<Tdic_NoteTreeDto>();
        if(list.size()>0){
            for(Map tzf : list){
                Tdic_NoteTreeDto dto=new Tdic_NoteTreeDto();
                dto.setId(String.valueOf(tzf.get("id")));
                dto.setText(String.valueOf(tzf.get("text")));
                dto.setPid(String.valueOf(tzf.get("pid")));
                list2.add(dto);
            }
        }

        return list2;
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
