package com.cczu.model.zyaqgl.entity;


import java.io.Serializable;

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
 * @ClassName: AQGL_Zyaq_Aqcs
 * @Description: 安全管理-安全措施中间表
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqgl_zyaq_aqcs")
public class AQGL_Zyaq_Aqcs implements Serializable{
	
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
	public long ID1;//安全作业id
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M1;//措施内容
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M2;//安全作业类型（1:动火/2:受限空间）
}
