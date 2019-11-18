package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;


/**
 * 
 * @ClassName: BIS_MatEntity
 * @Description: 企业基本信息-公用工程
 *
 */
@Entity
@Table(name="bis_utilities")
public class BIS_Utilities extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4840044843393697511L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M1;//类别

	@Column(name = "M2", nullable = true, columnDefinition="float")
	@Setter
	@Getter	
	private Double M2;//占地面积
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M3;//建筑结构
	
	@Column(name="M4", nullable = true, columnDefinition="varchar(2000)")
	@Getter
	@Setter
	private String M4;//主要危险性
	
	@Column(name="M5", nullable = true, columnDefinition="varchar(2000)")
	@Getter
	@Setter
	private String M5;//照片
	
	@Column(name="M6", nullable = true, columnDefinition="varchar(2000)")
	@Getter
	@Setter
	private String M6;//备注
}
