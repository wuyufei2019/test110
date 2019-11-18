package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 视频设备信息
 * @author jason
 * @date 2017年8月29日
 */
@Entity
@Table(name="bis_videoequipment")

public class Bis_VideoEquipmentEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8359876181232918926L;

	@Column(name = "videoid", nullable = true,columnDefinition="varchar(32)")
	@Setter
	@Getter
	private String videoid;// videoId  视频标识(32位UUID)

	@Column(name = "hazardcode", nullable = true,columnDefinition="varchar(12)")
	@Setter
	@Getter
	private String hazardcode;// 重大危险源标识

	@Column(name = "prodcellid", nullable = true,columnDefinition="varchar(32)")
	@Setter
	@Getter
	private String prodcellid;// 生产单元区域标识

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//企业ID

	@Column(name="m1", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M1;//摄像头编码
	
	@Column(name="m2", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M2;//摄像头类型
	
	@Column(name="m3", nullable=false, columnDefinition="varchar(50)" )
	@Setter
	@Getter
	private String M3;//设备名称  equipName

	@Column(name="m4", nullable=true, columnDefinition="varchar(50)" )
	@Setter
	@Getter
	private String M4;//设备编码  equipNo

	@Column(name="m5", nullable=true, columnDefinition="varchar(100)" )
	@Setter
	@Getter
	private String M5;//设备IP

	@Column(name="m6", nullable=true, columnDefinition="varchar(200)" )
	@Setter
	@Getter
	private String M6;//外观类型
	
	@Column(name="M7", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private String M7;//经度  longitude
	
	@Column(name="M8", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private String M8;//纬度  latitude

	@Column(name="M9", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M9;//布控区域 installLoc

	@Column(name="M10", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M10;//通道号

	@Column(name="M11", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M11;//端口号

	@Column(name="M12", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M12;//摄像头顺序号

	@Column(name="M13", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M13;//登录用户

	@Column(name="M14", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M14;//登录密码

	@Column(name="M15", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M15;//客户端接入服务器ID

	@Column(name="M16", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M16;//企业负责人

	@Column(name="M17", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M17;//负责人联系方式

	@Column(name="M18", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M18;//生产厂家

	@Column(name="M19", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M19;//主要技术参数

	@Column(name="M20", nullable=true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String M20;//备注

	@Column(name="rtsp", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String rtsp;//RTSP视频流

	@Column(name="apiaddress", nullable=false, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String apiaddress;//接口请求地址

	@Column(name="playaddress", nullable=false, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String playaddress;//视频播放地址

	@Column(name="url", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String url;//视频流url

	@Column(name="M21", nullable=true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	private String M21;//是否为重大危险源区域（0：否 1：是）

	@Column(name="M22", nullable = true, columnDefinition = "varchar(255)")
	@Setter
	@Getter
	private String M22;//定位X坐标

	@Column(name="M23", nullable = true, columnDefinition = "varchar(255)")
	@Setter
	@Getter
	private String M23;//定位Y坐标


	@Column(name="M24", nullable = true, columnDefinition = "varchar(255)")
	@Setter
	@Getter
	private String M24;//定位Z坐标
}
