package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: AQPX_CoursewareEntity
 * @Description: 企业-安全培训-课件库
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqpx_courseware")
public class AQPX_CoursewareEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6110911626256370020L;

	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ID1;//企业ID
	
	@Column(name = "ID2", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ID2;//课程ID
	
	@Column(name = "M1", nullable = true, length = 100)
	@Setter
	@Getter
	private String M1;//课件名称
	
	@Column(name = "M2", nullable = true, length = 500)
	@Setter
	@Getter
	private String M2;//文件地址

	@Column(name = "M3", nullable = true, length = 100)
	@Setter
	@Getter
	private String M3;//文件类型
	
	@Column(name = "M4", nullable = true, length = 500)
	@Setter
	@Getter
	private String M4;//转换成pdf的地址
	
}
