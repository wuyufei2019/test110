package com.cczu.model.lbyp.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * @ClassName: Lbyp_PurchaseDetail
 * @Description: 劳保用品采购物品详细记录
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name = "lbyp_purchasedetail")
public class Lbyp_PurchaseDetail extends BaseEntity{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;// 物品 id
	
	@Column(name = "amount", nullable = true)
	@Setter
	@Getter
	private Integer amount;// 采购数量
	
	@Column(name = "price", nullable = true)
	@Setter
	@Getter
	private Float price;// 采购单价
	
	@Column(name = "buytime", nullable = true,columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp buytime;// 采购时间
	
	@Column(name = "note", nullable = true, length = 200)
	@Setter
	@Getter
	private String note;// 备注
	
}
