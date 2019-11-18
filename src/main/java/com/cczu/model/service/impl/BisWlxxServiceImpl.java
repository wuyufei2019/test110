package com.cczu.model.service.impl;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IBisQyjbxxDao;
import com.cczu.model.dao.ITdicDangerousChemicalsDao;
import com.cczu.model.dao.impl.BisWlxxDaoImpl;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.BIS_MatEntity;
import com.cczu.model.entity.TMESK_ChemicalsdirectoryEntity;
import com.cczu.model.service.IBisWlxxService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

@Transactional(readOnly=true)
@Service("BisWlxxService")
public class BisWlxxServiceImpl implements IBisWlxxService {
	@Resource
	private BisWlxxDaoImpl bisWlxxDao;
	@Resource
	private IBisQyjbxxDao bisQyjbxx;
	@Resource
	private ITdicDangerousChemicalsDao tdicdangerous;
	
	@Override
	public BIS_MatEntity findById(Long id) {
		// TODO Auto-generated method stub
		return bisWlxxDao.findById(id);
	}

	@Override
	public Map<String, Object> findById2(Long id) {
		// TODO Auto-generated method stub
		return bisWlxxDao.findById2(id);
	}
	
	@Override
	public List<BIS_MatEntity> findAllWL(long qyid) {
		// TODO Auto-generated method stub
		return bisWlxxDao.findAllWL(qyid);
	}

	@Override
	public List<BIS_MatEntity> findAll(long qyid) {
		// TODO Auto-generated method stub
		return bisWlxxDao.findAll(qyid);
	}
	
	@Override
	public void addInfo(BIS_MatEntity bis) {
		// TODO Auto-generated method stub
		bisWlxxDao.addInfo(bis);
	}

	@Override
	public void updateInfo(BIS_MatEntity bis) {
		// TODO Auto-generated method stub
		bisWlxxDao.updateInfo(bis);
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		bisWlxxDao.deleteInfo(id);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<BIS_MatEntity> list = bisWlxxDao.dataGrid(mapData);
		int getTotalCount = bisWlxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List< Map<String, Object>> list = bisWlxxDao.dataGrid2(mapData);
		int getTotalCount = bisWlxxDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	@Override
	public String wlnmck(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String ret = bisWlxxDao.wlnmck(mapData);
		return ret;
	}

	@Override
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
			String fileName="物料信息表.xls";
			List<Map<String, Object>> list=bisWlxxDao.getExport(mapData);
			String[] title=mapData.get("coltext").toString().split(",");  
			String[] keys=mapData.get("colval").toString().split(",");  
			new ExportExcel(fileName, title, keys, list, response, true);
		}
		
		@Override
		public String findAllwlList() {
			ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
			if(sessionuser.getUsertype().equals("1")){//企业用户
				List<BIS_MatEntity> list = bisWlxxDao.findAllWL(sessionuser.getQyid());
				
				List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
				for(BIS_MatEntity mat:list){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", mat.getM1());
					map.put("text", mat.getM1());
					arrylist.add(map);
				}
				return JsonMapper.getInstance().toJson(arrylist);
			}else{// 非企业角色
				List<TMESK_ChemicalsdirectoryEntity> list=tdicdangerous.findlist();
				List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
				for(TMESK_ChemicalsdirectoryEntity tdc:list){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", tdc.getM2());
					map.put("text", tdc.getM2());
					arrylist.add(map);
				}
				return JsonMapper.getInstance().toJson(arrylist);
			}
			
		}
		
		@Override
		public String findWlforck(String qyid) {
			ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			if(sessionuser.getUsertype().equals("1")){
				list = bisWlxxDao.findwlByqyid(sessionuser.getQyid());
			}
			else {
				list = bisWlxxDao.findwlByqyid(Long.parseLong(qyid));
			}
			List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
			for(Map<String, Object> mat:list){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", mat.get("m1"));
				map.put("text", mat.get("m1") + "  " + mat.get("m5"));
				arrylist.add(map);
			}
			
			return JsonMapper.getInstance().toJson(arrylist);
		}

		@Override
		public List<BIS_MatEntity> findByName(String name) {
			ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
			BIS_EnterpriseEntity be = bisQyjbxx.findInfoById(sessionuser.getQyid());
			if(be!=null){
				return bisWlxxDao.findByNameList(name,be.getID());
			}else{
				return null;
			}
		}


		@Override
		public Map<String, Object> findObById(Long id) {
			// TODO Auto-generated method stub
			List<Map<String, Object>> arrylist = bisWlxxDao.findObById(id);
//			Map<String, Object> map = new HashMap<String, Object>();
			
			return arrylist.get(0);
		}

		@Override
		public Map<String, Object> dataGridAJ(Map<String, Object> map) {
			List<Map<String, Object>> list=bisWlxxDao.dataGridAJ(map);
			int getTotalCount=bisWlxxDao.getTotalCountAJ(map);
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("rows", list);
			map2.put("total", getTotalCount);
			return map2;
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
				for (List<Object> bis : list) {
					if(i<=2){ //跳过前三行
						i++;
						continue;
					}
					try{
						BIS_MatEntity mat = new BIS_MatEntity();
						Timestamp t = DateUtils.getSysTimestamp();
						mat.setS1(t);
						mat.setS2(t);
						mat.setS3(0);
						mat.setID1(Long.valueOf(map.get("qyid").toString()));
						mat.setM1(bis.get(1).toString());
						mat.setM2(Float.parseFloat((bis.get(2).toString()==null||bis.get(2).toString()=="")?"0":bis.get(2).toString()));
						mat.setM3(Float.parseFloat((bis.get(3).toString()==null||bis.get(3).toString()=="")?"0":bis.get(3).toString()));
						mat.setM4(bis.get(4).toString());

						if(bis.get(5)!=null&&bis.get(5).toString()!=""){
							String m5=bis.get(5).toString();
							switch (m5) {
							case "储罐":mat.setM5("1");break;
							case "桶装":mat.setM5("2");break;
							case "袋装":mat.setM5("3");break;
							case "其他":mat.setM5("4");break;
							default:
								break;
							}
						}
						
						mat.setM8(bis.get(6).toString());
						mat.setM6(bis.get(7).toString());
						
						if(bis.get(8)!=null&&bis.get(8).toString()!=""){
							String m9=bis.get(8).toString();
							switch (m9) {
							case "正常":mat.setM9("0");break;
							case "注销":mat.setM9("1");break;
							default:
								break;
							}
						}
						
						if(bis.get(9)!=null&&bis.get(9).toString()!=""){
							String m10=bis.get(9).toString();
							switch (m10) {
							case "否":mat.setM10("0");break;
							case "是":mat.setM10("1");break;
							default:
								break;
							}
						}
						
						if(bis.get(0)!=null&&bis.get(0).toString()!=""){
							String m11=bis.get(0).toString();
							switch (m11) {
							case "原料":mat.setM11("1");break;
							case "产品":mat.setM11("2");break;
							default:
								break;
							}
						}
						
						if(bis.get(10)!=null&&bis.get(10).toString()!=""){
							String m12=bis.get(10).toString();
							switch (m12) {
							case "否":mat.setM12("0");break;
							case "是":mat.setM12("1");break;
							default:
								break;
							}
						}
						
						if(bis.get(11)!=null&&bis.get(11).toString()!=""){
							String m13=bis.get(11).toString();
							switch (m13) {
							case "否":mat.setM13("0");break;
							case "是":mat.setM13("1");break;
							default:
								break;
							}
						}
						
						if(bis.get(12)!=null&&bis.get(12).toString()!=""){
							String m14=bis.get(12).toString();
							switch (m14) {
							case "否":mat.setM14("0");break;
							case "是":mat.setM14("1");break;
							default:
								break;
							}
						}
						
						mat.setM7(bis.get(13).toString());
						bisWlxxDao.addInfo(mat);
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
		public List<Map<String, Object>> findwlByqyid(long qyid) {
			// TODO Auto-generated method stub
			List<Map<String, Object>> list = bisWlxxDao.findwlByqyid(qyid);
			List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
			for(Map<String, Object> mat:list){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", mat.get("m1"));
				map.put("text", mat.get("m1") + "  " + mat.get("m5"));
				arrylist.add(map);
			}
			
			return arrylist;
		}

}