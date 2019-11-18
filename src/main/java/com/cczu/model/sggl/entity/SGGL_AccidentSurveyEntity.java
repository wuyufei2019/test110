package com.cczu.model.sggl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: SGGL_AccidentSurveyEntity
 * @Description: 事故调查
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="sggl_accidentsurvey")
public class SGGL_AccidentSurveyEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -869831118280016002L;
	
	@Column(name = "M1", nullable = true, length = 100)
	@Setter
	@Getter
	private String M1;//事故名称
	
	@Column(name = "M2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M2;//发生时间

	@Column(name = "M3", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Float M3;//经济损失(万元)
	
	@Column(name = "M4", nullable = true, columnDefinition="float")
	@Setter
	@Getter	
	private Float M4;//伤亡人数
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M5;//事故调查报告
	
	@Column(name = "qyid", nullable = true, length = 8)
	@Setter
	@Getter
	public Long qyid;//企业id
}
