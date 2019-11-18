package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="aqjg_zxglhmd")
public class AQJG_ZXGLHmdEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5733422317266949483L;

	// 黑名单企业
	@Column(name = "ID1", nullable = true, columnDefinition = "int")
	@Getter
	@Setter
	private Long ID1;

	// 黑名单行为
	@Column(name = "M1", nullable = true, columnDefinition = "varchar(100)")
	@Getter
	@Setter
	private String M1;

	// 黑名单描述
	@Column(name = "M2", nullable = true, columnDefinition = "varchar(500)")
	@Getter
	@Setter
	private String M2;

	// 黑名单级别
	@Column(name = "M3", nullable = true, columnDefinition = "varchar(50)")
	@Getter
	@Setter
	private String M3;

	// 作证材料
	@Column(name = "M4", nullable = true, columnDefinition = "varchar(500)")
	@Getter
	@Setter
	private String M4;

	// 黑名单时间起
	@Column(name = "M5", nullable = true, columnDefinition = "datetime")
	@Getter
	@Setter
	private Timestamp M5;

	// 黑名单时间止
	@Column(name = "M6", nullable = true, columnDefinition = "datetime")
	@Getter
	@Setter
	private Timestamp M6;

	// 备注
	@Column(name = "M7", nullable = true, columnDefinition = "varchar(500)")
	@Getter
	@Setter
	private String M7;
	
	// 状态
	@Column(name = "M8", nullable = true, columnDefinition = "int")
	@Getter
	@Setter
	private Integer M8;
}
