package com.cczu.model.jtjcsj.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * 静态基础数据-安全风险研判与承诺数据
 * @author Administrator
 *
 */
//@Entity
//@Table(name="jtjcsj_aqfxypycnsj")
public class Jtjcsj_AqfxypycnsjEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name="uuid", nullable=true, columnDefinition="varchar(36)")
	@Setter
	@Getter
	private String uuid;//唯一编码 UUID
	
	@Column(name="areacode", nullable=true, columnDefinition="varchar(6)")
	@Setter
	@Getter
	private String areacode;// AREA_CODE 行政区域编码       企业注册地行政区域，必须到区县级（6位）见行政区划附录
	
	@Column(name="shouldnumber", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer shouldnumber;// SHOULD_NUMBER  应承诺企业数        一个区县应承诺的企业数量
	
	@Column(name="actuallynumber", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer actuallynumber;// ACTUALLY_NUMBER    实际承诺企业数       一个区县当天实际承诺的企业数量
	
	@Column(name="risknumber", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer risknumber;// RISK_NUMBER   高风险企业数
	
	@Column(name="highernumber", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer highernumber;//HIGHER_NUMBER   较大风险企业数
	
	@Column(name="generalnumber", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer generalnumber;//GENERAL_NUMBER  一般风险企业数
	
	@Column(name="lownumber", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer lownumber;// LOW_NUMBER  低风险企业数
	
	@Column(name="committeddate", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp committeddate;// COMMITTED_DATE  承诺日期
	
	
}
