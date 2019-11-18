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
 * @ClassName: AQGL_SxkjzyFxEntity
 * @Description: 安全管理-受限空间作业分析表
 * @author zpc
 * @date 2018年1月3日
 *
 */
@Entity
@Table(name="aqgl_sxkjzyfx")
public class AQGL_SxkjzyFxEntity implements Serializable {
	
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
	public Long ID1;//受限空间作业id
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M1;//有毒有害介质
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M2;//可燃气
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M3;//含氧量
	
	@Column(name = "M4", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M4;//时间
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M5;//部位
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M6;//分析人
}
