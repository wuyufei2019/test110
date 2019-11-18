/**
 * @ClassName: ADMIN_DSFTzzyryEntity
 * @Description: 第三方技术服务管理——特种操作人员
 * @author iDoctor
 * @date 2017年4月18日
 *
 */
package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "aqjg_dsftzzyry")
public class AQJG_DSFTzzyryEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 40349320731526683L;

	// 第三方ID
	@Column(name = "ID1", nullable = true, columnDefinition = "int")
	@Getter
	@Setter
	private Long ID1;

	// 姓名
	@Column(name = "M1", nullable = true, columnDefinition = "varchar(50)")
	@Getter
	@Setter
	private String M1;

	// 性别
	@Column(name = "M2", nullable = true, columnDefinition = "int")
	@Getter
	@Setter
	private Long M2;

	// 操作证号
	@Column(name = "M3", nullable = true, columnDefinition = "varchar(100)")
	@Getter
	@Setter
	private String M3;

	// 身份证号
	@Column(name = "M4", nullable = true, columnDefinition = "varchar(50)")
	@Getter
	@Setter
	private String M4;

	// 作业类型
	@Column(name = "M5", nullable = true, columnDefinition = "varchar(100)")
	@Getter
	@Setter
	private String M5;

	// 操作证到期日期
	@Column(name = "M6", nullable = true, columnDefinition = "datetime")
	@Getter
	@Setter
	private Timestamp M6;

	// 备注
	@Column(name = "M7", nullable = true, columnDefinition = "varchar(100)")
	@Getter
	@Setter
	private String M7;
}
