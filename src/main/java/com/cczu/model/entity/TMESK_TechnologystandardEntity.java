package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: TMESK_TechnologystandardEntity
 * @Description: 安全专家知识库_安全生产技术标准
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="tmesk_technologystandard")
public class TMESK_TechnologystandardEntity extends BaseEntity{

	private static final long serialVersionUID = -1021172229852598044L;
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//创建者

	@Column(name = "M1", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String M1;//标题

	@Column(name = "M2", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M2;//正文

	@Column(name = "M3", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M3;//备注
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M4;//附件（word）
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M5;//附件（pdf）
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M6;//附件（swf）
}
