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
 * 字典---危险因素辨识
 * @author jason
 * @date 2017年8月8日
 */
@Entity
@Table(name="tdic_dangerfactoridentify")
public class Tdic_DangerFactorIdentifyEntity implements Serializable{

	private static final long serialVersionUID = -5724391986246753791L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, length = 8)
	@Setter
	@Getter
	public long ID;//编号

	@Column(name = "M1", nullable = true, length = 100)
	@Setter
	@Getter
	private String M1; //行业
	
	@Column(name = "M2", nullable = true, length = 100)
	@Setter
	@Getter
	private String M2; //行业类别
	
	@Column(name = "M3", nullable = true, length = 100)
	@Setter
	@Getter
	private String M3; //工段	
	
	@Column(name = "M4", nullable = true, length = 100)
	@Setter
	@Getter
	private String M4; //场所环节部位
	
	@Column(name = "M5", nullable = true, length = 500)
	@Setter
	@Getter
	private String M5; //较大危险因素	
	
	@Column(name = "M6", nullable = true, length = 500)
	@Setter
	@Getter
	private String M6; //易发生的事故类型	
	
	@Column(name = "M7", nullable = true, length = 2000)
	@Setter
	@Getter
	private String M7;//主要防范措施
	
	@Column(name = "M8", nullable = true, length = 500)
	@Setter
	@Getter
	private String M8;//依据
	
	@Column(name = "M9", nullable = true, length = 500)
	@Setter
	@Getter
	private String M9;//备注
	
	
}



