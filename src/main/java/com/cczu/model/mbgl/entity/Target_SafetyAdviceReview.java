package com.cczu.model.mbgl.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: Target_SafetyAdviceReview
 * @Description: 目标管理-安全文化-建言献策审核
 * @author jason
 * @date 2017年5月27日
 */
@Entity
@Table(name="target_safetyadvicereview")
public class Target_SafetyAdviceReview extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 2888187391651750192L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//合理化建议id
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID2;//用户id
	
	@Column(name = "name", nullable = true, columnDefinition="varchar(8)")
	@Setter
	@Getter
	public String name;//审核人姓名
	
	@Column(name = "result", nullable = false, columnDefinition="varchar(2)")
	@Setter
	@Getter
	private String result;//审核是否通过  1 通过 0 不通过.
	
	@Column(name = "opinion", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String opinion ;//审核人意见
	
	@Column(name = "integral", nullable = true, columnDefinition="int")
	@Setter
	@Getter	
	private int integral;//积分
	
	@Column(name = "time", nullable = false, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp time;//审核时间
	
}
