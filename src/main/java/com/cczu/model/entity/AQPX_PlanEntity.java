package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: AQPX_PlanEntity
 * @Description: 企业-安全培训-培训计划
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqpx_plan")
public class AQPX_PlanEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6698205443083499613L;
	
	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ID1;//企业ID
	
	@Column(name = "ID2", nullable = false, length = 50)
	@Setter
	@Getter
	private String ID2;//课程ID集合
	
	@Column(name = "ID3", nullable = false, length = 50)
	@Setter
	@Getter
	private String ID3;//部门ID集合

	@Column(name = "ID4", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID4;//操作者
	
	@Column(name = "M1", nullable = true, length = 100)
	@Setter
	@Getter
	private String M1;//计划名称
	
	@Column(name = "M2", nullable = true, length = 20)
	@Setter
	@Getter
	private String M2;//培训类别
	
	@Column(name = "M3", nullable = true, length = 20)
	@Setter
	@Getter
	private String M3;//培训学时
	
	@Column(name = "M4", nullable = true)
	@Setter
	@Getter
	private Timestamp M4;//生成时间
	
	@Column(name = "M5", nullable = true)
	@Setter
	@Getter
	private Timestamp M5;//时段起
	
	@Column(name = "M6", nullable = true)
	@Setter
	@Getter
	private Timestamp M6;//时段止
	
	@Column(name = "M7", nullable = true, length = 200)
	@Setter
	@Getter
	private String M7;//备注

	@Column(name = "M8", nullable = true, length = 2)
	@Setter
	@Getter
	private String M8;//1：已加入消息
	
}
