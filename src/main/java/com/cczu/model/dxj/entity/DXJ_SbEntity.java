package com.cczu.model.dxj.entity;

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
 * 点巡检--设备信息
 * @author zpc
 * @date 2018年3月01日
 */
@Entity
@Table(name="dxj_sb")
public class DXJ_SbEntity implements Serializable{

	private static final long serialVersionUID = 6322130668360738922L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1; //企业ID


    @Column(name = "M1_id", nullable = true, columnDefinition="varchar(200)")
    @Setter
    @Getter
    private String M1_id;//设备id 特种设备：S+设备id S_123  生产设备：P+设备id P_123

	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//设备名称
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M2;//照片地址
	
	@Column(name = "bindcontent", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String bindcontent;//绑定二维码
	
	@Column(name = "area", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String  area;//rfid卡批次代码
 
	@Column(name = "rfid", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String rfid;//绑定rfid
}
