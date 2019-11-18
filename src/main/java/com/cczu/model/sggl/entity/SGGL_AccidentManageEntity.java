package com.cczu.model.sggl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: SGGL_AccidentManageEntity
 * @Description: 事故管理
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="sggl_accidentmanage")
public class SGGL_AccidentManageEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -869831118280016002L;

	@Column(name = "M1", nullable = true, length = 50)
	@Setter
	@Getter
	private String M1;//事故编号
	
	@Column(name = "M2", nullable = true, length = 100)
	@Setter
	@Getter
	private String M2;//事故名称

	@Column(name = "M3", nullable = true, length = 50)
	@Setter
	@Getter
	private String M3;//事故类型

	@Column(name = "M4", nullable = true, length = 20)
	@Setter
	@Getter
	private String M4;//事故等级(特大/重大/较大/一般)
	
	@Column(name = "M5", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M5;//发生时间

	@Column(name = "M6", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M6;//发生地点
	
	@Column(name = "M7", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M7;//事故性质（责任事故/意外事故/其他[可下拉可输]）
	
	@Column(name = "M8", nullable = true, columnDefinition="float")
	@Setter
	@Getter	
	private Float M8;//死亡人数
	
	@Column(name = "M9", nullable = true, columnDefinition="float")
	@Setter
	@Getter	
	private Float M9;//重伤人数
	
	@Column(name = "M10", nullable = true, columnDefinition="float")
	@Setter
	@Getter	
	private Float M10;//轻伤人数
	
	@Column(name = "M11", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Float M11;//经济损失(万元)

	@Column(name = "M12", nullable = true, columnDefinition="float")
	@Setter
	@Getter	
	private Float M12;//伤亡人员
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M13;//事故经过
	
	@Column(name = "M14", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M14;//救援情况
	
	@Column(name = "M15", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M15;//事故教训
	
	@Column(name = "M16", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M16;//事故原因分析
	
	@Column(name = "M17", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M17;//事故预防措施
	
	@Column(name = "M18", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M18;//事故责任人处理
	
	@Column(name = "M19", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M19;//相关人员教育情况
	
	@Column(name = "M20", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M20;//备注
	
	@Column(name = "M21", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M21;//事故现场照片/视频
	
	@Column(name = "M22", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M22;//事故调查报告
	
	@Column(name = "M23", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M23;//所属部门
	
	@Column(name = "qyid", nullable = true, length = 8)
	@Setter
	@Getter
	public Long qyid;//企业id

	@Column(name = "zt", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	private String zt;//是否上报(1.已上报，未上报)
}
