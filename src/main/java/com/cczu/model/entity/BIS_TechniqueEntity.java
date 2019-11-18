package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 企业基本信息-高危工艺
 * @author jason
 * @date 2017年7月24日
 */
@Entity
@Table(name="bis_technique")
public class BIS_TechniqueEntity extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1106837690821192296L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private Long ID1;//企业编号

	@Column(name = "M1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M1;//高危工艺名称
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M2;//物料名称
	
	@Column(name = "M3", nullable = true, columnDefinition="float")
	@Setter
	@Getter	
	private Double M3;//容积
	
	@Column(name = "M4", nullable = true )
	@Setter
	@Getter	
	private int M4;//类型
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M5;//位号
	
	@Column(name = "M6", nullable = true , columnDefinition="float")
	@Setter
	@Getter	
	private Double M6;//罐径
	
	@Column(name = "M7", nullable = true , columnDefinition="float")
	@Setter 
	@Getter
	private Double M7;//罐高
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String M8;//高危工艺类别
	
	@Column(name = "level1", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double level1;//高液位预警
	
	@Column(name = "level2", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double level2;//低液位预警
	
	@Column(name = "temperature1", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double temperature1;//高温度预警
	
	@Column(name = "temperature2", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double temperature2;//低温度预警
	
	@Column(name = "temperature3", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double temperature3;//高温度预警
	
	@Column(name = "temperature4", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double temperature4;//低温度预警
	
	@Column(name = "pressure1", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double pressure1;//高压力预警
	
	@Column(name = "pressure2", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double pressure2;//低压力预警
	
	@Column(name = "flux1", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double flux1;//高流量预警
	
	@Column(name = "flux2", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double flux2;//低流量预警
	
	@Column(name = "r1", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String r1;//液位点号
	
	@Column(name = "r2", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String r2;//温度点号
	
	@Column(name = "r3", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String r3;//压力点号
	
	@Column(name = "r4", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String r4;//流量点号
	
	
}
