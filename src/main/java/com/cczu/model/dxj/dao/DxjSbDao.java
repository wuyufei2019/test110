package com.cczu.model.dxj.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.cczu.model.entity.dto.Tdic_NoteTreeDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.cczu.model.dxj.entity.DXJ_SbEntity;
import com.cczu.util.dao.BaseDao;

/**
 * dao层
 *
 */
@Repository("DxjSbDao")
public class DxjSbDao extends BaseDao<DXJ_SbEntity, Long>{

	/**
	 * 记录list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + " a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by a.id) rownum,a.*,b.m1 qyname "+
                   "FROM dxj_sb a left join bis_enterprise b on b.ID=a.id1 WHERE b.s3=0 "+content+")a "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
     * 记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql="SELECT COUNT(*) FROM dxj_sb a left join bis_enterprise b on b.ID=a.id1 WHERE b.s3=0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
	 * 得到设备list
	 * @return
	 */
	public List<Map<String, Object>> getsblist(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = "SELECT a.id,a.m1 text FROM dxj_sb a left join bis_enterprise b on b.ID=a.id1 WHERE b.s3=0 "+content;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND b.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("sbname")!=null&&mapData.get("sbname")!=""){
			content = content + "AND a.m1 like '%"+mapData.get("sbname")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND b.id = "+mapData.get("qyid")+" "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND b.id2 like '"+mapData.get("xzqy")+"%' "; 
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}
	
	/**
     * 删除
     * @param id
     */
	public void deleteInfo(long id) {
		String sql=" delete dxj_sb WHERE ID="+id;
		updateBySql(sql);
	}
	
	public boolean checkSameBuildContent(long id,String bindcontent) {
		String sql ="  SELECT * FROM dxj_sb  WHERE id!="+id+" and bindcontent = '"+bindcontent+"'";
		List<DXJ_SbEntity> list=findBySql(sql,null,DXJ_SbEntity.class);
		if(list.size()>0){
			return true;
		}
		return false;
	}
	
	public boolean checkSameRfid(long id,String rfid) {
		String sql ="  SELECT * FROM dxj_sb  WHERE id!="+id+" and rfid = '"+rfid+"'";
		List<DXJ_SbEntity> list=findBySql(sql,null,DXJ_SbEntity.class);
		if(list.size()>0){
			return true;
		}
		return false;
	}

    /**
     * 生产设备pid:1 ;特种设备pid:2
     * @param qyid
     * @return
     */
    public List<Map<String,Object>> getsbAll(Long qyid) {
	    String content="";
        if (!StringUtils.isEmpty(qyid.toString())) {
            content += "and id1 = "+qyid;
        }
	    String sql = " select 'P_'+convert(varchar(50),id) id,m1+'_'+m3 text ,'1' pid  from bis_productionfacility where s3=0 " + content
                +" union all" +
                " select 'S_'+convert(varchar(50),id) id,m2+'_'+m1 text ,'2' pid from bis_specialequipment where s3=0 " +content
                + " union all"
                + " select '1' id,'生产设备' text ,null pid "
                + " union all"
                + " select '2' id,'特种设备' text ,null pid ";
        List<Map<String,Object>> list = findBySql(sql, null, Map.class);
	    return list;
    }
}
