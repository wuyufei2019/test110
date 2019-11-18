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
 * 储罐、反应釜塔信号采集
 * @author jason
 * @date 2017年6月26日
 */
@Entity
@Table(name="main_signal_storage")
public class Main_SignalStorageEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7128431525067724050L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//自动编号
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//储罐ID
	
//	@Column(name="storageno", nullable=false, columnDefinition="varchar(20)")
//	@Setter
//	@Getter
//	private String StorageNo;//储罐位号
	
	@Column(name="level", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Float level;//实时液位(M)
	
	@Column(name="reserves", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Float reserves;//实时储量(t)
	
	@Column(name="temperature", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Float temperature;//实时温度(℃)
	
	@Column(name="pressure", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Float pressure;//实时压力(MPa)
	
	@Column(name="flux", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Float flux;//实时流量(L/S)
	
	@Column(name="percent", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Float percent;//比例

	@Column(name="colltime", nullable=true, columnDefinition="datetime" )
	@Setter
	@Getter
	private Timestamp colltime;//采集时间
	
	@Column(name="isupload", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private Integer isupload;//是否上传（0未上传 1已上传  默认0
	
	
}
