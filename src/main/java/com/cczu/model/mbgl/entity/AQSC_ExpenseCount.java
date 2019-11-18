package com.cczu.model.mbgl.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: AQSC_ExpenseCount
 * @Description: 安全生产投入_费用计算
 *
 */
@Entity
@Table(name="aqsc_expensecount")
public class AQSC_ExpenseCount implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号

	@Column(name = "M1", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String M1; //行业类型
	
	@Column(name = "M2",columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M2;//提取标准
	
	@Column(name = "M3", nullable = true )
	@Setter
	@Getter
	private Float M3;//左区间（万元）
	
	@Column(name = "M4", nullable = true )
	@Getter
	@Setter
	private Float M4;//右区间（万元）
	
	@Column(name = "M5", nullable = true )
	@Getter
	@Setter
	private Float M5;//税率
	
	@Column(name = "M6", nullable = true )
	@Getter
	@Setter
	private Float M6;//提取数（万元）
}
