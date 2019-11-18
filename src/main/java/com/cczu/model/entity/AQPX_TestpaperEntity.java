package com.cczu.model.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: AQPX_TestpaperEntity
 * @Description: 企业-安全培训-员工考试记录表
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqpx_testpaper")
public class AQPX_TestpaperEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ID1;//企业ID
	
	@Column(name = "ID2", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ID2;//题库ID
	
	@Column(name = "ID3", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ID3;//员工ID
	
	@Column(name = "ID4", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ID4;//试卷规则ID
	
	@Column(name = "ID5", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ID5;//课程ID
	
	@Column(name = "H", nullable = true, length = 100)
	@Setter
	@Getter
	private String H;//试卷标识
	
	@Column(name = "M1", nullable = true)
	@Setter
	@Getter
	private int M1;//成绩分数
	
	@Column(name = "M2", nullable = true, length = 100)
	@Setter
	@Getter
	private String M2;//答题结果
	
	@Column(name = "M3", nullable = true)
	@Setter
	@Getter
	private Date M3;//考试时间
	
}
