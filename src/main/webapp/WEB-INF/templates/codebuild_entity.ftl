package ${code_classpack }.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * @ClassName: ${code_classname }
 * @Description: ${code_description }
 * @author 
 * @date ${.now?string["yyyy年MM月dd日"] }
 *
 */
@Entity
@Table(name = "${code_classname?lower_case }")
public class ${code_classname } extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
   
    <#list columns as map>
    <#if map.col_ename!='ID'&&map.col_ename!='S1'&&map.col_ename!='S2'&&map.col_ename!='S3'>
	@Column(name = "${map.col_ename!'' }", nullable = ${map.col_nullable!"false" },columnDefinition="${(map.col_datatype=="varchar")?string(map.col_datatype+"("+ map.col_length?if_exists+")",map.col_datatype) }")
	@Setter
	@Getter
	private ${map.col_propertytype!'' } ${map.col_ename!'' };// ${map.col_cname }
   
   </#if>
   </#list>
}
