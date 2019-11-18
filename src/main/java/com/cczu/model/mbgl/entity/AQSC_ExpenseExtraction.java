package com.cczu.model.mbgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: AQSC_ExpenseExtraction
 * @Description: 安全生产投入_费用提取
 *
 */
@Entity
@Table(name="aqsc_expenseextraction")
public class AQSC_ExpenseExtraction extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "M1", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String M1; //年度

	@Column(name = "M1_2", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String M1_2; //月份

	@Column(name = "M2", nullable = true)
	@Setter
	@Getter
	private Float M2;//销售收入(万元)
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M3;//行业类型
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(max)")
	@Getter
	@Setter
	private String M4;//提取标准
	
	@Column(name = "M5", nullable = true)
	@Getter
	@Setter
	private Float M5;//提取基数
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(2000)")
	@Getter
	@Setter
	private String M6;//备注

	@Column(name = "M7", nullable = true, columnDefinition="varchar(2000)")
	@Getter
	@Setter
	private String M7;//附件

	@Column(name = "qyid", nullable = true, length = 8)
	@Setter
	@Getter
	public Long qyid;//企业id
}
