package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 隐患排查---日常检查隐患信息
 * @author jason
 * @date 2017年8月17日
 */
@Entity
@Table(name="yhpc_dailyhiddeninfo")
public class YHPC_DailyHiddenInfoEntity extends BaseEntity {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -8266862137595575763L;
	
	@Column(name = "ID1",  columnDefinition="bigint")
	@Setter
	@Getter
	private Long  ID1  ;//检查记录id 
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  qyid  ;//企业id 

	@Column(name = "createtime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp createtime;//隐患发现时间
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M1;//问题描述
	
	@Column(name = "M1_1", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M1_1;//检查内容id

	@Column(name = "M2", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M2;//现场照片

	@Column(name = "M3", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M3;//隐患类别
	
	@Column(name = "M4", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M4;//计划完成时间
	
	@Column(name = "M5", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M5;//实际完成时间
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M6;//整改后照片
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M7;//隐患等级
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M8;//备注

	@Column(name = "M9", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M9;//整改人
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M10;//整改费用
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M11;//整改状态(0:未整改/1:整改未完成/2：整改完成/3:复查不通过/4:复查通过)
	
	@Column(name = "M12", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  M12 ;//隐患发现人id
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M13;//责任部门（下拉框存汉字）
	
	@Column(name = "M14", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M14;//发生区域	
	
	@Column(name = "M15", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M15;//解决措施
	
	@Column(name = "M16", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M16;//复查照片
	
	@Column(name = "M17", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M17;//复查备注
}
