package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 企业基本信息-受限空间信息
 * @author jason
 * @date 2017年7月18日
 */
@Entity
@Table(name="bis_confinedspace")
public class BIS_ConfinedSpaceEntity extends BaseEntity {

	private static final long serialVersionUID = 1008731853554063386L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号
	
	@Column(name = "ID2", nullable = true, length = 8)
	@Setter
	@Getter
	private Long ID2;//操作者id

	@Column(name = "M1", nullable = true )
	@Setter
	@Getter
	private int M1;//有无

	@Column(name = "M2", nullable = true)
	@Setter
	@Getter
	private Integer M2;//数量

	@Column(name = "M3", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M3;//位置
	
	@Column(name = "M4", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M4;//应急设施
	
	@Column(name = "M5", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M5;//受限空间名称
	
	@Column(name = "M6", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M6;//主要危险有害物质
	
	@Column(name = "M7", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M7;//易导致事故类型
	
	@Column(name = "M8", nullable = true, length = 2000)
	@Setter
	@Getter	
	private String M8;//安全设施（预防/应急）
	
	@Column(name = "M9", nullable = true)
	@Setter
	@Getter	
	private String M9;//备注
	

}
