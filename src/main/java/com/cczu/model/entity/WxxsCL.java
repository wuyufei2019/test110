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
 * 物料储量危险系数
 * @author jason
 *
 */

@Entity
@Table(name = "wxxs_wlcl")
public class WxxsCL implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1079715472805015220L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID",  nullable = false,columnDefinition="int")
	@Setter
	@Getter
	private Integer ID; 
	
	@Column(name = "m1" ,nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private Integer m1; //储量下限
	
	@Column(name = "m2" ,nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private Integer m2; //储量上限
	
	@Column(name = "m3" ,nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Float m3; //危险系数
	 
}
