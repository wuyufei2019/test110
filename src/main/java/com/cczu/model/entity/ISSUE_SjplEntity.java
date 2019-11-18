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
 * @ClassName: ISSUE_SjplEntity
 * @Description: 安全文件发布_事件评论
 * @author zpc
 * @date 2017年12月23日
 *
 */
@Entity
@Table(name="issue_sjpl")
public class ISSUE_SjplEntity implements Serializable {
	
	private static final long serialVersionUID = 2888187391651750192L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//事件id
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID2;//回复人id
	
	@Column(name = "fid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long fid;//父id

	@Column(name = "M1", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M1;//评论内容

	@Column(name = "M2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M2;//评论时间
}
