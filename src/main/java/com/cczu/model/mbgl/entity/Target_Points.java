package com.cczu.model.mbgl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;
/**
 * 
 * @description 目标管理：积分记录表
 * @author jason
 * @date 2018年1月18日
 */
@Entity
@Table(name="target_points")
public class Target_Points extends BaseEntity {

	private static final long serialVersionUID = 1881349061240431560L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Getter
	@Setter
	private long ID1;//企业ID
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Getter
	@Setter
	private long ID2;//用户ID
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M1;//类别（隐患排查1、随手拍2、安全培训3、建言献策4、其他5）
	
	@Column(name = "M2", nullable = true, columnDefinition="int")
	@Getter
	@Setter
	private int M2;//积分值
	
	@Column(name="M3", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String M3;//获得的积分说明
	
	@Column(name="M4", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M4;//获得的时间
	
	public Target_Points(){}
	 
	public Target_Points(long ID1,long ID2,String M1,String M3){
		this.ID1=ID1;
		this.ID2=ID2;
		this.M1=M1;
		this.M3=M3;
	}
}
