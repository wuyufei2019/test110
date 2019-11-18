package com.cczu.model.lydw.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 蓝牙定位 报警管理
 */
@Entity
@Table(name = "lydw_bjgl")
public class LYDW_BJGL extends BaseEntity {

	private static final long serialVersionUID = -7943565615371517114L;

	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//企业id

	@Column(name = "m1", nullable = true, columnDefinition="varchar(200)")
    @Setter
    @Getter
    public String m1;//报警类别

    @Column(name = "m2", nullable = true, columnDefinition="varchar(500)")
    @Setter
    @Getter
    public String m2;//报警内容

	@Column(name = "m3", nullable = true, columnDefinition = "varchar(1000)")
	@Setter
	@Getter
	public String m3;//处理反馈

	@Column(name = "m4", nullable = true, columnDefinition = "varchar(100)")
	@Setter
	@Getter
	public String m4;//记录人

}
