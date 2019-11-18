package com.cczu.model.sbssgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-请购设备
 * @author ZPC
 */
@Entity
@Table(name = "sbssgl_qgsb")
public class SBSSGL_QGSBEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "sbsqid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long sbsqid;//设备申请id

	@Column(name = "m1", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m1;//资产名称
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m2;//规格型号
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m3;//制造商/品牌
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m4;//数量
	
	@Column(name = "m5", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m5;//单价
	
	@Column(name = "m6", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m6;//总价
	
	@Column(name = "m7", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m7;//是否验收状态（0.未验收1.验收）
}
