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
 * @ClassName: YHPC_SafetyDeductionEntity
 * @Description: 安全巡查-安全十二分
 *
 */
@Entity
@Table(name = "yhpc_safetydeduction")
public class YHPC_SafetyDeductionEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;// 企业id
	
	@Column(name = "M1", nullable = true, length = 20)
	@Setter
	@Getter
	private String M1;// 员工姓名

	@Column(name = "M2", nullable = true, length = 8)
	@Setter
	@Getter
	private Long M2;// 所属部门id

	@Column(name = "M3", nullable = true, columnDefinition = "datetime")
	@Setter
	@Getter
	private Timestamp M3;// 最近扣分时间

}
