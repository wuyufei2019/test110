package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.XZCF_EnquiryNoteEntity;
import com.cczu.util.dao.BaseDao;

@Repository("XzcfXwtzDao")
public class XzcfXwtzDao extends BaseDao<XZCF_EnquiryNoteEntity, Long>{

	/**
	 * 查询询问通知list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by a.id desc) rownum,a.*,b.m1 qyname FROM xzcf_enquirynote a LEFT JOIN bis_enterprise b ON b.id = a.id2 left join xzcf_ybcflasp c on c.id = a.id3 left join t_user u on c.userid = u.id "
		           + "WHERE a.s3 = 0 AND b.s3=0 and c.s3 = 0 "+content +") a "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1)  ";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM xzcf_enquirynote a LEFT JOIN bis_enterprise b ON b.id = a.id2 left join xzcf_ybcflasp c on c.id = a.id3 left join t_user u on c.userid = u.id WHERE a.s3 = 0 and b.s3 = 0 and c.s3=0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
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
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content + "AND a.m2 >= '"+mapData.get("m2")+" 00:00:00' and a.m2 <= '"+mapData.get("m2")+" 23:59:59' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND u.xzqy = '"+mapData.get("xzqy")+"' "; 
		}
		return content;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" UPDATE xzcf_enquirynote SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
	/**
	 * 根据id删除
	 * @param id
	 */
	public void updateLaspInfo(long id) {
		String sql=" update b set xwflag=0  from  xzcf_enquirynote  a LEFT JOIN xzcf_ybcflasp  b on  b.id=a.id3 where a.id= "+id;
		updateBySql(sql);
	}


	
	/**
	 * 添加询问通知信息
	 * @param zfry
	 */
	public void addInfo(XZCF_EnquiryNoteEntity zfry) {
		save(zfry);
	}
	
	/**
	 * 根据id查找询问通知信息
	 * @param id
	 * @return
	 */
	public XZCF_EnquiryNoteEntity findInfoById(long id) {	
		XZCF_EnquiryNoteEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(XZCF_EnquiryNoteEntity zfry) {
		save(zfry);
	}

	/**
	 * 查找符合word的数据
	 * @param id
	 * @return
	 */
	public Map<String, Object> findWord(Long id) {
		String sql="select a.*,b.m1 qyname FROM xzcf_enquirynote a LEFT JOIN bis_enterprise b ON b.id = a.id2 left join xzcf_ybcflasp c on c.id = a.id3 where a.s3=0 and b.s3=0 and c.s3=0 and a.id ="+id;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list.get(0);
	}
	
	public Map<String, Object> findWordByLaId(long laid) {
		// TODO Auto-generated method stub
		String sql="select a.*,b.m1 qyname FROM xzcf_enquirynote a LEFT JOIN bis_enterprise b ON b.id = a.id2 left join xzcf_ybcflasp c on a.id3=c.id where a.s3=0 and c.s3=0 and c.id ="+laid;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list.get(0);
	}

	/**
	 * 根据立案id查找询问通知的数据
	 * @param id
	 * @return
	 */
	public Map<String, Object> findById3(Long id) {
		String sql="select a.*,b.m1 qyname FROM xzcf_enquirynote a LEFT JOIN bis_enterprise b ON b.id = a.id2 left join xzcf_ybcflasp c on c.id = a.id3 where  a.s3=0 and b.s3=0 and c.s3=0 and a.id3 ="+id;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list.get(0);
	}
	
}
