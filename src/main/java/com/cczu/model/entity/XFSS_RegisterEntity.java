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
 * @ClassName: XFSS_RegisterEntity
 * @Description: 消防设施_登记
 * @author jason
 * @date 2017年8月24日
 *
 */
@Entity
@Table(name="xfss_register")
public class XFSS_RegisterEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 4810260550985421308L;
	
	@Column(name = "PID",columnDefinition="bigint")
	@Setter
	@Getter
	private long pid;//父ID

	@Column(name = "M1", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M1;//名称
	
	@Column(name = "M2", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M2;//规格型号
	
	@Column(name = "M3", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M3;//安装位置X
	
	@Column(name = "M4", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M4;//安装位置Y
	
	@Column(name = "M5", nullable = false, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M5;//投用时间
	
	@Column(name = "M6", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M6;//换验周期    1:每月、2:每季、3:每半年
	
	@Column(name = "M7", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M7;//生产厂商
	
	@Column(name = "M8", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M8;//状态    1:有效、2:过期、3:报废
	
	@Column(name = "M9", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M9;//备注
	
	@Column(name = "M10", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M10;//二维码地址
	
	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	public Long ID1;//操作者
	
	@Column(name = "ID2", nullable = false, length = 8)
	@Setter
	@Getter
	public Long ID2;//登记人员
	
	@Column(name = "qyid", nullable = true, length = 8)
	@Setter
	@Getter
	public Long qyid;//企业id
	
	@Column(name = "PMT_ID", nullable = true, length = 8)
	@Setter
	@Getter
	public Long pmtId;//平面图id
	
	@Column(name = "xzqy", nullable = true, length = 20)
	@Setter
	@Getter
	public String xzqy;//行政区域

}
