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
 * @Description: 行政处罚-调查证据
 * @author who
 * @date 2017年12月20日
 * 
 */
@Entity
@Table(name = "xzcf_dczj")
public class XZCF_DczjEntity implements Serializable {
	
	
	private static final long serialVersionUID = -1528033644109760656L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  ID1  ;//调查报告id
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M1;//证据
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String M2;//照片
	
}
