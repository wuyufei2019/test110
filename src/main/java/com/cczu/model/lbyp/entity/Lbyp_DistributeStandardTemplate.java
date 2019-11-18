package com.cczu.model.lbyp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: Lbyp_DistributeStandard
 * @Description: 劳保用品发放标准信息1
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name = "lbyp_distributestandardtemplate")
public class Lbyp_DistributeStandardTemplate implements Serializable {
	/**
	* @Fields serialVersionUID
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "jobname", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String jobname;//工种岗位名称

	@Column(name = "goodstype", nullable = true,columnDefinition="varchar(4)")
	@Setter
	@Getter
	private String goodstype;//物品类型 1、一般劳动防护用品  2、特种防护劳动物品  3、其他
	
	@Column(name = "goodsname", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String goodsname;//物品名称

	@Column(name = "cyclemonth", nullable = true,columnDefinition="varchar(10)")
	@Setter
	@Getter
	private String cyclemonth;// 发放周期月
	
}
