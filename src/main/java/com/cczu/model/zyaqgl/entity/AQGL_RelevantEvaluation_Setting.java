package com.cczu.model.zyaqgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: AQGL_RelevantEvaluation_Setting
 * @Description: 安全管理-相关方评定内容设置
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqgl_relevantevaluation_setting")
public class AQGL_RelevantEvaluation_Setting extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//企业id
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M1;//内容
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M2;//备注介绍
	
}
