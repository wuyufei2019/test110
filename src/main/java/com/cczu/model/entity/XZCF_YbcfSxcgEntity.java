package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: XZCF_YbcfTzgzEntity
 * @Description: 行政处罚-一般处罚-事先催告
 * @author who
 * @date 2017年8月22日
 *
 */
@Entity
@Table(name="xzcf_ybcfsxcg")
public class XZCF_YbcfSxcgEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(name = "qyname", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String qyname;//处罚单位或者个人名称

	@Column(name = "finedate", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp finedate;//罚款日期

	@Column(name = "xzjd", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String xzjd;//受处单位尚未履行的行政决定
	
	@Column(name = "fine", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String fine;//罚款大写
	
	@Column(name = "extrafine", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String extrafine;//额外罚款 大写
	
	@Column(name = "allfine", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String allfine;//罚款总额 大写
	
	@Column(name = "extraxzjd", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String extraxzjd;//立即履行的行政决定
	
	@Column(name = "number", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String number;//编号
	
	//立案审批表id
	@Column(name = "id1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long id1;
	
}
