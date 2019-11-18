package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: ERM_EmergencyResExpertEntity
 * @Description: 应急资源管理_应急专家
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="erm_emergencyresexpert")
public class ERM_EmergencyResExpertEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1889200879076596987L;

	@Column(name = "M1", nullable = true, length = 50)
	@Setter
	@Getter
	private String M1;//姓名
	
	@Column(name = "M2", nullable = true)
	@Setter
	@Getter
	private Integer M2;//性别   1男 0女
	
	@Column(name = "M3", nullable = true, length = 20)
	@Setter
	@Getter
	private String M3;//出生年月
	
	@Column(name = "M4", nullable = true, length = 40)
	@Setter
	@Getter
	private String M4;//身份证号码
	
	@Column(name = "M5", nullable = true, length = 50)
	@Setter
	@Getter
	private String M5;//政治面貌
	
	@Column(name = "M6", nullable = true, length = 100)
	@Setter
	@Getter
	private String M6;//地址
	
	@Column(name = "M7", nullable = true, length = 50)
	@Setter
	@Getter
	private String M7;//工作单位
	
	@Column(name = "M8", nullable = true, length = 50)
	@Setter
	@Getter
	private String M8;//毕业院校
	
	@Column(name = "M9", nullable = true, length = 50)
	@Setter
	@Getter
	private String M9;//最高学历
	
	@Column(name = "M10", nullable = true)
	@Setter
	@Getter
	private Integer M10;//工作年限
	
	@Column(name = "M11", nullable = true, length = 30)
	@Setter
	@Getter
	private String M11;//联系电话
	
	@Column(name = "M12", nullable = true, length = 30)
	@Setter
	@Getter
	private String M12;//手机
	
	@Column(name = "M13", nullable = true, length = 50)
	@Setter
	@Getter
	private String M13;//职务
	
	@Column(name = "M14", nullable = true, length = 50)
	@Setter
	@Getter
	private String M14;//职称
	
	@Column(name = "M15", nullable = true, length = 50)
	@Setter
	@Getter
	private String M15;//专业
	
	@Column(name = "M16", nullable = true, length = 200)
	@Setter
	@Getter
	private String M16;//应急专长
	
	@Column(name = "M17", nullable = true, length = 50)
	@Setter
	@Getter
	private String M17;//专家类别
	
	@Column(name = "M18", nullable = true, length = 50)
	@Setter
	@Getter
	private String M18;//电子邮件
	
	@Column(name = "M19", nullable = true, length = 100)
	@Setter
	@Getter
	private String M19;//应对事故类型(1对多)
	
	@Column(name = "M20", nullable = true, length =200)
	@Setter
	@Getter
	private String M20;//备注

	@Column(name = "M21", nullable = true, length = 50)
	@Setter
	@Getter
	private String M21;//经度	

	@Column(name = "M22", nullable = true, length = 50)
	@Setter
	@Getter
	private String M22;//纬度	

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
