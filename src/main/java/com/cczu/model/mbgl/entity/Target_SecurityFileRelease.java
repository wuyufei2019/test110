package com.cczu.model.mbgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: Target_SecurityFileRelease
 * @Description: 目标管理-安全动态
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="target_securityfilerelease")
public class Target_SecurityFileRelease extends BaseEntity {
	

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -5327001961148464120L;

	@Column(name = "ID2", nullable = false, length = 8)
	@Setter
	@Getter
	public long ID2;//企业id
	
	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	public long ID1;//发布人

	@Column(name = "M1", nullable = true, length = 100)
	@Setter
	@Getter
	private String M1;//文件名称

	@Column(name = "M2", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M2;//信息内容

	@Column(name = "M3", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M3;//文件路径
	
	@Column(name = "M4", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M4;//备注
	
	@Column(name = "M5", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M5;//文件类型
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M6;//附件(pdf)
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter	
	private String M7;//附件(swf)

	@Column(name = "viewcount", nullable = true, columnDefinition="int")
	@Setter
	@Getter	
	private int viewcount;//浏览次数

}
