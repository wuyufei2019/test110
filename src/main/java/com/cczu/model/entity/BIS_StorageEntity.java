package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: BIS_StorageEntity
 * @Description: 企业基本信息-仓库
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="bis_storage")
public class BIS_StorageEntity extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -8449150594078266458L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号
	
	@Column(name = "M1", nullable = true, length = 20)
	@Setter
	@Getter
	private String M1;//仓库名称

	@Column(name = "M2", nullable = true, length = 20)
	@Setter
	@Getter
	private String M2;//仓库编号

	@Column(name = "M3", nullable = true, columnDefinition="float")
	@Setter
	@Getter	
	private Double M3;//建筑面积

	@Column(name = "M4", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M4;//火灾危险等级

	@Column(name = "M5", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M5;//建筑结构

	@Column(name = "M6", nullable = true)
	@Setter
	@Getter	
	private Integer M6;//层数
	
	@Column(name = "M7", nullable = true)
	@Setter
	@Getter	
	private String M7;//备注
	
	@Column(name = "M8", nullable = true,columnDefinition="float")
	@Setter
	@Getter	
	private Double M8;//占地面积

	@Column(name = "M9", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M9;//耐火等级
	
	@Column(name = "M10", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M10;//储存物料

	@Column(name = "M11", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M11;//现场照片
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M12;//图纸

	@Column(name = "M13", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M13;//所属仓库区
}
