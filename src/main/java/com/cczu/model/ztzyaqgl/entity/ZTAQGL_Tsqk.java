package com.cczu.model.ztzyaqgl.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * @ClassName: AQGL_Tsqk
 * @Description: 安全管理-特殊情况表
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="ztaqgl_tsqk")
public class ZTAQGL_Tsqk implements Serializable{
	
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
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M1;//内容
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M2;//安全作业类型（1:动火/2:受限空间/3:高处）
}
