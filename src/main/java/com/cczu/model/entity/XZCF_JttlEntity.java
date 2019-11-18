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
 * @Description: 行政处罚-集体讨论
 * @author who
 * @date 2017年7月29日
 * 
 */
@Entity
@Table(name = "xzcf_jttl")
public class XZCF_JttlEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = false, columnDefinition = "bigint")
	@Setter
	@Getter
	private long ID1;// （立案审批id）
	
	@Column(name = "M1", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String M1;// 编号
	
	@Column(name = "M2", nullable = true, columnDefinition = "varchar(500)")
	@Setter
	@Getter
	private String M2;// 案件名称
	
	@Column(name = "M3", nullable = true, columnDefinition = "datetime")
	@Setter
	@Getter
	private Timestamp M3;// 讨论开始时间
	
	@Column(name = "M4", nullable = true, columnDefinition = "datetime")
	@Setter
	@Getter
	private Timestamp M4;// 讨论结束时间
	
	@Column(name = "M5", nullable = true, columnDefinition = "varchar(200)")
	@Setter
	@Getter
	private String M5;// 地点
	
	@Column(name = "M6", nullable = true, columnDefinition = "varchar(100)")
	@Setter
	@Getter
	private String M6;// 主持人
	
	@Column(name = "M7", nullable = true, columnDefinition = "varchar(200)")
	@Setter
	@Getter
	private String M7;// 汇报人
	
	@Column(name = "M8", nullable = true, columnDefinition = "varchar(100)")
	@Setter
	@Getter
	private String M8;// 记录人
	
	@Column(name = "M9", nullable = true, columnDefinition = "varchar(2000)")
	@Setter
	@Getter
	private String M9;// 出席人员及职务
	
	@Column(name = "M10", nullable = true, columnDefinition = "varchar(2000)")
	@Setter
	@Getter
	private String M10;// 讨论内容
	
	@Column(name = "M11", nullable = true, columnDefinition = "varchar(2000)")
	@Setter
	@Getter
	private String M11;// 讨论记录
	
	@Column(name = "M12", nullable = true, columnDefinition = "varchar(2000)")
	@Setter
	@Getter
	private String M12;// 结论性意见
	
	@Column(name = "M13", nullable = true, columnDefinition = "varchar(500)")
	@Setter
	@Getter
	private String M13;// 出席人员
}
