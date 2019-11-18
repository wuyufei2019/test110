package com.cczu.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 安全生产执法--现场检查内容
 * @author jason
 * @date 2017年7月26日
 */
@Entity
@Table(name="aqzf_safetycheckcontent")
public class AQZF_SafetyCheckContentEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7133209152728855819L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//检查记录ID（AQZF_SafetyCheckRecordEntity）
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID2;//检查内容ID（AQZF_SafetyCheckItemEntity）
	

	@Column(name = "M1", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	private Integer M1;//检查结果（1是，0否）

	@Column(name = "M2", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M2;//存在问题

	@Column(name = "M3", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M3;//备注
	
	@Column(name = "M4", nullable = true, length = 1000)
	@Setter
	@Getter	
	private String M4;//附件
}
