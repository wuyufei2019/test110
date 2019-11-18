package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: Upload_FilesEntity
 * @Description: 附件表
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="upload_files")
public class Upload_FilesEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -978356294013317067L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	public long ID1;//发布人

	@Column(name = "M1", nullable = true, length = 200)
	@Setter
	@Getter
	private String M1;//原文件名称

	@Column(name = "M2", nullable = true, length = 200)
	@Setter
	@Getter
	private String M2;//文件路径(新生成的相对路径名称)

	@Column(name = "M3", nullable = true, length = 50)
	@Setter
	@Getter
	private String M3;//文件类型
}
