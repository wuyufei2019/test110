package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;


@Entity
@Table(name="fxgk_accidentrisk")
public class FXGK_AccidentRisk extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4793560532817752303L;
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long ID1;//企业ID
	
	@Column(name = "DEPID", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long depid;//部门ID
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M1;//较大风险点名称
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(max)")
	@Setter
	@Getter
	private String M2;//风险分类
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M3;//行业
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M4;//行业类别
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M5;//工段
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M6;//部位
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M7;//主要危险有害性
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String M8;//易导致事故类型
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(2)")
	@Setter
	@Getter
	private String M9;//风险分级 (红、橙、黄、蓝)
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(max)")
	@Setter
	@Getter
	private String M10;//管控措施
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(max)")
	@Setter
	@Getter
	private String M11;//应急处置对策
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M12;//依据
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M13;//负责人
	
	@Column(name = "M14", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String M14;//联系方式
	
	@Column(name = "M15", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M15;//管控层级
	
	@Column(name = "M16", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M16;//现场照片
	
	@Column(name = "M17", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M17;//警示标志

	@Column(name = "M18", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M18;//编号
	
	@Column(name = "M19", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String M19;//X坐标
	
	@Column(name = "M20", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String M20;//Y坐标
	
	@Column(name = "lng", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String lng;//经度
	
	@Column(name = "lat", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String lat;//纬度
	
	@Column(name = "rfid", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String rfid;//绑定rfid
	
	@Column(name = "checkpointadderss", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String checkpointadderss;//rfid卡批次代码

	@Column(name = "bindcontent", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String bindcontent;//绑定二维码
	
	@Column(name = "usetype", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	private Integer usetype;//用途

	
	@Column(name = "areaname", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private  String areaname;//场地名称
	
	@Column(name = "aprobability", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	private String aprobability;//事故发生的可能性

	@Column(name = "exposefrequency", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	private String exposefrequency;//事故暴露频率
	
	@Column(name = "aseverity", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	private String aseverity;//事故后果严重性

	@Column(name = "radio1", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	private String radio1;//风险分级方式

	@Column(name = "result", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String result;//风险值
	
	@Column(name = "fjgk1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String fjgk1;//企业
	
	@Column(name = "fjgk2", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String fjgk2;//部门
	
	@Column(name = "fjgk3", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String fjgk3;//班组
	
	@Column(name = "fjgk4", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String fjgk4;//岗位
	
	@Column(name="M23", nullable=true, columnDefinition="varchar(2)")
	@Setter
	@Getter
	private String M23;//是否是重大危险源（0.否1.是）

	@Column(name="M24", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M24;//企业安全承诺卡
	
	@Override
	public String toString() {
		return "Sgfx_AccidentRisk [ID1=" + ID1 + ", M1=" + M1 + ", M2=" + M2
				+ ", M3=" + M3 + ", M4=" + M4 + ", M5=" + M5 + ", M6=" + M6
				+ ", M7=" + M7 + ", M8=" + M8 + ", M9=" + M9 + ", M10=" + M10
				+ ", M11=" + M11 + ", M12=" + M12 + ", M13=" + M13 + ", M14="
				+ M14 + ", M15=" + M15 + ", M16=" + M16 + ", M17=" + M17
				+ ", M18=" + M18 + ", M19=" + M19 + ", M20=" + M20 + "]";
	}
}
