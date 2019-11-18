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
import com.cczu.model.entity.ACA_PhysicalEntity;
import com.cczu.model.dao.IAcaPhysicalDao;
import com.cczu.util.dao.BaseDao;

@Repository("AcaPhysicalDao")
public class AcaPhysicalDaoImpl extends BaseDao<ACA_PhysicalEntity, Long> implements IAcaPhysicalDao {

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ACA_PhysicalEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ACA_PhysicalEntity> findListInfoByUserId(long id1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> saveInfo(ACA_PhysicalEntity aca) throws Exception{
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
        QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
        SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
        SGWebserviceSoap port = ss.getSGWebserviceSoap(); 	
        float P=Float.parseFloat(aca.getM1());
		float V=Float.parseFloat(aca.getM2());
		float Q0=(float) 90;
		float Q1=(float) 44;
		float Q2=(float) 17;
        //调用web Service//输出调用结果  
		float objects1 = port.rPhysical2(P,V,Q0);
		float objects2 = port.rPhysical2(P,V,Q1);
		float objects3 = port.rPhysical2(P,V,Q2);
//        System.out.println(objects1);  
//        System.out.println(objects2);  
//        System.out.println(objects3);  
//        aca.setM3(String.valueOf(Q0));//死亡
//        aca.setM3_1(String.valueOf(Q1));//重伤
//        aca.setM3_2(String.valueOf(Q2));//轻伤
//        aca.setM4(String.valueOf(objects1) );
//        aca.setM4_1(String.valueOf(objects2) );
//        aca.setM4_2(String.valueOf(objects3) );
//        save(aca);
        
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("sw", objects1);
        map.put("zs", objects2);
        map.put("qs", objects3);
        
		return map;
	}

	@Override
	public Map<String, Object> jcsaveInfo(ACA_PhysicalEntity aca) throws Exception{
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
        QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
        SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
        SGWebserviceSoap port = ss.getSGWebserviceSoap(); 	
        float P=Float.parseFloat(aca.getM1());
		float V=Float.parseFloat(aca.getM2());
		float Q0=(float) 90;
		float Q1=(float) 44;
		float Q2=(float) 17;
        //调用web Service//输出调用结果  
		float objects1 = port.rPhysical2(P,V,Q0);
		float objects2 = port.rPhysical2(P,V,Q1);
		float objects3 = port.rPhysical2(P,V,Q2);
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("sw", objects1);
        map.put("zs", objects2);
        map.put("qs", objects3);
//        System.out.println(objects1);  
//        System.out.println(objects2);  
//        System.out.println(objects3);  
        aca.setM3(String.valueOf(Q0));//死亡
        aca.setM3_1(String.valueOf(Q1));//重伤
        aca.setM3_2(String.valueOf(Q2));//轻伤
        aca.setM4(String.valueOf(objects1) );
        aca.setM4_1(String.valueOf(objects2) );
        aca.setM4_2(String.valueOf(objects3) );
        save(aca);
        System.out.println("物理爆炸（压力容器爆炸）_id："+aca.getID());
        map.put("id", aca.getID());        
        
		return map;
	}

	@Override
	public List<Map<String, Object>> appPhysical(String str1, String str2)
			throws Exception {
		// TODO Auto-generated method stub
		
		URL wsdlURL = SGWebservice.WSDL_LOCATION;
        QName SERVICE_NAME = new QName("http://tempuri.org/", "SG_webservice");
        SGWebservice ss = new SGWebservice(wsdlURL, SERVICE_NAME);
        SGWebserviceSoap port = ss.getSGWebserviceSoap(); 	
        float P=Float.parseFloat(str1);//压力
		float V=Float.parseFloat(str2);//容积
		float Q0=(float) 90;
		float Q1=(float) 44;
		float Q2=(float) 17;
        //调用web Service//输出调用结果  
		float objects1 = port.rPhysical2(P,V,Q0);
		float objects2 = port.rPhysical2(P,V,Q1);
		float objects3 = port.rPhysical2(P,V,Q2);
		
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
//    	float P=(float) 2.5;
//		float V=(float) 90;
//		float Q0=(float) 90;
//		float Q1=(float) 44;
//		float Q2=(float) 17;
//        Object[] objects1 = client.invoke("R_Physical_2",P,V,Q0);
//        Object[] objects2 = client.invoke("R_Physical_2",P,V,Q1);
//        Object[] objects3 = client.invoke("R_Physical_2",P,V,Q2);
//        //调用web Service//输出调用结果  
//        System.out.println(objects1[0].toString());  
//        System.out.println(objects2[0].toString());  
//        System.out.println(objects3[0].toString());  
//	} 
	
}
