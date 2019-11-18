package com.cczu.model.sbssgl.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-设备备品备件
 * @author 
 */
@Entity
@Table(name = "sbssgl_bpbj")
public class SBSSGL_BPBJEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "sbid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long sbid;

	@Column(name = "m1", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m1;//最低安全库存
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m2;//设备名称
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m3;//规格型号
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m4;//单位（例如，"件"）
	
	@Column(name = "m6", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m6;//数量
	
	@Column(name = "m7", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m7;//制造商
	
	@Column(name = "m9", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m9;//设备类型（0.普通设备1.特种设备）
	
	
}
