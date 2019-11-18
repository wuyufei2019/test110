package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: AQPX_TestguizeEntity
 * @Description: 企业-安全培训-试卷的出题规则
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqpx_testguize")
public class AQPX_TestguizeEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3050757269324926377L;

	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ID1;//企业ID
	
	@Column(name = "ID2", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ID2;//课程ID
	
	@Column(name = "M1", nullable = true, length = 50)
	@Setter
	@Getter
	private Integer M1;//单选
	
	@Column(name = "M2", nullable = true, length = 50)
	@Setter
	@Getter
	private Integer M2;//多选
	
	@Column(name = "M3", nullable = true, length = 50)
	@Setter
	@Getter
	private Integer M3;//填空
	
	@Column(name = "M4", nullable = true, length = 50)
	@Setter
	@Getter
	private Integer M4;//判断
	
	@Column(name = "M5", nullable = true, length = 50)
	@Setter
	@Getter
	private Integer M5;//单选分值
	
	@Column(name = "M6", nullable = true, length = 50)
	@Setter
	@Getter
	private Integer M6;//多选分值
	
	@Column(name = "M7", nullable = true, length = 50)
	@Setter
	@Getter
	private Integer M7;//填空分值
	
	@Column(name = "M8", nullable = true, length = 50)
	@Setter
	@Getter
	private Integer M8;//判断分值
	
	@Column(name = "M9", nullable = true, length = 50)
	@Setter
	@Getter
	private Integer M9;//合格分数线
}
