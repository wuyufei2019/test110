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
 * 日常隐患---整改复查表
 * @author jason
 * @date 2017年10月11日
 */
@Entity
@Table(name="yhpc_dailyhandlerecord")
public class YHPC_DailyHandleRecordEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3623104689629734943L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "userid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long userid;//整改人员id
	
	@Column(name = "handletime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp handletime;//整改时间

	@Column(name = "handledesc", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String handleDesc ;//备注
	
	@Column(name = "handlemoney", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String handleMoney ;//整改费用
	
	@Column(name = "handleuploadphoto", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String  handleUploadPhoto;//整改图片
	
	@Column(name = "handletype", nullable = true, columnDefinition="int")
	@Setter
	@Getter	
	private Integer handleType;//操作（1：整改；2：复查）
	
	@Column(name = "dangerid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long dangerid;//隐患id
	
	@Column(name = "handlestatus", nullable = true, columnDefinition="int")
	@Setter
	@Getter	
	private Integer handlestatus;//检查人复查结果(0:未整改/1:整改未完成/2：整改完成/3:复查不通过/4:复查通过)
	 
}
