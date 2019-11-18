package com.cczu.model.mbgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: Target_SafetyPromiseAgreement
 * @Description: 目标管理_安全承诺书书
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="target_safetypromiseagreement")
public class Target_SafetyPromiseAgreement extends BaseEntity {
	

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -5327001961148464120L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//企业id
	
	@Column(name = "year", nullable = true,columnDefinition="varchar(8)")
	@Setter
	@Getter
	private String year;//年份
	
	@Column(name = "post", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String post;//岗位
	
	@Column(name = "pername", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String pername;//姓名
	
	@Column(name = "phone", nullable = true,columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String phone;//电话

	@Column(name = "url", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String url;//承诺书附件
}
