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
 * 
 * @ClassName: Dic_DangerousChemicals
 * @Description: 字典-危险化学品分类信息表
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="tdic_dangerouschemicals")
public class Tdic_DangerousChemicalsEntity implements Serializable{

	private static final long serialVersionUID = 2955809929011457722L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, length = 8)
	@Setter
	@Getter
	public long ID;//编号
	
	@Column(name = "S3", nullable = false )
	@Setter
	@Getter
	private int S3;//删除标识  默认0 未删除  ,1删除
	
	@Column(name = "M1", nullable = true, length = 200)
	@Setter
	@Getter
	private String M1; //品名
	
	@Column(name = "M2", nullable = true, length = 500)
	@Setter
	@Getter
	private String M2; //别名
	
	@Column(name = "M3", nullable = true, length = 500)
	@Setter
	@Getter
	private String M3; //英文名
	
	@Column(name = "M4", nullable = true, length = 200)
	@Setter
	@Getter
	private String M4; //CAS号
	
	@Column(name = "M5", nullable = true, length = 1000)
	@Setter
	@Getter
	private String M5; //危险性类别
	
	@Column(name = "M6", nullable = true, length = 400)
	@Setter
	@Getter
	private String M6; //备注
	
}
