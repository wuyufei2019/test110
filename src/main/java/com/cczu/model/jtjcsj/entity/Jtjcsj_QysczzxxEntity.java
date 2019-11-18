package com.cczu.model.jtjcsj.entity;

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
 * 静态基础数据-企业生产装置信息
 * @author Administrator
 *
 */
@Entity
@Table(name="jtjcsj_qysczzxx")
public class Jtjcsj_QysczzxxEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name="qyid", nullable=true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long qyid;//企业id
	
	@Column(name="proddevid", nullable=true, columnDefinition="varchar(32)")
	@Setter
	@Getter
	private String proddevid;// proddevId  生产装置标识
	
	@Column(name="equipcode", nullable=true, columnDefinition="varchar(19)")
	@Setter
	@Getter
	private String equipcode;// equipCode  设备编码
	
	@Column(name="prodcellid", nullable=true, columnDefinition="varchar(32)")
	@Setter
	@Getter
	private String prodcellid;// prodcellId  生产单元区域标识
	
	@Column(name="companycode", nullable=true, columnDefinition="varchar(11)")
	@Setter
	@Getter
	private String companycode;// companyCode 企业编码     编码规范 市级行政区划编码（6位）+5位数字流水号
	
	@Column(name="parkid", nullable=true, columnDefinition="varchar(32)")
	@Setter
	@Getter
	private String parkid;//  parkId  所属园区标识
	
	@Column(name="districtcode", nullable=true, columnDefinition="varchar(32)")
	@Setter
	@Getter
	private String districtcode;//  districtCode  所属行政区划标识
	
	@Column(name="hazardcode", nullable=true, columnDefinition="varchar(14)")
	@Setter
	@Getter
	private String hazardcode;//  hazardCode  重大危险源编码
	
	@Column(name="proddevname", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String proddevname;//  proddevName  生产装置名称
	
	@Column(name="importart", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String importart;//  importArt  涉及重点监管工艺
	
	@Column(name="platformtiers", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String platformtiers;// platformTiers  平台最高层数
	
	@Column(name="ctrltel", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String ctrltel;// ctrlTel 调度室电话
	
	@Column(name="longitude", nullable=true, columnDefinition="numeric(18,10)")
	@Setter
	@Getter
	private Float longitude;//经度（度）
	
	@Column(name="latitude", nullable=true, columnDefinition="numeric(18,10)")
	@Setter
	@Getter
	private Float latitude;//纬度（度）
	
	@Column(name="isnormal", nullable=true, columnDefinition="char(1)")
	@Setter
	@Getter
	private String isnormal;// isNormal 是否正常状态        0：否；1：是
	
	@Column(name="dutypsn", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String dutypsn;// dutyPsn 负责人
	
	@Column(name="craftintroduction", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String craftintroduction;// craftIntroduction 生产工艺或存储情况简介
	
	@Column(name="safemeasures", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String safemeasures;// safeMeasures 安全措施
	
	@Column(name="chemartid", nullable=true, columnDefinition="varchar(32)")
	@Setter
	@Getter
	private String chemartid;// chemartId 重点监管危险化工工艺标识
	
	@Column(name="chemartart", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String chemartart;// chemartArt 重点监管危险化工工艺名称
	
	@Column(name="status", nullable=true, columnDefinition="char(1)")
	@Setter
	@Getter
	private String status;//删除标记         0未删除，1已删除
	
	@Column(name="creator", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String creator;//创建人
	
	@Column(name="createtime", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp createtime;//  createTime  创建日期
	
	@Column(name="updator", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String updator;//修改人
	
	@Column(name="updatetime", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp updatetime;// updateTime 修改日期
	
	

}
