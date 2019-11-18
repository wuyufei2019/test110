package com.cczu.model.lbyp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * @ClassName: Lbyp_Goods
 * @Description: 劳保用品物品信息
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name = "lbyp_goods")
public class Lbyp_Goods extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	private long ID1;// 企业id
	
	@Column(name = "ID2", nullable = true, length = 8)
	@Setter
	@Getter
	private Long ID2;// 仓库id

	@Column(name = "name", nullable = true, length = 100)
	@Setter
	@Getter
	private String name;// 物品名称

	@Column(name = "number", nullable = true, length = 50)
	@Setter
	@Getter
	private String number;// 物品编号

	@Column(name = "unit", nullable = true, length = 20)
	@Setter
	@Getter
	private String unit;// 计量单位

	@Column(name = "specifications", nullable = true,length = 20)
	@Setter
	@Getter
	private String specifications;// 规格

	@Column(name = "storagerate", nullable = true, length = 50)
	@Setter
	@Getter
	private Integer storagerate;// 库存量
	
}
