package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;



/**
 * 企业基本信息-现场供气
 *
 */
@Entity
@Table(name="bis_fieldsupply")
public class BIS_FieldSupplyEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6426619461084785157L;

	@Column(name = "id1", nullable = true, length = 8)
	@Setter
	@Getter
	private Long id1;//企业编号
	
	@Column(name = "m1", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m1;//介质
	
	@Column(name = "m2", nullable = true)
	@Setter
	@Getter	
	private Float m2;//容积
	
	@Column(name = "m3", nullable = true)
	@Setter
	@Getter	
	private Float m3;//用量
	
	@Column(name = "m4", nullable = true)
	@Getter
	@Setter
	private Integer m4;//气站性质
	
	@Column(name = "m5", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m5;//供气单位
	
	@Column(name = "m6_1", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String m6_1;//安评单位
	
	@Column(name = "m7_1", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m7_1;//备案编号
	
	@Column(name = "m8_1", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp m8_1;//备案日期
	
	@Column(name = "m6_2", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String m6_2;//安评单位
	
	@Column(name = "m7_2", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m7_2;//备案编号
	
	@Column(name = "m8_2", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp m8_2;//备案日期
	
	@Column(name = "m6_3", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String m6_3;//安评单位
	
	@Column(name = "m7_3", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m7_3;//备案编号
	
	@Column(name = "m8_3", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp m8_3;//备案日期
	
	@Column(name = "m9", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String m9;//备注

}
