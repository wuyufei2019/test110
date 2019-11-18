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
 * 隐患排查---巡检记录信息
 * @author jason
 * @date 2017年8月17日
 */
@Entity
@Table(name="yhpc_checkresult")
public class YHPC_CheckResultEntity implements Serializable {
 
	/**
	 * 
	 */
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
	private Long  checkpoint_id  ;//隐患点ID/风险点ID 
	
	@Column(name = "checkpointtype", nullable = true, columnDefinition="char(2)")
	@Setter
	@Getter
	private String checkpointtype;//巡查点类型（1风险点/2隐患排查点） 
	
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
	public Long qyid;//编号
	
	@Column(name = "starttime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp starttime;//开始检查时间
	
	@Column(name = "endtime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp endtime;//结束检查时间
}
