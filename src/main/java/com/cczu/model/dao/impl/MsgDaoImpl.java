package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IMsgDao;
import com.cczu.model.entity.MSG_detailEntity;
import com.cczu.util.dao.BaseDao;

@Repository("MsgDao")
public class MsgDaoImpl extends BaseDao<MSG_detailEntity, Long> implements IMsgDao {

	
	@Override
	public void addInfo(MSG_detailEntity obj) {
		save(obj);
	}

	@Override
	public void addWjInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO msg_detail (S1,S2,S3,ID1,content,info,SENG_OBJ,status,type,RELEASE_TIME,ID2) select getdate(),getdate(),0,"+map.get("uid")+",'"+map.get("content")+"','有新文件发布',id,'2','3', getdate(),id from t_user where id2 in("+map.get("qyids")+")";
	    updateBySql(sql);
	}
	@Override
	public void addAllInfo(long uid,String content,String remind,String type,String status,String qyids) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO msg_detail (S1,S2,S3,ID1,content,info,SENG_OBJ,status,type,RELEASE_TIME,ID2) select getdate(),getdate(),0,"+uid+",'"+content+"','"+remind+"',id,'"+status+"','"+type+"', getdate(),id from t_user where id2 in("+qyids+")";
		updateBySql(sql);
	}

	@Override
	public void updateInfo(MSG_detailEntity obj) {
		save(obj);
	}

	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.s1 desc) AS RowNumber,"
				+ " a.ID as id"
				+ ",b.NAME as id1"
				+ ",(case a.TYPE when '1' then '检测报告' when '2' then '培训计划' when '3' then '安全文件发布' when '4' then '安全生产动态' else '' end) as type"
				+ ",a.INFO as info"
				+ ",a.CONTENT as content"
				+ ",a.SENG_OBJ as sengObj"
				+ ",(case a.STATUS when '1' then '已读' when '2' then '未读' else '' end) as status"
				+ " FROM msg_detail a "
				+ " LEFT JOIN t_user b ON a.ID1=b.ID"
				+ " WHERE a.S3=0 " + content +") "
				+ " AS z WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM msg_detail a WHERE a.s3=0 " + content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	@Override
	public int getMsgNoReadCnt(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM msg_detail a WHERE a.s3=0 AND STATUS='2' " + content;
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
		if(mapData.get("userId")!=null && !"".equals(mapData.get("userId"))){
			content = content +" AND a.SENG_OBJ ="+mapData.get("userId"); 
		}
		if(mapData.get("type")!=null && !"".equals(mapData.get("type"))){
			content = content +" AND a.TYPE ='"+mapData.get("type") + "'"; 
		}
		if(mapData.get("status")!=null && !"".equals(mapData.get("status"))){
			content = content +" AND a.STATUS ='"+mapData.get("status") + "'"; 
		}
		return content;
	}

	@Override
	public void deleteInfo(Long id) {
		String sql=" UPDATE msg_detail SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
	
	@Override
	public void updateInfoByUserId(Long id) {
		String sql=" UPDATE msg_detail SET S2=getdate(),STATUS='1' WHERE SENG_OBJ="+id;
		updateBySql(sql);
	}

	@Override
	public MSG_detailEntity findInfoById(Long id) {
		String sql ="SELECT * "
				+ " FROM msg_detail "
				+ " WHERE S3=0 AND ID="+id;
		List<MSG_detailEntity> list=findBySql(sql, null,MSG_detailEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getExcelData(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT "
				+ " a.ID as id"
				+ ",b.NAME as id1"
				+ ",(case a.TYPE when '1' then '检测报告' when '2' then '培训计划' when '3' then '安全文件发布' when '4' then '安全生产动态' else '' end) as type"
				+ ",a.INFO as info"
				+ ",a.CONTENT as content"
				+ ",(case a.STATUS when '1' then '已读' when '2' then '未读' else '' end) as status"
				+ " FROM msg_detail a"
				+ " LEFT JOIN t_user b ON a.ID1=b.ID"
				+ " WHERE a.S3=0 "+ content + " ORDER BY a.s1 desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public List<MSG_detailEntity> findAllMsgList(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM msg_detail a WHERE a.S3=0 AND a.status=2 "+content(mapData)+"ORDER BY  S1 DESC";
		List<MSG_detailEntity> list = findBySql(sql,null,MSG_detailEntity.class);
		return list;
	}

	
}
