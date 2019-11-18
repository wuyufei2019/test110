/**
 * @ClassName: ADMIN_DSFManageEntity
 * @Description: 第三方技术服务管理——第三方管理
 * @author iDoctor
 * @date 2017年4月18日
 *
 */
package com.cczu.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "aqjg_dsfmanage")
public class AQJG_DSFManageEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 638144630697128267L;

	// 单位名称
	@Column(name = "M1", nullable = true, columnDefinition = "varchar(50)")
	@Getter
	@Setter
	private String M1;

	// 单位类型
	@Column(name = "M2", nullable = true, columnDefinition = "varchar(50)")
	@Getter
	@Setter
	private String M2;

	// 法人代表
	@Column(name = "M3", nullable = true, columnDefinition = "varchar(50)")
	@Getter
	@Setter
	private String M3;

	// 法人代表联系方式
	@Column(name = "M4", nullable = true, columnDefinition = "varchar(50)")
	@Getter
	@Setter
	private String M4;

	// 注册地址
	@Column(name = "M5", nullable = true, columnDefinition = "varchar(100)")
	@Getter
	@Setter
	private String M5;

	// 注册资金
	@Column(name = "M6", nullable = true, columnDefinition = "float")
	@Getter
	@Setter
	private String M6;

	// 项目负责人
	@Column(name = "M7", nullable = true, columnDefinition = "varchar(50)")
	@Getter
	@Setter
	private String M7;

	// 项目负责人联系方式
	@Column(name = "M8", nullable = true, columnDefinition = "varchar(50)")
	@Getter
	@Setter
	private String M8;

	// 备注
	@Column(name = "M9", nullable = true, columnDefinition = "varchar(100)")
	@Getter
	@Setter
	private String M9;

	// 上传文件--营业执照
	@Column(name = "M10", nullable = true, columnDefinition = "varchar(500)")
	@Getter
	@Setter
	private String M10;

	// 上传文件--税务登记
	@Column(name = "M11", nullable = true, columnDefinition = "varchar(500)")
	@Getter
	@Setter
	private String M11;

	// 上传文件--安全生产许可证
	@Column(name = "M12", nullable = true, columnDefinition = "varchar(500)")
	@Getter
	@Setter
	private String M12;

	// 上传文件--其他资政材料
	@Column(name = "M13", nullable = true, columnDefinition = "varchar(500)")
	@Getter
	@Setter
	private String M13;
}
