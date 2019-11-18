package com.cczu.model.zyaqgl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 安全管理-资质信息
 */
@Entity
@Table(name = "aqgl_zz")
public class AQGL_ZzEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6725623596120483005L;

	// 单位ID
	@Column(name = "ID1", nullable = true, columnDefinition="int")
	@Getter
	@Setter
	private Integer ID1;
	
	//资质证书名称
	@Column(name = "M1", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String M1;
	
	//级别
	@Column(name = "M2", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String M2;
	
	//许可内容
	@Column(name = "M3", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String M3;
	
	//许可有效期始
	@Column(name = "M4", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M4;
	
	//许可有效期止
	@Column(name = "M5", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M5;
	
	//上传附件
	@Column(name = "M6", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String M6;
}
