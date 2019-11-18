package com.cczu.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 安全监督--第三方现场检查内容
 * @author jason
 * @date 2017年7月26日
 */
@Entity
@Table(name="aqjd_dsfsafetycheckcontent")
public class AQJD_DSFCheckContentEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7133209152728855819L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//第三方检查记录ID（AQJD_DSFCheckRecordEntity	）

	@Column(name = "M1", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M1;//检查意见
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M2;//检查照片
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M3;//复查意见
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M4;//复查照片
	
	@Column(name = "M5", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	private Integer M5;//检查结果（1已处理，0未处理）
}
