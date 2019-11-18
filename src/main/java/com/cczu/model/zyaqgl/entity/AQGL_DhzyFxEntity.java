package com.cczu.model.zyaqgl.entity;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @ClassName: AQGL_DhzyFxEntity
 * @Description: 安全管理-动火作业分析表
 * @author ll
 * @date 2018年1月3日
 *
 */
@Entity
@Table(name="aqgl_dhzyfx")
public class AQGL_DhzyFxEntity implements Serializable {
	
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
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M2;//分析数据
	
	@Column(name = "M3", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M3;//分析时间
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M4;//分析人
}
