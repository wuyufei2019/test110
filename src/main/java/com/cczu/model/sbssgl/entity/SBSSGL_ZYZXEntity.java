package com.cczu.model.sbssgl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-资源中心
 * @author 
 */
@Entity
@Table(name = "sbssgl_zyzx")
public class SBSSGL_ZYZXEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//企业id

	@Column(name = "m1", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m1;//标题
	
	@Column(name = "scrid", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private Long scrid;//上传人id
	
	@Column(name = "m2", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp m2;//上传时间
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String m3;//上传附件
	
}
