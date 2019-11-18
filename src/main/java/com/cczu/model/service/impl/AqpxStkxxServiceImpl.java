package com.cczu.model.service.impl;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAqpxGzxxDao;
import com.cczu.model.dao.IAqpxKcxxDao;
import com.cczu.model.dao.IAqpxStkxxDao;
import com.cczu.model.entity.AQPX_CourseEntity;
import com.cczu.model.entity.AQPX_ItembankEntity;
import com.cczu.model.entity.AQPX_TestguizeEntity;
import com.cczu.model.entity.BIS_StorageEntity;
import com.cczu.model.service.IAqpxStkxxService;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;

@Transactional(readOnly=true)
@Service("AqpxStkxxService")
public class AqpxStkxxServiceImpl implements IAqpxStkxxService {
	
	@Resource
	private IAqpxStkxxDao aqpxstkxxdao;
	@Resource
	private IAqpxGzxxDao aqpxGzxxDao;
	@Resource
	private IAqpxKcxxDao aqpxkcxxDao;
	
	@Override
	public Page<AQPX_ItembankEntity> search(Page<BIS_StorageEntity> page,
			List<PropertyFilter> filters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addInfo(AQPX_ItembankEntity ai) {
		aqpxstkxxdao.addInfo(ai);
	}

	@Override
	public void updateInfo(AQPX_ItembankEntity ai) {
		aqpxstkxxdao.updateInfo(ai);
	}

	@Override
	public void deleteInfo(Long id) {
		aqpxstkxxdao.deleteInfo(id);
	}

	@Override
	public AQPX_ItembankEntity findAll(Long qyid) {
		return aqpxstkxxdao.findAll(qyid);
	}

	@Override
	public AQPX_ItembankEntity findkc(Long kcid) {
		return aqpxstkxxdao.findkc(kcid);
	}

	@Override
	public List<AQPX_ItembankEntity> getst(Long kcid) {
		return aqpxstkxxdao.getst(kcid);
	}

	@Override
	public List<AQPX_ItembankEntity> getdx(Long kcid, int dxsum) {
		return aqpxstkxxdao.getdx(kcid, dxsum);
	}

	@Override
	public List<AQPX_ItembankEntity> getdsx(Long kcid, int dsxsum) {
		return aqpxstkxxdao.getdsx(kcid, dsxsum);
	}

	@Override
	public List<AQPX_ItembankEntity> gettk(Long kcid, int tksum) {
		return aqpxstkxxdao.gettk(kcid, tksum);
	}

	@Override
	public List<AQPX_ItembankEntity> getpd(Long kcid, int pdsum) {
		return aqpxstkxxdao.getpd(kcid, pdsum);
	}

	@Override
	public List<AQPX_ItembankEntity> getkt(Long kcid, int dxsum, int dsxsum,
			int tksum, int pdsum) {
		return aqpxstkxxdao.getkt(kcid, dxsum, dsxsum, tksum, pdsum);
	}

	@Override
	public List<AQPX_ItembankEntity> getstxx(Long id) {
		return aqpxstkxxdao.getstxx(id);
	}

	@Override
	public String content(Map<String, Object> mapData) {
		return aqpxstkxxdao.content(mapData);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=aqpxstkxxdao.dataGrid(mapData);
		int getTotalCount=aqpxstkxxdao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public AQPX_ItembankEntity findByid(Long id) {
		// TODO Auto-generated method stub
		return aqpxstkxxdao.findByid(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String[] title={"课程名称","题目类型-","题干","A","B","C","D","E","答案"};  
		String fileName="试题库题目信息表.xls";
		List<Object> list=aqpxstkxxdao.getExport(mapData);
		new ExportExcel(fileName, title,list, response);
	}

	@Override
	public String content2(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return aqpxstkxxdao.content2(mapData);
	}

	@Override
	public Map<String, Object> findbym(Long id1, String m1) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> arrylist = aqpxstkxxdao.findbym(id1, m1);
		return arrylist.get(0);
	}

	@Override
	public Map<String, List<AQPX_ItembankEntity>> getSjByGz(Long kcid, int dxsum, int dsxsum,
			int tksum, int pdsum) {
		Map<String, List<AQPX_ItembankEntity>> map = new HashMap<String, List<AQPX_ItembankEntity>>();
		List<AQPX_ItembankEntity> dxlist=aqpxstkxxdao.getdx(kcid,dxsum);
		List<AQPX_ItembankEntity> dsxlist=aqpxstkxxdao.getdsx(kcid, dsxsum);
		List<AQPX_ItembankEntity> tklist=aqpxstkxxdao.gettk(kcid, tksum);
		List<AQPX_ItembankEntity> pdlist=aqpxstkxxdao.getpd(kcid, pdsum);

		map.put("dxlist", dxlist);
		map.put("dsxlist", dsxlist);
		map.put("tklist", tklist);
		map.put("pdlist", pdlist);	
		return map;
	}

	@Override
	public boolean STisOrNot(Long kcid,Long qyid){
		boolean flag=true;
		List<Map<String,Object>> list=aqpxstkxxdao.getStSum(kcid);
		AQPX_TestguizeEntity stgz=aqpxGzxxDao.findkc(qyid, kcid);
		for(int i=0;i<list.size();i++){
			Map<String,Object> map=list.get(i);
			if((int)map.get("m1")==1&&(int)map.get("sum")<stgz.getM1()){
				flag=false;break;
			}else
			if((int)map.get("m1")==2&&(int)map.get("sum")<stgz.getM2()){
				flag=false;break;
			}else
			if((int)map.get("m1")==3&&(int)map.get("sum")<stgz.getM3()){
				flag=false;break;
			}else
			if((int)map.get("m1")==4&&(int)map.get("sum")<stgz.getM4()){
				flag=false;break;
			}
		}
		return flag;
	}

	@Override
	public Map<String,Object> exinExcel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String,Object> resultmap = new HashMap<String, Object>();
		int result = 0;
		ExinExcel exin = new ExinExcel();
		List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
		int i = 0,  error = 0;
		if (list.size() > 3) {
			result = 0;
			resultmap.put("total", list.size()-3);
			resultmap.put("returncode", "success");
			resultmap.put("success",0);
			for (List<Object> bis : list) {
				if(i<=2){ //跳过前三行
					i++;
					continue;
				}
				if(StringUtils.isBlank(bis.get(0).toString()))//判断是否为空的一行
					continue;
				try{
					AQPX_ItembankEntity stk = new AQPX_ItembankEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					stk.setS1(t);
					stk.setS2(t);
					stk.setS3(0);
					stk.setID1(Long.valueOf(map.get("qyid").toString()));
					//判断课程名称是否存在
					Map<String, Object> map2=new HashMap<String, Object>();
					map2.put("kcname", bis.get(0).toString());
					List<AQPX_CourseEntity> kclist=aqpxkcxxDao.findByName(map2);
					if(kclist.size()==0){
						error++;
						continue;
					}else{
						AQPX_CourseEntity kc=kclist.get(0);
						stk.setID2(kc.getID());
					}
					String type=bis.get(1).toString();
					if(type.equals("单选题")){
						stk.setM1("1");
					}else if(type.equals("多选题")){
						stk.setM1("2");
					}else if(type.equals("填空题")){
						stk.setM1("3");
					}else if(type.equals("判断题")){
						stk.setM1("4");
					}
					stk.setM2(bis.get(2).toString());
					stk.setM3(bis.get(3).toString());
					stk.setM4(bis.get(4).toString());
					stk.setM5(bis.get(5).toString());
					stk.setM6(bis.get(6).toString());
					stk.setM7(bis.get(7).toString());
					stk.setM8(bis.get(8).toString());
					aqpxstkxxdao.addInfo(stk);
					result++;
				}catch(Exception e){
					error++;
				}
				resultmap.put("success",result);
				resultmap.put("error", error);
			}
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
	
	@Override
	public String STgzIsOrNot(Map<String, String> mapData) {
		String str = "";
		List<Map<String,Object>> list=aqpxstkxxdao.getStSum(Long.parseLong(mapData.get("id2")));
		for(int i=0;i<list.size();i++){
			Map<String,Object> map=list.get(i);
			if((int)map.get("m1")==1&&(int)map.get("sum")<Integer.parseInt(mapData.get("m1"))){
				str="1";break;
			}else
			if((int)map.get("m1")==2&&(int)map.get("sum")<Integer.parseInt(mapData.get("m2"))){
				str="2";break;
			}else
			if((int)map.get("m1")==3&&(int)map.get("sum")<Integer.parseInt(mapData.get("m3"))){
				str="3";break;
			}else
			if((int)map.get("m1")==4&&(int)map.get("sum")<Integer.parseInt(mapData.get("m4"))){
				str="4";break;
			}
		}
		return str;
	}
}
