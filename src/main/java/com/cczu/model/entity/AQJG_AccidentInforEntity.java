package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 安全监管--事件管理
 * @author jason
 * @date 2017年6月27日
 */
@Entity
@Table(name="aqjg_accidentinfor")
public class AQJG_AccidentInforEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2678040243987337810L;

	@Column(name = "M1", nullable = false, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M1;//发生时间
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M2;//发生单位   (下拉，从企业表中选择)
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M3;//发生地点
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M4;//事故类型 (下拉)
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M5;//事故等级 (下拉)
	
	@Column(name = "M6", nullable = true, columnDefinition="int")
	@Setter
	@Getter	
	private Integer M6;//死亡人数
	
	@Column(name = "M7", nullable = true, columnDefinition="int")
	@Setter
	@Getter	
	private Integer M7;//重伤人数
	
	@Column(name = "M8", nullable = true, columnDefinition="int" )
	@Setter
	@Getter	
	private Integer M8;//轻伤人数
	
	@Column(name = "M9", nullable = true, columnDefinition="float" )
	@Setter
	@Getter	
	private Float M9;//直接经济损失
	
	@Column(name = "M10", nullable = true, columnDefinition="float" )
	@Setter
	@Getter	
	private Float M10;//间接经济损失
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M11;//事故描述
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M12;//事故处理
	
	@Column(name = "M13", nullable = true,columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M13;//事故预防对策
	
	@Column(name = "M14", nullable = true,columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M14;//备注
	
	@Column(name = "M15", nullable = true,columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M15;//附件---事故调查
	
	@Column(name = "M16", nullable = true,columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M16;//附件---事故处理
}
