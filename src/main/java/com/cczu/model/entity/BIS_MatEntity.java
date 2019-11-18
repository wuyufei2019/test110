package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: BIS_MatEntity
 * @Description: 企业基本信息-企业原料信息
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="bis_mat")
public class BIS_MatEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -7293698814167710734L;

	@Column(name="uuid", nullable=true, columnDefinition="varchar(36)")
	@Setter
	@Getter
	private String uuid;//唯一编码 UUID

	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	private long ID1;//企业id

	@Column(name="companycode", nullable=true, columnDefinition="varchar(11)")
	@Setter
	@Getter
	private String companycode;//企业编码

	@Column(name="M1", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//原料名称

	@Column(name = "chemicalalias", nullable = true, length = 200)//新增
	@Setter
	@Getter
	private String chemicalalias;//别名

	@Column(name = "M11", nullable = true, columnDefinition="varchar(6)")//政府端为VARCHAR(6)
	@Setter
	@Getter
	private String M11;//化学品类型（1:产品;2:中间产品;3:进口化学品;4:原料;）

	@Column(name = "M4", nullable = true, length = 100)//长度改100
	@Setter
	@Getter
	private String M4;//CAS号

	@Column(name="capacity", nullable=true, columnDefinition="numeric(12,2)")
	@Setter
	@Getter
	private Float capacity;//产品生产能力（吨）

	@Column(name="capacitygas", nullable=true, columnDefinition="numeric(12,2)")
	@Setter
	@Getter
	private Float capacitygas;//产品生产能力气体（方）

	@Column(name="reserve", nullable=true, columnDefinition="numeric(12,2)")
	@Setter
	@Getter
	private Float reserve;//产品最大储量（吨）

	@Column(name="reservegas", nullable=true, columnDefinition="numeric(12,2)")
	@Setter
	@Getter
	private Float reservegas;//产品最大储量气体（方）

	@Column(name="parkid", nullable=true, columnDefinition="char(32)")
	@Setter
	@Getter
	private String parkid;//所属园区标识（由省厅统一下发）

	@Column(name="districtcode", nullable=true, columnDefinition="char(32)")
	@Setter
	@Getter
	private String districtcode;//所属园区行政标识（区县级行政区划编码（6位））

	@Column(name = "M5", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M5;//储存方式/地点

	@Column(name = "chemicaltype", nullable = true, columnDefinition="varchar(50)")//新增
	@Setter
	@Getter
	private String chemicaltype;//危化品作用

	@Column(name = "M12", nullable = true, columnDefinition="char(1)")//varchar改char
	@Setter
	@Getter
	private String M12;//是否为重点监管危险化学品（0：否，1：是 （默认0））

	
	
	
	
	@Column(name = "M2", nullable = true )
	@Setter
	@Getter
	private Float M2;//年用量

	@Column(name = "M3", nullable = true )
	@Setter
	@Getter	
	private Float M3;//最大储量

	@Column(name = "M6", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M6;//主要危险性
	
	@Column(name = "M7", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M7;//备注
	
	@Column(name = "M8", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M8;//物料类别

	@Column(name = "M9", nullable = true, length = 4)
	@Setter
	@Getter	
	private String M9;//状态

	@Column(name = "M10", nullable = true, length = 2)
	@Setter
	@Getter	
	private String M10;//是否领证    是/否
	
	@Column(name = "M13", nullable = true, length = 10, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M13;//是否为剧毒化学品:1:是
	
	@Column(name = "M14", nullable = true, length = 10, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M14;//是否为易制毒化学品:1:是
}
