package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 安全生产执法--安全检查单元
 * @author jason
 * @date 2017年7月26日
 */
@Entity
@Table(name="aqzf_safetycheckunit")
public class AQZF_SafetyCheckUnitEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6322130668360738922L;
	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//检查单元

}
