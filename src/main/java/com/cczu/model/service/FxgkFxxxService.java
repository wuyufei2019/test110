package com.cczu.model.service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.cczu.sys.system.dao.DepartmentDao;
import com.cczu.sys.system.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.FxgkFxxxDao;
import com.cczu.model.dao.FxgkFxzpzDao;
import com.cczu.model.dao.FxgkTjfxDao;
import com.cczu.model.dao.ITic_AccidentHandleDao;
import com.cczu.model.dao.YhpcRiskPointContentDao;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.FXGK_AccidentRisk;
import com.cczu.model.entity.FXGK_RiskPerEntity;
import com.cczu.model.entity.Tdic_AccidentHandle;
import com.cczu.model.entity.YHPC_RiskPoint_Content;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.dao.BarrioDao;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 
 * @author jason
 * @date 2017年8月8日
 */
@Transactional(readOnly=true)
@Service("SxgkfxxxService")
public class FxgkFxxxService {
	@Resource
	private FxgkFxxxDao fxgkFxxxDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Resource
	private BarrioDao barrioDao;
	@Autowired
	private IBisQyjbxxService bisQyjbxxService;
	@Resource
	private ITic_AccidentHandleDao accidentHandleDao;
	@Resource
	private YhpcRiskPointContentDao yhpcRiskPointContentDao;
	@Resource
	private FxgkFxzpzDao fxgkfxzpzdao;
	@Resource
	private FxgkTjfxDao fxgkTjfxDao;
	@Resource
	private YhpcRiskPointContentDao yhpcriskpointcontentdao;
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=fxgkFxxxDao.dataGrid(mapData);
		//System.out.println(list);
		int getTotalCount=fxgkFxxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	public String getAllType() {
		// TODO Auto-generated method stub
		List<Tdic_AccidentHandle> list=accidentHandleDao.findAllList();
		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
		for(Tdic_AccidentHandle dict:list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", dict.getID());
			
			map.put("text", dict.getM1());
			map.put("extra", dict.getM2());
			arrylist.add(map);
		}
		
		return JsonMapper.getInstance().toJson(arrylist); 
		  
	}
	
	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"企业名称","风险点名称","所在位置","主要危险因素","可能导致事故类型","风险等级","辨识时间","主要方法措施","主要依据","责任部门","责任人","责任人电话","可能性(L)","暴露频率(E)","严重性(C)","风险值","公司","部门","班组","岗位" };  
		String[] keys={"qyname","m1","areaname","m7","m8","fxfj","rq","m10","m12","depart","m13","m14","aprobability","exposefrequency","aseverity","fxz","fjgk1","fjgk2","fjgk3","fjgk4"};
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = (mapData.get("coltext").toString()).split(",") ;
			keys = (mapData.get("colval").toString()).split(",") ;
		}
		String fileName="企业危险因素辨识与防范控制措施表.xls";
		List<Map<String, Object>> list=fxgkFxxxDao.getExport(mapData);
		new ExportExcel(fileName, title, keys, list, response,true);

	}
	
	public List<Map<String, Object>> getAllByQyid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=fxgkFxxxDao.getAllByQyid(mapData);
		return list;
	}
	
	public String addInfo(FXGK_AccidentRisk sgfx,String bdnrids) {
		String datasuccess = "success";

		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			sgfx.setID1(UserUtil.getCurrentShiroUser().getQyid());
		}
		sgfx.setS1(new java.sql.Timestamp(new java.util.Date().getTime()));
		sgfx.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		sgfx.setS3(0);
		
		String bindcontent = sgfx.getBindcontent();
		String rfid = sgfx.getRfid();
		//判断绑定二维码rfid是否重复
		if(checkSameBuildContent(0, bindcontent)&&(!com.cczu.util.common.StringUtils.isEmpty(bindcontent))){
			datasuccess = "ewmerror";
		}else if(checkSameRfid(0,rfid)&&(!com.cczu.util.common.StringUtils.isEmpty(rfid))){
			datasuccess = "rfiderror";
		}else{
			try {
				fxgkFxxxDao.save(sgfx);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				datasuccess="failed";
			}
		}
		long id=sgfx.getID();
		//风险点添加成功后，绑定巡检内容
		if(id>0){
			String[] arrids = bdnrids.split(",");
			try{
				for (int i = 0; i < arrids.length; i++) {
					bulidCheckContent(id,Long.parseLong(arrids[i]));
				}
			}catch(Exception e){
				datasuccess="bderror";
			}
		}
		updateQyfxdj(sgfx.getID1());
		// 返回结果
		return datasuccess;
	}
	public long addInfoReturnID(FXGK_AccidentRisk sgfx) {
		fxgkFxxxDao.save(sgfx);
		return sgfx.getID();
	}

	public String updateInfo(FXGK_AccidentRisk sgfx,String bdnrids) {
		String datasuccess = "success";
		if("1".equals(UserUtil.getCurrentShiroUser().getUsertype())){
			sgfx.setID1(UserUtil.getCurrentShiroUser().getQyid());
		}
		sgfx.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		String bindcontent = sgfx.getBindcontent();
		String rfid = sgfx.getRfid();
		long id=sgfx.getID();
		//判断绑定二维码rfid是否重复
		if(checkSameBuildContent(id, bindcontent)&&(!com.cczu.util.common.StringUtils.isEmpty(bindcontent))){
			datasuccess = "ewmerror";
		}else if(checkSameRfid(id,rfid)&&(!com.cczu.util.common.StringUtils.isEmpty(rfid))){
			datasuccess = "rfiderror";
		}else{
			try {
				fxgkFxxxDao.updateInfo(sgfx);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				datasuccess="failed";
			}
		}
		//风险点update后,删除绑定内容重新绑定
		yhpcriskpointcontentdao.deleteInfoByID1(sgfx.getID());
		String[] arrids = bdnrids.split(",");
		
		int len=arrids.length;
		try{
			for (int i = 0; i < len; i++) {
				bulidCheckContent(id,Long.parseLong(arrids[i]));
			}
		}catch(Exception e){
			datasuccess="bderror";
		}
		//更新企业风险等级
		updateQyfxdj(sgfx.getID1());
		// 返回结果
		return datasuccess;
	}
	
	public void deleteInfo(long id) {
		fxgkFxxxDao.deleteInfo(id);
	}

	public FXGK_AccidentRisk findById(Long id) {
		return fxgkFxxxDao.find(id);
	}
	
	public FXGK_AccidentRisk find_sgfx_ById(Long id) {
		return fxgkFxxxDao.findUniqueBy("ID", id);
	}
	
	public boolean checkSameBuildContent(long  fxxxid,String bindcontent) {
		return fxgkFxxxDao.checkSameBuildContent(fxxxid, bindcontent);
	}
	
	public boolean checkSameRfid(long  fxxxid,String rfid) {
		return fxgkFxxxDao.checkSameRfid(fxxxid,rfid);
	}
	
	/**
	 * 根据id查询风险点详细信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		return fxgkFxxxDao.findInforById(id);
	}
	/**
	* 根据qyid查询风险点详细信息
	* @return
			*/
	public List<Map<String, Object>> getFXByqyid(Long id) {
		return fxgkFxxxDao.getFXByqyid(id);
	}

	public Map<String, Object> xjnrdataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=fxgkFxxxDao.xjnrdataGrid(mapData);
		int getTotalCount=fxgkFxxxDao.getxjnrTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public Map<String, Object> xjnrdataGrid2(Map<String, Object> mapData) {
		List<Map<String, Object>> list=fxgkFxxxDao.xjnrdataGrid2(mapData);
		int getTotalCount=fxgkFxxxDao.getxjnrTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	public Map<String, Object> xjnrdataGrid3(Map<String, Object> mapData) {
		List<Map<String, Object>> list=new ArrayList<>();
		int getTotalCount=0;
		if(mapData.get("type").equals("fxd")){
			list=fxgkFxxxDao.xjnrdataGrid3(mapData);
			getTotalCount=fxgkFxxxDao.getxjnrTotalCount2(mapData);
		}else if(mapData.get("type").equals("yhd")){
			list=fxgkFxxxDao.xjnrdataGrid4(mapData);
			getTotalCount=fxgkFxxxDao.getxjnrTotalCount2(mapData);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void deleteXjnr(long id) {
		fxgkFxxxDao.deleteXjnr(id);
	}

	public Map<String, Object> xjnralldataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=fxgkFxxxDao.xjnralldataGrid(mapData);
		int getTotalCount=fxgkFxxxDao.getxjnrallTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	public void bulidCheckContent(long id1, long id2) {
		YHPC_RiskPoint_Content y = new YHPC_RiskPoint_Content();
		y.setID1(id1);
		y.setID2(id2);
		yhpcRiskPointContentDao.save(y);
	}

	//修改企业风险等级
	public void updateQyfxdj(long id) {
		BIS_EnterpriseEntity bis = bisQyjbxxService.findInfoById(id);
		FXGK_RiskPerEntity riskp=fxgkfxzpzdao.findInfor();
		Map<String,Object> mapdata = new HashMap<>();
		mapdata.put("qyid",id);
		List<Map<String, Object>> listm=fxgkTjfxDao.findMapList(mapdata);
		if(listm.size()>0){
			Map<String,Object> map = listm.get(0);
			if(map.get("m1")!=null && !map.get("m1").toString().equals("")){
				int Rhong=(int)map.get("hong");
				int Rcheng=(int)map.get("cheng");
				int Rhuang=(int)map.get("huang");
				int Rlan=(int)map.get("lan");
				float level=(float)(Math.round((riskp.getM1()*Rhong+riskp.getM2()*Rcheng+riskp.getM3()*Rhuang+riskp.getM4()*Rlan)*10))/10;
				String yanse="";
				if(level>=riskp.getM5()){
					yanse="1";
				}else if(level>=riskp.getM6()){
					yanse="2";
				}else if(level>=riskp.getM7()){
					yanse="3";
				}else if(level<riskp.getM7()){
					yanse="4";
				}
				bis.setM44(yanse);
			}
		}else{
			bis.setM44("4");
		}
		bisQyjbxxService.updateInfo(bis);
	}
	//获取所有企业的风险值
	public List<Map<String, Object>> getQyfxzJson(Map<String, Object> mapdata) {
		FXGK_RiskPerEntity riskp=fxgkfxzpzdao.findInfor();
		List<Map<String, Object>> listm=fxgkTjfxDao.findMapList(mapdata);
		if(listm.size()>0){
			for(Map<String, Object> map:listm){
				Object lng=map.get("m16");
				Object lat=map.get("m17");
				int Rhong=(int)map.get("hong");
				int Rcheng=(int)map.get("cheng");
				int Rhuang=(int)map.get("huang");
				int Rlan=(int)map.get("lan");
				int count =(int) (riskp.getM1()*Rhong+riskp.getM2()*Rcheng+riskp.getM3()*Rhuang+riskp.getM4()*Rlan);
				map.clear();
				count=count<100?100:count;
				map.put("count", count);
				map.put("lng", lng);
				map.put("lat", lat);
			}
		}
		return listm;
	}
	//获取所有网格的风险值
	public Map<String,Object> getBarriofxzJson(Map<String, Object> mapdata) {
		Map<String,Object> map = new HashMap<>();
		List<Map<String,Object>> list=null;
		int len =0;
		int max =0;
		int min =0;
		try {
			list = fxgkTjfxDao.findBarrioMapList(mapdata);
			len = list.size();
			if(len==0)
				min=0;
			else
				min= (int)list.get(0).get("COUNT");
			max= min;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(len > 0){
			for(int i=0;i<len;i++){
				//返回该节点 和下一层节点
				if((int)list.get(i).get("COUNT")>max){
					max=(int)list.get(i).get("COUNT");
				}else if((int)list.get(i).get("COUNT")<min){
					min=(int)list.get(i).get("COUNT");
				}
				if(i == (len-1) || !list.get(i).get("lv").equals(list.get(i+1).get("lv"))){
					list = list.subList(0, i+1);
					break;
				}
			}
			map.put("min", min);
			map.put("max", max);
			map.put("list", list);
		}
		return map;
	}
	
	
	/**
	 * 分页显示 app
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridForApp(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=fxgkFxxxDao.dataGridForApp(mapData);
		//System.out.println(list);
		int getTotalCount=fxgkFxxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 五位一体导入
	 * @param map
	 *
	 */
    /**
     * 导入
     * @param map
     *
     */
    public Map<String,Object> exinExcel(Map<String, Object> map) {
        ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
        User user=UserUtil.getCurrentUser();//获取当前登录用户的对象
        Map<String,Object> resultmap = new HashMap<String, Object>();
        int result = 0;
        ExinExcel exin = new ExinExcel();
        List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
        int i = 0, error = 0;
        if (list.size() > 3) {
            result = 0;
            resultmap.put("total", list.size()-3);
            resultmap.put("returncode", "success");
            for (List<Object> bis : list) {
                if(i<=2){ //跳过前三行
                    i++;
                    continue;
                }
                try{
                    FXGK_AccidentRisk fxgk = new FXGK_AccidentRisk();

                    fxgk.setID1(Long.valueOf(map.get("qyid").toString()));
                    if(sessionuser.getDep()!=null)
                        fxgk.setDepid(sessionuser.getDep().getId());//部门id
                    Timestamp t = DateUtils.getSysTimestamp();
                    fxgk.setS1(t);
                    fxgk.setS2(t);
                    fxgk.setS3(0);
                    fxgk.setBindcontent(UUID.randomUUID().toString().replaceAll("-", ""));
                    fxgk.setM1(replaceBlank(bis.get(0).toString()));
                    fxgk.setM18(replaceBlank(bis.get(1).toString()));
                    fxgk.setM13(replaceBlank(bis.get(2).toString()));
                    fxgk.setM14(replaceBlank(bis.get(3).toString()));
                    fxgk.setM2(replaceBlank(bis.get(4).toString()));

                    String ys = replaceBlank(bis.get(5).toString());
                    String m9 = "";
                    if(ys.equals("红"))
                        m9 = "1";
                    else if(ys.equals("橙"))
                        m9 = "2";
                    else if(ys.equals("黄"))
                        m9 = "3";
                    else if(ys.equals("蓝"))
                        m9 = "4";
                    fxgk.setM9(m9);//风险分级

                    fxgk.setAreaname(replaceBlank(bis.get(6).toString()));
                    fxgk.setM3(replaceBlank(bis.get(7).toString()));
                    fxgk.setM4(replaceBlank(bis.get(8).toString()));
                    fxgk.setM5(replaceBlank(bis.get(9).toString()));
                    fxgk.setM6(replaceBlank(bis.get(10).toString()));
                    fxgk.setM8(replaceBlank(bis.get(11).toString()));
                    fxgk.setM7(replaceBlank(bis.get(12).toString()));
                    fxgk.setM10(replaceBlank(bis.get(13).toString()));
                    fxgk.setM12(replaceBlank(bis.get(14).toString()));
                    fxgk.setM11(replaceBlank(bis.get(15).toString()));

                    String gs=bis.get(16).toString();
                    String bm=bis.get(17).toString();
                    String bz=bis.get(18).toString();
                    String gw=bis.get(19).toString();
                    String gkcj="";
                    if(StringUtils.isNotBlank(gs)){
                        fxgk.setFjgk1(gs);
                        gkcj+="公司,";
                    }
                    if(StringUtils.isNotBlank(bm)){
                        fxgk.setFjgk2(bm);
                        gkcj+="部门,";
                    }
                    if(StringUtils.isNotBlank(bz)){
                        fxgk.setFjgk3(bz);
                        gkcj+="班组,";
                    }
                    if(StringUtils.isNotBlank(gw)){
                        fxgk.setFjgk4(gw);
                        gkcj+="岗位,";
                    }
                    if(StringUtils.isNotBlank(gkcj)){
                        gkcj=gkcj.substring(0,gkcj.length()-1);
                    }
                    fxgk.setM15(gkcj);

                    fxgkFxxxDao.save(fxgk);
                    result++;
                }catch(Exception e){
                    error++;
                }
                resultmap.put("success",result);
                resultmap.put("error", error);
            }
            //更新企业风险等级
            updateQyfxdj(Long.valueOf(map.get("qyid").toString()));
        }else if(list.size()==3){
            resultmap.put("success",result);
            resultmap.put("error", error);
            resultmap.put("returncode", "warn");
        }else if(list.size()<3){
            resultmap.put("success",result);
            resultmap.put("error", error);
            resultmap.put("returncode", "ext");
        }
        if(Integer.valueOf(resultmap.get("success").toString())==0){
            resultmap.put("returncode", "warn");
        }
        return resultmap;

    }

    /**
     * 淮化风险辨识导入
     * @param map
     *
     */
    public Map<String,Object> exinExcel2(Map<String, Object> map) {
        ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
        User user=UserUtil.getCurrentUser();//获取当前登录用户的对象
        Map<String,Object> resultmap = new HashMap<String, Object>();

		//查找所有部门
		Map<String ,Object> map2=new HashMap<>();
		map2.put("id2", UserUtil.getCurrentShiroUser().getQyid());
		List<Department> deplist=departmentDao.findJson(map2);

        int result = 0;
        ExinExcel exin = new ExinExcel();
        List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
        int i = 0, error = 0;
        if (list.size() > 3) {
            result = 0;
            resultmap.put("total", list.size()-3);
            resultmap.put("returncode", "success");
            for (List<Object> bis : list) {
                if(i<=2){ //跳过前三行
                    i++;
                    continue;
                }
                try{
                    FXGK_AccidentRisk fxgk = new FXGK_AccidentRisk();

                    fxgk.setID1(Long.valueOf(map.get("qyid").toString()));
                    Timestamp t = DateUtils.getSysTimestamp();
                    fxgk.setS1(t);
                    fxgk.setS2(t);
                    fxgk.setS3(0);
                    fxgk.setBindcontent(UUID.randomUUID().toString().replaceAll("-", ""));
                    fxgk.setM1(replaceBlank(bis.get(0).toString()));
					for (Department dep : deplist) {
						String depname=bis.get(1).toString();//模版里的部门名称
						if(depname.equals(dep.getM1())&&StringUtils.isNotBlank(dep.getM1())){
							fxgk.setDepid(dep.getId());//部门ID
							fxgk.setM18(replaceBlank(bis.get(1).toString()));
							break;
						}
					}
					if(fxgk.getDepid()==null) {
						error++;
						continue;
					}
                    fxgk.setM6(replaceBlank(bis.get(2).toString()));
                    fxgk.setM7(replaceBlank(bis.get(3).toString()));
                    fxgk.setM8(replaceBlank(bis.get(4).toString()));
                    fxgk.setAprobability(replaceBlank(bis.get(5).toString()));
                    fxgk.setAseverity(replaceBlank(bis.get(6).toString()));
                    fxgk.setM10(replaceBlank(bis.get(8).toString()));
                    fxgk.setM12(replaceBlank(bis.get(9).toString()));
                    fxgk.setM11(replaceBlank(bis.get(10).toString()));
                    fxgk.setFjgk1(replaceBlank(bis.get(11).toString()));
                    fxgk.setFjgk2(replaceBlank(bis.get(12).toString()));
                    fxgk.setFjgk4(replaceBlank(bis.get(13).toString()));
                    fxgk.setFjgk3(replaceBlank(bis.get(14).toString()));

                    String ys = replaceBlank(bis.get(7).toString());
                    String m9 = "";
                    if(ys.equals("红"))
                        m9 = "1";
                    else if(ys.equals("橙"))
                        m9 = "2";
                    else if(ys.equals("黄"))
                        m9 = "3";
                    else if(ys.equals("蓝"))
                        m9 = "4";
                    fxgk.setM9(m9);//风险分级

                    String gs=bis.get(11).toString();
                    String bm=bis.get(12).toString();
                    String bz=bis.get(13).toString();
                    String gw=bis.get(14).toString();
                    String gkcj="";
                    if(StringUtils.isNotBlank(gs)){
                        fxgk.setFjgk1(gs);
                        gkcj+="公司,";
                    }
                    if(StringUtils.isNotBlank(bm)){
                        fxgk.setFjgk2(bm);
                        gkcj+="责任单位,";
                    }
                    if(StringUtils.isNotBlank(bz)){
                        fxgk.setFjgk4(bz);
                        gkcj+="生产车间,";
                    }
                    if(StringUtils.isNotBlank(gw)){
                        fxgk.setFjgk3(gw);
                        gkcj+="班组,";
                    }
                    if(StringUtils.isNotBlank(gkcj)){
                        gkcj=gkcj.substring(0,gkcj.length()-1);
                    }
                    fxgk.setM15(gkcj);

                    fxgkFxxxDao.save(fxgk);
                    result++;
                }catch(Exception e){
                    error++;
                }
            }
			resultmap.put("success",result);
			resultmap.put("error", error);
            //更新企业风险等级
            updateQyfxdj(Long.valueOf(map.get("qyid").toString()));
        }else if(list.size()==3){
            resultmap.put("success",result);
            resultmap.put("error", error);
            resultmap.put("returncode", "warn");
        }else if(list.size()<3){
            resultmap.put("success",result);
            resultmap.put("error", error);
            resultmap.put("returncode", "ext");
        }
        if(Integer.valueOf(resultmap.get("success").toString())==0){
            resultmap.put("returncode", "warn");
        }
        return resultmap;

    }

    //过滤导入数据
	public String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 根据企业id和风险分级查询，返回json数据
	 * @param mapData
	 * @return
	 */
	public String getJsonByMap(Map<String, Object> mapData) {
		return JsonMapper.toJsonString(fxgkFxxxDao.findListByMap(mapData));
	}

	/**
	 * 根据企业id和风险分级查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getListByMap(Map<String, Object> mapData) {
		return fxgkFxxxDao.findListByMap(mapData);
	}

	/**
	 * 根据qyid获取风险点数量
	 * @param qyid
	 * @return
	 */
	public Map<String, Object> getFxdCount(Long qyid) {
		return fxgkFxxxDao.getFxdCount(qyid);
	}

	/**
	 * 根据qyid获取企业风险等级值
	 * @param qyid
	 */
	public Map<String, Object> getQyFxdjzByQyid(long qyid) {
		Map<String, Object> result = new HashMap<>();
		BIS_EnterpriseEntity bis = bisQyjbxxService.findInfoById(qyid);
		FXGK_RiskPerEntity riskp=fxgkfxzpzdao.findInfor();
		Map<String,Object> mapdata = new HashMap<>();
		mapdata.put("qyid",qyid);
		List<Map<String, Object>> listm=fxgkTjfxDao.findMapList(mapdata);
		if(listm.size()>0) {
			Map<String, Object> map = listm.get(0);
			if (map.get("m1") != null && !map.get("m1").toString().equals("")) {
				int Rhong = (int) map.get("hong");
				int Rcheng = (int) map.get("cheng");
				int Rhuang = (int) map.get("huang");
				int Rlan = (int) map.get("lan");
				float level = (float) (Math.round((riskp.getM1() * Rhong + riskp.getM2() * Rcheng + riskp.getM3() * Rhuang + riskp.getM4() * Rlan) * 10)) / 10;
				String yanse = "";
				if (level >= riskp.getM5()) {
					yanse = "红色风险";
				} else if (level >= riskp.getM6()) {
					yanse = "橙色风险";
				} else if (level >= riskp.getM7()) {
					yanse = "黄色风险";
				} else if (level < riskp.getM7()) {
					yanse = "蓝色风险";
				}
				result.put("data", level);
				result.put("color", yanse);
			}
		}
		return result;
	}

	/**
	 *  @author: zbc
	 *  @Date: 2019/10/12 15:49
	 *  大数据页面风险点列表
	 */
	public List<Map<String,Object>> bigDataGetFxdList(Map<String,Object> mapdata){
		List<Map<String,Object>> list =  fxgkFxxxDao.bigDataGetFxdList(mapdata);
		if (list.size()>0)
			return list;
		else
			return  null;
	}

	/**
	 * 大数据页面风险点个数统计
	 * @param mapData
	 * @return
	 */
	public Map<String,Object> bigDataGetFxdCount(Map<String,Object> mapdata){
		List<Map<String,Object>> list =  fxgkFxxxDao.bigDataGetFxdCount(mapdata);
		if (list.size()>0)
			return list.get(0);
		else
			return  null;
	}
}
