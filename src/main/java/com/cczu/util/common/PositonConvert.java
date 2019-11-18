package com.cczu.util.common;

import com.cczu.model.lydw.entity.Pub_FileTime;
import com.cczu.model.lydw.listener.ConfigProperties;

/*定位系统坐标转换*/
public class PositonConvert {

    private static float xOffset = 0, yOffset = 0, scale = 1;

    public static double[] toMapPosition(double[] pos, double scale){
        pos[0] = pos[0] * scale;
        pos[2] = pos[2] * scale;
        return pos;
    }

    public static double[] toMapPosition(double[] pos){
        return pos;
    }

    /**坐标转换
     *
     * @param pb
     * @return
     */
    public static Pub_FileTime convert(Pub_FileTime pb){
        pb.setX(pb.getX() * scale + xOffset);
        pb.setY(pb.getY() * scale + yOffset);
        return pb;
    }
}
