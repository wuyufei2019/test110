package com.cczu.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 隐患排查---巡检隐患记录
 * @author jason
 * @date 2017年8月18日
 */
@Entity
@Table(name="yhpc_checkhiddeninfo2")
public class YHPC_CheckHiddenInfo2Entity implements Serializable {

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

	@Column(name = "checkresult_flag", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String  checkresult_flag  ;//巡查记录标识

	@Column(name = "checkpointname", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String checkpointname;//检查点名称

	@Column(name = "checkcontent", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String checkcontent;//检查内容

	@Column(name = "dangerstatus", nullable = true, columnDefinition="varchar(1)")
	@Setter
	@Getter
	private String dangerstatus;//隐患状态(初始  0未整改   1整改完成   2整改未完成   3已完成 )

	@Column(name = "findperson", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String findperson;//隐患发现人

	@Column(name = "createtime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp createtime;//隐患发现时间

	@Column(name = "sechandletime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp sechandletime;//计划整改时间

	@Column(name = "handlepersons", nullable = true, columnDefinition="varchar(255)")
	@Setter
	@Getter
	private String handlepersons;//指定隐患整改人   多选

	@Column(name = "dangerdesc", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String dangerdesc;//隐患备注

	@Column(name = "dangerphoto", nullable = true, columnDefinition="varchar(max)")
	@Setter
	@Getter
	private String dangerphoto;//隐患附件

	@Column(name = "reformpersons", nullable = true, columnDefinition="varchar(255)")
	@Setter
	@Getter
	private String reformpersons;//整改人

	@Column(name = "reformdesc", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String reformdesc;//整改备注

	@Column(name = "reformphoto", nullable = true, columnDefinition="varchar(max)")
	@Setter
	@Getter
	private String reformphoto;//整改附件

	@Column(name = "reformtime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp reformtime;//整改时间

	@Column(name = "qyid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  qyid  ;//企业ID 
	
}
