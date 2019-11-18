package com.cczu.model.dao.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.springframework.stereotype.Repository;

import com.cczu.client.sgjs.webservice.SGWebservice;
import com.cczu.client.sgjs.webservice.SGWebserviceSoap;
import com.cczu.model.entity.ACA_FireballEntity;
import com.cczu.model.dao.IAcaFireballDao;
import com.cczu.util.dao.BaseDao;

@Repository("AcaFireballDao")
public class AcaFireballDaoImpl extends BaseDao<ACA_FireballEntity, Long> implements IAcaFireballDao {

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ACA_FireballEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ACA_FireballEntity> findListInfoByUserId(long id1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> saveInfo(ACA_FireballEntity aca) throws Exception{ 	
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
		QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
		SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
		SGWebserviceSoap port = ss.getSGWebserviceSoap(); 
    	float M=Float.parseFloat(aca.getM2());
		float Er=Float.parseFloat(aca.getM3());//(float) 90;
		float RH=(float) 0.5;
		float Q0=(float) 37.5;//死亡
		float Q1=(float) 25;//重伤
		float Q2=(float) 12.5;//轻伤
		float objects1 = port.rFireBall(M,Er,RH,Q0);
		float objects2 = port.rFireBall(M,Er,RH,Q1);
		float objects3 = port.rFireBall(M,Er,RH,Q2);
	    System.out.println(objects1);  
	    System.out.println(objects2);  
	    System.out.println(objects3);
//        aca.setM4("0.5");
//        aca.setM5("37.5");//死亡
//        aca.setM5_1("25");//重伤
//        aca.setM5_2("12.5");//轻伤
//        aca.setM6(String.valueOf(objects1) );
//        aca.setM6_1(String.valueOf(objects2) );
//        aca.setM6_2(String.valueOf(objects3) );
//        save(aca);
        
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("sw", objects1);
        map.put("zs", objects2);
        map.put("qs", objects3);
        
		return map;
	}
	
	@Override
	public Map<String, Object> jcsaveInfo(ACA_FireballEntity aca) throws Exception{ 	
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
		QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
		SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
		SGWebserviceSoap port = ss.getSGWebserviceSoap(); 
        Map<String, Object> map=new HashMap<String, Object>();
    	float M=Float.parseFloat(aca.getM2());
		float Er=Float.parseFloat(aca.getM3());//(float) 90;
		float RH=(float) 0.5;
		float Q0=(float) 37.5;//死亡
		float Q1=(float) 25;//重伤
		float Q2=(float) 12.5;//轻伤
		float objects1 = port.rFireBall(M,Er,RH,Q0);
		float objects2 = port.rFireBall(M,Er,RH,Q1);
		float objects3 = port.rFireBall(M,Er,RH,Q2);
        map.put("sw", objects1);
        map.put("zs", objects2);
        map.put("qs", objects3);
		
        aca.setM4("0.5");
        aca.setM5("37.5");//死亡
        aca.setM5_1("25");//重伤
        aca.setM5_2("12.5");//轻伤
        aca.setM6(String.valueOf(objects1) );
        aca.setM6_1(String.valueOf(objects2) );
        aca.setM6_2(String.valueOf(objects3) );
        save(aca);
        System.out.println(aca.getID());
        map.put("id", aca.getID());
        
		return map;
	}
	
	public static void main(String[] args) throws Exception {  
//      JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();  
//      org.apache.cxf.endpoint.Client client = dcf.createClient("http://218.93.5.74:8001/fw/SG_webservice.asmx?wsdl");  	
//      float K=(float) 2.5;
//      float P=(float) 2.5;
//		float V=(float) 90;
//		float Q0=(float) 90;
//		float Q1=(float) 44;
//		float Q2=(float) 17;
//      Object[] objects1 = client.invoke("R_Physical_1",K,P,V,Q0);
//      Object[] objects2 = client.invoke("R_Physical_1",K,P,V,Q1);
//      Object[] objects3 = client.invoke("R_Physical_1",K,P,V,Q2);
//      //调用web Service//输出调用结果  
//      System.out.println(objects1[0].toString());  
//      System.out.println(objects2[0].toString());  
//      System.out.println(objects3[0].toString());  
      
//		URL wsdlURL = SGWebservice.WSDL_LOCATION;
//		QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
//		SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
//		SGWebserviceSoap port = ss.getSGWebserviceSoap(); 
//    	float M=(float) 901;
//		float Er=(float) 90;
//		float RH=(float) 0.5;
//		float Q0=(float) 90;
//		float Q1=(float) 44;
//		float Q2=(float) 17;
//		float objects1 = port.rFireBall(M,Er,RH,Q0);
//		float objects2 = port.rFireBall(M,Er,RH,Q1);
//		float objects3 = port.rFireBall(M,Er,RH,Q2);
//	    System.out.println(objects1);  
//	    System.out.println(objects2);  
//	    System.out.println(objects3);  
	}

	@Override
	public List<Map<String, Object>> appFire(String str1, String str2)
			throws Exception {
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
		QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
		SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
		SGWebserviceSoap port = ss.getSGWebserviceSoap(); 
    	float M=Float.parseFloat(str1);//泄露量
		float Er=Float.parseFloat(str2);//燃烧热
		float RH=(float) 0.5;
		float Q0=(float) 37.5;//死亡
		float Q1=(float) 25;//重伤
		float Q2=(float) 12.5;//轻伤
		float objects1 = port.rFireBall(M,Er,RH,Q0);
		float objects2 = port.rFireBall(M,Er,RH,Q1);
		float objects3 = port.rFireBall(M,Er,RH,Q2);
	    System.out.println(objects1);  
	    System.out.println(objects2);  
	    System.out.println(objects3);
	    
	    List<Map<String,Object>> newlist=new ArrayList<Map<String,Object>>();
	    Map<String,Object> map=new HashMap<String,Object>();
	    map.put("sw", objects1);
		map.put("zs", objects2);
		map.put("qs", objects3);
		newlist.add(map);

		return newlist;
	} 
}
