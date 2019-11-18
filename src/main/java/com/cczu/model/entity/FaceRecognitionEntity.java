package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @ClassName: FaceRecognitionEntity
 * @Description: 双向人脸识别
 * @author 
 * @date 2019年11月18日
 *
 */
@Entity
@Table(name = "face_recognition_entity")
public class FaceRecognitionEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
   
	@Column(name = "ryname", nullable = true,columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String ryname;// 人员名称
   
	@Column(name = "code", nullable = true,columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String code;// 进出编号
   
	@Column(name = "in_out_time", nullable = true,columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp in_out_time;// 进出时间
   
}
