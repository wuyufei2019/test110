package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: BIS_UserAnJianJuEntity
 * @Description: 企业基本信息-企业-厂区表
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="bis_enterprisefactory")
public class BIS_EnterpriseFactoryEntity  extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -3258156011183284975L;

	@Column(name = "M1", nullable = false, length = 50)
	@Setter
	@Getter
	private String M1;//厂区名称

	@Column(name = "M2", nullable = false, length = 50)
	@Setter
	@Getter
	private String M2;//经度
	
	@Column(name = "M3", nullable = false, length = 50)
	@Setter
	@Getter
	private String M3;//纬度
	
	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	public long ID1;//企业ID
}
