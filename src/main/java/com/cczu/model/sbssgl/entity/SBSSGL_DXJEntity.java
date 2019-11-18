package com.cczu.model.sbssgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-点巡检
 * @author 
 */
@Entity
@Table(name = "sbssgl_dxj")
public class SBSSGL_DXJEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//企业id

	@Column(name = "m1", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m1;//标题
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m2;//年份
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m3;//月份
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(2000)")
	@Getter
	@Setter
	private String m4;//上传附件
	
	@Column(name = "m5", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private Long m5;//设备id
}
