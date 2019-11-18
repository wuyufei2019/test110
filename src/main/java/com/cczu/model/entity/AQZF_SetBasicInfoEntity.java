package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: AQZF_SetBasicInfoEntity
 * @Description: 行政处罚-自定义设置-基本信息
 * @author who
 * @date 2017年7月29日
 *
 */
@Entity
@Table(name="xzcf_basicinfo")
public class AQZF_SetBasicInfoEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1; //最后一次修改者id
	
	@Column(name = "bankname", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String bankname;//银行名称
	
	@Column(name = "account", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter	
	private String account;//账户
	
	@Column(name = "address", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String address;//安全生产监督管理部门地址
	
	@Column(name = "ybcode", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	private String ybcode;//邮编
	
	@Column(name = "gov", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String gov;//人民政府
	
	@Column(name = "highgov", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String highgov;//人民政府
	
	@Column(name = "court", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String court;//人民法院
	
	@Column(name = "contact", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String contact;//联系人
	
	@Column(name = "phone", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String phone;//联系电话
	
	@Column(name = "ssqmc", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String ssqmc;//省市区名称
	
	@Column(name = "ssqjc", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String ssqjc;//省市区简称
}
