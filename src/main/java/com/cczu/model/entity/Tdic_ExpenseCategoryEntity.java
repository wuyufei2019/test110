package com.cczu.model.entity;

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
 * 
 * @ClassName: Tdic_GBT4754Entity
 * @Description: 字典-支出项目分类表
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="tdic_expensecategory")
public class Tdic_ExpenseCategoryEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name="NAME", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String NAME;//名称
	
	@Column(name="PID", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String PID;//父ID
}
