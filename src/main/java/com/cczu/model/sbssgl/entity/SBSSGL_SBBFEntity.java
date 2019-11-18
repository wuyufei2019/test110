package com.cczu.model.sbssgl.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-设备报废
 * @author 
 */
@Entity
@Table(name = "sbssgl_sbbf")
public class SBSSGL_SBBFEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "sbid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long sbid;//设备id

	@Column(name = "zcy", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String zcy;//资产原/净值
	
	@Column(name = "gdsynx", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String gdsynx;//规定使用年限
	
	@Column(name = "sjsynx", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String sjsynx;//实际使用年限
	
	@Column(name = "reason", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String reason;//报废原因
	
	@Column(name = "status", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String status;//状态（0.待上传附件1.待审核2.通过3.不通过）
	
	@Column(name = "fj", nullable = true, columnDefinition="varchar(2000)")
	@Getter
	@Setter
	private String fj;//附件
	
}
