package com.cczu.model.zyaqgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: AQGL_RelevantEvaluation_content
 * @Description: 安全管理-相关方评定详细内容
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqgl_relevantevaluation_content")
public class AQGL_RelevantEvaluation_content extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//评定计划id
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID2;//评定人员id
	
	@Column(name = "M11", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Integer M11;//总评
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String M12;//评定等级
}
