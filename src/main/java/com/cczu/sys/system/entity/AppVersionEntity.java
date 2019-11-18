package com.cczu.sys.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @ClassName: AppVersionEntity
 * @Description: APP版本更新表
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name = "t_app_version")
@DynamicUpdate @DynamicInsert
public class AppVersionEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, columnDefinition="bigint")// comment'id主键' ")
	@Setter
	@Getter
	public long id;//编号

	@Setter
	@Getter
	@Column(name = "name",columnDefinition="varchar(1000)")// comment'名称' ")
	private String name;//新版本名称
	
	@Setter
	@Getter
	@Column(name = "fileurl",columnDefinition="varchar(1000)")// comment'新版本APK地址' ")
	private String fileurl;//新版本APK地址
	
	@Setter
	@Getter
	@Column(name = "message",columnDefinition="varchar(1000)")// comment'每个版本更新内容' ")
	private String message;//每个版本更新内容
	
	@Setter
	@Getter
	@Column(name = "type",columnDefinition="varchar(100)")// comment'app类型 巡检、培训、危险作业 等等' ")
	private String type;//app类型（巡检、培训、危险作业 等等）
	
	@Setter
	@Getter
	@Column(name = "version_no",columnDefinition="int default 100")// comment'版本号' ")
	private Integer versionNo;//版本号
	
	//构造函数
	public AppVersionEntity() {
		
	}
	
	//构造函数
	public AppVersionEntity(long id) {
		this.id=id;
	}
	
	public AppVersionEntity(String name, String fileurl, String message,String type,Integer versionNo) {
		this.name=name;
		this.fileurl=fileurl;
		this.message=message;
		this.type=type;
		this.versionNo=versionNo;
	}
	
	public AppVersionEntity(String fileurl, String message,String type) {
		this.fileurl=fileurl;
		this.message=message;
		this.type=type;
	}
	
	
}
