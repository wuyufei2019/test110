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
 * 隐患排查---巡检隐患记录
 * @author jason
 * @date 2017年8月18日
 */
@Entity
@Table(name="yhpc_checkhiddeninfo")
public class YHPC_CheckHiddenInfoEntity implements Serializable {

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
	
	@Column(name = "sechandletime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp sechandletime;//计划整改时间
	
	@Column(name = "dangerphoto", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String dangerphoto;//隐患照片
	
	@Column(name = "createtime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp createtime;//照片拍摄时间
	
	@Column(name = "dangerdesc", nullable = true, columnDefinition="varchar(255)")
	@Setter
	@Getter
	private String dangerdesc;//隐患备注
	
	@Column(name = "handlepersons", nullable = true, columnDefinition="varchar(255)")
	@Setter
	@Getter
	private String handlepersons;//指定隐患整改人   多选
	
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
	
	@Column(name = "dangerstatus", nullable = true, columnDefinition="varchar(1)")
	@Setter
	@Getter
	private String dangerstatus;//隐患状态(初始  0未整改   1整改完成   2整改未完成   3已完成 )
	
	@Column(name = "dangerorigin", nullable = true, columnDefinition="varchar(1)")
	@Setter
	@Getter
	private String dangerorigin;//隐患来源(1企业自查点    2无点任务    3随手拍 4网格点)
	
	@Column(name = "userid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  userid  ;//隐患发现人ID 
	
	@Column(name = "hiddentype", nullable = true, columnDefinition="varchar(255)")
	@Setter
	@Getter
	private String hiddentype;//隐患类型
	
	@Column(name = "violationlevel", nullable = true, columnDefinition="varchar(255)")
	@Setter
	@Getter
	private String violationlevel;//违规级别
	
	@Column(name = "approveduser", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  approveduser  ;//审核人ID 
	
	@Column(name = "dangerlevel", nullable = true, columnDefinition="varchar(1)")
	@Setter
	@Getter
	private String dangerlevel;//隐患等级（1：一级2：二级3：三级4：四级）
	
}
