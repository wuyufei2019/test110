package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 企业基本信息-工艺方块图信息
 *
 */
@Entity
@Table(name="bis_craftsquare")
public class BIS_CraftSquareEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号
	
	@Column(name = "ID2", nullable = true, length = 8)
	@Setter
	@Getter
	private int ID2;//操作者id

	@Column(name = "name", nullable = true, length = 20)
	@Setter
	@Getter
	private String name;//工艺名称
	
	@Column(name = "introduction", nullable = true)
	@Setter
	@Getter	
	private String introduction;//简介
	
	@Column(name = "url", nullable = true, length = 500)
	@Setter
	@Getter	
	private String url;//工艺图地址

}
