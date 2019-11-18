package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: ERM_EmergencyMateEntity
 * @Description: 应急资源管理_应急物资
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name = "erm_emergencymate")
public class ERM_EmergencyMateEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 618708771221858507L;

	@Column(name = "M1", nullable = true, length = 40)
	@Setter
	@Getter
	private String M1;// 物资类别

	@Column(name = "M2", nullable = true, length = 50)
	@Setter
	@Getter
	private String M2;// 名称

	@Column(name = "M3", nullable = true, length = 50)
	@Setter
	@Getter
	private String M3;// 型号

	@Column(name = "M4", nullable = true)
	@Setter
	@Getter
	private Float M4;// 数量

	@Column(name = "M5", nullable = true, length = 200)
	@Setter
	@Getter
	private String M5;// 功能用途

	@Column(name = "M6", nullable = true, columnDefinition = "bigint")
	@Setter
	@Getter
	private String M6;// 自储数量

	@Column(name = "M7", nullable = true, columnDefinition = "bigint")
	@Setter
	@Getter
	private String M7;// 代储数量

	@Column(name = "M8", nullable = true, columnDefinition = "bigint")
	@Setter
	@Getter
	private String M8;// 储存单位

	@Column(name = "M9", nullable = true, length = 100)
	@Setter
	@Getter
	private String M9;// 储存地址

	@Column(name = "M10", nullable = true, length = 50)
	@Setter
	@Getter
	private String M10;// 联系人

	@Column(name = "M11", nullable = true, length = 30)
	@Setter
	@Getter
	private String M11;// 联系电话

	@Column(name = "M12", nullable = true, length = 100)
	@Setter
	@Getter
	private String M12;// 应对事故类型(1对多)

	@Column(name = "M13", nullable = true, length = 200)
	@Setter
	@Getter
	private String M13;// 备注

	@Column(name = "M14", nullable = true, length = 50)
	@Setter
	@Getter
	private String M14;// 经度

	@Column(name = "M15", nullable = true, length = 50)
	@Setter
	@Getter
	private String M15;// 纬度

	@Column(name = "M16", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M16;// 图片附件
	
	@Column(name = "M17", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M17;// 文档附件
	
	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	public long ID1;// 操作者

	@Column(name = "qyid", nullable = true, length = 8)
	@Setter
	@Getter
	public Long qyid;// 企业id

	@Column(name = "userid", nullable = true, length = 20)
	@Setter
	@Getter
	public Long userid;//安监用户id
}
