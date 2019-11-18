package com.cczu.model.zdgl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 法律法规库
 * @author ZPC
 */
@Entity
@Table(name = "zdgl_flfg")
public class ZDGL_FLFGEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//企业id

	@Column(name = "M1", nullable = true, columnDefinition="varchar(5)")
	@Getter
	@Setter
	private String M1;//类别
	
	@Column(name = "M1_1", nullable = true, columnDefinition="varchar(5)")
	@Getter
	@Setter
	private String M1_1;//类别（1:法律2:法规3:规章4:地方性法规5:政府文件6:标准规范7:其他8:国际公约）
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M2;//名称
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M3;//颁布部门
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M4;//文件编号
	
	@Column(name = "M5", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M5;//发布日期

	@Column(name = "M6", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M6;//实施日期
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(MAX)")
	@Getter
	@Setter
	private String M7;//摘要
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(400)")
	@Getter
	@Setter
	private String M8;//附件(word)
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(MAX)")
	@Getter
	@Setter
	private String M9;//备注
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String M10;//录入人员id
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(400)")
	@Getter
	@Setter
	private String M11;//附件(pdf)
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(400)")
	@Getter
	@Setter
	private String M12;//附件(swf)

}
