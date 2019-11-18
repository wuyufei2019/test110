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
 * 静态基础数据-企业化学品仓库信息
 * @author Administrator
 *
 */
@Entity
@Table(name="jtjcsj_qyhxpckxx")
public class Jtjcsj_QyhxpckxxEntity {
	
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
	
	@Column(name="chmstorid", nullable=true, columnDefinition="char(32)")
	@Setter
	@Getter
	private String chmstorid;// chmstorId 化学品仓库标识
	
	@Column(name="equipcode", nullable=true, columnDefinition="varchar(19)")
	@Setter
	@Getter
	private String equipcode;// equipCode 设备编码
	
	@Column(name="prodcellid", nullable=true, columnDefinition="varchar(32)")
	@Setter
	@Getter
	private String prodcellid;// prodcellId 生产单元区域标识
	
	@Column(name="companycode", nullable=true, columnDefinition="varchar(11)")
	@Setter
	@Getter
	private String companycode;//  companyCode 企业编码
	
	@Column(name="parkid", nullable=true, columnDefinition="char(32)")
	@Setter
	@Getter
	private String parkid;// parkId 所属园区标识
	
	@Column(name="districtcode", nullable=true, columnDefinition="varchar(32)")
	@Setter
	@Getter
	private String districtcode;// districtCode 所属行政区划标识
	
	@Column(name="hazardcode", nullable=true, columnDefinition="varchar(14)")
	@Setter
	@Getter
	private String hazardcode;// hazardCode 重大危险源编码
	
	@Column(name="chmstorname", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String chmstorname;// chmstorName 化学品仓库名称
	
	@Column(name="chmstoruse", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String chmstoruse;// chmstorUse 化学品仓库用途
	
	@Column(name="areavol", nullable=true, columnDefinition="numeric(12,2)")
	@Setter
	@Getter
	private Float areavol;// areaVol 面积（㎡）或容积（m³）
	
	@Column(name="matterform", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String matterform;// matterForm  存放物体形态
	
	@Column(name="deslifespan", nullable=true, columnDefinition="numeric(12,2)")
	@Setter
	@Getter
	private Float deslifespan;// desLifespan 设计使用年限
	
	@Column(name="completime", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp completime;// compleTime 竣工时间
	
	@Column(name="storform", nullable=true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String storform;// storForm 库房形式
	
	@Column(name="storstructure", nullable=true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String storstructure;// storstructure 库房结构
	
	@Column(name="longitude", nullable=true, columnDefinition="numeric(18,10)")
	@Setter
	@Getter
	private Float longitude;//经度（度）
	
	@Column(name="latitude", nullable=true, columnDefinition="numeric(18,10)")
	@Setter
	@Getter
	private Float latitude;//纬度（度）
	
	@Column(name="storemanname", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String storemanname;// storemanName 仓库管理员姓名
	
	@Column(name="linkmode", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String linkmode;// linkMode 联系方式
	
	@Column(name="status", nullable=true, columnDefinition="char(1)")
	@Setter
	@Getter
	private String status;//删除标记          0未删除，1已删除
	
	@Column(name="creator", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String creator;//创建人
	
	@Column(name="createtime", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp createtime;// createTime  创建日期
	
	@Column(name="updator", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String updator;//最后修改人
	
	@Column(name="updatetime", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp updatetime;// updateTime 修改日期
	
	
	
}
