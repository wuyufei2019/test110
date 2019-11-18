package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: AQPX_PlancourseEntity
 * @Description: 企业-安全培训-培训计划课程
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqpx_plancourse")
public class AQPX_PlancourseEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5479749228150145331L;

	
	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ID1;//培训计划ID
	
	@Column(name = "ID2", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ID2;//课程ID
	
	@Column(name = "ID3", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ID3;//企业ID
	
	@Column(name = "ID4", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ID4;//员工ID
	
}
