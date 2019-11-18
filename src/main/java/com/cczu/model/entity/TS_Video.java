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
 * 视频监控信息视频监控信息
 * @author jason
 * @date 2017年9月6日
 */
@Entity
@Table(name="ts_video")
public class TS_Video implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -9106735751593001193L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号           
	
	@Column(name = "ID1", nullable = true,columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID1;//企业编号
	
	@Column(name="ip", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String ip;//设备ip
	
	@Column(name="port", nullable=true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String port;//端口号
	
	@Column(name="username", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String username;//用户名
	
	@Column(name="password", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String password;//密码
	
	
	@Column(name="url", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String url;//生成视频流地址
	
	@Column(name="beizhu", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String beizhu;//备注
	
	
	
	
	@Column(name = "videoid", nullable = true,columnDefinition="varchar(32)")
	@Setter
	@Getter
	private String videoid;// videoId  视频标识
	
	@Column(name="companycode", nullable=true, columnDefinition="varchar(11)")
	@Setter
	@Getter
	private String companycode;// companyCode 企业编码
	
	@Column(name="parkid", nullable=true, columnDefinition="char(32)")
	@Setter
	@Getter
	private String parkid;// parkId 所属园区标识
	
	@Column(name="districtcode", nullable=true, columnDefinition="char(32)")
	@Setter
	@Getter
	private String districtcode;// districtCode 所属行政区划标识
	
	@Column(name="hazardcode", nullable=true, columnDefinition="varchar(12)")
	@Setter
	@Getter
	private String hazardcode;//  hazardCode  重大危险源标识
	
	@Column(name="prodcellid", nullable=true, columnDefinition="char(32)")
	@Setter
	@Getter
	private String prodcellid;//  prodcellId  生产单元区域标识
	
	@Column(name="name", nullable=true, columnDefinition="varchar(50)")   //长度改为200
	@Setter
	@Getter
	private String name;//名称           equipName          
	
	@Column(name="equipno", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String equipno;//  equipNo  视频设备编号
	
	@Column(name="installloc", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String installloc;// installLoc 视频安装位置
	
	@Column(name="longitude", nullable=true, columnDefinition="numeric(18,10)")
	@Setter
	@Getter
	private Float longitude;// 经度
	
	@Column(name="latitude", nullable=true, columnDefinition="numeric(18,10)")
	@Setter
	@Getter
	private Float latitude;//纬度
	
	@Column(name="status", nullable=true, columnDefinition="char(1)")
	@Setter
	@Getter
	private String status;//删除标记         0未删除，1已删除
	
	
}
