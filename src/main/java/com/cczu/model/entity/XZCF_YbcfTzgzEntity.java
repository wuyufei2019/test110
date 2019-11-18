package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: XZCF_YbcfTzgzEntity
 * @Description: 行政处罚-一般处罚-听证告知
 * @author who
 * @date 2017年7月29日
 *
 */
@Entity
@Table(name="xzcf_ybcftzgz")
public class XZCF_YbcfTzgzEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(name = "name", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String name;//处罚单位或者个人名称

	@Column(name = "punishdate", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp punishdate;//处罚日期

	@Column(name = "illegalact", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String illegalact;//违法行为
	
	@Column(name = "evidence", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String evidence;//证据
	
	@Column(name = "unlaw", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String unlaw;//违反的法律
	
	@Column(name = "enlaw", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String enlaw;//依据的法律
	
	@Column(name = "punishresult", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String punishresult;//处罚结果
	
	@Column(name = "contactname", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String contactname;//联系人
	@Column(name = "phone", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String phone;//联系人电话
	
	@Column(name = "number", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String number;//编号
	
	//----------一般处罚的立案审批表id
	@Column(name = "id1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long id1;
	
}
