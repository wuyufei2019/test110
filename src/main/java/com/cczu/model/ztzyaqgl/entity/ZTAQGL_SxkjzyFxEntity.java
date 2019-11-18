package com.cczu.model.ztzyaqgl.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @ClassName: AQGL_SxkjzyFxEntity
 * @Description: 安全管理-受限空间作业分析表
 * @author zpc
 * @date 2018年1月3日
 *
 */
@Entity
@Table(name="ztaqgl_sxkjzyfx")
public class ZTAQGL_SxkjzyFxEntity implements Serializable {
	
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
	public Long ID1;//受限空间作业id
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M1;//类型(有毒有害介质，可燃气，氧含量)

	@Column(name = "M2", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter	
	private String M2;//介质
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M3;//数值
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M4;//单位
	
	@Column(name = "M5", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M5;//时间
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M6;//部位
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M7;//分析人
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(max)")
	@Setter
	@Getter	
	private String M8;//现场照片
}
