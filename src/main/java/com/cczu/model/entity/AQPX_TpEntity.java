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
 * @description 企业-安全培训-人员投票信息
 * @author jason
 * @date 2018年1月26日
 */
@Entity
@Table(name="aqpx_tp")
public class AQPX_TpEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2872665003603394323L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;

	@Column(name = "ID1", nullable = false, length = 500)
	@Setter
	@Getter
	private String ID1;//课程ID集合
	
	@Column(name = "ztid", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ztid;//培训调查主题ID
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID2;//投票人ID
	
	@Column(name = "ID3", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID3;//企业ID
}
