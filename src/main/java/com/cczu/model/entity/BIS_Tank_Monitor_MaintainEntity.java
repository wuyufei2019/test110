package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @ClassName: BIS_TankEntity
 * @Description: 企业基本信息-储罐检测维护数据
 * @author wbth
 * @date 2019年9月16日
 *
 */
@Entity
@Table(name="bis_tank_monitor_maintain")
public class BIS_Tank_Monitor_MaintainEntity extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1106837690821192296L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID1;//储罐id

	@Column(name = "WDCGQBH", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String WDCGQBH;//温度传感器编号（传感器唯一标识，按照行政区划+ 行业+企业编码+设备编码执行 ）
	
	@Column(name = "WDDWH", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String WDDWH;//温度点位号
	
	@Column(name = "WDCGQWZ", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String WDCGQWZ;//温度传感器位置
	
	@Column(name = "WDBJYZ1", nullable = true, columnDefinition="float")
	@Setter
	@Getter	
	private String WDBJYZ1;//温度第一级报警阈值
	
	@Column(name = "WDBJYZ2", nullable = true, columnDefinition="float")
	@Setter
	@Getter	
	private String WDBJYZ2;//温度第二级报警阈值
	
	@Column(name = "YLCGQBH", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String YLCGQBH;//压力传感器编号（传感器唯一标识，按照行政区划+ 行业+企业编码+设备编码执行 ）
	
	@Column(name = "YLDWH", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String YLDWH;//压力点位号

	@Column(name = "YLCGQWZ", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String YLCGQWZ;//压力传感器位置
	
	@Column(name = "YLBJYZ1", nullable = true, columnDefinition="float")
	@Setter
	@Getter	
	private String YLBJYZ1;//压力第一级报警阈值
	
	@Column(name = "YLBJYZ2", nullable = true, columnDefinition="float")
	@Setter
	@Getter	
	private String YLBJYZ2;//压力第二级报警阈值
	
	@Column(name = "YWCGQBH", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String YWCGQBH;//液位传感器设备编号（传感器唯一标识，按照行政区划+ 行业+企业编码+设备编码执行 ）
	
	@Column(name = "YWDWH", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String YWDWH;//液位点位号
	
	@Column(name = "YWCGQWZ", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String YWCGQWZ;//液位传感器位置
	
	@Column(name = "YWGWXBJYZ", nullable = true, columnDefinition="float")
	@Setter
	@Getter	
	private String YWGWXBJYZ;//液位高位限报警阈值
	
	@Column(name = "YWGGWXBJYZ", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private String YWGGWXBJYZ;//液位高高位限报警阈值
	
	@Column(name = "YWDWXBJYZ", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private String YWDWXBJYZ;//液位低位限报警阈值
	
	@Column(name = "YWDDWXBJYZ", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private String YWDDWXBJYZ;//液位低低位限报警阈值
	
}
