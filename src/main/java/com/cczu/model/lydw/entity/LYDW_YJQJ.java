package com.cczu.model.lydw.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 蓝牙定位 一键求救
 */
@Entity
@Table(name = "lydw_yjqj")
public class LYDW_YJQJ extends BaseEntity {

	private static final long serialVersionUID = -3412188520791340945L;

    @Column(name = "xbid", nullable = true, columnDefinition="bigint")
    @Setter
    @Getter
    public Long xbid;//信标id

    @Column(name = "gkid", nullable = true, columnDefinition="bigint")
    @Setter
    @Getter
    public Long gkid;//工卡id

}
