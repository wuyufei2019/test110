package com.cczu.sys.system.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.system.entity.Message;

/**
 * 
 * @description 消息提醒DAO
 * @author jason
 * @date 2018年1月15日
 */
@Repository
public class MessageDao extends HibernateDao<Message, Integer> {

	/**
	 * 根据id修改消息状态
	 * 
	 * @param id
	 */
	public void updateStatue(Long id) {
		String sql="update t_msg set sendstatue=1 where id ="+id;
		createSQLQuery(sql).executeUpdate();
	}
	
	public List<Map<String,Object>> findTypeCount(int uid) {
		String sql = "SELECT msgtype,count(1) total from t_msg where sendstatue=0 and touser='"+uid+"' GROUP BY msgtype";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	public void addMultMsg(Message msg, Map<String, Object> mapData) {
		String content = Content(mapData);
		String sql = "INSERT INTO t_msg(CONTENT,TOUSER,FROMUSER,SCHSENDTIME,SENDSTATUE,CREATETIME,SUCCESSTIME,TITLE,MSGTYPE,URL) "
				+ "select ?0,id,?1,?2,?3,?4,?5,?6,?7,?8 FROM t_user where 1=1"+ content;
		Query query = createSQLQuery(sql, msg.getCONTENT(), msg.getFROMUSER(),
				msg.getSCHSENDTIME(), msg.getSENDSTATUE(), msg.getCREATETIME(),
				msg.getSUCCESSTIME(), msg.getTITLE(), msg.getMSGTYPE(),
				msg.getURL());
		query.executeUpdate();
	}

	private String Content(Map<String, Object> mapData) {

		String content = "";
		if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			content = content + " AND xzqy='" + mapData.get("xzqy") + "'";
		}
		if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
			content = content + " AND id2=" + mapData.get("qyid");
		}
		if (mapData.get("deptid") != null && mapData.get("deptid") != "") {
			content = content + "AND departmen =" + mapData.get("deptid");
		}
		if(mapData.get("departmens")!=null&&mapData.get("departmens")!=""){
			content = content + " AND departmen in ("+mapData.get("departmens")+")"; 
		}
		return content;
	}
}
