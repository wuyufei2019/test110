package com.cczu.model.sbssgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-设备保养
 * @author ZPC
 */
@Entity
@Table(name = "sbssgl_sbby")
public class SBSSGL_SBBYEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "taskid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long taskid;//保养任务id
	
	@Column(name = "sbid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long sbid;//设备id
	
	@Column(name = "userid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long userid;//操作人id

	@Column(name = "m1", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m1;//季度月（月度：1、2、3... 季度：1、2、3、4 半年度：1、2年度：1）
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m2;//状态（0.未上传附件1.已上传附件）
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String m3;//附件
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m4;//状态（0.普通设备1.特种设备）
}
