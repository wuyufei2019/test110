package com.cczu.model.mbgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;
/**
 * 
 * @ClassName: safety_duty
 * @Description: 目标管理：安全生产责任制-安全职责
 *
 */
@Entity
@Table(name="target_safetyduty")
public class Target_SafetyDuty extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID1;//企业ID

	@Column(name = "jobid", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private Long jobid;//岗位id
	
	@Column(name = "duty", nullable = true, columnDefinition="varchar(max)")
	@Getter
	@Setter
	private String duty;//安全职责
	
	@Column(name="bzperson", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String bzperson;//编制人
	
	@Column(name="shperson", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String shperson;//审核人
	
	@Column(name="pzperson", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String pzperson;//批准人
	
	@Column(name="fileurl", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String fileurl;//附件
	
	@Column(name="note", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String note;//备注
	
}
