package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: BIS_WorkorderEntity
 * @Description: 企业基本信息-作业班次
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="bis_workorder")
public class BIS_WorkorderEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 7975395026341933703L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号

	@Column(name = "M1", nullable = true, length = 50)
	@Setter
	@Getter
	private String M1;//班次

	@Column(name = "M2", nullable = true )
	@Setter
	@Getter
	private String M2;//接班时间  0-23

	@Column(name = "M3", nullable = true )
	@Setter
	@Getter	
	private String M3;//交班时间    0-23

	@Column(name = "M4", nullable = true)
	@Setter
	@Getter	
	private int M4;//人数
	
	@Column(name = "M5", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M5;//备注

}
