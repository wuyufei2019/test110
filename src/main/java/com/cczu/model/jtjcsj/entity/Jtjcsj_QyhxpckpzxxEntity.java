package com.cczu.model.jtjcsj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 静态基础数据-企业化学品仓库配置信息
 * @author Administrator
 *
 */
@Entity
@Table(name="jtjcsj_qyhxpckpzxx")
public class Jtjcsj_QyhxpckpzxxEntity extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="qyid", nullable=true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long qyid;//企业id
	
	@Column(name="chmstorid", nullable=true, columnDefinition="char(32)")
	@Setter
	@Getter
	private String chmstorid;// chmstorId 化学品仓库标识
	
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
	private String chemename;//  chemEname  危化品英文名称
	
	@Column(name="olqty", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String olqty;// olQty 最大在线量
	
	@Column(name="unit", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String unit;//计量单位
	
	@Column(name="dangeart", nullable=true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String dangeart;// dangeArt 涉及危险工艺
	
	@Column(name="dangeartbm", nullable=true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String dangeartbm;// dangeArt 涉及危险工艺编码
	
	
	

}
