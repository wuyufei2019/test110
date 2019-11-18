package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: AQJD_CheckRecordEntity
 * @Description: 安全监督管理_检查记录（安监检查信息）
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqjd_ajcheckrecord")
public class AQJD_AjCheckRecordEntity extends BaseEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8099799231041082027L;

	@Column(name = "id1", nullable = false,  columnDefinition = "int")
	@Setter
	@Getter
	public long id1;//企业检查记录的的id

	@Column(name = "checkdate", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp checkdate;//检查日期

	@Column(name = "checkname", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String checkname;//检查人员
	
	@Column(name = "opinion", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String opinion;//检查意见汇总
	
	@Column(name = "businessperson", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String businessperson;//企业负责人
	
	@Column(name = "recheckdate", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp recheckdate;//复查时间
	
	@Column(name = "rename", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String rename;//复查人员
	
	@Column(name = "reopinion", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String reopinion;//复查人意见汇总
	
	@Column(name = "businessreperson", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String businessreperson;//复查企业负责人
	
	
	@Column(name = "url", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String url;//初检图片附件
	
	@Column(name = "reurl", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String reurl;//复查图片地址
	@Column(name = "fjurl", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String fjurl;//初检附件地址
	@Column(name = "refjurl", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String refjurl;//复查附件地址
	
}
