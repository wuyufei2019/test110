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
 * 点巡检---巡检记录信息
 * @author zpc
 * @date 2018年3月2日
 */
@Entity
@Table(name="dxj_checkresult")
public class DXJ_CheckResultEntity implements Serializable {
 
	private static final long serialVersionUID = -8266862137595575763L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "checkpoint_id", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  checkpoint_id  ;//设备ID 
	
	@Column(name = "checkplan_id", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long checkplan_id ;//巡查班次ID 
	
	@Column(name = "checkresult", nullable = false, columnDefinition="varchar(1)")
	@Setter
	@Getter
	private String checkresult;//检查结果（1有隐患/0无隐患） 
	
	@Column(name = "checkphoto", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String checkphoto;//巡检图片
	
	@Column(name = "userid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  userid  ;//巡检人ID 
	
	@Column(name = "note", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String note;//巡检备注
	
	@Column(name = "createtime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp createtime;//巡检时间
 
	@Column(name = "lng", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String lng;//经度（手机定位信息）
	
	@Column(name = "lat", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String lat;//纬度（手机定位信息）

	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//企业id
}
