package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: ACA_PhysicalEntity
 * @Description: 事故后果计算_物理爆炸（压力容器爆炸）
 * @author jason
 *
 */
@Entity
@Table(name="aca_physical")
public class ACA_PhysicalEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	public ACA_PhysicalEntity() {
	}

	public ACA_PhysicalEntity(Long id) {
		this.ID=id;
	}
	
	public ACA_PhysicalEntity(String m1, String m2, String m5, String m6 ) {
		this.M1 = m1;
		this.M2 = m2;
		this.M5 = m5;
		this.M6 = m6;
	}
	
	@Column(name="M1", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//压力
	
	@Column(name="M2", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M2;//容积
	
	@Column(name="M3", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M3;// 代表死亡
	
	@Column(name="M3_1", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M3_1;//代表重伤

	@Column(name="M3_2", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M3_2;//代表轻伤
	
	@Column(name="M4", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M4;//死亡计算结果
	
	@Column(name="M4_1", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M4_1;//重伤计算结果
	
	@Column(name="M4_2", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M4_2;//轻伤计算结果
	
	@Column(name="M5", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M5;//经度
	
	@Column(name="M6", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M6;//纬度
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//用户
}
