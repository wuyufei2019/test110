package com.cczu.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 
 * @ClassName: YSZY_TransportationGoods
 * @Description: 运输作业-审核意见
 * @author wbth
 * @date 2018年8月29日
 *
 */
@Entity
@Table(name="yszy_reviewopinion")
public class YSZY_ReviewOpinion {

	private static final long serialVersionUID = 3146428973588126644L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号

	@Column(name = "transid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long transId;//运单id

	@Column(name = "userid", nullable = false, columnDefinition="int")
	@Setter
	@Getter
	public Integer userid;//审核人id

	@Column(name = "opinion", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String opinion;//审核意见



}
