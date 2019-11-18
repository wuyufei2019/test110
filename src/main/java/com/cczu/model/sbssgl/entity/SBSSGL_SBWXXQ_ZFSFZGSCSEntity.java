package com.cczu.model.sbssgl.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-设备维修需求-再发生防止改善措施
 * @author 
 */
@Entity
@Table(name = "sbssgl_sbwxxq_zfsfzgscs")
public class SBSSGL_SBWXXQ_ZFSFZGSCSEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "wxid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long wxid;//设备维修id

	@Column(name = "m1", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m1;//措施描述
	
	@Column(name = "m2", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp m2;//计划时间
	
	@Column(name = "m3", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp m3;//完成时间
	
	@Column(name = "zrrid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long zrrid;//责任人id
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m4;//责任人
}
