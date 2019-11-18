package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: XZCF_YbcfQzzxEntity
 * @Description: 行政处罚-一般处罚-强制执行
 * @author who
 * @date 2017年8月22日
 *
 */
@Entity
@Table(name="xzcf_ybcfqzzx")
public class XZCF_YbcfQzzxEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(name = "court", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String court;//法院名称
	@Column(name = "number", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String number;//编号

	@Column(name = "clname", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String clname;//材料名称
	@Column(name = "dsname", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String dsname;//当事人名称
	
	@Column(name = "qfr", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter	
	private String qfr;//签发人
	
	//立案审批表id
	@Column(name = "id1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long id1;
	
}
