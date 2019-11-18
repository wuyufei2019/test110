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
 * 隐患排查---随手拍隐患审核记录
 * @author zpc
 * @date 2018年1月6日
 */
@Entity
@Table(name="yhpc_checkhiddeninfo_approve")
public class YHPC_CheckHiddenInfoApproveEntity implements Serializable {

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
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  qyid  ;//企业ID 
	
	@Column(name = "dangerstatus", nullable = true, columnDefinition="varchar(1)")
	@Setter
	@Getter
	private String dangerstatus;//隐患状态(初始  0未整改 )
	
	@Column(name = "dangerorigin", nullable = true, columnDefinition="varchar(1)")
	@Setter
	@Getter
	private String dangerorigin;//隐患来源(3随手拍)
	
	@Column(name = "userid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  userid  ;//隐患发现人ID 
	
	@Column(name = "approvestatue", nullable = true, columnDefinition="varchar(1)")
	@Setter
	@Getter
	private String approvestatue;//审核状态（0：待审核1：已审核）
	
	@Column(name = "approveduser", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  approveduser  ;//审核人ID 
	
	@Column(name = "dangerlevel", nullable = true, columnDefinition="varchar(1)")
	@Setter
	@Getter
	private String dangerlevel;//隐患等级（0:无隐患1：一级2：二级3：三级4：四级）
	
	@Column(name = "handlepersons", nullable = true, columnDefinition="varchar(255)")
	@Setter
	@Getter
	private String handlepersons;//指定隐患整改人
}
