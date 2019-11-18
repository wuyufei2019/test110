package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 
 * @ClassName: BIS_Monitor_Point_ExtraEntity
 * @Description: 企业基本信息-监测点取值点维护数据
 * @author wbth
 * @date 2019年10月9日
 *
 */
@Entity
@Table(name="bis_monitor_point_extra")
public class BIS_Monitor_Point_ExtraEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1106837690821192296L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号

	@Column(name = "number", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String number;//传感器设备编号（传感器唯一标识，按照行政区划+ 行业+企业编码+设备编码执行 ）
	
	@Column(name = "salveId", nullable = true, columnDefinition="int")
	@Setter
	@Getter	
	private Integer salveId;//从站id

	@Column(name = "offset", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	private Integer offset;//偏移量

}
