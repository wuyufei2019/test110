package com.cczu.model.lydw.entity;

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

/*
 * 蓝牙定位-设备管理-工卡管理实体类
 */
@Entity
@Table(name = "pub_file")
public class Pub_File implements Serializable {

	private static final long serialVersionUID = -4744051578860045759L;

	@Id
	@GeneratedValue
	@Column(name = "fileid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long fileid;//标签号

	@Column(name = "id1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long id1;//企业id

	@Column(name = "filecode", nullable = false, columnDefinition="varchar(255)")
	@Setter
	@Getter
	public String filecode;//标签编码

	@Column(name = "tag", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String tag;//标签

	@Column(name = "intime", nullable = false, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp intime;//载入时间

	@Column(name = "uptime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp uptime;//更新时间

	@Column(name = "fstatus", nullable = true,length=1, columnDefinition="int default 1")
	@Setter
	@Getter
	public int fstatus;//状态位（按键、运动状态、低电量提醒）bit8-按键信息，bit0-标签运动/禁止，bit1-低电量提醒

	@Column(name = "val1", nullable = true, columnDefinition="varchar(50) default 0")
	@Setter
	@Getter
	public String val1;//

	@Column(name = "ftype", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String ftype;//保留

	@Column(name = "online", nullable = true,length=1, columnDefinition="int default 0")
	@Setter
	@Getter
	public int online;//0-离线，1-在线
}
