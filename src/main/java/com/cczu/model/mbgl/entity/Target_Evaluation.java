package com.cczu.model.mbgl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;
/**
 * 
 * @ClassName: Target_Evaluation
 * @Description: 目标管理：目标考评
 *
 */
@Entity
@Table(name="target_evaluation")
public class Target_Evaluation extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private long ID1;//企业id
	
	@Column(name = "ID2", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private long ID2;//指标分配id
	
	@Column(name = "quarter", nullable = true, columnDefinition="varchar(8)")
	@Getter
	@Setter
	private String quarter;//季度
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String M1;//评定人
	
	@Column(name = "M2", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M2;//评定时间
	
	@Column(name="M3", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String M3;//备注
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(4)")
	@Getter
	@Setter
	private String M4;//达标情况  0：未达标，1：达标
	
	@Column(name="completion", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String completion;//完成值
}
