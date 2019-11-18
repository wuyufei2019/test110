package com.cczu.model.hjbh.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 环境保护-危险废物-入库记录
 * @author JZQ
 *
 */

@Entity
@Table(name="hjbh_instorage")
public class HJBH_InStorage extends BaseEntity{

	private static final long serialVersionUID = 4793560532817752303L;
	
	@Column(name = "id1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long id1;//废物ID
	
	@Column(name = "intime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp intime;//入库日期
	
	@Column(name = "resource", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String resource;//废物来源
	
	@Column(name = "amount", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String amount;//废物数量
	
	@Column(name = "containermaterial", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String containermaterial;//容器材质
	
	@Column(name = "containervolume", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String containervolume;//容器体积
	
	@Column(name = "containeramount", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	private Integer containeramount;//容器个数
	
	@Column(name = "location", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String location;//废物存放位置
	
	@Column(name = "ysoperator", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String ysoperator;//废物运送部门经办人
	
	@Column(name = "ccoperator", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String ccoperator;//废物贮存部门经办人
	
}
