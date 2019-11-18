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
 * @ClassName: BIS_EmployeeTest_Second
 * @Description: 企业基本信息-员工体检历史表
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="bis_employeetest_second")
public class BIS_EmployeeTest_Second extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -3929461343932061100L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//员工id
	
	@Column(name = "qyid", nullable = true, length = 8)
	@Setter
	@Getter
	public Long qyid;//企业id

	@Column(name = "M1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M1;//体检日期
	
	@Column(name = "M2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M2;//下次体检日期
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M3;//体检类型
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M4;//体检医院
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String M5;//接触危害因素
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String M6;//体检结果
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String M7;//体检结论
	
	
	
}
