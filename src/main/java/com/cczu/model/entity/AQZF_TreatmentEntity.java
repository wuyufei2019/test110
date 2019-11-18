package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 安全生产执法--现场处理措施
 * @author jason
 * @date 2017年7月26日
 */
@Entity
@Table(name="aqzf_treatment")
public class AQZF_TreatmentEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3907830051481624175L;
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//添加人id
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID2;//企业ID
	
	@Column(name = "ID3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID3;//检查记录ID
	
	@Column(name = "M0", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M0;//检查记录编号

	@Column(name = "M1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M1;//检查日期
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M2;//事故隐患
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String M3;//依据规定
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M4;//处理决定
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M5;//申诉政府
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M6;//诉讼法院
	
	@Column(name = "M7_1", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M7_1;//执法人员1
	
	@Column(name = "M7_2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M7_2;//执法人员2

	@Column(name = "M8_1", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M8_1;//执法人员证号1
	
	@Column(name = "M8_2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M8_2;//执法人员证号2
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M9;//被检查单位负责人
}
