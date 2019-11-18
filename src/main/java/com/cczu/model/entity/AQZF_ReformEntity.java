package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 安全生产执法--责令整改
 * @author jason
 * @date 2017年7月26日
 */
@Entity
@Table(name="aqzf_reform")
public class AQZF_ReformEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2517097442171787883L;

	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//检查记录ID
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID2;//被检查单位ID
	
	@Column(name = "M0", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M0;//检查记录编号

	@Column(name = "M1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M1;//检查日期
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M2;//存在问题
	
	@Column(name = "M3", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M3;//整改完毕日期
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M4;//申诉政府
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M5;//诉讼法院
	
	@Column(name = "M6_1", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M6_1;//执法人员1
	
	@Column(name = "M6_2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M6_2;//执法人员2

	@Column(name = "M7_1", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M7_1;//执法人员证号1
	
	@Column(name = "M7_2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M7_2;//执法人员证号2
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M8;//被检查单位负责人
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M9;//整改复查状态（0 未 1已）
}
