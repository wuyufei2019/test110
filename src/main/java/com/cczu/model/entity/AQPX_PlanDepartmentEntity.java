package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;
/**
 * 
 * @ClassName: AQPX_PlanDepartmentEntity
 * @Description: 企业-安全培训-培训计划部门
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqpx_plandepartment")
public class AQPX_PlanDepartmentEntity extends BaseEntity  {
	
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
	private Long ID2;//培训计划ID
	
	@Column(name = "ID3", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ID3;//培训部门ID
	
	

}
