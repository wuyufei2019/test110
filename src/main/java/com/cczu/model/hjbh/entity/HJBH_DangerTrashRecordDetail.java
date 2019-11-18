package com.cczu.model.hjbh.entity;

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
 * 环境保护-危险废物-记录信息-详细记录（附件记录）
 * @author XY
 *
 */

@Entity
@Table(name="hjbh_dangertrashrecorddetail")
public class HJBH_DangerTrashRecordDetail implements Serializable{

	private static final long serialVersionUID = 4793560532817752303L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "recordid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private long recordid;//HJBH_DangerTrashRecord 记录id
	
	@Column(name = "trashid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private String trashid;//废物id
	
	@Column(name = "resource", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String resource;//产生源
	
	@Column(name = "amount", nullable = true, columnDefinition="Numeric(18,2)")
	@Setter
	@Getter
	private Double amount;//产生量
	
	@Column(name = "unit", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String unit;//计量单位
	
	@Column(name = "direction", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String direction;//废物流向
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M1;//外单位处置的企业
	
	@Column(name = "M2", nullable = true, columnDefinition="Numeric(18,2)")
	@Setter
	@Getter
	private Double M2;//内部利用处理量
	
	@Column(name = "M3", nullable = true, columnDefinition="Numeric(18,2)")
	@Setter
	@Getter
	private Double M3;//委托利用处理量
	
	@Column(name = "M4", nullable = true, columnDefinition="Numeric(18,2)")
	@Setter
	@Getter
	private Double M4;//累计贮存量
	
	@Column(name = "M5", nullable = true, columnDefinition="Numeric(18,2)")
	@Setter
	@Getter
	private Double M5;//年度产生量
	
}
