package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 安全生产执法--复查意见
 * @author jason
 * @date 2017年7月26日
 */
@Entity
@Table(name="aqzf_review")
public class AQZF_ReviewEntity extends BaseEntity {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 602884776842097627L;

	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//责令整改记录ID
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID2;//被检查单位ID
	
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
	private String M2;//改正问题
	
	@Column(name = "M3_1", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M3_1;//执法人员1
	
	@Column(name = "M3_2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M3_2;//执法人员2

	@Column(name = "M4_1", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M4_1;//执法人员证号1
	
	@Column(name = "M4_2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M4_2;//执法人员证号2
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M5;//被检查单位负责人
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M6;//提出意见
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M7;//整改照片
}
