package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: AQPX_ExamresultsEntity
 * @Description: 企业-安全培训-考试成绩记录
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqpx_examresults")
public class AQPX_ExamresultsEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID1;//企业ID
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID2;//学员ID
	
	@Column(name = "ID3", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID3;//课程ID
	
	@Column(name = "ID4", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID4;//计划ID
	
	@Column(name = "H", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String H;//试卷标识
	
	@Column(name = "M1", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	private int M1;//成绩
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M2;//考试时长(秒)
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M3;//成绩结果（合格、不合格）

}
