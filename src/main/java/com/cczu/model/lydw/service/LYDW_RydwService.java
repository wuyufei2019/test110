package com.cczu.model.lydw.service;


import com.cczu.model.lydw.dao.LYDW_RydwDao;
import com.cczu.model.lydw.dao.Pub_fileRoomTimhisDao;
import com.cczu.model.lydw.entity.LYDW_BJJL;
import com.cczu.model.lydw.entity.LYDW_DZWL;
import com.cczu.model.lydw.entity.Pub_FileTime;
import com.cczu.model.lydw.entity.Pub_FileTimeHis;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 蓝牙定位-人员定位Service
 * @author jason
 * @date 2019年月9日
 */
@Transactional(readOnly=true)
@Service("LYDW_RydwService")
public class LYDW_RydwService {

	@Resource
	private LYDW_RydwDao lydw_rydwDao;

    @Resource
    private Pub_fileRoomTimhisDao pubFileRoomTimhisDao ;

    @Resource
    private LYDW_DzwlService lydw_dzwlService;

    /**
     * 增加/更新实时数据
     * @param pf
     */
    public void addInfo(Pub_FileTime pf) {
        lydw_rydwDao.save(pf);
    }

    /**
     * 增加历史数据
     * @param pf
     */
    public void addHisInfo(Pub_FileTimeHis pf) {
        pubFileRoomTimhisDao.save(pf);
    }

    /**
     * 通过标签号查询是否已有该数据存在
     * @param file
     * @return
     */
    public boolean findPubFileroomTime(String file){
        return lydw_rydwDao.findPubFileroomTime(file);
    }

    /**
     * 通过标签号获取数据
     * @param file
     * @return
     */
    public Pub_FileTime getPubFileroomTime(String file){
        return lydw_rydwDao.getPubFileroomTime(file);
    }

    /**
	 * 人员定位
	 * @return
	 */
	public String rydwData(Map<String, Object> mapData) {
		List<Map<String, Object>> list = lydw_rydwDao.rydwData(mapData);
		return JsonMapper.getInstance().toJson(list);
	}

    /**
     * 获取所有在线记录
     * @param
     * @return
     */
	public String getAllPubFileTime(){
	    List<Map<String, Object>> listMan = lydw_rydwDao.getAllPubFileTimeMan();
        List<Map<String, Object>> listCar = lydw_rydwDao.getAllPubFileTimeCar();
        listMan.addAll(listCar);
        Iterator<Map<String , Object>> it = listMan.iterator();
        while(it.hasNext()){
            Map<String, Object> item = it.next();
            if((int)item.get("type") == 0){
                item.put("id", "stuff_" + item.get("id"));
            }else{
                item.put("id", "truck_" + item.get("id"));
            }
            double[] pos = new double[]{(double)item.get("x"), (double)item.get("y"), (double)item.get("z"),};
            item.remove("x");
            item.remove("y");
            item.remove("z");
            item.put("position", pos);
            if((int)item.get("type") == 0){//解决前端代码type不能为0问题
                item.put("type" , 2);
            }

        }
	    return JsonMapper.getInstance().toJson(listMan);
    }

    /**
     * 根据tagid获取单条记录
     * @param tagid
     * @return
     */
    public String getByTagId(String tagid){
	    List<Map<String, Object>> list = lydw_rydwDao.getByTagId(tagid);
        Iterator<Map<String , Object>> it = list.iterator();
        while(it.hasNext()){
            Map<String, Object> item = it.next();
            if((int)item.get("type") == 0){
                item.put("id", "staff_" + item.get("id"));
            }else{
                item.put("id", "truck_" + item.get("id"));
            }
            double[] pos = new double[]{(double)item.get("x"), (double)item.get("y") , (double)item.get("z"),};
            item.remove("x");
            item.remove("y");
            item.remove("z");
            item.put("position", pos);
            if((int)item.get("type") == 0){//解决前端代码type不能为0问题
                item.put("type" , 2);
            }
        }
        return JsonMapper.getInstance().toJson(list);
    }

	/**
	 * 根据部门统计在线人数
	 */
	public String totalOnlinePoeple(Map<String, Object> mapData) {
		List<Map<String, Object>> list = lydw_rydwDao.totalOnlinePoeple2(mapData);
		return JsonMapper.getInstance().toJson(list);
	}

	/**
	 * 查询绑定工卡的人员
	 */
	public String getYGList(Map<String, Object> mapData) {
		List<Map<String, Object>> list = lydw_rydwDao.getYGList(mapData);
		return JsonMapper.getInstance().toJson(list);
	}


    /**
     * 查询人员历史轨迹
     * @param mapData
     * @return
     */
    public String getHisGjListMan(Map<String, Object> mapData){
        String sql1= "SELECT his.x,his.y,his.z FROM pub_filetimehis his "+
                "LEFT JOIN lydw_emp_pubfile pb on pb.fileid = his.tagid "
                + "WHERE pb.empid ='" + mapData.get("tagid") + "' AND" +
                " his.time BETWEEN '" + mapData.get("starttime") + "' AND '"+ mapData.get("endtime") +"' AND pb.emptype = 0 ORDER BY his.time";
        List<Map<String, Object>> path = lydw_rydwDao.findBySql(sql1,null,Map.class);
        Iterator<Map<String , Object>> it = path.iterator();
        List<double[]> pathArray = new ArrayList<>();
        while (it.hasNext()){
            double[] temp = new double[3];
            Map<String, Object> item = it.next();
            temp[0] = (double)item.get("x");
            temp[1] = (double)item.get("y") + 1;
            temp[2] = (double)item.get("z");
            pathArray.add(temp);
        }
        String sql2 = "SELECT ry.id id, ry.m1 name,ry.m4 carrer, gk.fileid tagid, gk.emptype type,dep.m1 depname,ss.x,ss.y,ss.z,ss.online online  FROM bis_employee ry " +
                "LEFT JOIN lydw_emp_pubfile gk on ry.id = gk.empid " +
                "LEFT JOIN pub_filetime ss on gk.fileid = ss.tagid " +
                "LEFT JOIN t_department dep on ry.ID4 = dep.id " +
                "WHERE (online=1 OR online=2) AND gk.emptype=0 AND ry.id = '" + mapData.get("tagid") + "'";
        List<Map<String, Object>> man = lydw_rydwDao.findBySql(sql2, null, Map.class);
        Map<String , Object> out = new HashMap<>();
        out.put("maninfo" , man);
        out.put("path" , pathArray);
        return JsonMapper.getInstance().toJson(out);
    }

    /**
     * 查询车辆历史轨迹
     * @param mapData
     * @return
     */
    public String getHisGjListCar(Map<String, Object> mapData){
        String sql1 = "SELECT his.x,his.y,his.z FROM pub_filetimehis his "+
                "LEFT JOIN lydw_emp_pubfile pb on pb.fileid = his.tagid "
                + "WHERE pb.empid ='" + mapData.get("tagid") + "' AND" +
                " his.time BETWEEN '" + mapData.get("starttime") + "' AND '"+ mapData.get("endtime") +"' AND pb.emptype = 1 ORDER BY his.time";
        List<Map<String,Object>> path = lydw_rydwDao.findBySql(sql1, null, Map.class);
        Iterator<Map<String , Object>> it = path.iterator();
        List<double[]> pathArray = new ArrayList<>();
        while (it.hasNext()){
            double[] temp = new double[3];
            Map<String, Object> item = it.next();
            temp[0] = (double)item.get("x");
            temp[1] = (double)item.get("y") + 1;
            temp[2] = (double)item.get("z");
            pathArray.add(temp);
        }
        String sql2 = "SELECT cl.id id, cl.m1 name, cl.m2 cartype, cl.m4 tel, gk.fileid tagid, gk.emptype type, cl.m3 depname,ss.x,ss.y,ss.z,ss.online online FROM bis_car cl " +
                "LEFT JOIN lydw_emp_pubfile gk on cl.id = gk.empid " +
                "LEFT JOIN pub_filetime ss on gk.fileid = ss.tagid " +
                "WHERE (online=1 OR online=2) AND gk.emptype=1 AND cl.id = '" + mapData.get("tagid") + "'";
        List<Map<String, Object>> car = lydw_rydwDao.findBySql(sql2, null, Map.class);
        Map<String , Object> out = new HashMap<>();
        out.put("maninfo" , car);
        out.put("path" , pathArray);
        return JsonMapper.getInstance().toJson(out);
    }

    public boolean findInDZWL(long tagid, double[] pos){

        return true;
    }

    /**
     * 根据实时定位信息 ，判断与围栏之间的信息 并报警
     * @param fileTime
     * @return
     */
    public List<LYDW_BJJL> alarmInfo(Pub_FileTime fileTime) {
        List<LYDW_BJJL> bjjlList = new ArrayList<>();
        //允许进入的围栏
        List<LYDW_DZWL> allow = lydw_dzwlService.findByZBID(fileTime.getTagId(), "1");
        if (allow.size() > 0) {
            for (LYDW_DZWL dzwl : allow) {
                //首先判断是否在同一楼层 再判断是否在区域中
                if (Float.valueOf(dzwl.getFloor()) !=fileTime.getY() || !lydw_dzwlService.isInclude(fileTime.getX(),fileTime.getZ(),dzwl)) {
                    bjjlList.add(getBjjl(fileTime,dzwl,"1","人员"+ getYgName(fileTime.getTagId()) +"离岗，未在"+dzwl.getName()+"围栏中"));
                }
            }
        }
        //禁止进入的围栏
        List<LYDW_DZWL> ban = lydw_dzwlService.findByZBID(fileTime.getTagId(), "2");
        if (ban.size() > 0) {
            for (LYDW_DZWL dzwl : ban) {
                //首先判断是否在同一楼层 再判断是否在区域中
                if (Float.valueOf(dzwl.getFloor()) ==fileTime.getY()&& lydw_dzwlService.isInclude(fileTime.getX(),fileTime.getZ(),dzwl)) {
                    bjjlList.add(getBjjl(fileTime,dzwl,"1","人员"+ getYgName(fileTime.getTagId()) +"串岗，出现在"+dzwl.getName()+"围栏中"));
                }
            }
        }
        return bjjlList;
    }

    /**
     * 根据 定位信息 围栏信息 生成报警信息
     * @param fileTime 定位信息
     * @param dzwl 围栏信息
     * @param bjType 报警类型
     * @param bjMessage 报警信息
     * @return
     */
    public LYDW_BJJL getBjjl(Pub_FileTime fileTime,LYDW_DZWL dzwl, String bjType, String bjMessage) {
        LYDW_BJJL lydwBjjl = new LYDW_BJJL();
        lydwBjjl.setType(bjType);
        lydwBjjl.setMsg(bjMessage);
        lydwBjjl.setXbid(fileTime.getTagId());
        lydwBjjl.setWlid(dzwl.getID());
        lydwBjjl.setX(fileTime.getX());
        lydwBjjl.setY(fileTime.getY());
        lydwBjjl.setZ(fileTime.getZ());
        lydwBjjl.setTime(DateUtils.getSystemTime());
        lydwBjjl.setStatus(0);
        return  lydwBjjl;
    }

    /**
     * 获取绑定工卡的车辆数据
     * @return
     */
    public String getCarList(){
        String sql = "SELECT cl.id id, cl.m1 num, cl.m3 drivers FROM bis_car cl " +
                "LEFT JOIN lydw_emp_pubfile emp ON cl.id = emp.empid " +
                "WHERE emp.emptype = 1";
        List<Map<String, Object>> list = lydw_rydwDao.findBySql(sql , null, Map.class);
        return JsonMapper.getInstance().toJson(list);
    }

    public String getYgName(String tagid){
        String sql = "SELECT ry.m1 name FROM bis_employee ry " +
                "LEFT JOIN lydw_emp_pubfile bp ON ry.id = bp.empid " +
                "WHERE bp.fileid = '" + tagid + "' AND bp.emptype = 0 UNION " +
                "SELECT car.m1 name FROM bis_car car " +
                "LEFT JOIN lydw_emp_pubfile bp ON car.id = bp.empid " +
                "WHERE bp.fileid = '" + tagid + "' AND bp.emptype = 1";
        List<Map<String, Object>> nameList = lydw_rydwDao.findBySql(sql, null, Map.class);
        if(nameList.size()>0){
            return (String)nameList.get(0).get("name");
        }else {
            return "";
        }
    }
}
