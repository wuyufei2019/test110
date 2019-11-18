package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;
/**
 * 
 * @ClassName: AQPX_ItembankEntity
 * @Description: 企业-安全培训-试题库
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqpx_itembank")
public class AQPX_ItembankEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	private String M1;//题目类型(1:单选,2:多选,3:填空,4:判断,)
	
	
	@Column(name = "M2", nullable = true, length = 500)
	@Setter
	@Getter
	private String M2;//题干
	
	@Column(name = "M3", nullable = true, length = 200)
	@Setter
	@Getter
	private String M3;//A
	
	@Column(name = "M4", nullable = true, length = 200)
	@Setter
	@Getter
	private String M4;//B
	
	@Column(name = "M5", nullable = true, length = 200)
	@Setter
	@Getter
	private String M5;//C
	
	@Column(name = "M6", nullable = true, length = 200)
	@Setter
	@Getter
	private String M6;//D
	
	@Column(name = "M7", nullable = true, length = 200)
	@Setter
	@Getter
	private String M7;//E
	
	@Column(name = "M8", nullable = true, length = 200)
	@Setter
	@Getter
	private String M8;//答案
	
	@Column(name = "M9", nullable = false, length = 8)
	@Setter
	@Getter
	private int M9;//试题分数

}
