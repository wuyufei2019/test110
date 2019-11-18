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
 * @ClassName: BIS_DirectorEntity
 * @Description: 企业基本信息-安全总监申报
 * @author jason
 *
 */
@Entity
@Table(name="bis_director")
public class BIS_DirectorEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public BIS_DirectorEntity() {
	}

	public BIS_DirectorEntity(Long ID) {
		this.ID=ID;
	}
	public BIS_DirectorEntity(String M1, String M2, Timestamp M3, String M4,
			String M5, String M6, int M7, Timestamp M8, Timestamp M8_1, String M9) {
		this.M1 = M1;
		this.M2 = M2;
		this.M3 = M3;
		this.M4 = M4;
		this.M5 = M5;
		this.M6 = M6;
		this.M7 = M7;
		this.M8 = M8;
		this.M8 = M8_1;
		this.M9 = M9;
	}
	
	public BIS_DirectorEntity(String M1, String M2, Timestamp M3, String M4,
			String M5, String M6, int M7, Timestamp M8, Timestamp M8_1, String M9, String M10,
			Timestamp M11, String M12, String M13) {
		this.M1 = M1;
		this.M2 = M2;
		this.M3 = M3;
		this.M4 = M4;
		this.M5 = M5;
		this.M6 = M6;
		this.M7 = M7;
		this.M8 = M8;
		this.M8 = M8_1;
		this.M9 = M9;
		this.M10 = M10;
		this.M11 = M11;
		this.M12 = M12;
		this.M13 = M13;
	}
	
	public BIS_DirectorEntity(long ID,Timestamp S1,String M1, String M2, Timestamp M3, String M4,
			String M5, String M6, int M7, Timestamp M8, Timestamp M8_1, String M9, String M10,
			Timestamp M11, String M12, String M13, long ID1,String ID2,long ID3) {
		this.ID = ID;
		this.S1 = S1;
		this.M1 = M1;
		this.M2 = M2;
		this.M3 = M3;
		this.M4 = M4;
		this.M5 = M5;
		this.M6 = M6;
		this.M7 = M7;
		this.M8 = M8;
		this.M8 = M8_1;
		this.M9 = M9;
		this.M10 = M10;
		this.M11 = M11;
		this.M12 = M12;
		this.M13 = M13;
		this.ID1 =ID1;
		this.ID2 =ID2;
		this.ID3 =ID3;
	}
	
	@Column(name="M1", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//姓名
	
	@Column(name="M2", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M2;//性别
	
	@Column(name="M3", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M3;//出生年月
	
	@Column(name="M4", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M4;//学历
	
	@Column(name="M5", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M5;//专业
	
	@Column(name="M6", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M6;//职称
	
	@Column(name="M7", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private int M7;//工作年限
	
	@Column(name="M8", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M8;//聘用开始时间
	
	@Column(name="M8_1", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M8_1;//聘用结束时间
	
	@Column(name="M9", nullable=true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M9;//附件（学历证书、职称证书、注册安全工程师证书、其他证明材料等）
	
	@Column(name="M10", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M10;//安监局审核意见
	
	@Column(name="M11", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M11;//安监局审批时间
	
	@Column(name="M12", nullable=true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M12;//备注
	
	@Column(name="M13", nullable=true, columnDefinition="varchar(2)")
	@Setter
	@Getter	
	private String M13;//审核状态（0未审核、1审核通过、2审核不通过）
	
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
