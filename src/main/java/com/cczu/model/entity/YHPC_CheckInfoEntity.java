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
 * 隐患排查---巡检隐患记录
 * @author jason
 * @date 2017年8月18日
 */
@Entity
@Table(name="yhpc_checkinfo")
public class YHPC_CheckInfoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4659242572434313161L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "checkresult_id", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  checkresult_id  ;//巡查记录ID 
	
	@Column(name = "dangerdesc", nullable = true, columnDefinition="varchar(255)")
	@Setter
	@Getter
	private String dangerdesc;//巡检备注
	
	@Column(name = "checkcontent_id", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  checkcontent_id  ;//巡检内容ID 
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  qyid  ;//企业ID 
	
	@Column(name = "pointid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  pointid  ;//巡检点ID 
	
	@Column(name = "checkpointtype", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long checkpointtype;//巡检点类型(1.风险点 2.检查点)
	
	@Column(name = "dangerorigin", nullable = true, columnDefinition="varchar(1)")
	@Setter
	@Getter
	private String dangerorigin;//来源(1企业自查点    2无点任务    3随手拍 4网格点)
	
	@Column(name = "userid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  userid  ;//发现人ID 
	
}
