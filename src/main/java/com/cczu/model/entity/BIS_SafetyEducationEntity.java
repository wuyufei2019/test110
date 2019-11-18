package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: BIS_SafetyEducationEntity
 * @Description: 企业基本信息-安全培训信息
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name = "bis_safetyeducation")
public class BIS_SafetyEducationEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;// 企业编号
	
	@Column(name = "eid", nullable = true, columnDefinition = "int")
	@Setter
	@Getter
	private Integer eid;// 员工id

	@Column(name = "M1", nullable = true, length = 20)
	@Setter
	@Getter
	private String M1;// 姓名

	@Column(name = "M2", nullable = true, length = 200)
	@Setter
	@Getter
	private String M2;// 安全人员类别

	@Column(name = "M3", nullable = true, length = 200)
	@Setter
	@Getter
	private String M3;// 职务

	@Column(name = "M4", nullable = true, columnDefinition = "datetime")
	@Setter
	@Getter
	private Timestamp M4;// 最近培训时间

	@Column(name = "M5", nullable = true, columnDefinition = "datetime")
	@Setter
	@Getter
	private Timestamp M5;// 下次培训时间

	@Column(name = "M6", nullable = true, length = 200)
	@Setter
	@Getter
	private String M6;// 发证机关

	@Column(name = "M7", nullable = true, length = 200)
	@Setter
	@Getter
	private String M7;// 备注
	
	@Column(name = "M8", nullable = true, length = 2000)
	@Setter
	@Getter
	private String M8;// 证书文件
	
	@Column(name = "M9", nullable = true, columnDefinition = "datetime")
	@Setter
	@Getter
	private Timestamp M9;// 有效期
	
	@Column(name = "M10", nullable = true, length = 200)
	@Setter
	@Getter
	private String M10;// 证书名称

	@Column(name = "M11", nullable = true, length = 100)
	@Setter
	@Getter
	private String M11;// 证书编号
}
