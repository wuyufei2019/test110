package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: ERM_ExercisePlanEntity
 * @Description: 应急演练_演练计划
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="erm_exerciseplan")
public class ERM_ExercisePlanEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -869831118280016002L;

	@Column(name = "M1", nullable = true, length = 50)
	@Setter
	@Getter
	private String M1;//年度

	@Column(name = "M2", nullable = true, length = 30)
	@Setter
	@Getter
	private String M2;//层级（公司级，部门级，班组级）

	@Column(name = "M3", nullable = true, length = 50)
	@Setter
	@Getter	
	private String M3;//部门（下拉，公司网格，单选）

	@Column(name = "M4", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M4;//演练主题
	
	@Column(name = "M5", nullable = true, length = 50)
	@Setter
	@Getter	
	private String M5;//参演部门（下拉，公司网格，多选）
	
	@Column(name = "M6", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M6;//执行人
	
	@Column(name = "M7", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M7;//完成情况
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M8;//附件资料
	
	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	public Long ID1;//演练记录id
	
	@Column(name = "qyid", nullable = true, length = 8)
	@Setter
	@Getter
	public Long qyid;//企业id
}
