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
 * @ClassName: XZCF_YBCFLaspEntity
 * @Description: 行政处罚-一般处罚-立案审批
 * @author who
 * @date 2017年7月29日
 * 
 */
@Entity
@Table(name = "xzcf_ybcflasp")
public class XZCF_YbcfLaspEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "id1", nullable = true, columnDefinition = "bigint")
	@Setter
	@Getter
	private long id1;// （后续可能需要，存入企业id）
	
	@Column(name = "ID2", nullable = true, columnDefinition = "bigint")
	@Setter
	@Getter
	private long ID2;// （检查记录id，如果直接添加立案则为0）

	@Column(name = "ayname", nullable = true, columnDefinition = "varchar(500)")
	@Setter
	@Getter
	private String ayname;// 案由

	@Column(name = "casesource", nullable = true, columnDefinition = "varchar(500)")
	@Setter
	@Getter
	private String casesource;// 案件来源

	@Column(name = "filldate", nullable = true, columnDefinition = "datetime")
	@Setter
	@Getter
	private Timestamp filldate;// 立案日期

	@Column(name = "casename", nullable = true, columnDefinition = "varchar(200)")
	@Setter
	@Getter
	private String casename;// 案件名称

	@Column(name = "dsperson", nullable = true, columnDefinition = "varchar(100)")
	@Setter
	@Getter
	private String dsperson;// （为了导出方便）当事人（企业名称）

	@Column(name = "contact", nullable = true, columnDefinition = "varchar(20)")
	@Setter
	@Getter
	private String contact;// 电话

	@Column(name = "legalperson", nullable = true, columnDefinition = "varchar(20)")
	@Setter
	@Getter
	private String legalperson;// 法定负责人

	@Column(name = "dsaddress", nullable = true, columnDefinition = "varchar(200)")
	@Setter
	@Getter
	private String dsaddress;// 当事人地址

	@Column(name = "ybcode", nullable = true, columnDefinition = "varchar(20)")
	@Setter
	@Getter
	private String ybcode;// 邮编

	@Column(name = "casecondition", nullable = true, columnDefinition = "varchar(2000)")
	@Setter
	@Getter
	private String casecondition;// 案件基本情况

	@Column(name = "opinion", nullable = true, columnDefinition = "varchar(2000)")
	@Setter
	@Getter
	private String opinion;// 承办人意见

	@Column(name = "enforcer1", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String enforcer1;// 承办人员一

	@Column(name = "identity1", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String identity1;// 承办人一证件号

	@Column(name = "enforcer2", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String enforcer2;// 承办人二

	@Column(name = "identity2", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String identity2;// 承办人二证件号
	
	@Column(name = "cbsj", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp cbsj;//承办时间
	
	@Column(name = "fzshyj", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String fzshyj;// 法制审核意见
	
	@Column(name = "fzshr", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String fzshr;// 法制审核人
	
	@Column(name = "fzshsj", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp fzshsj;//法制审核时间

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
	
	@Column(name = "url", nullable = true, columnDefinition = "varchar(500)")
	@Setter
	@Getter
	private String url;// 基本情况的附件地址

	@Column(name = "number", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String number;// 立案编号
	
	@Column(name = "sdhznumber", nullable = true, columnDefinition = "varchar(50)")
	@Setter
	@Getter
	private String sdhznumber;// 回执编号
	
	
    
	// 标志位
	@Column(name = "xwflag", nullable = true, columnDefinition = "varchar(2)")
	@Setter
	@Getter
	private String xwflag;// 是否已经填写询问书 标志 1：已填写，0未填写
	
	@Column(name = "dcflag", nullable = true, columnDefinition = "varchar(2)")
	@Setter
	@Getter
	private String dcflag;// 是否已经填写调查报告书 标志 1：已填写，0未填写
	
	@Column(name = "gzflag", nullable = true, columnDefinition = "varchar(2)")
	@Setter
	@Getter
	private String gzflag;// 是否已经填写告知书 标志 1：已填写，0未填写
	
	@Column(name = "tzflag", nullable = true, columnDefinition = "varchar(2)")
	@Setter
	@Getter
	private String tzflag;// 是否已经填写听证书 标志 1：已填写，0未填写

	@Column(name = "cbflag", nullable = true, columnDefinition = "varchar(2)")
	@Setter
	@Getter
	private String cbflag;// 是否已经填写案件呈报书 标志 1：已填写，0未填写
	
	@Column(name = "cfjdflag", nullable = true, columnDefinition = "varchar(2)")
	@Setter
	@Getter
	private String cfjdflag;// 是否已经填写处罚决定书 标志 1：已填写，0未填写
	
	@Column(name = "jaflag", nullable = true, columnDefinition = "varchar(2)")
	@Setter
	@Getter
	private String jaflag;// 是否已经填写结案审批标志 1：已填写，0未填写
	
	
	
	
	
	
	
	@Column(name = "cgflag", nullable = true, columnDefinition = "varchar(2)")
	@Setter
	@Getter
	private String cgflag;// 是否已经填写事先催告书 标志 1：已填写，0未填写
	
	@Column(name = "qzflag", nullable = true, columnDefinition = "varchar(2)")
	@Setter
	@Getter
	private String qzflag;// 是否已经填写强制执行标志 1：已填写，0未填写
	
	@Column(name = "tempflag", nullable = true, columnDefinition = "varchar(2)")
	@Setter
	@Getter
	private String tempflag;// 是否临时添加询问文书反向生产的立案审批标志，1是 0否 2信息已经补全
	
	@Column(name = "tlflag", nullable = true, columnDefinition = "varchar(2)")
	@Setter
	@Getter
	private String tlflag;// 是否已经集体讨论 标志 1：已填写，0未填写
	
	@Column(name = "sbflag", nullable = true, columnDefinition = "varchar(2)")
	@Setter
	@Getter
	private String sbflag;// 是否已经申辩笔录 标志 1：已填写，0未填写
	
	@Column(name = "userid", nullable = true, columnDefinition = "bigint")
	@Setter
	@Getter
	private long userid;// 创建人id
}
