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
 * 有毒气体信息
 * @author jason
 * @date 2017年8月29日
 */
@Entity
@Table(name="bis_sensor")
public class Bis_SensorEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8359876181232918926L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//自动编号
	
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//企业ID
	
	@Column(name="m1", nullable=false, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String M1;//位号
	
	@Column(name="m2", nullable=true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String M2;//气体名称
	
	@Column(name="m3", nullable=true, columnDefinition="varchar(20)" )
	@Setter
	@Getter
	private String M3;//气体类型
	
	@Column(name="level1", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Float level1;//一级预警
	
	@Column(name="level2", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Float level2;//二级预警
	
	@Column(name="r1", nullable=true, columnDefinition="varchar(30)")
	@Setter
	@Getter
	private String r1;//传感器位置
	
	@Column(name="r2", nullable=true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String r2;//保留
	
}
