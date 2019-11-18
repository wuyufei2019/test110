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
import com.cczu.model.entity.ACA_JetFireEntity;
import com.cczu.model.dao.IAcaJetFireDao;
import com.cczu.util.dao.BaseDao;

@Repository("AcaJetFireDao")
public class AcaJetFireDaoImpl extends BaseDao<ACA_JetFireEntity, Long> implements IAcaJetFireDao {

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ACA_JetFireEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ACA_JetFireEntity> findListInfoByUserId(long id1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> saveInfo(ACA_JetFireEntity aca) throws Exception{
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
        QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
        SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
        SGWebserviceSoap port = ss.getSGWebserviceSoap();  
    	float K=Float.parseFloat(aca.getM2());
		float P=Float.parseFloat(aca.getM3());
		float M=Float.parseFloat(aca.getM4());
		float Er=Float.parseFloat(aca.getM5());
		float D=Float.parseFloat(aca.getM6());
		int T= 25;
		float RH=(float) 0.5;
		float Q0=(float) 37.5;
		float Q1=(float) 25;
		float Q2=(float) 12.5;
		float objects1 = port.rJetFire(K,P,D,M,T,Er,RH,Q0);
		float objects2 = port.rJetFire(K,P,D,M,T,Er,RH,Q1);
		float objects3 = port.rJetFire(K,P,D,M,T,Er,RH,Q2);
//	    System.out.println(objects1);  
//	    System.out.println(objects2);  
//	    System.out.println(objects3);
//        Object[] objects1 = client.invoke("R_Jet_Fire",K,P,D,M,T,Er,RH,Q0);
//        Object[] objects2 = client.invoke("R_Jet_Fire",K,P,D,M,T,Er,RH,Q1);
//        Object[] objects3 = client.invoke("R_Jet_Fire",K,P,D,M,T,Er,RH,Q2);
//        //调用web Service//输出调用结果  
//        System.out.println(objects1[0].toString());  
//        System.out.println(objects2[0].toString());  
//        System.out.println(objects3[0].toString());  
//        aca.setM7(String.valueOf(T));//T环境温度
//        aca.setM8(String.valueOf(RH));//RH湿度 
//        aca.setM9("37.5");//死亡
//        aca.setM9_1("25");//重伤
//        aca.setM9_2("12.5");//轻伤
//        aca.setM10(String.valueOf(objects1) );
//        aca.setM10_1(String.valueOf(objects2) );
//        aca.setM10_2(String.valueOf(objects3) );
//        save(aca);
        
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("sw", objects1);
        map.put("zs", objects2);
        map.put("qs", objects3);
        
		return map;
	}

	@Override
	public Map<String, Object> jcsaveInfo(ACA_JetFireEntity aca) throws Exception{
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
        QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
        SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
        SGWebserviceSoap port = ss.getSGWebserviceSoap();  
    	float K=Float.parseFloat(aca.getM2());
		float P=Float.parseFloat(aca.getM3());
		float M=Float.parseFloat(aca.getM4());
		float Er=Float.parseFloat(aca.getM5());
		float D=Float.parseFloat(aca.getM6());
		int T= 25;
		float RH=(float) 0.5;
		float Q0=(float) 37.5;
		float Q1=(float) 25;
		float Q2=(float) 12.5;
		float objects1 = port.rJetFire(K,P,D,M,T,Er,RH,Q0);
		float objects2 = port.rJetFire(K,P,D,M,T,Er,RH,Q1);
		float objects3 = port.rJetFire(K,P,D,M,T,Er,RH,Q2);
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("sw", objects1);
        map.put("zs", objects2);
        map.put("qs", objects3);
 
        aca.setM7(String.valueOf(T));//T环境温度
        aca.setM8(String.valueOf(RH));//RH湿度 
        aca.setM9("37.5");//死亡
        aca.setM9_1("25");//重伤
        aca.setM9_2("12.5");//轻伤
        aca.setM10(String.valueOf(objects1) );
        aca.setM10_1(String.valueOf(objects2) );
        aca.setM10_2(String.valueOf(objects3) );
        save(aca);
        System.out.println("喷射火_id："+aca.getID());
        map.put("id", aca.getID());        
       
		return map;
	}

	@Override
	public List<Map<String, Object>> appJetFire(String str1, String str2,
			String str3, String str4, String str5) throws Exception {
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
        QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
        SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
        SGWebserviceSoap port = ss.getSGWebserviceSoap();  
    	float K=Float.parseFloat(str1);
		float P=Float.parseFloat(str2);
		float M=Float.parseFloat(str3);
		float Er=Float.parseFloat(str4);
		float D=Float.parseFloat(str5);
		int T= 25;
		float RH=(float) 0.5;
		float Q0=(float) 37.5;
		float Q1=(float) 25;
		float Q2=(float) 12.5;
		float objects1 = port.rJetFire(K,P,D,M,T,Er,RH,Q0);
		float objects2 = port.rJetFire(K,P,D,M,T,Er,RH,Q1);
		float objects3 = port.rJetFire(K,P,D,M,T,Er,RH,Q2);
        
		List<Map<String,Object>> newlist=new ArrayList<Map<String,Object>>();
	    Map<String,Object> map=new HashMap<String,Object>();
	    map.put("sw", objects1);
		map.put("zs", objects2);
		map.put("qs", objects3);
		newlist.add(map);

		return newlist;
	}

	
	
//	public static void main(String[] args) throws Exception {  
//        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();  
//        org.apache.cxf.endpoint.Client client = dcf.createClient("http://218.93.5.74:8001/fw/SG_webservice.asmx?wsdl");  	
//    	float K=(float) 2.5;
//		float P=(float) 90;
//		float M=(float) 730;
//		float Er=(float) 43700;
//		float D=(float) 0.5;
//		int T= 25;
//		float RH=(float) 0.5;
//		float Q0=(float) 37.5;
//		float Q1=(float) 25;
//		float Q2=(float) 12.5;
//        Object[] objects1 = client.invoke("R_Jet_Fire",K,P,D,M,T,Er,RH,Q0);
//        Object[] objects2 = client.invoke("R_Jet_Fire",K,P,D,M,T,Er,RH,Q1);
//        Object[] objects3 = client.invoke("R_Jet_Fire",K,P,D,M,T,Er,RH,Q2);
//        //调用web Service//输出调用结果  
//        System.out.println(objects1[0].toString());  
//        System.out.println(objects2[0].toString());  
//        System.out.println(objects3[0].toString());  
//	} 
	
}
