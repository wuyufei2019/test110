package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: ACA_GasphysicalEntity
 * @Description: 事故后果计算_压缩气体物理爆炸
 * @author jason
 *
 */
@Entity
@Table(name="aca_gasphysical")
public class ACA_GasphysicalEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	public ACA_GasphysicalEntity() {
	}

	public ACA_GasphysicalEntity(Long id) {
		this.ID=id;
	}
	
	public ACA_GasphysicalEntity(String m1,String m1_1, String m2, String m3, String m4, String m7, String m8) {
		this.M1 = m1;
		this.M1_1 = m1_1;
		this.M2 = m2;
		this.M3 = m3;
		this.M4 = m4;
		this.M7 = m7;
		this.M8 = m8;
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
	private String M2;//热容比
	
	@Column(name="M3", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M3;//压力：P
	
	@Column(name="M4", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M4;//容积：V
	
	@Column(name="M5", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M5;//代表死亡
	
	@Column(name="M5_1", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M5_1;//代表重伤

	@Column(name="M5_2", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M5_2;// 代表轻伤
	
	@Column(name="M6", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M6;//死亡计算结果
	
	@Column(name="M6_1", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M6_1;//重伤计算结果
	
	@Column(name="M6_2", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M6_2;//轻伤计算结果
	
	@Column(name="M7", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M7;//经度
	
	@Column(name="M8", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M8;//纬度
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//用户
}
