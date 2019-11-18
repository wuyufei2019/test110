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
 * @ClassName: AQGL_Criteria
 * @Description: 安全管理-考评标准
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqgl_criteria")
public class AQGL_Criteria implements Serializable{
	
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
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M1;//分类
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M2;//考评内容
	
	@Column(name = "M3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Integer M3;//记分
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M4;//抵扣款(元)
}
