package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @Description: 行政处罚-陈述申辩笔录
 * @author who
 * @date 2017年7月29日
 * 
 */
@Entity
@Table(name = "xzcf_cssbbl")
public class XZCF_CssbblEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = false, columnDefinition = "bigint")
	@Setter
	@Getter
	private long ID1;// （立案审批id）
	
	@Column(name = "M1", nullable = true, columnDefinition = "datetime")
	@Setter
	@Getter
	private Timestamp M1;// 申辩开始时间
	
	@Column(name = "M2", nullable = true, columnDefinition = "datetime")
	@Setter
	@Getter
	private Timestamp M2;// 申辩结束时间
	
	@Column(name = "M3", nullable = true, columnDefinition = "varchar(200)")
	@Setter
	@Getter
	private String M3;// 地点
	
	@Column(name = "M4", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M4;// 陈述申辩人
	
	@Column(name = "M5", nullable = true, columnDefinition = "varchar(10)")
	@Setter
	@Getter
	private String M5;// 性别
	
	@Column(name = "M6", nullable = true, columnDefinition = "varchar(100)")
	@Setter
	@Getter
	private String M6;// 职务
	
	@Column(name = "M7", nullable = true, columnDefinition = "varchar(200)")
	@Setter
	@Getter
	private String M7;// 工作单位
	
	@Column(name = "M8", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M8;// 电话
	
	@Column(name = "M9", nullable = true, columnDefinition = "varchar(200)")
	@Setter
	@Getter
	private String M9;// 联系地址
	
	@Column(name = "M10", nullable = true, columnDefinition = "varchar(10)")
	@Setter
	@Getter
	private String M10;// 邮编
	
	@Column(name = "M11", nullable = true, columnDefinition = "varchar(200)")
	@Setter
	@Getter
	private String M11;// 案件名称
	
	@Column(name = "M12", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M12;// 执法人员1
	
	@Column(name = "M13", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M13;// 执法人员1证号
	
	@Column(name = "M14", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M14;// 执法人员2
	
	@Column(name = "M15", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M15;// 执法人员2证号
	
	@Column(name = "M16", nullable = true, columnDefinition = "varchar(5000)")
	@Setter
	@Getter
	private String M16;// 陈述申辩记录
	
	@Column(name = "M17", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M17;// 记录人
}
