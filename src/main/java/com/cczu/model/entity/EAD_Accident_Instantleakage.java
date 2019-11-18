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
 * @ClassName: EAD_Accident_Instantleakage
 * @Description: 应急辅助决策_事故后果计算&瞬时泄漏
 * @author jason
 *
 */
@Entity
@Table(name="ead_accident_instantleakage")
public class EAD_Accident_Instantleakage implements Serializable {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	public EAD_Accident_Instantleakage() {
	}

	public EAD_Accident_Instantleakage(EAD_AccidentEntity accident, ACA_InstantleakageEntity instantleakage) {
		this.accident = accident;
		this.instantleakage = instantleakage;
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
	@JoinColumn(name = "INSTANTLEAKAGE_ID", nullable = false)
	@Setter
	@Getter
	private ACA_InstantleakageEntity instantleakage;//火球计算
	
}
