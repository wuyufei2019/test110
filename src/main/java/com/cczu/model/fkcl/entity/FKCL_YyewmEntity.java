package com.cczu.model.fkcl.entity;

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
 * 预约二维码
 * @author ZPC
 */
@Entity
@Table(name = "fkcl_yyewm")
public class FKCL_YyewmEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号

	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//企业id
	
	@Column(name = "ewm", nullable = false, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String ewm;//二维码
	
	@Column(name = "type", nullable = false, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String type;//类型：1.访客2.车辆
}
