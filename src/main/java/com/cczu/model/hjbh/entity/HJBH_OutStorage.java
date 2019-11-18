package com.cczu.model.hjbh.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 环境保护-危险废物-出库记录
 * @author JZQ
 *
 */

@Entity
@Table(name="hjbh_outstorage")
public class HJBH_OutStorage extends BaseEntity{

	private static final long serialVersionUID = 4793560532817752303L;
	
	@Column(name = "id1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long id1;//废物ID
	
	@Column(name = "outtime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp outtime;//出库日期
	
	@Column(name = "amount", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String amount;//废物数量
	
	@Column(name = "direction", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String direction;//废物流向
	
	@Column(name = "ysoperator", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String ysoperator;//废物运送部门经办人
	
	@Column(name = "ccoperator", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String ccoperator;//废物贮存部门经办人
	
}
