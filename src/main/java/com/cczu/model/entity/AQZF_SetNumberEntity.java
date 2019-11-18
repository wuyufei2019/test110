package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 安全生产执法--设置文书编号起始值
 * @author jason
 * @date 2017年8月3日
 */
@Entity
@Table(name="aqzf_setnumber")
public class AQZF_SetNumberEntity  extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1048407704230218548L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1; //最后一次修改者id

	@Column(name = "M1", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M1; //检查方案编号
	
	@Column(name = "M2", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M2; //检查记录编号
	
	@Column(name = "M3", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M3; //处理措施决定书编号
	
	
	@Column(name = "M4", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M4; //责令限期整改指令书编号
	
	
	@Column(name = "M5", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M5; //整改复查意见书编号
	
	
	@Column(name = "M6", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M6; //立案审批编号
	
	@Column(name = "M7", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M7; //询问通知编号
	
	@Column(name = "M8", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M8; //行政处罚告知书编号
	
	@Column(name = "M9", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M9; //行政处罚听证告知书编号
	
	@Column(name = "M10", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M10; //案件处理呈批表编号
	
	@Column(name = "M11", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M11; //行政处罚决定书（单位）编号
	
	@Column(name = "M12", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M12; //行政处罚决定书（个人）编号
	@Column(name = "M13", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M13; //文书送达回执编号  （    ）安监回〔    〕 号
	@Column(name = "M14", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M14; //行政强制执行事先催告书编号  （   ）安监执行催告〔  〕    号
	@Column(name = "M15", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M15; //强制执行申请书（    ）安监强执〔    〕 号 

	@Column(name = "M16", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int M16; //结案审批表（    ）安监结〔    〕 号
}
