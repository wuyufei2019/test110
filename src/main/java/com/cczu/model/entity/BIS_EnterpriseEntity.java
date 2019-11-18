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
 * @ClassName: BIS_EnterpriseEntity
 * @Description: 企业基本信息-企业基本信息
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="bis_enterprise")
public class BIS_EnterpriseEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public BIS_EnterpriseEntity() {
	}

	public BIS_EnterpriseEntity(Long id) {
		this.ID=id;
	}
	
	public BIS_EnterpriseEntity(String m1, String m2, String m3, Timestamp m4,
			String m5, String m6, String m7, String m8, String m9, String m10,
			String m11, String m11_1, String m11_2, String m11_3,String m12, String m13,
			String m14, String m15, String m16, String m17, String m18,
			String m19, String m20, String m21, String m22, String m23,
			String m24, String m25, String m26, String m27, String m28,
			String m29, String m30, String m31, String m32, String m33,
			String m34, String m35, String m36, String m37, String m38,
			String m39, String m40, String m41, String m42, String m43,
			String m44,long id1) {
		M1 = m1;
		M2 = m2;
		M3 = m3;
		M4 = m4;
		M5 = m5;
		M6 = m6;
		M7 = m7;
		M8 = m8;
		M9 = m9;
		M10 = m10;
		M11 = m11;
		M11_1 = m11_1;
		M11_2 = m11_2;
		M11_3 = m11_3;
		M12 = m12;
		M13 = m13;
		M14 = m14;
		M15 = m15;
		M16 = m16;
		M17 = m17;
		M18 = m18;
		M19 = m19;
		M20 = m20;
		M21 = m21;
		M22 = m22;
		M23 = m23;
		M24 = m24;
		M25 = m25;
		M26 = m26;
		M27 = m27;
		M28 = m28;
		M29 = m29;
		M30 = m30;
		M31 = m31;
		M32 = m32;
		M33 = m33;
		M34 = m34;
		M35 = m35;
		M36 = m36;
		M37 = m37;
		M38 = m38;
		M39 = m39;
		M40 = m40;
		M41 = m41;
		M42 = m42;
		M43 = m43;
		M44 = m44;
		ID1 =id1;
	}
	
	public BIS_EnterpriseEntity(String m1, String m2, String m3, Timestamp m4,
			String m5, String m6, String m7, String m8, String m9, String m10,
			String m11, String m11_1, String m11_2, String m11_3,String m12, String m13,
			String m14, String m15, String m16, String m17, String m18,
			String m19, String m20, String m21, String m22, String m23,
			String m24, String m25, String m26, String m27, String m28,
			String m29, String m30, String m31, String m32, String m33,
			String m34, String m35, String m36, String m37, String m38,
			String m39, String m40, String m41, String m42, String m43,
			String m44,long id1,String id2) {
		M1 = m1;
		M2 = m2;
		M3 = m3;
		M4 = m4;
		M5 = m5;
		M6 = m6;
		M7 = m7;
		M8 = m8;
		M9 = m9;
		M10 = m10;
		M11 = m11;
		M11_1 = m11_1;
		M11_2 = m11_2;
		M11_3 = m11_3;
		M12 = m12;
		M13 = m13;
		M14 = m14;
		M15 = m15;
		M16 = m16;
		M17 = m17;
		M18 = m18;
		M19 = m19;
		M20 = m20;
		M21 = m21;
		M22 = m22;
		M23 = m23;
		M24 = m24;
		M25 = m25;
		M26 = m26;
		M27 = m27;
		M28 = m28;
		M29 = m29;
		M30 = m30;
		M31 = m31;
		M32 = m32;
		M33 = m33;
		M34 = m34;
		M35 = m35;
		M36 = m36;
		M37 = m37;
		M38 = m38;
		M39 = m39;
		M40 = m40;
		M41 = m41;
		M42 = m42;
		M43 = m43;
		M44 = m44;
		ID1 =id1;
		ID2 =id2;
	}
	
	@Column(name="M1_1", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1_1;//母公司名称
	
	@Column(name="M1_2", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1_2;//集团公司名称
	
	@Column(name="M2", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M2;//注册号
	
	@Column(name="M2_1", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M2_1;//营业执照类别
	
	@Column(name="M3", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M3;//组织机构代码
	
	@Column(name="M6_1", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M6_1;//联系人

	@Column(name="M6_2", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M6_2;//联系微信号
	
	@Column(name="M6_3", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M6_3;//联系QQ号
	
	@Column(name="M6", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M6;//联系电话
	
	@Column(name="M7", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M7;//电子邮件
	
	@Column(name="M8_1", nullable=true, columnDefinition="varchar(600)")
	@Setter
	@Getter
	private String M8_1;//注册资金(万元)
	
	@Column(name="M8_2", nullable=true, columnDefinition="varchar(600)")
	@Setter
	@Getter
	private String M8_2;//年销售收入(万元)
	
	@Column(name="M8_3", nullable=true, columnDefinition="varchar(600)")
	@Setter
	@Getter
	private String M8_3;//年利润(万元)
	
	@Column(name="M8_4", nullable=true, columnDefinition="varchar(600)")
	@Setter
	@Getter
	private String M8_4;//资产总额（万元）
	
	@Column(name="M8_5", nullable=true, columnDefinition="varchar(600)")
	@Setter
	@Getter
	private String M8_5;//占地面积
	
	@Column(name="M8_6", nullable=true, columnDefinition="varchar(600)")
	@Setter
	@Getter
	private String M8_6;//属地安监机构
	
	@Column(name="M11", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M11;//行政区域(省)
	
	@Column(name="M11_1", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M11_1;//行政区域(市)
	
	@Column(name="M11_2", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M11_2;//行政区域(区)
	
	@Column(name="M11_3", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M11_3;//行政区域(镇或街道)
	
	@Column(name="M12", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M12;//行业类别
	
	@Column(name="M13", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M13;//企业行政隶属关系
	
	@Column(name="M14", nullable=true, columnDefinition="varchar(2000)")//长度改为2000
	@Setter
	@Getter
	private String M14;//经营范围
	
	@Column(name="M22", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M22;//主要负责人电子邮箱
	
	@Column(name="M26", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M26;//安全负责人电子邮箱
	
	@Column(name="M28", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private String M28;//从业人员数量
	
	@Column(name="M29", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private String M29;//特种作业人员数量
	
	@Column(name="M30", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private String M30;//专职安全生产管理人员数
	
	@Column(name="M30_1", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private String M30_1;//兼职安全生产管理人员数量
	
	@Column(name="M31", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private String M31;//专职应急管理人员数
	
	@Column(name="M32", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private String M32;//注册安全工程师人员数
	
	@Column(name="M33_2", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M33_2;//单位传真
	
	@Column(name="M33_3", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M33_3;//企业平面图
	
	@Column(name="M33_4", nullable=true, columnDefinition="varchar(5000)")
	@Setter
	@Getter
	private String M33_4;//企业风险四色图（多文件）
	
	@Column(name="M34", nullable=true, columnDefinition="varchar(2)")
	@Setter
	@Getter
	private String M34;//规模情况
	
	@Column(name="M36", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M36;//监管分类（ 1工贸  2化工  ）
	
	@Column(name="M37", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M37;//隐患排查治理制度
	
	@Column(name="M38", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M38;//隐患排查治理计划
	
	@Column(name="M39", nullable=true, columnDefinition="varchar(2)")
	@Setter
	@Getter	
	private String M39;//是否构成重大危险源
	
	@Column(name="M40", nullable=true, columnDefinition="varchar(2)")
	@Setter
	@Getter
	private String M40;//重大危险源等级
	
	@Column(name="M41", nullable=true, columnDefinition="varchar(2)")
	@Setter
	@Getter	
	private String M41;//物料风险等级
	
	@Column(name="M42", nullable=true, columnDefinition="varchar(2)")
	@Setter
	@Getter	
	private String M42;//工艺风险等级
	
	@Column(name="M43", nullable=true, columnDefinition="varchar(2)")
	@Setter
	@Getter	
	private String M43;//物料储量风险等级	
	
	@Column(name="M44", nullable=true, columnDefinition="varchar(2)")
	@Setter
	@Getter
	private String M44;//企业风险等级
	
	@Column(name="M45", nullable=true, columnDefinition="varchar(max)")
	@Setter
	@Getter
	private String M45;//公司介绍
	
	@Column(name="M47", nullable=true, columnDefinition="varchar(2)")
	@Setter
	@Getter
	private String M47;//是否涉及重点监管危化品1:是，0：否
	
	@Column(name="M48", nullable=true, columnDefinition="varchar(2)")
	@Setter
	@Getter
	private String M48;//是否涉及高危工艺1:是，0：否
	
	@Column(name="M49", nullable=true, columnDefinition="varchar(2)")
	@Setter
	@Getter
	private String M49;//是否涉及剧毒品生产或使用1:是，0：否

	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//用户

	@Column(name = "ID2", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String ID2;//行政区划ID
	
	@Column(name = "fid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long fid;//父id
	
	@Column(name = "isbloc", nullable = true, columnDefinition="bit")
	@Setter
	@Getter
	public Integer isbloc;//是否为集团公司(1 是  0 否)
	
	@Column(name = "qyimg", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	public String qyimg;//企业照片 （首页用）

	@Column(name = "qylogo", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	public String qylogo;//企业logo
	
	@Column(name = "ktdate", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp ktdate;//开通日期（默认当前日期）
	
	@Column(name = "dqdate", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp dqdate;//到期日期（默认当前日期加一年的日期）
	
	@Column(name = "state", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String state;//状态（null 启用 0 停用）



	@Column(name = "companycode", nullable = true, columnDefinition="varchar(11)")//新增
	@Setter
	@Getter
	public String companycode;//企业编码，编码规范 市级行政区划编码（6位）+5位数字流水号，作为表唯一主键。

	@Column(name = "chemicalindustrycode", nullable = true, columnDefinition="varchar(6)")//新增
	@Setter
	@Getter
	public String chemicalindustrycode;//化工行业编码

	@Column(name = "nrcccode", nullable = true, columnDefinition="varchar(9)")//新增
	@Setter
	@Getter
	public String nrcccode;//化学品登记系统编码

	@Column(name = "parentcode", nullable = true, columnDefinition="varchar(11)")//新增
	@Setter
	@Getter
	public String parentcode;//父级企业编码

	@Column(name="M1", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//企业名称

	@Column(name = "companyshortname", nullable = true, columnDefinition="varchar(100)")//新增
	@Setter
	@Getter
	public String companyshortname;//企业简称
	
	@Column(name="areacode", nullable=true, columnDefinition="varchar(6)")
	@Setter
	@Getter
	private String areacode;//行政区域编码

	@Column(name="M8", nullable=true, columnDefinition="varchar(400)")
	@Setter
	@Getter
	private String M8;//工商注册地址（企业注册所在地地址）

	@Column(name="M33", nullable=true, columnDefinition="varchar(400)")
	@Setter
	@Getter
	private String M33;//生产场所地址

	@Column(name="M16", nullable=true, columnDefinition="varchar(50)")//表中的字段类型为NUMBER(9,6)
	@Setter
	@Getter
	private String M16;//企业位置经度

	@Column(name="M17", nullable=true, columnDefinition="varchar(50)")//表中的字段类型为NUMBER(9,6)
	@Setter
	@Getter
	private String M17;//企业位置纬度

	@Column(name="M5", nullable=true, columnDefinition="varchar(50)") //长度100变为50
	@Setter
	@Getter
	private String M5;//法定代表人

	@Column(name="M5_1", nullable=true, columnDefinition="varchar(50)") //新增
	@Setter
	@Getter
	private String M5_1;//法定代表人手机

	@Column(name="M19", nullable=true, columnDefinition="varchar(50)")//长度100变为50
	@Setter
	@Getter
	private String M19;//企业负责人

	@Column(name="M21", nullable=true, columnDefinition="varchar(20)")//长度100变为20
	@Setter
	@Getter
	private String M21;//企业负责人手机（短信发送使用	）

	@Column(name="M20", nullable=true, columnDefinition="varchar(20)")//长度100变为20
	@Setter
	@Getter
	private String M20;//企业负责人电话

	@Column(name="M23", nullable=true, columnDefinition="varchar(50)")//长度100变为50
	@Setter
	@Getter
	private String M23;//安全负责人

	@Column(name="M25", nullable=true, columnDefinition="varchar(20)")//长度100变为20
	@Setter
	@Getter
	private String M25;//安全负责人手机(必填，短信发送使用)

	@Column(name="M24", nullable=true, columnDefinition="varchar(20)")//长度100变为20
	@Setter
	@Getter
	private String M24;//安全负责人固定电话

	@Column(name="dutyphone", nullable=true, columnDefinition="varchar(20)")//新增
	@Setter
	@Getter
	private String dutyphone;//安全值班电话

	@Column(name="M9", nullable=true, columnDefinition="varchar(6)")//长度50变为6
	@Setter
	@Getter
	private String M9;//邮政编码

	@Column(name="M4", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M4;//成立日期

	@Column(name="M33_1", nullable=true, columnDefinition="varchar(100)")//长度1000变为100
	@Setter
	@Getter
	private String M33_1;//企业网址

	@Column(name="M35", nullable=true, columnDefinition="varchar(6)")//长度2变为6
	@Setter
	@Getter
	private String M35;//企业规模（见附录 1:大;2:中;3:小;4:微）

	@Column(name="companytype", nullable=true, columnDefinition="varchar(6)")//新增
	@Setter
	@Getter
	private String companytype;//企业类型（见附录 01:生产;02:经营;03:使用）

	@Column(name="M10", nullable=true, columnDefinition="varchar(6)")//长度50变为6
	@Setter
	@Getter
	private String M10;//经济类型（见附录 参照国标 GB/T12402-2000,保留两级）

	@Column(name="industrycategory", nullable=true, columnDefinition="varchar(6)")
	@Setter
	@Getter
	private String industrycategory;//所属行业门类（见附录 参照国标 GB/T 4754—2017）

	@Column(name="industryclass", nullable=true, columnDefinition="varchar(6)")
	@Setter
	@Getter
	private String industryclass;//所属行业大类（见附录 参照国标 GB/T 4754—2017）

	@Column(name="M3_1", nullable=true, columnDefinition="varchar(18)")//长度150变为18
	@Setter
	@Getter
	private String M3_1;//统一社会信用代码

	@Column(name="M36_1", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M36_1;//监管层级（见附录，字典码表：H11）

	@Column(name="M15", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M15;//企业状态（见附录，字典码表：E08）

	@Column(name="M18", nullable=true, columnDefinition="varchar(6)")//长度变为6
	@Setter
	@Getter
	private String M18;//安全生产标准化等级（安全生产标准化等级）

	@Column(name="safetylicenseno", nullable=true, columnDefinition="varchar(18)")//新增
	@Setter
	@Getter
	private String safetylicenseno;//安全生产许可证编号

	@Column(name="safetylicensestart", nullable=true, columnDefinition="datetime")//新增
	@Setter
	@Getter
	private Timestamp safetylicensestart;//安全生产许可证有效期开始日期

	@Column(name="safetylicenseend", nullable=true, columnDefinition="datetime")//新增
	@Setter
	@Getter
	private Timestamp safetylicenseend;//安全生产许可证有效期结束日期

	@Column(name="peopleemployee", nullable=true, columnDefinition="numeric(8)")//新增
	@Setter
	@Getter
	private Integer peopleemployee;//职工人数

	@Column(name="peopletoxic", nullable=true, columnDefinition="numeric(8)")//新增
	@Setter
	@Getter
	private Integer peopletoxic;//剧毒化学品作业人员人数

	@Column(name="peoplehazard", nullable=true, columnDefinition="numeric(8)")//新增
	@Setter
	@Getter
	private Integer peoplehazard;//危险化学品作业人员人数

	@Column(name="M27", nullable=true, columnDefinition="char(1)")//int改char
	@Setter
	@Getter
	private String M27;//是否设有安全机构 （0否;1是）

	@Column(name="M46", nullable=true, columnDefinition="char(1)")//长度改1，改char
	@Setter
	@Getter
	private String M46;//是否在化工园区内 （0否;1是）

	@Column(name="parkid", nullable=true, columnDefinition="varchar(32)")//新增
	@Setter
	@Getter
	private String parkid;//园区标识 

	@Column(name="M46_1", nullable=true, columnDefinition="varchar(200)")//长度改200
	@Setter
	@Getter
	private String M46_1;//所属化工园区名称

	@Column(name = "createby", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String createby;//创建者id

	@Column(name="updateby", nullable=true, columnDefinition="varchar(50)")//新增
	@Setter
	@Getter
	private String updateby;//最后修改人

    @Column(name="gatewaycode", nullable=true, columnDefinition="varchar(11)")//新增
    @Setter
    @Getter
    private String gatewaycode;//网关编码
}
