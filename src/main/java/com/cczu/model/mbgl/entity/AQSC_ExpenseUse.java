package com.cczu.model.mbgl.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: AQSC_ExpenseUse
 * @Description: 安全生产-费用使用
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqsc_expenseuse")
public class AQSC_ExpenseUse extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//企业id
	
	@Column(name = "ID2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String ID2;//部门名称（多选）
	
	@Column(name = "M1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M1;//日期
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M2;//支出项目类别
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M3;//具体用途
	
	@Column(name = "M4", nullable = true)
	@Setter
	@Getter
	public Float M4;//金额  （万元）
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M5;//经办人
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M6;//审核人

	@Column(name = "M7", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M7;//批准人
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M8;//备注
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M9;//附件地址
	
}
