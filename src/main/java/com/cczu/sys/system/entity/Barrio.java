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
 * 网格化 entity
 * @author jason
 * @date 2017年5月31日
 */
@Entity
@Table(name = "t_barrio")
@DynamicUpdate @DynamicInsert
public class Barrio implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8406186043651178932L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false,columnDefinition="bigint")
	@Setter
	@Getter
	private long id;//ID
	
	@Column(name = "m1",columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String m1;//网格名称
	
	@Column(name = "m2",columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String m2;//图标
	
	@Column(name = "m3",columnDefinition="int")
	@Setter
	@Getter
	private Integer m3;//排序
	
	@Column(name = "m4",columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String m4;//备注
	@Column(name = "lng", nullable = true, length = 50)
	@Setter
	@Getter
	private String lng;//经度	

	@Column(name = "lat", nullable = true, length = 50)
	@Setter
	@Getter
	private String lat;//纬度	
	
	@Column(name = "code",columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String code;//网格编码
	
	@Column(name = "mappoint",columnDefinition="varchar(max)")
	@Setter
	@Getter
	private String mappoint;//网格地图实线交汇点
	
	
	@Column(name = "pcode",columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String pcode;//父网格编码
	

	@Column(name = "fid",columnDefinition="bigint")
	@Setter
	@Getter
	private long fid;//父id
	 

	/** default constructor */
	public Barrio() {
	}

	/** minimal constructor */
	public Barrio(String m1) {
		this.m1 = m1;
	}
	
	public Barrio(long id) {
		this.id=id;
	}
	
	public Barrio (long id,String pcode,String m1){
		this.id=id;
		this.pcode=pcode;
		this.m1=m1;
	}
	
	public Barrio (String pcode,String m1,String m2){
		this.pcode=pcode;
		this.m1=m1;
		this.m2=m2;
	}

	public Barrio(String m1, String m2,
			Integer m3, String m4) {
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
		this.m4 = m4;
	}
	
	public Barrio(String pcode, String m1, String m2,
			Integer m3, String m4) {
		this.pcode = pcode;
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
		this.m4 = m4;
	}
	
	public Barrio(String pcode, String m1, String m2,
			Integer m3, String m4, String code) {
		this.pcode = pcode;
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
		this.m4 = m4;
		this.code = code;
	}

}