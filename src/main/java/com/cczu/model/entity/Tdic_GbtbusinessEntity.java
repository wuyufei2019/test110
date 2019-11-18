package com.cczu.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @ClassName: Tdic_GBT4754Entity
 * @Description: 字典-国民经济行业分类表
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="tdic_gbtbusiness")
@DynamicUpdate @DynamicInsert
public class Tdic_GbtbusinessEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//编号
	
	@Column(name="CID", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String CID;//ID
	
	@Column(name="CNAME", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String CNAME;//名称
	
	@Column(name="CPID", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String CPID;//父ID
}
