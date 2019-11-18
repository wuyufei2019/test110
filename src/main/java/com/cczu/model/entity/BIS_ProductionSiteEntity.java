package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @ClassName: BIS_ProductionSiteEntity
 * @Description: 企业基本信息-生产场所
 * @author wbth
 * @date 2019年8月30日
 *
 */
@Entity
@Table(name="bis_productionsite")
public class BIS_ProductionSiteEntity extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1106837690821192296L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private Long ID1;//企业编号

	@Column(name = "M1", nullable = true, length = 50)
	@Setter
	@Getter
	private String M1;//单元名称
	
	@Column(name = "M2", nullable = true, length = 50)
	@Setter
	@Getter
	private String M2;//固定资产总值
	
	@Column(name = "M3", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M3;//lng
	
	@Column(name = "M4", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M4;//lat
	
	@Column(name = "M5", nullable = true, length = 50)
	@Setter
	@Getter	
	private String M5;//编号
	
	@Column(name = "M6", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M6;//占地面积
	
	@Column(name = "M7", nullable = true, length = 20)
	@Setter
	@Getter
	private String M7;//正常当班人数

	@Column(name = "M8", nullable = true, length = 500)
	@Setter
	@Getter
	private String M8;//单元内危险化学品

	@Column(name = "M9", nullable = true, length = 500)
	@Setter
	@Getter
	private String M9;//备注

}
