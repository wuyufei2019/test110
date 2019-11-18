package com.cczu.model.lydw.dao;
/*
 * 蓝牙定位-工卡管理
 */
import com.cczu.model.lydw.entity.Pub_File;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository("Pub_FileDao")
public class Pub_FileDao extends BaseDao<Pub_File,Long>{


		public List<Pub_File> dataGrid(Map<String, Object> mapData) {
			String content=content(mapData);
			String ordercont=setSortWay(mapData,"","ORDER BY fileid desc");
			String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,* FROM pub_file ws WHERE 1=1"+content+" ) "
					+ "as s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
			List<Pub_File> list=findBySql(sql, null,Pub_File.class);
			
			return list;
		}

		public int getTotalCount(Map<String, Object> mapData) {
			String content=content(mapData);
			String sql=" SELECT COUNT(*) sum  FROM pub_file ws WHERE 1=1"+ content;
			List<Object> list=findBySql(sql);
			return (int) list.get(0);
		}

	    /**
	     * 查询条件
	     * @param mapData
	     * @return
	     */
	    public String content(Map<String, Object> mapData) {
			String content="";
			if(mapData.get("fileid")!=null&&mapData.get("fileid")!=""){
				content = content +" AND ws.fileid like '%"+mapData.get("fileid")+"%' ";
			}
			if(mapData.get("fstatus")!=null&&mapData.get("fstatus")!=""){
				content = content +" AND ws.fstatus = '"+mapData.get("fstatus")+"'";
			}
			if(mapData.get("online")!=null&&mapData.get("online")!=""){
				content = content +" AND ws.online = '"+mapData.get("online")+"'";
			}
			if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
				content = content + "AND ws.id1 = "+mapData.get("qyid")+" ";
			}
			if(mapData.get("filecode")!=null&&mapData.get("")!=""){
				content = content + "AND ws.filecode ='" + mapData.get("filecode") +"' ";
			}
			return content;
		}

	
		public Pub_File findById(Long fileid) {
			String sql ="SELECT * FROM pub_file WHERE fileid=" + fileid;
			List<Pub_File> list=findBySql(sql, null,Pub_File.class);
			if(list.size()>0){
				return list.get(0);
			}
			return null;
		}

		public void saveInfo(Pub_File pf) {
			String sql=" INSERT INTO pub_file (fileid,id1,filecode,tag,intime,uptime,fstatus,val1,ftype,online) " +
					"VALUES ("+pf.getFileid()+","+pf.getId1()+","+pf.getFilecode()+","+pf.getTag()+",'"+pf.getIntime()+"','"+pf.getUptime()+"',"+pf.getFstatus()+","+pf.getVal1()+","+pf.getFtype()+","+pf.getOnline()+") ";
			updateBySql(sql);
		}

		/**
		 * 工卡号json list
		 * @return
		 */
		public List<Map<String,Object>> jsonlist() {
			String sql ="SELECT ws.fileid id, ws.fileid text FROM pub_file ws LEFT JOIN lydw_emp_pubfile lep on ws.fileid=lep.fileid where lep.id is null ";
			List<Map<String,Object>> list=findBySql(sql, null,Map.class);
			return list;
		}

        /**
         * 工卡号json list
         * @return
         */
        public List<Map<String,Object>> jsonlist2() {
            String sql ="SELECT ws.fileid id, bis.m1 text FROM pub_file ws LEFT JOIN lydw_emp_pubfile lep on ws.fileid=lep.fileid left join bis_employee bis on lep.empid = bis.id where lep.emptype =0 " +
                    "union "+
                    "SELECT ws.fileid id, car.m1 text FROM pub_file ws LEFT JOIN lydw_emp_pubfile lep on ws.fileid=lep.fileid left join bis_car car on lep.empid = car.id where lep.emptype =1";
            List<Map<String,Object>> list=findBySql(sql, null,Map.class);
            return list;
        }

        public List<Map<String,Object>> findByTagId(Map<String , Object> mapDate) {
        	String sql = "SELECT ws.fileid FROM pub_file ws WHERE 1=1" + content(mapDate);
			List<Map<String,Object>> list = findBySql(sql, null, Map.class);
			return list;
		}
}
