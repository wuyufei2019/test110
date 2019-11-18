package com.cczu.model.mbgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;
/**
 * 
 * @ClassName: Target_Basic
 * @Description: 目标管理：目标设置
 *
 */
@Entity
@Table(name="target_basic")
public class Target_Basic extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Getter
	@Setter
	private long ID1;//企业ID

	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M1;//指标名称
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String M2;//指标值
	
	@Column(name="M3", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String M3;//备注
}
