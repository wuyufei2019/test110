package com.cczu.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * @ClassName: YHPC_DailyCheckEntity
 * @Description: 隐患排查---日常检查类型管理
 * @author 
 * @date 2018年06月25日
 *
 */
@Entity
@Table(name = "yhpc_rcjclxgl")
public class YHPC_RcjcLxglEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2478997794224276724L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
   
	@Column(name = "type", nullable = true,columnDefinition="varchar(2)")
	@Setter
	@Getter
	private String type;// 类型分类（1：检查类型，2：缺失类型）
   
	@Column(name = "m1", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M1;// 类型名称
   
}
