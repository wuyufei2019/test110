package com.cczu.model.dxj.entity;

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
 * 点巡检---点巡检设备项目记录
 * @author zpc
 * @date 2018年3月09日
 */
@Entity
@Table(name="dxj_checkhiddeninfo")
public class DXJ_CheckHiddenInfoEntity implements Serializable {

	private static final long serialVersionUID = 4659242572434313161L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "checkresult_id", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  checkresult_id  ;//巡查记录ID 
	
	@Column(name = "checksbxm_id", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  checksbxm_id;//设备项目ID  
	
	@Column(name = "dangerphoto", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String dangerphoto;//隐患照片
	
	@Column(name = "dangerdesc", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String dangerdesc;//隐患备注
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long qyid;//企业ID 
	
	/***********预留字段*************/
	@Column(name = "dangerstatus", nullable = true, columnDefinition="varchar(1)")
	@Setter
	@Getter
	private String dangerstatus;//隐患状态(1未整改   2已处理 )
	
	@Column(name = "zgrid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  zgrid  ;//整改人ID 
}
