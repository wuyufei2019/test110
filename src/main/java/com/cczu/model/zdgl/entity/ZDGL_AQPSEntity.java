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
 * 安全评审
 * @author ZPC
 */
@Entity
@Table(name = "zdgl_aqps")
public class ZDGL_AQPSEntity implements Serializable {

	private static final long serialVersionUID = -6725623596120483005L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private Long ID1;//企业id

	@Column(name = "M1", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String M1;//选择制度
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M2;//评审主题
	
	@Column(name = "M3", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M3;//评审日期
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M4;//地点
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String M5;//主持人
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String M6;//评审依据
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String M7;//评审范围
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String M8;//参评人员
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(MAX)")
	@Getter
	@Setter
	private String M9;//评审纪要
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String M10;//评审结论
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String M11;//总经理决策
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String M12;//审核人id
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(1)")
	@Getter
	@Setter
	private String M13;//审核人意见(0：不同意1：同意)
	
	@Column(name = "M14", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M14;//审核日期
	
	@Column(name = "M15", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String M15;//批准人id
	
	@Column(name = "M16", nullable = true, columnDefinition="varchar(1)")
	@Getter
	@Setter
	private String M16;//批准人意见(0：不同意1：同意)
	
	@Column(name = "M17", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M17;//批准日期
	
	@Column(name = "M18", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String M18;//1:管理制度2:操作规程
	
	@Column(name = "M19", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M19;//创建日期
	
	@Column(name = "M20", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String M20;//userid
}
