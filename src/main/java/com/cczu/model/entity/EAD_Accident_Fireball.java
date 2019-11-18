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
 * @ClassName: EAD_Accident_Fireball
 * @Description: 应急辅助决策_事故后果计算&火球
 * @author jason
 *Gasphysical  应急辅助决策_事故后果计算&压缩气体物理爆炸
 *Instantleakage 应急辅助决策_事故后果计算&瞬时泄漏
 *JetFire  应急辅助决策_事故后果计算&喷射火
 *Leakage  应急辅助决策_事故后果计算&持续泄漏
 *Physical  应急辅助决策_事故后果计算&物理爆炸（压力容器爆炸）
 *PoolFire  应急辅助决策_事故后果计算&池火灾
 *Vce       应急辅助决策_事故后果计算&化学爆炸（蒸气云爆炸）
 *
 */
@Entity
@Table(name="ead_accident_fireball")
public class EAD_Accident_Fireball implements Serializable {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	public EAD_Accident_Fireball() {
	}

	public EAD_Accident_Fireball(EAD_AccidentEntity accident, ACA_FireballEntity fireball) {
		this.accident = accident;
		this.fireball = fireball;
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
	@JoinColumn(name = "FIREBALL_ID", nullable = false)
	@Setter
	@Getter
	private ACA_FireballEntity fireball;//火球计算
	
}
