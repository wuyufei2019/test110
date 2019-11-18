package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @description 企业-安全培训-需求投票信息
 * @author jason
 * @date 2018年1月26日
 */
@Entity
@Table(name="aqpx_demandinfor")
public class AQPX_DemandInforEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2872665003603394323L;

	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ID1;//企业ID
	
	@Column(name = "ztid", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ztid;//培训调查主题ID
	
	@Column(name = "m1", nullable = true, length = 100)
	@Setter
	@Getter
	private String M1;//课程名称
	
	@Column(name = "m2", nullable = true)
	@Setter
	@Getter
	private int M2;//投票数
	
}
