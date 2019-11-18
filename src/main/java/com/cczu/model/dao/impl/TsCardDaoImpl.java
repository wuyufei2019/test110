package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.TS_Card;
import com.cczu.model.dao.ITsCardDao;
import com.cczu.util.dao.BaseDao;

@Repository("ITsCardDao")
public class TsCardDaoImpl extends BaseDao<TS_Card, Long> implements ITsCardDao {

	@Override
	public List<Map<String, Object>> findCardIdTextList() {
		String sql="SELECT id,cardNo text FROM ts_card ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> findCardMaqList() {
		String sql="select tc.cardNo text,t1.lng,t1.lat from ts_cardpos t1,ts_card tc "
				+" where t1.id = ( select max(t2.id) from ts_cardpos t2 where t2.id_card = t1.id_card  group by t2.id_card ) and t1.id_card=tc.id ";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> findCardByid(Map<String, Object> map) {
//		String sql="SELECT TOP 1 * FROM ts_cardpos where id_card="+id+" order by gpstime desc ";
		String content=content(map);
		String sql="select tc.id, tc.cardNo text,t1.lng,t1.lat from ts_cardpos t1,ts_card tc "
				+" where t1.id_card=tc.id "+content;// and tc.id=1
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> map) {
		String content=" ";
		
		if(map.get("id")!=null&&map.get("id")!=""){
			content = content + "AND tc.id="+map.get("id")+" "; 
		}else{
//			content = content + "AND t1.id = ( select max(t2.id) from ts_cardpos t2 where t2.id_card = t1.id_card  group by t2.id_card )  "; 

			if( (map.get("starttime")!=null&&map.get("starttime")!="") || (map.get("endtime")!=null&&map.get("endtime")!="") ){
			}else{
				content = content + "AND t1.id = ( select max(t2.id) from ts_cardpos t2 where t2.id_card = t1.id_card  group by t2.id_card )  "; 
			}
		}
		if(map.get("starttime")!=null&&map.get("starttime")!=""){
			content = content + "AND t1.gpstime >= '"+map.get("starttime")+"' "; 
		}
		if(map.get("endtime")!=null&&map.get("endtime")!=""){
			content = content + "AND t1.gpstime <= '"+map.get("endtime")+"' "; 
		}
		
		return content;
	}
	
	@Override
	public void updateCardState(String cardno,int state) {
		String sql=" UPDATE ts_card SET isrelation="+state+" WHERE cardno like '"+cardno+"'";
		updateBySql(sql);
	}

	@Override
	public List<TS_Card> dataGrid(Map<String, Object> mapData) {
		String content=cardContent(mapData);
		if(mapData.get("sort")!=null&&mapData.get("sort")!=""&&mapData.get("order")!=null&&mapData.get("order")!="")
			content = content + "order by "+mapData.get("sort")+" "+mapData.get("order"); 
		
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY ID DESC) AS RowNumber, * FROM ts_card  ) "
				+ "AS a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " +content;
		List<TS_Card> list=findBySql(sql,null,TS_Card.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=cardContent(mapData);
		String sql="SELECT COUNT(*) SUM   FROM ts_card  where 0=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
     * 工卡分页查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String cardContent(Map<String, Object> map) {
		String content=" ";
		
		if(map.get("qyid")!=null&&map.get("qyid")!=""){
			content = content + "AND id1="+map.get("qyid")+" "; 
		}
		if(map.get("state")!=null&&map.get("state")!=""){
			content = content + "AND isrelation="+map.get("state")+" "; 
		}
		return content;
	}

	@Override
	public void deleteCard(long id) {
		String sql="DELETE FROM ts_card  WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public List<Map<String, Object>> findYgGPSList(Map<String, Object> map) {
		String content=" ";
		if(map.get("qyid")!=null&&map.get("qyid")!=""){
			content = content + "AND emp.id3="+map.get("qyid")+" "; 
		}
		if(map.get("xzqy")!=null&&map.get("xzqy")!=""){
			content = content + "AND emp.id2 like'"+map.get("xzqy")+"%' "; 
		}
		String sql="select emp.id, emp.m1 ygname,ent.m1 qyname,tc.cardNo text,t1.lng,t1.lat  from ts_card tc left join ts_cardpos t1 on t1.id_card=tc.id left join bis_employee emp on tc.cardNo=emp.m2 left join bis_enterprise ent on emp.id3=ent.id where t1.id in ( select max(t2.id) from ts_cardpos t2 group by t2.id_card ) and tc.isrelation=1 and tc.isOnline=2 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public TS_Card findCardByCardNO(String cardNo) {
		String sql ="SELECT * FROM ts_card WHERE cardNo='"+cardNo+"'";
		List<TS_Card> list=findBySql(sql, null,TS_Card.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
