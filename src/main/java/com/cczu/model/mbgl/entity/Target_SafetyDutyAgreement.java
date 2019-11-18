package com.cczu.model.mbgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: Target_SafetyDutyAgreement
 * @Description: 目标管理_安全责任书
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="target_safetydutyagreement")
public class Target_SafetyDutyAgreement extends BaseEntity {
	

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -5327001961148464120L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//企业id
	
	@Column(name = "title", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String title;//标题
	
	@Column(name = "year", nullable = true,columnDefinition="varchar(8)")
	@Setter
	@Getter
	private String year;//年份
	
	@Column(name = "departments", nullable = true,columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String departments;//部门ids
	
	@Column(name = "note", nullable = true,columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String note;//备注

	@Column(name = "url", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String url;//责任书附件
}
