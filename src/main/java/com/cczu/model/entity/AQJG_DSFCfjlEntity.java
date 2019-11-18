package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

@Entity
@Table(name = "aqjg_dsfpunish")
public class AQJG_DSFCfjlEntity extends BaseEntity{

	    private static final long serialVersionUID = 638144630697128267L;
	
		// 第三方id
		@Column(name = "M1", nullable = false, columnDefinition = "bigint")
		@Getter
		@Setter
		private Long M1;
	
		// 处罚类型
		@Column(name = "M2", nullable = true, columnDefinition = "int")
		@Getter
		@Setter
		private Integer M2;
	
		// 处罚内容
		@Column(name = "M3", nullable = false, columnDefinition = "varchar(200)")
		@Getter
		@Setter
		private String M3;
	
		// 备注
		@Column(name = "M5", nullable = true, columnDefinition = "varchar(200)")
		@Getter
		@Setter
		private String M5;
		
		// 保留
		@Column(name = "Dname", nullable = true, columnDefinition = "varchar(50)")
		@Getter
		@Setter
		private String Dname;
		
}
