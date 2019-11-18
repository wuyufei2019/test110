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
 * @ClassName: Target_SafetyMeeting
 * @Description: 目标管理：安全文化-安全会议
 *
 */
@Entity
@Table(name="target_safetymeeting")
public class Target_SafetyMeeting extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long ID1;//企业id
	
	@Column(name = "ID2", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long ID2;//召集部门id

	@Column(name = "theme", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String theme;//会议主题
	
	@Column(name = "hostess", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String hostess;//会议主持人
	
	@Column(name = "time", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp time;//会议时间（精确到分）
	
	@Column(name = "address", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String address;//会议地点
	
	@Column(name = "attenddeps", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String attenddeps;//参加部门

	@Column(name = "type", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String type;//会议类型 1 ： 安委会例会   2：部门例会 3：临时会议 4：其他会议
	
	@Column(name="content", nullable = true, columnDefinition="varchar(5000)")
	@Getter
	@Setter
	private String content;//会议记录
	
	@Column(name = "url", nullable = true, columnDefinition="varchar(5000)")
	@Getter
	@Setter
	private String url;//会议记录、照片、视频
	
	@Column(name="recordper", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String recordper;//记录人
	
	@Column(name="note", nullable = true, columnDefinition="varchar(2000)")
	@Getter
	@Setter
	private String note;//会议督办事项
	
	@Column(name="feedback", nullable = true, columnDefinition="varchar(2000)")
	@Getter
	@Setter
	private String feedback;//会议反馈
	
	@Column(name = "state", nullable = true, columnDefinition="varchar(4)")
	@Getter
	@Setter
	private String state;//状态  1：待开  2： 推迟  3：结束
	
	@Column(name = "delayreason", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String delayreason;//state=2时记录推迟原因
	
	
}
