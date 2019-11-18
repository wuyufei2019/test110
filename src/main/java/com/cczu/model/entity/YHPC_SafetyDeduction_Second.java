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
 * @ClassName: YHPC_SafetyDeduction_Second
 * @Description: 安全巡查-安全十二分历史扣分表
 *
 */
@Entity
@Table(name = "yhpc_safetydeduction_second")
public class YHPC_SafetyDeduction_Second extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//安全十二分id
	
	@Column(name = "QYID", nullable = true, length = 8)
	@Setter
	@Getter
	public Long QYID;//企业id
	
	@Column(name = "M1", nullable = true, columnDefinition = "datetime")
	@Setter
	@Getter
	private Timestamp M1;// 扣分时间

	@Column(name = "M2", nullable = true, length = 500)
	@Setter
	@Getter
	private String M2;// 扣分原因

	@Column(name = "M3", nullable = true, columnDefinition = "int")
	@Setter
	@Getter
	private Integer M3;// 扣分分值
	
}
