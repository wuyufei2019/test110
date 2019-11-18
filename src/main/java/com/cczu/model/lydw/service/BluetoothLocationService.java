package com.cczu.model.lydw.service;

import com.cczu.model.lydw.entity.BluetoothLocation;
import com.cczu.model.lydw.entity.Pub_File;
import com.cczu.model.lydw.entity.Pub_FileRoomTime;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * 蓝牙定位service
 */
@Service("BluetoothLocationService")
public class BluetoothLocationService {

    /**
     * 将msg中的数据解析为实体类 BluetoothLocation
     * @param msg
     * @return
     */
    public BluetoothLocation getData(String msg) {
        if (StringUtils.isNotEmpty(msg)) {
            BluetoothLocation bluetoothLocation = new BluetoothLocation();
            String[] split = msg.split(",");
            bluetoothLocation.setSourceName(split[0]);
            bluetoothLocation.setFormatType(split[1]);
            bluetoothLocation.setTagId(split[2]);
            bluetoothLocation.setTagIdFormat(split[3]);
            bluetoothLocation.setX(Double.valueOf(split[4]));
            bluetoothLocation.setY(Double.valueOf(split[5]));
            bluetoothLocation.setZ(Double.valueOf(split[6]));
            bluetoothLocation.setBattery(split[7]);
            bluetoothLocation.setTimeStamp(split[8]);
            bluetoothLocation.setBlinkId(Integer.valueOf(split[9]));
            bluetoothLocation.setQualityIndicator(Integer.valueOf(split[10]));
            bluetoothLocation.setPayload(split[11]);

            return bluetoothLocation;

        }
        return null;

    }

    /**
     *
     * @param bluetoothLocation
     */
    public void bluetoothConversion(BluetoothLocation bluetoothLocation) {
        //工卡管理
        Pub_File pubFile = new Pub_File();
        pubFile.setFilecode(bluetoothLocation.getTagId());
        pubFile.setFstatus(Integer.parseInt(bluetoothLocation.getTagIdFormat()));
        //标签房间实时表
        Pub_FileRoomTime pubFileRoomTime = new Pub_FileRoomTime();

    }
}
