package com.cczu.model.sbssgl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-设备交付
 * @author ZPC
 */
@Entity
@Table(name = "sbssgl_sbjf")
public class SBSSGL_SBJFEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//企业id
	
	@Column(name = "sbysid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long sbysid;//设备验收id

	@Column(name = "m1", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m1;//调出单位
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m2;//调入单位
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m3;//事由
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m4;//设备编号
	
	@Column(name = "m5", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m5;//名称型号
	
	@Column(name = "m6", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m6;//出厂编号
	
	@Column(name = "m7", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m7;//账面原值
	
	@Column(name = "m8", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m8;//现值
	
	@Column(name = "m9", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m9;//残值
	
	@Column(name = "m10", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m10;//折旧年限
	
	@Column(name = "m11", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m11;//已提折旧
	
	@Column(name = "m12", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m12;//年折旧额
	
	@Column(name = "m13", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String m13;//折旧方法
	
	@Column(name = "m14", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m14;//完好程度
	
	@Column(name = "m15", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp m15;//启用、封存、调拨日期
	
	@Column(name = "m16", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp m16;//封存开始日期
	
	@Column(name = "m17", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp m17;//封存结束日期
	
	@Column(name = "m18", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String m18;//记事
	
	@Column(name = "m19", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String m19;//附件
	
	@Column(name = "m20", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String m20;//状态（0.待上传附件1.待建立设备台账2.已建立设备台账）
}
