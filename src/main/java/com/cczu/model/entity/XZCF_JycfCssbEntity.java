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
 * @ClassName: XZCF_JYCFCssbEntity
 * @Description: 行政处罚-简易处罚-陈述申辩
 * @author who
 * @date 2017年7月29日
 *
 */
@Entity
@Table(name="xzcf_jycfcssb")
public class XZCF_JycfCssbEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	

	@Column(name = "id1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long id1;//案件，告知书的id
	
	@Column(name = "name", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String name;//陈述申辩人

	@Column(name = "startdate", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp startdate;//开始时间
	
	@Column(name = "enddate", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp enddate;//结束时间

	@Column(name = "address", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String address;//地点
	
	@Column(name = "sex", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String sex;//性别
	
	@Column(name = "duty", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String duty;//职务
	
	@Column(name = "workunit", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String workunit;//工作单位
	
	@Column(name = "phone", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String phone;//电话
	
	@Column(name = "contactaddress", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String contactaddress;//联系地址
	
	@Column(name = "ybcode", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter	
	private String ybcode;//邮编
	
	@Column(name = "director", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String director;//承办人
	
	@Column(name = "recorder", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String recorder;//记录人
	
	@Column(name = "enforcer1", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String enforcer1;//执法人员一
	
	@Column(name = "identity1", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String identity1;//执法人员一身份证
	
	@Column(name = "enforcer2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String enforcer2;//执法人员二
	
	@Column(name = "identity2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String identity2;//执法人员二身份证
	
	@Column(name = "cssbrecord", nullable = true, columnDefinition="varchar(5000)")
	@Setter
	@Getter	
	private String cssbrecord;//陈述记录

}
