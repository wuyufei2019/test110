package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: ACA_LeakageEntity
 * @Description: 事故后果计算_持续泄漏
 * @author jason
 *
 */
@Entity
@Table(name="aca_leakage")
public class ACA_LeakageEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	public ACA_LeakageEntity() {
	}

	public ACA_LeakageEntity(Long id) {
		this.ID=id;
	}
	
	public ACA_LeakageEntity(String m1,String m1_1, String m2, String m3, String m4,
			String m5, String m6, String m7, String m8, String m9, String m10, String m11
			, String m12, String m13, String m14) {
		M1 = m1;
		M1_1 = m1_1;
		M2 = m2;
		M3 = m3;
		M4 = m4;
		M5 = m5;
		M6 = m6;
		M7 = m7;
		M8 = m8;
		M9 = m9;
		M10 = m10;
		M11 = m11;
		M12 = m12;
		M13 = m13;
		M14 = m14;
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
	private String M2;//边界浓度1
	
	@Column(name="M3", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M3;//边界浓度2
	
	@Column(name="M4", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M4;//边界浓度3
	
	@Column(name="M5", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M5;//分子量
	
	@Column(name="M6", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M6;//绝热指数
	
	@Column(name="M7", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M7;//温度
	
	@Column(name="M8", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M8;//泄漏孔直径
	
	@Column(name="M9", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M9;//内部压力
	
	@Column(name="M10", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M10;//地面风速
	
	@Column(name="M11", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M11;//风向

	@Column(name="M12", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M12;//天气条件
	
	@Column(name="M13", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M13;//经度
	
	@Column(name="M14", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M14;//纬度
	
	@Column(name="M15", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M15;//FX风向角度
	
	@Column(name="M16", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M16;//WDX稳定度
	
	@Column(name="M17", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M17;//泄漏速度
	
	@Column(name="M18", nullable=true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M18;//计算返回lng1
	
	@Column(name="M19", nullable=true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M19;//计算返回lat1
	
	@Column(name="M20", nullable=true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M20;//计算返回lng2
	
	@Column(name="M21", nullable=true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M21;//计算返回lat2
	
	@Column(name="M22", nullable=true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M22;//计算返回lng3
	
	@Column(name="M23", nullable=true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M23;//计算返回lat3
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//用户
}
