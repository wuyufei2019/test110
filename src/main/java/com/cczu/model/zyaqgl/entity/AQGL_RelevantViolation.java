package com.cczu.model.zyaqgl.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: AQGL_RelevantViolation
 * @Description: 安全管理-相关方违规
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqgl_relevanteviolation")
public class AQGL_RelevantViolation extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//企业id
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID2;//相关方单位id
	
	@Column(name = "pdry", nullable = false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String pdry;//考核人

	@Column(name = "type", nullable = false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String type;//分类
	
	@Column(name = "M1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M1;//违规日期
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M2;//考评内容
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M3;//相关责任人
	
	@Column(name = "M4", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Integer M4;//扣分分值

	@Column(name = "M5", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M5;//考核金额

	@Column(name = "M6", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	public String M6;//备注
}
