package com.cczu.model.entity;

import java.io.Serializable;

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
 * @ClassName: Tdic_BIS_OccupharmExamEntity
 * @Description: 职业病因素字典
 * @author jason
 * @date 2017年5月27日
 *
 */

@Entity
@Table(name="tdic_bis_occupharmexam")
public class Tdic_BIS_OccupharmExamEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//编号
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//职业病危害因素类别

	@Column(name = "M2", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M2;//职业病危害因素名称

	@Column(name = "M3", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M3;//CAS号

	@Column(name = "M4", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M4;//备注

}
