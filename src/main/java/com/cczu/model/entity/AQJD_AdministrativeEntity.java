package com.cczu.model.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: AQJD_AdministrativeEntity
 * @Description: 安全监督管理_企业行政许可
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqjd_administrative")
public class AQJD_AdministrativeEntity extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -6428335501620710360L;

	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	public long ID1;//企业编号

	@Column(name = "M1", nullable = true, length = 50)
	@Setter
	@Getter
	private String M1;//许可名称 （含新改扩建、安全生产许可证、危化品经营许可证、危化品登记、安全标准化等类别）

	@Column(name = "M2", nullable = true)
	@Setter
	@Getter
	private Date M2;//批准时间

	@Column(name = "M3", nullable = true)
	@Setter
	@Getter	
	private Date M3;//到期时间

	@Column(name = "M4", nullable = true, length = 50)
	@Setter
	@Getter	
	private String M4;//许可部门
	
	@Column(name = "M5", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M5;//许可内容  
	
	@Column(name = "M6", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M6;//备注

}
