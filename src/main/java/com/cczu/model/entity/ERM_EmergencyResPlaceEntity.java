package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: ERM_EmergencyResPlaceEntity
 * @Description: 应急资源管理_避难场所
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="erm_emergencyresplace")
public class ERM_EmergencyResPlaceEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 8717163558425219003L;

	@Column(name = "M1", nullable = true, length = 20)
	@Setter
	@Getter
	private String M1;//场所名称

	@Column(name = "M2", nullable = true, length = 20)
	@Setter
	@Getter
	private String M2;//场所类型

	@Column(name = "M3", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M3;//详细地址

	@Column(name = "M4", nullable = true)
	@Setter
	@Getter	
	private Integer M4;//可容纳人数
	
	@Column(name = "M5", nullable = true, length = 30)
	@Setter
	@Getter	
	private String M5;//联系人
	
	@Column(name = "M6", nullable = true, length = 30)
	@Setter
	@Getter	
	private String M6;//联系人电话
	
	@Column(name = "M7", nullable = true, length = 30)
	@Setter
	@Getter	
	private String M7;//负责人
	
	@Column(name = "M8", nullable = true, length = 30)
	@Setter
	@Getter	
	private String M8;//负责人电话
	
	@Column(name = "M9", nullable = true, length = 1000)
	@Setter
	@Getter	
	private String M9;//功能描述
	
	@Column(name = "M10", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M10;//应对事故类型(1对多)
	
	@Column(name = "M11", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M11;//备注

	@Column(name = "M12", nullable = true, length = 50)
	@Setter
	@Getter
	private String M12;//经度	

	@Column(name = "M13", nullable = true, length = 50)
	@Setter
	@Getter
	private String M13;//纬度	
	
	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	public long ID1;//操作者
	
	
	@Column(name = "qyid", nullable = true, length = 8)
	@Setter
	@Getter
	public Long qyid;//企业id
	
	@Column(name = "xzqy", nullable = true, length = 20)
	@Setter
	@Getter
	public String xzqy;//行政区划
}
