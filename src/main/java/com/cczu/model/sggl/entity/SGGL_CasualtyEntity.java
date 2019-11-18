package com.cczu.model.sggl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @ClassName: SGGL_CasualtyEntity
 * @Description: 事故管理-伤亡人员基本信息
 * @author jason
 *
 */
@Entity
@Table(name="sggl_casualty")
public class SGGL_CasualtyEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name="ID1", nullable=true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID1;//事故id
	
	@Column(name="ID2", nullable=true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID2;//员工id
	
	@Column(name = "M1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long M1;//伤害程度
}
