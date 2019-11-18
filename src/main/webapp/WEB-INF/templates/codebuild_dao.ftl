package ${code_classpack }.dao;

import java.util.*;

import org.springframework.stereotype.Repository;

import ${code_classpack }.entity.${code_classname };
import com.cczu.util.dao.BaseDao;

/**
 * ${code_description }Dao
 * @author 
 * @date ${.now?string["yyyy年MM月dd日"] }
 */
@Repository("${code_classname?replace('_', '' ) }Dao")
public class ${code_classname?replace('_', '' ) }Dao extends BaseDao<${code_classname }, Long> {

	public List<${code_classname}> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT top " + mapData.get("pageSize") 
                    + " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.* FROM ${code_classname?lower_case } a WHERE a.s3=0 "
					+ content + " ) as a WHERE RowNumber > "
					+ mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
		List<${code_classname}> list = findBySql(sql, null, ${code_classname}.class);
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT COUNT(1) sum  FROM ${code_classname?lower_case } a WHERE a.s3=0" + content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	public String content(Map<String, Object> mapData) {
		String content = "";
       <#list columns as map>
       <#if map.col_serach=='true'>
		if (mapData.get("${map.col_ename }") != null && mapData.get("${map.col_ename }") != "") {
			content = content + " AND a.${map.col_ename } = '" + mapData.get("${map.col_ename }") + "'";
		}
      </#if>
      </#list>
		return content;
	}

	public void deleteInfo(Long id) {
		String sql = " UPDATE ${code_classname?lower_case } SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}
   
}
