package com.cczu.model.service;

import com.cczu.model.dao.YszyTransGoodsDao;
import com.cczu.model.entity.BASIC_CarEntity;
import com.cczu.model.entity.YSZY_KkTaskDeclarationEntity;
import com.cczu.model.entity.YSZY_TransportationGoods;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.entity.Role;
import com.cczu.sys.system.service.MessageService;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.Constant;
import com.cczu.util.common.Parameter;
import com.google.common.collect.Maps;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description: 卡口作业申报Service
 * @author: wbth
 * @date: 2018年8月29日
 */
@Transactional(readOnly = true)
@Service("YszyKkzysbService")
public class YszyKkzysbService {
    @Resource
    private com.cczu.model.dao.YszyKkzysbDao YszyKkzysbDao;
    @Resource
    private YszyTransGoodsDao goodsDao;
    @Resource
    private BasicCarService carService;
    @Resource
    private MessageService messageService;


    /**
     * 查询卡口作业申报信息list
     *
     * @param mapData
     * @return
     */
    public Map<String, Object> dataGrid(Map<String, Object> mapData) {

        List<Map<String, Object>> list = YszyKkzysbDao.dataGrid(mapData);
        int getTotalCount = YszyKkzysbDao.getTotalCount(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }

    public void save(YSZY_KkTaskDeclarationEntity entity) {
        YszyKkzysbDao.save(entity);
    }

    //添加
    public String addInfo(YSZY_KkTaskDeclarationEntity entity, String[] whpNames, String[] uniteNationNums,
                          String[] tonnages, String[] types, String[] packageTypes, String[] specifications,
                          String[] hazardouswastenames, String[] hazardouswastetypes, String[] hazardouswastenums, String[] transferNumbers) {
        String datasuccess = "success";
        //验证是否已存在车辆
        if (carService.validPlateNum(entity.getPlateNum())) {
            BASIC_CarEntity car = new BASIC_CarEntity(entity.getPlateNum(),
                    entity.getDriverName(), entity.getDriverContact());
            car.setM13(entity.getPlateNumG());
            Timestamp t = DateUtils.getSysTimestamp();
            car.setS1(t);
            car.setS2(t);
            car.setS3(0);
            carService.addInfo(car);
        }

        entity.setState(YSZY_KkTaskDeclarationEntity.ORDER_STATE_UNREVIEW);//设置订单状态为未审核
        //设置用户id
        entity.setUserid(UserUtil.getCurrentShiroUser().getId());
        entity.setNumber(DateUtils.getDate("yyMMddhhmmsss"));
        YszyKkzysbDao.save(entity);
        Map msgMap = Maps.newHashMap();
        String userType = "0";
        if (entity.getType() == YSZY_KkTaskDeclarationEntity.TYPE_HB) {
            userType = "3";
        } else if (entity.getType() == YSZY_KkTaskDeclarationEntity.TYPE_GA) {
            userType = "4";
        }
        msgMap.put("usertype", userType);
        String whp = "";
        for (String whpName : whpNames) {
            whp += whpName + ",";
        }

        Message message = new Message(null, UserUtil.getCurrentShiroUser().getId().toString(), "有新的运单待审核，请及时处理！",
                null, "货物名称：" + whp.substring(0, whp.length() - 1),
                "yszy/kkzysb/index");
        message.setCREATETIME(DateUtils.getSystemTime());
        message.setSENDSTATUE("0");
        messageService.addMultiUserMsg(message, msgMap);
        try {
            for (int i = 0; i < whpNames.length; i++) {
                YSZY_TransportationGoods goods = new YSZY_TransportationGoods();
                goods.setTransId(entity.getID());
                if(entity.getType().equals(YSZY_KkTaskDeclarationEntity.TYPE_HB)){
                    goods.setHazardouswastename(hazardouswastenames[i]);
                    goods.setHazardouswastetype(hazardouswastetypes[i]);
                    goods.setHazardouswastenum(hazardouswastenums[i]);
                    goods.setTransferNumber(transferNumbers[i]);
                }else{
                    goods.setUniteNationNum(uniteNationNums[i]);
                }
                goods.setPackageType(packageTypes[i]);
                goods.setSpecification(specifications[i]);
                goods.setTonnage(StringUtils.isNotEmpty(tonnages[i]) ? Float.parseFloat(tonnages[i]) : 0);
                if (types != null)
                    goods.setType(StringUtils.isNotEmpty(types[i]) ? types[i] : "");
                goods.setWhpName(whpNames[i]);
                goodsDao.save(goods);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datasuccess;
    }

    //根据id查询详细信息
    public YSZY_KkTaskDeclarationEntity findInforById(Long id) {
        return YszyKkzysbDao.findInforById(id);
    }

    //根据订单编号查询详细信息
    public YSZY_KkTaskDeclarationEntity findInforByOrderNum(String orderNum) {
        YSZY_KkTaskDeclarationEntity entity = YszyKkzysbDao.findUniqueBy("number", orderNum);
        return entity;
    }

    public List<YSZY_KkTaskDeclarationEntity> findAllInfo() {
        return YszyKkzysbDao.findAllInfo();
    }

    //更新信息
    @Transactional(readOnly = false)
    public String updateInfo(YSZY_KkTaskDeclarationEntity entity, String[] whpNames, String[] uniteNationNums, String[] tonnages, String[] types,
                             String[] packageTypes, String[] specifications,
                             String[] hazardouswastenames, String[] hazardouswastetypes, String[] hazardouswastenums, String[] transferNumbers) {

        String datasuccess = "success";
        //验证是否已存在车辆
        Timestamp t = DateUtils.getSysTimestamp();
        if (carService.validPlateNum(entity.getPlateNum())) {
            BASIC_CarEntity car = new BASIC_CarEntity(entity.getPlateNum(),
                    entity.getDriverName(), entity.getDriverContact());
            car.setS1(t);
            car.setS2(t);
            car.setS3(0);
            carService.addInfo(car);
        }
        entity.setS2(t);
        entity.setS3(0);
        entity.setState(YSZY_KkTaskDeclarationEntity.ORDER_STATE_UNREVIEW);
        YszyKkzysbDao.save(entity);
        goodsDao.deleteAllByTransId(entity.getID());
        try {
            for (int i = 0; i < whpNames.length; i++) {
                YSZY_TransportationGoods goods = new YSZY_TransportationGoods();
                goods.setTransId(entity.getID());
                goods.setPackageType(packageTypes[i]);
                goods.setSpecification(specifications[i]);
                goods.setTonnage(StringUtils.isNotBlank(tonnages[i]) ? Float.parseFloat(tonnages[i]) : 0);
                goods.setType(types[i]);
                goods.setWhpName(whpNames[i]);
                if(entity.getType().equals(YSZY_KkTaskDeclarationEntity.TYPE_HB)){
                    goods.setHazardouswastename(hazardouswastenames[i]);
                    goods.setHazardouswastetype(hazardouswastetypes[i]);
                    goods.setHazardouswastenum(hazardouswastenums[i]);
                    goods.setTransferNumber(transferNumbers[i]);
                }else{
                    goods.setUniteNationNum(uniteNationNums[i]);
                }
                goodsDao.save(goods);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datasuccess;
    }

    //删除信息
    @Transactional(readOnly = false)
    public void deleteInfo(long id) {
        goodsDao.deleteAllByTransId(id);
        YszyKkzysbDao.deleteInfo(id);
    }

    //获取订单未完成的车辆
    public List<Map<String, Object>> findByPlateNumOnline(String plateNum, String number) {
        String sql = "select a.s1 as createtime,a.id ,a.plate_num plateNum ,a.number,b.score from " +
                "yszy_kktaskdeclaration a left join basic_car b on a.plate_num = b.m3" +
                " where a.s3=0 and b.s3=0 and a.state !=?0";
        if (StringUtils.isNotBlank(plateNum))
            sql += " and a.plate_num like '%" + plateNum + "%'";
        if (StringUtils.isNotBlank(number))
            sql += " and a.number = '" + number + "'";

        List<Map<String, Object>> list = YszyKkzysbDao.createSQLQuery(sql,
                YSZY_KkTaskDeclarationEntity.ORDER_STATE_FINISH)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        return list;
    }

    //获取订单未完成的车辆
    public List<YSZY_KkTaskDeclarationEntity> listUnReviewExpiration(String duration) {
        List<YSZY_KkTaskDeclarationEntity> list = null;
        try {
            Criteria criteria = YszyKkzysbDao.createCriteria(Restrictions.eq("S3", 0),
                    Restrictions.eq("state", YSZY_KkTaskDeclarationEntity.ORDER_STATE_UNREVIEW),
                    Restrictions.eq("type", Integer.parseInt(UserUtil.getCurrentShiroUser().getUsertype())));
            if (StringUtils.isNotBlank(duration)) {
                criteria.add(Restrictions.le("S1",
                        DateUtils.addHours(DateUtils.getNow(), -(Integer.parseInt(duration)))));
            }
            list = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //获取订单未完成的车辆
    public List<YSZY_KkTaskDeclarationEntity> listUnReview(String name) {
        String sql = "select a.* from yszy_kktaskdeclaration a where a.s3=0 and a.state =?0 and a.type=?1";
        if (StringUtils.isNotBlank(name))
            sql += " and (a.entrust_Company like '%" + name + "%' or a.consignee_Company like '%" + name + "%')";

        List<YSZY_KkTaskDeclarationEntity> list = YszyKkzysbDao.createSQLQuery(sql,
                YSZY_KkTaskDeclarationEntity.ORDER_STATE_UNREVIEW, UserUtil.getCurrentShiroUser().getUsertype())
                .addEntity(YSZY_KkTaskDeclarationEntity.class).list();

        return list;
    }

    /**
     * 导出
     *
     * @param response
     * @param mapData
     */
    public void exportExcel(HttpServletResponse response,
                            Map<String, Object> mapData) {
        String fileName = "卡口作业申报表.xls";
        List<Map<String, Object>> list = YszyKkzysbDao.getExportInfo(mapData);
        String[] title = mapData.get("coltext").toString().split(",");
        String[] keys = mapData.get("colval").toString().split(",");
        new ExportExcel(fileName, title, keys, list, response, true);
    }

    public String bindLicencePlate(String licencePlate) {
        List<YSZY_KkTaskDeclarationEntity> declarationList = YszyKkzysbDao.find(
                Restrictions.eq("S3", 0),
                Restrictions.eq("licencePlate", licencePlate),
                Restrictions.eq("state", YSZY_KkTaskDeclarationEntity.ORDER_STATE_UNFINISH));
        if (declarationList.size() > 0) {
            YSZY_KkTaskDeclarationEntity newest = declarationList.get(0);
            newest.setState(YSZY_KkTaskDeclarationEntity.ORDER_STATE_BIND);
            YszyKkzysbDao.save(newest);
            return Constant.SUCCESS;
        } else
            return Constant.FAILED;
    }

    /**
     * 获得该单号的历史轨迹
     */
    public List<Map<String, Object>> historicalRoute(String orderNum) {
        String sql = " select lon , lat from t_gps where ordernum = :p1 ORDER BY time ";
        Parameter parameter = new Parameter(orderNum);
        return YszyKkzysbDao.findBySql(sql, parameter, Map.class);
    }


    public void updateOrderState(String number, int state) {
        YszyKkzysbDao.updateOrderState(number, state);
    }

    public List<YSZY_KkTaskDeclarationEntity> getOrderNotBind() {
        Criteria criteria = YszyKkzysbDao.createCriteria(Restrictions.eq("S3", 0),
                Restrictions.eq("state", YSZY_KkTaskDeclarationEntity.ORDER_STATE_PASS));
        criteria.addOrder(Order.asc("predictArriveTime"));
        List<YSZY_KkTaskDeclarationEntity> list = criteria.setCacheable(true).list();
        return list;
    }

    public List<YSZY_KkTaskDeclarationEntity> getOrderNotBindOrCheck() {
        Criteria criteria = YszyKkzysbDao.createCriteria(Restrictions.eq("S3", 0),
                Restrictions.in("state", Arrays.asList(YSZY_KkTaskDeclarationEntity.ORDER_STATE_BIND,
                        YSZY_KkTaskDeclarationEntity.ORDER_STATE_PASS)));
        List<YSZY_KkTaskDeclarationEntity> list = criteria.setCacheable(true).list();
        return list;
    }

    public List<YSZY_TransportationGoods> listByOrderNum(String orderNum) {
        return YszyKkzysbDao.listByOrderNum(orderNum);
    }

    /*
    根据登陆者id得到登陆者的角色编码
     */
    public List<Role> getUserRoleCode(int id){
       List<Role> userList =  YszyKkzysbDao.getUserRoleCode(id);
        return userList;
    }


    /*
    得到已经超过预计到达时间两天的运单ID集合
    */
    public List<Long> getYdId(){
        List<Long> list = YszyKkzysbDao.getYdId();
        return list;
    }
}
