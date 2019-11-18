package com.cczu.sys.comm.utils;

import it.sauronsoftware.base64.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 坐标计算、gps百度坐标转换
 * @author jason
 */
@SuppressWarnings("unchecked")
public class BaiDuMap {

	static double DEF_PI = 3.14159265359;//3.14159265359;//Math.PI;//3.14159265359; // PI
    static double DEF_2PI= 6.28318530712;//Math.PI*2;//6.28318530712; // 2*PI
    static double DEF_PI180= 0.01745329252;//Math.PI/180;//0.01745329252; // PI/180.0
    static double DEF_R =6370693.5;//6370996.81;//6370693.5; // 地球半径
    
    /**
     * 获取2个坐标点之间的距离（适用于近距离）
     * @param lon1
     * @param lat1
     * @param lon2
     * @param lat2
     * @return
     */
    public static double GetShortDistance(double lon1, double lat1, double lon2, double lat2)
    {
        double ew1, ns1, ew2, ns2;
        double dx, dy, dew;
        double distance;
        // 角度转换为弧度
        ew1 = lon1 * DEF_PI180;
        ns1 = lat1 * DEF_PI180;
        ew2 = lon2 * DEF_PI180;
        ns2 = lat2 * DEF_PI180;
        // 经度差
        dew = ew1 - ew2;
        // 若跨东经和西经180 度，进行调整
        if (dew > DEF_PI)
        dew = DEF_2PI - dew;
        else if (dew < -DEF_PI)
        dew = DEF_2PI + dew;
        dx = DEF_R * Math.cos(ns1) * dew; // 东西方向长度(在纬度圈上的投影长度)
        dy = DEF_R * (ns1 - ns2); // 南北方向长度(在经度圈上的投影长度)
        // 勾股定理求斜边长
        distance = Math.sqrt(dx * dx + dy * dy);
        return distance;
    }
    /**
     * 获取2个坐标点之间的距离（适用于远距离）
     * @param lon1
     * @param lat1
     * @param lon2
     * @param lat2
     * @return
     */
    public static double GetLongDistance(double lon1, double lat1, double lon2, double lat2)
    {
        double ew1, ns1, ew2, ns2;
        double distance;
        // 角度转换为弧度
        ew1 = lon1 * DEF_PI180;
        ns1 = lat1 * DEF_PI180;
        ew2 = lon2 * DEF_PI180;
        ns2 = lat2 * DEF_PI180;
        // 求大圆劣弧与球心所夹的角(弧度)
        distance = Math.sin(ns1) * Math.sin(ns2) + Math.cos(ns1) * Math.cos(ns2) * Math.cos(ew1 - ew2);
        // 调整到[-1..1]范围内，避免溢出
        if (distance > 1.0)
             distance = 1.0;
        else if (distance < -1.0)
              distance = -1.0;
        // 求大圆劣弧长度
        distance = DEF_R * Math.acos(distance);
        return distance;
    }
    
    /**
     * 获取2个坐标点之间的距离
     * @param lon1
     * @param lat1
     * @param lon2
     * @param lat2
     * @return
     */
	private static double GetDistanceFromXtoY(double lon1, double lat1, double lon2, double lat2) {  
        double pk = 180 / DEF_PI;//3.14159265359;  
        double a1 = lat1 / pk;  
        double a2 = lon1 / pk;  
        double b1 = lat2 / pk;  
        double b2 = lon2 / pk;  
        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);  
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);  
        double t3 = Math.sin(a1) * Math.sin(b1);  
        double tt = Math.acos(t1 + t2 + t3);  
        return DEF_R * tt;  
    }  
    
    /**
     * GPS转百度坐标
     * @param x 
     * @param y
     * @return
     * @throws IOException
     */
    public static Map<String,Object> gpsTobaidu(double x,double y){
    	String path = "http://api.map.baidu.com/ag/coord/convert?from=0&to=4&x="+x+"+&y="+y+"&callback=BMap.Convertor.cbk_7594";
    	try {
	    	//使用http请求获取转换结果
	    	URL url = new URL(path);
	    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    	conn.setRequestMethod("GET");
	    	conn.setConnectTimeout(5 * 1000);
	    	InputStream inStream = conn.getInputStream();
	    	ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	    	Map<String, Object> remap=new HashMap<String,Object>();
			ObjectMapper objectMapper=new ObjectMapper();
	    	byte[] buffer = new byte[1024];
	    	int len = 0;
	    	while ((len = inStream.read(buffer)) != -1)
	    	{
	    		outStream.write(buffer, 0, len);
	    	}
	    	//得到返回的结果
	    	String res = outStream.toString();
	    	//处理结果
	    	if (res.indexOf("(") > 0 && res.indexOf(")") > 0)
	    	{
		    	String str = res.substring(res.indexOf("(") + 1, res.indexOf(")"));
		    	String err = res.substring(res.indexOf("error") + 7, res.indexOf("error") + 8);
		    	if ("0".equals(err))
		    	{
					Map<String,Object> jsmap = objectMapper.readValue(str, Map.class);
			    	//编码转换
			    	remap.put("x", Base64.decode((String) jsmap.get("x")));
			    	remap.put("y", Base64.decode((String) jsmap.get("y")));
		    	}
	    	}
			return remap;
    	} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (java.net.ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    public static void main(String[] args) throws IOException {
        double mLat1 = 31.956487; // point1纬度
        double mLon1 = 119.958855; // point1经度
        double mLat2 = 31.976503;// point2纬度
        double mLon2 = 120.308839;// point2经度
        double distance = BaiDuMap.GetShortDistance(mLon1, mLat1, mLon2, mLat2);
        double distance2 = BaiDuMap.GetLongDistance(mLon1, mLat1, mLon2, mLat2);
        double distance3 = BaiDuMap.GetDistanceFromXtoY(mLon1, mLat1, mLon2, mLat2);
        
        System.out.println(distance);
        System.out.println(distance2);
        System.out.println(distance3);

    	//转换前的GPS坐标
//    	double x=119.961536;
//    	double y=31.685032;
//    	Map<String, Object> map=BaiDuMap.gpsTobaidu(x,y);
//    	//转换后百度坐标
//    	System.out.println(map.get("x")+"   "+map.get("y"));
    }
}
