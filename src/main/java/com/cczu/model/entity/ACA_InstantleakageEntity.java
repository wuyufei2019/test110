package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: ACA_InstantleakageEntity
 * @Description: 事故后果计算_瞬时泄漏
 * @author jason
 *
 */
@Entity
@Table(name="aca_instantleakage")
public class ACA_InstantleakageEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	public ACA_InstantleakageEntity() {
	}

	public ACA_InstantleakageEntity(Long id) {
		this.ID=id;
	}
	
	public ACA_InstantleakageEntity(String m1,String m1_1, String m2, String m3, String m4,
			String m5, String m6, String m7, String m8 ) {
		M1 = m1;
		M1_1 = m1_1;
		M2 = m2;
		M3 = m3;
		M4 = m4;
		M5 = m5;
		M6 = m6;
		M7 = m7;
		M8 = m8;
	}
	
	@Column(name="M1", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//物质
	
	@Column(name="M1_1", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1_1;//物质中文名称
	
	@Column(name="M2", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M2;//边界浓度
	
	@Column(name="M3", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M3;//泄漏质量
	
	@Column(name="M4", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M4;//地面风速
	
	@Column(name="M5", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M5;//风向
	
	@Column(name="M6", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M6;//天气条件
	
	@Column(name="M7", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M7;//经度
	
	@Column(name="M8", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M8;//纬度
	
	@Column(name="M9", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M9;//时间
	
	@Column(name="M10", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M10;//FX风向角度
	
	@Column(name="M11", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M11;//WDX稳定度
	
	@Column(name="M12", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M12;//每次计算返回的经度lng
	
	@Column(name="M13", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M13;//每次计算返回的纬度lat
	
	@Column(name="M14", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M14;//每次计算返回的半径r
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//用户
}
