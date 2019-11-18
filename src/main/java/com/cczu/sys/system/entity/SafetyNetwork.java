package com.cczu.sys.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 安全管理网络 entity
 * @author jason
 */
@Entity
@Table(name = "t_safetynetwork")
@DynamicUpdate @DynamicInsert
public class SafetyNetwork implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4677612831083903353L;

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
	
	@Column(name = "M1",columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String m1;//名称
	
	@Column(name = "M2",columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m2;//主要负责人
	
	@Column(name = "M3",columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m3;//主要负责人联系方式
	
	@Column(name = "M4",columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String m4;//安全职责

	@Column(name = "M5",columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String m5;//备注
	
	@Column(name = "ID1",columnDefinition="bigint")
	@Setter
	@Getter
	private long ID1;//企业id
	
	// Constructors

	/** default constructor */
	public SafetyNetwork() {
	}

	/** minimal constructor */
	public SafetyNetwork(String m1) {
		this.m1 = m1;
	}
	
	public SafetyNetwork(long id) {
		this.id=id;
	}
	
	public SafetyNetwork (long id,String m1){
		this.id=id;
		this.m1=m1;
	}

	public SafetyNetwork(long id, long pid, String m1, String m2, String m3,
			String m4, String m5, long iD1) {
		super();
		this.id = id;
		this.pid = pid;
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
		this.m4 = m4;
		this.m5 = m5;
		ID1 = iD1;
	}



}