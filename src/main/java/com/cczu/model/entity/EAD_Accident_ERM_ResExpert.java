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
 * @ClassName: EAD_Accident_ERM_ResExpert
 * @Description: 应急辅助决策_事故后果计算&应急资源管理_应急专家
 * @author jason
 *
 */
@Entity
@Table(name="ead_accident_erm_resexpert")
public class EAD_Accident_ERM_ResExpert implements Serializable {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	public EAD_Accident_ERM_ResExpert() {
	}

	public EAD_Accident_ERM_ResExpert(EAD_AccidentEntity accident, ERM_EmergencyResExpertEntity resexpert ) {
		this.accident = accident;
		this.resexpert = resexpert;
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
	@JoinColumn(name = "RESEXPERT_ID", nullable = false)
	@Setter
	@Getter
	private ERM_EmergencyResExpertEntity resexpert;//应急资源管理_应急专家
	
	@Column(name = "TYPE", nullable = false, columnDefinition="int")
	@Setter
	@Getter
	private Integer type;//事故类型  1 火灾/2 爆炸/3 泄漏

}
