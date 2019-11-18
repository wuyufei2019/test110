package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 安全生产执法--询问笔录
 * @author zpc
 * @date 2017年08月05日
 */
@Entity
@Table(name="xzcf_interrogationrecord")
public class XZCF_InterrogationRecordEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5117595642022874311L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//操作人ID
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID2;//立案处罚ID
	
	@Column(name = "M0", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String M0;//询问次号
	
	@Column(name = "M1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M1;//询问起始时间
	
	@Column(name = "M2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M2;//询问结束时间
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M3;//询问地址
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter	
	private String M4;//被询问人姓名
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M5;//性别
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M6;//年龄
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter	
	private String M7;//身份证号
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M8;//工作单位名称
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M9;//职务
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M10;//住址
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter	
	private String M11;//电话
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter	
	private String M12;//询问人
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter	
	private String M13;//记录人
	
	@Column(name = "M14", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M14;//在场人
	
	@Column(name = "M15", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M15;//行政执法人员
	
	@Column(name = "M16", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M16;//案由
	
	@Column(name = "M17", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M17;//询问记录

}
