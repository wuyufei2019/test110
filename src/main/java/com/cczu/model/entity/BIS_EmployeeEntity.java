package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: BIS_EmployeeEntity
 * @Description: 企业基本信息-企业员工基本信息
 * @author jason
 *
 */
@Entity
@Table(name="bis_employee")
public class BIS_EmployeeEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public BIS_EmployeeEntity() {
	}

	public BIS_EmployeeEntity(Long id) {
		this.ID=id;
	}
	
	public BIS_EmployeeEntity(String m1, String m2, String m3, String m4,
			String m5, String m6, String m7) {
		M1 = m1;
		M2 = m2;
		M3 = m3;
		M4 = m4;
		M5 = m5;
		M6 = m6;
		M7 = m7;
	}
	
	public BIS_EmployeeEntity(Long ID1,String ID2,Long ID3,Long ID4,String m1,String m2, String m3,
			String m9, Timestamp m10) {
		this.ID1=ID1;
		this.ID2=ID2;
		this.ID3=ID3;
		this.ID4=ID4;
		this.M1=m1;
		this.M2=m2;
		this.M3=m3;
		this.M9=m9;
		this.M10=m10;
		
	}
	
	@Column(name="M1", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//姓名
	
	@Column(name="M2", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M2;//工卡号
	
	@Column(name="M3", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M3;//性别
	
	@Column(name="M4", nullable=false, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M4;//岗位、工种（职位）
	
	@Column(name="M5", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M5;//学历
	
	@Column(name="M6", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M6;//专业
	
	@Column(name="M7", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M7;//职称（职务）
	
	@Column(name="M8", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M8;//身份证号
	
	@Column(name="M9", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M9;//联系方式
	
	@Column(name="M10", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M10;//出生日期
	
	@Column(name="M11", nullable=true, columnDefinition="varchar(5)")
	@Setter
	@Getter
	private String M11;//婚姻状况
	
	@Column(name="M12", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M12;//贯籍
	
	@Column(name="M13", nullable=true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String M13;//民族
	
	@Column(name="M14", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M14;//入职日期
	
	@Column(name="M15", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M15;//离职日期
	
	@Column(name="M16", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M16;//人员类别
	
	@Column(name="M17", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M17;//二维码
	
	@Column(name="M18", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M18;//责任区域
	
	@Column(name="M19", nullable=true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M19;//照片
	
	@Column(name="status", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private Integer status;//状态、1 正常 2：离职
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//用户

	@Column(name = "ID2", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String ID2;//行政区划ID
	
	@Column(name = "ID3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID3;//企业ID
	
	@Column(name = "ID4", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID4;//部门ID
}
