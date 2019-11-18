package com.cczu.model.mbgl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;
/**
 * 
 * @ClassName: Target_SafetyAction
 * @Description: 目标管理：安全文化-安全活动
 *
 */
@Entity
@Table(name="target_safetyaction")
public class Target_SafetyAction extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long ID1;//企业id
	
	@Column(name = "ID2", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long ID2;//召集部门id

	@Column(name = "name", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String name;//活动名称
	
	@Column(name = "address", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String address;//地点

	@Column(name = "time", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp time;//活动时间
	
	@Column(name = "gatherper", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String gatherper;//召集人

	@Column(name = "attenddeps", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String attenddeps;//参加部门
	
	@Column(name = "actiontlev", nullable = true, columnDefinition="varchar(4)")
	@Getter
	@Setter
	private String actiontlev;//活动级别  1：公司  2： 部门  3：班组
	
	@Column(name="content", nullable = true, columnDefinition="varchar(max)")
	@Getter
	@Setter
	private String content;//活动内容
	
	@Column(name="recordper", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String recordper;//记录人
	
	@Column(name = "state", nullable = true, columnDefinition="varchar(4)")
	@Getter
	@Setter
	private String state;//状态  1：待开  2： 推迟  3：结束
	@Column(name = "url", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String url;//活动照片、视频
	@Column(name = "otherurl", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String otherurl;//其他材料
	
	
}
