package com.cczu.model.lydw.service;

import com.cczu.model.lydw.dao.LYDW_BjjlDao;
import com.cczu.model.lydw.entity.LYDW_BJJL;
import com.cczu.sys.comm.mapper.JsonMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Transactional(readOnly=true)
@Service("LYDW_BjjlService")
public class LYDW_BjjlService {

    @Resource
    LYDW_BjjlDao lydw_bjjlDao;


    /**
     * 报警管理list
     * @return
     */
    public Map<String, Object> dataGrid(Map<String, Object> mapData) {
        List<Map<String, Object>> list=lydw_bjjlDao.dataGrid(mapData);
        int getTotalCount=lydw_bjjlDao.getTotalCount(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }

    /**
     * 获取所有报警记录
     * @return
     */
    public String getAllBjjl(){
        return JsonMapper.getInstance().toJson(lydw_bjjlDao.findAll());
    }

    /**
     * 添加一条报警记录
     * @param bjjl
     * @return
     */
    public void addBjjl(LYDW_BJJL bjjl){
        lydw_bjjlDao.save(bjjl);
    }

    /**
     * 设置状态已处理
     * @param id
     */
    public void setStatus(Long id){
        LYDW_BJJL bjjl = lydw_bjjlDao.find(id);
        bjjl.setStatus(1);
        lydw_bjjlDao.save(bjjl);
    }

    public void addList(List<LYDW_BJJL> bjjls){
        Iterator<LYDW_BJJL> it = bjjls.iterator();
        while (it.hasNext()){
            lydw_bjjlDao.save(it.next());
        }
    }

    public boolean findOne(String tagId, int wlid){
        if(lydw_bjjlDao.findByTagIdAndWlid(tagId,wlid) > 0){
            return true;
        }else{
            return false;
        }
    }
}
