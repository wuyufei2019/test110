package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @description 企业-安全培训-培训调查主题信息
 * @author jason
 * @date 2018年1月26日
 */
@Entity
@Table(name="aqpx_pxdczt")
public class AQPX_PxdcztEntity extends BaseEntity {

	private static final long serialVersionUID = -2872665003603394323L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//企业ID
	
	@Column(name = "pxzt", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String pxzt;//培训调查主题
	
	@Column(name = "flag", nullable = true)
	@Setter
	@Getter
	private int flag;//0 恢复  1终止
}
