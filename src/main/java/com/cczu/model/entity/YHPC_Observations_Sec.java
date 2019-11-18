package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 隐患排查---观察记录副表管理
 * @author jason
 * @date 2017年8月17日
 */
@Entity
@Table(name="yhpc_observations_sec")
public class YHPC_Observations_Sec extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID1;//主表ID
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID2;//不安全行为ID
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M1;//有无不安全行为（安全/不安全）
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M2;//预计伤害事故（A轻伤事故 B重伤事故 C死亡事故 D其他事故）
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M3;//状态（不安全行为/不安全状态）
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M4;//涉及人员（手填）
	
	@Column(name = "M5", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	private Integer M5;//数量
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M6;//不安全行为描述
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(max)")
	@Setter
	@Getter
	private String M7;//附件（照片或视频）
}
