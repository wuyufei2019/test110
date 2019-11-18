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
import com.cczu.model.entity.ACA_LeakageEntity;
import com.cczu.model.dao.IAcaLeakageDao;
import com.cczu.sys.comm.utils.BaiDuMap;
import com.cczu.util.dao.BaseDao;

@Repository("AcaLeakageDao")
public class AcaLeakageDaoImpl extends BaseDao<ACA_LeakageEntity, Long> implements IAcaLeakageDao {

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ACA_LeakageEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ACA_LeakageEntity> findListInfoByUserId(long id1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> saveInfo(ACA_LeakageEntity aca) throws Exception{

		float FX = 0;//FX风向角度
		float u = Float.parseFloat(aca.getM10());//u风速
		String WDX = "B";//WDX稳定度
		//风向
		switch(aca.getM11()){
//			case "1": FX = 0; break;//东
//			case "2": FX = (float) (Math.PI / 4); break;//东南
//			case "3": FX = (float)Math.PI / 2; break;//南
//			case "4": FX = (float)(3*Math.PI / 4); break;//西南
//			case "5": FX = (float)Math.PI; break;//西
//			case "6": FX = (float)(5*Math.PI / 4); break;//西北
//			case "7": FX = (float)(3*Math.PI / 2); break;//北
//			case "8": FX = (float)(7*Math.PI / 4); break;//东北
			case "1": FX = (float)Math.PI; break;//东
			case "2": FX = (float)(5*Math.PI / 4); break;//东南
			case "3": FX = (float)(3*Math.PI / 2); break;//南
			case "4": FX = (float)(7*Math.PI / 4); break;//西南
			case "5": FX = 0; break;//西
			case "6": FX = (float) (Math.PI / 4); break;//西北
			case "7": FX = (float)Math.PI / 2; break;//北
			case "8": FX = (float)(3*Math.PI / 4); break;//东北
		}
		//天气条件
		switch(aca.getM12()){
			case "1": 
				if(aca.getM10().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM10().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM10().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM10().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM10().equals("8")){//>6
					WDX = "D";
				}
				break;//白天-晴
			case "2": 
				if(aca.getM10().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM10().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM10().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM10().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM10().equals("8")){//>6
					WDX = "D";
				} 
				break;//白天-少云
			case "3": 
				if(aca.getM10().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM10().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM10().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM10().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM10().equals("8")){//>6
					WDX = "D";
				} 
				break;//白天-多云
			case "4":
				if(aca.getM10().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM10().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM10().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM10().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM10().equals("8")){//>6
					WDX = "D";
				}
				break;//阴天
			case "5": 
				if(aca.getM10().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM10().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM10().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM10().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM10().equals("8")){//>6
					WDX = "D";
				} 
				break;//夜间-阴
			case "6": 
				if(aca.getM10().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM10().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM10().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM10().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM10().equals("8")){//>6
					WDX = "D";
				}
				break;//夜间-多云
		}
		
		float K=Float.parseFloat(aca.getM6());//Float.parseFloat(aca.getM2());//K比热系数
		float P=Float.parseFloat(aca.getM9());//Float.parseFloat(aca.getM3());//P As 压力Mpa
		float M=Float.parseFloat(aca.getM5());;//Float.parseFloat(aca.getM4());//M As M分子量
		float D=Float.parseFloat(aca.getM8());;//Float.parseFloat(aca.getM6());/D As D孔径
		int T=Integer.parseInt(aca.getM7());;//Float.parseFloat(aca.getM6());//T As T环境温度
		
		float C1 = Float.parseFloat(aca.getM2());	//C边界浓度
		float C2 = Float.parseFloat(aca.getM3());	//C边界浓度
		float C3 = Float.parseFloat(aca.getM4());	//C边界浓度
		boolean Rural = true;//rural乡村还是城市，true乡村，false城市
		float FB = (float)1;//FB，分辨率或比例尺
		double lng = Double.parseDouble(aca.getM13());
		double lat = Double.parseDouble(aca.getM14());
		//持续泄漏
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
        QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
        SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
        SGWebserviceSoap port = ss.getSGWebserviceSoap();  	
        //泄漏速度
        float Q = port.qGas(K, P, D, M, T);
        
        ArrayOfString re1 = port.plumePLngLat(C1,Q,WDX,u,FX,Rural,FB,lng,lat);
        String[] arrstr1 = re1.getString().toString().split(","); 
        String lng1 = arrstr1[0].toString().substring(1, arrstr1[0].length()-1) ;
        String lat1 = arrstr1[1].toString().substring(1, arrstr1[1].length()-1) ;
        
//        System.out.println("====================");
//        System.out.println(arrstr1[0].toString().substring(1, arrstr1[0].length()-1));
//        System.out.println(arrstr1[1].toString().substring(1, arrstr1[1].length()-1));
//        for(String str:arrstr1){
//        	System.out.println(str);
//        	System.out.println();
//        }   
		ArrayOfString re2 = port.plumePLngLat(C2,Q,WDX,u,FX,Rural,FB,lng,lat);
        String[] arrstr2 = re2.getString().toString().split(","); 
        String lng2 = arrstr2[0].toString().substring(1, arrstr2[0].length()-1) ;
        String lat2 = arrstr2[1].toString().substring(1, arrstr2[1].length()-1) ;
        
		ArrayOfString re3 = port.plumePLngLat(C3,Q,WDX,u,FX,Rural,FB,lng,lat);
        String[] arrstr3 = re3.getString().toString().split(","); 
        String lng3 = arrstr3[0].toString().substring(1, arrstr3[0].length()-1) ;
        String lat3 = arrstr3[1].toString().substring(1, arrstr3[1].length()-1) ;
        
        String[] arrlng1 = lng1.split("%");
		String[] arrlat1 = lat1.split("%");
		String[] arrlng2 = lng2.split("%");
		String[] arrlat2 = lat2.split("%");
		String[] arrlng3 = lng3.split("%");
		String[] arrlat3 = lat3.split("%");
        
		Map<String,Object> lsss= lss(lng,lat,arrlng1,arrlat1);
		Map<String,Object> lsss2= lss(lng,lat,arrlng2,arrlat2);
		Map<String,Object> lsss3= lss(lng,lat,arrlng3,arrlat3);
        
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("lng1", lng1);
        map.put("lat1", lat1);
        map.put("lng2", lng2);
        map.put("lat2", lat2);
        map.put("lng3", lng3);
        map.put("lat3", lat3);
        map.put("max1", lsss.get("dmax"));
        map.put("min1", lsss.get("dmin"));
        map.put("max2", lsss2.get("dmax"));
        map.put("min2", lsss2.get("dmin"));
        map.put("max3", lsss3.get("dmax"));
        map.put("min3", lsss3.get("dmin"));
		
		return map;
	}

	@Override
	public Map<String, Object> jcsaveInfo(ACA_LeakageEntity aca) throws Exception{

		float FX = 0;//FX风向角度
		float u = Float.parseFloat(aca.getM10());//u风速
		String WDX = "B";//WDX稳定度
		//风向
		switch(aca.getM11()){
			case "1": FX = (float)Math.PI; break;//东
			case "2": FX = (float)(5*Math.PI / 4); break;//东南
			case "3": FX = (float)(3*Math.PI / 2); break;//南
			case "4": FX = (float)(7*Math.PI / 4); break;//西南
			case "5": FX = 0; break;//西
			case "6": FX = (float) (Math.PI / 4); break;//西北
			case "7": FX = (float)Math.PI / 2; break;//北
			case "8": FX = (float)(3*Math.PI / 4); break;//东北
		}
		//天气条件
		switch(aca.getM12()){
			case "1": 
				if(aca.getM10().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM10().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM10().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM10().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM10().equals("8")){//>6
					WDX = "D";
				}
				break;//白天-晴
			case "2": 
				if(aca.getM10().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM10().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM10().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM10().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM10().equals("8")){//>6
					WDX = "D";
				} 
				break;//白天-少云
			case "3": 
				if(aca.getM10().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM10().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM10().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM10().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM10().equals("8")){//>6
					WDX = "D";
				} 
				break;//白天-多云
			case "4":
				if(aca.getM10().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM10().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM10().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM10().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM10().equals("8")){//>6
					WDX = "D";
				}
				break;//阴天
			case "5": 
				if(aca.getM10().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM10().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM10().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM10().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM10().equals("8")){//>6
					WDX = "D";
				} 
				break;//夜间-阴
			case "6": 
				if(aca.getM10().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM10().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM10().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM10().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM10().equals("8")){//>6
					WDX = "D";
				}
				break;//夜间-多云
		}
		
		float K=Float.parseFloat(aca.getM6());//Float.parseFloat(aca.getM2());//K比热系数
		float P=Float.parseFloat(aca.getM9());//Float.parseFloat(aca.getM3());//P As 压力Mpa
		float M=Float.parseFloat(aca.getM5());;//Float.parseFloat(aca.getM4());//M As M分子量
		float D=Float.parseFloat(aca.getM8());;//Float.parseFloat(aca.getM6());/D As D孔径
		int T=Integer.parseInt(aca.getM7());;//Float.parseFloat(aca.getM6());//T As T环境温度
		
		float C1 = Float.parseFloat(aca.getM2());	//C边界浓度
		float C2 = Float.parseFloat(aca.getM3());	//C边界浓度
		float C3 = Float.parseFloat(aca.getM4());	//C边界浓度
		boolean Rural = true;//rural乡村还是城市，true乡村，false城市
		float FB = (float)1;//FB，分辨率或比例尺
		double lng = Double.parseDouble(aca.getM13());
		double lat = Double.parseDouble(aca.getM14());
		//持续泄漏
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
        QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
        SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
        SGWebserviceSoap port = ss.getSGWebserviceSoap();  	
        //泄漏速度
        float Q = port.qGas(K, P, D, M, T);
        
        ArrayOfString re1 = port.plumePLngLat(C1,Q,WDX,u,FX,Rural,FB,lng,lat);
        String[] arrstr1 = re1.getString().toString().split(","); 
        String lng1 = arrstr1[0].toString().substring(1, arrstr1[0].length()-1) ;
        String lat1 = arrstr1[1].toString().substring(1, arrstr1[1].length()-1) ;
          
		ArrayOfString re2 = port.plumePLngLat(C2,Q,WDX,u,FX,Rural,FB,lng,lat);
        String[] arrstr2 = re2.getString().toString().split(","); 
        String lng2 = arrstr2[0].toString().substring(1, arrstr2[0].length()-1) ;
        String lat2 = arrstr2[1].toString().substring(1, arrstr2[1].length()-1) ;
        
		ArrayOfString re3 = port.plumePLngLat(C3,Q,WDX,u,FX,Rural,FB,lng,lat);
        String[] arrstr3 = re3.getString().toString().split(","); 
        String lng3 = arrstr3[0].toString().substring(1, arrstr3[0].length()-1) ;
        String lat3 = arrstr3[1].toString().substring(1, arrstr3[1].length()-1) ;
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("lng1", lng1);
        map.put("lat1", lat1);
        map.put("lng2", lng2);
        map.put("lat2", lat2);
        map.put("lng3", lng3);
        map.put("lat3", lat3);

        aca.setM15(String.valueOf(FX) );
        aca.setM16(String.valueOf(WDX) );
        aca.setM17(String.valueOf(Q) );
        aca.setM18(lng1 );
        aca.setM19(lat1 );
        aca.setM20(lng2 );
        aca.setM21(lat2 );
        aca.setM22(lng3 );
        aca.setM23(lat3 );
        save(aca);
        map.put("id", aca.getID());        
        
		return map;
	}
	
//	public static void main(String[] args) throws Exception {  
////        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();  
////        org.apache.cxf.endpoint.Client client = dcf.createClient("http://192.168.1.6:8001/fw/SG_webservice.asmx?wsdl");  	
////    	float C=(float) 2.5;//C边界浓度
////		float Q=(float) 90;//Q泄漏速度kg/s
////		String WDX="B";//WDX稳定度
////		float u=(float) 2;//u风速
////		float FX=(float) Math.PI;//FX风向角度
////		boolean Rural=true;//rural乡村还是城市，true乡村，false城市
////		float FB=(float) 1;//FB，分辨率或比例尺，取1
////		double lng=120.034376;
////		double lat=31.946553;
////		Object[]  obj = client.invoke("Plume_P_Lng_Lat",new Object[] {C,Q,WDX,u,FX,Rural,FB,lng,lat});
////        //调用web Service//输出调用结果  =
////		
//////			ArrayOfString aos = ArrayOfString(obj[0]); 
//////			System.out.println(ArrayOfString(obj));
//		
//		URL wsdlURL = SGWebservice.WSDL_LOCATION;
//        QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
//        SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
//        SGWebserviceSoap port = ss.getSGWebserviceSoap();  
//        //泄漏速度
//    	float K=(float)12;//Float.parseFloat(aca.getM2());//K比热系数
//		float P=(float)1400;//Float.parseFloat(aca.getM3());//P As 压力Mpa
//		float M=(float)12;//Float.parseFloat(aca.getM4());//M As M分子量
//		float D=(float)0.2;//Float.parseFloat(aca.getM6());/D As D孔径
//		int T=25;//Float.parseFloat(aca.getM6());//T As T环境温度
//		
//        float Q = port.qGas(K, P, D, M, T);
//        System.out.println(Q);
//		float C1 = (float)1.003;	//C边界浓度
//		float C2 = (float)0.05;	//C边界浓度
//		float C3 = (float)0.025;	//C边界浓度
//        
//		//float Q = 90.0f;
//		String WDX = "B";
//		float u = 2.0f;
//		float FX = (float) Math.PI;
//		boolean Rural = true;
//		float FB = 1.0f;
//		double lng = 120.034376;
//		double lat = 31.946553;
//		ArrayOfString re1 = port.plumePLngLat(C1,Q,WDX,u,FX,Rural,FB,lng,lat);
//        String[] arrstr1 = re1.getString().toString().split(","); 
//        System.out.println("====================");
//        for(String str:arrstr1){
//        	System.out.println(str);
//        	System.out.println();
//        }   
//		ArrayOfString re2 = port.plumePLngLat(C2,Q,WDX,u,FX,Rural,FB,lng,lat);
//        String[] arrstr2 = re2.getString().toString().split(","); 
//		System.out.println("====================");
//        for(String str:arrstr2){
//        	System.out.println(str);
//        	System.out.println();
//        } 
//        
//		ArrayOfString re3 = port.plumePLngLat(C3,Q,WDX,u,FX,Rural,FB,lng,lat);
//        String[] arrstr3 = re3.getString().toString().split(","); 
//        System.out.println("====================");
//        for(String str:arrstr3){
//        	System.out.println(str);
//        	System.out.println();
//        }
//		
////		ArrayOfString re1 = port.plumePLngLat(C1,Q,WDX,u,FX,Rural,FB,lng,lat);
////        String[] arrstr1 = re1.getString().toString().split(","); 
////        String lng1 = arrstr1[0].toString().substring(1, arrstr1[0].length()-1) ;
////        String lat1 = arrstr1[1].toString().substring(1, arrstr1[1].length()-1) ;
////
////		ArrayOfString re2 = port.plumePLngLat(C2,Q,WDX,u,FX,Rural,FB,lng,lat);
////        String[] arrstr2 = re2.getString().toString().split(","); 
////        String lng2 = arrstr2[0].toString().substring(1, arrstr2[0].length()-1) ;
////        String lat2 = arrstr2[1].toString().substring(1, arrstr2[1].length()-1) ;
////        
////		ArrayOfString re3 = port.plumePLngLat(C3,Q,WDX,u,FX,Rural,FB,lng,lat);
////        String[] arrstr3 = re3.getString().toString().split(","); 
////        String lng3 = arrstr3[0].toString().substring(1, arrstr3[0].length()-1) ;
////        String lat3 = arrstr3[1].toString().substring(1, arrstr3[1].length()-1) ;
////        
////        Map<String, Object> map=new HashMap<String, Object>();
////        map.put("lng1", lng1);
////        map.put("lat1", lat1);
////        map.put("lng2", lng2);
////        map.put("lat2", lat2);
////        map.put("lng3", lng3);
////        map.put("lat3", lat3);
////		
////		
////        String[] arrlng1 = map.get("lng1").toString().split("%");
////		String[] arrlat1 = map.get("lat1").toString().split("%");
////		String[] arrlng2 = map.get("lng2").toString().split("%");
////		String[] arrlat2 = map.get("lat2").toString().split("%");
////		String[] arrlng3 = map.get("lng3").toString().split("%");
////		String[] arrlat3 = map.get("lat3").toString().split("%");
////		//System.out.println("1arrlng1:"+arrlng1[0]+"--2arrlng1:"+arrlng1[arrlng1.length-1]);
////        
////		Map<String,Object> lsss= lss(lng,lat,arrlng1,arrlat1);
////		System.out.println("----"+lsss.get("dmax"));
////		Map<String,Object> lsss2= lss(lng,lat,arrlng2,arrlat2);
////		System.out.println("----"+lsss2);
////		Map<String,Object> lsss3= lss(lng,lat,arrlng3,arrlat3);
////		System.out.println("----"+lsss3);
//
//		
//        
//	}

	@Override
	public Map<String, Object> savesInfo(ACA_LeakageEntity aca)
			throws Exception {
		// TODO Auto-generated method stub
		float FX = 0;//FX风向角度
		float u = Float.parseFloat(aca.getM10());//u风速
		String WDX = "B";//WDX稳定度
		//风向
		switch(aca.getM11()){
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
		switch(aca.getM12()){
			case "1": 
				if(aca.getM10().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM10().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM10().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM10().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM10().equals("8")){//>6
					WDX = "D";
				}
				break;//白天-晴
			case "2": 
				if(aca.getM10().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM10().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM10().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM10().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM10().equals("8")){//>6
					WDX = "D";
				} 
				break;//白天-少云
			case "3": 
				if(aca.getM10().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM10().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM10().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM10().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM10().equals("8")){//>6
					WDX = "D";
				} 
				break;//白天-多云
			case "4":
				if(aca.getM10().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM10().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM10().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM10().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM10().equals("8")){//>6
					WDX = "D";
				}
				break;//阴天
			case "5": 
				if(aca.getM10().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM10().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM10().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM10().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM10().equals("8")){//>6
					WDX = "D";
				} 
				break;//夜间-阴
			case "6": 
				if(aca.getM10().equals("1.8")){//<2
					WDX = "A"; 
				}else if(aca.getM10().equals("2.5")){//2~3
					WDX = "B";
				}else if(aca.getM10().equals("3.5")){//3~4
					WDX = "B";
				}else if(aca.getM10().equals("5")){//4~6
					WDX = "C";
				}else if(aca.getM10().equals("8")){//>6
					WDX = "D";
				}
				break;//夜间-多云
		}
		
		float K=Float.parseFloat(aca.getM6());//Float.parseFloat(aca.getM2());//K比热系数
		float P=Float.parseFloat(aca.getM9());//Float.parseFloat(aca.getM3());//P As 压力Mpa
		float M=Float.parseFloat(aca.getM5());;//Float.parseFloat(aca.getM4());//M As M分子量
		float D=Float.parseFloat(aca.getM8());;//Float.parseFloat(aca.getM6());/D As D孔径
		int T=Integer.parseInt(aca.getM7());;//Float.parseFloat(aca.getM6());//T As T环境温度
		
		float C1 = Float.parseFloat(aca.getM2());	//C边界浓度
		float C2 = Float.parseFloat(aca.getM3());	//C边界浓度
		float C3 = Float.parseFloat(aca.getM4());	//C边界浓度
		boolean Rural = true;//rural乡村还是城市，true乡村，false城市
		float FB = (float)1;//FB，分辨率或比例尺
		double lng = Double.parseDouble(aca.getM13());
		double lat = Double.parseDouble(aca.getM14());
		//持续泄漏
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
        QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
        SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
        SGWebserviceSoap port = ss.getSGWebserviceSoap();  	
        //泄漏速度
        float Q = port.qGas(K, P, D, M, T);
//        if(Q>90.0f){
//        	Q = 90.0f;
//        }

        ArrayOfString re1 = port.plumePLngLat(C1,Q,WDX,u,FX,Rural,FB,lng,lat);
        String[] arrstr1 = re1.getString().toString().split(","); 
        String lng1 = arrstr1[0].toString().substring(1, arrstr1[0].length()-1) ;
        String lat1 = arrstr1[1].toString().substring(1, arrstr1[1].length()-1) ;
        
        ArrayOfString re2 = port.plumePLngLat(C2,Q,WDX,u,FX,Rural,FB,lng,lat);
        String[] arrstr2 = re2.getString().toString().split(","); 
        String lng2 = arrstr2[0].toString().substring(1, arrstr2[0].length()-1) ;
        String lat2 = arrstr2[1].toString().substring(1, arrstr2[1].length()-1) ;
        
		ArrayOfString re3 = port.plumePLngLat(C3,Q,WDX,u,FX,Rural,FB,lng,lat);
        String[] arrstr3 = re3.getString().toString().split(","); 
        String lng3 = arrstr3[0].toString().substring(1, arrstr3[0].length()-1) ;
        String lat3 = arrstr3[1].toString().substring(1, arrstr3[1].length()-1) ;
        
        String[] arrlng1 = lng1.split("%");
		String[] arrlat1 = lat1.split("%");
		String[] arrlng2 = lng2.split("%");
		String[] arrlat2 = lat2.split("%");
		String[] arrlng3 = lng3.split("%");
		String[] arrlat3 = lat3.split("%");
        
		Map<String,Object> lsss= lss(lng,lat,arrlng1,arrlat1);
		Map<String,Object> lsss2= lss(lng,lat,arrlng2,arrlat2);
		Map<String,Object> lsss3= lss(lng,lat,arrlng3,arrlat3);
		
		Map<String, Object> newmap=new HashMap<String, Object>();
		newmap.put("lng1", lng1);
		newmap.put("lat1", lat1);
        newmap.put("lng2", lng2);
        newmap.put("lat2", lat2);
        newmap.put("lng3", lng3);
        newmap.put("lat3", lat3);
		newmap.put("max1", lsss.get("dmax"));
		newmap.put("min1", lsss.get("dmin"));
		newmap.put("max2", lsss2.get("dmax"));
		newmap.put("min2", lsss2.get("dmin"));
		newmap.put("max3", lsss3.get("dmax"));
		newmap.put("min3", lsss3.get("dmin"));
        
		return newmap;
	} 
	
	public static Map<String, Object> lss(Double lng,Double lat,String[] arrlng1,String[] arrlat1){
		
		int arrd = arrlng1.length;//arrd是数组长度
		List<Map<String,Object>> list1=new ArrayList<Map<String,Object>>();
		for(int i=0;i<arrlng1.length;i++){
			Map<String, Object> map1=new HashMap<String, Object>();
			Double d = BaiDuMap.GetShortDistance(lng, lat, Double.parseDouble(arrlng1[i]), Double.parseDouble(arrlat1[i]));
			map1.put("lng", arrlng1[i]);
    	    map1.put("lat", arrlat1[i]);
    	    map1.put("d", d);
    	    list1.add(map1);
		}
		Double max;//获取长度的最大值
		Map<String, Object> map2=new HashMap<String, Object>();//用于封装最长处的点以及长度、经纬度
		Double[] a = new Double[arrd];
		for(int i=0;i<arrd;i++){
			a[i]=(Double) list1.get(i).get("d");
		}
		max=(Double) list1.get(0).get("d");
		for(int i=0;i<a.length;i++){
		if(a[i]>max){   // 判断最大值
		   max=(Double) list1.get(i).get("d");
		   map2=list1.get(i);
		 }
		}
		//System.out.println("\n数组的最大值是："+max); // 输出最大值
		//System.out.println("\n最长处的点是："+map2);//最长处的点
		
		int indenx=a.length/2;
		List<Map<String,Object>> list11=new ArrayList<Map<String,Object>>();
		Map<String, Object> map11=new HashMap<String, Object>();
		for(int i=0;i<indenx;i++){
			map11=list1.get(i);
			list11.add(map11);
		}
		
		List<Map<String,Object>> list12=new ArrayList<Map<String,Object>>();
		Map<String, Object> map12=new HashMap<String, Object>();
		for(int i=0;i<indenx;i++){
			map12=list1.get(i+indenx);
			list12.add(map12);
		}
		
		Double[] dl = new Double[indenx];
		Map<String, Object> map3=new HashMap<String, Object>();//存放里线段最远的点
		Double max1=0.0;
		for(int i=0;i<indenx;i++){
			
			dl[i] = pointToLine(lng, lat, Double.parseDouble(map2.get("lng").toString()), 
					Double.parseDouble(map2.get("lat").toString()),Double.parseDouble(list11.get(i).get("lng").toString()),
					Double.parseDouble(list11.get(i).get("lat").toString()));
			if(dl[i]>max1){
				max1=dl[i];
				map3.put("lat", list11.get(i).get("lat").toString());
				map3.put("lng", list11.get(i).get("lng").toString());
			}
			
		}
		//System.out.println("map3"+map3);
		
		Double max2=0.0;
		Map<String, Object> map4=new HashMap<String, Object>();//存放里线段最远的点
		for(int i=0;i<indenx;i++){
			
			dl[i] = pointToLine(lng, lat, Double.parseDouble(map2.get("lng").toString()), 
					Double.parseDouble(map2.get("lat").toString()),Double.parseDouble(list12.get(i).get("lng").toString()),
					Double.parseDouble(list12.get(i).get("lat").toString()));
			if(dl[i]>max2){
				max1=dl[i];
				map4.put("lat", list12.get(i).get("lat").toString());
				map4.put("lng", list12.get(i).get("lng").toString());
			}
			
		}
		
		Double d = BaiDuMap.GetShortDistance(Double.parseDouble(map4.get("lng").toString()), Double.parseDouble(map3.get("lat").toString()), Double.parseDouble(map3.get("lng").toString()), Double.parseDouble(map3.get("lat").toString()));
		
		//System.out.println("map4"+map4);
		Map<String,Object> newmap=new HashMap<String, Object>();
		newmap.put("dmax", max);
		newmap.put("dmin", d);
		
		
		return newmap;
	}
	
	private static double pointToLine(double x1, double y1, double x2, double y2, double x0,
    		double y0) {
         double space = 0;
         double a, b, c;
         a = lineSpace(x1, y1, x2, y2);// 线段的长度
         b = lineSpace(x1, y1, x0, y0);// (x1,y1)到点的距离
         c = lineSpace(x2, y2, x0, y0);// (x2,y2)到点的距离
         if (c <= 0.000001 || b <= 0.000001) {
            space = 0;
            return space;
         }
         if (a <= 0.000001) {
            space = b;
            return space;
         }
         if (c * c >= a * a + b * b) {
            space = b;
            return space;
         }
         if (b * b >= a * a + c * c) {
            space = c;
            return space;
         }
         double p = (a + b + c) / 2;// 半周长
         double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));// 海伦公式求面积
         space = 2 * s / a;// 返回点到线的距离（利用三角形面积公式求高）
         return space;
     }
    
    private static double lineSpace(double x1, double y1, double x2, double y2) {
        double lineLength = 0;
        lineLength = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2)
               * (y1 - y2));
        return lineLength;
    }
	
}
