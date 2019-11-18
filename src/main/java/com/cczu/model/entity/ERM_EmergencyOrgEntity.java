package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: ERM_EmergencyOrgEntity
 * @Description: 应急资源管理_应急组织
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="erm_emergencyorg")
public class ERM_EmergencyOrgEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -869831118280016002L;

	@Column(name = "M1", nullable = true, length = 50)
	@Setter
	@Getter
	private String M1;//组织名称

	@Column(name = "M2", nullable = true, length = 30)
	@Setter
	@Getter
	private String M2;//组织负责人

	@Column(name = "M3", nullable = true, length = 50)
	@Setter
	@Getter	
	private String M3;//负责人联系电话

	@Column(name = "M4", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M4;//组织联系人
	
	@Column(name = "M5", nullable = true, length = 50)
	@Setter
	@Getter	
	private String M5;//组织联系人电话
	
	@Column(name = "M6", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M6;//组织应急职责
	
	@Column(name = "M7", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M7;//备注
	
	@Column(name = "M8", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M8;//应对事故类型(1对多) 按照这个类型来填：物体打击、 车辆伤害、机械伤害、 起重伤害、触电、淹溺、灼烫、０８ 火灾、 高处坠落、坍塌、冒顶片帮、 透水、放炮、火药爆炸、瓦斯爆炸、锅炉爆炸、 容器爆炸、其它爆炸、 中毒和窒息、其它伤害

	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	public long ID1;//操作者
	
	@Column(name = "qyid", nullable = true, length = 8)
	@Setter
	@Getter
	public Long qyid;//企业id
	
	@Column(name = "userid", nullable = true, length = 20)
	@Setter
	@Getter
	public Long userid;//安监用户id
}
