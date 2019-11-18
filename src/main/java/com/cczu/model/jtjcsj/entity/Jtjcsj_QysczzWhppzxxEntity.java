package com.cczu.model.jtjcsj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 静态基础数据-企业生产装置危化品配置信息
 * @author Administrator
 *
 */
@Entity
@Table(name="jtjcsj_qysczzwhppzxx")
public class Jtjcsj_QysczzWhppzxxEntity extends BaseEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	@Column(name="qyid", nullable=true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long qyid;//企业id
	
	@Column(name="proddevid", nullable=true, columnDefinition="varchar(32)")
	@Setter
	@Getter
	private String proddevid;// proddevId 生产装置标识        标识对应生产装置标识
	
	@Column(name="cascode", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String cascode;// casCode CAS号
	
	@Column(name="chemcname", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String chemcname;// chemCname 危化品中文名称
	
	@Column(name="chemename", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String chemename;// chemEname 危化品英文名称
	
	@Column(name="maxolqty", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String maxolqty;// maxOlQty  最大在线量
	
	
	
	
	
}
