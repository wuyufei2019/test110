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
 * 报警数据
 * @author jason
 *
 */
@Entity
@Table(name="ts_warningdata")
public class TS_WarningData implements Serializable{

	private static final long serialVersionUID = 3394817184833173976L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//编号
	
	@Column(name="ID1", nullable=false, columnDefinition="bigint")
	@Setter
	@Getter
	private long ID1;//关联ts_devicechannel  ID
	
	@Column(name="acceptdatetime", nullable=true , columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp acceptDatetime;//采集时间

	@Column(name="data1", nullable=true )
	@Setter
	@Getter
	private float data1;//实时数据
	
	@Column(name="min", nullable=true )
	@Setter
	@Getter
	private float min;//下限
	
	@Column(name="max", nullable=true )
	@Setter
	@Getter
	private float max;//上限
	
	@Column(name="bjyy", nullable=true ,length = 500)
	@Setter
	@Getter
	private String bjyy;//报警原因
	
	@Column(name="bjcl", nullable=true ,length = 500)
	@Setter
	@Getter
	private String bjcl;//报警处理
	
	@Column(name="clsj", nullable=true , columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp clsj;//处理时间
	
	
	@Column(name="type", nullable=true )
	@Setter
	@Getter
	private int type;//报警类型   1液位 2温度 3压力 4浓度
	
}
