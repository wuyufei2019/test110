package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 隐患排查---观察记录主表管理
 * @author jason
 * @date 2017年8月17日
 */
@Entity
@Table(name="yhpc_observations_main")
public class YHPC_Observations_Main extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID1;//企业ID
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID2;//观察员id
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M1;//区域/部门
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M2;//所观察的安全行为
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M3;//所观察的不安全行为
}
