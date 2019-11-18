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
 * 
 * @ClassName: TMESK_ChemicalsdirectoryEntity 
 * @Description: 安全专家知识库_危化品名录
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="tmesk_chemicalsdirectory")
public class TMESK_ChemicalsdirectoryEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, length = 8, columnDefinition="bigint")
	@Setter
	@Getter
	private long ID;//编号
	
	@Column(name = "S1", nullable = true, columnDefinition="datetime" )
	@Setter
	@Getter
	private Timestamp S1;//创建时间
	
	@Column(name = "S2", nullable = true, columnDefinition="datetime" )
	@Setter
	@Getter
	private Timestamp S2;//更新时间
	
	@Column(name = "S3", nullable = true, columnDefinition="int" )
	@Setter
	@Getter
	private int S3;//删除标识
	
	@Column(name = "ID1", nullable = true, length = 8, columnDefinition="bigint")
	@Setter
	@Getter
	private long ID1;//创建者

	@Column(name = "M1", nullable = true, length = 50, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M1;//

	@Column(name = "M2", nullable = true, length = 100, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M2;//品名

	@Column(name = "M3", nullable = true, length = 100, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M3;//别名

	@Column(name = "M4", nullable = true, length = 300, columnDefinition="varchar(300)")
	@Setter
	@Getter	
	private String M4;//英文名
	
	@Column(name = "M5", nullable = true, length = 200, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M5;//CAS
	
	@Column(name = "M6", nullable = true, length = 500, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M6;//危险性类别
	
	@Column(name = "M7", nullable = true, length = 500, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M7;//备注
	
	@Column(name = "M8", nullable = true, length = 10, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M8;//是否为重点监管危险化学品:1:是
	
	@Column(name = "M9", nullable = true, length = 10, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M9;//是否为剧毒化学品:1:是
	
	@Column(name = "M10", nullable = true, length = 10, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M10;//是否为易制毒化学品:1:是
}
