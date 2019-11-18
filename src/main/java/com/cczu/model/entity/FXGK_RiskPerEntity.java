package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 *风险值配置比例
 */
@Entity
@Table(name="fxgk_riskper")
public class FXGK_RiskPerEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "M1")
	@Setter
	@Getter	
	private Float M1;//风险值配置比例：红色
	
	@Column(name = "M2")
	@Setter
	@Getter	
	private Float M2;//风险值配置比例：橙色
	
	@Column(name = "M3")
	@Setter
	@Getter	
	private Float M3;//风险值配置比例：黄色
	
	@Column(name = "M4")
	@Setter
	@Getter	
	private Float M4;//风险值配置比例：蓝色
	
	@Column(name = "M5")
	@Setter
	@Getter	
	private Float M5;//风险值配置比例：橙色界限
	
	@Column(name = "M6")
	@Setter
	@Getter	
	private Float M6;//风险值配置比例：黄色界限
	
	@Column(name = "M7")
	@Setter
	@Getter	
	private Float M7;//风险值配置比例：蓝色界限

	@Override
	public String toString() {
		return "RiskPerEntity [M1=" + M1 + ", M2=" + M2 + ", M3=" + M3
				+ ", M4=" + M4 + "]";
	}

}
