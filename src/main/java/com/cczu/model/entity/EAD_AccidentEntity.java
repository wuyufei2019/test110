package com.cczu.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @ClassName: EAD_AccidentEntity
 * @Description: 应急辅助决策_事故后果计算
 * @author jason
 *
 */
@Entity
@Table(name="ead_accident")
public class EAD_AccidentEntity implements Serializable {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	public EAD_AccidentEntity() {
	}

	public EAD_AccidentEntity(Long id) {
		this.ID=id;
	}
	
	public EAD_AccidentEntity(long qyid, String type,String lng,String lat,String todayfx ) {
		this.qyid = qyid;
		this.type = type;
		this.lng = lng;
		this.lat = lat;
		this.todayfx = todayfx;
	}
	
	public EAD_AccidentEntity(long qyid, String type,String lng,String lat,Set<EAD_Accident_Fireball> fireballs,Set<EAD_Accident_Gasphysical> gasphysicals,
			Set<EAD_Accident_Instantleakage> instantleakages,Set<EAD_Accident_JetFire> jetFires,Set<EAD_Accident_Leakage> leakages,
			Set<EAD_Accident_Physical> physicals,Set<EAD_Accident_PoolFire> poolFires,Set<EAD_Accident_Vce> vces ) {
		this.qyid = qyid;
		this.type = type;
		this.lng = lng;
		this.lat = lat;
		this.fireballs = fireballs;
		this.gasphysicals = gasphysicals;
		this.instantleakages = instantleakages;
		this.jetFires = jetFires;
		this.leakages = leakages;
		this.physicals = physicals;
		this.poolFires = poolFires;
		this.vces = vces;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//编号
	
	@Column(name = "S1", nullable = false, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp S1;//创建时间
	
	@Column(name="QYID", nullable=true, columnDefinition="bigint")
	@Setter
	@Getter
	private long qyid;//企业
	
	@Column(name="QYNAME", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String qyname;//企业名称
	
	@Column(name="TYPE", nullable=true, columnDefinition="varchar(4)")
	@Setter
	@Getter
	private String type;//事故类型
	
	@Column(name="MATTER", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String matter;//涉及危险化学品
	
	@Column(name="MATTERSTR", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String matterstr;//涉及危险化学品
	
	@Column(name="LNG", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String lng;//经度
	
	@Column(name="LAT", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String lat;//纬度
	
	@Column(name="TODAYFX", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String todayfx;//风向
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//用户
	
	@Column(name = "ID2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String ID2;//行政区域
	
	@Column(name = "ID3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID3;//企业ID
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accident")
	@Setter
	@Getter
	private Set<EAD_Accident_Fireball> fireballs = new HashSet<EAD_Accident_Fireball>(0);
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accident")
	@Setter
	@Getter
	private Set<EAD_Accident_Gasphysical> gasphysicals = new HashSet<EAD_Accident_Gasphysical>(0);
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accident")
	@Setter
	@Getter
	private Set<EAD_Accident_Instantleakage> instantleakages = new HashSet<EAD_Accident_Instantleakage>(0);
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accident")
	@Setter
	@Getter
	private Set<EAD_Accident_JetFire> jetFires = new HashSet<EAD_Accident_JetFire>(0);
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accident")
	@Setter
	@Getter
	private Set<EAD_Accident_Leakage> leakages = new HashSet<EAD_Accident_Leakage>(0);
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accident")
	@Setter
	@Getter
	private Set<EAD_Accident_Physical> physicals = new HashSet<EAD_Accident_Physical>(0);
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accident")
	@Setter
	@Getter
	private Set<EAD_Accident_PoolFire> poolFires = new HashSet<EAD_Accident_PoolFire>(0);
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accident")
	@Setter
	@Getter
	private Set<EAD_Accident_Vce> vces = new HashSet<EAD_Accident_Vce>(0);
}
