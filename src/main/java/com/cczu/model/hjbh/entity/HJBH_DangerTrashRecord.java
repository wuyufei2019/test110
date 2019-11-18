package com.cczu.model.hjbh.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 环境保护-危险废物-记录信息
 * @author JZQ
 *
 */

@Entity
@Table(name="hjbh_dangertrashrecord")
public class HJBH_DangerTrashRecord extends BaseEntity{

	private static final long serialVersionUID = 4793560532817752303L;
	
	@Column(name = "qyid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long qyid;//企业ID
	
	@Column(name = "year", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	private String year;//年度
	
	@Column(name = "informant", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String informant;//填报人
	
	@Column(name = "phone", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String phone;//联系电话
	
	@Column(name = "recordtime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp recordtime;//填报日期
	
	@Column(name = "principal", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String principal;//单位负责人
	
}
