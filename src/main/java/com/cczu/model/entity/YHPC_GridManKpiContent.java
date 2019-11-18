package com.cczu.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 网格化管理---网格员月度绩效考核内容(规则)
 * @author jason
 * @date 2017年8月18日
 */
@Entity
@Table(name="yhpc_gridmankpicontent")
public class YHPC_GridManKpiContent extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private long id1;//网格 barrio id
	
	@Column(name = "name", nullable = false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String name;//评分项目
	
	@Column(name = "score", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	private Integer score;//总分
	
	@Column(name = "content", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String content;//考核内容
	
	@Column(name = "note", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String note;//备注
	
}
