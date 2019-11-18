package com.cczu.sys.system.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Barrio;

/**
 * 网格区域DAO
 * @author jason
 */
@Repository
@SuppressWarnings("unchecked")
public class BarrioDao extends HibernateDao<Barrio, Long>{

	/**
	 * 根据网格区域查询所有下属网格
	 * @param code
	 * @return
	 */
	public List<Barrio> findAllBycode(String code) {
		String sql=" select * from t_barrio where code like :p1 order by m3";
		SQLQuery sqlQuery=createSQLQuery(sql).addEntity(Barrio.class);
		sqlQuery.setParameter("p1", code+"%");
		return sqlQuery.list();
	}
	/**
	 * 根据网格code 查询网格
	 * @param code
	 * @return
	 */
	public Barrio findPointBycode(String code) {
		String sql=" select * from t_barrio where code =:p1 ";
		SQLQuery sqlQuery=createSQLQuery(sql).addEntity(Barrio.class);
		sqlQuery.setParameter("p1", code);
		List<Barrio> list=sqlQuery.list();
		if(list.size()>0)
			return list.get(0);
		else
			return null;
	}
	
	/**
	 * 修改网格所属父级，及该网格下所有子网格的编码都要修改
	 * @param oldcode
	 * @param newcode
	 */
	public void updateFcode(String oldpcode,String newpcode){
		String sql=" update  t_barrio set code=replace(code,'"+oldpcode+"','"+newpcode+"')";
		createSQLQuery(sql).executeUpdate();
	}
	/**
	 * 根据id修改mappoint
	 * @param oldcode
	 * @param newcode
	 */
	public void updateMapPoint(long id,String mappoint){
		String sql="update t_barrio set mappoint='"+mappoint+"' where id="+id;
		createSQLQuery(sql).executeUpdate();
	}
	/**
	 * 修改企业的行政区域
	 * @param user
	 */
	public void updateQyAndUserXzqy(String ids,String wgxzqy,String userxzqy){
		//Session   session = sessionFactory.openSession();//从连接池获得连接，Session自动把连接设为手工提交事务模式
		//Transaction tx = session.beginTransaction();//声明事务
		//1、将该网格区域的所有的企业的行政区域置为当前用户的行政区域（删除）
		String sql;
		String[] wgs=wgxzqy.split("-");
		String wglen=(wgs.length==1)?"0":wgxzqy.substring(0, wgxzqy.length()-(wgs[wgs.length-1].length()+1));//移除置为上级区域（顶级网格置0）；
		//先将网格全部置为上级网格，再重新设置成选中的网格区域
			sql="update bis_enterprise set id2='"+wglen+"' where s3=0 and id2='"+wgxzqy
					+"';update bis_enterprise set id2='"+wgxzqy+"' where s3=0 and id in ("+ids+")"
					+";update t_user set xzqy='"+wglen+"' where usertype=1 and  xzqy='"+wgxzqy
					+"';update t_user set xzqy='"+wgxzqy+"' where usertype=1 and id2 in ("+ids+")";
			createSQLQuery(sql).executeUpdate();
			/*tx.commit(); //提交事务
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="error"
			tx.rollback();
		}*/
	}
	/**
	 * 修改用户的行政区域
	 * @param user
	 */
	public void updateUserXzqy(String ids,String xzqy){
		String sql="update t_user set xzqy='"+xzqy+"' where id2 in ("+ids+")";
		createSQLQuery(sql).executeUpdate();
	}
	/**
	 * 查询企业
	 * @param map userxzqy：用户的行政区域
	 */
	public List<Map<String,Object>> findQy(Map<String,Object> map) {
		String sql1="SELECT BIS.id,BIS.m1,'0'flag FROM BIS_ENTERPRISE BIS WHERE BIS.S3=0 AND BIS.ID2 like '"+map.get("userxzqy")+"%'";
		String sql2="SELECT bis.id,bis.m1,'1'flag FROM BIS_ENTERPRISE bis WHERE S3=0 ";
		if(map.get("wgxzqy")!=null&&map.get("wgxzqy")!=""){
			sql1+=" and len(bis.id2) <"+map.get("wgxzqy").toString().length();
			sql2+= "AND bis.ID2 ='"+map.get("wgxzqy")+"'";
		}
		if(map.get("qynm")!=null&&map.get("qynm")!=""){
			sql1+=" and bis.m1 like '%"+map.get("qynm")+"%'";
			sql2+=" and bis.m1 like '%"+map.get("qynm")+"%'";
		}
		if(map.get("zhen")!=null&&map.get("zhen")!=""){
			sql1+=" and bis.M11_3 ='"+map.get("zhen")+"'";
			sql2+=" and bis.M11_3 ='"+map.get("zhen")+"'";
		}
		//SQLQuery sqlQuery=createSQLQuery(sql).addEntity(Map.class);
		SQLQuery query = getSession().createSQLQuery(sql1+" union "+sql2+" ORDER BY flag desc");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	/**
	 * 查询企业(根据网格)
	 * @param map
	 */
	public List<Map<String, Object>> findQyByXzqy(String xzqy) {
		String sql="SELECT bis.id,bis.m1,bis.m16 lng,m17 lat,m33 address  FROM bis_enterprise bis where bis.s3=0 and bis.id2 = '"+xzqy+"'";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	/**
	 * 根据编码删除网格和该网格下所有子网格
	 * @param code
	 */
	public void deleteByCode(String code){
		String sql=" delete from t_barrio  where code like '"+code+"%' ";
		createSQLQuery(sql).executeUpdate();
	}
	/**
	 *查询所有不包含code的网格(勾画区域时用)
	 * @param code
	 */
	public List<Barrio> getNotNonelist(String code){
		String sql=" select * from t_barrio where (mappoint is not null or mappoint!='')"+(StringUtils.isNotBlank(code)?" and code !='"+code+"'":"");
		SQLQuery sqlQuery=createSQLQuery(sql).addEntity(Barrio.class);
		return sqlQuery.list();
	}
	/**
	 *查询改网格区域和他该网格的下级网格（网格总览时用）
	 * @param code
	 */
	public List<Map<String,Object>> getChildlist(String code,String lev){
		String sql="with temptb as( select id,m1,code,lat,lng,mappoint ,0 lv from t_barrio where code =:p1 union all "
				+ " select b.id,b.m1,b.code,b.lat,b.lng,b.mappoint ,lv+1 from temptb a inner join t_barrio b on a.id= b.fid)"
				+ " select * from temptb where mappoint!=''"+(StringUtils.isBlank(lev)?"":"and lv in(0,:p2)");
		
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.setParameter("p1", code);
		if(StringUtils.isNotBlank(lev))
		sqlQuery.setParameter("p2", lev);
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return sqlQuery.list();
	}
	/**
	 *查询网格区域下的所有叶子节点网格（风险管控-云图用）
	 * @param code
	 */
	public List<Map<String, Object>> getAllChildlist(String code){
		String sql=" select m1,code xzqy,mappoint from t_barrio where  mappoint!='' and id not in(select fid from t_barrio) and code like '"+code+"%'";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	/**
	 *查询改网格区域的所有直属父网格网格（网格总览时用）
	 * @param code
	 */
	public List<Map<String,Object>> getParentlist(String code,String curcode){
		String sql="with temptb as( select fid,m1,code,lat,lng,mappoint ,0 lv from t_barrio where code =:p1 union all  select b.fid,b.m1,b.code,b.lat,b.lng,b.mappoint ,lv+1 from temptb a inner join t_barrio b on a.fid= b.id and b.code like :p2) select code from temptb where mappoint!=''";
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.setParameter("p1", code);
		sqlQuery.setParameter("p2", curcode+"%");
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return sqlQuery.list();
	}

	/**
	 *查询选中网格区域下的统计数据（网格总览时用）
	 * @param code
	 */
	public Map<String,Object> getTjDatalist(Map<String, Object> map){
		//SELECT count(1) childcount from t_barrio where code like '10-%' and len(code)=(SELECT max(len(code)) from t_barrio where code like '10-%')
		StringBuilder sbr = new StringBuilder();
		String content="";
		String content1 ="";
		if(map.get("xzqy")!=null&&map.get("xzqy")!=""){
			content1+=" AND bis.id2 ='"+map.get("xzqy")+"'";
			content=" AND code like '"+map.get("xzqy")+"%' ";
			content1+=(map.get("jglx")!=null&&map.get("jglx")!="")?" AND bis.m36='"+map.get("jglx")+"'":"";
		}
		sbr.append("SELECT * FROM");
		sbr.append("(SELECT COUNT(1) as qycount FROM bis_enterprise bis  WHERE bis.s3=0 "+content1+") as a,");
		sbr.append("(select top 1 count(1) childcount from t_barrio where 1=1 "+content+" GROUP BY len(code) ORDER BY len(code) desc) as b, ");
		sbr.append("(SELECT COUNT(1) yhpccount FROM yhpc_checkpoint a left join bis_enterprise bis on bis.id=a.id1 WHERE bis.s3=0 and a.usetype='1' "+content1+") as c");
		SQLQuery query = getSession().createSQLQuery(sbr.toString());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return (Map<String, Object>)query.list().get(0);
	}

}
