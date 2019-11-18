package com.cczu.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 第三方评价记录
 * @author zpc
 * @date 2017/07/10
 */
@Entity
@Table(name = "aqjg_dsfcomment")
public class AQJG_DSFPjEntity implements Serializable{
	
	private static final long serialVersionUID = -664067905704589121L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//自动编号
	
	@Column(name = "M1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M1;//第三方ID
	
	@Column(name="M2", nullable=false, columnDefinition="int")
	@Setter
	@Getter
	private Integer M2;//评价
	
	@Column(name = "M3", nullable = false, columnDefinition = "datetime")
	@Setter
	@Getter
	private Timestamp M3;// 评价时间
	
	@Column(name = "M4", nullable = false, columnDefinition = "int")
	@Getter
	@Setter
	private Integer M4;// 评价年度
	
	@Column(name = "M5", nullable = true, columnDefinition = "varchar(50)")
	@Getter
	@Setter
	private String M5;// 评价人
	
	@Column(name = "M6", nullable = true, columnDefinition = "varchar(200)")
	@Getter
	@Setter
	private String M6;// 备注
}
