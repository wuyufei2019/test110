package com.cczu.model.zdgl.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 法律法规识别
 * @author ZPC
 */
@Entity
@Table(name = "zdgl_flfgsb")
public class ZDGL_FLFGSBEntity implements Serializable {

	private static final long serialVersionUID = -6725623596120483005L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//法律法规库id
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M1;//适用条款
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String M2;//适用部门（id集合）
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String M3;//备注

	@Column(name = "M4", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String M4;//识别人id
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String M5;//识别部门id
	
	@Column(name = "M6", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M6;//识别日期
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String M7;//审核人id
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(1)")
	@Getter
	@Setter
	private String M8;//审核人意见(0：不同意1：同意)
	
	@Column(name = "M9", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M9;//审核日期
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String M10;//批准人id
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(1)")
	@Getter
	@Setter
	private String M11;//批准人意见(0：不同意1：同意)
	
	@Column(name = "M12", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M12;//批准日期
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(255)")
	@Getter
	@Setter
	private String M13;//Gap差异
}
