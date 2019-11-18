package com.cczu.model.sbssgl.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-设备维修需求
 * @author 
 */
@Entity
@Table(name = "sbssgl_sbwxxq")
public class SBSSGL_SBWXXQEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//企业id

	@Column(name = "m1", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp m1;//申请日期（年月日）
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m2;//设备/设施编号
	
	@Column(name = "sbid", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private Long sbid;//设备id
	
	
	@Column(name = "syrid", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private Long syrid;//使用人id
	
	@Column(name = "m3", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp m3;//故障发现时间（年月日时分秒）
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m4;//故障处理等级（0.立刻处理1.停机后处理2.保养过程中处理3.其他情况）
	
	@Column(name = "m5", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m5;//故障现象
	
	@Column(name = "sqrid", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private Long sqrid;//申请人id
	
	@Column(name = "m6", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m6;//故障原因（0.操作失误1.采购件质量问题。。。）
	
	@Column(name = "m7", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m7;//处理方法及结果
	
	@Column(name = "m8", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m8;//更换备件名称
	
	@Column(name = "m9", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m9;//数量
	
	@Column(name = "wxryid", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private Long wxryid;//维修人员id
	
	@Column(name = "m10", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp m10;//维修开始时间
	
	@Column(name = "m11", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp m11;//维修结束时间
	
	@Column(name = "m12", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m12;//维修时间
	
	@Column(name = "m13", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m13;//是否能正常使用（0.正常1.不正常）
	
	@Column(name = "m14", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m14;//运行振动情况（0.正常1.不正常）
	
	@Column(name = "m15", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m15;//精度有无达到要求（0.正常1.不正常）
	
	@Column(name = "m16", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m16;//试运行有无异响（0.正常1.不正常）
	
	@Column(name = "m17", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m17;//相关运行参数有无达到标准（0.正常1.不正常）
	
	@Column(name = "m18", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m18;//其他验收依据
	
	@Column(name = "m19", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m19;//验收结论
	
	@Column(name = "m20", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String m20;//故障原因分析及结果评定
	
	@Column(name = "m21", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String m21;//附件
	
	@Column(name = "m22", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m22;//使用人
	
	@Column(name = "m23", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m23;//状态（0.等待确认1.已通过，等待维修2.驳回3.已维修，等待验收4.已验收，待结果评定5.已评定，等待添加改善措施6.全部结束）
	
	@Column(name = "m24", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m24;//维修人员
	
	@Column(name = "m25", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp m25;//维修日期
	
	@Column(name = "m26", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m26;//维护维修记录
	
	@Column(name = "m27", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m27;//记录者
	
}
