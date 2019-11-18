package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 企业基本信息-卡口信息
 *
 */
@Entity
@Table(name="bis_bayonet")
public class BIS_BayonetEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号
	
	@Column(name = "M1", nullable = true, length = 50)
	@Setter
	@Getter
	private String M1;//卡口名称

	@Column(name = "M2", nullable = true, length = 100)
	@Setter
	@Getter
	private String M2;//卡口位置

	@Column(name = "M3", nullable = true, length = 50)
	@Setter
	@Getter
	private String M3;//卡口设备

	@Column(name = "M4", nullable = true)
	@Setter
	@Getter
	private String M4;//卡口图片
}
