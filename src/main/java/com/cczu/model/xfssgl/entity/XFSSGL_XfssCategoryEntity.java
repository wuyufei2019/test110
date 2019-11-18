package com.cczu.model.xfssgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: XFSSGL_XfsbCategoryEntity
 * @Description: 消防设备类别
 * @author wbth
 * @date 2018年4月21日
 *
 */
@Entity
@Table(name="xfssgl_xfsscategory")
public class XFSSGL_XfssCategoryEntity extends BaseEntity{
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -1060713878751052920L;
	
	
	@Column(name = "name", nullable = false, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String name;//消防设备名称
	
}
