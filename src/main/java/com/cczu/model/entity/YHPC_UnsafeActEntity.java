package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 隐患排查---不安全行为管理
 * @author jason
 * @date 2017年8月17日
 */
@Entity
@Table(name="yhpc_unsafeact")
public class YHPC_UnsafeActEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID1;//企业ID
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//不安全行为类型
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M2;//行为描述
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M3;//备注
	
	@Column(name = "M4", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Integer M4;//排序
}
