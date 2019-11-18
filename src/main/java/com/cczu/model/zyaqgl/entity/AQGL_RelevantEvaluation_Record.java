package com.cczu.model.zyaqgl.entity;

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
 * 
 * @ClassName: AQGL_RelevantEvaluation_Record
 * @Description: 安全管理-相关方评定内容评定分数记录中间表
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqgl_relevantevaluation_record")
public class AQGL_RelevantEvaluation_Record implements Serializable {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//AQGL_RelevantEvaluation_content id
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID2;//AQGL_RelevantEvaluation_setting id
	
	@Column(name = "point", nullable = true, columnDefinition="int")
	@Setter
	@Getter	
	private Integer point;//得分
	
	public AQGL_RelevantEvaluation_Record() {
		super();
	}

	public AQGL_RelevantEvaluation_Record(Long ID,Long ID1,Long ID2,Integer point){
		this.ID = ID;
		this.ID1 = ID1;
		this.ID2 = ID2;
		this.point = point;
	}
	
	
}
