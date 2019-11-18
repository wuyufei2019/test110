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
 * @ClassName: TMESK_LawsEntity
 * @Description: 安全专家知识库_法律法规
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="tmesk_laws")
public class TMESK_LawsEntity implements Serializable{

	private static final long serialVersionUID = 8148808109374289889L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, length = 8)
	@Setter
	@Getter
	public long ID;//编号
	
	@Column(name = "S1", nullable = true )
	@Setter
	@Getter
	public Timestamp S1;//创建时间
	
	@Column(name = "S2", nullable = true )
	@Setter
	@Getter
	public Timestamp S2;//更新时间
	
	@Column(name = "S3", nullable = true )
	@Setter
	@Getter
	private int S3;//删除标识
	
	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	public long ID1;//创建者

	@Column(name = "M1", nullable = true, length = 50)
	@Setter
	@Getter
	private String M1;//类别

	@Column(name = "M2", nullable = true, length = 500)
	@Setter
	@Getter
	private String M2;//标题

	@Column(name = "M3", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M3;//正文

	@Column(name = "M4", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M4;//备注
	
	@Column(name = "M5", nullable = true, length = 1000)
	@Setter
	@Getter	
	private String M5;//附件(word)
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M6;//附件(pdf)
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M7;//附件(swf)
	
	@Column(name = "M8", nullable = true, length = 1000)
	@Setter
	@Getter	
	private String M8;//附件
	
	@Column(name = "qrcode", nullable = true, length = 500)
	@Setter
	@Getter	
	private String qrcode;//二维码
}
