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
 * 点巡检---巡检班次信息
 * @author zpc
 * @date 2018年3月2日
 */
@Entity
@Table(name="dxj_checkplan")
public class DXJ_CheckPlanEntity implements Serializable {
	
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
	private Long  ID1  ;//企业ID
	
	@Column(name = "name", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String name;//班次名称
	
	@Column(name = "type", nullable = true, columnDefinition="char(1)")
	@Setter
	@Getter
	private String type;//班次类型 (1.日2.周3.月4.年)
	
	@Column(name = "userid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  userid  ;//建立人ID
	
	@Column(name = "createtime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp createtime;//建立时间
}
