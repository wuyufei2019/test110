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
 * @ClassName: AQJD_CheckRecordEntity
 * @Description: 安全监督管理_检查记录（企业自查信息）
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqjd_checkrecord")
public class AQJD_CheckRecordEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "ID1", nullable = false,  columnDefinition = "int")
	@Setter
	@Getter
	public long ID1;//检查工作计划的id
	
	
	@Column(name = "ID2", nullable = false,  columnDefinition = "int")
	@Setter
	@Getter
	public long ID2;//企业的id
	
	
	@Column(name = "ID3", nullable = false,  columnDefinition = "int")
	@Setter
	@Getter
	public long ID3;//用户的id
	

	@Column(name = "M1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M1;//专项检查名称

	@Column(name = "M2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M2;//检查时间

	@Column(name = "M3", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M3;//检查人员姓名
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M4;//隐患描述
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M5;//主要责任人整改负责人
	
	@Column(name = "M6", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M6;//主要负责人整改日期
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M7;//初检照片附件
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M8;//企业初检附件地址
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M9;//复查人员姓名
	
	@Column(name = "M10", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M10;//复查时间
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M11;//复查意见
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M12;//复查照片
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M13;//复查文件

	@Column(name = "Checkflag", nullable = true, columnDefinition="varchar(2)")
	@Setter
	@Getter	
	private String checkflag;//检查标志位--0：初检，1：复检 ;2:安监初检：3；安监复检
	
}
