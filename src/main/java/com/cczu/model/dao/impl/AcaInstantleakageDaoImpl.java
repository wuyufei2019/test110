package com.cczu.model.dao.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.springframework.stereotype.Repository;

import com.cczu.client.sgjs.webservice.ArrayOfString;
import com.cczu.client.sgjs.webservice.SGWebservice;
import com.cczu.client.sgjs.webservice.SGWebserviceSoap;
import com.cczu.model.entity.ACA_InstantleakageEntity;
import com.cczu.model.dao.IAcaInstantleakageDao;
import com.cczu.util.dao.BaseDao;

@Repository("AcaInstantleakageDao")
public class AcaInstantleakageDaoImpl extends BaseDao<ACA_InstantleakageEntity, Long> implements IAcaInstantleakageDao {

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ACA_InstantleakageEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ACA_InstantleakageEntity> findListInfoByUserId(long id1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> saveInfo(ACA_InstantleakageEntity aca) throws Exception{

		float FX = 0;//FX风向角度
		float u = Float.parseFloat(aca.getM4());//u风速
		String WDX = "B";//WDX稳定度
		//风向
		switch(aca.getM5()){
			case "1": FX = 0; break;//东
			case "2": FX = (float) (Math.PI / 4); break;//东南
			case "3": FX = (float)Math.PI / 2; break;//南
			case "4": FX = (float)(3*Math.PI / 4); break;//西南
			case "5": FX = (float)Math.PI; break;//西
			case "6": FX = (float)(5*Math.PI / 4); break;//西北
			case "7": FX = (float)(3*Math.PI / 2); break;//北
			case "8": FX = (float)(7*Math.PI / 4); break;//东北
		}
		//天气条件
		switch(aca.getM6()){
			case "1": 
				if(aca.getM4().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM4().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM4().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM4().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM4().equals("8")){//>6
					WDX = "D";
				}
				break;//白天-晴
			case "2": 
				if(aca.getM4().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM4().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM4().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM4().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM4().equals("8")){//>6
					WDX = "D";
				} 
				break;//白天-少云
			case "3": 
				if(aca.getM4().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM4().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM4().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM4().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM4().equals("8")){//>6
					WDX = "D";
				} 
				break;//白天-多云
			case "4":
				if(aca.getM4().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM4().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM4().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM4().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM4().equals("8")){//>6
					WDX = "D";
				}
				break;//阴天
			case "5": 
				if(aca.getM4().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM4().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM4().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM4().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM4().equals("8")){//>6
					WDX = "D";
				} 
				break;//夜间-阴
			case "6": 
				if(aca.getM4().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM4().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM4().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM4().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM4().equals("8")){//>6
					WDX = "D";
				}
				break;//夜间-多云
		}
        float C = Float.parseFloat(aca.getM2());	//C边界浓度
		float Q = Float.parseFloat(aca.getM3());//泄漏质量
		double lng = Double.parseDouble(aca.getM7());
		double lat = Double.parseDouble(aca.getM8());
		int T = Integer.parseInt(aca.getM9());	//时间 间隔10秒    判断返回值，小于1停止
		
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
        QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
        SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
        SGWebserviceSoap port = ss.getSGWebserviceSoap();  

		ArrayOfString re = port.puffPUT(C, T, Q, WDX, u, FX, lng, lat);
		String[] arrstr = re.getString().toString().split(","); 
		String Str1=arrstr[0].toString().substring(1, arrstr[0].length());
		String Str2=arrstr[1].toString().substring(1, arrstr[1].length());
		String Str3=arrstr[2].toString().substring(1, arrstr[2].length());
		if(Str3.equals("非数字")){
			Str3="0";
		}
		if(Str1.equals("null")){
			Str1=aca.getM7();
		}
		if(Str2.equals("null")){
			Str2=aca.getM8();
		}
		
//		aca.setM10(String.valueOf(FX) );
//		aca.setM11(String.valueOf(WDX) );
//		aca.setM12(Str1 );
//		aca.setM13(Str2 );
//		aca.setM14(Str3 );
//		save(aca);
		
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("lng", Str1);
        map.put("lat", Str2);
        map.put("r", Str3);
        
		return map;
	}

	@Override
	public Map<String, Object> jcsaveInfo(ACA_InstantleakageEntity aca) throws Exception{

		float FX = 0;//FX风向角度
		float u = Float.parseFloat(aca.getM4());//u风速
		String WDX = "B";//WDX稳定度
		//风向
		switch(aca.getM5()){
			case "1": FX = 0; break;//东
			case "2": FX = (float) (Math.PI / 4); break;//东南
			case "3": FX = (float)Math.PI / 2; break;//南
			case "4": FX = (float)(3*Math.PI / 4); break;//西南
			case "5": FX = (float)Math.PI; break;//西
			case "6": FX = (float)(5*Math.PI / 4); break;//西北
			case "7": FX = (float)(3*Math.PI / 2); break;//北
			case "8": FX = (float)(7*Math.PI / 4); break;//东北
		}
		//天气条件
		switch(aca.getM6()){
			case "1": 
				if(aca.getM4().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM4().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM4().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM4().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM4().equals("8")){//>6
					WDX = "D";
				}
				break;//白天-晴
			case "2": 
				if(aca.getM4().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM4().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM4().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM4().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM4().equals("8")){//>6
					WDX = "D";
				} 
				break;//白天-少云
			case "3": 
				if(aca.getM4().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM4().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM4().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM4().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM4().equals("8")){//>6
					WDX = "D";
				} 
				break;//白天-多云
			case "4":
				if(aca.getM4().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM4().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM4().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM4().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM4().equals("8")){//>6
					WDX = "D";
				}
				break;//阴天
			case "5": 
				if(aca.getM4().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM4().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM4().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM4().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM4().equals("8")){//>6
					WDX = "D";
				} 
				break;//夜间-阴
			case "6": 
				if(aca.getM4().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM4().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM4().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM4().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM4().equals("8")){//>6
					WDX = "D";
				}
				break;//夜间-多云
		}
        float C = Float.parseFloat(aca.getM2());	//C边界浓度
		float Q = Float.parseFloat(aca.getM3());//泄漏质量
		double lng = Double.parseDouble(aca.getM7());
		double lat = Double.parseDouble(aca.getM8());
		int T = Integer.parseInt(aca.getM9());	//时间 间隔10秒    判断返回值，小于1停止
		
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
        QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
        SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
        SGWebserviceSoap port = ss.getSGWebserviceSoap();  

		ArrayOfString re = port.puffPUT(C, T, Q, WDX, u, FX, lng, lat);
		String[] arrstr = re.getString().toString().split(","); 
		String Str1=arrstr[0].toString().substring(1, arrstr[0].length());
		String Str2=arrstr[1].toString().substring(1, arrstr[1].length());
		String Str3=arrstr[2].toString().substring(1, arrstr[2].length());
		if(Str3.equals("非数字")){
			Str3="0";
		}
		if(Str1.equals("null")){
			Str1=aca.getM7();
		}
		if(Str2.equals("null")){
			Str2=aca.getM8();
		}
		
		aca.setM10(String.valueOf(FX) );
		aca.setM11(String.valueOf(WDX) );
		aca.setM12(Str1 );
		aca.setM13(Str2 );
		aca.setM14(Str3 );
		save(aca);
		
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("lng", Str1);
        map.put("lat", Str2);
        map.put("r", Str3);
        
		return map;
	}
	
	@Override
	public List<Map<String, Object>> jcsaveTimeAllInfo(ACA_InstantleakageEntity aca) throws Exception{

		float FX = 0;//FX风向角度
		float u = Float.parseFloat(aca.getM4());//u风速
		String WDX = "B";//WDX稳定度
		//风向
		switch(aca.getM5()){
			case "1": FX = 0; break;//东
			case "2": FX = (float) (Math.PI / 4); break;//东南
			case "3": FX = (float)Math.PI / 2; break;//南
			case "4": FX = (float)(3*Math.PI / 4); break;//西南
			case "5": FX = (float)Math.PI; break;//西
			case "6": FX = (float)(5*Math.PI / 4); break;//西北
			case "7": FX = (float)(3*Math.PI / 2); break;//北
			case "8": FX = (float)(7*Math.PI / 4); break;//东北
		}
		//天气条件
		switch(aca.getM6()){
			case "1": 
				if(aca.getM4().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM4().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM4().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM4().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM4().equals("8")){//>6
					WDX = "D";
				}
				break;//白天-晴
			case "2": 
				if(aca.getM4().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM4().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM4().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM4().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM4().equals("8")){//>6
					WDX = "D";
				} 
				break;//白天-少云
			case "3": 
				if(aca.getM4().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM4().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM4().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM4().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM4().equals("8")){//>6
					WDX = "D";
				} 
				break;//白天-多云
			case "4":
				if(aca.getM4().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM4().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM4().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM4().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM4().equals("8")){//>6
					WDX = "D";
				}
				break;//阴天
			case "5": 
				if(aca.getM4().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM4().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM4().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM4().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM4().equals("8")){//>6
					WDX = "D";
				} 
				break;//夜间-阴
			case "6": 
				if(aca.getM4().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM4().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM4().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM4().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM4().equals("8")){//>6
					WDX = "D";
				}
				break;//夜间-多云
		}
        float C = Float.parseFloat(aca.getM2());	//C边界浓度
		float Q = Float.parseFloat(aca.getM3());//泄漏质量
		double lng = Double.parseDouble(aca.getM7());
		double lat = Double.parseDouble(aca.getM8());
		
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		int T = Integer.parseInt(aca.getM9());	//时间 间隔10秒    判断返回值，小于1停止
		
		aca.setM10(String.valueOf(FX) );
		aca.setM11(String.valueOf(WDX) );
		
        while(true)
        {
    		URL wsdlURL = SGWebservice.WSDL_LOCATION;
            QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
            SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
            SGWebserviceSoap port = ss.getSGWebserviceSoap();  
        	
        	ArrayOfString re = port.puffPUT(C, T, Q, WDX, u, FX, lng, lat);
    		String[] arrstr = re.getString().toString().split(","); 
    		String Str1=arrstr[0].toString().substring(1, arrstr[0].length());
    		String Str2=arrstr[1].toString().substring(1, arrstr[1].length());
    		String Str3=arrstr[2].toString().substring(1, arrstr[2].length());
    		if(Str3.equals("非数字")){
    			Str3="0";
    		}
    		if(Str1.equals("null")){
    			Str1=aca.getM7();
    		}
    		if(Str2.equals("null")){
    			Str2=aca.getM8();
    		}
    		
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("lng", Str1);
            map.put("lat", Str2);
            map.put("r", Str3);
            map.put("t", T);
            
            list.add(map);

    		if(Double.valueOf(Str3)<1){
                break;
    		}
    		
    		T=T+10;
        }
        
		List<Map<String,Object>> list2=new ArrayList<Map<String,Object>>();

        //存数据库
        for(Map<String, Object> map:list){
    		ACA_InstantleakageEntity aca2=new ACA_InstantleakageEntity();
    		aca2 = aca;
            aca2.setM9( map.get("t").toString() );
    		aca2.setM12(map.get("lng").toString() );
    		aca2.setM13(map.get("lat").toString() );
    		aca2.setM14(map.get("r").toString() );
    		saveIn(aca2);
            System.out.println("瞬时泄漏_id："+aca2.getID());
            map.put("id", aca2.getID());
            list2.add(map);
        }
        
        return list2;
	}
	
	public static void main(String[] args) throws Exception {   
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
	    QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
	    SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
	    SGWebserviceSoap port = ss.getSGWebserviceSoap();  
	    float C = (float)2.5;
		int T = 10;
		float Q = (float)10000;
		String WDX = "B";
		float u = 2.0f;
		float FX = (float) Math.PI;
		double lng = 120.034376;
		double lat = 31.946553;
		ArrayOfString re = port.puffPUT(C, T, Q, WDX, u, FX, lng, lat);
		String[] arrstr = re.getString().toString().split(","); 
		String Str1=arrstr[0].toString().substring(1, arrstr[0].length());
		String Str2=arrstr[1].toString().substring(1, arrstr[1].length());
		String Str3=arrstr[2].toString().substring(1, arrstr[2].length());
		if(Str3.equals("非数字")){
			Str3="0";
		}
		if(Str1.equals("null")){
			Str1="120.03";
		}
		if(Str2.equals("null")){
			Str2="31.9";
		}
		System.out.println(Str1);
		System.out.println(Str2);
		System.out.println(Str3);
		 
//		 List<Map<String,Object>> newlist=new ArrayList<Map<String,Object>>();
//		 for(int i=0;;i++){
//			 Map<String, Object> map=new HashMap<String, Object>();
//			 ArrayOfString re = port.puffPUT(C, T, Q, WDX, u, FX, lng, lat);
//	    		String[] arrstr = re.getString().toString().split(","); 
//	    		String Str1=arrstr[0].toString().substring(1, arrstr[0].length());
//	    		String Str2=arrstr[1].toString().substring(1, arrstr[1].length());
//	    		String Str3=arrstr[2].toString().substring(1, arrstr[2].length());
//	    		
//	    		if(Str3.equals("非数字")){
//	    			Str3="0";
//	    		}
//	    		if(Str1.equals("null")){
//	    			Str1="120.03";
//	    		}
//	    		if(Str2.equals("null")){
//	    			Str2="31.9";
//	    		}
//	    		map.put("lng"+i, Str1);
//	    	    map.put("lat"+i, Str2);
//	    	    map.put("t"+i, T);
//	    	    newlist.add(map);
//			 
//			 if (Double.valueOf(Str3)<1) {
//				 break;
//			 }
//			 T=T+10;
//		 }
//		 
//		 System.out.println("sss:"+newlist);
//		 System.out.println("sss1:"+newlist.get(newlist.size()-2));
//		 int s = newlist.size()-2;
//		 Map<String, Object> newmap=new HashMap<String, Object>();
//		 newmap = newlist.get(newlist.size()-2);
//		 System.out.println("newmap:"+newmap);
//		 System.out.println(newmap.get("lat"+s));
//		 while(true)
//	        {
//	    		
//	        	
//	        	ArrayOfString re = port.puffPUT(C, T, Q, WDX, u, FX, lng, lat);
//	    		String[] arrstr = re.getString().toString().split(","); 
//	    		String Str1=arrstr[0].toString().substring(1, arrstr[0].length());
//	    		String Str2=arrstr[1].toString().substring(1, arrstr[1].length());
//	    		String Str3=arrstr[2].toString().substring(1, arrstr[2].length());
//	    		
//	    		
//	    		if(Str3.equals("非数字")){
//	    			Str3="0";
//	    		}
//	    		if(Str1.equals("null")){
//	    			Str1="120.03";
//	    		}
//	    		if(Str2.equals("null")){
//	    			Str2="31.9";
//	    		}
//	    		System.out.println("Str1:"+Str1+"-----Str2=:"+Str2+"--Str3:"+Str3);
//	    		map.put("lng", Str1);
//	    	    map.put("lat", Str2);
//	    	    map.put("t", T);
//	    		if(Double.valueOf(Str3)<1){
//	                break;
//	    		}
//	    		
//	    		T=T+10;
//	    		 
//	        }
//			//System.out.println("Str1:"+Str1+"-----Str2=:"+Str2+"--Str3:"+Str3);
//		 System.out.println("amap:"+map);
	}

	@Override
	public List<Map<String, Object>> appInstantleakage(String stra1,
			String stra2, String stra3, String stra4, String stra5, String stra6,
			String stra7) throws Exception {
		float FX = 0;//FX风向角度
		float u = Float.parseFloat(stra3);//u风速
		String WDX = "B";//WDX稳定度
		//风向
		switch(stra4){
			case "1": FX = 0; break;//东
			case "2": FX = (float) (Math.PI / 4); break;//东南
			case "3": FX = (float)Math.PI / 2; break;//南
			case "4": FX = (float)(3*Math.PI / 4); break;//西南
			case "5": FX = (float)Math.PI; break;//西
			case "6": FX = (float)(5*Math.PI / 4); break;//西北
			case "7": FX = (float)(3*Math.PI / 2); break;//北
			case "8": FX = (float)(7*Math.PI / 4); break;//东北
		}
		//天气条件
		switch(stra5){
			case "1": 
				if(stra3.equals("1.8")){//<2
					WDX = "A"; 
				}else if(stra3.equals("2.5")){//2~3
					WDX = "B";
				}else if(stra3.equals("3.5")){//3~4
					WDX = "B";
				}else if(stra3.equals("5")){//4~6
					WDX = "C";
				}else if(stra3.equals("8")){//>6
					WDX = "D";
				}
				break;//白天-晴
			case "2": 
				if(stra3.equals("1.8")){//<2
					WDX = "A"; 
				}else if(stra3.equals("2.5")){//2~3
					WDX = "B";
				}else if(stra3.equals("3.5")){//3~4
					WDX = "B";
				}else if(stra3.equals("5")){//4~6
					WDX = "C";
				}else if(stra3.equals("8")){//>6
					WDX = "D";
				} 
				break;//白天-少云
			case "3": 
				if(stra3.equals("1.8")){//<2
					WDX = "A"; 
				}else if(stra3.equals("2.5")){//2~3
					WDX = "B";
				}else if(stra3.equals("3.5")){//3~4
					WDX = "B";
				}else if(stra3.equals("5")){//4~6
					WDX = "C";
				}else if(stra3.equals("8")){//>6
					WDX = "D";
				} 
				break;//白天-多云
			case "4":
				if(stra3.equals("1.8")){//<2
					WDX = "A"; 
				}else if(stra3.equals("2.5")){//2~3
					WDX = "B";
				}else if(stra3.equals("3.5")){//3~4
					WDX = "B";
				}else if(stra3.equals("5")){//4~6
					WDX = "C";
				}else if(stra3.equals("8")){//>6
					WDX = "D";
				}
				break;//阴天
			case "5": 
				if(stra3.equals("1.8")){//<2
					WDX = "A"; 
				}else if(stra3.equals("2.5")){//2~3
					WDX = "B";
				}else if(stra3.equals("3.5")){//3~4
					WDX = "B";
				}else if(stra3.equals("5")){//4~6
					WDX = "C";
				}else if(stra3.equals("8")){//>6
					WDX = "D";
				} 
				break;//夜间-阴
			case "6": 
				if(stra3.equals("1.8")){//<2
					WDX = "A"; 
				}else if(stra3.equals("2.5")){//2~3
					WDX = "B";
				}else if(stra3.equals("3.5")){//3~4
					WDX = "B";
				}else if(stra3.equals("5")){//4~6
					WDX = "C";
				}else if(stra3.equals("8")){//>6
					WDX = "D";
				}
				break;//夜间-多云
		}
        float C = Float.parseFloat(stra1);	//C边界浓度
		float Q = Float.parseFloat(stra2);//泄漏质量
		double lng = Double.parseDouble(stra6);
		double lat = Double.parseDouble(stra7);
		
		
		int T = 10;	//时间 间隔10秒    判断返回值，小于1停止
		
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
        QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
        SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
        SGWebserviceSoap port = ss.getSGWebserviceSoap();  
       
        
        List<Map<String,Object>> newlist=new ArrayList<Map<String,Object>>();
		 while(true){
			 Map<String, Object> map=new HashMap<String, Object>();
			 ArrayOfString re = port.puffPUT(C, T, Q, WDX, u, FX, lng, lat);
	    		String[] arrstr = re.getString().toString().split(","); 
	    		String Str1=arrstr[0].toString().substring(1, arrstr[0].length());
	    		String Str2=arrstr[1].toString().substring(1, arrstr[1].length());
	    		String Str3=arrstr[2].toString().substring(1, arrstr[2].length());
	    		
	    		if(Str3.equals("非数字")){
	    			Str3="0";
	    		}
	    		if(Str1.equals("null")){
	    			Str1="120.03";
	    		}
	    		if(Str2.equals("null")){
	    			Str2="31.9";
	    		}
	    		map.put("lng", Str1);
	    	    map.put("lat", Str2);
	    	    map.put("r", Str3);
	    	    map.put("t", T);
	    	    newlist.add(map);
			 
			 if (Double.valueOf(Str3)<1) {
				 break;
			 }
			 T=T+10;
		 }
		 return newlist;
	} 
	
}
