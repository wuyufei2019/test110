package com.cczu.util.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class BaseEntity implements Serializable {

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
	
	@Column(name = "S1", nullable = false, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp S1;//创建时间
	
	@Column(name = "S2", nullable = false, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp S2;//更新时间
	
	@Column(name = "S3", nullable = false, columnDefinition="int")
	@Setter
	@Getter
	public int S3;//删除标识
}
