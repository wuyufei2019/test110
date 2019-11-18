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
 * @ClassName: XWAQ_ObservationsEntity
 * @Description: 行为安全_观察记录
 * @author jason
 * @date 2017年8月24日
 *
 */
@Entity
@Table(name="xwaq_observations")
public class XWAQ_ObservationsEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -5835658019400728914L;

	@Column(name = "M1", nullable = true, length = 8)
	@Setter
	@Getter	
	private long M1;//有无不安全行为    有:1/无:0
	
	@Column(name = "M2", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M2;//伤害     A:轻伤/B:重伤/C:死亡/D:其他事故
	
	@Column(name = "M3", nullable = true, length = 8)
	@Setter
	@Getter	
	private Long M3;//数量
	
	@Column(name = "M4", nullable = false, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M4;//观察时间
	
	@Column(name = "M5", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M5;//备注
	
	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	public long ID1;//操作者
	
	@Column(name = "ID2", nullable = false, length = 8)
	@Setter
	@Getter
	public Long ID2;//行为id
	
	@Column(name = "ID3", nullable = false, length = 8)
	@Setter
	@Getter
	public Long ID3;//部门
	
	@Column(name = "ID4", nullable = true, length = 8)
	@Setter
	@Getter
	public Long ID4;//员工
	
	@Column(name = "qyid", nullable = true, length = 8)
	@Setter
	@Getter
	public Long qyid;//企业id
	
	@Column(name = "xzqy", nullable = true, length = 20)
	@Setter
	@Getter
	public String xzqy;//行政区划
}
