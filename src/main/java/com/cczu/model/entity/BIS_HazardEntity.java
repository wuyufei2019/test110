package com.cczu.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

import java.sql.Timestamp;

/**
 * 
 * @ClassName: BIS_HazardEntity
 * @Description: 企业基本信息-重大危险源-重大危险源信息
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="bis_hazard")
public class BIS_HazardEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -2272338633536100579L;

	@Column(name = "hazardcode", nullable = true,columnDefinition="varchar(14)")
	@Setter
	@Getter
	private String hazardcode;//重大危险源编码(企业编码(11位）+3位流水号)

	@Column(name = "ID1", nullable = true,columnDefinition="bigint")
	@Setter
	@Getter
	private long ID1;//企业id

	@Column(name="companycode", nullable=true, columnDefinition="varchar(11)")
	@Setter
	@Getter
	private String companycode;//企业编码(企业编码外键（9位）)

	@Column(name = "M12", nullable = true,columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M12;//重大危险源名称      M12   hazardName

	@Column(name = "hazardshortname", nullable = true,columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String hazardshortname;//危险源简称

	@Column(name = "M1", nullable = true,columnDefinition="varchar(6)")
	@Setter
	@Getter
	private String M1;//重大危险源级别（1:一级;2:二级;3:三级;4:四级）  M1    hazardRank

	@Column(name = "M2", nullable = true,columnDefinition="numeric(8,2)") //类型修改了
	@Setter
	@Getter
	private Float M2;//R值       M2   rvalue

	@Column(name = "areacode", nullable = true,columnDefinition="varchar(6)")
	@Setter
	@Getter
	private String areacode;//行政区域编码（危险源所在地行政区域，到区县级（6位））

	@Column(name = "address", nullable = true,columnDefinition="varchar(400)")//长度改400
	@Setter
	@Getter
	private String address;//地址

	@Column(name = "longitude", nullable = true,columnDefinition="numeric(9,6)") //经度
	@Setter
	@Getter
	private Float longitude;//经度

	@Column(name = "latitude", nullable = true,columnDefinition="numeric(9,6)") //维度
	@Setter
	@Getter
	private Float latitude;//纬度

	@Column(name = "establishdate", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp establishdate;//投用日期

	@Column(name="protectiontargetdistance", nullable=true, columnDefinition="numeric(5)")
	@Setter
	@Getter
	private Integer protectiontargetdistance;//周边防护目标最近距离（米）

	@Column(name="people500m", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer people500m;//外边界500米范围人数估算

	@Column(name = "maxjar", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String maxjar;//最大罐组

	@Column(name="presvesselnum", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer presvesselnum;//压力容器总数

	@Column(name = "activetype", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String activetype;//生产经营活动类型（见附录，字典码表：E51）

	@Column(name="presVessel3thnum", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer presVessel3thnum;//三类压力容器总数

	@Column(name = "storagefacilityproperty", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String storagefacilityproperty;//生产存储场所产权（见附录，字典码表：E54）

	@Column(name = "material", nullable = true,columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String material;//主要产品

	@Column(name="lightdevlastchkdate", nullable=true, columnDefinition="datetime")//表中的类型为char(8)
	@Setter
	@Getter
	private Timestamp lightdevlastchkdate;//最近一次检测时间

	@Column(name="lightdevvalidate", nullable=true, columnDefinition="datetime")//表中的类型为char(8)
	@Setter
	@Getter
	private Timestamp lightdevvalidate;//到期时间

	@Column(name="startarchivedate", nullable=true, columnDefinition="datetime")//表中的类型为char(8)
	@Setter
	@Getter
	private Timestamp startarchivedate;//危险源备案期限(起)

	@Column(name="endarchivedate", nullable=true, columnDefinition="datetime")//表中的类型为char(8)
	@Setter
	@Getter
	private Timestamp endarchivedate;//危险源备案期限(止)

	@Column(name = "M6", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M6;//安全负责人姓名           M6    safedutyername

	@Column(name = "M7", nullable = true,columnDefinition="varchar(50)")//长度改50
	@Setter
	@Getter
	private String M7;//安全负责人手机              M7    linkmode

	@Column(name="inindustrialpark", nullable=true, columnDefinition="char(1)")//新增
	@Setter
	@Getter
	private String inindustrialpark;//是否在化工园区内(0否;1是)

	@Column(name="parkid", nullable=true, columnDefinition="char(32)")//新增
	@Setter
	@Getter
	private String parkid;//园区标识（由省应急管理厅下发）

	@Column(name = "industrialparkname", nullable = true,columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String industrialparkname;//所属化工园区名称
	
	@Column(name="createby", nullable=true, columnDefinition="varchar(50)")//新增
	@Setter
	@Getter
	private String createby;//创建人

	@Column(name="updateby", nullable=true, columnDefinition="varchar(50)")//新增
	@Setter
	@Getter
	private String updateby;//最后修改人

	@Column(name="watercode", nullable=true, columnDefinition="varchar(50)")//新增
	@Setter
	@Getter
	private String watercode;//流水号

	@Column(name="gatewaycode", nullable=true, columnDefinition="varchar(11)")
	@Setter
	@Getter
	private String gatewaycode;// gatewayCode 网关编码

	@Column(name = "M5", nullable = true,columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M5;//主要危险性（选）

	@Column(name = "M3", nullable = true,columnDefinition="int")
	@Setter
	@Getter	
	private Integer M3;//厂区人数
	
	@Column(name = "M4", nullable = true,columnDefinition="int")
	@Setter
	@Getter	
	private Integer M4;//是否有安全监控预警系统( 0/1  否/是  )

	@Column(name = "M8", nullable = true,columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M8;//安全监控措施
	
	@Column(name = "M9", nullable = true,columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M9;//重大危险源计算Q值
	@Column(name = "M9_1", nullable = true,columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M9_1;//增加暴露人数
	@Column(name = "M10", nullable = true,columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M10;//易导致事故类型
	
	@Column(name = "M11", nullable = true,columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M11;//照片

}
