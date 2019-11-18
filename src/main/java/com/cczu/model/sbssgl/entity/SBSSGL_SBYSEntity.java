package com.cczu.model.sbssgl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-设备验收
 * @author ZPC
 */
@Entity
@Table(name = "sbssgl_sbys")
public class SBSSGL_SBYSEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//企业id

	@Column(name = "m1", nullable = false, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m1;//设备类型（生产设备/厂务设施、产品量测/检验仪器、其它）
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m2;//设备编号
	
	@Column(name = "m3", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp m3;//验收开始日期
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m4;//设备名称
	
	@Column(name = "m5", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m5;//规格/型号
	
	@Column(name = "m6", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m6;//出厂编号
	
	@Column(name = "m7", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m7;//设备制造商/代理商
	
	@Column(name = "m8", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp m8;//出厂日期
	
	@Column(name = "m9", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m9;//放置地点
	
	/*******************************主件***************************************/
	@Column(name = "m10", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m10;//定购数量
	
	@Column(name = "m11", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m11;//实交数量
	
	@Column(name = "m12", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m12;//外观
	
	@Column(name = "m13", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m13;//说明
	
	/*******************************附件***************************************/
	@Column(name = "m14", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m14;//定购数量
	
	@Column(name = "m15", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m15;//实交数量
	
	@Column(name = "m16", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m16;//外观
	
	@Column(name = "m17", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m17;//说明
	
	/*******************************技术手册、设备资料***************************************/
	@Column(name = "m18", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m18;//定购数量
	
	@Column(name = "m19", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m19;//实交数量
	
	@Column(name = "m20", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m20;//外观
	
	@Column(name = "m21", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m21;//说明
	
	/*******************************随机工具***************************************/
	@Column(name = "m22", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m22;//定购数量
	
	@Column(name = "m23", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m23;//实交数量
	
	@Column(name = "m24", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m24;//外观
	
	@Column(name = "m25", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m25;//说明
	
	@Column(name = "m26", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp m26;//保修开始日期
	
	@Column(name = "m27", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp m27;//保修结束日期
	
	@Column(name = "m28", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m28;//保修证书
	
	@Column(name = "m29", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String m29;//保修收费条件
	
	@Column(name = "m30", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m30;//检验条件（1.不需校验2.需校验）
	
	@Column(name = "m31", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m31;//新购买设备（1.不选择2.选择）
	
	@Column(name = "m32", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String m32;//合格实验室校验报告
	
	@Column(name = "m33", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String m33;//验收最终结果和相关说明
	
	@Column(name = "m34", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m34;//状态（0.待上传附件1.待审核2.通过3.不通过）
	
	@Column(name = "m35", nullable = true, columnDefinition="varchar(2000)")
	@Getter
	@Setter
	private String m35;//附件
	
	@Column(name = "qgsbid", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private Long qgsbid;//请购设备id
}
