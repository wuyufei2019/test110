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
 * @ClassName: BIS_DirectorAssessEntity
 * @Description: 企业基本信息-安全总监年度考核
 * @author jason
 *
 */
@Entity
@Table(name="bis_directorassess")
public class BIS_DirectorAssessEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public BIS_DirectorAssessEntity() {
	}

	public BIS_DirectorAssessEntity(Long ID) {
		this.ID=ID;
	}
	
	public BIS_DirectorAssessEntity(String M1, String M2, String M3,String M6){
		this.M1 = M1;
		this.M2 = M2;
		this.M3 = M3;
		this.M6 = M6;
	}
	
	public BIS_DirectorAssessEntity(String M1, String M2, String M3, String M4,
			Timestamp M5, String M6, String M7,long ID1,String ID2) {
		this.M1 = M1;
		this.M2 = M2;
		this.M3 = M3;
		this.M4 = M4;
		this.M5 = M5;
		this.M6 = M6;
		this.M7 = M7;
		this.ID1 =ID1;
		this.ID2 =ID2;
	}
	
	@Column(name="M1", nullable=false, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M1;//年度
	
	@Column(name="M2", nullable=true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M2;//工作报告
	
	@Column(name="M3", nullable=true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M3;//附件（学历证书、职称证书、注册安全工程师证书、其他证明材料等）
	
	@Column(name="M4", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M4;//安监局审核意见
	
	@Column(name="M5", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M5;//安监局审核时间
	
	@Column(name="M6", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M6;//备注
	
	@Column(name="M7", nullable=true, columnDefinition="varchar(2)")
	@Setter
	@Getter	
	private String M7;//审核状态（0未审核、1同意、2不同意）
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//企业ID

	@Column(name = "ID2", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String ID2;//行政区划ID

	@Column(name = "ID3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID3;//操作者
}
