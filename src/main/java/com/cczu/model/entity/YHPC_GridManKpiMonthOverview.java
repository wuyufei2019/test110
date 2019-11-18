package com.cczu.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 网格化管理---网格员月度绩效考核(考核结果)
 * @author jason
 * @date 2017年8月18日
 */
@Entity
@Table(name="yhpc_gridmankpimonthoverview")
public class YHPC_GridManKpiMonthOverview extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "id1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long id1;//网格员id
	
	@Column(name = "time", nullable = false, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String time;//评分年-月 
	
	@Column(name = "score", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private float score;//总分
	
}
