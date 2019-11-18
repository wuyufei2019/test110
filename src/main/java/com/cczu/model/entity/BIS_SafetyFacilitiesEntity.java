package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 安全设施
 * @author jason
 * @date 2017年5月25日
 */
@Entity
@Table(name="bis_safetyfacilities")
public class BIS_SafetyFacilitiesEntity extends BaseEntity {
	
	private static final long serialVersionUID = -7594207348492143231L;

	@Column(name = "ID1", nullable = true)
	@Setter
	@Getter
	private long ID1;//企业编号

	@Column(name = "M1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M1;//大类别

	@Column(name = "M2", nullable = true , columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M2;//小类别

	@Column(name = "M3", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M3;//涉及工艺设施

	@Column(name = "M4", nullable = true )
	@Setter
	@Getter	
	private Integer M4;//数量
	
	@Column(name = "M5", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M5;//检测时间
	
	@Column(name = "M6", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M6;//到期时间
	
	@Column(name = "M7", nullable = true )
	@Setter
	@Getter	
	private Integer M7;//状态
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M8;//备注

	@Column(name = "M9", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M9;//安全设施名称
}
