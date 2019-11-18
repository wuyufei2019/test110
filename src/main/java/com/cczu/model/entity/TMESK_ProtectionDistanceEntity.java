package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: TMESK_ProtectionDistanceEntity
 * @Description: 初始化防护距离
 *
 */
@Entity
@Table(name="tmesk_protectiondistance")
public class TMESK_ProtectionDistanceEntity extends BaseEntity {

	private static final long serialVersionUID = 1037804142487393772L;

	@Column(name = "M1", nullable = true, length = 100)
	@Setter
	@Getter
	private String M1;//UN号 
	
	@Column(name = "M2", nullable = true, length = 100)
	@Setter
	@Getter
	private String M2;//中文名称

	@Column(name = "M3", nullable = true, length = 20)
	@Setter
	@Getter
	private String M3;//初始隔离距离(少量泄露)/米 

	@Column(name = "M4", nullable = true, length = 20 )
	@Setter
	@Getter	
	private String M4;//白天下风向防护距离(少量泄露)/米

	@Column(name = "M5", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M5;//夜晚下风向防护距离(少量泄露)/米
	
	@Column(name = "M6", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M6;//初始隔离距离(大量泄露)/米 
		
	@Column(name = "M7", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M7;//白天下风向防护距离(大量泄露)/米
	
	@Column(name = "M8", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M8;//夜晚下风向防护距离(大量泄露)/米

	@Column(name = "ID1", nullable = false,  columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//操作者
}
