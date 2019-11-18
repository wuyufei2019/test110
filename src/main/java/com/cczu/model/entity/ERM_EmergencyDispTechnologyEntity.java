package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: ERM_EmergencyDispTechnologyEntity
 * @Description: 应急资源管理_应急处置技术
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="erm_emergencydisptechnology")
public class ERM_EmergencyDispTechnologyEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 2261054473392710471L;

	@Column(name = "M1", nullable = true, length = 40)
	@Setter
	@Getter
	private String M1;//化学品名称

	@Column(name = "M2", nullable = true, length = 1000)
	@Setter
	@Getter
	private String M2;//主要危险性

	@Column(name = "M3", nullable = true, length = 1000)
	@Setter
	@Getter	
	private String M3;//事故应急处置技术

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	public long ID1;//操作者
	
	@Column(name = "qyid", nullable = true, length = 8)
	@Setter
	@Getter
	public Long qyid;//企业id
	
	@Column(name = "xzqy", nullable = true, length = 20)
	@Setter
	@Getter
	public String xzqy;//行政区划
	
}
