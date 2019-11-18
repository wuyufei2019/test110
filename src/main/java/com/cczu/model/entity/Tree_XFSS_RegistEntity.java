package com.cczu.model.entity;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: Tree_XFSS_RegistEntity
 * @Description: 消防设施_登记 tree
 * @author jason
 * @date 2017年8月24日
 *
 */
public class Tree_XFSS_RegistEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Setter
	@Getter
	private long pid;//父ID
	
	@Setter
	@Getter	
	private String Text;//名称
	
	@Setter
	@Getter	
	private String M2;//规格型号
	
	@Setter
	@Getter	
	private String M3;//安装位置X
	
	@Setter
	@Getter	
	private String M4;//安装位置Y
	
	@Setter
	@Getter
	public Timestamp M5;//投用时间
	
	@Setter
	@Getter	
	private String M6;//换验周期    1:每月、2:每季、3:每半年
	
	@Setter
	@Getter	
	private String M7;//生产厂商
	
	@Setter
	@Getter	
	private String M8;//状态    1:有效、2:过期、3:报废
	
	@Setter
	@Getter	
	private String M9;//备注
	
	@Setter
	@Getter	
	private String M10;//二维码地址
	
	@Setter
	@Getter
	public Long ID1;//操作者
	
	@Setter
	@Getter
	public Long ID2;//登记人员
	
	@Setter
	@Getter
	public Long qyid;//企业id
	
	@Setter
	@Getter
	public Long pmtId;//平面图id
	
	@Setter
	@Getter
	public String xzqy;//行政区域
	
	@Setter
	@Getter
	private List<Tree_XFSS_RegistEntity> children;//子
}
