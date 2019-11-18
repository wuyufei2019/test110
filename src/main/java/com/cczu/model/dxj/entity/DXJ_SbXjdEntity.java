package com.cczu.model.dxj.entity;

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
 * 点巡检--设备巡检点
 * @author zpc
 * @date 2018年3月01日
 */
@Entity
@Table(name="dxj_sbxjd")
public class DXJ_SbXjdEntity implements Serializable{

	private static final long serialVersionUID = 6322130668360738922L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  ID1;//企业ID
	
	@Column(name = "ID2", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  ID2;//设备ID
	
	@Column(name = "name", nullable = false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String name;//设备巡查点项目名称
	
	@Column(name = "standard", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String standard;//标准
	
	@Column(name = "scope", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String scope;//范围
	
	@Column(name = "checkmethod", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String checkmethod;//点检方法

	@Column(name = "createtime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp createtime;//创建时间
}
