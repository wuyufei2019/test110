package com.cczu.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: ISSUE_SafetyProductionDynamicEntity
 * @Description: 安全文件发布_安全生产动态信息
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="issue_safetyproductiondynamicinfo")
public class ISSUE_SafetyProductionDynamicEntity extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 2888187391651750192L;

	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	public long ID1;//发布人

	@Column(name = "M1", nullable = true, length = 100)
	@Setter
	@Getter
	private String M1;//信息标题

	@Column(name = "M2", nullable = true, length = 2000)
	@Setter
	@Getter	
	private String M2;//信息内容

	@Column(name = "M3", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M3;//文件路径
	
	@Column(name = "M4", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M4;//备注

}
