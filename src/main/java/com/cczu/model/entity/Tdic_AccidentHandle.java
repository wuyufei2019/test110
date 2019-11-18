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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
/**
 * 
 * @ClassName: Tdic_GBT4754Entity
 * @Description: 字典-国民经济行业分类表
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="tdic_accidenthandle")
@DynamicUpdate @DynamicInsert
public class Tdic_AccidentHandle  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//编号
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M1;//事故类型
	
	
	@Column(name="M2", nullable=false, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M2;//处理方式
	
 
}
