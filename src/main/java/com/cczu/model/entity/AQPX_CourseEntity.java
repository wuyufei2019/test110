package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * @ClassName: AQPX_CourseEntity
 * @Description: 企业-安全培训-课程信息表
 * @author jason
 *
 */
@Entity
@Table(name="aqpx_course")
public class AQPX_CourseEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7440973518403243606L;

	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ID1;//企业ID
	
	@Column(name = "ID2", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String ID2;//部门ID
	
	@Column(name = "M1", nullable = true, length = 200)
	@Setter
	@Getter
	private String M1;//课程名称
	
	@Column(name = "M2", nullable = true, length = 20)
	@Setter
	@Getter
	private String M2;//学时
	
	@Column(name = "M3", nullable = false, length = 8)
	@Setter
	@Getter
	private Integer M3;//学分
	
	@Column(name = "M4", nullable = true, columnDefinition = "varchar(max)")
	@Setter
	@Getter	
	private String M4;//附件
	
	@Column(name = "M5", nullable = true, length = 2)
	@Setter
	@Getter
	private String M5;//课程类别 （1、日常培训  2、三级教育培训  3、外来方培训）

}
