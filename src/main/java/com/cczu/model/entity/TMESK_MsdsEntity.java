package com.cczu.model.entity;

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
 * 
 * @ClassName: TMESK_MsdsEntity 
 * @Description: 安全专家知识库_MSDS
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="tmesk_msds")
public class TMESK_MsdsEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public TMESK_MsdsEntity() {
	}
	
	public TMESK_MsdsEntity(Long id) {
		this.ID = id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//编号
	
	@Column(name = "S1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp S1;//创建时间
	
	@Column(name = "S2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp S2;//更新时间
	
	@Column(name = "S3", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int S3;//删除标识
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint" )
	@Setter
	@Getter
	public long ID1;//创建者

	@Column(name = "M1", nullable = true, columnDefinition="varchar(1000)" )
	@Setter
	@Getter
	private String M1;//化学品中文名

	@Column(name = "M2", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M2;//化学品英文名称

	@Column(name = "M3", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M3;//中文名称2

	@Column(name = "M4", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M4;//英文名称2
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M5;//技术说明书编号
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M6;//CASNo
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M7;//分子式
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M8;//分子量
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M9;//含量
	@Column(name = "M10", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M10;//健康危害
	@Column(name = "M11", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M11;//环境危害
	@Column(name = "M12", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M12;//燃爆危险
	@Column(name = "M13", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M13;//皮肤接触
	@Column(name = "M14", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M14;//眼睛接触
	@Column(name = "M15", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M15;//吸入
	@Column(name = "M16", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M16;//食入
	@Column(name = "M17", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M17;//危险特性
	@Column(name = "M18", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M18;//有害燃烧产物
	@Column(name = "M19", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M19;//灭火方法
	@Column(name = "M20", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M20;//应急处理
	@Column(name = "M21", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M21;//操作注意事项
	@Column(name = "M22", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M22;//储存注意事项
	@Column(name = "M23", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M23;//中国MAC
	@Column(name = "M24", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M24;//前苏联
	@Column(name = "M25", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M25;//TLVTN
	@Column(name = "M26", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M26;//TLVWN
	@Column(name = "M27", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M27;//工程控制
	@Column(name = "M28", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M28;//呼吸系统防护
	@Column(name = "M29", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M29;//眼睛防护
	@Column(name = "M30", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M30;//身体防护
	@Column(name = "M31", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M31;//手防护
	@Column(name = "M32", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M32;//其他防护
	@Column(name = "M33", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M33;//主要成分
	@Column(name = "M34", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M34;//外观与性状
	@Column(name = "M35", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M35;//熔点
	@Column(name = "M36", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M36;//沸点
	@Column(name = "M37", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M37;//相对密度
	@Column(name = "M38", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M38;//相对蒸气密度
	@Column(name = "M39", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M39;//饱和蒸气压
	@Column(name = "M40", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M40;//燃烧热
	@Column(name = "M41", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M41;//临界温度
	@Column(name = "M42", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M42;//临界压力
	@Column(name = "M43", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M43;//水分配系数的对数值
	@Column(name = "M44", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M44;//闪点
	@Column(name = "M45", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M45;//引燃温度
	@Column(name = "M46", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M46;//爆炸上限
	@Column(name = "M47", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M47;//爆炸下限
	@Column(name = "M48", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M48;//主要用途
	@Column(name = "M49", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M49;//禁配物
	@Column(name = "M50", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M50;//急性毒性LD50
	@Column(name = "M51", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M51;//急性毒性LC50
	@Column(name = "M52", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M52;//其它有害作用
	@Column(name = "M53", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M53;//废弃物性质废弃处置方法
	@Column(name = "M54", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M54;//危险货物编号
	@Column(name = "M55", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M55;//UN编号
	@Column(name = "M56", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M56;//包装类别
	@Column(name = "M57", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M57;//包装方法
	@Column(name = "M58", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M58;//运输注意事项
	@Column(name = "M59", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter 
	private String M59;//法规信息

}
