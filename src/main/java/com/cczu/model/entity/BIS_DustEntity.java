package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 企业基本信息-粉尘信息
 *
 */
@Entity
@Table(name="bis_dust")
public class BIS_DustEntity extends BaseEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1235738003259479679L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号
	
	@Column(name = "ID2", nullable = true, length = 8)
	@Setter
	@Getter
	private Long ID2;//操作者id

	@Column(name = "M1", nullable = true, length = 20)
	@Setter
	@Getter
	private String M1;//粉尘种类

	@Column(name = "M2", nullable = true )
	@Setter
	@Getter
	private Integer M2;//涉粉作业人数

	@Column(name = "M3", nullable = true )
	@Setter
	@Getter	
	private Integer M3;//涉粉单班作业人数

	@Column(name = "M4", nullable = true)
	@Setter
	@Getter	
	private int M4;//是否取缔
	
	@Column(name = "M5", nullable = true)
	@Setter
	@Getter	
	private int M5;//是否关闭
	
	@Column(name = "M6", nullable = true )
	@Setter
	@Getter	
	private int M6;//是否有除尘器
	
	@Column(name = "M7", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M7;//除尘器种类
	
	@Column(name = "M8", nullable = true)
	@Setter
	@Getter	
	private int M8;//是否建立粉尘清扫制度
	
	@Column(name = "M9", nullable = true )
	@Setter
	@Getter	
	private int M9;//是否安装监控
	
	@Column(name = "M10", nullable = true, length = 1000)
	@Setter
	@Getter	
	private String M10;//备注
	
	@Column(name = "M11", nullable = true )
	@Setter
	@Getter	
	private int M11;//是否通过验收
	
	@Column(name = "M12", nullable = true )
	@Setter
	@Getter
	private Integer M12;//除尘器数量
	
	@Column(name = "M13", nullable = true)
	@Setter
	@Getter	
	private Integer M13;//是否涉爆
	
	@Column(name = "M14", nullable = true)
	@Setter
	@Getter	
	private Integer M14;//是否为职业病危害因素
	
	


}
