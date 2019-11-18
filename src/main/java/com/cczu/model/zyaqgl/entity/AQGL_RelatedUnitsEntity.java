package com.cczu.model.zyaqgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 安全管理-相关方单位名录
 */
@Entity
@Table(name = "aqgl_relatedunits")
public class AQGL_RelatedUnitsEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8894163336135783562L;

	@Column(name = "ID1", nullable = false, columnDefinition = "bigint")
	@Getter
	@Setter
	private Long ID1;// 企业id

	@Column(name = "M1", nullable = true, columnDefinition = "varchar(10)")
	@Getter
	@Setter
	private String M1;// 类别（承包商/供应商）

	@Column(name = "M2", nullable = true, columnDefinition = "varchar(100)")
	@Getter
	@Setter
	private String M2;// 单位名称

	@Column(name = "M3", nullable = true, columnDefinition = "varchar(200)")
	@Getter
	@Setter
	private String M3;// 行业类型（下拉，多选）

	@Column(name = "M4", nullable = true, columnDefinition = "varchar(50)")
	@Getter
	@Setter
	private String M4;// 法人代表

	@Column(name = "M5", nullable = true, columnDefinition = "varchar(50)")
	@Getter
	@Setter
	private String M5;// 联系电话

	@Column(name = "M6", nullable = true, columnDefinition = "varchar(50)")
	@Getter
	@Setter
	private String M6;// 联系人	

	@Column(name = "M7", nullable = true, columnDefinition = "varchar(50)")
	@Getter
	@Setter
	private String M7;// 联系电话
	
	@Column(name="M8", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M8;//注册地址
	
	@Column(name="M9", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M9;//注册资金(万元)

	@Column(name="M10", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M10;//经营范围
	
	@Column(name="M11", nullable=true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M11;//营业执照（上传）
	
	@Column(name="M12", nullable=true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long M12;//添加人id
	
	@Column(name="M13", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M13;//资质（上传）
	
	@Column(name="M14", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M14;//合同（上传）
	
	@Column(name="M15", nullable=true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M15;//商务合同（上传）
	
	@Column(name="M16", nullable=true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M16;//安全合同（上传）
	
	@Column(name="M17", nullable=true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M17;//人员资质（上传）
	
	@Column(name="M18", nullable=true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M18;//安全协议（上传）
	
	@Column(name="M19", nullable=true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M19;//施工方案（上传）
	
	@Column(name="M20", nullable=true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M20;//技术措施（上传）
	
	@Column(name="M21", nullable=true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M21;//相关培训资料（上传）
	
	@Column(name="M22", nullable=true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M22;//法人和安全人员证书（上传）

	@Column(name = "type", nullable = true, columnDefinition = "varchar(10)")
	@Setter
	@Getter
	private String type;//是否列入黑名单 0：否 1;是
}
