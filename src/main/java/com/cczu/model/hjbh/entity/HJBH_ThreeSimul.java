package com.cczu.model.hjbh.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 环境保护--三同时管理
 * @author wbth
 * @date 2018年6月20日
 */
@Entity
@Table(name="hjbh_threesimul")
public class HJBH_ThreeSimul extends BaseEntity{

	private static final long serialVersionUID = 1130232439817931540L;
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//企业ID

	@Column(name = "id1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long id1;//企业ID
	
	@Column(name = "M1", nullable = false, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M1;//企业名称 
	
	@Column(name = "M2", nullable = false, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M2;//所在乡镇
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String  M3;//项目名称 
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M4;//建设地址
	
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M5;//产能
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M6;//劳动定员
 
	@Column(name = "M7", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M7;//环评批复意见文号
	
	@Column(name = "M8", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M8;//批复时间
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M9;//试生产核准意见文号
	
	@Column(name = "M10", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M10;//核准时间
	
	@Column(name = "M11", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M11;//项目三同时验收时间
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M12;//产能核准量
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M13;//产污核准量

	@Column(name = "M14", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M14;//批准部门
	
	@Column(name = "M15", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M15;//核准部门
	
	@Column(name = "M16", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M16;//三同时验收部门
}
