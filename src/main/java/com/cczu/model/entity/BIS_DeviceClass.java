package com.cczu.model.entity;

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
 * @ClassName: BIS_DeviceClass
 * @Description: 设备类别
 *
 */
@Entity
@Table(name="bis_deviceclass")
public class BIS_DeviceClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "M1", nullable = true,columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//类别名称
	
	@Column(name = "PID", nullable = true, length = 8)
	@Setter
	@Getter
	private long PID;//
}
