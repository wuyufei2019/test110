package com.cczu.model.xfssgl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: XFSSGL_XfssjcEntity
 * @Description: 消防设施检查
 * @author wbth
 * @date 2018年4月24日
 *
 */
@Entity
@Table(name="xfssgl_xfssjc")
public class XFSSGL_XfssjcEntity extends BaseEntity{
	
	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 1165850916676801332L;

	@Column(name = "ID1", nullable = false, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private Long ID1;//消防设施登记表的主键id
	
	@Column(name = "ID2", nullable = false, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private Long ID2;//检查人员所在表的主键id
	
	@Column(name = "ID3", nullable = false, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private Long ID3;//企业id
	
	@Column(name = "checktime", nullable = false, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp checktime;//检查时间
	
	@Column(name = "checkcontent", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String checkcontent;//检查内容
	
	@Column(name = "checkresult", nullable = false, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String checkresult;//检查结论
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M1;//备注
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M2;//图片/视频地址

}
