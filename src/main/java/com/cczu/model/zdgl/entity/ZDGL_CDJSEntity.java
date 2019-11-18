package com.cczu.model.zdgl.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 文件传递与接收
 */
@Entity
@Table(name="zdgl_cdjs")
public class ZDGL_CDJSEntity implements Serializable {
	
	private static final long serialVersionUID = -6725623596120483005L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//文件id
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String M1;//用户id
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(2)")
	@Getter
	@Setter
	private String M2;//是否查看：0未查看，1已查看
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(2)")
	@Getter
	@Setter
	private String M3;//是否下载：0未下载，1已下载
}
