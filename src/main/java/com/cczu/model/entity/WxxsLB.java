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
 * 物料类别危险系数
 * @author jason
 *
 */

@Entity
@Table(name = "wxxs_wllb")
public class WxxsLB implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7663726916590467352L;
	 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID",  nullable = false,columnDefinition="int")
	@Setter
	@Getter
	private Integer ID; 

	 
	@Column(name = "m1",nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m1; //物料类别

	@Column(name = "m2" ,nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Float m2; //危险系数

	 
}
