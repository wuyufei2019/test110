package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * @ClassName: YHPC_DailyCheckEntity
 * @Description: 隐患排查---日常隐患检查信息
 * @author 
 * @date 2018年06月25日
 *
 */
@Entity
@Table(name = "yhpc_dailyhiddencheck")
public class YHPC_DailyHiddenCheckEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
   
	@Column(name = "qyid", nullable = false,columnDefinition="bigint")
	@Setter
	@Getter
	private Long qyid;// 企业id 
   
	@Column(name = "M1", nullable = true,columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M1;// 检查日期
   
	@Column(name = "M2", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M2;// 责任部门
   
	@Column(name = "M3", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M3;// 辖区部门
   
	@Column(name = "M4", nullable = true,columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M4;// 现场照片
   
	@Column(name = "M5", nullable = true,columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M5;// 问题描述

	@Column(name = "M6", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M6;// 隐患类别

	@Column(name = "M6_1", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M6_1;// 检查类型
	
	@Column(name = "M6_2", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M6_2;// 缺失类型

	@Column(name = "M16", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M16;// 严重程度
   
	@Column(name = "M8", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M8;// 责任部门负责人

	@Column(name = "M18", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M18;// 检查人员

	@Column(name = "M19", nullable = true,columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M19;// 整改单号

	@Column(name = "M9", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M9;// 指定整改人


	@Column(name = "M10", nullable = true,columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M10;// 计划完成时间


	@Column(name = "M17", nullable = true,columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String M17;// 审核反馈

	@Column(name = "shstate", nullable = true,columnDefinition="varchar(5)")
	@Setter
	@Getter
	private String shstate;// 审核状态（0.待审核 1.审核通过 2.审核不通过 ）


	@Column(name = "M7", nullable = true,columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M7;// 解决措施

	@Column(name = "M12", nullable = true,columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M12;// 整改后照片

	@Column(name = "M15", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M15;// 整改费用

	@Column(name = "M11", nullable = true,columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M11;// 实际完成时间
   
	@Column(name = "M13", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M13;// 状态确认（ 1.待整改 2.整改待审核  3.审核不通过  4.整改完成）
   
	@Column(name = "M14", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M14;// 稽核人

	@Column(name = "M20", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M20;// 结案日期
}
