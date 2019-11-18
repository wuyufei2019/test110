package com.cczu.model.sbssgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-设备一级保养任务
 * @author 
 */
@Entity
@Table(name = "sbssgl_sbbytaskfir")
public class SBSSGL_SBBYTASKFIREntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "sbid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long sbid;//设备id
	
	@Column(name = "m1", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m1;//一号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m2;//二号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m3;//三号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m4;//四号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m5", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m5;//五号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m6", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m6;//六号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m7", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m7;//七号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m8", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m8;//八号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m9", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m9;//九号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m10", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m10;//十号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m11", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m11;//十一号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m12", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m12;//十二号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m13", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m13;//十三号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m14", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m14;//十四号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m15", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m15;//十五号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m16", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m16;//十六号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m17", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m17;//十七号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m18", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m18;//十八号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m19", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m19;//十九号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m20", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m20;//二十号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m21", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m21;//二十一号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m22", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m22;//二十二号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m23", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m23;//二十三号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m24", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m24;//二十四号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m25", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m25;//二十五号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m26", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m26;//二十六号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m27", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m27;//二十七号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m28", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m28;//二十八号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m29", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m29;//二十九号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m30", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m30;//三十号（0.该号没有计划1.该号有计划）
	
	@Column(name = "m31", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m31;//三十一（0.该号没有计划1.该号有计划）
	
	@Column(name = "taskid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long taskid;//计划表id
	
	@Column(name = "m32", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m32;//状态（0.待上传附件1.待审核2.完成3.未完成）
	
	@Column(name = "m33", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	public String m33;//附件
	
}
