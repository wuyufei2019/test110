package com.cczu.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 水表电表气表信号采集
 * @author jason
 * @date 2017年6月26日
 */
@Entity
@Table(name="main_signal_water")
public class Main_SignalWaterEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7497212339264510376L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//自动编号
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//企业ID
	
	@Column(name="depno", nullable=false, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String depNo;//表编号
	
	@Column(name="number", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Float number;//实时度数
	
	@Column(name="colltime", nullable=true, columnDefinition="datetime" )
	@Setter
	@Getter
	private Timestamp colltime;//采集时间
	
	@Column(name="isupload", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private Integer isupload;//是否上传（0未上传 1已上传  默认0
	
	
}
