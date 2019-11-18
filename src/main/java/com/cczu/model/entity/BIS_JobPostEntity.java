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
 * 企业基本信息-工种岗位信息
 *
 */
@Entity
@Table(name="bis_jobpostentity")
public class BIS_JobPostEntity  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号
	
	@Column(name = "M1", nullable = true, length = 50)
	@Setter
	@Getter
	private String M1;//工种（岗位）名称

	@Column(name = "M2", nullable = true, length = 20)
	@Setter
	@Getter
	private String M2;//编号

	@Column(name = "M3", nullable = true, length = 50)
	@Setter
	@Getter
	private String M3;//车间（装置）名称

	@Column(name = "M4", nullable = true, length = 10)
	@Setter
	@Getter
	private String M4;//装置区与岗位允许的最大人数

	@Column(name = "M5", nullable = true, length = 20)
	@Setter
	@Getter
	private String M5;//人员在岗时间段开始时间

	@Column(name = "M6", nullable = true, length = 20)
	@Setter
	@Getter
	private String M6;//人员在岗时间段结束时间

}
