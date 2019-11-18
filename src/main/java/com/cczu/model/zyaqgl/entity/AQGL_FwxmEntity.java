package com.cczu.model.zyaqgl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 安全管理-服务项目
 */
@Entity
@Table(name = "aqgl_fwxm")
public class AQGL_FwxmEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8894163336135783562L;

	// 相关方单位id
	@Column(name = "ID1", nullable = false, columnDefinition = "int")
	@Getter
	@Setter
	private Long ID1;

	// 项目类型
	@Column(name = "M1", nullable = true, columnDefinition = "varchar(50)")
	@Getter
	@Setter
	private String M1;

	// 项目名称
	@Column(name = "M2", nullable = true, columnDefinition = "varchar(50)")
	@Getter
	@Setter
	private String M2;

	// 服务项目内容
	@Column(name = "M3", nullable = true, columnDefinition = "varchar(200)")
	@Getter
	@Setter
	private String M3;

	// 项目负责人
	@Column(name = "M4", nullable = true, columnDefinition = "varchar(50)")
	@Getter
	@Setter
	private String M4;

	// 项目合同金额
	@Column(name = "M5", nullable = true, columnDefinition = "float")
	@Getter
	@Setter
	private Float M5;

	// 开始时间
	@Column(name = "M6", nullable = true, columnDefinition = "datetime")
	@Getter
	@Setter
	private Timestamp M6;

	// 结束时间
	@Column(name = "M7", nullable = true, columnDefinition = "datetime")
	@Getter
	@Setter
	private Timestamp M7;

	//项目相关附件
	// 技术方案
	@Column(name = "M8", nullable = true, columnDefinition = "varchar(500)")
	@Getter
	@Setter
	private String M8;

	// 安全措施
	@Column(name = "M9", nullable = true, columnDefinition = "varchar(500)")
	@Getter
	@Setter
	private String M9;

	// 作业许可
	@Column(name = "M10", nullable = true, columnDefinition = "varchar(500)")
	@Getter
	@Setter
	private String M10;

	// 现场照片
	@Column(name = "M11", nullable = true, columnDefinition = "varchar(500)")
	@Getter
	@Setter
	private String M11;

	// 评估报告
	@Column(name = "M12", nullable = true, columnDefinition = "varchar(500)")
	@Getter
	@Setter
	private String M12;

	// 检测报告
	@Column(name = "M13", nullable = true, columnDefinition = "varchar(500)")
	@Getter
	@Setter
	private String M13;

	// 项目合同
	@Column(name = "M14", nullable = true, columnDefinition = "varchar(500)")
	@Getter
	@Setter
	private String M14;
}
