package com.cczu.model.sbssgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-设备台时
 * @author 
 */
@Entity
@Table(name = "sbssgl_tsqd")
public class SBSSGL_TSQDEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//企业id
	
	@Column(name = "deptid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long deptid;//部门id
	
	@Column(name = "m1", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m1;//主要设备制度开动台时
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m2;//主要设备实际开动台时
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String m3;//日期(年-月)
	
	
}
