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
 * 
 * @ClassName: Sbgl_StsglUrlEntity
 * @Description: 设备管理-三同时管理附件表
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="sbgl_stsglurlentity")
public class Sbgl_StsglUrlEntity implements Serializable {

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

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//附件表id
	
	@Column(name = "type", nullable = true, length = 50)
	@Setter
	@Getter
	private String type;//附件类型

	@Column(name = "url", nullable = true, length = 500)
	@Setter
	@Getter
	private String url;//附件地址
	
	@Column(name = "contjson", nullable = true, length = 500)
	@Setter
	@Getter
	private String contjson;//附件介绍json

}
