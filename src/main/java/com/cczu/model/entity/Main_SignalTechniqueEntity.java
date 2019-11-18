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
 * 高危工艺信号采集
 * @author jason
 * @date 2017年6月26日
 */
@Entity
@Table(name="main_signal_tsechnique")
public class Main_SignalTechniqueEntity implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1938837393733062831L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//自动编号
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//高危工艺ID（bis_tsechnique  id）
	
	@Column(name="storageno", nullable=false, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String StorageNo;//传感器编号
	
	@Column(name="level", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Float level;//液位(M)
	
	@Column(name="innertemp", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Float innerTemp;//釜内温度
	
	@Column(name="ourtertemp", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Float ourterTemp;//夹套温度
	
	@Column(name="pressure", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Float pressure;//压力(MPa)
	
	@Column(name="flux", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Float flux;//流量（进）（m3/h）
	
	@Column(name="weight", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Float weight;//称重（kg）
	
	@Column(name="[current]", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Float current;//搅拌电流（A）
	
	@Column(name="ph", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Float ph;//pH值

	@Column(name="colltime", nullable=true, columnDefinition="datetime" )
	@Setter
	@Getter
	private Timestamp colltime;//采集时间
	
	@Column(name="isupload", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private Integer isupload;//是否上传（0未上传 1已上传  默认0
	
	@Column(name="[percent]", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private Float percent;//比例
}
