package com.cczu.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 隐患排查---巡检记录信息
 * @author ll
 * @date 2019年11月4日
 */
@Entity
@Table(name="yhpc_checkresult2")
public class YHPC_CheckResult2Entity implements Serializable {
 
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
	
	@Column(name = "checkpointname", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String checkpointname;//检查点名称

	@Column(name = "checkorder", nullable = false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String checkorder;//所属班次名称

	@Column(name = "createtime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp createtime;//检查时间

	@Column(name = "checkperson", nullable = false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String checkperson;//检查人

	@Column(name = "checkresult", nullable = false, columnDefinition="varchar(1)")
	@Setter
	@Getter
	private String checkresult;//检查结果（1有隐患/0无隐患）

	@Column(name = "checkphoto", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String checkphoto;//现场附件

	@Column(name = "flag", nullable = false, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String flag;//标识（用于跟隐患关联,必填）

	@Column(name = "qyid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//编号

	@Column(name = "note", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String note;//巡检备注
}
