package com.cczu.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;


/**
 * 企业基本信息-燃气信息
 *
 */
@Entity
@Table(name="bis_gas")
public class BIS_GasEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2154823992251853399L;

	@Column(name = "id1", nullable = true, length = 8)
	@Setter
	@Getter
	private Long id1;//企业编号

	@Column(name = "m1", nullable = true, length = 20)
	@Setter
	@Getter
	private String m1;//燃气类别

	@Column(name = "m2", nullable = true, length = 20 )
	@Setter
	@Getter
	private String m2;//储存类型

	@Column(name = "m3", nullable = true )
	@Setter
	@Getter	
	private Integer m3;//储罐数量

	@Column(name = "m4", nullable = true)
	@Setter
	@Getter	
	private Float m4;//储罐容积
	
	@Column(name = "m5", nullable = true )
	@Setter
	@Getter	
	private Integer m5;//瓶组数量
	
	@Column(name = "m6", nullable = true )
	@Setter
	@Getter	
	private Float m6;//瓶组体积
	
	@Column(name = "m7", nullable = true )
	@Setter
	@Getter	
	private Float m7;//管道月用量

}
