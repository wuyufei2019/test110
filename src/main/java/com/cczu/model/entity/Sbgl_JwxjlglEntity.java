package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: Sbgl_JwxjlglEntity
 * @Description: 设备管理-检维修记录管理
 * @author xj
 * @date 2018年8月14日
 *
 */
@Entity
@Table(name="sbgl_jwxjlglentity")
public class Sbgl_JwxjlglEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -8131788956707946472L;
	
	@Column(name = "qyid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long qyid;//企业id
	
	@Column(name = "sbname", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String sbname;//设备名称
	
	@Column(name = "sbxh", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String sbxh;//型号规格
	
	@Column(name = "sbbh", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String sbbh;//设备编号
	
	@Column(name = "m1", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String m1;//使用部门
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String m2;//故障现象
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m3;//分析人
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String m4;//维修风险分析
	
	@Column(name = "m5", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m5;//方案制定人
	
	@Column(name = "m6", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String m6;//检维修方案
	
	@Column(name = "m7", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m7;//措施制定人
	
	@Column(name = "m8", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String m8;//采取和落实 预防和控制措施
	
	@Column(name = "m9", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m9;//培训人
	
	@Column(name = "m10", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String m10;//培训内容
	
	@Column(name = "m11", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m11;//检修人（被培训人）
	
	@Column(name = "m12", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m12;//监护人（被培训人）
	
	@Column(name = "m13", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m13;//检维修人
	
	@Column(name = "m14", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String m14;//检维修记录
	
	@Column(name = "m15", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m15;//验收人
	
	@Column(name = "m16", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String m16;//验收记录
	
	@Column(name = "m17", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp m17;//检维修时间
	
}
