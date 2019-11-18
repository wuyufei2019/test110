package com.cczu.model.fkcl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 访客预约
 * @author ZPC
 */
@Entity
@Table(name = "fkcl_fkyy")
public class FKCL_FkyyEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//企业id
	
	@Column(name = "M1", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M1;//预约时间
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M2;//被预约人
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String M3;//事由
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String M4;//预约人
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String M5;//手机号码
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String M6;//预约确认人员
	
	@Column(name = "M7", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M7;//预约确认时间
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String M8;//进厂确认人员
	
	@Column(name = "M9", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M9;//进厂时间
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String M10;//进厂人数
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String M11;//出厂确认人员
	
	@Column(name = "M12", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M12;//出厂时间
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String M13;//出厂人数
	
	@Column(name = "status", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String status;//1.预约确认中2.拒绝预约3.预约通过待进厂4.进厂待出厂5.已出厂
}
