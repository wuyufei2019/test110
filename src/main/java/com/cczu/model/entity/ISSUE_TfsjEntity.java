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
 * @ClassName: ISSUE_TfsjEntity
 * @Description: 安全文件发布_突发事件管理
 * @author zpc
 * @date 2017年12月23日
 *
 */
@Entity
@Table(name="issue_tfsj")
public class ISSUE_TfsjEntity extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 2888187391651750192L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//发布人

	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//标题

	@Column(name = "M2", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M2;//描述内容
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M3;//发生地点
	
	@Column(name = "M4", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M4;//发生时间
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(2)")
	@Setter
	@Getter	
	private String M5;//状态（1:未处理2:处理中3:处理完成）
	
}
