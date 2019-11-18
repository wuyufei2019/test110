package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 日常培训线下记录
 * @author Administrator
 *
 */
@Entity
@Table(name="aqpx_aqpxrcxxpxjl")
public class AQPX_RcxxpxjlEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 894624256314072685L;
	
	
	@Column(name = "ID1", nullable = false, columnDefinition = "bigint")
	@Setter
	@Getter
	private Long ID1;//企业ID
	

	
	
	@Column(name = "m1_1", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M1_1;//姓名
	
	
	@Column(name = "m1_3", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M1_3;//工作岗位
	
	@Column(name = "m1_4", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M1_4;//培训类别
	
	
	
	@Column(name = "m2", nullable = true, columnDefinition = "datetime")
	@Setter
	@Getter
	private Timestamp M2;//培训日期
	
	@Column(name = "m3", nullable = true, columnDefinition = "varchar(1000)")
	@Setter
	@Getter
	private String M3;//培训内容
	
	
	@Column(name = "m4", nullable = true, columnDefinition = "varchar(500)")
	@Setter
	@Getter
	private String M4;//培训人员
	
	@Column(name = "m7", nullable = true, columnDefinition = "varchar(10)")
	@Setter
	@Getter
	private String M7;//培训成绩
	
	
	@Column(name = "m8", nullable = true, columnDefinition = "varchar(20)")
	@Setter
	@Getter
	private String M8;//培训结果是否合格
	
	
	@Column(name = "m9", nullable = true, columnDefinition = "varchar(200)")
	@Setter
	@Getter
	private String M9;//备注
	
	@Column(name = "m10", nullable = true, columnDefinition = "varchar(max)")
	@Setter
	@Getter
	private String M10;//附件
	
	@Column(name = "state", nullable = true, columnDefinition = "varchar(2)")
	@Setter
	@Getter
	private String state;//培训方式（1、线上  2、线下）
	
	
	
	
	
	
	
	
	
	
	
	
	

}
