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
 * @ClassName: AQGL_RelevantEvaluation
 * @Description: 安全管理-相关方评定
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqgl_relevantevaluation")
public class AQGL_RelevantEvaluation extends BaseEntity {
	
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
	
	@Column(name = "pdryids", nullable = false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String pdryids;//评定人员id
	
	@Column(name = "M1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M1;//评定日期
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M2;//合作项目清单
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M3;//评定主持人
	
	@Column(name = "M4", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Integer M4;//评定结论(平均分)
	
	@Column(name = "M5", nullable = true,  columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String M5;//评定等级
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String M6;//审核意见

	@Column(name = "M7", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M7;//审核人
	
	@Column(name = "M8", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M8;//审核日期
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String M9;//批准意见

	@Column(name = "M10", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M10;//批准人
	
	@Column(name = "M11", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M11;//批准日期
	
	@Column(name = "M12", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public Integer M12;//评定状态
}
