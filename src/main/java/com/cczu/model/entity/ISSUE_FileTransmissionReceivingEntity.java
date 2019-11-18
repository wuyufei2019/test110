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
 * @ClassName: ISSUE_FileTransmissionReceivingEntity
 * @Description: 安全文件发布_文件传递与接收
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="issue_filetransmissionreceiving")
public class ISSUE_FileTransmissionReceivingEntity extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 2888187391651750192L;

	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	public long ID1;//安全文件发布ID

	@Column(name = "ID2", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID2;//企业ID

	@Column(name = "M1", nullable = true, length = 2)
	@Setter
	@Getter	
	private int M1;//是否查看：0未查看，1已查看
	
	@Column(name = "M2", nullable = true, length = 2)
	@Setter
	@Getter	
	private int M2;//是否下载：0未下载，1已下载
	
	@Column(name = "M3", nullable = true)
	@Setter
	@Getter	
	private Timestamp M3;//下载时间
	
	@Column(name = "M4", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M4;//回执内容
	@Column(name = "M5", nullable = true, length = 2)
	@Setter
	@Getter	
	private int M5;//是否回执：0否，1是
	@Column(name = "url", nullable = true, length = 500)
	@Setter
	@Getter	
	private String url;//回执附件url

}
