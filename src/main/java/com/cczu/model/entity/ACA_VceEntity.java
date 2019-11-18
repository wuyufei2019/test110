package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: ACA_VceEntity
 * @Description: 事故后果计算_化学爆炸（蒸气云爆炸）
 * @author jason
 *
 */
@Entity
@Table(name="aca_vce")
public class ACA_VceEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	public ACA_VceEntity() {
	}

	public ACA_VceEntity(Long id) {
		this.ID=id;
	}
	
	public ACA_VceEntity(String m1,String m1_1, String m2, String m3,String m6, String m7) {
		this.M1 = m1;
		this.M1_1 = m1_1;
		this.M2 = m2;
		this.M3 = m3;
		this.M6 = m6;
		this.M7 = m7;
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
	private String M2;//质量：M
	
	@Column(name="M3", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M3;//燃烧热：Hc
	
	@Column(name="M4", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M4;//7.5 代表死亡
	
	@Column(name="M4_1", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M4_1;//25代表重伤

	@Column(name="M4_2", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M4_2;//12.5 代表轻伤
	
	@Column(name="M5", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M5;//死亡计算结果
	
	@Column(name="M5_1", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M5_1;//重伤计算结果
	
	@Column(name="M5_2", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M5_2;//轻伤计算结果
	
	@Column(name="M6", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M6;//经度
	
	@Column(name="M7", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M7;//纬度
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//用户
}
