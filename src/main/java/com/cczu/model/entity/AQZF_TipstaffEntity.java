package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 安全生产执法--执法人员信息
 * @author jason
 * @date 2017年8月2日
 */
@Entity
@Table(name="aqzf_tipstaff")
public class AQZF_TipstaffEntity  extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8302481136041415248L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1; //创建者id

	@Column(name = "M1", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	public String M1; //姓名
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String M2; //性别
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(25)")
	@Setter
	@Getter
	public String M3; //证件号
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	public String M4; //职称
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	public String M5; //联系电话
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	public String M6; //所属行政区域
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	public String M7; //专家类别
}
