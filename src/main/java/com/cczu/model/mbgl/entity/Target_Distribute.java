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
 * @ClassName: target_distribute
 * @Description: 目标管理：指标分配
 *
 */
@Entity
@Table(name="target_distribute")
public class Target_Distribute extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long ID1;//指标ID
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Getter
	@Setter
	private long ID2;//企业ID

	@Column(name = "ID3", nullable = true, columnDefinition="int")
	@Getter
	@Setter
	private Integer ID3;//责任部门
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String M1;//年份
	
	@Column(name="M3", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String M3;//级别
	
	@Column(name="M5", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M5;//批准日期
	
	@Column(name="M6", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String M6;//备注
	
	@Column(name="targetval", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String targetval;//指标值 

	@Column(name="M7", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String M7 ;//制定人
	
	@Column(name="M8", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String M8 ;//审核人
	
	@Column(name="M9", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String M9 ;//批准人
	
	@Column(name="M11", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String M11 ;//预算（万元）
	
	@Column(name="M12", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String M12 ;//责任人
	
	@Column(name="M13", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String M13;//完成时间
	
	@Column(name="url", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String url ;//附件地址
}
