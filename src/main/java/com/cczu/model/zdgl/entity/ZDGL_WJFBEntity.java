package com.cczu.model.zdgl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 安全文件发布
 * @author ZPC
 */
@Entity
@Table(name = "zdgl_wjfb")
public class ZDGL_WJFBEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//企业id
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M1;//文件名称
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M2;//文件编号
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String M3;//类型
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String M4;//性质
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(MAX)")
	@Getter
	@Setter
	private String M5;//文件内容
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M6;//附件
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String M7;//审核人id
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(1)")
	@Getter
	@Setter
	private String M8;//审核人意见(0：不同意1：同意)
	
	@Column(name = "M9", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M9;//审核日期
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String M10;//批准人id
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(1)")
	@Getter
	@Setter
	private String M11;//批准人意见(0：不同意1：同意)
	
	@Column(name = "M12", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M12;//批准日期
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String M13;//发送部门
	
	@Column(name = "userid", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private Long userid;//创建人员id
	
	@Column(name = "M14", nullable = true, columnDefinition="varchar(400)")
	@Getter
	@Setter
	private String M14;//附件(pdf)
	
	@Column(name = "M15", nullable = true, columnDefinition="varchar(400)")
	@Getter
	@Setter
	private String M15;//附件(swf)
}
