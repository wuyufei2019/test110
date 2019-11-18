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
 * @ClassName: XZCF_YbcfJaspEntity
 * @Description: 行政处罚-一般处罚-结案审批
 * @author who
 * @date 2017年7月29日
 *
 */
@Entity
@Table(name="xzcf_ybcfjasp")
public class XZCF_YbcfJaspEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	@Column(name = "id1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long id1;//立案审批id
	
	@Column(name = "number", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String number;//编号
	
	@Column(name = "casename", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String casename;//案件名称、
	
	@Column(name = "punishname", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String punishname;//受处个人或者单位名称
	
	@Column(name = "percomflag", nullable = true, columnDefinition="varchar(2)")
	@Setter
	@Getter
	private String percomflag;//企业、个人标志位 0：个人 1：企业
	
	@Column(name = "result", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String result;//处理结果
	
	@Column(name = "exeucondition", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String exeucondition;//执行情况
	
	//------------------------受处人为单位------------------------------
	@Column(name = "qyaddress", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String qyaddress;//企业地址 
	
	@Column(name = "legal", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String legal;//法人
	
	@Column(name = "duty", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String duty;//受处单位法人或者个人职务    企业对应法人职务   个人对应个人职务
	
	@Column(name = "qyyb", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String qyyb;//企业邮编
	
	//------------------------受处人为个人------------------------------
	@Column(name = "sex", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String sex;//性别
	
	@Column(name = "age", nullable = true, columnDefinition="int")
	@Setter
	@Getter	
	private Integer age;//性别
	
	@Column(name = "dwname", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String dwname;//所在单位名称
	
	@Column(name = "mobile", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String mobile;//联系电话
	
	@Column(name = "address", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String address;//家庭地址
	
	@Column(name = "dwaddress", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String dwaddress;//单位地址 
	
	@Column(name = "jtyb", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String jtyb;//企业邮编
	
	@Column(name = "cbr1", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String cbr1;// 承办人
	
	@Column(name = "cbr2", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String cbr2;// 承办人
	
	@Column(name = "cbsj", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp cbsj;//承办时间
	
	@Column(name = "shyj", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String shyj;// 审核意见
	
	@Column(name = "shr", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String shr;// 审核人
	
	@Column(name = "shsj", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp shsj;//审核时间
	
	@Column(name = "spyj", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String spyj;// 审批意见
	
	@Column(name = "spr", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String spr;// 审批人
	
	@Column(name = "spsj", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp spsj;//审批时间
	
}
