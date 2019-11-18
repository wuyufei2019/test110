package com.cczu.model.sbssgl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-设备申请
 * @author ZPC
 */
@Entity
@Table(name = "sbssgl_sbsq")
public class SBSSGL_SBSQEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//企业id

	@Column(name = "m1", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m1;//请购部门
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m2;//请购人
	
	@Column(name = "m3", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp m3;//请购日期
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String m4;//请购资产明细
	
	@Column(name = "m5", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m5;//合计
	
	@Column(name = "m6", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m6;//供应商
	
	@Column(name = "m7", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m7;//请购理由类别（1.预算内2.预算外）
	
	@Column(name = "m8", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m8;//预算项目编号
	
	@Column(name = "m9", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String m9;//预算外理由
	
	@Column(name = "m10", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m10;//状态（0.待上传附件1.待审核2.通过3.不通过）
	
	@Column(name = "m11", nullable = true, columnDefinition="varchar(2000)")
	@Getter
	@Setter
	private String m11;//附件
	
	@Column(name = "m12", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m12;//是否全部验收（0.未全部验收1.全部验收）
	
}
