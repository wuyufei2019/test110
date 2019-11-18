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
 * @ClassName: AQJD_BeiAnEntity
 * @Description: 安全监督管理_企业行政备案表
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqjd_beian")
public class AQJD_BeiAnEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -3377886031555894677L;
	
	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	public long ID1;//编号

	@Column(name = "M1", nullable = true, length = 50)
	@Setter
	@Getter
	private String M1;//备案名称

	@Column(name = "M2", nullable = true)
	@Setter
	@Getter
	private Date M2;//备案时间

	@Column(name = "M3", nullable = true)
	@Setter
	@Getter	
	private Date M3;//有效期至

	@Column(name = "M4", nullable = true, length = 50)
	@Setter
	@Getter	
	private String M4;//备案部门
	
	@Column(name = "M5", nullable = true, length = 4000)
	@Setter
	@Getter	
	private String M5;//备注
	
	@Column(name = "M6", nullable = true, length = 2000)
	@Setter
	@Getter	
	private String M6;//上传文件

	public AQJD_BeiAnEntity(long ID1, String M1, Date M2, Date M3, String M4,
			String M5, String M6) {
		this.ID1 = ID1;
		this.M1 = M1;
		this.M2 = M2;
		this.M3 = M3;
		this.M4 = M4;
		this.M5 = M5;
		this.M6 = M6;
	}

	public AQJD_BeiAnEntity() {
	}
}
