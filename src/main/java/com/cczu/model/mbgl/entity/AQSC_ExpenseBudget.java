package com.cczu.model.mbgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * @ClassName: AQSC_ExpenseBudget
 * @Description: 安全生产投入_费用预算表
 *
 */
@Entity
@Table(name="aqsc_expensebudget")
public class AQSC_ExpenseBudget extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "M1", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String M1; //年度
	
	@Column(name = "M2",columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M2;//支出项目类别
	
	@Column(name = "M3", nullable = true )
	@Setter
	@Getter
	private Float M3;//预算费用（万元）
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M4;//预算培训人
	
	@Column(name = "M5",columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M5;//项目说明
	
	@Column(name = "qyid", nullable = true, length = 8)
	@Setter
	@Getter
	public Long qyid;//企业id
}
