package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * @ClassName: BIS_WorkshopEntity
 * @Description: 企业基本信息- 企业化学能源信息表
 * @author jason
 * @date 2017年7月18日
 */
@Entity
@Table(name="bis_chemicalEnergySource")
public class BIS_ChemicalEnergySourceEntity extends BaseEntity {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7411102103151651025L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号
	
	@Column(name = "ID2", nullable = true, length = 8)
	@Setter
	@Getter
	private Long ID2;//操作者id
	
	@Column(name = "M1", nullable = true, length = 200)
	@Setter
	@Getter
	private String M1;//能源名称
	
	@Column(name = "M2", nullable = true, length = 100)
	@Setter
	@Getter
	private String M2;//年用量（t/m3）
	
	@Column(name = "M3", nullable = true, length = 100)
	@Setter
	@Getter
	private String M3;//最大储存量（t/m3）
	
	@Column(name = "M4", nullable = true, length = 100)
	@Setter
	@Getter
	private String M4;//涉及工艺
	
	@Column(name = "M5", nullable = true, length = 100)
	@Setter
	@Getter
	private String M5;//是否进行安全评价
	
	@Column(name = "M6", nullable = true, length = 100)
	@Setter
	@Getter
	private String M6;//是否设置监控设施
	
	@Column(name = "M8", nullable = true)
	@Setter
	@Getter
	private String M8;//设置监控设施内容
	
	@Column(name = "M7", nullable = true, length = 1000)
	@Setter
	@Getter
	private String M7;//备注

	@Column(name = "M9", nullable = true, length = 100)
	@Setter
	@Getter
	private String M9;//存量
}
