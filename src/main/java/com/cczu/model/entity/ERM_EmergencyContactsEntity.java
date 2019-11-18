package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: ERM_EmergencyContactsEntity
 * @Description: 应急资源管理_通讯录
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="erm_emergencycontact")
public class ERM_EmergencyContactsEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -7942863682398738928L;

	@Column(name = "M1", nullable = true, length = 50)
	@Setter
	@Getter
	private String M1;//姓名

	@Column(name = "M2", nullable = true, length = 500)
	@Setter
	@Getter
	private String M2;//单位

	@Column(name = "M3", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M3;//职务

	@Column(name = "M4", nullable = true, length = 50)
	@Setter
	@Getter	
	private String M4;//电话
	
	@Column(name = "M5", nullable = true, length = 50)
	@Setter
	@Getter	
	private String M5;//手机
	
	@Column(name = "M6", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M6;//备注
	
	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	public long ID1;//操作者
	
	@Column(name = "qyid", nullable = true, length = 8)
	@Setter
	@Getter
	public Long qyid;//企业id
	
	@Column(name = "userid", nullable = true, length = 20)
	@Setter
	@Getter
	public Long userid;//安监用户id
}
