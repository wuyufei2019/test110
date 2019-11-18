package com.cczu.model.mbgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * @ClassName: AQSC_ExpenseType
 * @Description: 安全生产投入_费用类别
 *
 */
@Entity
@Table(name="aqsc_expensetype")
public class AQSC_ExpenseType extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "fid", columnDefinition="bigint")
	@Getter
	@Setter
	private long fid;//父id
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String M1; //类型名称
	
	@Column(name = "M2",columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M2;//图标
	
	@Column(name = "M3",columnDefinition="int")
	@Setter
	@Getter
	private Integer M3;//序号
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(2000)")
	@Getter
	@Setter
	private String M4;//备注
}
