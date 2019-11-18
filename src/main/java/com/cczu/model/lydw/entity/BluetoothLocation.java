package com.cczu.model.lydw.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 蓝牙定位实体类
 */
public class BluetoothLocation implements Serializable {
    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -6428335501620710360L;

    @Setter
    @Getter
    public String sourceName;

    @Setter
    @Getter
    public String formatType;//TP:上传 Tag 位置信息  TD:上传 Tag 自定义数据

    @Setter
    @Getter
    public String tagId;

    @Setter
    @Getter
    public String tagIdFormat;

    @Setter
    @Getter
    public Double x;

    @Setter
    @Getter
    public Double y;

    @Setter
    @Getter
    public Double z;

    @Setter
    @Getter
    public String battery;

    @Setter
    @Getter
    public String timeStamp;

    @Setter
    @Getter
    public String anchOrName;

    @Setter
    @Getter
    public String ipAddressV4;

    @Setter
    @Getter
    public Integer blinkId;

    @Setter
    @Getter
    public Integer qualityIndicator;

    @Setter
    @Getter
    public String payload;

    @Override
    public String toString() {
        return "BluetoothLocation{" +
                "sourceName='" + sourceName + '\'' +
                ", formatType='" + formatType + '\'' +
                ", tagId='" + tagId + '\'' +
                ", tagIdFormat='" + tagIdFormat + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", battery='" + battery + '\'' +
                ", timeStamp=" + timeStamp +
                ", anchOrName='" + anchOrName + '\'' +
                ", ipAddressV4='" + ipAddressV4 + '\'' +
                ", blinkId=" + blinkId +
                ", qualityIndicator=" + qualityIndicator +
                ", payload='" + payload + '\'' +
                '}';
    }
}
