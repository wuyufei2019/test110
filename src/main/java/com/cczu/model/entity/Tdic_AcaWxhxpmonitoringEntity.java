package com.cczu.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @ClassName: Tdic_WxhxpEntity
 * @Description: 字典-危险化学品分类表
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="tdic_acawxhxpmonitoring")
@DynamicUpdate @DynamicInsert
public class Tdic_AcaWxhxpmonitoringEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//编号
	
	@Column(name="M1", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//化学品名称
	
	@Column(name="M2", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M2;//分子量M
	
	@Column(name="M3", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M3;//沸点℃

	@Column(name="M4", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M4;//比热容CP  kJ/(kg·K)

	@Column(name="M5", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M5;//CV

	@Column(name="M6", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M6;//热容比k

	@Column(name="M7", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M7;//（瞬时只取）边界浓度1

	@Column(name="M8", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M8;//边界浓度2

	@Column(name="M9", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M9;//边界浓度3

	@Column(name="M10", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M10;//密度kg/m3

	@Column(name="M11", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M11;//相对于水   不用

	@Column(name="M12", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M12;//燃烧热kJ/mol   不用

	@Column(name="M13", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M13;//燃烧热kJ/kg

	@Column(name="M14", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M14;//蒸发热（kJ/kg）
}
