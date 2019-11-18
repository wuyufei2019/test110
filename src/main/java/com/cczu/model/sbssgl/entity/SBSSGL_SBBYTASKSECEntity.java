package com.cczu.model.sbssgl.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-设备二级保养任务
 * @author ZPC
 */
@Entity
@Table(name = "sbssgl_sbbytasksec")
public class SBSSGL_SBBYTASKSECEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	

	@Column(name = "sbid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long sbid;//设备id
	
	@Column(name = "m1", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m1;//一月（0.该月没有计划1.该月有计划）
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m2;//二月（0.该月没有计划1.该月有计划）
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m3;//三月（0.该月没有计划1.该月有计划）
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m4;//四月（0.该月没有计划1.该月有计划）
	
	@Column(name = "m5", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m5;//五月（0.该月没有计划1.该月有计划）
	
	@Column(name = "m6", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m6;//六月（0.该月没有计划1.该月有计划）
	
	@Column(name = "m7", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m7;//七月（0.该月没有计划1.该月有计划）
	
	@Column(name = "m8", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m8;//八月（0.该月没有计划1.该月有计划）
	
	@Column(name = "m9", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m9;//九月（0.该月没有计划1.该月有计划）
	
	@Column(name = "m10", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m10;//十月（0.该月没有计划1.该月有计划）
	
	@Column(name = "m11", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m11;//十一月（0.该月没有计划1.该月有计划）
	
	@Column(name = "m12", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m12;//十二月（0.该月没有计划1.该月有计划）
	
	@Column(name = "taskid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long taskid;//计划表id
	
	@Column(name = "m13", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m13;//状态（0.待上传附件1.待审核2.已完成3.未完成）
	
	@Column(name = "m14", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	public String m14;//附件
}
