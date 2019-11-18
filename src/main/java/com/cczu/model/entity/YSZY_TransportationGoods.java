package com.cczu.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 
 * @ClassName: YSZY_TransportationGoods
 * @Description: 运输作业-货物
 * @author wbth
 * @date 2018年8月29日
 *
 */
@Entity
@Table(name="yszy_transoods")
public class YSZY_TransportationGoods{

	private static final long serialVersionUID = 3146428973588126644L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号

	@Column(name = "transid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long transId;//运单id

	@Column(name = "whpname", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String whpName;//危化品名称

	@Column(name = "tonnage", nullable = true)
	@Setter
	@Getter
	public Float tonnage;//数量  吨/立方

	@Column(name = "type", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String type;//  类/项别

	@Column(name = "packageType", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String packageType;//包装类别

	@Column(name = "specification", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String specification;//规格
	
	@Column(name = "uniteNationNum", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String uniteNationNum;//联合国编码

	@Column(name = "hazardouswastename", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String hazardouswastename;//危废名称
	
	@Column(name = "hazardouswastetype", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String hazardouswastetype;//危废类别
	
	@Column(name = "hazardouswastenum", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String hazardouswastenum;//危废代码

	@Column(name = "transfernumber", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String transferNumber;//转移联单编号
}
