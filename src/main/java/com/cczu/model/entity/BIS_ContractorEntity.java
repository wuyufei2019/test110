package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: BIS_ContractorEntity
 * @Description: 企业基本信息-承包商信息
 * @author jason
 * @date 2017年7月18日
 */
@Entity
@Table(name="bis_contractor")
public class BIS_ContractorEntity extends BaseEntity {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1783291012132499133L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号
	
	@Column(name = "ID2", nullable = true, length = 8)
	@Setter
	@Getter
	private Long ID2;//操作者id

	@Column(name = "M1", nullable = true, length = 100)
	@Setter
	@Getter
	private String M1;//承包商名称

	@Column(name = "M2", nullable = true, length = 100 )
	@Setter
	@Getter
	private String M2;//承包项目

	@Column(name = "M3", nullable = true, length = 100 )
	@Setter
	@Getter	
	private String M3;//作业内容

	@Column(name = "M4", nullable = true, length = 50)
	@Setter
	@Getter	
	private String M4;//作业人数
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M5;//资质情况
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M6;//承包协议
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M7;//安全共管协议
	
	@Column(name = "M8", nullable = true)
	@Setter
	@Getter	
	private String M8;//承包商负责人

	@Column(name = "M9", nullable = true)
	@Setter
	@Getter	
	private String M9;

	@Override
	public String toString() {
		return "BIS_ContractorEntity [ID1=" + ID1 + ", ID2=" + ID2 + ", M1="
				+ M1 + ", M2=" + M2 + ", M3=" + M3 + ", M4=" + M4 + ", M5="
				+ M5 + ", M6=" + M6 + ", M7=" + M7 + ", M8=" + M8 + ", M9="
				+ M9 + "]";
	}

 
	

}
