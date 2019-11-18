package com.cczu.model.entity;

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
 * 安全生产执法--字典
 * @author jason
 * @date 2017年8月3日
 */
@Entity
@Table(name="aqzf_dict")
public class AQZF_DictEntity  implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5888403945961535718L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(400)")
	@Setter
	@Getter
	private String M1;//标签
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M2;//类型
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M3;//类型编码
	
	@Column(name = "M4", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	private Integer M4;//排序

}
