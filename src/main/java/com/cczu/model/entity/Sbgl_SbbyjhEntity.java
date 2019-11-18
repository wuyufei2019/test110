package com.cczu.model.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: Sbgl_SbbyjhEntity
 * @Description: 设备管理-设备保养计划
 * @author xj
 * @date 2018年8月9日
 *
 */
@Entity
@Table(name="sbgl_sbbyjhentity")
public class Sbgl_SbbyjhEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -6748128936760700020L;
	
	@Column(name = "qyid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long qyid;//企业id

	@Column(name = "sblb", nullable = true, columnDefinition="varchar(8)")
	@Setter
	@Getter
	private String sblb;//设备类别  tzsb：特种设备   scsb：生产设备     aqss：安全设施

	@Column(name = "sbname", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String sbname;//设备名称
	
	@Column(name = "sbxh", nullable = true,columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String sbxh;//型号规格
	
	@Column(name = "sbbh", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String sbbh;//设备编号
	
	@Column(name = "m1", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m1;//保养项目
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m2;//保养级别  0:例行保养 1:一级保养  2:二级保养
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m3;//例行保养周期
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m4;//一级保养周期
	
	@Column(name = "m5", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m5;//二级保养周期
	
	@Column(name = "m6", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String m6;//保养计划名称
	
	@Column(name = "m7", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m7;//制定人
	
	@Column(name = "m8", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp m8;//制定时间
}
