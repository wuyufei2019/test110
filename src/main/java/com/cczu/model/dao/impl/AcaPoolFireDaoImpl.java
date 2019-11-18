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
import com.cczu.model.entity.ACA_PoolFireEntity;
import com.cczu.model.dao.IAcaPoolFireDao;
import com.cczu.util.dao.BaseDao;

@Repository("AcaPoolFireDao")
public class AcaPoolFireDaoImpl extends BaseDao<ACA_PoolFireEntity, Long> implements IAcaPoolFireDao {

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ACA_PoolFireEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ACA_PoolFireEntity> findListInfoByUserId(long id1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> saveInfo(ACA_PoolFireEntity aca) throws Exception{
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
        QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
        SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
        SGWebserviceSoap port = ss.getSGWebserviceSoap(); 	
    	float Cp=Float.parseFloat(aca.getM2());//2.5;
		float Tf=Float.parseFloat(aca.getM3());//(float) 90;
		float Ds=Float.parseFloat(aca.getM4());//(float) 730;
		float Er=Float.parseFloat(aca.getM5());//(float) 43700;
		float RH=Float.parseFloat(aca.getM6());//(float) 0.5;
		float Hv=Float.parseFloat(aca.getM7());//(float) 300;
		float D= Float.parseFloat(aca.getM8());//(float) 25;
		float Q0=(float) 37.5;//死亡
		float Q1=(float) 25;//重伤
		float Q2=(float) 12.5;//轻伤
		//调用web Service//输出调用结果  
		float objects1 = port.rPoolFire(Cp,Tf,Ds,Er,RH,D,Hv,Q0);
		float objects2 = port.rPoolFire(Cp,Tf,Ds,Er,RH,D,Hv,Q1);
		float objects3 = port.rPoolFire(Cp,Tf,Ds,Er,RH,D,Hv,Q2);
//        System.out.println(objects1);  
//        System.out.println(objects2);  
//        System.out.println(objects3);  
        
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
	public Map<String, Object> jcsaveInfo(ACA_PoolFireEntity aca) throws Exception{
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
        QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
        SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
        SGWebserviceSoap port = ss.getSGWebserviceSoap(); 	
    	float Cp=Float.parseFloat(aca.getM2());//2.5;
		float Tf=Float.parseFloat(aca.getM3());//(float) 90;
		float Ds=Float.parseFloat(aca.getM4());//(float) 730;
		float Er=Float.parseFloat(aca.getM5());//(float) 43700;
		float RH=Float.parseFloat(aca.getM6());//(float) 0.5;
		float Hv=Float.parseFloat(aca.getM7());//(float) 300;
		float D= Float.parseFloat(aca.getM8());//(float) 25;
		float Q0=(float) 37.5;//死亡
		float Q1=(float) 25;//重伤
		float Q2=(float) 12.5;//轻伤
		//调用web Service//输出调用结果  
		float objects1 = port.rPoolFire(Cp,Tf,Ds,Er,RH,D,Hv,Q0);
		float objects2 = port.rPoolFire(Cp,Tf,Ds,Er,RH,D,Hv,Q1);
		float objects3 = port.rPoolFire(Cp,Tf,Ds,Er,RH,D,Hv,Q2);
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("sw", objects1);
        map.put("zs", objects2);
        map.put("qs", objects3);

        aca.setM9("37.5");//死亡
        aca.setM9_1("25");//重伤
        aca.setM9_2("12.5");//轻伤
        aca.setM10(String.valueOf(objects1) );
        aca.setM10_1(String.valueOf(objects2) );
        aca.setM10_2(String.valueOf(objects3) );
        save(aca);
        System.out.println("池火灾_id："+aca.getID());
        map.put("id", aca.getID());
        
		return map;
	}
	
	public static void main(String[] args) throws Exception {  
//        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();  
//        org.apache.cxf.endpoint.Client client = dcf.createClient("http://218.93.5.74:8001/fw/SG_webservice.asmx?wsdl");  	
//    	float Cp=(float) 2.5;
//		float Tf=(float) 90;
//		float Ds=(float) 730;
//		float Er=(float) 43700;
//		float RH=(float) 0.5;
//		float D=(float) 25;
//		float Hv=(float) 300;
//		float Q0=(float) 37.5;
//		float Q1=(float) 25;
//		float Q2=(float) 12.5;
//        Object[] objects1 = client.invoke("R_Pool_Fire",Cp,Tf,Ds,Er,RH,D,Hv,Q0);
//        Object[] objects2 = client.invoke("R_Pool_Fire",Cp,Tf,Ds,Er,RH,D,Hv,Q1);
//        Object[] objects3 = client.invoke("R_Pool_Fire",Cp,Tf,Ds,Er,RH,D,Hv,Q2);
//        //调用web Service//输出调用结果  
//        System.out.println(objects1[0].toString());  
//        System.out.println(objects2[0].toString());  
//        System.out.println(objects3[0].toString());  
	}

	@Override
	public List<Map<String, Object>> appPoolFire(String str1, String str2,
			String str3, String str4, String str5, String str6, String str7)
			throws Exception {
		// TODO Auto-generated method stub
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
        QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
        SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
        SGWebserviceSoap port = ss.getSGWebserviceSoap(); 	
    	float Cp=Float.parseFloat(str1);//比热容：Cp
		float Tf=Float.parseFloat(str2);//沸点：Tf
		float Ds=Float.parseFloat(str3);//密度：Ds
		float Er=Float.parseFloat(str4);//燃烧热：Er
		float RH=Float.parseFloat(str5);//相对湿度RH
		float Hv=Float.parseFloat(str6);//蒸发热：Hv
		float D= Float.parseFloat(str7);//液池半径：D
		float Q0=(float) 37.5;//死亡
		float Q1=(float) 25;//重伤
		float Q2=(float) 12.5;//轻伤
		//调用web Service//输出调用结果  
		float objects1 = port.rPoolFire(Cp,Tf,Ds,Er,RH,D,Hv,Q0);
		float objects2 = port.rPoolFire(Cp,Tf,Ds,Er,RH,D,Hv,Q1);
		float objects3 = port.rPoolFire(Cp,Tf,Ds,Er,RH,D,Hv,Q2);
		
		List<Map<String,Object>> newlist=new ArrayList<Map<String,Object>>();
	    Map<String,Object> map=new HashMap<String,Object>();
	    map.put("sw", objects1);
		map.put("zs", objects2);
		map.put("qs", objects3);
		newlist.add(map);

		return newlist;
	} 
	
}
