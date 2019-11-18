package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: BIS_OccupharmExamEntity
 * @Description: 企业基本信息-职业卫生_职业病危害因素
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="bis_occupharmexam")
public class BIS_OccupharmExamEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -7571741899979394383L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号

	@Column(name = "M1", nullable = true, length = 50)
	@Setter
	@Getter
	private String M1;//类别
	
	@Column(name = "M2", nullable = true, length = 50)
	@Setter
	@Getter
	private String M2;//名称

	@Column(name = "M3", nullable = true, length = 50)
	@Setter
	@Getter	
	private String M3;//危害
	
	@Column(name = "M4", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M4;//备注
	
	@Column(name = "M5", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M5;//可能导致的职业病

	@Column(name = "M6", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M6;//存在部门
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M7;//存在岗位
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M8;//接触人数
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M9;//接触名单
}
