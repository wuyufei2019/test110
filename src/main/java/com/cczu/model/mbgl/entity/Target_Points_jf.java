package com.cczu.model.mbgl.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @description 积分设置表
 */
@Entity
@Table(name="target_points_jf")
public class Target_Points_jf implements Serializable{

	private static final long serialVersionUID = 1881349061240431560L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1; //最后一次修改者id
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID2; //企业id

	@Column(name = "M1", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M1; //隐患排查
	
	@Column(name = "M1_1", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M1_1; //隐患排查系数(百分比)
	
	@Column(name = "M2", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M2; //随手拍
	
	@Column(name = "M2_1", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M2_1; //随手拍系数
	
	@Column(name = "M3", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M3; //安全培训
	
	@Column(name = "M3_1", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M3_1; //安全培训系数
	
	@Column(name = "M4", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M4; //建言献策
	
	@Column(name = "M4_1", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M4_1; //建言献策系数
	
	@Column(name = "M5", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M5; //其他
	
	@Column(name = "M5_1", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M5_1; //其他系数
}
