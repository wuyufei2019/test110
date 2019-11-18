package com.cczu.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @ClassName: EAD_Accident_ERM_Hospital
 * @Description: 应急辅助决策_事故后果计算&应急资源管理_应急医院
 * @author jason
 *
 */
@Entity
@Table(name="ead_accident_erm_hospital")
public class EAD_Accident_ERM_Hospital implements Serializable {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	public EAD_Accident_ERM_Hospital() {
	}

	public EAD_Accident_ERM_Hospital(EAD_AccidentEntity accident, ERM_EmergencyHospitalEntity hospital) {
		this.accident = accident;
		this.hospital = hospital;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//编号
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCIDENT_ID", nullable = false)
	@Setter
	@Getter
	private EAD_AccidentEntity accident;//事故后果
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HOSPITAL_ID", nullable = false)
	@Setter
	@Getter
	private ERM_EmergencyHospitalEntity hospital;//应急资源管理_应急医院
	
	@Column(name = "DISTANCE", nullable = false, columnDefinition="int")
	@Setter
	@Getter
	private Integer distance;//距离范围 3/5/10   km

}
