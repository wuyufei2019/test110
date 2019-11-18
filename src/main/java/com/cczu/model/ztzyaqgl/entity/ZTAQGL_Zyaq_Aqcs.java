package com.cczu.model.ztzyaqgl.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * @ClassName: AQGL_Zyaq_Aqcs
 * @Description: 安全管理-安全措施中间表
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="ztaqgl_zyaq_aqcs")
public class ZTAQGL_Zyaq_Aqcs implements Serializable{
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//安全作业id

	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID2;//编制人id
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M1;//措施内容
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M2;//安全作业类型（1:动火/2:受限空间/3:高处）
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M3;//确认人
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(max)")
	@Setter
	@Getter	
	private String M4;//现场照片	
}
