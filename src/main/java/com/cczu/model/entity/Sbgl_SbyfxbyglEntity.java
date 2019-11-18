package com.cczu.model.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: Sbgl_SbbyjhEntity
 * @Description: 设备管理-设备预防性保养管理
 * @author xj
 * @date 2018年8月9日
 *
 */
@Entity
@Table(name="sbgl_sbyfxbyglentity")
public class Sbgl_SbyfxbyglEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -7976901968936597830L;
	
	@Column(name = "qyid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long qyid;//企业id

	@Column(name = "sbname", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String sbname;//设备名称
	
	@Column(name = "m1", nullable = true,columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String m1;//维护项目/部位
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String m2;//维护要求
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String m3;//维护方法
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m4;//维护周期  日、周、月、季、半年、年
	
	@Column(name = "m5", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m5;//操作者
	
	@Column(name = "m6", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m6;//维修者
}
