package com.cczu.model.lbyp.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * @ClassName: Lbyp_ApplyRecord
 * @Description: 劳保用品申请临时发放记录信息
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name = "lbyp_applyrecord")
public class Lbyp_ApplyRecord extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = true, length = 8,columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID1;// 企业编号

	@Column(name = "ID2", nullable = true, length = 8,columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID2;// 申请人id
	
	@Column(name = "ID3", nullable = true, length = 8,columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID3;// 审批人id
	
	@Column(name = "ID4", nullable = true, length = 8,columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID4;//申请人部门
	
	@Column(name = "reason", nullable = true, length = 200)
	@Setter
	@Getter
	private String reason;// 申请原因
	
	@Column(name = "opinion", nullable = true, length = 200)
	@Setter
	@Getter
	private String opinion;// 领导审批意见
	
	@Column(name = "result", nullable = true, length = 4)
	@Setter
	@Getter
	private String result;// 领导审批结果 1：通过 0：未通过
	
	@Column(name = "url", nullable = true, length = 200)
	@Setter
	@Getter
	private String url;// 上传图片地址
	
	@Column(name = "reviewtime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp reviewtime;//审核时间
}
