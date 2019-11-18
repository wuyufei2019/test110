package com.cczu.sys.system.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 部门 entity
 * @author jason
 */
@Entity
@Table(name = "t_department")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@DynamicUpdate @DynamicInsert
public class Department implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long fid;//父id
	private Long id;//ID
	private String m1;//部门名称
	private Integer m2;//排序
	private String m3;//备注
	private String m4;//权限标识(1.本级 2.下级 3.全部)
	private String flag;//所属类别（和用户表userType 保持相同）
	private long id1;//操作者
	private long id2;//企业ID
	private String id3;//安监行政区域
	private String code;//编码
	private String pcode;//父网格编码
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "M1",columnDefinition="varchar(200)")
	public String getM1() {
		return m1;
	}

	public void setM1(String m1) {
		this.m1 = m1;
	}

	@Column(name = "M2",columnDefinition="int")
	public Integer getM2() {
		return m2;
	}

	public void setM2(Integer m2) {
		this.m2 = m2;
	}

	@Column(name = "M3",columnDefinition="varchar(200)")
	public String getM3() {
		return m3;
	}

	public void setM3(String m3) {
		this.m3 = m3;
	}

	@Column(name = "FLAG",columnDefinition="varchar(200)")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "ID1",columnDefinition="bigint")
	public long getId1() {
		return id1;
	}

	public void setId1(long id1) {
		this.id1 = id1;
	}

	@Column(name = "ID2",columnDefinition="bigint")
	public long getId2() {
		return id2;
	}

	public void setId2(long id2) {
		this.id2 = id2;
	}

	@Column(name = "ID3",columnDefinition="varchar(200)")
	public String getId3() {
		return id3;
	}

	public void setId3(String id3) {
		this.id3 = id3;
	}

	@Column(name = "FID", nullable = true, length = 8)
	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	@Column(name = "CODE",columnDefinition="varchar(200)")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "PCODE",columnDefinition="varchar(200)")
	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	@Column(name = "M4",columnDefinition="varchar(10)")
	public String getM4() {
		return m4;
	}

	public void setM4(String m4) {
		this.m4 = m4;
	}	
	// Constructors

	/** default constructor */
	public Department() {
	}

	/** minimal constructor */
	public Department(String m1) {
		this.m1 = m1;
	}
	
	public Department(Long id) {
		this.id=id;
	}
	
	public Department (Long id,String m1){
		this.id=id;
		this.m1=m1;
	}
	
	public Department (String m1,Integer m2){
		this.m1=m1;
		this.m2=m2;
	}

	public Department(String m1, Integer m2,
			String m3) {
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
	}
	
	public Department(Long id,Long fid,String m1, Integer m2,
			String m3,String m4,String flag,long id1,long id2,String id3,String code,String pcode) {
		this.id = id;
		this.fid = fid;
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
		this.m4 = m4;
		this.flag=flag;
		this.id1=id1;
		this.id2=id2;
		this.id3=id3;
		this.code=code;
		this.pcode=pcode;
	}
	
}