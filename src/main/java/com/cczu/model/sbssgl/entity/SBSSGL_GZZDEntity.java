package com.cczu.model.sbssgl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-规章制度
 * @author 
 */
@Entity
@Table(name = "sbssgl_gzzd")
public class SBSSGL_GZZDEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//企业id

	@Column(name = "m1", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m1;//规章制度名称
	
	@Column(name = "scrid", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private Long scrid;//上传人id
	
	@Column(name = "m2", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp m2;//上传时间
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String m3;//附件url(word)
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m4;//颁布单位
	
	@Column(name = "m5", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m5;//文件编号
	
	@Column(name = "m6", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp m6;//发布日期
	
	@Column(name = "m7", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp m7;//实施日期
	
	@Column(name = "m8", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m8;//摘要
	
	@Column(name = "m9", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String m9;//附件url(pdf)
	
	@Column(name = "m10", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String m10;//附件url(swf)
	
	
}
