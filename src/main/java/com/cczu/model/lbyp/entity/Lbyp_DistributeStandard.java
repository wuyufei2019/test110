package com.cczu.model.lbyp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * @ClassName: Lbyp_DistributeStandard
 * @Description: 劳保用品发放标准信息1
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name = "lbyp_distributestandard")
public class Lbyp_DistributeStandard extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;// 企业编号

	@Column(name = "goodsname", nullable = true, length = 50)
	@Setter
	@Getter
	private String goodsname;// 物品名称
	
	@Column(name = "jobtype", nullable = true, length = 50)
	@Setter
	@Getter
	private String jobtype;// 工种名称

	@Column(name = "amount", nullable = true)
	@Setter
	@Getter
	private Integer amount;// 发放物品数量

	@Column(name = "cyclemonth", nullable = true)
	@Setter
	@Getter
	private Integer cyclemonth;// 发放周期月
	
}
