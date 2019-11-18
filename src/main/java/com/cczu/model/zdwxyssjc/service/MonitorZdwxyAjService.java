package com.cczu.model.zdwxyssjc.service;

import com.cczu.model.zdwxyssjc.dao.MonitorZdwxyAjDao;
import com.cczu.model.zdwxyssjc.dao.MonitorZdwxyCgDao;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.util.entity.TreeNode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  重大危险源储罐实时数据Service
 *
 */
@Service("MonitorZdwxyAjService")
public class MonitorZdwxyAjService {

	@Resource
	private MonitorZdwxyAjDao monitorZdwxyAjDao;
	
    /**
     * 获取重大危险源树状列表
     * @param map
     * @return
     */
    public String getJson_list(Map<String, Object> map) {
        List<Map<String,Object>> list=monitorZdwxyAjDao.dataGrid(map);
        List<TreeNode> list2=new ArrayList<TreeNode>();
        if(list.size()>0){
            for(Map<String,Object> vedio: list){
                TreeNode dto=new TreeNode();
                dto.setId(vedio.get("id").toString());
                if(vedio.get("fid").toString().equals("0"))
                    dto.setPid("0");
                else
                    dto.setPid(vedio.get("fid").toString());
                /*if(vedio.get("fid").toString().indexOf("qy")==-1) {
                    dto.setState("closed");
                }*/

                dto.setName(vedio.get("name").toString());//上级名称
                list2.add(dto);
            }
        }
        return commJsonListTree(list2);
    }

    /**
     * 迭代共通处理
     * @return
     */
    public String commJsonListTree(List<TreeNode> list){
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        for(TreeNode dto1 : list){
            boolean mark = false;
            for(TreeNode dto2 : list){
                if( dto1.getPid().equals(dto2.getId())){
                    mark = true;
                    if(dto2.getChildren() == null)
                        dto2.setChildren(new ArrayList<TreeNode>());
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
     * 获取接入企业数
     * @param tmap
     * @return
     */
    public int getQyCount(Map<String, Object> tmap) {
        return monitorZdwxyAjDao.getQyTotalCount(tmap);
    }

    /**
     * 获取重大危险源等级为一级、二级、的企业json数据
     * @param mapData
     * @return
     */
    public String getZdwxydjJson() {
        return JsonMapper.toJsonString(monitorZdwxyAjDao.findZdwxydjQy());
    }
}
