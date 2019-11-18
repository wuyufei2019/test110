package com.cczu.model.lydw.entity;

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

/*
 * 蓝牙定位-设备管理-标签房间历史表实体类
 */
@Entity
@Table(name = "Pub_fileroomtimehis")
public class Pub_FileRoomTimehis implements Serializable {

	private static final long serialVersionUID = -2895729790841030699L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, columnDefinition="int")
	@Setter
	@Getter
	public int id;
	
	@Column(name = "[file]", nullable = false, columnDefinition="varchar(255)")
	@Setter
	@Getter
	public String file;//标签号
	
	@Column(name = "room", nullable = false, columnDefinition="int")
	@Setter
	@Getter
	public int room;//标签所在信标区域
	
	@Column(name = "intime", nullable = false, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp intime;//载入时间
	
	@Column(name = "uptime", nullable = false, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp uptime;//更新时间
	
	@Column(name = "RSSI", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int RSSI;//标签接收rssi值
	
	@Column(name = "tolong", nullable = true, columnDefinition="varchar(255)")
	@Setter
	@Getter
	public String tolong;//滞留时间

    @Column(name = "sourcename", nullable = true, columnDefinition="varchar(255)")
    @Setter
    @Getter
    public String sourcename;

    @Column(name = "formattype", nullable = true, columnDefinition="varchar(255)")
    @Setter
    @Getter
    public String formattype;//TP:上传 Tag 位置信息  TD:上传 Tag 自定义数据

    @Column(name = "tagidformat", nullable = true, columnDefinition="varchar(255)")
    @Setter
    @Getter
    public String tagidformat;

    @Column(name = "x", nullable = true, columnDefinition="float")
    @Setter
    @Getter
    public Double x;

    @Column(name = "y", nullable = true, columnDefinition="float")
    @Setter
    @Getter
    public Double y;

    @Column(name = "z", nullable = true, columnDefinition="float")
    @Setter
    @Getter
    public Double z;

    @Column(name = "battery", nullable = true, columnDefinition="varchar(255)")
    @Setter
    @Getter
    public String battery;

    @Column(name = "qualityindicator", nullable = true, columnDefinition="int")
    @Setter
    @Getter
    public Integer qualityindicator;

    @Column(name = "payload", nullable = true, columnDefinition="varchar(255)")
    @Setter
    @Getter
    public String payload;
	
	

}
