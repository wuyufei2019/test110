package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @ClassName: BIS_StorageAreaEntity
 * @Description: 企业基本信息-仓库区
 * @author wbth
 * @date 2019年8月30日
 *
 */
@Entity
@Table(name="bis_storagearea")
public class BIS_StorageAreaEntity extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -8449150594078266458L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号
	
	@Column(name = "M1", nullable = true, length = 20)
	@Setter
	@Getter
	private String M1;//仓库区名称

	@Column(name = "M2", nullable = true, length = 20)
	@Setter
	@Getter
	private String M2;//仓库区编号

	@Column(name = "M3", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M3;//库区占地面积

	@Column(name = "M4", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M4;//仓库个数

	@Column(name = "M5", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M5;//lng

	@Column(name = "M6", nullable = true, length = 20)
	@Setter
	@Getter
	private String M6;//lat

	@Column(name = "M7", nullable = true, length = 500)
	@Setter
	@Getter
	private String M7;//备注

}
