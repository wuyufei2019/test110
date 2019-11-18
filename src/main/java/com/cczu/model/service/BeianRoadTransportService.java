package com.cczu.model.service;

import com.cczu.model.dao.BeianRoadTransportDao;
import com.cczu.model.entity.BEIAN_RoadTransportEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description: 道路运输证管理Service
 * @author: wbth
 * @date: 2018年8月24日
 */
@Transactional(readOnly = true)
@Service("BeianRoadTransportService")
public class BeianRoadTransportService {
    @Resource
    private BeianRoadTransportDao beianRoadTransportDao;
    @Resource
    private BasicCarService carService;

    /**
     * 查询道路运输证list
     *
     * @param mapData
     * @return
     */
    public Map<String, Object> dataGrid(Map<String, Object> mapData) {

        List<Map<String, Object>> list = beianRoadTransportDao.dataGrid(mapData);
        int getTotalCount = beianRoadTransportDao.getTotalCount(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }

    //添加
    public String addInfo(BEIAN_RoadTransportEntity entity) {
        Timestamp t = DateUtils.getSysTimestamp();
   /*     if (carService.validPlateNum(entity.getM3())) {
            BASIC_CarEntity car = new BASIC_CarEntity(entity.getM3());
            car.setS1(t);
            car.setS2(t);
            car.setS3(0);
            carService.addInfo(car);
        }//添加车辆信息*/
        String datasuccess = "success";
        beianRoadTransportDao.save(entity);
        return datasuccess;
    }

    //根据id查询详细信息
    public BEIAN_RoadTransportEntity findInforById(Long id) {
        return beianRoadTransportDao.findInforById(id);
    }

    //更新信息
    public String updateInfo(BEIAN_RoadTransportEntity entity) {
        String datasuccess = "success";
        beianRoadTransportDao.save(entity);
        return datasuccess;
    }

    //删除信息
    public void deleteInfo(long id) {
        beianRoadTransportDao.deleteInfo(id);
    }


    /**
     * 导出
     *
     * @param response
     * @param mapData
     */
    public void exportExcel(HttpServletResponse response,
                            Map<String, Object> mapData) {
        String fileName = "道路运输证表.xls";
        List<Map<String, Object>> list = beianRoadTransportDao.getExportInfo(mapData);
        for (Map<String, Object> map : list) {
            if (map.get("m9") != null) {//有效期
                Timestamp m9 = (Timestamp) map.get("m9");
                map.put("m9", new SimpleDateFormat("yyyy-MM-dd").format(m9));
            }
            if (map.get("m10") != null) {//有效期
                Timestamp m10 = (Timestamp) map.get("m10");
                map.put("m10", new SimpleDateFormat("yyyy-MM-dd").format(m10));
            }
        }
        String[] title = mapData.get("coltext").toString().split(",");
        String[] keys = mapData.get("colval").toString().split(",");
        new ExportExcel(fileName, title, keys, list, response, true);
    }

    public boolean validPlateNum(String plateNum) {
        List<BEIAN_RoadTransportEntity> list = beianRoadTransportDao.find(Restrictions.eq("S3", 0),
                Restrictions.eq("m3", plateNum));
        return list.size() == 0;
    }

    public boolean validReviewPlateNum(String plateNum) {
        List<BEIAN_RoadTransportEntity> list = beianRoadTransportDao.find(Restrictions.eq("S3", 0),
                Restrictions.eq("m3", plateNum), Restrictions.eq("state", 1));
        return list.size() == 0;
    }


    public Map<String, Object> exinExcel(Map<String, Object> map) {
        ShiroRealm.ShiroUser user = UserUtil.getCurrentShiroUser();
        int userid = user.getId();
        int state = 1;
//        if (user.getUsertype() == User.USERTYPE.QY.getUsertype()) {
//            state = 0;
//        }
        Map<String, Object> resultmap = new HashMap<String, Object>();
        int result = 0;
        ExinExcel exin = new ExinExcel();
        List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
        List<String> yhNameList = beianRoadTransportDao.getYhName();
        int i = 0, error = 0;
        if (list.size() > 3) {
            result = 0;
            resultmap.put("total", list.size() - 3);
            resultmap.put("returncode", "success");
            for (List<Object> bis : list) {
                if (i <= 2) { //跳过前三行
                    i++;
                    continue;
                }
                try {
                    BEIAN_RoadTransportEntity ws = new BEIAN_RoadTransportEntity();
                    Timestamp t = DateUtils.getSysTimestamp();
                    if(yhNameList.contains(bis.get(0).toString())==false) {
                    ws.setS1(t);
                    ws.setS2(t);
                    ws.setS3(0);
                    ws.setM1(bis.get(0).toString());//业户姓名
                    ws.setM2(bis.get(1).toString());//地址
                    ws.setM3(bis.get(2).toString());//车牌号码
                    ws.setM4(bis.get(3).toString());//道路运输证号
                    ws.setM5(bis.get(4).toString());//车辆类型
                    ws.setM6(bis.get(5).toString());//吨(座)位
                    ws.setM7(bis.get(6).toString());//车辆(毫米)

                    ws.setM8(bis.get(7).toString());//经营范围
                    ws.setM9(StringUtils.isBlank(bis.get(8).toString()) ? null :
                            Timestamp.valueOf(bis.get(8).toString()));//发证日期
                    ws.setM10(StringUtils.isBlank(bis.get(9).toString()) ? null :
                            Timestamp.valueOf(bis.get(9).toString()));//有效期

                    ws.setM11(bis.get(10).toString());//备注
                    ws.setUserid(userid);
                    ws.setState(state);
                    beianRoadTransportDao.save(ws);
                    result++;
                    }
                } catch (Exception e) {
                    error++;
                }
                resultmap.put("success", result);
                resultmap.put("error", error);
            }
        } else if (list.size() == 3) {
            resultmap.put("success", result);
            resultmap.put("error", error);
            resultmap.put("returncode", "warn");
        } else if (list.size() < 3) {
            resultmap.put("success", result);
            resultmap.put("error", error);
            resultmap.put("returncode", "ext");
        }
        if (Integer.valueOf(resultmap.get("success").toString()) == 0) {
            resultmap.put("returncode", "warn");
        }
        return resultmap;

    }

    public List<Map<String, Object>> listAll() {
        String hql = "select a.m3,a.m4,b.m18 from beian_roadtransport a left join beian_drivingpermit b on a.m3 = b" +
                ".m1 where a.s3=0 and a.state=1  and ((b.s3 = 0 and b.state =1) or b.s3 is null) ";

        List<Map<String, Object>> list = beianRoadTransportDao.createSqlQuery(hql, null).
                setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        return list;
    }

    /*
    得到已经过期的道路经营许可证的ID集合
    */
    public List<Long> getExpiredId(){
        List<Long> list = beianRoadTransportDao.getExpiredId();
        return list;
    }
}
