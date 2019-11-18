package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 企业基本信息-车辆信息
 *
 */
@Entity
@Table(name="bis_car")
public class BIS_CarEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号
	
	@Column(name = "M1", nullable = true, length = 50)
	@Setter
	@Getter
	private String M1;//车牌号码

	@Column(name = "M2", nullable = true, length = 50)
	@Setter
	@Getter
	private String M2;//车型

	@Column(name = "M3", nullable = true, length = 50)
	@Setter
	@Getter
	private String M3;//驾驶员姓名

	@Column(name = "M4", nullable = true, length = 50)
	@Setter
	@Getter
	private String M4;//驾驶员手机号码

	@Column(name = "M5", nullable = true, length = 50)
	@Setter
	@Getter
	private String M5;//押送员姓名

	@Column(name = "M6", nullable = true, length = 50)
	@Setter
	@Getter
	private String M6;//押送员手机号码

	@Column(name = "M7", nullable = true, length = 200)
	@Setter
	@Getter
	private String M7;//允许行驶的线路

	@Column(name = "M8", nullable = true, length = 100)
	@Setter
	@Getter
	private String M8;//允许停泊的位置

	@Column(name = "M9", nullable = true, length = 50)
	@Setter
	@Getter
	private String M9;//允许停泊的时长

	@Column(name = "M10", nullable = true, length = 500)
	@Setter
	@Getter
	private String M10;//备注

}
