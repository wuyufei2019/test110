package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: AQJD_BeiAnEntity
 * @Description: 安全监督管理_检查工作计划
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqjd_checkplan")
public class AQJD_CheckPlanEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	public long ID1;//最后修改者ID

	@Column(name = "M1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M1;//专项检查名称

	@Column(name = "M2", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter	
	private String M2;//日期

	@Column(name = "M3", nullable = true, columnDefinition="varchar(Max)")
	@Setter
	@Getter	
	private String M3;//工作清单
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M4;//备注
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M5;//上传附件

	@Column(name = "QYIDS", nullable = true, columnDefinition="varchar(Max)")
	@Setter
	@Getter	
	private String qyids;//检查的企业

}
