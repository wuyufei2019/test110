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
 * @ClassName: BIS_DirectorResumeEntity
 * @Description: 企业基本信息-安全总监申报工作经验
 * @author jason
 *
 */
@Entity
@Table(name="bis_directorresume")
public class BIS_DirectorResumeEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public BIS_DirectorResumeEntity() {
	}

	public BIS_DirectorResumeEntity(Long id) {
		this.ID=id;
	}

	public BIS_DirectorResumeEntity(Timestamp m1, Timestamp m2, String m3, String m4,
			String m5) {
		M1 = m1;
		M2 = m2;
		M3 = m3;
		M4 = m4;
		M5 = m5;
	}
	
	public BIS_DirectorResumeEntity(Timestamp m1, Timestamp m2, String m3, String m4,
			String m5, long id1) {
		M1 = m1;
		M2 = m2;
		M3 = m3;
		M4 = m4;
		M5 = m5;
		ID1 =id1;
	}
	
	@Column(name="M1", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M1;//时间起
	
	@Column(name="M2", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M2;//时间止
	
	@Column(name="M3", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M3;//单位
	
	@Column(name="M4", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M4;//工作描述
	
	@Column(name="M5", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M5;//备注
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//关联安全总监表id
}
