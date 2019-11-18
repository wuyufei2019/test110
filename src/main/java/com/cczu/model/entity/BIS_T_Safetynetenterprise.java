package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 安全网络entity
 * @author jason
 * @date 2017年5月31日
 */
@Entity
@Table(name = "bis_t_safetynetenterprise")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@DynamicUpdate @DynamicInsert
public class BIS_T_Safetynetenterprise implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false,columnDefinition="bigint")
	@Setter
	@Getter
	private long id;//ID
	
	@Column(name = "PID",columnDefinition="bigint")
	@Setter
	@Getter
	private long pid;//父ID
	
	@Column(name = "M1", nullable = false,columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String m1;//结构名称
	
	@Column(name = "M2",columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String m2;//主要负责人
	
	@Column(name = "M3",columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String m3;//负责人联系方式
	
	@Column(name = "M4",columnDefinition="text")
	@Setter
	@Getter
	private String m4;//安全职责
	
	@Column(name = "M5", length = 200,columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String m5;//备注

	@Column(name = "ID1",columnDefinition="bigint")
	@Setter
	@Getter
	private long id1;//企业id
	
	
	// Constructors

	/** default constructor */
	public BIS_T_Safetynetenterprise() {
	}

	/** minimal constructor */
	public BIS_T_Safetynetenterprise(String m1) {
		this.m1 = m1;
	}
	
	public BIS_T_Safetynetenterprise(long id) {
		this.id=id;
	}
	
	public BIS_T_Safetynetenterprise (long id,long pid,String m1){
		this.id=id;
		this.pid=pid;
		this.m1=m1;
	}
	
	public BIS_T_Safetynetenterprise (long pid,String m1,String m3){
		this.pid=pid;
		this.m1=m1;
		this.m3=m3;
	}

	public BIS_T_Safetynetenterprise(long id, long pid, String m1, String m2,
			String m3, String m4, String m5, long id1) {
		super();
		this.id = id;
		this.pid = pid;
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
		this.m4 = m4;
		this.m5 = m5;
		this.id1 = id1;
	}

 
}