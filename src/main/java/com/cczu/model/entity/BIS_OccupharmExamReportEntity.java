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
 * @ClassName: BIS_OccupharmExamReportEntity
 * @Description: 企业基本信息-职业卫生_检测报告
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="bis_occupharmexamreport")
public class BIS_OccupharmExamReportEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -6182789765305881383L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号
	
	@Column(name = "ID2", nullable = true, length = 8)
	@Setter
	@Getter
	private Long ID2;//操作者

	@Column(name = "M1", nullable = true, length = 100)
	@Setter
	@Getter
	private String M1;//检测机构
	
	@Column(name = "M2", nullable = true,columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M2;//检测日期

	@Column(name = "M3", nullable = true, length = 50)
	@Setter
	@Getter	
	private String M3;//检测报告编号
	
	@Column(name = "M4", nullable = true,columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M4;//下次检测日期
	
	@Column(name = "M5", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M5;//附件

	@Column(name = "M6", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M6;//检测结果
	
	@Column(name = "M7", nullable = true, length = 2)
	@Setter
	@Getter	
	private String M7;//1：已加入消息

	@Column(name = "M10", nullable = true, length = 50)
	@Setter
	@Getter
	private String M10;//类别
	
	@Column(name = "M11", nullable = true, length = 50)
	@Setter
	@Getter
	private String M11;//名称
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String M12;//X坐标
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String M13;//Y坐标
	
	@Column(name = "M14", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M14;//采样地点
}
