package com.cczu.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 网格化管理---网格员月度绩效考核(考核结果)（详细信息）
 * @author jason
 * @date 2017年8月18日
 */
@Entity
@Table(name="yhpc_gridmankpimonth")
public class YHPC_GridManKpiMonth extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "id1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long id1;//总分表 yhpc_gridmankpimonthoverview id
	
	@Column(name = "id2", nullable = false, columnDefinition="int")
	@Setter
	@Getter
	private Long id2;//规则表id
	
	@Column(name = "score", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private float score;//评分
	
	@Column(name = "note", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String note;//评分备注
	
}
