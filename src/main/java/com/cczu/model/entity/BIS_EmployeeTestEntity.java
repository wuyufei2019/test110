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
 * @ClassName: BIS_EmployeeTestEntity
 * @Description: 员工体检信息
 *
 */
@Entity
@Table(name="bis_employeetest")
public class BIS_EmployeeTestEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5272729602293545615L;

	@Column(name = "ID1", nullable = true,columnDefinition="bigint")
	@Setter
	@Getter
	private long ID1;//企业编号
	
	@Column(name = "M1", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M1;//员工身份证号
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M2;//体检类型
	
	@Column(name = "M3", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M3;//体检日期
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M4;//体检医院
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String M5;//体检结论
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String M6;//备注
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String M7;//员工姓名
	
	@Column(name = "M8", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M8;//下次体检日期
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String M9;//接触危害因素
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String M10;//体检结果
}
