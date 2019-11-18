package com.cczu.model.sbssgl.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-设备随手拍
 * @author 
 */
@Entity
@Table(name = "sbssgl_ssp")
public class SBSSGL_SSPEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//企业id

	@Column(name = "m1", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m1;//发现时间
	
	@Column(name = "sbid", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private Long sbid;//设备id
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m3;//发现人
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String m4;//隐患描述
	
	@Column(name = "m5", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private Long m5;//使用单位（部门id）
	
	@Column(name = "m6", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m6;//数量
	
	@Column(name = "m7", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m7;//制造商
	
	@Column(name = "m8", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String m8;//备注
	
	@Column(name = "m9", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m9;//设备类型（0.普通设备1.特种设备）
	
	
}
