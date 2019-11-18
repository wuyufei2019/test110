package com.cczu.model.sbssgl.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-设备履历
 * @author 
 */
@Entity
@Table(name = "sbssgl_sbll")
public class SBSSGL_SBLLEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//企业id

	@Column(name = "m1", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m1;//设备编号
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m2;//设备名称
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m3;//规格/型号
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m4;//出厂编号
	
	@Column(name = "m5", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m5;//设备制造商
	
	@Column(name = "m6", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m6;//购买日期
	
	@Column(name = "m7", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m7;//价格
	
	@Column(name = "m8", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m8;//放置地点
	
	
	@Column(name = "m9", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m9;//状态
	
	@Column(name = "bgrid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long bgrid;//保管人id
	
	@Column(name = "m11", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String m11;//备注
	
	
}
