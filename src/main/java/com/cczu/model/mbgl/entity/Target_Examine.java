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
 * @ClassName: Target_Examine
 * @Description: 目标管理：指标考核  （年末数据统计）
 *
 */
@Entity
@Table(name="target_examine")
public class Target_Examine extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private long ID1;//企业id
	
	@Column(name = "ID2", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private long ID2;//考核部门

	@Column(name = "year", nullable = true, columnDefinition="varchar(8)")
	@Getter
	@Setter
	private String year;//年份
	
	@Column(name = "zpnum", nullable = true, columnDefinition="int")
	@Getter
	@Setter
	private int zpnum;//自评达标数
	
	@Column(name = "tnum", nullable = true, columnDefinition="int")
	@Getter
	@Setter
	private int tnum;//targetnum目标指标数
	
	@Column(name = "khnum", nullable = true, columnDefinition="int")
	@Getter
	@Setter
	private Integer khnum;//考核达标数
	
	@Column(name="M2", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String M2;//考核人员
	
	@Column(name="M3", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M3;//考核时间
	
	@Column(name="M4", nullable = true, columnDefinition="varchar(4)")
	@Getter
	@Setter
	private String M4;//考核结论  1：达标  0：未达标
	
	@Column(name="M5", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String M5;//考核情况说明
	
	@Column(name="dbids", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String dbids;//达标的目标分配id（偷懒用）
	
	@Column(name="bdbids", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String bdbids;//不达标的目标分配id（）
	
}
