package com.cczu.model.ztzyaqgl.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @ClassName: AQGL_DhzyFxEntity
 * @Description: 安全管理-动火作业分析表
 * @author ll
 * @date 2018年1月3日
 *
 */
@Entity
@Table(name="ztaqgl_dhzyfx")
public class ZTAQGL_DhzyFxEntity implements Serializable {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//动火作业id
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M1;//分析点名称
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M2;//介质

	@Column(name = "M2_1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M2_1;//数值
	
	@Column(name = "M2_2", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M2_2;//单位
	
	@Column(name = "M3", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M3;//分析时间
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M4;//分析人
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(max)")
	@Setter
	@Getter	
	private String M5;//现场照片
}
