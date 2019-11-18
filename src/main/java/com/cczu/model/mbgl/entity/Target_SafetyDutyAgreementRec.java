package com.cczu.model.mbgl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: Target_SafetyDutyAgreementRec
 * @Description: 目标管理_安全责任书接收表
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="target_safetydutyagreementrec")
public class Target_SafetyDutyAgreementRec extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -5327001961148464120L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//安全责任书id
	
	@Column(name = "ID3", nullable = false, columnDefinition="int")
	@Setter
	@Getter
	public Long ID3;//部门成员（用户）id
	
	@Column(name = "gwname", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String gwname;//岗位名称
	
	@Column(name = "pername", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String pername;//姓名
	
	@Column(name = "phone", nullable = true,columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String phone;//电话
	
	@Column(name = "signtime", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp signtime;//责任书签订时间

	@Column(name = "url", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String url;//责任书上传（已签名的pdf或者图片格式 文件）附件
	
	@Column(name = "note", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String note;//备注
}
