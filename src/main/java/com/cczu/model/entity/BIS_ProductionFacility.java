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
 * @ClassName: BIS_ProductionFacility
 * @Description: 企业基本信息-生产设备信息
 *
 */
@Entity
@Table(name="bis_productionfacility")
public class BIS_ProductionFacility extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4550189612589284400L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M1;//设备位号
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M2;//设备类别
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(300)" )
	@Setter
	@Getter
	private String M3;//设备名称

	@Column(name = "M4", nullable = true, columnDefinition="varchar(200)" )
	@Setter
	@Getter
	private String M4;//规格尺寸

	@Column(name = "M5", nullable = true, columnDefinition="varchar(300)" )
	@Setter
	@Getter
	private String M5;//型号

	@Column(name = "M6", nullable = true, columnDefinition="varchar(200)" )
	@Setter
	@Getter
	private String M6;//数量

	@Column(name = "M7", nullable = true, columnDefinition="varchar(500)" )
	@Setter
	@Getter
	private String M7;//制造单位

	@Column(name = "M8", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M8;//投用日期

	@Column(name = "M9", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M9;//介质

	@Column(name = "M10", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M10;//下次检验日期

	@Column(name = "M11", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M11;//责任人

	@Column(name = "M12", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M12;//联系电话

	@Column(name = "M13", nullable = true, columnDefinition="varchar(2000)")
	@Getter
	@Setter
	private String M13;//主要危险性

	@Column(name = "m14", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M14;//功率

	@Column(name = "m15", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M15;//出厂日期

	@Column(name = "m16", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M16;//设备状况   完好 待修 待报废

	@Column(name = "m17", nullable = true, length = 8)
	@Getter
	@Setter
	private Long M17;//设备使用单位

	@Column(name = "m18", nullable = true, columnDefinition="varchar(2000)")
	@Getter
	@Setter
	private String M18;//主要技术参数
}
