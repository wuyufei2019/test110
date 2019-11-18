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
 * @ClassName: AQPX_StudyhistoryEntity
 * @Description: 企业-安全培训-学习记录
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqpx_studyhistory")
public class AQPX_StudyhistoryEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "ID1", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ID1;//企业ID
	
	@Column(name = "ID2", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ID2;//学员ID
	
	@Column(name = "ID3", nullable = false, length = 8)
	@Setter
	@Getter
	private Long ID3;//课程ID
	
	@Column(name = "M1", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	private Integer M1;//学习时长
	
	@Column(name = "M2", nullable = true)
	@Setter
	@Getter
	private Timestamp M2;//开始学习时间
	
	@Column(name = "M3", nullable = true)
	@Setter
	@Getter
	private Timestamp M3;//结束学习时间


}
