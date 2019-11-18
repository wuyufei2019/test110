package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: BIS_WorkshopEntity
 * @Description: 企业基本信息-车间信息
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="bis_workshop")
public class BIS_WorkshopEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -1017245482089189466L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号
	
	@Column(name = "M1", nullable = true, length = 20)
	@Setter
	@Getter
	private String M1;//车间名称
	
	@Column(name = "M2", nullable = true, length = 20)
	@Setter
	@Getter
	private String M2;//车间编号
	
	@Column(name = "M3", nullable = true)
	@Setter
	@Getter	
	private Double M3;//建筑面积
	
	@Column(name = "M4", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M4;//火灾危险等级
	
	@Column(name = "M5", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M5;//建筑结构
	
	@Column(name = "M6", nullable = true)
	@Setter
	@Getter	
	private Integer M6;//层数
	
	@Column(name = "M7", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M7;//备注
	
	@Column(name = "M8", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M8;//耐火等级
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M9;//现场照片
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M10;//图纸
}
