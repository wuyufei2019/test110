package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: XWAQ_UnsafebehaviorEntity
 * @Description: 行为安全_不安全行为管理
 * @author jason
 * @date 2017年8月24日
 *
 */
@Entity
@Table(name="xwaq_unsafebehavior")
public class XWAQ_UnsafebehaviorEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -6196358711652701463L;


	@Column(name = "M1", nullable = true, length = 50)
	@Setter
	@Getter
	private String M1;//不安全行为类型

	@Column(name = "M2", nullable = true, length = 100)
	@Setter
	@Getter
	private String M2;//行为描述

	@Column(name = "M3", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M3;//备注
	
	@Column(name = "ID1", nullable = false, length = 8)
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
