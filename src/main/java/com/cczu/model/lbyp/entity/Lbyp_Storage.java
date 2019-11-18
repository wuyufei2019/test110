package com.cczu.model.lbyp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * @ClassName: Lbyp_Storage
 * @Description: 劳保用品仓库信息
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name = "lbyp_storage")
public class Lbyp_Storage extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	private long ID1;// 企业编号

	@Column(name = "name", nullable = true, length = 100)
	@Setter
	@Getter
	private String name;// 仓库名称

	@Column(name = "number", nullable = true, length = 50)
	@Setter
	@Getter
	private String number;// 仓库编号

	@Column(name = "principle", nullable = true, length = 50)
	@Setter
	@Getter
	private String principle;// 负责人

	@Column(name = "phone", nullable = true,length = 20)
	@Setter
	@Getter
	private String phone;// 负责人电话

	@Column(name = "address", nullable = true,length = 100)
	@Setter
	@Getter
	private String address;// 仓库地址

	@Column(name = "note", nullable = true, length = 200)
	@Setter
	@Getter
	private String note;// 备注
	
}
