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
 * @ClassName: Sbgl_StsglEntity
 * @Description: 设备管理-三同时管理
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="sbgl_stsglentity")
public class Sbgl_StsglEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -3929461343932061100L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号

	@Column(name = "projectname", nullable = true, length = 30)
	@Setter
	@Getter
	private String projectname;//项目名称

	@Column(name = "projectproduce", nullable = true, length = 500)
	@Setter
	@Getter
	private String projectproduce;//项目简介

	@Column(name = "approvetime", nullable = true ,columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp approvetime;//批注日期

	@Column(name = "approvedept", nullable = true, length = 30)
	@Setter
	@Getter	
	private String approvedept;//批准部门
	
	@Column(name = "constructiontime", nullable = true ,columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp constructiontime;//建设日期
	
	@Column(name = "finishtime", nullable = true ,columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp finishtime;//完成日期
	

}
