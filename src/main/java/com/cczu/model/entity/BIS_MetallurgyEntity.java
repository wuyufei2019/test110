package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @Description: 企业基本信息——冶金信息
 * @author jason
 * @date: 2017年12月27日
 */
@Entity
@Table(name="bis_metallurgy")
public class BIS_MetallurgyEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 962516529525072235L;

	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long ID1;//企业编号
	
	@Column(name = "ID2", nullable = true, length = 8)
	@Setter
	@Getter
	private Long ID2;//操作者id  
	
	

	@Column(name = "M1", nullable = true, length = 100)
	@Setter
	@Getter
	private String M1;//类别名称
	
	@Column(name = "M2", nullable = true, length = 100)
	@Setter
	@Getter
	private String M2;//主要产品
	
	@Column(name = "M3", nullable = true)
	@Setter
	@Getter
	private Integer M3;//高温金属液体载体是否为移动式
	
	@Column(name = "M4", nullable = true, length = 100)
	@Setter
	@Getter
	private String M4;//加热方式
	
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M5;//年产量
	
	@Column(name = "M6", nullable = true)
	@Setter
	@Getter
	private Integer M6;//现场人数
	
	@Column(name = "M7", nullable = true, length = 100)
	@Setter
	@Getter
	private String M7;//熔炼场所建筑物结构
	
	@Column(name = "M8", nullable = true, length = 100)
	@Setter
	@Getter
	private String M8;//金属液体转运方式
	
	@Column(name = "M9", nullable = true, length = 1000)
	@Setter
	@Getter
	private String M9;//备注
	
}
