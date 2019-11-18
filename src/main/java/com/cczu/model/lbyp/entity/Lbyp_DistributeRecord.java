package com.cczu.model.lbyp.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * @ClassName: Lbyp_DistributeRecord
 * @Description: 劳保用品发放记录信息
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name = "lbyp_distributerecord")
public class Lbyp_DistributeRecord extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = true, length = 8,columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID1;// 员工id
	
	@Column(name = "goodsname", nullable = true, length = 8,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String goodsname;// 物品名称
	
	@Column(name = "ID3", nullable = true, length = 8,columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID3;// 临时申请记录id（如果为空，则为正常发放记录）
	
	@Column(name = "amount", nullable = true,columnDefinition="int")
	@Setter
	@Getter
	private Integer amount;//物品数量 （防止标准修改，物品数量也记录下来）

	@Column(name = "time", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp time;// 领取日期
	
	@Column(name = "receiveperson", nullable = true, length=50)
	@Setter
	@Getter
	private String receiveperson;// 领取人（默认自己）

}
