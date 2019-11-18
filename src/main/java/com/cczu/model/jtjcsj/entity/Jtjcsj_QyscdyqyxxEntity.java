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
 * 静态基础数据-企业生产单元区域信息
 * @author Administrator
 *
 */
@Entity
@Table(name="jtjcsj_qyscdyqyxx")
public class Jtjcsj_QyscdyqyxxEntity {
	
	
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
	
	@Column(name="prodcellid", nullable=true, columnDefinition="varchar(32)")
	@Setter
	@Getter
	private String prodcellid;//生产单元区域标识
	
	@Column(name="companycode", nullable=true, columnDefinition="varchar(11)")
	@Setter
	@Getter
	private String companycode;// companyCode 企业编码     编码规范 市级行政区划编码（6位）+5位数字流水号，作为表唯一主键。
	
	@Column(name="hazardcode", nullable=true, columnDefinition="varchar(14)")
	@Setter
	@Getter
	private String hazardcode;// hazardCode 重大危险源编码       企业编码(11位）+3位流水号
	
	@Column(name="parkid", nullable=true, columnDefinition="varchar(32)")
	@Setter
	@Getter
	private String parkid;// parkId 园区标识      
	
	@Column(name="prodcelltype", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String prodcelltype;// prodcellType  生产区域类型
	
	@Column(name="prodcellname", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String prodcellname;// prodcellName  生产单元区域名称
	
	@Column(name="safedutyername", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String safedutyername;// safeDutyerName 安全负责人姓名   
	
	@Column(name="linkmode", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String linkmode;// linkMode 联系方式
	
	@Column(name="prodarea", nullable=true, columnDefinition="numeric(18,2)")
	@Setter
	@Getter
	private Float prodarea;//  prodArea  面积
	
	@Column(name="isprotect", nullable=true, columnDefinition="char(1)")
	@Setter
	@Getter
	private String isprotect;// isProtect  有无防护堤         0否;1是
	
	@Column(name="iprotectarea", nullable=true, columnDefinition="numeric(18,2)")
	@Setter
	@Getter
	private Float iprotectarea;// iprotectArea  防护堤面积  
	
	@Column(name="tanknum", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer tanknum;//  tankNum  贮罐个数
	
	@Column(name="mintankspace", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Float mintankspace;// minTankspace 罐间最小间距
	
	@Column(name="craftintroduction", nullable=true, columnDefinition="VARCHAR(1000)")
	@Setter
	@Getter
	private String craftintroduction;// craftIntroduction  生产工艺或存储状况简介
	
	@Column(name="safemeasures", nullable=true, columnDefinition="VARCHAR(1000)")
	@Setter
	@Getter
	private String safemeasures;//  safeMeasures  安全措施
	
	@Column(name="status", nullable=true, columnDefinition="varchar(1)")
	@Setter
	@Getter
	private String status;//删除标记       0未删除，1已删除
	
	@Column(name="createdate", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp createdate;//  createDate  创建时间
	
	@Column(name="createby", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String createby;//  createBy  创建人
	
	@Column(name="updatedate", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp updatedate;//  updateDate  最后修改时间
	
	@Column(name="updateby", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String updateby;//  updateBy 最后修改人
	
	
	
	
}
