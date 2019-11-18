package com.cczu.util.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.persistence.SimpleHibernateDao;
import com.cczu.util.common.Parameter;

/**
 * 
 * @ClassName: BaseDao
 * @Description: 基础
 * @author jason
 * @date 2017年5月26日
 *
 */
public class BaseDao<T, PK extends Serializable> extends HibernateDao<T, PK> {
	/**
	 * SessionFactory
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	public BaseDao() {
		// TODO Auto-generated constructor stub
	}
	public BaseDao(final SessionFactory sessionFactory, final Class<T> entityClass){
		super(sessionFactory, entityClass);
	}
	/**
	 * 获取 Session
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	/**
	 * 赋值 Session
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 强制与数据库同步
	 */
	public void flush(){
		getSession().flush();
	}

	/**
	 * 清除缓存数据
	 */
	public void clear(){ 
		getSession().clear();
	}
	
	// -------------- SQL Query --------------

	/**
	 * SQL 查询
	 * @param sqlString
	 * @return
	 */
	public <E> List<E> findBySql(String sqlString){
		return findBySql(sqlString, null, null);
	}
	
	/**
	 * SQL 查询
	 * @param sqlString
	 * @param parameter
	 * @return
	 */
	public <E> List<E> findBySql(String sqlString, Parameter parameter){
		return findBySql(sqlString, parameter, null);
	}
	
	/**
	 * SQL 查询
	 * @param sqlString
	 * @param resultClass
	 * @param parameter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <E> List<E> findBySql(String sqlString, Parameter parameter, Class<?> resultClass){
		SQLQuery query = createSqlQuery(sqlString, parameter);
		setResultTransformer(query, resultClass);
		return query.list();
	}
	
	/**
	 * SQL 更新
	 * @param sqlString
	 * @return
	 */
	public int updateBySql(String sqlString){
		return createSQLQuery(sqlString).executeUpdate();
	}

	/**
	 * SQL 更新
	 * @param sqlString
	 * @param parameter
	 * @return
	 */
	public int updateBySql(String sqlString, Parameter parameter){
		return createSqlQuery(sqlString, parameter).executeUpdate();
	}
	
	/**
	 * SQL 新插入数据并且返回新插入id
	 * @param sqlString
	 * @return
	 */
	public Long executeCountID(String sqlString) {
		long lg=0;
        Query query = getSession().createSQLQuery(sqlString);
        List<?> list = query.list();
        if (list != null && list.size() > 0) {
            if(list.get(0)!=null){
                return Long.parseLong(list.get(0).toString());
            }
        }
        return lg;
    }
	
	/**
	 * 创建 SQL 查询对象
	 * @param sqlString
	 * @param parameter
	 * @return
	 */
	public SQLQuery createSqlQuery(String sqlString, Parameter parameter){
		SQLQuery query = getSession().createSQLQuery(sqlString);
		setParameter(query, parameter);
		return query;
	}
	
	// -------------- Query Tools --------------

	/**
	 * 设置查询结果类型
	 * @param query
	 * @param resultClass
	 */
	private void setResultTransformer(SQLQuery query, Class<?> resultClass){
		if (resultClass != null){
			if (resultClass == Map.class){
				query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			}else if (resultClass == List.class){
				query.setResultTransformer(Transformers.TO_LIST);
			}else{
				query.addEntity(resultClass);
			}
		}
	}
	
	/**
	 * 设置查询参数
	 * @param query
	 * @param parameter
	 */
	private void setParameter(Query query, Parameter parameter){
		if (parameter != null) {
            Set<String> keySet = parameter.keySet();
            for (String string : keySet) {
                Object value = parameter.get(string);
                //这里考虑传入的参数是什么类型，不同类型使用的方法不同  
                if(value instanceof Collection<?>){
                    query.setParameterList(string, (Collection<?>)value);
                }else if(value instanceof Object[]){
                    query.setParameterList(string, (Object[])value);
                }else{
                    query.setParameter(string, value);
                }
            }
        }
	}
	/**
	 * 返回排序sql
	 * @param mapData页面排序字段,orderBy默认排序, Alias别名
	 * 
	 * @return
	 */
	public String  setSortWay(Map<String,Object> mapData,String alias,String orderBy) {
		String content;
		if(mapData.get("orderBy")!=null&&mapData.get("orderBy")!="")
			content=" ORDER BY "+alias+mapData.get("orderBy")+" "+mapData.get("order")+" ";
		else
			content=orderBy;
		return content;
	}
	
}
