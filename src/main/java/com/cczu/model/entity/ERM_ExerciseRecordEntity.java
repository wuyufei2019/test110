package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: ERM_ExerciseRecordEntity
 * @Description: 应急演练_演练记录
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="erm_exerciserecord")
public class ERM_ExerciseRecordEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -869831118280016002L;

	@Column(name = "M1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M1;//演练时间

	@Column(name = "M2", nullable = true, length = 200)
	@Setter
	@Getter
	private String M2;//演练地点

	@Column(name = "M3", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M3;//总指挥

	@Column(name = "M4", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M4;//参演部门
	
	@Column(name = "M5", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M5;//演练名称
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M6;//演练内容
	
	@Column(name = "M7", nullable = true, length = 10)
	@Setter
	@Getter	
	private String M7;//效果评价（优秀/良好/合格/不合格）
	
	@Column(name = "M8", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M8;//评审人员
	
	@Column(name = "M9", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M9;//评审时间
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M10;//存在问题
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M11;//附件资料
	
	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	public long ID1;//演练计划id
	
	@Column(name = "qyid", nullable = true, length = 8)
	@Setter
	@Getter
	public Long qyid;//企业id
}
