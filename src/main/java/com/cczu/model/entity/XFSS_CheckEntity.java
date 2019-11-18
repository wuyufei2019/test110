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
 * @ClassName: XFSS_CheckEntity
 * @Description: 消防设施_点检
 * @author jason
 * @date 2017年8月24日
 *
 */
@Entity
@Table(name="xfss_check")
public class XFSS_CheckEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -5535048978271111056L;
	
	@Column(name = "M1", nullable = false, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M1;//检查日期
	
	@Column(name = "M2", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M2;//检查内容
	
	@Column(name = "M3", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M3;//检查结论
	
	@Column(name = "M4", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M4;//备注
	
	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	public Long ID1;//操作者
	
	@Column(name = "ID2", nullable = false, length = 8)
	@Setter
	@Getter
	public Long ID2;//消防设施的ID
	
	@Column(name = "ID3", nullable = false, length = 8)
	@Setter
	@Getter
	public Long ID3;//检查人员
	
	@Column(name = "qyid", nullable = true, length = 8)
	@Setter
	@Getter
	public Long qyid;//企业id
	
	@Column(name = "xzqy", nullable = true, length = 20)
	@Setter
	@Getter
	public String xzqy;//行政区域
}
