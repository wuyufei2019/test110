package com.cczu.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @ClassName: MSG_detailEntity
 * @Description: 消息详细信息
 * @author jason
 * @date 2017年12月29日
 *
 */
@Entity
@Table(name="msg_detail")
public class MSG_detailEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public MSG_detailEntity() {
	}
	
	public MSG_detailEntity(Long id) {
		this.ID = id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//编号
	
	@Column(name = "S1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp S1;//创建时间
	
	@Column(name = "S2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp S2;//更新时间
	
	@Column(name = "S3", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int S3;//删除标识
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint" )
	@Setter
	@Getter
	public long ID1;//发布者
	
	@Column(name = "ID2", nullable = true, columnDefinition="bigint" )
	@Setter
	@Getter
	public Long ID2;//消息来源id

	@Column(name = "TYPE", nullable = true, columnDefinition="varchar(2)" )
	@Setter
	@Getter
	private String type;//类型 1：检测报告，2：培训计划，3：安全文件发布，4：安全生产动态,5:检查计划

	@Column(name = "INFO", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String info;//提醒

	@Column(name = "CONTENT", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String content;//内容

	@Column(name = "SENG_OBJ", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter	
	private Long sengObj;//发送对象
	
	@Column(name = "STATUS", nullable = true, columnDefinition="varchar(2)")
	@Setter
	@Getter	
	private String status;//状态  1:已读，2：未读

	@Column(name = "RELEASE_TIME", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp releaseTime;//发布时间
}
