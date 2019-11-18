package com.cczu.model.jtjcsj.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: 
 * @Description: 静态基础数据-设备信息
 *
 */
@Entity
@Table(name="jtjcsj_sbxx")
public class Jtjcsj_SbxxEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4550189612589284400L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号
	
	@Column(name="equipcode", nullable=true, columnDefinition="varchar(19)")
	@Setter
	@Getter
	private String equipcode;// equipCode 设备编码
	
	@Column(name="hazardcode", nullable=true, columnDefinition="varchar(14)")
	@Setter
	@Getter
	private String hazardcode;// hazardCode 危险源编码
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(100)" )   //改为必填项    长度改为varchar(100)
	@Setter
	@Getter	
	private String M3;//设备名称          M3 equipName
	
	@Column(name = "equipdescribe", nullable = true, columnDefinition="varchar(400)" ) 
	@Setter
	@Getter	
	private String equipdescribe;// equipDescribe 设备描述 
	
	@Column(name = "equiptype", nullable = true, columnDefinition="varchar(6)")
	@Getter
	@Setter
	private String equiptype;// equipType 设备类型           G0:罐;Q0气体检测仪; S0 生产装置C0：仓库
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String M9;//M9 介质       equipMedium 设备介质
	
	@Column(name = "equipstatus", nullable = true, columnDefinition="char(1)")
	@Getter
	@Setter
	private String equipstatus;// equipStatus 设备运行状态         0停用，1在用
	
	@Column(name = "parkid", nullable = true, columnDefinition="char(32)")
	@Getter
	@Setter
	private String parkid;// parkId 所属园区标识
	
	@Column(name = "districtcode", nullable = true, columnDefinition="char(32)")
	@Getter
	@Setter
	private String districtcode;// districtCode  所属行政区划标识
	
	@Column(name = "installloc", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String installloc;//  installLoc  安装位置
	
	@Column(name = "longitude", nullable = true, columnDefinition="numeric(18,10)")
	@Getter
	@Setter
	private Float longitude;//经度
	
	@Column(name = "latitude", nullable = true, columnDefinition="numeric(18,10)")
	@Getter
	@Setter
	private Float latitude;//纬度
	
	@Column(name = "status", nullable = true, columnDefinition="char(1)")
	@Getter
	@Setter
	private String status;//删除标记        0未删除，1已删除
	
	@Column(name = "createdate", nullable = true, columnDefinition="char(14)")
	@Getter
	@Setter
	private String createdate;// createDate 创建时间
	
	@Column(name = "createby", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String createby;//  createBy  创建人  
	
	@Column(name = "updatedate", nullable = true, columnDefinition="char(14)")
	@Getter
	@Setter
	private String updatedate;// updateDate 最后修改时间
	
	@Column(name = "updateby", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String updateby;// updateBy 最后修改人
	
	
}
