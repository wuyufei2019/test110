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
 * @ClassName: AQGL_SaftyMeasure
 * @Description: 安全管理-安全 措施
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqgl_saftymeasure")
public class AQGL_SaftyMeasure implements Serializable{
	
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
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M1;//安全措施
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String M2;//安全类别（1：动火，2：受限空间）
}
