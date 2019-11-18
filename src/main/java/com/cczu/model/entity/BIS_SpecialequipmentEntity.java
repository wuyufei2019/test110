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
 * @ClassName: BIS_SpecialequipmentEntity
 * @Description: 企业基本信息-特种设备信息
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="bis_specialequipment")
public class BIS_SpecialequipmentEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -3929461343932061100L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号

	@Column(name = "M1", nullable = true, length = 30)
	@Setter
	@Getter
	private String M1;//设备名称

	@Column(name = "M2", nullable = true, length = 30)
	@Setter
	@Getter
	private String M2;//设备编号

	@Column(name = "M3", nullable = true )
	@Setter
	@Getter	
	private String M3;//设备类型

	@Column(name = "M4", nullable = true, length = 30)
	@Setter
	@Getter	
	private String M4;//规格型号
	
	@Column(name = "M5", nullable = true, length = 50)
	@Setter
	@Getter	
	private String M5;//主要参数
	
	@Column(name = "M6", nullable = true)
	@Setter
	@Getter	
	private Integer M6;//数量
	
	@Column(name = "M7", nullable = true, length = 50)
	@Setter
	@Getter	
	private String M7;//功能
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M8;//备注
	
	@Column(name = "M9", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M9;//特种设备检测日期(上次审验)
	
	@Column(name = "M10", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M10;//有效期
	
	@Column(name = "M11", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M11;//检测单位
	
	@Column(name = "M12", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M12;//检测报告文件
	
	@Column(name = "M13", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M13;//照片上传
	
	@Column(name="M14", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M14;//投入使用时间(始用年月)

	@Column(name="M15", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M15;//出厂年月

	@Column(name = "M16", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M16;//生产厂名
	
	@Column(name = "M17", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M17;//出厂编号
	
	@Column(name = "M18", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M18;//使用证号注册代码
	
	@Column(name = "M19", nullable = true, length = 50)
	@Setter
	@Getter	
	private String M19;//使用部门/责任部门
	
	@Column(name = "M20", nullable = true, length = 50)
	@Setter
	@Getter	
	private String M20;//使用地点
	
	@Column(name = "M21", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M21;//设备参数相关信息
}
