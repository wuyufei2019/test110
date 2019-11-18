/**
 * @ClassName: ADMIN_DSFZzEntity
 * @Description: 第三方技术服务管理——资质
 * @author iDoctor
 * @date 2017年4月18日
 *
 */
package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "aqjg_dsfzz")
public class AQJG_DSFZzEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6725623596120483005L;

	// 第三方ID
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
