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
 * 水电气用量数据
 * @author zpc
 * @date 2017/07/06
 */
@Entity
@Table(name="basic_meter")
public class Basic_MeterEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//自动编号
	
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//企业ID
	
	@Column(name="No", nullable=false, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String No;//表编号
	
	@Column(name="metername", nullable=false, columnDefinition="varchar(30)")
	@Setter
	@Getter
	private String MeterName;//表名称
	
	@Column(name="r1", nullable=true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String r1;//保留
}
