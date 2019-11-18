package com.cczu.model.sbssgl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-设备管理
 * @author ZPC
 */
@Entity
@Table(name = "sbssgl_sbgl")
public class SBSSGL_SBGLEntity extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//企业id
	
	@Column(name = "sbjfid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long sbjfid;//设备交付id（没有则为0）

	@Column(name = "m1", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m1;//设备编号
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m2;//设备名称
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m3;//规格
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m4;//出厂编号
	
	@Column(name = "m5", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m5;//制造单位
	
	@Column(name = "m6", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp m6;//购置日期
	
	@Column(name = "m7", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m7;//价格
	
	@Column(name = "m8", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m8;//安装地点
	
	@Column(name = "m9", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m9;//系列号
	
	@Column(name = "m10", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m10;//电气功率/Power (30KVA)
	
	@Column(name = "m11", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m11;//用气量/Compressed Air(m3/min)（弃用）
	
	@Column(name = "m12", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m12;//用水量/Water（弃用）
	
	@Column(name = "m13", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m13;//外形尺寸/overall size
	
	@Column(name = "m14", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m14;//加工范围/Process field
	
	@Column(name = "m15", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m15;//设备重量/Weight
	
	@Column(name = "m16", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp m16;//启用时间
	
	@Column(name = "m17", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m17;//固定资产编号
	
	@Column(name = "m18", nullable = true, columnDefinition="varchar(2000)")
	@Getter
	@Setter
	private String m18;//备注
	
	@Column(name = "m19", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m19;//状态（0.启用1.停用2.报废）
	
	@Column(name = "bgrid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long bgrid;//保管人id
	
	@Column(name = "m20", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m20;//设备类型（0.A类1.B类2.C类）
	
	@Column(name = "m21", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m21;//是否是特种设备（0.否1.是）

	@Column(name = "m22", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String m22;//设备正面照片附件
	
	@Column(name = "m23", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private Long m23;//部门id
	
	@Column(name = "m24", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String m24;//设备侧面照片附件
	
	@Column(name = "m25", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String m25;//设备铭牌照片附件
	
	@Column(name = "m26", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m26;//是否有备品备件信息（0.否1.是）
	
	@Column(name = "m27", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m27;//型号
	
/****************************** 特种设备专有字段 ******************************************/	
	@Column(name = "m28", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp m28;//注册登记日期
	
	@Column(name = "m29", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m29;//状态（0.不完好1.完好）
	
	@Column(name = "m31", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp m31;//本次检验日期
	
	@Column(name = "m32", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp m32;//下次检验日期
	/****************************** 特种设备专有字段 ******************************************/		
	
	@Column(name = "m30", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m30;//安装单位
	
	@Column(name = "m33", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m33;//加工精度
	
	@Column(name = "m34", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m34;//复杂系数
	
	@Column(name = "m35", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m35;//原值
	
}
