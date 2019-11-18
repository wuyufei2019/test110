package com.cczu.model.zdgl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 安全管理制度
 * @author ZPC
 */
@Entity
@Table(name = "zdgl_glzd")
public class ZDGL_GLZDEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//企业id
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M1;//制度名称
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M2;//制度编号
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M3;//版本号
	
	@Column(name = "M4", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M4;//发布日期
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(MAX)")
	@Getter
	@Setter
	private String M5;//制度正文
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M6;//附件
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String M7;//编辑部门（id集合）
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String M8;//适用部门（id集合）
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String M9;//审核人id
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(1)")
	@Getter
	@Setter
	private String M10;//审核人意见(0：不同意1：同意)
	
	@Column(name = "M11", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M11;//审核日期
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String M12;//批准人id
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(1)")
	@Getter
	@Setter
	private String M13;//批准人意见(0：不同意1：同意)
	
	@Column(name = "M14", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M14;//批准日期
	
	@Column(name = "userid", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private Long userid;//创建人员id
	
	@Column(name = "M15", nullable = true, columnDefinition="varchar(400)")
	@Getter
	@Setter
	private String M15;//附件(pdf)
	
	@Column(name = "M16", nullable = true, columnDefinition="varchar(400)")
	@Getter
	@Setter
	private String M16;//附件(swf)

	@Column(name = "zt", nullable = true, columnDefinition="varchar(5)")
	@Getter
	@Setter
	private String zt;//状态(-1.作废)
}
